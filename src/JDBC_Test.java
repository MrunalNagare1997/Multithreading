import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.util.Scanner;

public class JDBC_Test {
    public static void main(String args[]) {
        String username,name,password;
        Connection con = null;
        Statement stm = null;
        Scanner sc= new Scanner(System.in);
        try {
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/mysql", "root", "");
            if(con!= null)
            {
                System.out.println("Connection successful...");
            }

            stm= con.createStatement();
            stm.executeUpdate("use LoginData");

            System.out.println("Enter the user name...");
            username=sc.next();
            System.out.println("Enter the password...");
            password=sc.next();
            System.out.println("Enter the name...");
            name=sc.next();

            stm.executeUpdate("insert into Users values ('"+username+"','"+password+"','"+name+"')");

        } catch (Exception e) {
            System.out.println("something went wrong...");
            e.printStackTrace();
        }
    }


}
