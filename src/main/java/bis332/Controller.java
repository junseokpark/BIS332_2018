package bis332;

import bis332.objects.CalculatorObject;
import bis332.objects.GeneInfoObject;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@RestController
public class Controller {

    private final String header = "<html><title>bis332 first example</title><body>";
    private final String footer = "</body></html>";


    // example #1 : Root Page view
    @RequestMapping("/")
    public String root() {
        String webContents = header +
                "Hello BiS332 Students!" +
                footer;
        return webContents;
    }

    // example #2 : Calculator Page view
    @RequestMapping("/calculator")
    public String calculator() {
        String webContents = header +
                "<h1>Form</h1>\n" +
                "        <h2>Input two integers</h2>\n" +
                "        <form action=\"/calculatorResult\" method=\"post\">\n" +
                "            <p>first Number: <input type=\"text\" name=\"firstNo\" /></p>\n" +
                "            <p>second Number: <input type=\"text\" name=\"secondNo\" /></p>\n" +
                "            <p><input type=\"submit\" value=\"Submit\" />\n" +
                "        </form>" +
                footer;

        return webContents;
    }


    // example #3: Calculator Result Page process and result
    @RequestMapping("/calculatorResult")
    public String calculatorResult(@RequestParam(value="firstNo", defaultValue ="0") int firstNo, @RequestParam(value="secondNo", defaultValue="0") int secondNo) {

        int sum = firstNo + secondNo;

        String webContents = header +
                "your input firstNo is " + Integer.toString(firstNo) + "<br>" +
                "your input secondNo is " + Integer.toString(secondNo) + "<br>" +
                "Sum is " + Integer.toString(sum) + "<br>" +
                footer;

        return webContents;

    }

    // example #4 : JDBC Page Test
    @RequestMapping("/jdbcexample")
    public String jdbcexample() throws Exception {
        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;
        String sql = "";
        String IP_ADDRESS="";
        String PORT="";
        String DB_NAME=""; // Student Number
        String ID= ""; //Student Number
        String Passwd="";  //Password

        String webContents = "";

        try {
            Class.forName("org.postgresql.Driver");
            conn = DriverManager.getConnection("jdbc:postgresql://" + IP_ADDRESS + ":" + PORT + "/" + DB_NAME, ID, Passwd);
            sql = "select geneid, symbol, synonyms from gene_info limit 100";
            stmt = conn.createStatement();
            rs = stmt.executeQuery(sql);

            webContents = header + "<table><tr><td>geneid</td><td>symbol</td><td>synonyms</td></tr>";


            while(rs.next()) {
                webContents += "<tr>";
                webContents += "<td>"+rs.getString("geneid")+"</td>";
                webContents += "<td>"+rs.getString("symbol")+"</td>";
                webContents += "<td>"+rs.getString("synonyms")+"</td>";
                webContents += "</tr>";
            }

            webContents = webContents + "</table>";

            stmt.close();
            conn.close();

        }
        catch (SQLException e) {
            System.err.println(e.getMessage());
        }
        catch (Exception e) {
            System.err.println(e.getMessage());
        }

        webContents = webContents + footer;

        return webContents;

    }

    // Example #5 : restCalculatorResult
    @RequestMapping("/restCalculatorResult")
    public CalculatorObject restCalculatorResult(@RequestParam(value="firstNo", defaultValue ="0") int firstNo,
                                                 @RequestParam(value="secondNo", defaultValue="0") int secondNo,
                                                 @RequestParam(value="string", defaultValue="text") String text ) {
        int sum = firstNo + secondNo;
        String upperCase = text.toUpperCase();

        CalculatorObject obj = new CalculatorObject(firstNo,secondNo,sum, upperCase);
        return obj;
    }


    // Example #6 : RestJDBCResult
    // Example #6-1 : JDBC function
    private Connection PostgresqlConnector() throws Exception {
        Connection conn = null;
        String IP_ADDRESS="";
        String PORT="";
        String DB_NAME=""; // Student Number
        String ID= ""; //Student Number
        String Passwd= "";  //Password

        String webContents = "";

        try {
            Class.forName("org.postgresql.Driver");
            conn = DriverManager.getConnection("jdbc:postgresql://" + IP_ADDRESS + ":" + PORT + "/" + DB_NAME, ID, Passwd);

        }
        catch (SQLException e) {
            System.err.println(e.getMessage());
        }
        catch (Exception e) {
            System.err.println(e.getMessage());
        }

        return conn;
    }

    // Example #6-2 : JDBC RestfulAPI
    @RequestMapping("/restjdbcexample")
    public List<GeneInfoObject> restjdbcexample(@RequestParam(value="geneid", defaultValue ="0") int geneid) throws Exception {
        Connection conn = PostgresqlConnector();

        String sql = "";
        if (geneid == 0 ) {
            sql = "select geneid, symbol, synonyms from gene_info limit 100";
        } else {
            sql = "select geneid, symbol, synonyms from gene_info where geneid = '"+Integer.toString(geneid)+"' limit 100";
        }

        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery(sql);

        List<GeneInfoObject> geneInfoList = new ArrayList<GeneInfoObject>();

        while(rs.next()) {
            geneInfoList.add(new GeneInfoObject(rs.getString("geneid"),rs.getString("symbol"),rs.getString("synonyms")));
        }

        stmt.close();
        conn.close();

        return geneInfoList;

    }



}
