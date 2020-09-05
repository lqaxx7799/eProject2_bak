/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package views.managements;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;

/**
 *
 * @author Admin
 */
public class IngredientManagementView extends javax.swing.JPanel {

    /**
     * Creates new form IngredientManagementView
     */
    public IngredientManagementView() {
        initComponents();
    }

    public JTable getIngredientManagementTable() {
        return IngredientManagementTable;
    }

    public void setIngredientManagementTable(JTable IngredientManagementTable) {
        this.IngredientManagementTable = IngredientManagementTable;
    }

    public JButton getAddIngredientButton() {
        return addIngredientButton;
    }

    public void setAddIngredientButton(JButton addIngredientButton) {
        this.addIngredientButton = addIngredientButton;
    }

    public JScrollPane getjScrollPane1() {
        return jScrollPane1;
    }

    public void setjScrollPane1(JScrollPane jScrollPane1) {
        this.jScrollPane1 = jScrollPane1;
    }

    public JButton getRemoveIngredientButton() {
        return removeIngredientButton;
    }

    public void setRemoveIngredientButton(JButton removeIngredientButton) {
        this.removeIngredientButton = removeIngredientButton;
    }

    public JButton getUpdateIngredientButton() {
        return updateIngredientButton;
    }

    public void setUpdateIngredientButton(JButton updateIngredientButton) {
        this.updateIngredientButton = updateIngredientButton;
    }

    public JLabel getMessageLable() {
        return messageLable;
    }

    public void setMessageLable(JLabel messageLable) {
        this.messageLable = messageLable;
    }

    public JTextField getIngredientNameTextField() {
        return ingredientNameTextField;
    }

    public void setIngredientNameTextField(JTextField ingredientNameTextField) {
        this.ingredientNameTextField = ingredientNameTextField;
    }

    public JLabel getjLabel1() {
        return jLabel1;
    }

    public void setjLabel1(JLabel jLabel1) {
        this.jLabel1 = jLabel1;
    }

    public JLabel getjLabel2() {
        return jLabel2;
    }

    public void setjLabel2(JLabel jLabel2) {
        this.jLabel2 = jLabel2;
    }

    public JTextField getUnitTextField() {
        return unitTextField;
    }

    public void setUnitTextField(JTextField unitTextField) {
        this.unitTextField = unitTextField;
    }

    public JButton getCancelButton() {
        return cancelButton;
    }

    public void setCancelButton(JButton cancelButton) {
        this.cancelButton = cancelButton;
    }

    public JButton getDoneButton() {
        return doneButton;
    }

    public void setDoneButton(JButton doneButton) {
        this.doneButton = doneButton;
    }

    public JRadioButton getIsAvailbleRadioButton() {
        return isAvailbleRadioButton;
    }

    public void setIsAvailbleRadioButton(JRadioButton isAvailbleRadioButton) {
        this.isAvailbleRadioButton = isAvailbleRadioButton;
    }

    public JLabel getjLabel3() {
        return jLabel3;
    }

    public void setjLabel3(JLabel jLabel3) {
        this.jLabel3 = jLabel3;
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
        IngredientManagementTable = new javax.swing.JTable();
        addIngredientButton = new javax.swing.JButton();
        updateIngredientButton = new javax.swing.JButton();
        removeIngredientButton = new javax.swing.JButton();
        messageLable = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        ingredientNameTextField = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        unitTextField = new javax.swing.JTextField();
        cancelButton = new javax.swing.JButton();
        doneButton = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        isAvailbleRadioButton = new javax.swing.JRadioButton();

        setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        setEnabled(false);

        IngredientManagementTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "ID ", "Ingredient Name", "Unit", "Is Available"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.String.class, java.lang.String.class, java.lang.Boolean.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(IngredientManagementTable);

        addIngredientButton.setText("Add New");
        addIngredientButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addIngredientButtonActionPerformed(evt);
            }
        });

        updateIngredientButton.setText("Edit");

        removeIngredientButton.setText("Delete");

        messageLable.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        jLabel1.setText("Ingredient Name");

        ingredientNameTextField.setCursor(new java.awt.Cursor(java.awt.Cursor.TEXT_CURSOR));

        jLabel2.setText("Unit");

        cancelButton.setText("Cancel");

        doneButton.setText("Submit");

        jLabel3.setText("Is Available");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabel2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.LEADING))
                                .addGap(18, 138, Short.MAX_VALUE)
                                .addComponent(messageLable, javax.swing.GroupLayout.PREFERRED_SIZE, 403, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addGroup(layout.createSequentialGroup()
                                    .addComponent(addIngredientButton)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                    .addComponent(updateIngredientButton, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                    .addComponent(removeIngredientButton, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                    .addComponent(jLabel3)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(isAvailbleRadioButton)
                                        .addComponent(unitTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 136, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(ingredientNameTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 136, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(cancelButton, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(doneButton, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(37, 37, 37)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(306, 306, 306)
                        .addComponent(messageLable, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(addIngredientButton)
                            .addComponent(updateIngredientButton)
                            .addComponent(removeIngredientButton))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 264, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel1)
                            .addComponent(ingredientNameTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(cancelButton))
                        .addGap(11, 11, 11)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(doneButton)
                            .addComponent(jLabel2)
                            .addComponent(unitTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(16, 16, 16)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(isAvailbleRadioButton)
                            .addComponent(jLabel3))))
                .addContainerGap(81, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void addIngredientButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addIngredientButtonActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_addIngredientButtonActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTable IngredientManagementTable;
    private javax.swing.JButton addIngredientButton;
    private javax.swing.JButton cancelButton;
    private javax.swing.JButton doneButton;
    private javax.swing.JTextField ingredientNameTextField;
    private javax.swing.JRadioButton isAvailbleRadioButton;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel messageLable;
    private javax.swing.JButton removeIngredientButton;
    private javax.swing.JTextField unitTextField;
    private javax.swing.JButton updateIngredientButton;
    // End of variables declaration//GEN-END:variables
}
