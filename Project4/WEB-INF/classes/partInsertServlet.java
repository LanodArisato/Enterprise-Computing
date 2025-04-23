import java.io.*;
import java.sql.*;
import java.util.*;
import com.mysql.cj.jdbc.MysqlDataSource;
import jakarta.servlet.*;
import jakarta.servlet.http.*;

public class partInsertServlet extends HttpServlet
{
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException 
    {
        try 
        {
            String pnum = req.getParameter("pnum");
            String pname = req.getParameter("pname");
            String city = req.getParameter("city");
            int weight = Integer.parseInt(req.getParameter("weight"));
            String color = req.getParameter("color");

            Properties tmp = new Properties();
            String propPath = getServletContext().getRealPath("/WEB-INF/lib/dataentry.properties");
            MysqlDataSource dataSource = new MysqlDataSource();

            tmp.load(new FileInputStream(propPath));
            dataSource.setUrl(tmp.getProperty("MYSQL_DB_URL"));
            dataSource.setUser(tmp.getProperty("MYSQL_DB_USERNAME"));
            dataSource.setPassword(tmp.getProperty("MYSQL_DB_PASSWORD"));

            Connection connect = dataSource.getConnection();

            String sqlCommand = "insert into parts values (? , ? , ?, ?, ?)";

            PreparedStatement statement = connect.prepareStatement(sqlCommand);
            statement.setString(1, pnum);
            statement.setString(2, pname);
            statement.setString(3, color);
            statement.setInt(4, weight);
            statement.setString(5, city);
            

            statement.executeUpdate();

            req.setAttribute("message", "New part record: (" + pnum + ", " + pname + ", " + color + ", " + weight + ", " + city +") - successfully entered to database.");

        }
        catch (Exception e)
        {
            req.setAttribute("error", "Error: " + e.getMessage());
        }

        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/dataEntryHome.jsp");
        dispatcher.forward(req, resp);
    }
}
