package controllers.managements;

import controllers.BaseController;
import java.util.ArrayList;
import java.util.Date;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.table.DefaultTableModel;
import models.MenuCategory;
import services.MenuCategoryService;
import views.managements.MenuCategoryManagementView;

public class MenuCategoryManagementController implements BaseController {

    private MenuCategoryService menuCategoryService;
    private MenuCategoryManagementView menuCategoryManagementView;
    private String actionName;
    private int CurrentCategoryId;

    public MenuCategoryManagementController() {
        menuCategoryService = new MenuCategoryService();
        menuCategoryManagementView = new MenuCategoryManagementView();
        loadTable();
        setButtonState(true);
        setFormState(false);
        menuCategoryManagementView.getBtnAdd().addActionListener(e -> btnAddHandler());
        menuCategoryManagementView.getBtnFix().addActionListener(e -> btnFixHandler());
        menuCategoryManagementView.getBtnRemove().addActionListener(e -> btnRemoveHandler());
        menuCategoryManagementView.getBtnOK().addActionListener(e -> btnOKHandler());
        menuCategoryManagementView.getBtnCancel().addActionListener(e -> btnCancelHandler());
    }

    public void loadTable() {
        ArrayList<MenuCategory> menuCategorys = menuCategoryService.getAll();
        DefaultTableModel menuItemTableModel = (DefaultTableModel) menuCategoryManagementView.getjTable1().getModel();
        for (int i = menuItemTableModel.getRowCount() - 1; i >= 0; i--) {
            menuItemTableModel.removeRow(i);
        }
        for (MenuCategory item : menuCategorys) {
            if (item.isAvailable()) {
                Object[] data = new Object[]{
                    item.getId(),
                    item.getCategoryName(),
                    item.isAvailable(),
                    item.getCreatedTime()
                };
                menuItemTableModel.addRow(data);
            }
        }
    }

    public void btnAddHandler() {
        setButtonState(false);
        setFormState(true);
        actionName = "add";
    }

    public void btnFixHandler() {
        int selectedRow = menuCategoryManagementView.getjTable1().getSelectedRow();
        if (selectedRow == - 1) {
            JOptionPane.showMessageDialog(null, "Select a row first");
            return;
        }
        int menuCategoryId = (int) menuCategoryManagementView.getjTable1().getValueAt(selectedRow, 0);
        MenuCategory menuCategory = menuCategoryService.getById(menuCategoryId);
        menuCategoryManagementView.getTxtCategoryName().setText(menuCategory.getCategoryName());

        setButtonState(false);
        setFormState(true);
        actionName = "fix";
        CurrentCategoryId = menuCategoryId;
    }

    public void btnRemoveHandler() {
        int dialogResult = JOptionPane.showConfirmDialog(null, "Do you really want to delete this category", "Confirmation", JOptionPane.YES_NO_OPTION);
        if (dialogResult == JOptionPane.YES_OPTION) {
            int selectedRow = menuCategoryManagementView.getjTable1().getSelectedRow();
            if (selectedRow == - 1) {
                JOptionPane.showMessageDialog(null, "Chọn một hàng trước");
                return;
            }
            int menuCategoryId = (int) menuCategoryManagementView.getjTable1().getValueAt(selectedRow, 0);
            MenuCategory menuCategory = menuCategoryService.getById(menuCategoryId);
            menuCategory.setAvailable(false);
            menuCategoryService.update(menuCategory);
            JOptionPane.showMessageDialog(null, "Success");
            loadTable();
        }
    }

    public void btnOKHandler() {
        resetErrorLabel();

        String categoryName = menuCategoryManagementView.getTxtCategoryName().getText();
        boolean check = true;
        if (categoryName.equals("")) {
            menuCategoryManagementView.getLblErrorName().setText("Category name is required");
            check = false;
        } else if (actionName.equals("add")) {
            ArrayList<MenuCategory> menuCategorys = menuCategoryService.getAll();
            for (MenuCategory item : menuCategorys) {
                if (item.getCategoryName().equalsIgnoreCase(categoryName) && item.isAvailable()) {
                    menuCategoryManagementView.getLblErrorName().setText("Category name is already existed");
                    check = false;
                    break;
                } else if (item.getCategoryName().equals(categoryName) && item.isAvailable() == false) {
                    check = false;
                    item.setAvailable(true);
                    item.setCreatedTime(new Date());
                    menuCategoryService.update(item);
                    JOptionPane.showMessageDialog(null, "Success");
                    loadTable();
                    resetForm();
                    resetErrorLabel();
                    setButtonState(true);
                    setFormState(false);
                    return;
                }
            }
        }

        if (check) {
            if (actionName.equals("add")) {
                MenuCategory category = new MenuCategory();
                category.setCategoryName(categoryName);
                category.setAvailable(true);
                category.setCreatedTime(new Date());
                menuCategoryService.insert(category);
                JOptionPane.showMessageDialog(null, "Success");
                loadTable();
                resetForm();
                setButtonState(true);
                setFormState(false);
            } else if (actionName.equals("fix")) {
                MenuCategory category = menuCategoryService.getById(CurrentCategoryId);
                category.setCategoryName(categoryName);
                menuCategoryService.update(category);
                JOptionPane.showMessageDialog(null, "Success");
                loadTable();
                resetForm();
                setButtonState(true);
                setFormState(false);
            }
        }
    }

    public void btnCancelHandler() {
        resetErrorLabel();
        resetForm();
        setButtonState(true);
        setFormState(false);
        actionName = "";
    }

    public void setButtonState(boolean state) {
        menuCategoryManagementView.getBtnAdd().setEnabled(state);
        menuCategoryManagementView.getBtnFix().setEnabled(state);
        menuCategoryManagementView.getBtnRemove().setEnabled(state);
        menuCategoryManagementView.getBtnOK().setEnabled(!state);
        menuCategoryManagementView.getBtnCancel().setEnabled(!state);
    }

    public void setFormState(boolean state) {
        menuCategoryManagementView.getTxtCategoryName().setEnabled(state);
    }

    public void resetForm() {
        menuCategoryManagementView.getTxtCategoryName().setText("");

    }

    public void resetErrorLabel() {
        menuCategoryManagementView.getLblErrorName().setText("");
    }

    @Override
    public JPanel getPanel() {
        return menuCategoryManagementView;
    }

    @Override
    public void loadData() {
        loadTable();
        resetForm();
        resetErrorLabel();
        setButtonState(true);
        setFormState(false);
    }
}
