/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */


/**
 *
 * @author Taryan
 */
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.sql.rowset.CachedRowSet;
import javax.sql.rowset.RowSetProvider;
public class DatabaseManager 
{
    //declaring three important connection parameter:path to db+driver,user,password
    private String dbUrl;
    private String dbUser;
    private String dbPassword;
    
    /**
     * Constructor to initialize database credentials.
     * assigning connection variables with correct values
     */
    public DatabaseManager() {
        this.dbUrl = "jdbc:mysql://localhost:3306/highschool_db";
        this.dbUser = "root";
        this.dbPassword = "0000";
    }
    /**
     * a method to establish a connection and return a live connection object
     */
    private Connection getConnection() throws SQLException 
    {
        return DriverManager.getConnection(dbUrl, dbUser, dbPassword);
    }
    
    // ==========================================
    // METHOD TO CREATE or INSERT NEW RECORD
    // ==========================================
    public void createRecord(String sqlStatementToInsert) 
    {
        // (Not PreparedStatement)
        String sqlQuery = sqlStatementToInsert;
        
        try 
            (Connection conn = getConnection();
             Statement stmt = conn.createStatement()) 
        {
            
            int rowsAffected = stmt.executeUpdate(sqlQuery);
            System.out.println("Inserted " + rowsAffected + " row(s) successfully.");
            
        } 
        catch (SQLException e) 
        {
            System.err.println("Error creating record: " + e.getMessage());
        }
    }
    // ==========================================
    // METHOD TO READ DATA
    // ==========================================
    public CachedRowSet extractData(String sql) 
    {
        CachedRowSet resultdata = null;
        
        // Use try-with-resources to automatically close connection and statement
        // Executes raw SQL directly
        try 
            (Connection conn = getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) 
        { 
            // Safely copies data to memory        
            resultdata = RowSetProvider.newFactory().createCachedRowSet();
            resultdata.populate(rs); 
            
        } 
        catch (SQLException e) {
            System.err.println("Error reading users: " + e.getMessage());
        }
        
        return resultdata; 
    }


    // ==========================================
    // METHOD TO UPDATE RECORD
    // ==========================================
    public void updateRecord(String sqlStatementToUpdate) 
    {
        String sqlQuery = sqlStatementToUpdate;
        
        try 
            (Connection conn = getConnection();
             Statement stmt = conn.createStatement()) 
        {
            
            int rowsAffected = stmt.executeUpdate(sqlQuery);
            System.out.println("Updated " + rowsAffected + " row(s) successfully.");
            
        } 
        catch (SQLException e)
        {
            System.err.println("Error updating user: " + e.getMessage());
        }
    }

    // ==========================================
    // METHOD TO DELETE
    // ==========================================
    public void deleteRecord(String sqlStatementToDelete) 
    {
            String sqlQuery = sqlStatementToDelete;     
        try 
            (Connection conn = getConnection();
             Statement stmt = conn.createStatement())
        {
            
            int rowsAffected = stmt.executeUpdate(sqlQuery);
            System.out.println("Deleted " + rowsAffected + " row(s) successfully.");
            
        } catch (SQLException e)
        {
            System.err.println("Error deleting user: " + e.getMessage());
        }
    }
   public  ResultSet getData(String sqlTable) {
        
        String sqlQuery = sqlTable;
            try {
                Connection conn = getConnection();
                Statement statement = conn.createStatement();
                return statement.executeQuery(sqlQuery);
            } 
            catch (Exception e) 
            {
                e.printStackTrace();
            }
        
        return null;
    }
     public  boolean userAllowed(String sql) {
        
           try 
           {
                Connection conn = getConnection();
                Statement statement = conn.createStatement();
                ResultSet rs = statement.executeQuery(sql);
                    
                if (rs.next())
                { 
                    return true;
                    
                }
                else   
                {
                    return false;
                }
           }
                catch (SQLException e)
                        {
                  System.err.println("Error reading users: " + e.getMessage());
                
            }
        
        return false;
    }
    

     }