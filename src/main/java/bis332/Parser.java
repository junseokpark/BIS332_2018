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
        String IP_ADDRESS="biostar.kaist.ac.kr";
        String PORT="5432";
        String DB_NAME="uta"; // Student Number
        String ID= "uta"; //Student Number
        String Passwd= "tabislaprom3";  //Password

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


    public String queryGeneration(String query, String[] lineArray, String tableName) {
        String inputValues = "";
        for (int i = 0; i < lineArray.length; i++) {
            String value = lineArray[i];
            value = value.replaceAll("\"", "\\\"").replaceAll("'", "''");

            if (value.matches("[0-9]+") && value.length() >= 1) {
                inputValues = inputValues + value + ",";
            } else {
                inputValues = inputValues + "'" + value + "',";
            }

        }
        return query + inputValues.substring(0,inputValues.length()-1) + ")";

    }

    // Insert data to table from read line string
    public void insertLineToTableRow(Statement stmt,String tableName,String line) throws Exception {
        String[] lineArray = line.split("\t");

        String query = "insert into  " + tableName + " VALUES (";
        query = queryGeneration(query,lineArray, tableName);

        try {

            // Insert data to table
            stmt.executeUpdate(query);

        }   catch(SQLException se){
            //Handle errors for JDBC
            se.printStackTrace();
        }   catch(Exception e){
            //Handle errors for Class.forName
            e.printStackTrace();
        }

    }

    // Load a file and read line by line from the file, then insert each line to selected database table
    public void insertDataToTable(String fileNameWithLocation, String tableName, Boolean headerIncluded) throws Exception {
        String line;
        Connection conn = null;
        Statement stmt = null;

        try {

            //File Read
            BufferedReader bufferreader = new BufferedReader(new FileReader(fileNameWithLocation));
            conn = PostgresqlConnector();
            stmt = conn.createStatement();

            line = bufferreader.readLine();

            if (headerIncluded = true) {line = bufferreader.readLine();}
            while (line != null) {

                // Insert Line to Database Table
                insertLineToTableRow(stmt,tableName, line);
                // Read line
                line = bufferreader.readLine();
            }


        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
        } catch (IOException ex) {
            ex.printStackTrace();
        }  finally{
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

    public static void main(String [] args) throws Exception {
        Parser parser = new Parser();
        parser.insertDataToTable(args[0],args[1],true);

    }

}
