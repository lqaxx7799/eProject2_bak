/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package views.managements;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Admin
 */
public class AccountManagementView extends javax.swing.JPanel {

    /**
     * Creates new form AccountManagementView
     */
    private DefaultTableModel tblAccountModel;

    public AccountManagementView() {
        initComponents();
    }

    public DefaultTableModel getTblAccountModel() {
        return tblAccountModel;
    }

    public void setTblAccountModel(DefaultTableModel tblAccountModel) {
        this.tblAccountModel = tblAccountModel;
    }

    public JButton getBtnAddNew() {
        return btnAddNew;
    }

    public void setBtnAddNew(JButton btnAddNew) {
        this.btnAddNew = btnAddNew;
    }

    public JButton getBtnEdit() {
        return btnEdit;
    }

    public void setBtnEdit(JButton btnEdit) {
        this.btnEdit = btnEdit;
    }

    public JButton getBtnCancel() {
        return btnCancel;
    }

    public void setBtnCancel(JButton btnCancel) {
        this.btnCancel = btnCancel;
    }

    public JButton getBtnSubmit() {
        return btnSubmit;
    }

    public void setBtnSubmit(JButton btnSubmit) {
        this.btnSubmit = btnSubmit;
    }

    public JComboBox<String> getCbxGender() {
        return cbxGender;
    }

    public void setCbxGender(JComboBox<String> cbxGender) {
        this.cbxGender = cbxGender;
    }

    public JComboBox<String> getCbxRole() {
        return cbxRole;
    }

    public void setCbxRole(JComboBox<String> cbxRole) {
        this.cbxRole = cbxRole;
    }

    public JCheckBox getChbIsWorking() {
        return chbIsWorking;
    }

    public void setChbIsWorking(JCheckBox chbIsWorking) {
        this.chbIsWorking = chbIsWorking;
    }

    public JScrollPane getjScrollPane1() {
        return jScrollPane1;
    }

    public void setjScrollPane1(JScrollPane jScrollPane1) {
        this.jScrollPane1 = jScrollPane1;
    }

    public JLabel getLblAddress() {
        return lblAddress;
    }

    public void setLblAddress(JLabel lblAddress) {
        this.lblAddress = lblAddress;
    }

    public JLabel getLblDateOfBirth() {
        return lblDateOfBirth;
    }

    public void setLblDateOfBirth(JLabel lblDateOfBirth) {
        this.lblDateOfBirth = lblDateOfBirth;
    }

    public JLabel getLblEmail() {
        return lblEmail;
    }

    public void setLblEmail(JLabel lblEmail) {
        this.lblEmail = lblEmail;
    }

    public JLabel getLblErrAddress() {
        return lblErrAddress;
    }

    public void setLblErrAddress(JLabel lblErrAddress) {
        this.lblErrAddress = lblErrAddress;
    }

    public JLabel getLblErrDateOfBirth() {
        return lblErrDateOfBirth;
    }

    public void setLblErrDateOfBirth(JLabel lblErrDateOfBirth) {
        this.lblErrDateOfBirth = lblErrDateOfBirth;
    }

    public JLabel getLblErrEmail() {
        return lblErrEmail;
    }

    public void setLblErrEmail(JLabel lblErrEmail) {
        this.lblErrEmail = lblErrEmail;
    }

    public JLabel getLblErrGender() {
        return lblErrGender;
    }

    public void setLblErrGender(JLabel lblErrGender) {
        this.lblErrGender = lblErrGender;
    }

    public JLabel getLblErrIsWorking() {
        return lblErrIsWorking;
    }

    public void setLblErrIsWorking(JLabel lblErrIsWorking) {
        this.lblErrIsWorking = lblErrIsWorking;
    }

    public JLabel getLblErrRole() {
        return lblErrRole;
    }

    public void setLblErrRole(JLabel lblErrRole) {
        this.lblErrRole = lblErrRole;
    }

    public JLabel getLblErrUserName() {
        return lblErrUserName;
    }

    public void setLblErrUserName(JLabel lblErrUserName) {
        this.lblErrUserName = lblErrUserName;
    }

    public JLabel getLblGender() {
        return lblGender;
    }

    public void setLblGender(JLabel lblGender) {
        this.lblGender = lblGender;
    }

    public JLabel getLblIsWorking() {
        return lblIsWorking;
    }

    public void setLblIsWorking(JLabel lblIsWorking) {
        this.lblIsWorking = lblIsWorking;
    }

    public JLabel getLblRole() {
        return lblRole;
    }

    public void setLblRole(JLabel lblRole) {
        this.lblRole = lblRole;
    }

    public JLabel getLblUserName() {
        return lblUserName;
    }

    public void setLblUserName(JLabel lblUserName) {
        this.lblUserName = lblUserName;
    }

    public JTable getTblAccount() {
        return tblAccount;
    }

    public void setTblAccount(JTable tblAccount) {
        this.tblAccount = tblAccount;
    }

    public JTextField getTxtAddress() {
        return txtAddress;
    }

    public void setTxtAddress(JTextField txtAddress) {
        this.txtAddress = txtAddress;
    }

    public JTextField getTxtDateOfBirth() {
        return txtDateOfBirth;
    }

    public void setTxtDateOfBirth(JTextField txtDateOfBirth) {
        this.txtDateOfBirth = txtDateOfBirth;
    }

    public JTextField getTxtEmail() {
        return txtEmail;
    }

    public void setTxtEmail(JTextField txtEmail) {
        this.txtEmail = txtEmail;
    }

    public JTextField getTxtUserName() {
        return txtUserName;
    }

    public void setTxtUserName(JTextField txtUserName) {
        this.txtUserName = txtUserName;
    }

    public JButton getBtnViewSalary() {
        return btnViewSalary;
    }

    public void setBtnViewSalary(JButton btnViewSalary) {
        this.btnViewSalary = btnViewSalary;
    }
    
    

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        tblAccount = new javax.swing.JTable();
        btnAddNew = new javax.swing.JButton();
        btnEdit = new javax.swing.JButton();
        btnCancel = new javax.swing.JButton();
        btnSubmit = new javax.swing.JButton();
        btnViewSalary = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        txtUserName = new javax.swing.JTextField();
        lblUserName = new javax.swing.JLabel();
        lblAddress = new javax.swing.JLabel();
        txtAddress = new javax.swing.JTextField();
        lblGender = new javax.swing.JLabel();
        lblDateOfBirth = new javax.swing.JLabel();
        txtDateOfBirth = new javax.swing.JTextField();
        lblRole = new javax.swing.JLabel();
        cbxRole = new javax.swing.JComboBox<>();
        cbxGender = new javax.swing.JComboBox<>();
        lblErrEmail = new javax.swing.JLabel();
        lblErrUserName = new javax.swing.JLabel();
        lblErrAddress = new javax.swing.JLabel();
        lblErrGender = new javax.swing.JLabel();
        lblErrDateOfBirth = new javax.swing.JLabel();
        txtEmail = new javax.swing.JTextField();
        lblErrRole = new javax.swing.JLabel();
        lblEmail = new javax.swing.JLabel();
        chbIsWorking = new javax.swing.JCheckBox();
        lblIsWorking = new javax.swing.JLabel();
        lblErrIsWorking = new javax.swing.JLabel();

        tblAccount.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Id", "Email", "User Name", "Address", "Gender", "Date Of Birth", "Role", "Start Work Date", "Is Wokring"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.Boolean.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(tblAccount);

        btnAddNew.setText("Add New");

        btnEdit.setText("Edit");

        btnCancel.setText("Cancel");

        btnSubmit.setText("Submit");

        btnViewSalary.setText("View Salary");

        lblUserName.setText("User Name");

        lblAddress.setText("Address");

        lblGender.setText("Gender");

        lblDateOfBirth.setText("Date Of Birth");

        lblRole.setText("Role");

        cbxGender.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        lblErrEmail.setForeground(new java.awt.Color(255, 0, 0));
        lblErrEmail.setText("jLabel7");

        lblErrUserName.setForeground(new java.awt.Color(255, 0, 0));
        lblErrUserName.setText("jLabel7");

        lblErrAddress.setForeground(new java.awt.Color(255, 0, 0));
        lblErrAddress.setText("jLabel7");

        lblErrGender.setForeground(new java.awt.Color(255, 0, 0));
        lblErrGender.setText("jLabel7");

        lblErrDateOfBirth.setForeground(new java.awt.Color(255, 0, 0));
        lblErrDateOfBirth.setText("jLabel7");

        lblErrRole.setForeground(new java.awt.Color(255, 0, 0));
        lblErrRole.setText("jLabel7");

        lblEmail.setText("Email");

        lblIsWorking.setText("Is Working");

        lblErrIsWorking.setForeground(new java.awt.Color(255, 0, 0));
        lblErrIsWorking.setText("jLabel7");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblEmail, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblUserName)
                            .addComponent(lblAddress))
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(44, 44, 44)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(txtEmail, javax.swing.GroupLayout.PREFERRED_SIZE, 148, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtUserName, javax.swing.GroupLayout.PREFERRED_SIZE, 148, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(lblErrEmail)
                                    .addComponent(lblErrUserName)))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(43, 43, 43)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(chbIsWorking)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                            .addComponent(txtAddress, javax.swing.GroupLayout.PREFERRED_SIZE, 148, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(txtDateOfBirth, javax.swing.GroupLayout.PREFERRED_SIZE, 148, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(cbxGender, javax.swing.GroupLayout.PREFERRED_SIZE, 148, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(cbxRole, javax.swing.GroupLayout.PREFERRED_SIZE, 148, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGap(18, 18, 18)
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(lblErrAddress)
                                            .addComponent(lblErrGender)
                                            .addComponent(lblErrDateOfBirth)
                                            .addComponent(lblErrRole))))))
                        .addGap(0, 29, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblIsWorking)
                            .addComponent(lblRole)
                            .addComponent(lblDateOfBirth)
                            .addComponent(lblGender))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(lblErrIsWorking)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(19, 19, 19)
                        .addComponent(lblEmail))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtEmail, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblErrEmail, javax.swing.GroupLayout.Alignment.TRAILING))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(lblUserName)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(txtUserName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(lblErrUserName)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(lblAddress)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(txtAddress, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(lblErrAddress)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(lblGender)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(cbxGender, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(lblErrGender)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtDateOfBirth, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblDateOfBirth)
                    .addComponent(lblErrDateOfBirth))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cbxRole, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblRole)
                    .addComponent(lblErrRole))
                .addGap(10, 10, 10)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblIsWorking)
                    .addComponent(chbIsWorking)
                    .addComponent(lblErrIsWorking))
                .addGap(16, 16, 16))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 309, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnCancel, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnSubmit, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(56, 56, 56))
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(btnAddNew, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnEdit, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnViewSalary)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnAddNew)
                    .addComponent(btnEdit)
                    .addComponent(btnViewSalary))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 195, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addComponent(btnCancel)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnSubmit)
                        .addContainerGap())))
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAddNew;
    private javax.swing.JButton btnCancel;
    private javax.swing.JButton btnEdit;
    private javax.swing.JButton btnSubmit;
    private javax.swing.JButton btnViewSalary;
    private javax.swing.JComboBox<String> cbxGender;
    private javax.swing.JComboBox<String> cbxRole;
    private javax.swing.JCheckBox chbIsWorking;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblAddress;
    private javax.swing.JLabel lblDateOfBirth;
    private javax.swing.JLabel lblEmail;
    private javax.swing.JLabel lblErrAddress;
    private javax.swing.JLabel lblErrDateOfBirth;
    private javax.swing.JLabel lblErrEmail;
    private javax.swing.JLabel lblErrGender;
    private javax.swing.JLabel lblErrIsWorking;
    private javax.swing.JLabel lblErrRole;
    private javax.swing.JLabel lblErrUserName;
    private javax.swing.JLabel lblGender;
    private javax.swing.JLabel lblIsWorking;
    private javax.swing.JLabel lblRole;
    private javax.swing.JLabel lblUserName;
    private javax.swing.JTable tblAccount;
    private javax.swing.JTextField txtAddress;
    private javax.swing.JTextField txtDateOfBirth;
    private javax.swing.JTextField txtEmail;
    private javax.swing.JTextField txtUserName;
    // End of variables declaration//GEN-END:variables
}
