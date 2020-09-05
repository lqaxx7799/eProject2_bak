/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers.managements;

import app.App;
import java.awt.Color;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import javax.swing.JFrame;
import static javax.swing.WindowConstants.EXIT_ON_CLOSE;
import javax.swing.table.DefaultTableModel;
import models.SalaryInformation;
import services.SalaryInformationService;
import utils.CommonUltilities;
import views.managements.SalaryManagementView;

/**
 *
 * @author Admin
 */
public class SalaryManagementController {

    private SalaryManagementView salaryManagementView;
    private JFrame salaryMangementFrame;
    private SalaryInformationService salaryInformationService;

    public SalaryManagementController() {
        salaryInformationService = new SalaryInformationService();
        salaryManagementView = new SalaryManagementView();
        salaryMangementFrame = new JFrame();
        salaryMangementFrame.setSize(600, 400);
        salaryMangementFrame.add(salaryManagementView);

        salaryManagementView.getLblErrSalary().setText("");

        loadData();

        salaryManagementView.getBtnUpdate().addActionListener(e -> editSalaryHandler());
    }

    public JFrame initController() {
        return salaryMangementFrame;
    }

    private void loadData() {
        DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
        ArrayList<SalaryInformation> salaryInformations = salaryInformationService.getByAccountId(AccountManagementController.activeAccountId);

        DefaultTableModel salaryModel = (DefaultTableModel) salaryManagementView.getTblSalary().getModel();

        for (int i = salaryModel.getRowCount() - 1; i >= 0; i--) {
            salaryModel.removeRow(i);
        }

        for (SalaryInformation salaryInformation : salaryInformations) {
            String fromDate = salaryInformation.getFromDate() == null ? "" : df.format(salaryInformation.getFromDate());
            String toDate = salaryInformation.getToDate() == null ? "" : df.format(salaryInformation.getToDate());

            Object[] rowData = new Object[]{
                CommonUltilities.formatCurrency(salaryInformation.getSalary()),
                fromDate,
                toDate
            };

            salaryModel.addRow(rowData);

            if (salaryInformation.getToDate() == null) {
                salaryManagementView.getTxtSalary().setText(String.format("%.0f", salaryInformation.getSalary()));
            }
        }

        if (salaryInformations.isEmpty()) {
            salaryManagementView.getTxtSalary().setText("0");
        }
    }

    private void editSalaryHandler() {
        salaryManagementView.getLblErrSalary().setText("");
        salaryManagementView.getLblErrSalary().setForeground(new Color(255, 0, 0));

        String salaryString = salaryManagementView.getTxtSalary().getText();
        boolean isValid = true;

        if (!CommonUltilities.checkDoubleNumberFormat(salaryString)) {
            salaryManagementView.getLblErrSalary().setText("Must be a number");
            isValid = false;
        }

        if (isValid) {
            double salary = Double.parseDouble(salaryString);
            ArrayList<SalaryInformation> salaryInformations = salaryInformationService.getByAccountId(AccountManagementController.activeAccountId);

            for (SalaryInformation s : salaryInformations) {
                if (s.getToDate() == null) {
                    s.setToDate(new Date());
                    salaryInformationService.update(s);
                }
            }

            SalaryInformation salaryInformation = new SalaryInformation();
            salaryInformation.setAccountId(AccountManagementController.activeAccountId);
            salaryInformation.setFromDate(new Date());
            salaryInformation.setSalary(salary);
            salaryInformationService.insert(salaryInformation);

            salaryManagementView.getLblErrSalary().setText("Salary updated successfully");
            salaryManagementView.getLblErrSalary().setForeground(new Color(0, 153, 0));

            loadData();
        }
    }
}
