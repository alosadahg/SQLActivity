/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package alosadaapplication;

import java.sql.*;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author L14Y09W07
 */
public class Connect {
    Connection conn=null;
    public Connect(){
        try {
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/bankalosada","root","");
//            JOptionPane.showMessageDialog(null, "Connected");
        } catch (SQLException ex) {
            Logger.getLogger(Connect.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public boolean registerUser(User user){
        Statement stmt;
        String sql=null;
        ResultSet rs=null;
        try {
            stmt = conn.createStatement();
            sql="select * from user where username='"+user.getUsername()+"'";
            System.out.println(sql);
            rs =stmt.executeQuery(sql);
            if(rs.next()==false){
                sql="insert into user values('"+user.getUsername()+"','"+user.getPassword()+"','"+user.getFirstname()+"','"+user.getLastname()+"',0)";
                stmt.executeUpdate(sql);
                JOptionPane.showMessageDialog(null, "Account created successfully");
                return true;
            } else {
                JOptionPane.showMessageDialog(null, "Username already exists.\nPlease log in.", "Create User failed.", JOptionPane.ERROR_MESSAGE);
                return false;
            }
        } catch (SQLException ex) {
            Logger.getLogger(Connect.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            try {
                conn.close();
            } catch (SQLException ex) {
                Logger.getLogger(Connect.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return false;
    }
    
    public int login(String username, String password){
        Statement stmt;
        String sql;
        ResultSet rs;
        try {
            stmt=conn.createStatement();
            sql ="select * from user where username='"+username+"' and password='"+password+"'";
            rs = stmt.executeQuery(sql);
            if (rs.next()==true) {
                sql ="select usertype from user where username='"+username+"'";
                int accType = rs.getInt("usertype");
                if(accType == 0) {
                    return 0;
                } else {
                    return 1;
                } 
            } else {
                return 2;
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(Connect.class.getName()).log(Level.SEVERE, null, ex);
        }
        return -1;
    }
    
    public void addAccount(Account acc) {
        Statement stmt;
        String sql = "insert into account values('" + acc.getAccountNumber() + "',"+ acc.getBalance() + ",'" + acc.getUsername() + "')";
        try {
            stmt = conn.createStatement();
            stmt.executeUpdate(sql);
            JOptionPane.showMessageDialog(null, "Account successfully added.");
        } catch (SQLException ex) {
            Logger.getLogger(Connect.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public ArrayList<Account> displayAccount(String username){
        ArrayList<Account> acc = new ArrayList<Account>();
        String sql ="select * from account where username='"+username+"'";
        Statement stmt;
        ResultSet rs;
        
        try {
            stmt = conn.createStatement();
            rs = stmt.executeQuery(sql);
            while(rs.next()){
              Account a = new Account(rs.getString(1),rs.getDouble(2)) ;
              acc.add(a);
            }
        } catch (SQLException ex) {
            Logger.getLogger(Connect.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return acc;
    }
    
    public boolean updateBalance(Account account){
        String accNum = account.getAccountNumber();
        double newBal = account.getBalance();
        String sql = "insert into verification values('"+account.getUsername()+"','"+accNum+"',"+newBal+",'update',0)";
        Statement stmt;
        try {
            stmt = conn.createStatement();
            stmt.executeUpdate(sql);
            JOptionPane.showMessageDialog(null, "Transaction update is pending for approval");
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(Connect.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }
    
    public void deleteAccount(Account account){
        String accNum = account.getAccountNumber();
        double newBal = account.getBalance();
        String sql = "insert into verification values('"+account.getUsername()+"','"+accNum+"',"+newBal+",'delete',0)";
        Statement stmt;
        try {
            stmt = conn.createStatement();
            stmt.executeUpdate(sql);
            JOptionPane.showMessageDialog(null, "Transaction update is pending for approval");
        } catch (SQLException ex) {
            Logger.getLogger(Connect.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public boolean balanceTransfer(String acctNumFrom, String acctNumTo, double amnt, String username) {
        String sql = "select * from account where accountnumber='"+acctNumTo+"' and username='"+username+"'";
        Statement stmt;
        ResultSet rs;
        try {
            stmt = conn.createStatement();
            rs = stmt.executeQuery(sql);
            if(rs.next()==false) {
                return false;
            }
            double toBal = rs.getDouble(2) + amnt;
            sql = "update account set balance="+ toBal + " where accountnumber='"+acctNumTo+"' and username='"+username+"'";
            stmt.execute(sql);
            sql = "select * from account where accountNumber='"+acctNumFrom+"' and username='"+username+"'";
            rs = stmt.executeQuery(sql);
            if(rs.next()==false) {
                return false;
            }
            double fromBal = rs.getDouble(2) - amnt;
            sql = "update account set balance="+ fromBal + " where accountNumber='"+acctNumFrom+"' and username='"+username+"'";
            stmt.execute(sql);
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(Connect.class.getName()).log(Level.SEVERE, null, ex);
        } 
        return false;
    }
    
    public ResultSet displayVerification() {
        Statement stmt;
        ResultSet rs;
        String sql = "select * from verification where status=0";
        try {
            stmt = conn.createStatement();
            rs = stmt.executeQuery(sql);
            return rs;
        } catch (SQLException ex) {
            Logger.getLogger(Connect.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
    
    public int approveTransaction(Verification ver) {
        Statement stmt;
        ResultSet rs;
        String sql = null;
        int transaction = 0;
        if(ver.getTypeoftransaction().equalsIgnoreCase("update")) {
            transaction = 1;
            sql = "update account set balance=" + ver.getAmount() + " where accountnumber='" + ver.getAccountNumber() + "'";
        } else if(ver.getTypeoftransaction().equalsIgnoreCase("delete")) {
            transaction = 2;
            sql = "delete from account where accountnumber='" + ver.getAccountNumber() + "'";
        }
        try {
            stmt = conn.createStatement();
            stmt.executeUpdate(sql);
            sql = "update verification set status=1 where customerusername='" + ver.getCustomerUsername() +"' and accountnumber='" + ver.getAccountNumber() + "' and amount=" + ver.getAmount() + " and typeoftransaction='" + ver.getTypeoftransaction() + "'";
            stmt.executeUpdate(sql);
        } catch (SQLException ex) {
            Logger.getLogger(Connect.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return transaction;
    }
}