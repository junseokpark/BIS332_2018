import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import java.sql.*;

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
    private Connection conn = null;
    private Statement stmt = null;
    private ResultSet rs = null;
    private String sql = "";
    private String IP_ADDRESS="biostar.kaist.ac.kr";
    private String PORT="5438";
    private String DB_NAME="postgres"; // Student Number
    private String ID="postgres"; //Student Number
    private String Passwd="bislaprom3!";  //Password

    @RequestMapping("/jdbcexample")
    public String jdbcexample() throws Exception {
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

}
