/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers.managements;

import com.mysql.jdbc.exceptions.MySQLIntegrityConstraintViolationException;
import controllers.BaseController;
import java.util.ArrayList;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import models.Ingredient;
import services.IngredientService;
import views.managements.IngredientManagementView;

/**
 *
 * @author Admin
 */
public class IngredientManagementController implements BaseController {

    private IngredientManagementView ingredientManagementView;
    private IngredientService ingredientService;
    private static String actionType = "";

    public IngredientManagementController() {
        ingredientManagementView = new IngredientManagementView();
        ingredientService = new IngredientService();

        ingredientManagementView.getAddIngredientButton().addActionListener(al -> addIngredientHandler());
        ingredientManagementView.getUpdateIngredientButton().addActionListener(al -> updateIngredientHandler());
        ingredientManagementView.getRemoveIngredientButton().addActionListener(al -> removeIngredientHandler());
        ingredientManagementView.getDoneButton().addActionListener(al -> doneHandler());
        ingredientManagementView.getCancelButton().addActionListener(al -> cancelHandler());
    }

    private void addIngredientHandler() {
        ingredientManagementView.getIngredientNameTextField().setEnabled(true);
        ingredientManagementView.getUnitTextField().setEnabled(true);
        ingredientManagementView.getIsAvailbleRadioButton().setEnabled(false);
        setButtonState(false);
        ingredientManagementView.getIsAvailbleRadioButton().setEnabled(false);
        actionType = "add";
    }

    private void updateIngredientHandler() {
        setButtonState(true);
        setFormState(true);
        DefaultTableModel listModel = (DefaultTableModel) ingredientManagementView.getIngredientManagementTable().getModel();
        // Lấy hàng được chọn
        int rowSelected = ingredientManagementView.getIngredientManagementTable().getSelectedRow();
        if (rowSelected == -1) {
            JOptionPane.showMessageDialog(null, "Chọn một hàng trước");
            setFormState(false);
            return;
        }
        setButtonState(false);
        // Lấy ID
        int id = (int) ingredientManagementView.getIngredientManagementTable().getValueAt(rowSelected, 0);
        // Truy xuất CSDL Ingredient theo ID
        ArrayList<Ingredient> ingredientList = ingredientService.getAll();
        for (Ingredient item : ingredientList) {
            if (item.getId() == id) {
                ingredientManagementView.getIngredientNameTextField().setText(item.getIngredientName());
                ingredientManagementView.getUnitTextField().setText(item.getUnit());
                ingredientManagementView.getIsAvailbleRadioButton().setSelected(item.isAvailable());
            }
        }
        actionType = "edit";
    }

    private void removeIngredientHandler() {
        DefaultTableModel listModel = (DefaultTableModel) ingredientManagementView.getIngredientManagementTable().getModel();
        int rowSelected = ingredientManagementView.getIngredientManagementTable().getSelectedRow();
        if (rowSelected == -1) {
            JOptionPane.showMessageDialog(null, "Select a row first");
            return;
        }

        int dialogButton = JOptionPane.YES_NO_OPTION;
        int dialogResult = JOptionPane.showConfirmDialog(null, "Delete this ingredient?", "Confirmation", dialogButton);
        if (dialogResult == JOptionPane.YES_OPTION) {
            int id = (int) ingredientManagementView.getIngredientManagementTable().getValueAt(rowSelected, 0);
            Ingredient ingredient = ingredientService.getById(id);
            ingredient.setAvailable(false);
            ingredientService.update(ingredient);
            JOptionPane.showMessageDialog(null, "Success!");
        }
        loadData();
    }

    private void cancelHandler() {
        setButtonState(true);
        setFormState(false);
        resetForm();
    }

    private void doneHandler() {
        DefaultTableModel listModel = (DefaultTableModel) ingredientManagementView.getIngredientManagementTable().getModel();

        ArrayList<Ingredient> ingredientList = ingredientService.getAll();

        if (actionType.equals("edit")) // EDIT
        {
            int rowSelected = ingredientManagementView.getIngredientManagementTable().getSelectedRow();
            int id = (int) ingredientManagementView.getIngredientManagementTable().getValueAt(rowSelected, 0);
            
            //Truy xuất CSDL theo ID để lấy bản ghi
            Ingredient item = ingredientService.getById(id);

            String newName = ingredientManagementView.getIngredientNameTextField().getText();
            String unit = ingredientManagementView.getUnitTextField().getText();
            String regex = "^[a-zA-Z]{1,}$";

            if (newName.equals("")) {
                JOptionPane.showMessageDialog(null, "Inredient name is required!");
                return;
            }

            if (unit.equals("")) {
                JOptionPane.showMessageDialog(null, "Unit is requied!");
                return;
            } else if (!unit.matches(regex)) {
                JOptionPane.showMessageDialog(null, "Unit is incorrect!");
                return;
            }

//            for (Ingredient item1 : ingredientList) {
//                if (item1.getIngredientName().equalsIgnoreCase(newName) && item1.getUnit().equalsIgnoreCase(unit)) {
//                    JOptionPane.showMessageDialog(null, "Nguyên liệu đã tồn tạị!");
//                    return;
//                }
//            }

            item.setIngredientName(ingredientManagementView.getIngredientNameTextField().getText());
            item.setUnit(ingredientManagementView.getUnitTextField().getText());
            item.setAvailable(ingredientManagementView.getIsAvailbleRadioButton().isSelected());

            ingredientService.update(item);
            
            JOptionPane.showMessageDialog(null, "Success");
            ingredientManagementView.getIngredientNameTextField().setText("");
            ingredientManagementView.getUnitTextField().setText("");
            setFormState(false);
            setButtonState(true);
            loadData();
            return;
        } else if (actionType.equals("add")) // ADD
        {
            Ingredient newIngredient = new Ingredient();
            // Lay du lieu tu Form
            String a = ingredientManagementView.getIngredientNameTextField().getText();
            
            for (Ingredient item1 : ingredientList)
            {
                if (item1.getIngredientName().equalsIgnoreCase(a)) {
                    JOptionPane.showMessageDialog(null, "Ingredient is already existed");
                    return;
                } else if (ingredientManagementView.getIngredientNameTextField().getText().equals("")) {
                    JOptionPane.showMessageDialog(null, "Ingredient name is required!");
                    return;
                } else if (ingredientManagementView.getUnitTextField().getText().equals("")) {
                    JOptionPane.showMessageDialog(null, "Unit is required!");
                    return;
                }
            }
            
            newIngredient.setIngredientName(a);
            newIngredient.setUnit(ingredientManagementView.getUnitTextField().getText());
            newIngredient.setAvailable(true);
            
            ingredientService.insert(newIngredient);
            
            JOptionPane.showMessageDialog(null, "Success");
            ingredientManagementView.getIngredientNameTextField().setText("");
            ingredientManagementView.getUnitTextField().setText("");
            setFormState(false);
            setButtonState(true);
            loadData();
            return;
        }
    }

    private void setButtonState(boolean state) {
        ingredientManagementView.getAddIngredientButton().setEnabled(state);
        ingredientManagementView.getUpdateIngredientButton().setEnabled(state);
        ingredientManagementView.getRemoveIngredientButton().setEnabled(state);
        ingredientManagementView.getCancelButton().setEnabled(!state);
        ingredientManagementView.getDoneButton().setEnabled(!state);
    }

    private void resetForm() {
        ingredientManagementView.getIngredientNameTextField().setText("");
        ingredientManagementView.getUnitTextField().setText("");
        ingredientManagementView.getIsAvailbleRadioButton().setSelected(false);
    }

    private void setFormState(boolean state) {
        ingredientManagementView.getIngredientNameTextField().setEnabled(state);
        ingredientManagementView.getUnitTextField().setEnabled(state);
        ingredientManagementView.getIsAvailbleRadioButton().setEnabled(state);
    }

    @Override
    public JPanel getPanel() {
        return ingredientManagementView;
    }

    @Override
    public void loadData() {
        resetForm();
        ArrayList<Ingredient> ingredientList = ingredientService.getAll();
        DefaultTableModel listModel = (DefaultTableModel) ingredientManagementView.getIngredientManagementTable().getModel();

        for (int i = listModel.getRowCount() - 1; i >= 0; i--) {
            listModel.removeRow(i);
        }

        for (Ingredient item : ingredientList) {
        //    if (item.isAvailable()) {
                Object[] rowData = new Object[]{
                    item.getId(), // ID
                    item.getIngredientName(), // Ten NL
                    item.getUnit(), // Don vi
                    item.isAvailable(),
                };
                listModel.addRow(rowData);
         //   }
        }
        decorateTable();
        setFormState(false);
        setButtonState(true);
    }

    public void decorateTable() {
        ((DefaultTableCellRenderer) ingredientManagementView.getIngredientManagementTable().getTableHeader().getDefaultRenderer())
                .setHorizontalAlignment((int) JLabel.CENTER_ALIGNMENT);

        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(JLabel.CENTER);
        ingredientManagementView.getIngredientManagementTable().getColumnModel().getColumn(0).setCellRenderer(centerRenderer);
        for (int x = 0; x < ingredientManagementView.getIngredientManagementTable().getColumnCount(); x++) {
            ingredientManagementView.getIngredientManagementTable().getColumnModel().getColumn(x).setCellRenderer(centerRenderer);
        }
    }
}
