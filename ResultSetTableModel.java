import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import javax.swing.table.AbstractTableModel;

// ResultSet rows and columns are counted from 1 and JTable 
// rows and columns are counted from 0. When processing 
// ResultSet rows or columns for use in a JTable, it is 
// necessary to add 1 to the row or column number to manipulate
// the appropriate ResultSet column (i.e., JTable column 0 is 
// ResultSet column 1 and JTable row 0 is ResultSet row 1).
public class ResultSetTableModel extends AbstractTableModel {
    private Connection userConnect;
    private Connection logConnect;
    private PreparedStatement statement;
    private ResultSet resultSet;
    private ResultSetMetaData metaData;
    private int numberOfRows;
    private String curUser;

    // keep track of database connection status
    private boolean connectedToDatabase = false;

    // constructor initializes resultSet and obtains its meta data object;
    // determines number of rows
    public ResultSetTableModel(String query, Connection userConn, Connection logConn) 
            throws SQLException, ClassNotFoundException {
        userConnect = userConn;
        logConnect = logConn;

        statement = userConnect.prepareStatement(query, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
        curUser = userConnect.getMetaData().getUserName();

        connectedToDatabase = true;

        if (query.toLowerCase().startsWith("select")) {
            setQuery();
        } else {
            setUpdate();
        }
    }

    // get class that represents column type
    public Class getColumnClass(int column) throws IllegalStateException {
        if (!connectedToDatabase) 
            throw new IllegalStateException("Not Connected to Database");
        
        try {
            String className = metaData.getColumnClassName(column + 1);
            return Class.forName(className);
        } catch (Exception exception) {
            exception.printStackTrace();
        }
        return Object.class;
    }

    // get number of columns in ResultSet
    public int getColumnCount() throws IllegalStateException {
        if (!connectedToDatabase) 
            throw new IllegalStateException("Not Connected to Database");
        
        try {
            return metaData.getColumnCount();
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }
        return 0;
    }

    // get name of a particular column in ResultSet
    public String getColumnName(int column) throws IllegalStateException {
        if (!connectedToDatabase) 
            throw new IllegalStateException("Not Connected to Database");
        
        try {
            return metaData.getColumnName(column + 1);
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }
        return "";
    }

    // return number of rows in ResultSet
    public int getRowCount() throws IllegalStateException {
        if (!connectedToDatabase) 
            throw new IllegalStateException("Not Connected to Database");
        
        return numberOfRows;
    }

    // obtain value in particular row and column
    public Object getValueAt(int row, int column) throws IllegalStateException {
        if (!connectedToDatabase) 
            throw new IllegalStateException("Not Connected to Database");
        
        try {
            resultSet.next();  /* fixes a bug in MySQL/Java with date format */
            resultSet.absolute(row + 1);
            return resultSet.getObject(column + 1);
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }
        return "";
    }

    // set new database query string
    public void setQuery() throws SQLException, IllegalStateException {
        if (!connectedToDatabase) 
            throw new IllegalStateException("Not Connected to Database");
        
        resultSet = statement.executeQuery();
        metaData = resultSet.getMetaData();
        
        resultSet.last(); // move to last row
        numberOfRows = resultSet.getRow(); // get row number      
        
        // notify JTable that model has changed
        fireTableStructureChanged();

		String logQuery = "INSERT INTO operationscount (login_username, num_queries, num_updates) VALUES(?, 1, 0) ON DUPLICATE KEY UPDATE num_queries = num_queries + 1;";

		PreparedStatement logStatement = logConnect.prepareStatement(logQuery);
		logStatement.setString(1, curUser);
		logStatement.executeUpdate();
    }

    // set new database update-query string
    public void setUpdate() throws SQLException, IllegalStateException {
        if (!connectedToDatabase) 
            throw new IllegalStateException("Not Connected to Database");
        
        statement.executeUpdate();

		String logQuery = "INSERT INTO operationscount (login_username, num_queries, num_updates) VALUES(?, 0, 1) ON DUPLICATE KEY UPDATE num_updates = num_updates + 1;";

		PreparedStatement logStatement = logConnect.prepareStatement(logQuery);
		logStatement.setString(1, curUser);
		logStatement.executeUpdate();
    }

    // close Statement and Connection
    public void disconnectFromDatabase() {
        if (!connectedToDatabase) 
            return;
        
        try {
            statement.close();
            userConnect.close();
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        } finally {
            // update database connection status
            connectedToDatabase = false;
        }
    }
}
