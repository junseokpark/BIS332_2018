package bis332;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.*;

public class Parser {

    // Database connector
    private Connection PostgresqlConnector() throws Exception {
        Connection conn = null;
        String IP_ADDRESS="";
        String PORT="";
        String DB_NAME=""; // Student Number
        String ID= ""; //Student Number
        String Passwd= "";  //Password

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


    // Insert data to table from read line string
    public void insertLineToTableRow(String tableName,String line) throws Exception {
        String[] lineArray = line.split("\t");
        String query = "insert into  " + tableName + " VALUES (";

        // Build insert query per a table
        switch(tableName) {
            case "tableA" :
                query = query + " '"+lineArray[0]+"',"+lineArray[1]+")";
                break;
            case "tableB" :
                query = query + " '"+lineArray[0]+"',"+lineArray[1]+")";
                break;
            default :
                query = "invalid table name";
                break;
        }

        Connection conn = null;
        Statement stmt = null;

        try {
            conn = PostgresqlConnector();
            stmt = conn.createStatement();

            // Insert data to table
            stmt.executeUpdate(query);

        }   catch(SQLException se){
            //Handle errors for JDBC
            se.printStackTrace();
        }   catch(Exception e){
            //Handle errors for Class.forName
            e.printStackTrace();
        }   finally{
            //finally block used to close resources
            try{
                if(stmt!=null)
                    conn.close();
            }catch(SQLException se){
            }// do nothing
            try{
                if(conn!=null)
                    conn.close();
            }catch(SQLException se){
                se.printStackTrace();
            }//end finally try
        }//end try

    }

    // Load a file and read line by line from the file, then insert each line to selected database table
    public void insertDataToTable(String fileNameWithLocation, String tableName, Boolean headerIncluded) throws Exception {
        String line;
        Integer lineNo = 0;

        try {

            //File Read
            BufferedReader bufferreader = new BufferedReader(new FileReader(fileNameWithLocation));
            line = bufferreader.readLine();

            while (line != null) {
                // Read line
                line = bufferreader.readLine();

                // Insert Line to Database Table
                if (headerIncluded = true) {
                    if (lineNo > 1) { insertLineToTableRow(tableName, line); }
                } else { insertLineToTableRow(tableName, line); }
                lineNo++;
            }

        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
        } catch (IOException ex) {
            ex.printStackTrace();
        }

    }

    public static void main(String [] args) throws Exception {
        Parser parser = new Parser();
        parser.insertDataToTable(args[0],args[1],true);

    }

}
