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
import services.ReceiptDetailService;
import services.ReceiptService;
import utils.CommonUltilities;
import views.statistics.RevenueStatisticView;

/**
 *
 * @author Admin
 */
public class RevenueStatisticController implements BaseController {

    private RevenueStatisticView revenueStatisticView;
    private ReceiptService receiptService;
    private ReceiptDetailService receiptDetailService;
    private DateFormat dfDate;
    private DateFormat dfTime;

    public RevenueStatisticController() {
        revenueStatisticView = new RevenueStatisticView();
        receiptService = new ReceiptService();
        receiptDetailService = new ReceiptDetailService();
        dfDate = new SimpleDateFormat("dd/MM/yyyy");
        dfTime = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");

        revenueStatisticView.getCbxTimeRange().addActionListener(e -> changeTimeRange());
        revenueStatisticView.getBtnFilter().addActionListener(e -> filterHandler());
    }

    @Override
    public JPanel getPanel() {
        return revenueStatisticView;
    }

    @Override
    public void loadData() {
        revenueStatisticView.getTblRevenueStatistic().getColumnModel().getColumn(0).setMaxWidth(50);

        revenueStatisticView.getCbxTimeRange().removeAllItems();
        revenueStatisticView.getCbxTimeRange().addItem("Today");
        revenueStatisticView.getCbxTimeRange().addItem("Yesterdat");
        revenueStatisticView.getCbxTimeRange().addItem("Last 7 days");
        revenueStatisticView.getCbxTimeRange().addItem("This month");
        revenueStatisticView.getCbxTimeRange().addItem("Custom");

        revenueStatisticView.getCbxPeriod().removeAllItems();
        revenueStatisticView.getCbxPeriod().addItem("Hour");
        revenueStatisticView.getCbxPeriod().addItem("Day");
        revenueStatisticView.getCbxPeriod().addItem("Week");
        revenueStatisticView.getCbxPeriod().addItem("Month");

        revenueStatisticView.getLblErrFilter().setText("");
        revenueStatisticView.getPnlInformation().setVisible(false);
        revenueStatisticView.getLblTimeRange().setText("");
        revenueStatisticView.getLblTotalRevenue().setText("");
        revenueStatisticView.getLblTotalReceipt().setText("");

        revenueStatisticView.getTxtFromDate().setEnabled(false);
        revenueStatisticView.getTxtToDate().setEnabled(false);

        revenueStatisticView.getCbxTimeRange().setSelectedIndex(2);
        revenueStatisticView.getCbxPeriod().setSelectedIndex(1);
    }

    private void changeTimeRange() {
        int timeRangeIndex = revenueStatisticView.getCbxTimeRange().getSelectedIndex();
        if (timeRangeIndex == 4) {
            revenueStatisticView.getTxtFromDate().setEnabled(true);
            revenueStatisticView.getTxtToDate().setEnabled(true);
        } else {
            revenueStatisticView.getTxtFromDate().setEnabled(false);
            revenueStatisticView.getTxtToDate().setEnabled(false);
        }
    }

    private void filterHandler() {
        loadStatistic();
    }

    private void loadStatistic() {
        ArrayList<Receipt> receipts = receiptService.getAll();
        ArrayList<ReceiptDetail> receiptDetails = receiptDetailService.getAll();

        revenueStatisticView.getLblErrFilter().setText("");

        int timeRangeIndex = revenueStatisticView.getCbxTimeRange().getSelectedIndex();
        int periodIndex = revenueStatisticView.getCbxPeriod().getSelectedIndex();
        String fromDateString = revenueStatisticView.getTxtFromDate().getText();
        String toDateString = revenueStatisticView.getTxtToDate().getText();

        Date[] timeRange = CommonUltilities.generateTimeRange(timeRangeIndex, fromDateString, toDateString);
        if (timeRange == null) {
            revenueStatisticView.getLblErrFilter().setText("Invalid date");
            return;
        }
        Date fromDate = timeRange[0], toDate = timeRange[1];
        ArrayList<Date> timePeriods = CommonUltilities.generateTimeKey(fromDate, toDate, periodIndex);

        ArrayList<Receipt> validReceipt = new ArrayList<>();
        for (Receipt receipt : receipts) {
            if (receipt.getArrivedTime().after(fromDate) && receipt.getArrivedTime().before(toDate)) {
                validReceipt.add(receipt);
            }
        }

        String timeRangeString = String.format("From %s to %s", dfTime.format(fromDate), dfTime.format(toDate));
        double totalRevenue = 0;
        int totalReceipt = 0;

        ArrayList<RevenueStatistic> tableData = new ArrayList<>();
        for (int i = 0; i < timePeriods.size(); i++) {
            double revenue = 0;
            int receiptCount = 0;
            for (Receipt receipt : validReceipt) {
                if (i == timePeriods.size() - 1) {
                    if (receipt.getArrivedTime().after(timePeriods.get(i))) {
                        for (ReceiptDetail rd : receiptDetails) {
                            if (rd.getReceiptId() == receipt.getId()) {
                                revenue += rd.getUnitPrice() * rd.getQuantity();
                            }
                        }
                        receiptCount++;
                    }
                } else {
                    if (receipt.getArrivedTime().after(timePeriods.get(i)) && receipt.getArrivedTime().before(timePeriods.get(i + 1))) {
                        for (ReceiptDetail rd : receiptDetails) {
                            if (rd.getReceiptId() == receipt.getId()) {
                                revenue += rd.getUnitPrice() * rd.getQuantity();
                            }
                        }
                        receiptCount++;
                    }
                }
            }
            String formattedTime = "";
            if (periodIndex == 0) {
                formattedTime = dfTime.format(timePeriods.get(i));
            } else {
                formattedTime = dfDate.format(timePeriods.get(i));
            }
            RevenueStatistic revenueStatistic = new RevenueStatistic();

            revenueStatistic.setTime(formattedTime);
            revenueStatistic.setRevenue(revenue);
            revenueStatistic.setReceiptCount(receiptCount);
            tableData.add(revenueStatistic);

            totalRevenue += revenue;
            totalReceipt += receiptCount;
        }

        DefaultTableModel revenueStatisticModel = (DefaultTableModel) revenueStatisticView.getTblRevenueStatistic().getModel();
        for (int i = revenueStatisticModel.getRowCount() - 1; i >= 0; i--) {
            revenueStatisticModel.removeRow(i);
        }
        for (int i = 0; i < tableData.size(); i++) {
            Object[] rowData = new Object[]{
                i + 1,
                tableData.get(i).getTime(),
                tableData.get(i).getRevenue(),
                tableData.get(i).getReceiptCount()
            };
            revenueStatisticModel.addRow(rowData);
        }

        revenueStatisticView.getPnlInformation().setVisible(true);
        revenueStatisticView.getLblTimeRange().setText(timeRangeString);
        revenueStatisticView.getLblTotalRevenue().setText(CommonUltilities.formatCurrency(totalRevenue));
        revenueStatisticView.getLblTotalReceipt().setText(String.valueOf(totalReceipt));

        CategoryDataset ds = createDataset(tableData);

        JFreeChart chart = ChartFactory.createBarChart("Revenue Statistic", "Time", "Revenue", ds, PlotOrientation.VERTICAL, true, true, false);
        CategoryPlot plot = chart.getCategoryPlot();
        CategoryAxis axis = plot.getDomainAxis();
        axis.setCategoryLabelPositions(CategoryLabelPositions.createDownRotationLabelPositions(Math.PI / 4.0));
        ChartPanel cp = new ChartPanel(chart);
        revenueStatisticView.getjSplitPane1().setBottomComponent(cp);
        revenueStatisticView.getjSplitPane1().setDividerLocation(250);
    }

    private CategoryDataset createDataset(ArrayList<RevenueStatistic> data) {
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        for (RevenueStatistic rs : data) {
            dataset.addValue(rs.getRevenue(), "Revenue", rs.getTime());
        }
        return dataset;
    }
}
