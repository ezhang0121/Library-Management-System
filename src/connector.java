/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Acer
 */
import java.sql.*;
import javax.swing.*;

public class connector {
    Connection conn = null;
    String user;
    String passWord;
    
    public static Connection ConnectDB(){
        try{
            Class.forName("com.mysql.jdbc.Driver");
            String user = "root";
            String passWord = "ze338655238";
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/library",user, passWord);
            JOptionPane.showConfirmDialog(null, "Connection Established");
            return conn;
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, e);
            return null;
        }
    }
}
