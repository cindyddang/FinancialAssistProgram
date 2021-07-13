/* November 12, 2019
 * Class to access database
*/
package financialassist;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;


public class Database
{
    String dbName;
    Connection connection;
    ArrayList <ArrayList<String>> dataList;
    
    public Database()
    {
        dbName = "";
        connection = null;
        dataList = null;
    }
    
    public Database(String dbName)
    {
        setDbName(dbName);
        setConnection();
        dataList = null;
    }
    
    //setters
    public void setDbName(String dbName)
    {
        this.dbName = dbName;
    }
    
    public void setConnection()
    {
        String connectionURL = "jdbc:derby:" + this.dbName;
        //Find the driver and make connection
        try
        {
            Class.forName("org.apache.derby.jdbc.EmbeddedDriver");
            this.connection = DriverManager.getConnection(connectionURL);
        }
        catch(ClassNotFoundException cnfe)
        {
            System.out.println("CLass for name not found");
            cnfe.printStackTrace(System.err);
        }
        catch(SQLException se)
        {
            System.out.println("SQL connection error");
            se.printStackTrace(System.err);
        }
    }
    
    public void setDataList(ArrayList<ArrayList<String>> dataList)
    {
        this.dataList = dataList;
    }
    
    //getters
    public String getDbName()
    {
        return this.dbName;
    }
    
    public Connection getConnection()
    {
        return this.connection;
    }
    
    public ArrayList<ArrayList<String>> getDataList(String tableName, String[] tableHeaders)
    {
        int columnLength = tableHeaders.length;
        Statement s = null;
        ResultSet rs = null;
        String dbQuery = "SELECT * FROM " + tableName;
        this.dataList = new ArrayList<>();
        
        //read the data
        try
        {
            s = this.connection.createStatement();
            rs = s.executeQuery(dbQuery);
            
            while(rs.next())
            {
                ArrayList<String> row = new ArrayList<>();
                for (int i =0; i<columnLength; i++)
                {
                    row.add(rs.getString(tableHeaders[i]));
                }
                this.dataList.add(row);
            }
        }
        catch (SQLException se)
        {
            System.out.println("SQL Error: Not able to get data");
            se.printStackTrace(System.err);
        }
                
        return this.dataList;
    }
    
    public void createDatabase(String newDbName)
    {
        setDbName(newDbName);
        String connectionURL = "jdbc:derby:" + this.dbName + ";create=true";
        this.connection = null;
        //Find the driver and make connection
        try
        {
            Class.forName("org.apache.derby.jdbc.EmbeddedDriver");
            this.connection = DriverManager.getConnection(connectionURL);
            System.out.println("Create a new Database");
        }
        catch (ClassNotFoundException cnfe)
        {
            System.out.println("Class for name not found");
            cnfe.printStackTrace(System.err);
        }
        catch (SQLException se)
        {
        System.out.println("SQL connection error");
        se.printStackTrace(System.err);
        }
    }
    
    public void createTable(String newTable, String dbName)
    {
        System.out.println(newTable);
        setDbName(dbName);
        setConnection();
        Statement s = null;
        
        try
        {
            s = this.connection.createStatement();
            s.execute(newTable);
            System.out.println("New table created!");
        }
        catch (SQLException se)
        {
            System.out.println("SQL error creating table");
            se.printStackTrace(System.err);
        }
    }
    
    public Object[][] to2dArray(ArrayList<ArrayList<String>> data)
    {
        int columnCount = data.get(0).size();
        Object[][] dataList = new Object[data.size()][columnCount];
        
        for (int i=0; i<data.size(); i++)
        {
            ArrayList<String> row = data.get(i);
            for (int j=0; j<columnCount; j++)
            {
                dataList[i][j]= row.get(j);
            }
        }
        
        return dataList;
    }
}
