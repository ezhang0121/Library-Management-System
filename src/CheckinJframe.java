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

public class CheckinJframe extends javax.swing.JFrame {
    Connection conn = null;
    ResultSet rs = null;
    PreparedStatement ppst = null;
    /**
     * Creates new form CheckinJframe
     */
    public CheckinJframe() {
        initComponents();
        conn = connector.ConnectDB();
        //checkinUpdate();
        //calculateFine();
    }
    private void cleartext(){
        txt_card_field.setText(null);
        txt_id_field.setText(null);
        txt_bran_field.setText(null);
        txt_fname_field.setText(null);
        txt_lname_field.setText(null);
    }
    /*
    * check-in update table
    */
    private void checkinUpdate(){
        try{
            String search1="SELECT l.Loan_id, l.Book_id, l.Branch_id, l.Card_no, l.Date_out, l.Date_in"
                    + " FROM Book_loans AS l, borrowers AS bo"
                    + " WHERE (l.book_id=? AND l.card_no = bo.card_no)";
            String search2="SELECT l.Loan_id, l.Book_id, l.Branch_id, l.Card_no, l.Date_out, l.Date_in"
                    + " FROM Book_loans AS l, borrowers AS bo"
                    + " WHERE (l.card_no=? AND l.card_no=bo.card_no)";
            String search3="SELECT l.Loan_id, l.Book_id, l.Branch_id, l.Card_no, l.Date_out, l.Date_in"
                    + " FROM Book_loans AS l, borrowers AS bo"
                    + " WHERE (l.book_id=? AND l.card_no=? AND l.card_no = bo.card_no)";
            String search4="SELECT l.Loan_id, l.Book_id, l.Branch_id, l.Card_no, l.Date_out, l.Date_in"
                    + " FROM Book_loans AS l, borrowers AS bo"
                    + " WHERE (bo.Fname=? AND bo.card_no=l.card_no)";
            String search5="SELECT l.Loan_id, l.Book_id, l.Branch_id, l.Card_no, l.Date_out, l.Date_in"
                    + " FROM Book_loans AS l, borrowers AS bo"
                    + " WHERE (bo.Lname=? AND bo.card_no=l.card_no)";
            String search6="SELECT l.Loan_id, l.Book_id, l.Branch_id, l.Card_no, l.Date_out, l.Date_in"
                    + " FROM Book_loans AS l, borrowers AS bo"
                    + " WHERE (l.book_id=? AND bo.Fname=? AND bo.card_no=l.card_no)";
            String search7="SELECT l.Loan_id, l.Book_id, l.Branch_id, l.Card_no, l.Date_out, l.Date_in"
                    + " FROM Book_loans AS l, borrowers AS bo"
                    + " WHERE (l.card_no=? AND bo.Fname=? AND bo.card_no=l.card_no)";
            String search8="SELECT l.Loan_id, l.Book_id, l.Branch_id, l.Card_no, l.Date_out, l.Date_in"
                    + " FROM Book_loans AS l, borrowers AS bo"
                    + " WHERE (l.book_id=? AND bo.Lname=? AND bo.card_no=l.card_no)";
            String search9="SELECT l.Loan_id, l.Book_id, l.Branch_id, l.Card_no, l.Date_out, l.Date_in"
                    + " FROM Book_loans AS l, borrowers AS bo"
                    + " WHERE (l.card_no=? AND bo.Lname=? AND bo.card_no=l.card_no)";
            String search10="SELECT l.Loan_id, l.Book_id, l.Branch_id, l.Card_no, l.Date_out, l.Date_in"
                    + " FROM Book_loans AS l, borrowers AS bo"
                    + " WHERE (l.card_no=? AND bo.Fname=? AND bo.Lname=? AND bo.card_no=l.card_no)";
            String search11="SELECT l.Loan_id, l.Book_id, l.Branch_id, l.Card_no, l.Date_out, l.Date_in"
                    + " FROM Book_loans AS l, borrowers AS bo"
                    + " WHERE (l.book_id=? AND bo.Fname=? AND bo.Lname=? AND bo.card_no=l.card_no)";
            String search12="SELECT l.Loan_id, l.Book_id, l.Branch_id, l.Card_no, l.Date_out, l.Date_in"
                    + " FROM Book_loans AS l, borrowers AS bo"
                    + " WHERE (l.book_id=? AND l.card_no=? AND bo.Fname=? AND l.card_no = bo.card_no)";
            String search13="SELECT l.Loan_id, l.Book_id, l.Branch_id, l.Card_no, l.Date_out, l.Date_in"
                    + " FROM Book_loans AS l, borrowers AS bo"
                    + " WHERE (l.book_id=? AND l.card_no=? AND bo.Lname=? AND l.card_no = bo.card_no)";
            String search14="SELECT l.Loan_id, l.Book_id, l.Branch_id, l.Card_no, l.Date_out, l.Date_in"
                    + " FROM Book_loans AS l, borrowers AS bo"
                    + " WHERE (l.book_id=? AND l.card_no=? AND bo.Fname=? AND bo.Lname=? AND l.card_no = bo.card_no)";
            
            if((txt_id_field.getText().length()!=0)&&(txt_card_field.getText().length()==0)&&(txt_fname_field.getText().length()==0)&&(txt_lname_field.getText().length()==0)){
                ppst = conn.prepareStatement(search1);
                ppst.setString(1, txt_id_field.getText());
            }else if((txt_id_field.getText().length()==0)&&(txt_card_field.getText().length()!=0)&&(txt_fname_field.getText().length()==0)&&(txt_lname_field.getText().length()==0)){
                ppst = conn.prepareStatement(search2);
                ppst.setString(1, txt_card_field.getText());
            }else if((txt_id_field.getText().length()!=0)&&(txt_card_field.getText().length()!=0)&&(txt_fname_field.getText().length()==0)&&(txt_lname_field.getText().length()==0)){
                ppst = conn.prepareStatement(search3);
                ppst.setString(1, txt_id_field.getText());
                ppst.setString(2, txt_card_field.getText());
            }else if((txt_id_field.getText().length()==0)&&(txt_card_field.getText().length()==0)&&(txt_fname_field.getText().length()!=0)&&(txt_lname_field.getText().length()==0)){
                ppst = conn.prepareStatement(search4);
                ppst.setString(1, txt_fname_field.getText());
            }else if((txt_id_field.getText().length()==0)&&(txt_card_field.getText().length()==0)&&(txt_fname_field.getText().length()==0)&&(txt_lname_field.getText().length()!=0)){
                ppst = conn.prepareStatement(search5);
                ppst.setString(1, txt_lname_field.getText());
            }else if((txt_id_field.getText().length()!=0)&&(txt_card_field.getText().length()==0)&&(txt_fname_field.getText().length()!=0)&&(txt_lname_field.getText().length()==0)){
                ppst = conn.prepareStatement(search6);
                ppst.setString(1, txt_card_field.getText());
                ppst.setString(2, txt_fname_field.getText());
            }else if((txt_id_field.getText().length()==0)&&(txt_card_field.getText().length()!=0)&&(txt_fname_field.getText().length()!=0)&&(txt_lname_field.getText().length()==0)){
                ppst = conn.prepareStatement(search7);
                ppst.setString(1, txt_card_field.getText());
                ppst.setString(2, txt_fname_field.getText());
            }else if((txt_id_field.getText().length()!=0)&&(txt_card_field.getText().length()==0)&&(txt_fname_field.getText().length()==0)&&(txt_lname_field.getText().length()!=0)){
                ppst = conn.prepareStatement(search8);
                ppst.setString(1, txt_id_field.getText());
                ppst.setString(2, txt_lname_field.getText());
            }else if((txt_id_field.getText().length()==0)&&(txt_card_field.getText().length()!=0)&&(txt_fname_field.getText().length()==0)&&(txt_lname_field.getText().length()!=0)){
                ppst = conn.prepareStatement(search9);
                ppst.setString(1, txt_card_field.getText());
                ppst.setString(2, txt_lname_field.getText());
            }else if((txt_id_field.getText().length()==0)&&(txt_card_field.getText().length()!=0)&&(txt_fname_field.getText().length()!=0)&&(txt_lname_field.getText().length()!=0)){
                ppst = conn.prepareStatement(search10);
                ppst.setString(1, txt_card_field.getText());
                ppst.setString(2, txt_fname_field.getText());
                ppst.setString(3, txt_lname_field.getText());
            }else if((txt_id_field.getText().length()!=0)&&(txt_card_field.getText().length()==0)&&(txt_fname_field.getText().length()!=0)&&(txt_lname_field.getText().length()!=0)){
                ppst = conn.prepareStatement(search11);
                ppst.setString(1, txt_id_field.getText());
                ppst.setString(2, txt_fname_field.getText());
                ppst.setString(3, txt_lname_field.getText());
            }else if((txt_id_field.getText().length()!=0)&&(txt_card_field.getText().length()!=0)&&(txt_fname_field.getText().length()!=0)&&(txt_lname_field.getText().length()==0)){
                ppst = conn.prepareStatement(search12);
                ppst.setString(1, txt_id_field.getText());
                ppst.setString(2, txt_card_field.getText());
                ppst.setString(3, txt_fname_field.getText());
            }else if((txt_id_field.getText().length()!=0)&&(txt_card_field.getText().length()!=0)&&(txt_fname_field.getText().length()==0)&&(txt_lname_field.getText().length()!=0)){
                ppst = conn.prepareStatement(search13);
                ppst.setString(1, txt_id_field.getText());
                ppst.setString(2, txt_card_field.getText());
                ppst.setString(3, txt_lname_field.getText());
            }else if((txt_id_field.getText().length()!=0)&&(txt_card_field.getText().length()!=0)&&(txt_fname_field.getText().length()!=0)&&(txt_lname_field.getText().length()!=0)){
                ppst = conn.prepareStatement(search14);
                ppst.setString(1, txt_id_field.getText());
                ppst.setString(2, txt_card_field.getText());
                ppst.setString(3, txt_fname_field.getText());
                ppst.setString(4, txt_lname_field.getText());
            }
            rs=ppst.executeQuery();
          
            check_table.setModel(DbUtils.resultSetToTableModel(rs));
            
            
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    private void updateAvailable(){
        try{
            String ava = "UPDATE book_available"
                    + " SET book_available.Available = "
                    + "(SELECT (Available+1) FROM "
                    + "(SELECT * FROM book_available) AS x WHERE Book_id = ? AND Branch_id = ?)"
                    + " WHERE book_available.Book_id = ? AND book_available.Branch_id = ?";
            ppst = conn.prepareStatement(ava);
            
            ppst.setString(1, txt_id_field.getText());
            ppst.setString(2, txt_bran_field.getText());
            ppst.setString(3, txt_id_field.getText());
            ppst.setString(4, txt_bran_field.getText());
            
            ppst.executeUpdate();
        }catch(Exception e){
            e.printStackTrace();
        }
        
    }
    
    private void updateDatein(){
        try{
            String date = "UPDATE BOOK_LOANS"
                    + " SET BOOK_LOANS.Date_in = CURRENT_DATE"
                    + " WHERE BOOK_LOANS.Card_no = ? AND BOOK_LOANS.Book_id = ?";
            ppst = conn.prepareStatement(date);
            
            ppst.setString(1, txt_card_field.getText());
            ppst.setString(2, txt_id_field.getText());
            
            ppst.executeUpdate();
            
        }catch(Exception e){
            e.printStackTrace();
        }
        JOptionPane.showMessageDialog(null, "Check-in Success!");
        checkReturn();
        updateTable();
    }
    /*
    * check in or not update if checked in 
    */
    private void checkReturn(){
        try{
            String check = "update fines, book_loans "
                    + "set fines.paid = 'true' "
                    + "where book_loans.loan_id = fines.loan_id and length(book_loans.date_in)>0";
            
            ppst = conn.prepareStatement(check);
            ppst.executeUpdate();
            
        }catch(Exception e){
            e.printStackTrace();
        }
    }

//    private boolean late(){
//        try{
//            String late = "select datediff(l.date_in, date_add(l.date_out, interval 14 day)) "
//                    + "from book_loans as l, fines "
//                    + "where fines.paid = 'false' and fines.loan_id = l.loan_id";
//            ppst = conn.prepareStatement(late);
//            rs = ppst.executeQuery();
//            if(rs.next()){
//                if(rs.getInt(1)>0){
//                    System.out.println("late");
//                    return true;
//                }else{
//                    System.out.println("not late");
//                    return false;
//                }
//            }
//        }catch(Exception e){
//            e.printStackTrace();
//        }
//        return true;
//    }
    /*
    ** calculate fine amount after check-in
    */
//     private void calafter(){
//        if(late()){
//            System.out.println("It's late");
//            try{
//            String after = "update fines "
//                    + "set fines.fine_amt = "
//                    + "(select datediff(l.date_in, DATE_ADD(l.date_out, INTERVAL 14 DAY))*0.25 "
//                    + "from book_loans as l "
//                    + "where l.loan_id = fines.loan_id and fines.paid = 'true') "
//                    + "where paid = 'true'";
//            
//            ppst = conn.prepareStatement(after);
//            ppst.executeUpdate();
//            }catch(Exception e){
//                e.printStackTrace();
//            }
//        }else{
//            System.out.println("No fines");
//        }
//    }   
//modified
    private void updateTable(){
        try{
            String sql = "SELECT l.Loan_id, l.Book_id, l.Branch_id, l.Card_no, l.Date_out, l.Date_in"
                    + " FROM book_loans AS l"
                    + " WHERE Card_no = ?";
            ppst = conn.prepareStatement(sql);
            //ppst.setString(1, txt_id_field.getText());
            ppst.setString(1, txt_card_field.getText());
            
            rs = ppst.executeQuery();
            check_table.setModel(DbUtils.resultSetToTableModel(rs));
            
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
        cleartext();
    }
    /*
    ** check in action update DB
    */
    private void checkIn(){
        updateAvailable();
        updateDatein();
        //calafter();
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
        txt_card_field = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        txt_id_field = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        txt_bran_field = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
        txt_fname_field = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        txt_lname_field = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        check_table = new javax.swing.JTable();

        jLabel1.setText("Card_no");

        txt_card_field.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txt_card_fieldKeyReleased(evt);
            }
        });

        jLabel2.setText("Book_id");

        txt_id_field.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txt_id_fieldKeyReleased(evt);
            }
        });

        jLabel3.setText("Fname");

        jButton1.setText("Check-in");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jLabel4.setText("Lname");

        txt_fname_field.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txt_fname_fieldKeyReleased(evt);
            }
        });

        jLabel5.setText("Branch_id");

        txt_lname_field.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_lname_fieldActionPerformed(evt);
            }
        });
        txt_lname_field.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txt_lname_fieldKeyReleased(evt);
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
        check_table.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                check_tableMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(check_table);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1)
                            .addComponent(jLabel3))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txt_card_field)
                            .addComponent(txt_fname_field, javax.swing.GroupLayout.DEFAULT_SIZE, 100, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel2)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txt_id_field, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel4)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(txt_lname_field, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel5)
                        .addGap(9, 9, 9)
                        .addComponent(txt_bran_field, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jButton1)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(txt_card_field, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2)
                    .addComponent(txt_id_field, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txt_bran_field, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton1)
                    .addComponent(jLabel5))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txt_fname_field, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel3)
                            .addComponent(jLabel4)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(7, 7, 7)
                        .addComponent(txt_lname_field, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 275, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txt_lname_fieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_lname_fieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_lname_fieldActionPerformed

    private void txt_card_fieldKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_card_fieldKeyReleased
        // TODO add your handling code here:
        checkinUpdate();
    }//GEN-LAST:event_txt_card_fieldKeyReleased

    private void txt_id_fieldKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_id_fieldKeyReleased
        // TODO add your handling code here:
        checkinUpdate();
    }//GEN-LAST:event_txt_id_fieldKeyReleased

    private void txt_fname_fieldKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_fname_fieldKeyReleased
        // TODO add your handling code here:
        checkinUpdate();
    }//GEN-LAST:event_txt_fname_fieldKeyReleased

    private void txt_lname_fieldKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_lname_fieldKeyReleased
        // TODO add your handling code here:
        checkinUpdate();
    }//GEN-LAST:event_txt_lname_fieldKeyReleased

    private void check_tableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_check_tableMouseClicked
        // TODO add your handling code here:
        try{
            int row = check_table.getSelectedRow();
            String str = check_table.getModel().getValueAt(row, 0).toString();
            //System.out.println(str);
            String query = "SELECT l.Book_id, l.Branch_id, l.Card_no, bo.Fname, bo.Lname FROM BOOK_LOANS AS l, BORROWERS AS bo"
                    + " WHERE l.Loan_id = ? AND l.card_no = bo.Card_no";
            ppst = conn.prepareStatement(query);
            ppst.setString(1, str);
            rs = ppst.executeQuery();
            if(rs.next()){
                String add1 = rs.getString("Book_id");
                txt_id_field.setText(add1);
//                String add2 = rs.getString("Branch_id");
//                txt_bran_field.setText(add2);
                String add3 = rs.getString("Card_no");
                txt_card_field.setText(add3);
                String add4 = rs.getString("Fname");
                txt_fname_field.setText(add4);
                String add5 = rs.getString("Lname");
                txt_lname_field.setText(add5);
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        
    }//GEN-LAST:event_check_tableMouseClicked

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        checkIn();
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
            java.util.logging.Logger.getLogger(CheckinJframe.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(CheckinJframe.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(CheckinJframe.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(CheckinJframe.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new CheckinJframe().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTable check_table;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextField txt_bran_field;
    private javax.swing.JTextField txt_card_field;
    private javax.swing.JTextField txt_fname_field;
    private javax.swing.JTextField txt_id_field;
    private javax.swing.JTextField txt_lname_field;
    // End of variables declaration//GEN-END:variables
}
