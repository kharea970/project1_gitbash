import java.sql.*;
import java.util.Scanner;

class BankingApplication{
    public static void main(String args[]){
        try{
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/idfc","root","abc456");
            System.out.println("Connected");

            Scanner sc = new Scanner(System.in);
            while(true){
                System.out.println("press  1.To Create Account 2.Transfer Amt 3.Withdraw 4.Deposit 5.exit");
                int n = sc.nextInt();
                if(n==1){

                    System.out.println("Enter Account No");
                    int an = sc.nextInt();
                    System.out.println("Enter opening balance");
                    float b = sc.nextFloat();
                    PreparedStatement stmt = con.prepareStatement("insert into BankingApplication values(?,?)");
                    stmt.setInt(1,an);
                    stmt.setFloat(2,b);
                    stmt.executeUpdate();
                    System.out.println("Account Created");


                }else if(n==2){
                    float b1=0;
                    float b2=0;
                    System.out.println("Enter ur Account No");
                    int a1 = sc.nextInt();
                    PreparedStatement stmt = con.prepareStatement("select Balance from BankingApplication where AccountNo=?");
                    stmt.setInt(1,a1);
                    ResultSet rs = stmt.executeQuery();
                    while(rs.next()){
                        b1 = rs.getFloat(1);
                    }
                    System.out.println("Enter  Account No to transfer to");
                    int a2 = sc.nextInt();
                    PreparedStatement stmt2 = con.prepareStatement("select Balance from BankingApplication where AccountNo=?");
                    stmt2.setInt(1,a2);
                    ResultSet rs1 = stmt2.executeQuery();
                    while(rs1.next()){
                        b2 = rs1.getFloat(1);
                    }
                    System.out.println("Enter  Amount");
                    float am = sc.nextFloat();
                    PreparedStatement stmt3 =  con.prepareStatement("update BankingApplication set balance = ? where accountNo = ?");
                    float ans1 = b1-am;
                    float ans2 = b2+am;
                    stmt3.setFloat(1,ans1);
                    stmt3.setInt(2,a1);
                    long nor = stmt3.executeUpdate();

                    PreparedStatement stmt4 =  con.prepareStatement("update BankingApplication set balance = ? where accountNo = ?");
                    stmt4.setFloat(1,ans2);
                    stmt4.setInt(2,a2);
                     nor = stmt4.executeUpdate();

                    System.out.println("transfer done");


                }else if(n==3){
                    float b1=0;
                    System.out.println("Enter ur Account No");
                    int a1 = sc.nextInt();
                    PreparedStatement stmt = con.prepareStatement("select Balance from BankingApplication where AccountNo=?");
                    stmt.setInt(1,a1);
                    ResultSet rs = stmt.executeQuery();
                    while(rs.next()){
                        b1 = rs.getFloat(1);
                    }
                    System.out.println("Enter  Amount");
                    float am = sc.nextFloat();
                    float ans1 = b1-am;
                    PreparedStatement stmt3 =  con.prepareStatement("update BankingApplication set balance = ? where accountNo = ?");
                    stmt3.setFloat(1,ans1);
                    stmt3.setInt(2,a1);
                    long nor = stmt3.executeUpdate();
                    System.out.println("withdraw done");

                }else if(n==4){
                    float b1=0;
                    System.out.println("Enter ur Account No");
                    int a1 = sc.nextInt();
                    PreparedStatement stmt = con.prepareStatement("select Balance from BankingApplication where AccountNo=?");
                    stmt.setInt(1,a1);
                    ResultSet rs = stmt.executeQuery();
                    while(rs.next()){
                        b1 = rs.getFloat(1);
                    }
                    System.out.println("Enter  Amount");
                    float am = sc.nextFloat();
                    float ans1 = b1+am;
                    PreparedStatement stmt3 =  con.prepareStatement("update BankingApplication set balance = ? where accountNo = ?");
                    stmt3.setFloat(1,ans1);
                    stmt3.setInt(2,a1);
                    long nor = stmt3.executeUpdate();
                    System.out.println("deposit done");
                }else if(n==5){
                    break;
                }
            }

            con.close();
            System.out.println("disconnected");
        }
        catch(SQLException se){
            System.out.println("issue"+se);
        }

    }
}