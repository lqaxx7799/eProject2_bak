package controllers.managements;

import controllers.BaseController;
import java.util.ArrayList;
import java.util.Date;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.table.DefaultTableModel;
import models.MenuCategory;
import models.MenuItem;
import services.MenuCategoryService;
import services.MenuItemService;
import views.managements.MenuItemManagementView;

public class MenuItemManagementController implements BaseController {

    private MenuCategoryService menuCategoryService;
    private MenuItemService menuItemService;
    private MenuItemManagementView menuItemManagementView;
    private String actionName;
    private int CurrentItemId;

    public MenuItemManagementController() {
        menuCategoryService = new MenuCategoryService();
        menuItemService = new MenuItemService();
        menuItemManagementView = new MenuItemManagementView();
        loadTable();
        setButtonState(true);
        setFormState(false);
        menuItemManagementView.getBtnAdd().addActionListener(e -> btnAddHandler());
        menuItemManagementView.getBtnFix().addActionListener(e -> btnFixHandler());
        menuItemManagementView.getBtnRemove().addActionListener(e -> btnRemoveHandler());
        menuItemManagementView.getBtnOK().addActionListener(e -> btnOKHandler());
        menuItemManagementView.getBtnCancel().addActionListener(e -> btnCancelHandler());
    }

    public void loadTable() {
        ArrayList<MenuItem> menuItems = menuItemService.getAll();
        DefaultTableModel menuItemTableModel = (DefaultTableModel) menuItemManagementView.getTblMenuItem().getModel();
        for (int i = menuItemTableModel.getRowCount() - 1; i >= 0; i--) {
            menuItemTableModel.removeRow(i);
        }
        for (MenuItem item : menuItems) {
            if (item.isAvailable()) {
                Object[] data = new Object[]{
                    item.getId(),
                    item.getItemName(),
                    item.getCreatedTime(),
                    item.isAvailable(),
                    item.getPrice(),
                    item.getMenuCategoryId()
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
        int selectedRow = menuItemManagementView.getTblMenuItem().getSelectedRow();
        if (selectedRow == - 1) {
            JOptionPane.showMessageDialog(null, "Chọn một hàng trước");
            return;
        }
        int menuitemId = (int) menuItemManagementView.getTblMenuItem().getValueAt(selectedRow, 0);
        MenuItem menuItem = menuItemService.getById(menuitemId);
        menuItemManagementView.getTxtItemName().setText(menuItem.getItemName());
        menuItemManagementView.getTxtPrice().setText(String.valueOf(menuItem.getPrice()));
        menuItemManagementView.getTxtCategoryId().setText(String.valueOf(menuItem.getMenuCategoryId()));

        setButtonState(false);
        setFormState(true);
        actionName = "fix";
        CurrentItemId = menuitemId;
    }

    public void btnRemoveHandler() {
        int dialogResult = JOptionPane.showConfirmDialog(null, "Bạn có muốn xóa món này", "Xác nhận", JOptionPane.YES_NO_OPTION);
        if (dialogResult == JOptionPane.YES_OPTION) {
            int selectedRow = menuItemManagementView.getTblMenuItem().getSelectedRow();
            if (selectedRow == - 1) {
                JOptionPane.showMessageDialog(null, "Chọn một hàng trước");
                return;
            }
            int menuitemId = (int) menuItemManagementView.getTblMenuItem().getValueAt(selectedRow, 0);
            MenuItem menuItem = menuItemService.getById(menuitemId);
            menuItem.setAvailable(false);
            menuItemService.update(menuItem);
            JOptionPane.showMessageDialog(null, "Xóa thành công");
            loadTable();
        }
    }

    public void btnOKHandler() {
        resetErrorLabel();
        Double price = 0.0;
        int categoryId = -1;
        String regex = "^[0-9]{1,}\\.{1}[0-9]{1,}$";
        String regex1 = "^[0-9]{1,}$";
        if (menuItemManagementView.getTxtPrice().getText().matches(regex) == true || menuItemManagementView.getTxtPrice().getText().matches(regex1) == true) {
            price = Double.parseDouble(menuItemManagementView.getTxtPrice().getText());
        }
        if (menuItemManagementView.getTxtCategoryId().getText().matches(regex1) == true) {
            categoryId = Integer.parseInt(menuItemManagementView.getTxtCategoryId().getText());
        }
        String itemName = menuItemManagementView.getTxtItemName().getText();
        boolean check = true;
        if (itemName.equals("")) {
            menuItemManagementView.getLblErrorName().setText("Chưa nhập tên");
            check = false;
        } else if (actionName.equals("add")){
            ArrayList<MenuItem> menuItems = menuItemService.getAll();
            for (MenuItem item : menuItems) {
                if (item.getItemName().equalsIgnoreCase(itemName) && item.isAvailable()) {
                    menuItemManagementView.getLblErrorName().setText("Món đã tồn tại");
                    check = false;
                    break;
                } else if (item.getItemName().equals(itemName) && item.isAvailable() == false) {
                    check = false;
                    item.setAvailable(true);
                    item.setPrice(price);
                    item.setCreatedTime(new Date());
                    item.setMenuCategoryId(categoryId);
                    menuItemService.update(item);
                    JOptionPane.showMessageDialog(null, "Thêm thành công");
                    loadTable();
                    resetForm();
                    resetErrorLabel();
                    setButtonState(true);
                    setFormState(false);
                    return;
                }
            }
        }
        if (menuItemManagementView.getTxtPrice().getText().equals("")) {
            menuItemManagementView.getLblErrorPrice().setText("Chưa nhập giá");
            check = false;
        } else if (menuItemManagementView.getTxtPrice().getText().matches(regex) == false && menuItemManagementView.getTxtPrice().getText().matches(regex1) == false) {
            menuItemManagementView.getLblErrorPrice().setText("Chỉ đc nhập số");
            check = false;
        }
        int count = 0;
        if (menuItemManagementView.getTxtCategoryId().getText().equals("")) {
            menuItemManagementView.getLblErrorCategoryId().setText("Chưa nhập id danh mục");
            check = false;
        } else if (menuItemManagementView.getTxtCategoryId().getText().matches(regex1) == false) {
            menuItemManagementView.getLblErrorCategoryId().setText("Chỉ được nhập số");
            check = false;
        } else {
            ArrayList<MenuCategory> menuCategorys = menuCategoryService.getAll();
            for (MenuCategory item : menuCategorys) {
                if (item.getId() == categoryId) {
                    count = 1;
                    break;
                }
            }
            if (count == 0) {
                menuItemManagementView.getLblErrorCategoryId().setText("Không tồn tại danh mục");
                check = false;
            }
        }

        if (check) {
            if (actionName.equals("add")) {
                MenuItem item = new MenuItem();
                item.setItemName(itemName);
                item.setAvailable(true);
                item.setPrice(price);
                item.setMenuCategoryId(categoryId);
                item.setCreatedTime(new Date());
                menuItemService.insert(item);
                JOptionPane.showMessageDialog(null, "Thêm thành công");
                loadTable();
                resetForm();
                setButtonState(true);
                setFormState(false);
            } else if (actionName.equals("fix")) {
                MenuItem item = menuItemService.getById(CurrentItemId);
                item.setItemName(itemName);
                item.setAvailable(true);
                item.setPrice(price);
                item.setMenuCategoryId(categoryId);
                menuItemService.update(item);
                JOptionPane.showMessageDialog(null, "Sửa thành công");
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
        menuItemManagementView.getBtnAdd().setEnabled(state);
        menuItemManagementView.getBtnFix().setEnabled(state);
        menuItemManagementView.getBtnRemove().setEnabled(state);
        menuItemManagementView.getBtnOK().setEnabled(!state);
        menuItemManagementView.getBtnCancel().setEnabled(!state);
    }

    public void setFormState(boolean state) {
        menuItemManagementView.getTxtCategoryId().setEnabled(state);
        menuItemManagementView.getTxtItemName().setEnabled(state);
        menuItemManagementView.getTxtPrice().setEnabled(state);
    }

    public void resetForm() {
        menuItemManagementView.getTxtCategoryId().setText("");
        menuItemManagementView.getTxtItemName().setText("");
        menuItemManagementView.getTxtPrice().setText("");
    }

    public void resetErrorLabel() {
        menuItemManagementView.getLblErrorName().setText("");
        menuItemManagementView.getLblErrorPrice().setText("");
        menuItemManagementView.getLblErrorCategoryId().setText("");
    }

    @Override
    public JPanel getPanel() {
        return menuItemManagementView;
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
