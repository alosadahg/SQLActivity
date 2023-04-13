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
            JOptionPane.showMessageDialog(null,"Connected");
        } catch (SQLException ex) {
            Logger.getLogger(Connect.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void registerUser(User user) {
        Statement stmt;
        String sql = null;
        try {
            stmt = conn.createStatement();
            sql = "insert into user values('"+user.getUsername()+"','"+user.getPassword()+"','"+user.getFirstname()+"','"+user.getLastname()+"',0)";
            stmt.executeUpdate(sql);
        } catch (SQLException ex) {
            Logger.getLogger(Connect.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
