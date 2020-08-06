/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import java.awt.ComponentOrientation;
import java.util.ArrayList;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import models.MenuCategory;
import models.MenuItem;
import models.Receipt;
import models.ReceiptDetail;
import models.Table;
import services.MenuCategoryService;
import services.MenuItemService;
import services.ReceiptDetailService;
import services.ReceiptService;
import services.TableService;
import views.AwaitedDishListWaiterView;

/**
 *
 * @author Admin
 */
public class AwaitedDishListWaiterController implements BaseController{

    private AwaitedDishListWaiterView awaitedDishListWaiterView;
    private ReceiptDetailService receiptDetailService;
    private MenuItemService menuItemService;
    private MenuCategoryService menuCategoryService;
    private ReceiptService receiptService;
    private TableService tableService;

    public AwaitedDishListWaiterController() {
        awaitedDishListWaiterView = new AwaitedDishListWaiterView();
        receiptDetailService = new ReceiptDetailService();
        menuItemService = new MenuItemService();
        menuCategoryService = new MenuCategoryService();
        receiptService = new ReceiptService();
        tableService = new TableService();

        awaitedDishListWaiterView.getBtnWatierUpdate().addActionListener(al -> updateHandler());
        awaitedDishListWaiterView.getBtnWatierDone().addActionListener(al -> doneHandler());
    }
    
    @Override
    public JPanel getPanel() {
        return awaitedDishListWaiterView;
    }

    @Override
    public void loadData() {
        DefaultTableModel listModel = (DefaultTableModel) awaitedDishListWaiterView.getAwaitedDishLstWaiterTable().getModel();
        
        for (int i = listModel.getRowCount() - 1; i >= 0; i--) {
            listModel.removeRow(i);
        }
    }
    

    private void updateHandler() {
        DefaultTableModel listModel = (DefaultTableModel) awaitedDishListWaiterView.getAwaitedDishLstWaiterTable().getModel();
        
        ArrayList<ReceiptDetail> receiptDetails = receiptDetailService.getAll(); // CSDL receiptDetails

        ArrayList<MenuItem> menuItems = menuItemService.getAll();  // CSDL menuItems

        ArrayList<MenuCategory> menuCategorys = menuCategoryService.getAll(); // CSDL menuCategory

        ArrayList<Receipt> receipts = receiptService.getAll();      // CSDL receipt

        ArrayList<Table> tables = tableService.getAll(); // CSDL table

        for (int i = listModel.getRowCount() - 1; i >= 0; i--) {
            listModel.removeRow(i);
        }

        for (ReceiptDetail item : receiptDetails) {
            if (item.isServed() == false && item.isMade() == true) {
                MenuItem menuItem = new MenuItem();
                MenuCategory mc = new MenuCategory();   // mc.getCategoryName()
                Receipt hd = new Receipt();
                Table ban = new Table();

                for (Receipt hdTemp : receipts) {
                    if (item.getReceiptId() == hdTemp.getId()) {
                        hd = hdTemp;
                        for (Table banTemp : tables) {
                            if (hd.getTableId() == banTemp.getId()) {
                                ban = banTemp;
                                break;
                            }
                        }
                    }
                }

                for (MenuItem mi : menuItems) {
                    if (mi.getId() == item.getMenuItemId()) {
                        for (MenuCategory temp : menuCategorys) {
                            if (mi.getMenuCategoryId() == temp.getId()) {
                                mc = temp;
                                break;
                            }
                        }
                        menuItem = mi;
                        break;
                    }
                }

                Object[] rowData = new Object[]{
                    item.getId(),               // ID cua ReceiptDetail
                    menuItem.getItemName(),     // Ten mon
                    mc.getCategoryName(),       // Danh muc mon
                    item.getQuantity(),         // So luong
                    ban.getTableName(),         // Ban  
                    hd.getArrivedTime()         // Thoi gian
                };
                listModel.addRow(rowData);
            }
        }
        decorateTable();
    }

    private void doneHandler() {
        DefaultTableModel listModel = (DefaultTableModel) awaitedDishListWaiterView.getAwaitedDishLstWaiterTable().getModel();

        int rowSelected = awaitedDishListWaiterView.getAwaitedDishLstWaiterTable().getSelectedRow();
        
        if (rowSelected == -1)
        {
            JOptionPane.showMessageDialog(null, "Chọn một hàng trước");
            return;
        }

        int id = (int) awaitedDishListWaiterView.getAwaitedDishLstWaiterTable().getValueAt(rowSelected, 0);

        listModel.removeRow(rowSelected);

        ReceiptDetail rd = receiptDetailService.getById(id); // Lay ban ghi theo ID

        rd.setServed(true);

        receiptDetailService.update(rd);
    }

    public void decorateTable() {
        ((DefaultTableCellRenderer) awaitedDishListWaiterView.getAwaitedDishLstWaiterTable().getTableHeader().getDefaultRenderer())
                .setHorizontalAlignment((int) JLabel.CENTER_ALIGNMENT);

        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(JLabel.CENTER);
        awaitedDishListWaiterView.getAwaitedDishLstWaiterTable().getColumnModel().getColumn(0).setCellRenderer(centerRenderer);
        for (int x = 0; x < awaitedDishListWaiterView.getAwaitedDishLstWaiterTable().getColumnCount(); x++) {
            awaitedDishListWaiterView.getAwaitedDishLstWaiterTable().getColumnModel().getColumn(x).setCellRenderer(centerRenderer);
        }
    }
}
