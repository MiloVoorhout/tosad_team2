package webservices.resources;

import tosad.database.OracleDatabase;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;


@WebServlet("/TestDatabase")
public class TestDatabase extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        String text = null;
        try {
            text = OracleDatabase.getTableData();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        out.println(text);
    }
}
