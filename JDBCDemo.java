import java.sql.*;
class JDBCDemo
{
 public static void main(String ar[])throws Exception
{
  Class.forName("oracle.jdbc.driver.OracleDriver");
  System.out.println("Driver Loaded");
  Connection con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","manager");
System.out.println("Connected");
}
}