/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package alosadaapplication;

import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author L14Y09W07
 */
public class Connect {
    Connection conn = null;

    public Connect() {
        try {
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/BankAlosada","root","");
        } catch (SQLException ex) {
            Logger.getLogger(Connect.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void registerUser(User user) {
        Statement stmt;
        String sql = null;
        ResultSet rs = null;
        try {
            stmt = conn.createStatement();
            sql = "select * from user where username='"+user.getUsername()+"'";
            rs = stmt.executeQuery(sql);
            if(rs.next()==false) {
                sql = "insert into user values('"+user.getUsername()+"','"+user.getPassword()+"','"+user.getFirstname()+"','"+user.getLastname()+"',0)";
                stmt.executeUpdate(sql);
                JOptionPane.showMessageDialog(null, "New record saved.", "Registration Successful",1);
            } else {
                JOptionPane.showMessageDialog(null,"Username already existing.","Registration failed",0);
            }
        } catch (SQLException ex) {
            Logger.getLogger(Connect.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
