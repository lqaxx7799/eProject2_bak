/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package views;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTextField;

/**
 *
 * @author Admin
 */
public class ChangePasswordView extends javax.swing.JPanel {

    /**
     * Creates new form ChangePasswordView
     */
    public ChangePasswordView() {
        initComponents();
    }

    public JButton getBtnSubmit() {
        return btnSubmit;
    }

    public void setBtnSubmit(JButton btnSubmit) {
        this.btnSubmit = btnSubmit;
    }

    public JLabel getLblErrNewPassword() {
        return lblErrNewPassword;
    }

    public void setLblErrNewPassword(JLabel lblErrNewPassword) {
        this.lblErrNewPassword = lblErrNewPassword;
    }

    public JLabel getLblErrOldPassword() {
        return lblErrOldPassword;
    }

    public void setLblErrOldPassword(JLabel lblErrOldPassword) {
        this.lblErrOldPassword = lblErrOldPassword;
    }

    public JLabel getLblErrReenterNewPassword() {
        return lblErrReenterNewPassword;
    }

    public void setLblErrReenterNewPassword(JLabel lblErrReenterNewPassword) {
        this.lblErrReenterNewPassword = lblErrReenterNewPassword;
    }

    public JLabel getLblNewPassword() {
        return lblNewPassword;
    }

    public void setLblNewPassword(JLabel lblNewPassword) {
        this.lblNewPassword = lblNewPassword;
    }

    public JLabel getLblOldPassword() {
        return lblOldPassword;
    }

    public void setLblOldPassword(JLabel lblOldPassword) {
        this.lblOldPassword = lblOldPassword;
    }

    public JLabel getLblReenterNewPassword() {
        return lblReenterNewPassword;
    }

    public void setLblReenterNewPassword(JLabel lblReenterNewPassword) {
        this.lblReenterNewPassword = lblReenterNewPassword;
    }

    public JTextField getTxtNewPassword() {
        return txtNewPassword;
    }

    public void setTxtNewPassword(JTextField txtNewPassword) {
        this.txtNewPassword = txtNewPassword;
    }

    public JTextField getTxtOldPassword() {
        return txtOldPassword;
    }

    public void setTxtOldPassword(JTextField txtOldPassword) {
        this.txtOldPassword = txtOldPassword;
    }

    public JTextField getTxtReenterNewPassword() {
        return txtReenterNewPassword;
    }

    public void setTxtReenterNewPassword(JTextField txtReenterNewPassword) {
        this.txtReenterNewPassword = txtReenterNewPassword;
    }

    public JLabel getLblSuccess() {
        return lblSuccess;
    }

    public void setLblSuccess(JLabel lblSuccess) {
        this.lblSuccess = lblSuccess;
    }

    
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        txtOldPassword = new javax.swing.JTextField();
        txtNewPassword = new javax.swing.JTextField();
        txtReenterNewPassword = new javax.swing.JTextField();
        lblOldPassword = new javax.swing.JLabel();
        lblNewPassword = new javax.swing.JLabel();
        lblReenterNewPassword = new javax.swing.JLabel();
        lblErrOldPassword = new javax.swing.JLabel();
        lblErrNewPassword = new javax.swing.JLabel();
        lblErrReenterNewPassword = new javax.swing.JLabel();
        btnSubmit = new javax.swing.JButton();
        lblSuccess = new javax.swing.JLabel();

        lblOldPassword.setText("Mật khẩu cũ:");

        lblNewPassword.setText("Mật khẩu mới:");

        lblReenterNewPassword.setText("Nhập lại mật khẩu mới: ");

        lblErrOldPassword.setForeground(new java.awt.Color(255, 0, 0));

        lblErrNewPassword.setForeground(new java.awt.Color(255, 0, 0));

        lblErrReenterNewPassword.setForeground(new java.awt.Color(255, 0, 0));

        btnSubmit.setText("Xác nhận");

        lblSuccess.setForeground(new java.awt.Color(0, 153, 0));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(41, 41, 41)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblOldPassword)
                    .addComponent(lblNewPassword)
                    .addComponent(lblReenterNewPassword))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(11, 11, 11)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txtOldPassword, javax.swing.GroupLayout.DEFAULT_SIZE, 127, Short.MAX_VALUE)
                            .addComponent(txtNewPassword)))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btnSubmit)
                            .addComponent(txtReenterNewPassword, javax.swing.GroupLayout.PREFERRED_SIZE, 128, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblSuccess))))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblErrOldPassword, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblErrNewPassword, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblErrReenterNewPassword, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(181, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(63, 63, 63)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtOldPassword, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblOldPassword)
                    .addComponent(lblErrOldPassword))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtNewPassword, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblNewPassword)
                    .addComponent(lblErrNewPassword))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtReenterNewPassword, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblReenterNewPassword)
                    .addComponent(lblErrReenterNewPassword))
                .addGap(32, 32, 32)
                .addComponent(btnSubmit)
                .addGap(18, 18, 18)
                .addComponent(lblSuccess)
                .addContainerGap(97, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnSubmit;
    private javax.swing.JLabel lblErrNewPassword;
    private javax.swing.JLabel lblErrOldPassword;
    private javax.swing.JLabel lblErrReenterNewPassword;
    private javax.swing.JLabel lblNewPassword;
    private javax.swing.JLabel lblOldPassword;
    private javax.swing.JLabel lblReenterNewPassword;
    private javax.swing.JLabel lblSuccess;
    private javax.swing.JTextField txtNewPassword;
    private javax.swing.JTextField txtOldPassword;
    private javax.swing.JTextField txtReenterNewPassword;
    // End of variables declaration//GEN-END:variables
}