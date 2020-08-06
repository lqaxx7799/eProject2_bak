/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import app.App;
import java.text.DateFormat;
import java.util.ArrayList;
import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListModel;
import javax.swing.JList;
import views.IngredientImportView;
import models.Ingredient;
import models.IngredientImport;
import services.IngredientImportService;
import services.IngredientService;
import java.util.Date;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

/**
 *
 * @author Admin
 */
public class IngredientImportController implements BaseController {

    private IngredientImportView ingredientImportView;

    private IngredientService ingredientService;
    private IngredientImportService ingredientImportService;
    private JList<String> IngredientList;

    public IngredientImportController() {
        ingredientImportView = new IngredientImportView();
        ingredientService = new IngredientService();
        ingredientImportService = new IngredientImportService();

        ingredientImportView.getCbIngredientList().addActionListener(al -> onIngredientChange()); // Khi chon nguyen lieu trong combobox
        ingredientImportView.getNhapnlButton().addActionListener(al -> ingredientImportHandler());
    }

    @Override
    public JPanel getPanel() {
        return ingredientImportView;
    }

    @Override
    public void loadData() {
        ArrayList<Ingredient> ingredientList = ingredientService.getAll();
        ArrayList<String> ingredientName = new ArrayList<>();
        for (Ingredient item : ingredientList) {
            if(item.isAvailable()){
                ingredientName.add(item.getIngredientName());
            }
        }
        ingredientImportView.getCbIngredientList().setModel(new DefaultComboBoxModel<String>(ingredientName.toArray(new String[0])));
        ingredientImportView.getTxtMessage().setText("");
        ingredientImportView.getLblUnit().setText(ingredientList.isEmpty() ? "" : ingredientList.get(0).getUnit());
    }

    private void onIngredientChange() {
        String ingredientName = ingredientImportView.getCbIngredientList().getSelectedItem().toString();
        ArrayList<Ingredient> ingredientList = ingredientService.getAll();
        for(Ingredient item: ingredientList){
            if(item.getIngredientName().equalsIgnoreCase(ingredientName)){
                ingredientImportView.getLblUnit().setText(item.getUnit());
                break;
            }
        }
    }

    private void ingredientImportHandler() { // Chi xử lí khi mình ấn vào nút nhập nguyên liệu
        // CAN LUU
        // ID nguyen lieu .
        // Thoi gian nhap .
        // So luong .
        // Gia . 
        // ID tai khoan .
        ingredientImportView.getTxtMessage().setText("");

        if (ingredientImportView.getCbIngredientList().getItemCount() == 0) {
            return;
        }

        int ingredientIndex = ingredientImportView.getCbIngredientList().getSelectedIndex();
        ArrayList<Ingredient> ingredientList = ingredientService.getAll();
        Ingredient ingredient = ingredientList.get(ingredientIndex);

        String regex = "^[0-9]{1,}$";

        int ingredientID = ingredient.getId(); // day la id cua nguyen lieu duoc chon

        String amountString = ingredientImportView.getTxtAmount().getText();
        if (amountString.equals("") || !amountString.matches(regex)) {
            JOptionPane.showMessageDialog(null, "Chưa nhập sốlượng hoặc nhập sai định dạng!");
            return;
        }
        int amount = Integer.parseInt(amountString); // day la so luong cua nguyen lieu muon nhap

        if (amount == 0) {
            JOptionPane.showMessageDialog(null, "Chưa nhập sốlượng!");
            return;
        }

        String costString = ingredientImportView.getTxtCost().getText();
        if (costString.equals("") || !costString.matches(regex)) {
            JOptionPane.showMessageDialog(null, "Chưa nhập thành tiền hoặc nhập sai định dạng!");
            return;
        }
        int cost = Integer.parseInt(costString); // day la thanh tien cua nguyen lieu

        if (cost == 0) {
            JOptionPane.showMessageDialog(null, "Chưa nhập thành tiền!");
            return;
        }

        int accountId = App.currentAccount.getId(); // day la ID cua tai khoan nguoi dang nhap

        Date import_Time = new Date(); // Day la thoi gian nhap

        IngredientImport ingredientImport = new IngredientImport();
        ingredientImport.setIngredientId(ingredientID);
        ingredientImport.setImportTime(import_Time);
        ingredientImport.setAmount(amount);
        ingredientImport.setCost(cost);
        ingredientImport.setAccountId(accountId);

        ingredientImportService.insert(ingredientImport);

        JOptionPane.showMessageDialog(null, "Nhập nguyên liệu thành công!");

        ingredientImportView.getCbIngredientList().setSelectedIndex(0);
        ingredientImportView.getTxtAmount().setText("0");
        ingredientImportView.getTxtCost().setText("0");

    }
}
