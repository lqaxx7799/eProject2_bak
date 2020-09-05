package controllers;

import java.awt.Color; 
import java.util.ArrayList;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.table.DefaultTableModel;
import models.BillList;
import models.MenuItem;
import models.Receipt;
import models.ReceiptDetail;
import models.Table;
import services.MenuCategoryService;
import services.MenuItemService;
import services.TableService;
import views.OrderView;
import services.ReceiptService;
import services.ReceiptDetailService;
import services.BillListService;
import app.App;
import java.awt.Dimension;
import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.Date;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class OrderController implements BaseController{
    private OrderView orderView;
    private TableService tableService;
    private MenuItemService menuItemService;
    private MenuCategoryService menuCategoryService;
    private ReceiptService receiptService;
    private ReceiptDetailService receiptDetailService;
    private BillListService billListService;
    public int CurrentTableId;
    public int CurrentReceiptId;
    public double totalPaymentPrice = 0;
    
    public OrderController(){
        orderView = new OrderView();
        loadTable();
        menuCategoryService = new MenuCategoryService();
        menuItemService = new MenuItemService();
        billListService = new BillListService();
        receiptService = new ReceiptService();
        receiptDetailService = new ReceiptDetailService();
        orderView.getCbMenuCategory().addActionListener(e -> showCbMenuItem());
        orderView.getBtnAdd().addActionListener(e -> BtnAddHandler());
        orderView.getBtnPay().addActionListener(e -> BtnPayHandlder());
        orderView.getBtnAddReceipt().addActionListener(e -> btnAddReceiptHandler());
    } 
    public OrderView initController(){
        return orderView;
    }
    
    private void showCbMenuItem(){
        int slected = orderView.getCbMenuCategory().getSelectedIndex() + 1 ;
        ArrayList<String> menuItemList = new ArrayList<>();
        ArrayList<MenuItem> menuItem = menuItemService.getAll();
        for(MenuItem item : menuItem){
            if(item.getMenuCategoryId() == slected && item.isAvailable()){
                menuItemList.add(item.getItemName());
            }
        }
        orderView.getCbMenuItem().setModel(new DefaultComboBoxModel<String>(menuItemList.toArray(new String[0])));
    }
    
    private void showBill(int tableId){
            CurrentTableId = tableId;
            CurrentReceiptId = billListService.getReceiptIdByTableId(tableId);
            orderView.getLabelTableName().setText(tableService.getById(CurrentTableId).getTableName());
            orderView.getLblReceiptId().setText("No. " + CurrentReceiptId);
            DefaultTableModel tableModel= (DefaultTableModel)orderView.getjTable2().getModel();
            for (int i = tableModel.getRowCount() - 1; i >= 0; i--) {
                tableModel.removeRow(i);
            }
            ArrayList<BillList> billList = billListService.getByTableId(CurrentTableId);
            for(BillList item : billList){
                Object[] data = {item.getItem_name(),item.getQuantity(),item.getPrice(),item.getTotalPrice()};
                tableModel.addRow(data);
            }
    }

    private void BtnAddHandler(){
        if(CurrentReceiptId==0){
            JOptionPane.showMessageDialog(null, "This table does not have any receipt");
            return;
        }
        String regex = "^[0-9]{1,}$";
        int selectedIdex = orderView.getCbMenuItem().getSelectedIndex();
        if (orderView.getTxtQuantity().getText().equals("")){
            JOptionPane.showMessageDialog(null, "Quantity cannot be empty");
            return;
        }else if(orderView.getTxtQuantity().getText().matches(regex) == false){
            JOptionPane.showMessageDialog(null, "Quantity must be a number");
            return;
        }
        if(selectedIdex == - 1){
            JOptionPane.showMessageDialog(null, "Menu Item must be selected");
            return;
        }
        int quantity = Integer.parseInt(orderView.getTxtQuantity().getText());
        ArrayList<MenuItem> menuItemList = menuItemService.getAll();
        String slectedMenuItem = orderView.getCbMenuItem().getSelectedItem().toString();
        MenuItem menuItem = new MenuItem();
        for(MenuItem item : menuItemList){
            if(item.getItemName().equals(slectedMenuItem)){
                menuItem = item;
                break;
            }
        }
        ArrayList<ReceiptDetail> rd = receiptDetailService.getAll();
        for(ReceiptDetail item: rd){
            if(item.getReceiptId()==CurrentReceiptId && item.getMenuItemId()==menuItem.getId()){
                item.setQuantity(item.getQuantity()+quantity);
                receiptDetailService.update(item);
                showBill(CurrentTableId);
                resetForm();
                return;
            }
        }
            int menu_item_id = menuItem.getId();
            Double unit_price = menuItem.getPrice();
            boolean is_made = false;
            boolean is_served = false;
            ReceiptDetail receiptDetail = new ReceiptDetail();
            receiptDetail.setReceiptId(CurrentReceiptId);
            receiptDetail.setMenuItemId(menu_item_id);
            receiptDetail.setQuantity(quantity);
            receiptDetail.setUnitPrice(unit_price);
            receiptDetail.setMade(is_made);
            receiptDetail.setServed(is_served);
            receiptDetailService.insert(receiptDetail);
            showBill(CurrentTableId);
            resetForm();
    }    
    
    private void BtnPayHandlder() {
        if(CurrentReceiptId == 0){
            JOptionPane.showMessageDialog(null,"This table does not have any receipt" , "Alert", JOptionPane.INFORMATION_MESSAGE);
            return;
        }
        else{
            int dialogResult = JOptionPane.showConfirmDialog(null, "Do you want to check out "+tableService.getById(CurrentTableId).getTableName(),
                    "Confirm", JOptionPane.YES_NO_OPTION);
            if(dialogResult == JOptionPane.YES_OPTION){
                DefaultTableModel model = (DefaultTableModel) orderView.getjTable2().getModel();
                for (int i = model.getRowCount() - 1; i >= 0; i--) {
                    totalPaymentPrice += (Double)orderView.getjTable2().getValueAt(i, 3);
                }
//                try {
//                    FileOutputStream outputStream = new FileOutputStream("E:\\Work\\CNPM\\cnpm_11_restaurant\\Bill.txt");
//                    OutputStreamWriter outputStreamWriter = new OutputStreamWriter(outputStream, "UTF-16");
//                    BufferedWriter bufferedWriter = new BufferedWriter(outputStreamWriter);
//                    bufferedWriter.write("Receipt: "+CurrentReceiptId);
//                    for(int i= 0;i <  model.getRowCount() ; i++){
//                        bufferedWriter.newLine();
//                        bufferedWriter.write(orderView.getjTable2().getValueAt(i,0).toString()+" "+orderView.getjTable2().getValueAt(i,1).toString()+" "+orderView.getjTable2().getValueAt(i,2).toString()+" "+orderView.getjTable2().getValueAt(i,3).toString());
//                    }
//                    bufferedWriter.newLine();
//                    bufferedWriter.write("Total: "+totalPaymentPrice);
//                    bufferedWriter.close();
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
                Receipt rc_temp = receiptService.getById(CurrentReceiptId);
                rc_temp.setPaid(true);
                rc_temp.setPaidTime(new Date());
                receiptService.update(rc_temp);
                Table table = tableService.getById(CurrentTableId);
                table.setOccupied(false);
                tableService.update(table);
                JOptionPane.showMessageDialog(null, "Check out successfully");
                showBill(CurrentTableId);
                loadTable();
                totalPaymentPrice = 0;
            }
        }
        CurrentReceiptId = billListService.getReceiptIdByTableId(CurrentTableId);
        orderView.getLblReceiptId().setText("No. " + CurrentReceiptId);
    }
    
    private void loadTable(){
        orderView.getjPanel3().removeAll();
        tableService = new TableService();
        ArrayList<Table> tableList = tableService.getAll();
        int i = tableList.size();
        int h;
        if(i%4 == 0){h=i/4;}
        else{h=i/4 + 1; }
        orderView.getjPanel3().setPreferredSize(new Dimension(400,h*100));
        for(Table item : tableList){
            JButton btn = new JButton();
            btn.setPreferredSize(new Dimension(200,180));
            btn.setText(item.getTableName());
            if(item.isOccupied()){
                btn.setBackground(Color.red);
            }
            btn.addActionListener(e -> showBill(item.getId()));
            orderView.getjPanel3().add(btn);
        }
    }
        
    public void btnAddReceiptHandler(){
        if(CurrentReceiptId >0){
            JOptionPane.showMessageDialog(null, "This table already had a receipt");
            return;
        }
        else{
            Receipt rc = new Receipt();
            rc.setArrivedTime(new Date());
            rc.setPaidTime(null);
            rc.setPaid(false);
            rc.setTableId(CurrentTableId);
            rc.setAccountId(App.currentAccount.getId());
            receiptService.insert(rc);
            JOptionPane.showMessageDialog(null, "Add receipt successfully");
            Table table = tableService.getById(CurrentTableId);
            table.setOccupied(true);
            tableService.update(table);
            loadTable();
        }
        CurrentReceiptId = billListService.getReceiptIdByTableId(CurrentTableId);
        orderView.getLblReceiptId().setText("No. " + CurrentReceiptId);
    }
    
    public void resetForm(){
        orderView.getTxtQuantity().setText("");
        orderView.getCbMenuItem().setSelectedIndex(-1);
        orderView.getCbMenuCategory().setSelectedIndex(-1);
    }
    @Override
    public JPanel getPanel() {
        return orderView;
    }

    @Override
    public void loadData(){
        loadTable();
        ArrayList<String> menuCategoryList = menuCategoryService.getNameAll();
        orderView.getCbMenuCategory().setModel(new DefaultComboBoxModel<String>(menuCategoryList.toArray(new String[0])));
        orderView.getTxtQuantity().setText("");
        orderView.getCbMenuItem().setSelectedIndex(-1);
        orderView.getCbMenuCategory().setSelectedIndex(-1);
    }
 }
