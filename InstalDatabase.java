/* November 12, 2019
 * This class will be run only one time to create database and table
*/
package financialassist;

public class InstalDatabase
{
    public static void main(String[] args)
    {
        //Create a database
        String dbName = "FinancialAssistant";
        Database database = new Database();
        database.createDatabase(dbName);
        
        //Create tables
        String spendingTable = "CREATE TABLE Spending (OrderNumber int, Day int, Month int, Amount int, Reason varchar(100), Payer varchar(30))";
        database.createTable(spendingTable, dbName);
        String limitTable = "CREATE TABLE Limit(Limit int)";
        database.createTable(limitTable, dbName);
        String nameTable = "CREATE TABLE Names (Name varchar(30))";
        database.createTable(nameTable, dbName);
        String peopleTable = "CREATE TABLE People (OrderNumber int, Name varchar(30), Day int, Month int, Paid int, Spending int, Reason varchar(100))";
        database.createTable(peopleTable, dbName);
    }
}
