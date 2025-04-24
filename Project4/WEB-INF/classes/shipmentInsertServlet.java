/* Name: Landon Morjal
Course: CNT 4714 – Spring 2025 – Project Four
Assignment title: A Three-Tier Distributed Web-Based Application
Date: April 23, 2025
*/
import java.io.*;
import java.sql.*;
import java.util.*;
import com.mysql.cj.jdbc.MysqlDataSource;
import jakarta.servlet.*;
import jakarta.servlet.http.*;

public class shipmentInsertServlet extends HttpServlet
{
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException 
    {
        try 
        {
            String snum = req.getParameter("snum");
            String pnum = req.getParameter("pnum");
            String jnum = req.getParameter("jnum");
            int quantity = Integer.parseInt(req.getParameter("quantity"));

            Properties tmp = new Properties();
            String propPath = getServletContext().getRealPath("/WEB-INF/lib/dataentry.properties");
            MysqlDataSource dataSource = new MysqlDataSource();

            tmp.load(new FileInputStream(propPath));
            dataSource.setUrl(tmp.getProperty("MYSQL_DB_URL"));
            dataSource.setUser(tmp.getProperty("MYSQL_DB_USERNAME"));
            dataSource.setPassword(tmp.getProperty("MYSQL_DB_PASSWORD"));

            Connection connect = dataSource.getConnection();

            String sqlCommand = "insert into shipments values (? , ? , ?, ?)";

            PreparedStatement statement = connect.prepareStatement(sqlCommand);
            statement.setString(1, snum);
            statement.setString(2, pnum);
            statement.setString(3, jnum);
            statement.setInt(4, quantity);

            statement.executeUpdate();

            if (quantity >= 100)
            {
                String suppliersUpdated = "update suppliers set status = status + 5 where snum = ?";
                PreparedStatement updateSupplier = connect.prepareStatement(suppliersUpdated);

                updateSupplier.setString(1, snum);
                updateSupplier.executeUpdate();

                req.setAttribute("message", "New shipment record: (" + snum + ", " + pnum + ", " + jnum + ", " + quantity +") - successfully entered to database. Business logic triggered.");
            }
            else
            {
                req.setAttribute("message", "New shipment record: (" + snum + ", " + pnum + ", " + jnum + ", " + quantity +") - successfully entered to database.");
            }
        }
        catch (Exception e)
        {
            req.setAttribute("error", "Error: " + e.getMessage());
        }

        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/dataEntryHome.jsp");
        dispatcher.forward(req, resp);
    }
}
