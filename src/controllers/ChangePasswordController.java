/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import app.App;
import javax.swing.JPanel;
import models.Account;
import services.AccountService;
import utils.CommonUltilities;
import views.ChangePasswordView;

/**
 *
 * @author Admin
 */
public class ChangePasswordController implements BaseController{
    
    private ChangePasswordView changePasswordView;
    private AccountService accountService;
    
    public ChangePasswordController() {
        changePasswordView = new ChangePasswordView();
        accountService = new AccountService();
        
        changePasswordView.getBtnSubmit().addActionListener(e -> changePasswordHandler());
    }

    @Override
    public JPanel getPanel() {
        return changePasswordView;
    }

    @Override
    public void loadData() {
        changePasswordView.getLblSuccess().setText("");
        changePasswordView.getLblErrOldPassword().setText("");
        changePasswordView.getLblErrNewPassword().setText("");
        changePasswordView.getLblErrReenterNewPassword().setText("");
        changePasswordView.getTxtOldPassword().setText("");
        changePasswordView.getTxtNewPassword().setText("");
        changePasswordView.getTxtReenterNewPassword().setText("");
    }
    
    private void changePasswordHandler() {
        String oldPassword = changePasswordView.getTxtOldPassword().getText();
        String newPassword = changePasswordView.getTxtNewPassword().getText();
        String reenterNewPassword = changePasswordView.getTxtReenterNewPassword().getText();
        
        changePasswordView.getLblErrOldPassword().setText("");
        changePasswordView.getLblErrNewPassword().setText("");
        changePasswordView.getLblErrReenterNewPassword().setText("");
        
        boolean isValid = true;
        
        if (oldPassword.equals("")) {
            isValid = false;
            changePasswordView.getLblErrOldPassword().setText("Cần nhập mật khẩu cũ");
        } else if (!CommonUltilities.generateSHA1(oldPassword).equals(App.currentAccount.getPassword())) {
            isValid = false;
            changePasswordView.getLblErrOldPassword().setText("Mật khẩu cũ không đúng");
        }
        
        if (newPassword.equals("")) {
            isValid = false;
            changePasswordView.getLblErrNewPassword().setText("Cần nhập mật khẩu mới");
        }
        
        if (reenterNewPassword.equals("")) {
            isValid = false;
            changePasswordView.getLblErrReenterNewPassword().setText("Cần nhập lại mật khẩu mới");
        } else if (!reenterNewPassword.equals(newPassword)) {
            isValid = false;
            changePasswordView.getLblErrReenterNewPassword().setText("Nhập lại mật khẩu mới không trùng");
        }
        
        if (isValid) {
            Account account = App.currentAccount;
            account.setPassword(CommonUltilities.generateSHA1(newPassword));
//            accountService.updatePassword(account.getId(), account.getPassword());
            accountService.update(account);
            App.currentAccount = account;
            changePasswordView.getLblSuccess().setText("Thay đổi mật khẩu thành công");
            changePasswordView.getTxtOldPassword().setText("");
            changePasswordView.getTxtNewPassword().setText("");
            changePasswordView.getTxtReenterNewPassword().setText("");
            
        }
    }
    
}
