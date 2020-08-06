/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import controllers.managements.AccountManagementController;
import app.App;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JPanel;
import models.Account;
import services.AccountService;
import utils.CommonUltilities;
import views.AccountSettingView;

/**
 *
 * @author Admin
 */
public class AccountSettingController implements BaseController {

    private AccountSettingView accountSettingView;
    private AccountService accountService;
    private DateFormat df;

    public AccountSettingController() {
        accountSettingView = new AccountSettingView();
        accountService = new AccountService();
        df = new SimpleDateFormat("dd/MM/yyyy");

        accountSettingView.getBtnUpdate().addActionListener(e -> updateHandler());
    }

    @Override
    public JPanel getPanel() {
        return accountSettingView;
    }

    @Override
    public void loadData() {
        accountSettingView.getCbxGender().removeAllItems();
        accountSettingView.getCbxGender().addItem("male");
        accountSettingView.getCbxGender().addItem("female");
        accountSettingView.getCbxGender().addItem("other");

        accountSettingView.getLblErrAddress().setText("");
        accountSettingView.getLblErrDateOfBirth().setText("");
        accountSettingView.getLblErrGender().setText("");
        accountSettingView.getLblErrUserName().setText("");
        accountSettingView.getLblSuccess().setText("");

        Account account = accountService.getById(App.currentAccount.getId());
        accountSettingView.getLblEmail().setText(account.getEmail());
        accountSettingView.getTxtUserName().setText(account.getUserName());
        accountSettingView.getLblStartWorkingDate().setText(df.format(account.getStartWorkDate()));
        accountSettingView.getTxtAddress().setText(account.getAddress());
        accountSettingView.getTxtDateOfBirth().setText(account.getDateOfBirth() == null ? "" : df.format(account.getDateOfBirth()));
        accountSettingView.getCbxGender().setSelectedItem(account.getGender() == null ? "male" : account.getGender());

    }

    private void updateHandler() {
        accountSettingView.getLblErrAddress().setText("");
        accountSettingView.getLblErrDateOfBirth().setText("");
        accountSettingView.getLblErrGender().setText("");
        accountSettingView.getLblErrUserName().setText("");
        accountSettingView.getLblSuccess().setText("");

        String userName = accountSettingView.getTxtUserName().getText();
        String address = accountSettingView.getTxtAddress().getText();
        String dateOfBirthString = accountSettingView.getTxtDateOfBirth().getText();
        String gender = accountSettingView.getCbxGender().getSelectedItem().toString();

        boolean isValid = true;

        if (userName.equals("")) {
            isValid = false;
            accountSettingView.getLblErrUserName().setText("Nhập tên người dùng");
        }

        Date dateOfBirth = null;
        if (!dateOfBirthString.equals("")) {
            if (!CommonUltilities.checkDateFormat(dateOfBirthString)) {
                isValid = false;
                accountSettingView.getLblErrDateOfBirth().setText("Nhập ngày sinh đúng định dạng");
            } else {
                try {
                    dateOfBirth = df.parse(dateOfBirthString);
                    if (dateOfBirth.after(new Date())) {
                        isValid = false;
                        accountSettingView.getLblErrDateOfBirth().setText("Ngày sinh phải trước hiện tại");
                    }
                } catch (ParseException ex) {
                    Logger.getLogger(AccountManagementController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }

        if (isValid) {
            Account account = accountService.getById(App.currentAccount.getId());
            account.setAddress(address);
            account.setUserName(userName);
            account.setDateOfBirth(dateOfBirth);
            account.setGender(gender);

            accountService.update(account);
            loadData();
            accountSettingView.getLblSuccess().setText("Cập nhật thành công");
        }
    }

}
