/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Acer
 */
import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import javax.swing.*;
import net.proteanit.sql.DbUtils;
import java.util.Calendar;
import java.util.GregorianCalendar;

public class SearchJframe extends javax.swing.JFrame {
    Connection conn = null;
    ResultSet rs = null;
    PreparedStatement ppst = null;
    /**
     * Creates new form SearchJframe
     */
    public SearchJframe() {
        initComponents();
        conn = connector.ConnectDB();
    }
    
    private void cleartext(){
        id_txt_field.setText(null);
        author_txt_field.setText(null);
        title_txt_field.setText(null);
    }
    
    private void updateRest(){
        try{
            // search id
            String sql1 = "SELECT b.Book_id, b.Title, o.Author_name, c.Branch_id, c.No_of_copies, l.Available,o.Type"
                    + " FROM BOOK AS b, OVERALL_AUTHORS AS o, BOOK_COPIES AS c, BOOK_AVAILABLE AS l"
                    +" WHERE b.Book_id = c.Book_id AND o.Book_id = c.Book_id AND l.Book_id = c.Book_id AND c.Book_id = ? AND l.Branch_id = c.Branch_id";
            // search author_name
            String sql2 = "SELECT b.Book_id, b.Title, o.Author_name, c.Branch_id, c.No_of_copies, l.Available,o.Type"
                    + " FROM BOOK AS b, OVERALL_AUTHORS AS o, BOOK_COPIES AS c, BOOK_AUTHORS as a, BOOK_AVAILABLE AS l"
                    +" WHERE a.Author_name LIKE ? AND o.Book_id = b.Book_id AND o.Book_id = c.Book_id AND a.Book_id = o.Book_id AND l.Book_id = o.Book_id AND l.Branch_id = c.Branch_id";
            // search title
            String sql3 = "SELECT b.Book_id, b.Title, o.Author_name, c.Branch_id, c.No_of_copies, l.Available,o.Type"
                    + " FROM BOOK AS b, OVERALL_AUTHORS AS o, BOOK_COPIES AS c, BOOK_AVAILABLE AS l"
                    + " WHERE b.Title = ? AND b.Book_id = c.Book_id AND b.Book_id = o.Book_id AND b.Book_id = l.Book_id AND c.Branch_id = l.Branch_id";
            // search id & name
            String sql4 = "SELECT b.Book_id, b.Title, o.Author_name, c.Branch_id, c.No_of_copies, l.Available,o.Type"
                    + " FROM BOOK AS b, OVERALL_AUTHORS AS o, BOOK_COPIES AS c, BOOK_AUTHORS as a, BOOK_AVAILABLE AS l"
                    + " WHERE b.Book_id = c.Book_id AND o.Book_id = c.Book_id AND a.Book_id = c.Book_id AND c.Book_id = l.Book_id AND c.Book_id = ? AND a.Author_name LIKE ? AND l.Branch_id = c.Branch_id";
            // search id & title
            String sql5 = "SELECT b.Book_id, b.Title, o.Author_name, c.Branch_id, c.No_of_copies, l.Available,o.Type"
                    + " FROM BOOK AS b, OVERALL_AUTHORS AS o, BOOK_COPIES AS c, BOOK_AVAILABLE AS l"
                    + " WHERE b.Book_id = c.Book_id AND o.Book_id = c.Book_id AND l.Book_id = c.Book_id AND c.Book_id = ? AND b.title = ? AND l.Branch_id = c.Branch_id";
            // search name & title
            String sql6 = "SELECT b.Book_id, b.Title, o.Author_name, c.Branch_id, c.No_of_copies, l.Available,o.Type"
                    + " FROM BOOK AS b, OVERALL_AUTHORS AS o, BOOK_COPIES AS c, BOOK_AUTHORS as a, BOOK_AVAILABLE AS l"
                    + " WHERE b.Book_id = c.Book_id AND o.Book_id = c.Book_id AND a.Book_id = b.Book_id AND c.Book_id = l.Book_id AND a.author_name LIKE ? AND b.title = ? AND c.Branch_id = l.Branch_id";
            // search id & name & title
            String sql7 = "SELECT b.Book_id, b.Title, o.Author_name, c.Branch_id, c.No_of_copies, l.Available,o.Type"
                    + " FROM BOOK AS b, OVERALL_AUTHORS AS o, BOOK_COPIES AS c, BOOK_AUTHORS as a, BOOK_AVAILABLE AS l"
                    + " WHERE b.Book_id = c.Book_id AND o.Book_id = c.Book_id AND a.Book_id = c.Book_id AND c.Book_id = l.Book_id AND c.Book_id = ? AND a.author_name LIKE ? AND b.title = ? AND c.Branch_id = l.Branch_id";
            if((id_txt_field.getText().length()!=0)&&(author_txt_field.getText().length()==0)&&(title_txt_field.getText().length()==0)){
                ppst = conn.prepareStatement(sql1);
                ppst.setString(1, id_txt_field.getText());
            }else if((author_txt_field.getText().length()!=0)&&(id_txt_field.getText().length()==0)&&(title_txt_field.getText().length()==0)){
                ppst = conn.prepareStatement(sql2);
                ppst.setString(1, "%"+author_txt_field.getText()+"%");
            }else if((author_txt_field.getText().length()==0)&&(id_txt_field.getText().length()==0)&&(title_txt_field.getText().length()!=0)){
                ppst = conn.prepareStatement(sql3);
                ppst.setString(1, title_txt_field.getText());
            }else if((author_txt_field.getText().length()!=0)&&(id_txt_field.getText().length()!=0)&&(title_txt_field.getText().length()==0)){
                ppst = conn.prepareStatement(sql4);
                ppst.setString(1, id_txt_field.getText());
                ppst.setString(2, "%"+author_txt_field.getText()+"%");
            }else if((author_txt_field.getText().length()==0)&&(id_txt_field.getText().length()!=0)&&(title_txt_field.getText().length()!=0)){
                ppst = conn.prepareStatement(sql5);
                ppst.setString(1, id_txt_field.getText());
                ppst.setString(2, title_txt_field.getText());
            }else if((author_txt_field.getText().length()!=0)&&(id_txt_field.getText().length()==0)&&(title_txt_field.getText().length()!=0)){
                ppst = conn.prepareStatement(sql6);
                ppst.setString(1, "%"+author_txt_field.getText()+"%");
                ppst.setString(2, title_txt_field.getText());
            }else if((author_txt_field.getText().length()!=0)&&(id_txt_field.getText().length()!=0)&&(title_txt_field.getText().length()!=0)){
                ppst = conn.prepareStatement(sql7);
                ppst.setString(1, id_txt_field.getText());
                ppst.setString(2, "%"+author_txt_field.getText()+"%");
                ppst.setString(3, title_txt_field.getText());
            }
            rs = ppst.executeQuery();
            ResultTable.setModel(DbUtils.resultSetToTableModel(rs));  
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
        id_txt_field = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        author_txt_field = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        title_txt_field = new javax.swing.JTextField();
        SearchButton = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        ResultTable = new javax.swing.JTable();

        jLabel1.setText("Book_id");

        id_txt_field.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                id_txt_fieldActionPerformed(evt);
            }
        });

        jLabel2.setText("Author(s)");

        author_txt_field.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                author_txt_fieldActionPerformed(evt);
            }
        });

        jLabel3.setText("Title");

        SearchButton.setText("Search");
        SearchButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                SearchButtonActionPerformed(evt);
            }
        });

        ResultTable.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane1.setViewportView(ResultTable);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(SearchButton))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addComponent(jLabel1)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(id_txt_field, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel2)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(author_txt_field, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel3)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(title_txt_field))
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 578, Short.MAX_VALUE))))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(id_txt_field, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2)
                    .addComponent(author_txt_field, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3)
                    .addComponent(title_txt_field, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(SearchButton)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 216, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void id_txt_fieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_id_txt_fieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_id_txt_fieldActionPerformed

    private void author_txt_fieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_author_txt_fieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_author_txt_fieldActionPerformed

    private void SearchButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SearchButtonActionPerformed
        // TODO add your handling code here:
        updateRest();
    }//GEN-LAST:event_SearchButtonActionPerformed

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
            java.util.logging.Logger.getLogger(SearchJframe.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(SearchJframe.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(SearchJframe.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(SearchJframe.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new SearchJframe().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTable ResultTable;
    private javax.swing.JButton SearchButton;
    private javax.swing.JTextField author_txt_field;
    private javax.swing.JTextField id_txt_field;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextField title_txt_field;
    // End of variables declaration//GEN-END:variables
}
