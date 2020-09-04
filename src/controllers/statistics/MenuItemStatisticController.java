/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers.statistics;

import controllers.BaseController;
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
import models.MenuCategory;
import models.MenuItem;
import models.MenuItemStatistic;
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
import services.MenuCategoryService;
import services.MenuItemService;
import services.ReceiptDetailService;
import services.ReceiptService;
import utils.CommonUltilities;
import views.statistics.ExpenseStatisticView;
import views.statistics.MenuItemStatisticView;

/**
 *
 * @author Admin
 */
public class MenuItemStatisticController implements BaseController {

    private MenuItemStatisticView menuItemStatisticView;
    private ReceiptDetailService receiptDetailService;
    private ReceiptService receiptService;
    private MenuItemService menuItemService;
    private MenuCategoryService menuCategoryService;
    private DateFormat dfDate;
    private DateFormat dfTime;

    public MenuItemStatisticController() {
        menuItemStatisticView = new MenuItemStatisticView();
        receiptDetailService = new ReceiptDetailService();
        receiptService = new ReceiptService();
        menuItemService = new MenuItemService();
        menuCategoryService = new MenuCategoryService();
        dfDate = new SimpleDateFormat("dd/MM/yyyy");
        dfTime = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");

        menuItemStatisticView.getCbxTimeRange().addActionListener(e -> changeTimeRange());
        menuItemStatisticView.getBtnFilter().addActionListener(e -> filterHandler());
    }

    @Override
    public JPanel getPanel() {
        return menuItemStatisticView;
    }

    @Override
    public void loadData() {
        menuItemStatisticView.getTblMenuItemStatistic().getColumnModel().getColumn(0).setMaxWidth(50);

        menuItemStatisticView.getCbxTimeRange().removeAllItems();
        menuItemStatisticView.getCbxTimeRange().addItem("Today");
        menuItemStatisticView.getCbxTimeRange().addItem("Yesterday");
        menuItemStatisticView.getCbxTimeRange().addItem("Last 7 days");
        menuItemStatisticView.getCbxTimeRange().addItem("This month");
        menuItemStatisticView.getCbxTimeRange().addItem("Custom");

        menuItemStatisticView.getLblErrFilter().setText("");
        menuItemStatisticView.getPnlInformation().setVisible(false);
        menuItemStatisticView.getLblTimeRange().setText("");

        menuItemStatisticView.getTxtFromDate().setEnabled(false);
        menuItemStatisticView.getTxtToDate().setEnabled(false);

        menuItemStatisticView.getCbxTimeRange().setSelectedIndex(2);
    }

    private void changeTimeRange() {
        int timeRangeIndex = menuItemStatisticView.getCbxTimeRange().getSelectedIndex();
        if (timeRangeIndex == 4) {
            menuItemStatisticView.getTxtFromDate().setEnabled(true);
            menuItemStatisticView.getTxtToDate().setEnabled(true);
        } else {
            menuItemStatisticView.getTxtFromDate().setEnabled(false);
            menuItemStatisticView.getTxtToDate().setEnabled(false);
        }
    }

    private void filterHandler() {
        loadStatistic();
    }

    private void loadStatistic() {
        ArrayList<ReceiptDetail> receiptDetails = receiptDetailService.getAll();
        ArrayList<Receipt> receipts = receiptService.getAll();

        menuItemStatisticView.getLblErrFilter().setText("");

        int timeRangeIndex = menuItemStatisticView.getCbxTimeRange().getSelectedIndex();
        String fromDateString = menuItemStatisticView.getTxtFromDate().getText();
        String toDateString = menuItemStatisticView.getTxtToDate().getText();

        Date[] timeRange = CommonUltilities.generateTimeRange(timeRangeIndex, fromDateString, toDateString);
        if (timeRange == null) {
            menuItemStatisticView.getLblErrFilter().setText("Invalid date");
            return;
        }
        Date fromDate = timeRange[0], toDate = timeRange[1];
        double totalRevenue = 0;
        int totalCount = 0;

        ArrayList<MenuItemStatistic> tableData = new ArrayList<>();

        for (Receipt receipt : receipts) {
            if (receipt.getArrivedTime().after(fromDate) && receipt.getArrivedTime().before(toDate)) {
                for (ReceiptDetail receiptDetail : receiptDetails) {
                    if (receiptDetail.getReceiptId() == receipt.getId()) {
                        for (MenuItemStatistic menuItemStatistic : tableData) {
                            if (menuItemStatistic.getMenuItemId() == receiptDetail.getMenuItemId()) {
                                menuItemStatistic.setCount(menuItemStatistic.getCount() + receiptDetail.getQuantity());
                                menuItemStatistic.setRevenue(menuItemStatistic.getRevenue() + receiptDetail.getQuantity() * receiptDetail.getUnitPrice());
                                totalRevenue += receiptDetail.getQuantity() * receiptDetail.getUnitPrice();
                                totalCount += receiptDetail.getQuantity();
                                break;
                            }
                        }
                        MenuItemStatistic mis = new MenuItemStatistic();
                        mis.setCount(receiptDetail.getQuantity());
                        mis.setMenuItemId(receiptDetail.getMenuItemId());
                        mis.setRevenue(receiptDetail.getQuantity() * receiptDetail.getUnitPrice());
                        tableData.add(mis);
                        totalRevenue += receiptDetail.getQuantity() * receiptDetail.getUnitPrice();
                        totalCount += receiptDetail.getQuantity();
                    }
                }
            }
        }

        for (MenuItemStatistic mis : tableData) {
            MenuItem menuItem = menuItemService.getById(mis.getMenuItemId());
            if (menuItem == null) {
                mis.setMenuItemName("");
                mis.setMenuCategoryName("");
                continue;
            }
            mis.setMenuItemName(menuItem.getItemName());
            MenuCategory menuCategory = menuCategoryService.getById(menuItem.getMenuCategoryId());
            if (menuCategory == null) {
                mis.setMenuCategoryName("");
                continue;
            }
            mis.setMenuCategoryName(menuCategory.getCategoryName());
        }

        String timeRangeString = String.format("From %s to %s", dfTime.format(fromDate), dfTime.format(toDate));

        DefaultTableModel revenueStatisticModel = (DefaultTableModel) menuItemStatisticView.getTblMenuItemStatistic().getModel();
        for (int i = revenueStatisticModel.getRowCount() - 1; i >= 0; i--) {
            revenueStatisticModel.removeRow(i);
        }
        for (int i = 0; i < tableData.size(); i++) {
            Object[] rowData = new Object[]{
                i + 1,
                tableData.get(i).getMenuItemName(),
                tableData.get(i).getMenuCategoryName(),
                tableData.get(i).getCount(),
                tableData.get(i).getRevenue()
            };
            revenueStatisticModel.addRow(rowData);
        }

        menuItemStatisticView.getPnlInformation().setVisible(true);
        menuItemStatisticView.getLblTimeRange().setText(timeRangeString);
        menuItemStatisticView.getLblTotalRevenue().setText(CommonUltilities.formatCurrency(totalRevenue));
        menuItemStatisticView.getLblTotalCount().setText(CommonUltilities.formatCurrency(totalCount));

        CategoryDataset ds = createDataset(tableData);

        JFreeChart chart = ChartFactory.createBarChart("Menu Item Statistic", "Item", "Revenue", ds, PlotOrientation.VERTICAL, true, true, false);
        ChartPanel cp = new ChartPanel(chart);
        menuItemStatisticView.getjSplitPane1().setBottomComponent(cp);
        menuItemStatisticView.getjSplitPane1().setDividerLocation(250);
    }

    private CategoryDataset createDataset(ArrayList<MenuItemStatistic> data) {
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        for (MenuItemStatistic mis : data) {
            dataset.addValue(mis.getRevenue(), "Revenue", mis.getMenuItemName());
        }
        return dataset;
    }
}
