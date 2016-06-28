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
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import javax.swing.JOptionPane;
import net.proteanit.sql.DbUtils;

public class CheckJframe extends javax.swing.JFrame {
    Connection conn = null;
    ResultSet rs = null;
    PreparedStatement ppst = null;
    //PreparedStatement ppst2 = null;
    Statement stm = null;
    /**
     * Creates new form CheckJframe
     */
    public CheckJframe() {
        initComponents();
        conn = connector.ConnectDB();
        UpdateTable();
        
    }
    private void cleartext(){
        txt_id_field.setText(null);
        txt_bran_field.setText(null);
        txt_card_field.setText(null);
    }
    /*
    ** insert loan_id after check-out
    */
    private void insertLoanid(){
        try{
            String insert = "insert into fines(loan_id, card_no) "
                    + "select book_loans.loan_id, book_loans.card_no "
                    + "from book_loans "
                    + "where book_loans.book_id = ? and book_loans.branch_id = ? and book_loans.card_no = ? "
                    + "on duplicate key update loan_id = book_loans.loan_id";
            ppst = conn.prepareStatement(insert);
            ppst.setString(1, txt_id_field.getText());
            ppst.setString(2, txt_bran_field.getText());
            ppst.setString(3, txt_card_field.getText());
            
            ppst.executeUpdate();
            
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    
    //?
    private void UpdateDb(){
        try{
            String update = "UPDATE book_available"
                    + " SET book_available.Available = "
                    + "((SELECT No_of_copies FROM book_copies AS c WHERE c.Book_id = ? and c.Branch_id = ?) - "
                    + "(SELECT COUNT(*) FROM book_loans AS l WHERE l.Book_id = ? and l.Branch_id = ?))"
                    + " WHERE book_available.Book_id = ? AND book_available.Branch_id = ?";
            ppst = conn.prepareStatement(update);
            ppst.setString(1, txt_id_field.getText());
            ppst.setString(2, txt_bran_field.getText());
            ppst.setString(3, txt_id_field.getText());
            ppst.setString(4, txt_bran_field.getText());
            ppst.setString(5, txt_id_field.getText());
            ppst.setString(6, txt_bran_field.getText());
            
            ppst.executeUpdate();
        }catch(Exception e){
            e.printStackTrace();
        }finally{
            try{
                rs.close();
                //conn.close();
            }catch(Exception e){
                JOptionPane.showMessageDialog(null, e);
            }
        }
    }

    private void UpdateTable(){
        try{
            String sql = "SELECT * from BOOK_LOANS";
            
            ppst = conn.prepareStatement(sql);
            rs = ppst.executeQuery();
            check_table.setModel(DbUtils.resultSetToTableModel(rs));
            
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, e);
        }
    }
    private boolean checkNum(){
        try{
            String check = "SELECT COUNT(*)+1 FROM BOOK_LOANS WHERE Card_no = ? and date_in is null";
            ppst = conn.prepareStatement(check);
            ppst.setString(1, txt_card_field.getText());
            rs = ppst.executeQuery();   
            if(rs.next()){
                if(Integer.parseInt(rs.getString(1))<=3){
                    System.out.println(Integer.parseInt(rs.getString(1)));
                    return true;
                }else{
                    return false;
                }
            }     
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, e);
        }
        return true;
    }
    private boolean checkAvailable(){
        try{
            String check = "SELECT Available FROM book_available WHERE Book_id = ? AND Branch_id = ?";
            ppst = conn.prepareStatement(check);
            ppst.setString(1, txt_id_field.getText());
            ppst.setString(2, txt_bran_field.getText());
            
            rs = ppst.executeQuery();
            if(rs.next()){
                if(Integer.parseInt(rs.getString(1))>=1){
                    return true;
                }else{
                    return false;
                }
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        return true;
    }
    /**
     * check-out update table
     */
    private void checkoutUpdate(){
        try{
                // insert
                String sql1 = "INSERT INTO BOOK_LOANS(Book_id, Branch_id, Card_no, Date_out)"
                    //+ " VALUES(?,?,?,?,?)"
                    + " SELECT c.Book_id, c.Branch_id, b.Card_no, CURRENT_DATE"
                    + " FROM BOOK_COPIES AS c, BORROWERS AS b"
                    + " WHERE c.Book_id = ? AND c.Branch_id = ? AND b.Card_no = ? AND (SELECT COUNT(*) FROM BOOK_LOANS AS l WHERE l.Card_no = b.Card_no AND length(l.date_in)=0)<=3";
                if(checkNum()){
                    if(checkAvailable()){
                        ppst = conn.prepareStatement(sql1);
            
                        ppst.setString(1, txt_id_field.getText());
                        ppst.setString(2, txt_bran_field.getText());
                        ppst.setString(3, txt_card_field.getText());
                        //ppst.setString(4, CurrentDate());
                        //ppst.setString(5, FutureDate());
                        ppst.executeUpdate();
                    }else{
                        JOptionPane.showMessageDialog(null, "The Book Is Now Unavailable In This Branch");
                    }
                    
                }else{
                    JOptionPane.showMessageDialog(null, "You have already borrowed three books!");
                }
        }catch(Exception e){
            e.printStackTrace();
        }
        UpdateTable();
        insertLoanid();
        UpdateDb();
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
        txt_id_field = new javax.swing.JTextField();
        txt_bran_field = new javax.swing.JTextField();
        txt_card_field = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        outButton = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        check_table = new javax.swing.JTable();
        jButton1 = new javax.swing.JButton();

        jLabel1.setText("Book_id");

        jLabel2.setText("Branch_id");

        jLabel3.setText("Card_no");

        outButton.setText("Check-out");
        outButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                outButtonActionPerformed(evt);
            }
        });

        check_table.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane1.setViewportView(check_table);

        jButton1.setText("Refresh");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txt_card_field, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txt_id_field, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txt_bran_field, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 18, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jButton1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(outButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(txt_card_field, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(outButton)
                    .addComponent(jLabel1)
                    .addComponent(txt_id_field, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2)
                    .addComponent(txt_bran_field, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 205, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void outButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_outButtonActionPerformed
        // TODO add your handling code here:
        checkoutUpdate();
    }//GEN-LAST:event_outButtonActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        UpdateTable();
    }//GEN-LAST:event_jButton1ActionPerformed

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
            java.util.logging.Logger.getLogger(CheckJframe.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(CheckJframe.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(CheckJframe.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(CheckJframe.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new CheckJframe().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTable check_table;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JButton outButton;
    private javax.swing.JTextField txt_bran_field;
    private javax.swing.JTextField txt_card_field;
    private javax.swing.JTextField txt_id_field;
    // End of variables declaration//GEN-END:variables
}
