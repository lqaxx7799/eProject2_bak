/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers.statistics;

import controllers.BaseController;
import java.awt.Dimension;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.table.DefaultTableModel;
import models.ExpenseStatistic;
import models.IngredientImport;
import models.Receipt;
import models.ReceiptDetail;
import models.RevenueStatistic;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.CategoryAxis;
import org.jfree.chart.axis.CategoryLabelPositions;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.xy.DefaultXYDataset;
import org.jfree.data.xy.XYDataset;
import services.IngredientImportService;
import services.ReceiptDetailService;
import services.ReceiptService;
import utils.CommonUltilities;
import views.statistics.ExpenseStatisticView;

/**
 *
 * @author Admin
 */
public class ExpenseStatisticController implements BaseController {

    private ExpenseStatisticView expenseStatisticView;
    private IngredientImportService ingredientImportService;
    private DateFormat dfDate;
    private DateFormat dfTime;

    public ExpenseStatisticController() {
        expenseStatisticView = new ExpenseStatisticView();
        ingredientImportService = new IngredientImportService();
        dfDate = new SimpleDateFormat("dd/MM/yyyy");
        dfTime = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");

        expenseStatisticView.getCbxTimeRange().addActionListener(e -> changeTimeRange());
        expenseStatisticView.getBtnFilter().addActionListener(e -> filterHandler());
    }

    @Override
    public JPanel getPanel() {
        return expenseStatisticView;
    }

    @Override
    public void loadData() {
        expenseStatisticView.getTblExpenseStatistic().getColumnModel().getColumn(0).setMaxWidth(50);

        expenseStatisticView.getCbxTimeRange().removeAllItems();
        expenseStatisticView.getCbxTimeRange().addItem("Today");
        expenseStatisticView.getCbxTimeRange().addItem("Yesterday");
        expenseStatisticView.getCbxTimeRange().addItem("Last 7 days");
        expenseStatisticView.getCbxTimeRange().addItem("This month");
        expenseStatisticView.getCbxTimeRange().addItem("Custom");

        expenseStatisticView.getCbxPeriod().removeAllItems();
        expenseStatisticView.getCbxPeriod().addItem("Hour");
        expenseStatisticView.getCbxPeriod().addItem("Day");
        expenseStatisticView.getCbxPeriod().addItem("Week");
        expenseStatisticView.getCbxPeriod().addItem("Month");

        expenseStatisticView.getLblErrFilter().setText("");
        expenseStatisticView.getPnlInformation().setVisible(false);
        expenseStatisticView.getLblTimeRange().setText("");
        
        expenseStatisticView.getTxtFromDate().setEnabled(false);
        expenseStatisticView.getTxtToDate().setEnabled(false);

        expenseStatisticView.getCbxTimeRange().setSelectedIndex(2);
        expenseStatisticView.getCbxPeriod().setSelectedIndex(1);
    }

    private void changeTimeRange() {
        int timeRangeIndex = expenseStatisticView.getCbxTimeRange().getSelectedIndex();
        if (timeRangeIndex == 4) {
            expenseStatisticView.getTxtFromDate().setEnabled(true);
            expenseStatisticView.getTxtToDate().setEnabled(true);
        } else {
            expenseStatisticView.getTxtFromDate().setEnabled(false);
            expenseStatisticView.getTxtToDate().setEnabled(false);
        }
    }

    private void filterHandler() {
        loadStatistic();
    }

    private void loadStatistic() {
        ArrayList<IngredientImport> ingredientImports = ingredientImportService.getAll();

        expenseStatisticView.getLblErrFilter().setText("");

        int timeRangeIndex = expenseStatisticView.getCbxTimeRange().getSelectedIndex();
        int periodIndex = expenseStatisticView.getCbxPeriod().getSelectedIndex();
        String fromDateString = expenseStatisticView.getTxtFromDate().getText();
        String toDateString = expenseStatisticView.getTxtToDate().getText();

        Date[] timeRange = CommonUltilities.generateTimeRange(timeRangeIndex, fromDateString, toDateString);
        if (timeRange == null) {
            expenseStatisticView.getLblErrFilter().setText("Invalid date");
            return;
        }
        Date fromDate = timeRange[0], toDate = timeRange[1];
        ArrayList<Date> timePeriods = CommonUltilities.generateTimeKey(fromDate, toDate, periodIndex);

        ArrayList<IngredientImport> validImport = new ArrayList<>();
        for (IngredientImport ingredientImport : ingredientImports) {
            if (ingredientImport.getImportTime().after(fromDate) && ingredientImport.getImportTime().before(toDate)) {
                validImport.add(ingredientImport);
            }
        }

        String timeRangeString = String.format("From %s to %s", dfTime.format(fromDate), dfTime.format(toDate));
        double totalExpense = 0;

        ArrayList<ExpenseStatistic> tableData = new ArrayList<>();
        for (int i = 0; i < timePeriods.size(); i++) {
            double expense = 0;
            for (IngredientImport ingredientImport : validImport) {
                if (i == timePeriods.size() - 1) {
                    if (ingredientImport.getImportTime().after(timePeriods.get(i))) {
                        expense+= ingredientImport.getCost();
                    }
                } else {
                    if (ingredientImport.getImportTime().after(timePeriods.get(i)) && ingredientImport.getImportTime().before(timePeriods.get(i + 1))) {
                        expense+= ingredientImport.getCost();
                    }
                }
            }
            String formattedTime = "";
            if (periodIndex == 0) {
                formattedTime = dfTime.format(timePeriods.get(i));
            } else {
                formattedTime = dfDate.format(timePeriods.get(i));
            }
            ExpenseStatistic expenseStatistic = new ExpenseStatistic();

            expenseStatistic.setTime(formattedTime);
            expenseStatistic.setExpense(expense);
            tableData.add(expenseStatistic);

            totalExpense += expense;
        }

        DefaultTableModel revenueStatisticModel = (DefaultTableModel) expenseStatisticView.getTblExpenseStatistic().getModel();
        for (int i = revenueStatisticModel.getRowCount() - 1; i >= 0; i--) {
            revenueStatisticModel.removeRow(i);
        }
        for (int i = 0; i < tableData.size(); i++) {
            Object[] rowData = new Object[]{
                i + 1,
                tableData.get(i).getTime(),
                tableData.get(i).getExpense(),
            };
            revenueStatisticModel.addRow(rowData);
        }

        expenseStatisticView.getPnlInformation().setVisible(true);
        expenseStatisticView.getLblTimeRange().setText(timeRangeString);
        expenseStatisticView.getLblTotalExpense().setText(CommonUltilities.formatCurrency(totalExpense));

        CategoryDataset ds = createDataset(tableData);

        JFreeChart chart = ChartFactory.createBarChart("Expense Statistic", "Time", "Expense", ds, PlotOrientation.VERTICAL, true, true, false);
        CategoryPlot plot = chart.getCategoryPlot();
        CategoryAxis axis = plot.getDomainAxis();
        axis.setCategoryLabelPositions(CategoryLabelPositions.createDownRotationLabelPositions(Math.PI / 4.0));
        ChartPanel cp = new ChartPanel(chart);
        expenseStatisticView.getjSplitPane1().setBottomComponent(cp);
        expenseStatisticView.getjSplitPane1().setDividerLocation(250);
    }

    private CategoryDataset createDataset(ArrayList<ExpenseStatistic> data) {
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        for (ExpenseStatistic es : data) {
            dataset.addValue(es.getExpense(), "Expense", es.getTime());
        }
        return dataset;
    }
}
