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
import views.AwaitedDishListChefView;

/**
 *
 * @author Admin
 */
public class AwaitedDishListChefController  implements BaseController{

    // Lay isMade trong ReceiptDetail.
    // Lây categoryName trong MenuCategory (Danh muc mon)
    // Lay quantity trong ReceiptDetail .
    // Lay tableID trong Receipt xong lấy tên bàn trong Table
    // Lay ArrivedTime trong Receipt
    
    private AwaitedDishListChefView awaitedDishListChefView;
    private ReceiptDetailService receiptDetailService;
    private MenuItemService menuItemService;
    private MenuCategoryService menuCategoryService;
    private ReceiptService receiptService;
    private TableService tableService;

    public AwaitedDishListChefController() {
        awaitedDishListChefView = new AwaitedDishListChefView();
        receiptDetailService = new ReceiptDetailService();
        menuItemService = new MenuItemService();
        menuCategoryService = new MenuCategoryService();
        receiptService = new ReceiptService();
        tableService = new TableService();

        awaitedDishListChefView.getBtnUpdate().addActionListener(al -> updateHandler());
        awaitedDishListChefView.getBtnDone().addActionListener(al -> doneHandler());
    }
    
    
    @Override
    public JPanel getPanel() {
        return awaitedDishListChefView;
    }

    @Override
    public void loadData() {
        DefaultTableModel listModel = (DefaultTableModel) awaitedDishListChefView.getAwaitedDishLstTable().getModel();
        for (int i = listModel.getRowCount() - 1; i >= 0; i--) {
            listModel.removeRow(i);
        }
    }

    private void updateHandler() {
        DefaultTableModel listModel = (DefaultTableModel) awaitedDishListChefView.getAwaitedDishLstTable().getModel();

        ArrayList<ReceiptDetail> receiptDetails = receiptDetailService.getAll(); // CSDL receiptDetails

        ArrayList<MenuItem> menuItems = menuItemService.getAll();  // CSDL menuItems

        ArrayList<MenuCategory> menuCategorys = menuCategoryService.getAll(); // CSDL menuCategory

        ArrayList<Receipt> receipts = receiptService.getAll();      // CSDL receipt

        ArrayList<Table> tables = tableService.getAll(); // CSDL table

        for (int i = listModel.getRowCount() - 1; i >= 0; i--) {
            listModel.removeRow(i);
        }

        for (ReceiptDetail item : receiptDetails)
        {
            if (item.isMade() == false) // Nếu chưa được làm
            {          
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
                    item.getId(),           // ID cua           ReceiptDetail. 
                    menuItem.getItemName(), // Ten mon.         MenuItem
                    mc.getCategoryName(),   // Danh muc mon.    MenuCategory
                    item.getQuantity(),     // So luong.        ReceiptDetail
                    ban.getTableName(),     // Ban.             Table
                    hd.getArrivedTime()     // Thoi gian        Receipt
                };
                listModel.addRow(rowData);
            }
        }
       decorateTable();
    }

    private void doneHandler() {
        DefaultTableModel listModel = (DefaultTableModel) awaitedDishListChefView.getAwaitedDishLstTable().getModel();
        
        int rowSelected = awaitedDishListChefView.getAwaitedDishLstTable().getSelectedRow();
        
        if(rowSelected == -1)
        {
            JOptionPane.showMessageDialog(null, "Chọn một hàng trước");
            return;
        }
            
        
        int id = (int) awaitedDishListChefView.getAwaitedDishLstTable().getValueAt(rowSelected, 0);
        
        listModel.removeRow(rowSelected);

        ReceiptDetail rd = receiptDetailService.getById(id); // Lay ban ghi theo ID
        
        rd.setMade(true);
        
        receiptDetailService.update(rd);

    }
    
    public void decorateTable() {
        ((DefaultTableCellRenderer) awaitedDishListChefView.getAwaitedDishLstTable().getTableHeader().getDefaultRenderer())
                .setHorizontalAlignment((int) JLabel.CENTER_ALIGNMENT);

        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(JLabel.CENTER);
        awaitedDishListChefView.getAwaitedDishLstTable().getColumnModel().getColumn(0).setCellRenderer(centerRenderer);
        for (int x = 0; x < awaitedDishListChefView.getAwaitedDishLstTable().getColumnCount(); x++) {
            awaitedDishListChefView.getAwaitedDishLstTable().getColumnModel().getColumn(x).setCellRenderer(centerRenderer);
        }
    }
}
