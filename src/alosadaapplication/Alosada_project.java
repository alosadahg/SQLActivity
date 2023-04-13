/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package alosadaapplication;

/**
 *
 * @author L14Y09W07
 */
public class Alosada_project {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        LogIn login = new LogIn();
        Connect conn = new Connect();
        User u = new User("admin", "qwerty", "john", "doe");
        conn.registerUser(u);
    }
    
}
