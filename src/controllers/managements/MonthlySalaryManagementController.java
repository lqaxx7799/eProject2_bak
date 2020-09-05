/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers.managements;

import app.App;
import controllers.BaseController;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import javax.swing.JPanel;
import javax.swing.table.DefaultTableModel;
import models.SalaryInformation;
import models.Timekeeping;
import models.Account;
import services.AccountService;
import services.SalaryInformationService;
import services.TimekeepingService;
import utils.CommonUltilities;
import views.managements.MonthlySalaryManagementView;

/**
 *
 * @author Admin
 */
public class MonthlySalaryManagementController implements BaseController {

    private MonthlySalaryManagementView monthlySalaryManagementView;
    private SalaryInformationService salaryInformationService;
    private TimekeepingService timekeepingService;
    private AccountService accountService;
    private DateFormat dfDate;
    private DateFormat dfTime;

    public MonthlySalaryManagementController() {
        monthlySalaryManagementView = new MonthlySalaryManagementView();
        salaryInformationService = new SalaryInformationService();
        timekeepingService = new TimekeepingService();
        accountService = new AccountService();
        dfDate = new SimpleDateFormat("dd/MM/yyyy");
        dfTime = new SimpleDateFormat("HH:mm:ss");

        monthlySalaryManagementView.getBtnShow().addActionListener(e -> showSalary());
    }

    @Override
    public JPanel getPanel() {
        return monthlySalaryManagementView;
    }

    @Override
    public void loadData() {
        monthlySalaryManagementView.getTxtErr().setText("");
        monthlySalaryManagementView.getTxtMonth().setText("");
        monthlySalaryManagementView.getTxtYear().setText("");
    }

    private void showSalary() {
        monthlySalaryManagementView.getTxtErr().setText("");

        String monthString = monthlySalaryManagementView.getTxtMonth().getText();
        String yearString = monthlySalaryManagementView.getTxtYear().getText();

        boolean isValid = true;
        if (yearString.equals("")) {
            monthlySalaryManagementView.getTxtErr().setText("Year is required");
            isValid = false;
        } else if (!CommonUltilities.checkIntegerNumberFormat(yearString)) {
            monthlySalaryManagementView.getTxtErr().setText("Year must be a number");
            isValid = false;
        }
        if (monthString.equals("")) {
            monthlySalaryManagementView.getTxtErr().setText("Month is required");
            isValid = false;
        } else if (!CommonUltilities.checkIntegerNumberFormat(monthString)) {
            monthlySalaryManagementView.getTxtErr().setText("Month must be a number");
            isValid = false;
        }

        if (!isValid) {
            return;
        }

        int month = Integer.parseInt(monthString);
        int year = Integer.parseInt(yearString);

        Calendar firstDayOfMonth = Calendar.getInstance();
        Calendar lastDayOfMonth = Calendar.getInstance();
        firstDayOfMonth.set(Calendar.DAY_OF_MONTH, 1);
        firstDayOfMonth.set(Calendar.MONTH, month - 1);
        firstDayOfMonth.set(Calendar.YEAR, year);
        firstDayOfMonth.set(Calendar.HOUR, 0);
        firstDayOfMonth.set(Calendar.MINUTE, 0);
        firstDayOfMonth.set(Calendar.SECOND, 0);

        lastDayOfMonth.set(Calendar.DAY_OF_MONTH, CommonUltilities.getLastDayOfMonth(month, year));
        lastDayOfMonth.set(Calendar.MONTH, month - 1);
        lastDayOfMonth.set(Calendar.YEAR, year);
        lastDayOfMonth.set(Calendar.HOUR, 23);
        lastDayOfMonth.set(Calendar.MINUTE, 59);
        lastDayOfMonth.set(Calendar.SECOND, 59);

        ArrayList<Date> timeKeys = CommonUltilities.generateTimeKey(firstDayOfMonth.getTime(), lastDayOfMonth.getTime(), 1);

        ArrayList<Account> accounts = accountService.getWorkingAccounts();

        DefaultTableModel monthSalaryModel = (DefaultTableModel) monthlySalaryManagementView.getTblSalary().getModel();
        for (int i = monthSalaryModel.getRowCount() - 1; i >= 0; i--) {
            monthSalaryModel.removeRow(i);
        }

        for (Account account : accounts) {
            if (account.getRoleId() == 4) {
                continue;
            }
            ArrayList<Timekeeping> timekeepings = timekeepingService.getByAccountId(account.getId());
            double countActualTimekeeping = 0;
            long totalMinuteFee = 0;
            for (Date timeKey : timeKeys) {
                for (Timekeeping timekeeping : timekeepings) {
                    Calendar inTime = Calendar.getInstance();
                    Calendar time = Calendar.getInstance();
                    inTime.setTime(timekeeping.getInTime());
                    time.setTime(timeKey);
                    if (inTime.get(Calendar.DATE) == time.get(Calendar.DATE) && inTime.get(Calendar.MONTH) == time.get(Calendar.MONTH) && inTime.get(Calendar.YEAR) == time.get(Calendar.YEAR)) {
                        Calendar expectedInTime = Calendar.getInstance();
                        expectedInTime.setTime(timeKey);
                        expectedInTime.set(Calendar.HOUR, 8);
                        expectedInTime.set(Calendar.MINUTE, 30);
                        expectedInTime.set(Calendar.SECOND, 0);
                        expectedInTime.set(Calendar.AM_PM, Calendar.AM);

                        Calendar expectedOutTime = Calendar.getInstance();
                        expectedOutTime.setTime(timeKey);
                        expectedOutTime.set(Calendar.HOUR, 6);
                        expectedOutTime.set(Calendar.MINUTE, 0);
                        expectedOutTime.set(Calendar.SECOND, 0);
                        expectedOutTime.set(Calendar.AM_PM, Calendar.PM);

                        long inDiff = inTime.getTime().getTime() - expectedInTime.getTime().getTime();
                        long minuteInLate = inDiff < 0 ? 0 : inDiff / (60 * 1000);
                        long minuteOutEarly = 0;
                        if (timekeeping.getOutTime() != null) {
                            Calendar outTime = Calendar.getInstance();
                            outTime.setTime(timekeeping.getOutTime());
                            long outDiff = expectedOutTime.getTime().getTime() - outTime.getTime().getTime();
                            minuteOutEarly = outDiff < 0 ? 0 : outDiff / (60 * 1000);
                            countActualTimekeeping += 1;
                        } else {
                            countActualTimekeeping += 0.5;
                        }
                        totalMinuteFee += minuteInLate + minuteOutEarly;
                    }

                }
            }
            ArrayList<SalaryInformation> salaryInformations = salaryInformationService.getByAccountId(account.getId());
            SalaryInformation salaryInformation = null;
            for (SalaryInformation si : salaryInformations) {
                Calendar salaryFromDate = Calendar.getInstance();
                salaryFromDate.setTime(si.getFromDate());

                boolean conditionFrom1 = year == salaryFromDate.get(Calendar.YEAR) && month > salaryFromDate.get(Calendar.MONTH);
                boolean conditionFrom2 = year > salaryFromDate.get(Calendar.YEAR);
                boolean conditionFrom = conditionFrom1 || conditionFrom2;

                boolean conditionTo;
                if (si.getToDate() == null) {
                    conditionTo = true;
                } else {
                    Calendar salaryToDate = Calendar.getInstance();
                    salaryToDate.setTime(si.getToDate());
                    boolean conditionTo1 = year == salaryFromDate.get(Calendar.YEAR) && month < salaryFromDate.get(Calendar.MONTH) - 1;
                    boolean conditionTo2 = year < salaryFromDate.get(Calendar.YEAR);
                    conditionTo = conditionTo1 || conditionTo2;
                }
                if (conditionFrom && conditionTo) {
                    salaryInformation = si;
                    break;
                }
            }

            if (salaryInformation != null) {
                double actualSalary = salaryInformation.getSalary() * countActualTimekeeping / timeKeys.size();
                actualSalary -= totalMinuteFee < 30 ? 0 : (totalMinuteFee - 30) * 1000;
                actualSalary = actualSalary < 0 ? 0 : actualSalary;
                Object[] rowData = new Object[]{
                    account.getId(),
                    account.getEmail(),
                    account.getUserName(),
                    countActualTimekeeping,
                    CommonUltilities.formatCurrency(salaryInformation.getSalary()),
                    CommonUltilities.formatCurrency(actualSalary)
                };
                monthSalaryModel.addRow(rowData);
            }
        }
    }
}
