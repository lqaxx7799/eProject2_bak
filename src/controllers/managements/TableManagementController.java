/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers.managements;

import controllers.BaseController;
import java.util.ArrayList;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import models.Table;
import services.TableService;
import views.managements.TableManagementView;

/**
 *
 * @author Admin
 */
public class TableManagementController implements BaseController {

    private TableManagementView tableManagementView;
    private TableService tableService;
    private static String actionType = "";

    public TableManagementController() {
        tableManagementView = new TableManagementView();
        tableService = new TableService();

        tableManagementView.getAddTableButton().addActionListener(al -> addTableHandler());
        tableManagementView.getEditTableButton().addActionListener(al -> editTableHandler());
        tableManagementView.getRemoveTableButton().addActionListener(al -> removeTableHandler());
        tableManagementView.getCancelButton().addActionListener(al -> cancelHandler());
        tableManagementView.getDoneButton().addActionListener(al -> doneHandler());
    }

    private void addTableHandler() {
        setButtonState(false);
        tableManagementView.getTableNameTextField().setEnabled(true);
        tableManagementView.getIsOccupiedRadioButton().setEnabled(false);
        tableManagementView.getIsAvailableRadioButton().setEnabled(false);

        actionType = "add";
    }

    private void editTableHandler() {
        DefaultTableModel listModel = (DefaultTableModel) tableManagementView.getTableManagementTable().getModel();
        int rowSelected = tableManagementView.getTableManagementTable().getSelectedRow();
        if (rowSelected == -1) {
            JOptionPane.showMessageDialog(null, "Select a row first");
            return;
        }
        setButtonState(false);
        setFormState(true);
        int id = (int) tableManagementView.getTableManagementTable().getValueAt(rowSelected, 0);
        tableManagementView.getTableNameTextField().setText(tableService.getById(id).getTableName());
        tableManagementView.getIsOccupiedRadioButton().setSelected(tableService.getById(id).isOccupied());
        tableManagementView.getIsAvailableRadioButton().setSelected(tableService.getById(id).isAvailable());
        actionType = "edit";
    }

    private void removeTableHandler() {
        DefaultTableModel listModel = (DefaultTableModel) tableManagementView.getTableManagementTable().getModel();
        int rowSelected = tableManagementView.getTableManagementTable().getSelectedRow();
        if (rowSelected == -1) {
            JOptionPane.showMessageDialog(null, "Select a row first");
            return;
        }
        setButtonState(false);
        int dialogButton = JOptionPane.YES_NO_OPTION;
        int dialogResult = JOptionPane.showConfirmDialog(null, "Do you really want to delete this table?", "Confirmation", dialogButton);
        if (dialogResult == JOptionPane.YES_OPTION) {
            int id = (int) tableManagementView.getTableManagementTable().getValueAt(rowSelected, 0);
            Table table = tableService.getById(id);
            table.setAvailable(false);
            tableService.update(table);
            JOptionPane.showMessageDialog(null, "Success!");
        }
        loadData();
        return;
    }

    private void cancelHandler() {
        setButtonState(true);
        setFormState(false);
        resetForm();
        loadData();
    }

    private void doneHandler() {
        ArrayList<Table> tableList = tableService.getAll();
        if (actionType.equals("add")) {
            Table newTable = new Table();
            String tabelName = tableManagementView.getTableNameTextField().getText();
            for (Table table : tableList) {
                if (tabelName.equalsIgnoreCase(table.getTableName())) {
                    JOptionPane.showMessageDialog(null, "Table name is already existed");
                    return;
                }
            }
            newTable.setTableName(tabelName);
            boolean isOccupied = tableManagementView.getIsOccupiedRadioButton().isSelected();
            newTable.setOccupied(isOccupied);
            newTable.setAvailable(true);
            if (tabelName.equals("")) {
                JOptionPane.showMessageDialog(null, "Table name is required");
                return;
            }
            tableService.insert(newTable);
            JOptionPane.showMessageDialog(null, "Success");
            resetForm();
            setButtonState(true);
            loadData();
            return;
        } else if (actionType.equals("edit")) {
            DefaultTableModel listModel = (DefaultTableModel) tableManagementView.getTableManagementTable().getModel();
            int rowSelected = tableManagementView.getTableManagementTable().getSelectedRow();
            int id = (int) tableManagementView.getTableManagementTable().getValueAt(rowSelected, 0);
            String tableNameTxt = tableManagementView.getTableNameTextField().getText();
            if (tableNameTxt.equals("")) {
                JOptionPane.showMessageDialog(null, "Table name is required");
                return;
            }
//            for (Table table : tableList) {
//                if (tableNameTxt.equals(table.getTableName())) {
//                    JOptionPane.showMessageDialog(null, "Tên bàn đã tồn tại!");
//                    return;
//                }
//            }
            Table tbl = tableService.getById(id);
            tbl.setTableName(tableNameTxt);
            tbl.setOccupied(tableManagementView.getIsOccupiedRadioButton().isSelected());
            tbl.setAvailable(tableManagementView.getIsAvailableRadioButton().isSelected());
            tableService.update(tbl);
            JOptionPane.showMessageDialog(null, "Success");
            resetForm();
            setButtonState(true);
            setFormState(false);
            loadData();
            return;
        }

    }

    @Override
    public JPanel getPanel() {
        return tableManagementView;
    }

    @Override
    public void loadData() {
        DefaultTableModel listModel = (DefaultTableModel) tableManagementView.getTableManagementTable().getModel();
        for (int i = listModel.getRowCount() - 1; i >= 0; i--) {
            listModel.removeRow(i);
        }
        ArrayList<Table> tableList = tableService.getAll();
        for (Table item : tableList) {
            //    if(item.isAvailable()){
            Object[] rowData = new Object[]{
                item.getId(),
                item.getTableName(),
                item.isOccupied(),
                item.isAvailable(),};
            listModel.addRow(rowData);
            //    }   
        }
        decorateTable();
        setButtonState(true);
        setFormState(false);
    }

    private void setButtonState(boolean state) {
        tableManagementView.getAddTableButton().setEnabled(state);
        tableManagementView.getEditTableButton().setEnabled(state);
        tableManagementView.getRemoveTableButton().setEnabled(state);
        tableManagementView.getCancelButton().setEnabled(!state);
        tableManagementView.getDoneButton().setEnabled(!state);
    }

    private void setFormState(boolean state) {
        tableManagementView.getTableNameTextField().setEnabled(state);
        tableManagementView.getIsOccupiedRadioButton().setEnabled(state);
        tableManagementView.getIsAvailableRadioButton().setEnabled(state);
    }

    private void resetForm() {
        tableManagementView.getTableNameTextField().setText("");
        tableManagementView.getIsOccupiedRadioButton().setSelected(false);
        tableManagementView.getIsAvailableRadioButton().setSelected(false);
    }

    public void decorateTable() {
        ((DefaultTableCellRenderer) tableManagementView.getTableManagementTable().getTableHeader().getDefaultRenderer())
                .setHorizontalAlignment((int) JLabel.CENTER_ALIGNMENT);

        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(JLabel.CENTER);
        tableManagementView.getTableManagementTable().getColumnModel().getColumn(0).setCellRenderer(centerRenderer);
        for (int x = 0; x < tableManagementView.getTableManagementTable().getColumnCount() - 1; x++) {
            tableManagementView.getTableManagementTable().getColumnModel().getColumn(x).setCellRenderer(centerRenderer);
        }
    }
}
