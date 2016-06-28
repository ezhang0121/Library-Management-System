/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Acer
 */
import java.awt.Toolkit;
import java.awt.event.WindowEvent;
import java.sql.*;
import javax.swing.JOptionPane;
import net.proteanit.sql.DbUtils;

public class NewJframe extends javax.swing.JFrame {
    Connection conn = null;
    ResultSet rs = null;
    PreparedStatement ppst = null;
    /**
     * Creates new form NewJframe
     */
    public NewJframe() {
        initComponents();
        conn = connector.ConnectDB();
    }
    private void cleartext(){
        fname_field.setText(null);
        lname_field.setText(null);
        phone_field.setText(null);
        addr_field.setText(null);
    }
//    public void closeFrame(){
//        WindowEvent closingEvent = new WindowEvent(this, WindowEvent.WINDOW_CLOSED);
//        Toolkit.getDefaultToolkit().getSystemEventQueue().postEvent(closingEvent);
//    }
    
    private boolean checkNexist(){
        try{
            String check = "SELECT * FROM BORROWERS"
                    + " WHERE Fname = ? AND Lname = ? AND Address = ? AND Phone = ?";
            ppst = conn.prepareStatement(check);
            ppst.setString(1, fname_field.getText());
            ppst.setString(2, lname_field.getText());
            ppst.setString(3, addr_field.getText());
            ppst.setString(4, phone_field.getText());
            
            rs = ppst.executeQuery();
            if(rs.next()){
                return false; // borrower exist
            }else{
                return true; // not exist
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        return true;
    }
    
    private String cardnoGen(){
        String card_no = null;
        try{
            String gen = "SELECT COUNT(*)+9001 "
                    + "FROM BORROWERS";
            ppst = conn.prepareStatement(gen);
            rs = ppst.executeQuery();
            if(rs.next()){
                card_no = rs.getString(1);
                //System.out.println(card_no);
                return card_no;
                
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        return card_no;
    }
    
    private void updateBorrower(){
        try{
            String card_no = cardnoGen();
            if(checkNexist()){
                String add = "INSERT INTO BORROWERS(Card_no, Fname, Lname, Address, Phone) VALUES(?,?,?,?,?)";
                ppst = conn.prepareStatement(add);
                
                ppst.setString(1, card_no);
                ppst.setString(2, fname_field.getText());
                ppst.setString(3, lname_field.getText());
                ppst.setString(4, addr_field.getText());
                ppst.setString(5, phone_field.getText());
                
                ppst.executeUpdate();
                JOptionPane.showMessageDialog(null, "New Borrower Added!");
               
                //closeFrame();
                
            }else{
                JOptionPane.showMessageDialog(null, "User Already Exist!");
                //closeFrame();
            }
             
        }catch(Exception e){
            e.printStackTrace();
        }finally{ 
            try{
                rs.close();
                conn.close();
            }catch(Exception e){
                JOptionPane.showMessageDialog(null, e);
            }
        }
        cleartext();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        fname_field = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        lname_field = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        phone_field = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        addr_field = new javax.swing.JTextField();
        addButton = new javax.swing.JButton();

        jLabel1.setText("Fname");

        jLabel2.setText("Lname");

        jLabel3.setText("Phone");

        jLabel4.setText("Address");

        addButton.setText("Add");
        addButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(addButton, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1)
                            .addComponent(jLabel4))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(fname_field, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel2)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(lname_field, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel3)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(phone_field, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addComponent(addr_field))))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(fname_field, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2)
                    .addComponent(lname_field, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3)
                    .addComponent(phone_field, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(addr_field, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 8, Short.MAX_VALUE)
                .addComponent(addButton)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void addButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addButtonActionPerformed
        // TODO add your handling code here:
        updateBorrower();
        
    }//GEN-LAST:event_addButtonActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(NewJframe.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(NewJframe.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(NewJframe.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(NewJframe.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new NewJframe().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton addButton;
    private javax.swing.JTextField addr_field;
    private javax.swing.JTextField fname_field;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JTextField lname_field;
    private javax.swing.JTextField phone_field;
    // End of variables declaration//GEN-END:variables
}