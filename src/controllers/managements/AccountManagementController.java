/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers.managements;

import controllers.BaseController;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.AbstractMap.SimpleEntry;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import models.Account;
import models.Role;
import services.AccountService;
import services.RoleService;
import utils.CommonUltilities;
import views.managements.AccountManagementView;

/**
 *
 * @author Admin
 */
public class AccountManagementController implements BaseController {

    private AccountManagementView accountManagementView;
    private AccountService accountService;
    private RoleService roleService;

    private DateFormat df;

    private static String actionType = "";
    public static int activeAccountId = 0;

    public AccountManagementController() {
        accountManagementView = new AccountManagementView();
        accountService = new AccountService();
        roleService = new RoleService();
        df = new SimpleDateFormat("dd/MM/yyyy");

        accountManagementView.getBtnAddNew().addActionListener(e -> addNewHandler());
        accountManagementView.getBtnEdit().addActionListener(e -> editHandler());
        accountManagementView.getBtnCancel().addActionListener(e -> cancelHandler());
        accountManagementView.getBtnSubmit().addActionListener(e -> submitHandler());
        accountManagementView.getBtnViewSalary().addActionListener(e -> viewSalaryHandler());

    }

    public AccountManagementView initController() {
        return accountManagementView;
    }

    private void loadTable() {
        DateFormat df = new SimpleDateFormat("dd/MM/yyyy");

        ArrayList<Account> accounts = accountService.getAll();
        ArrayList<Role> roles = roleService.getAll();

        DefaultTableModel accountModel = (DefaultTableModel) accountManagementView.getTblAccount().getModel();

        for (int i = accountModel.getRowCount() - 1; i >= 0; i--) {
            accountModel.removeRow(i);
        }

        for (Account account : accounts) {
            String roleName = "";
            for (Role role : roles) {
                if (role.getId() == account.getRoleId()) {
                    roleName = role.getRoleName();
                    break;
                }
            }
            String dateOfBirth = account.getDateOfBirth() == null ? "" : df.format(account.getDateOfBirth());
            String startWorkDate = account.getStartWorkDate() == null ? "" : df.format(account.getStartWorkDate());

            Object[] rowData = new Object[]{
                account.getId(),
                account.getEmail(),
                account.getUserName(),
                account.getAddress(),
                account.getGender(),
                dateOfBirth,
                roleName,
                startWorkDate,
                account.isWorking()
            };

            accountModel.addRow(rowData);
        }
    }
    
    @Override
    public JPanel getPanel() {
        return accountManagementView;
    }

    @Override
    public void loadData() {
        accountManagementView.getCbxGender().removeAllItems();
        accountManagementView.getCbxGender().addItem("male");
        accountManagementView.getCbxGender().addItem("female");
        accountManagementView.getCbxGender().addItem("other");

        accountManagementView.getCbxRole().removeAllItems();
        for (Role role : roleService.getAll()) {
            accountManagementView.getCbxRole().addItem(role.getRoleName());
        }
        
        loadTable();
        
        setButtonsState(true);
        setFormState(false);
        resetError();
    }

    private void addNewHandler() {
        setButtonsState(false);
        setFormState(true);
        actionType = "add";
    }

    private void editHandler() {
        int selectedRow = accountManagementView.getTblAccount().getSelectedRow();

        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(null, "Select a row first");
            return;
        }

        int accountId = (int) accountManagementView.getTblAccount().getValueAt(selectedRow, 0);
        Account account = accountService.getById(accountId);
        accountManagementView.getTxtEmail().setText(account.getEmail());
        accountManagementView.getTxtDateOfBirth().setText(account.getDateOfBirth() == null ? "" : df.format(account.getDateOfBirth()));
        accountManagementView.getTxtAddress().setText(account.getAddress());
        accountManagementView.getTxtUserName().setText(account.getUserName());
        accountManagementView.getCbxGender().setSelectedItem(account.getGender() == null ? "male" : account.getGender());

        Role role = roleService.getById(account.getRoleId());
        accountManagementView.getCbxRole().setSelectedItem(role.getRoleName());

        accountManagementView.getChbIsWorking().setSelected(account.isWorking());

        setButtonsState(false);
        setFormState(true);
        actionType = "edit";
        activeAccountId = accountId;
    }

    private void cancelHandler() {
        resetError();
        resetForm();
        setButtonsState(true);
        setFormState(false);
        actionType = "";
    }

    private void submitHandler() {
        resetError();

        String email = accountManagementView.getTxtEmail().getText();
        String userName = accountManagementView.getTxtUserName().getText();
        String address = accountManagementView.getTxtAddress().getText();
        String dateOfBirthString = accountManagementView.getTxtDateOfBirth().getText();
        String gender = accountManagementView.getCbxGender().getSelectedItem().toString();
        String role = (String) accountManagementView.getCbxRole().getSelectedItem();
        boolean isWorking = accountManagementView.getChbIsWorking().isSelected();

        boolean isValid = true;

        if (email.equals("")) {
            isValid = false;
            accountManagementView.getLblErrEmail().setText("Email is required");
        } else if (!CommonUltilities.checkEmailFormat(email)) {
            isValid = false;
            accountManagementView.getLblErrEmail().setText("Email is not in correct format");
        } else if (actionType.equals("add")) {
            ArrayList<Account> accounts = accountService.getAll();
            for (Account a : accounts) {
                if (a.getEmail().equals(email)) {
                    isValid = false;
                    accountManagementView.getLblErrEmail().setText("Email is already used");
                    break;
                }
            }
        }

        if (userName.equals("")) {
            isValid = false;
            accountManagementView.getLblErrUserName().setText("User name is required");
        }

        Date dateOfBirth = null;
        if (!dateOfBirthString.equals("")) {
            if (!CommonUltilities.checkDateFormat(dateOfBirthString)) {
                isValid = false;
                accountManagementView.getLblErrDateOfBirth().setText("Date of birth is not in correct format");
            } else {
                try {
                    dateOfBirth = df.parse(dateOfBirthString);
                    if (dateOfBirth.after(new Date())) {
                        isValid = false;
                        accountManagementView.getLblErrDateOfBirth().setText("Date of birth must be in the past");
                    }
                } catch (ParseException ex) {
                    Logger.getLogger(AccountManagementController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }

        int roleId = 0;
        for (Role r : roleService.getAll()) {
            if (r.getRoleName().equals(role)) {
                roleId = r.getId();
                break;
            }
        }
        if (roleId == 0) {
            isValid = false;
            accountManagementView.getLblErrRole().setText("Role does not exist");
        }

        if (isValid) {
            if (actionType.equals("add")) {
                Account account = new Account();
                account.setEmail(email);
                account.setAddress(address);
                account.setUserName(userName);
                account.setDateOfBirth(dateOfBirth);
                account.setGender(gender);
                account.setStartWorkDate(new Date());
                account.setWorking(isWorking);
                account.setPassword(CommonUltilities.generateSHA1("123456"));
                account.setRoleId(roleId);

                accountService.insert(account);

                loadTable();
                resetForm();
                setFormState(false);
                setButtonsState(true);
            } else if (actionType.equals("edit")) {
                Account account = accountService.getById(activeAccountId);
                account.setEmail(email);
                account.setAddress(address);
                account.setUserName(userName);
                account.setDateOfBirth(dateOfBirth);
                account.setGender(gender);
                account.setWorking(isWorking);
                account.setRoleId(roleId);

                accountService.update(account);

                loadTable();
                resetForm();
                setFormState(false);
                setButtonsState(true);
                activeAccountId = 0;
            }
        }
    }

    private void viewSalaryHandler(){
        int selectedRow = accountManagementView.getTblAccount().getSelectedRow();

        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(null, "Select a row first");
            return;
        }
        
        int accountId = (int) accountManagementView.getTblAccount().getValueAt(selectedRow, 0);
        activeAccountId = accountId;
        SalaryManagementController salaryManagementController = new SalaryManagementController();
        JFrame frame = salaryManagementController.initController();
        frame.setVisible(true);
    }
    
    private void resetError() {
        accountManagementView.getLblErrEmail().setText("");
        accountManagementView.getLblErrDateOfBirth().setText("");
        accountManagementView.getLblErrAddress().setText("");
        accountManagementView.getLblErrUserName().setText("");
        accountManagementView.getLblErrGender().setText("");
        accountManagementView.getLblErrRole().setText("");
        accountManagementView.getLblErrIsWorking().setText("");
    }

    private void resetForm() {
        accountManagementView.getTxtEmail().setText("");
        accountManagementView.getTxtDateOfBirth().setText("");
        accountManagementView.getTxtAddress().setText("");
        accountManagementView.getTxtUserName().setText("");
        accountManagementView.getCbxGender().setSelectedIndex(0);
        accountManagementView.getCbxRole().setSelectedIndex(0);
        accountManagementView.getChbIsWorking().setSelected(false);
    }

    private void setFormState(boolean state) {
        accountManagementView.getTxtEmail().setEnabled(state);
        accountManagementView.getTxtDateOfBirth().setEnabled(state);
        accountManagementView.getTxtAddress().setEnabled(state);
        accountManagementView.getTxtUserName().setEnabled(state);
        accountManagementView.getCbxGender().setEnabled(state);
        accountManagementView.getCbxRole().setEnabled(state);
        accountManagementView.getChbIsWorking().setEnabled(state);
    }

    private void setButtonsState(boolean state) {
        accountManagementView.getBtnAddNew().setEnabled(state);
        accountManagementView.getBtnEdit().setEnabled(state);
        accountManagementView.getBtnCancel().setEnabled(!state);
        accountManagementView.getBtnSubmit().setEnabled(!state);
        accountManagementView.getBtnViewSalary().setEnabled(state);
    }
}
