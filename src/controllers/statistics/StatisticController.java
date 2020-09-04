/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers.statistics;

import controllers.BaseController;
import controllers.statistics.ExpenseStatisticController;
import controllers.statistics.MenuItemStatisticController;
import controllers.statistics.RevenueStatisticController;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.event.ChangeEvent;
import views.statistics.StatisticView;

/**
 *
 * @author Admin
 */
public class StatisticController implements BaseController {

    private StatisticView statisticView;

    private RevenueStatisticController revenueStatisticController;
    private ExpenseStatisticController expenseStatisticController;
    private MenuItemStatisticController menuItemStatisticController;

    public StatisticController() {
        statisticView = new StatisticView();
        revenueStatisticController = new RevenueStatisticController();
        expenseStatisticController = new ExpenseStatisticController();
        menuItemStatisticController = new MenuItemStatisticController();

        statisticView.getTabStatistic().add("Revenue", revenueStatisticController.getPanel());
        statisticView.getTabStatistic().add("Expense", expenseStatisticController.getPanel());
        statisticView.getTabStatistic().add("Menu Item", menuItemStatisticController.getPanel());

        statisticView.getTabStatistic().addChangeListener(e -> changeTab(e));
    }

    @Override
    public JPanel getPanel() {
        return statisticView;
    }

    @Override
    public void loadData() {
        revenueStatisticController.loadData();
    }

    private void changeTab(ChangeEvent e) {
        JTabbedPane sourceTabbedPane = (JTabbedPane) e.getSource();
        int index = sourceTabbedPane.getSelectedIndex();
        switch (index) {
            case 0:
                revenueStatisticController.loadData();
                break;
            case 1:
                expenseStatisticController.loadData();
                break;
            case 2:
                menuItemStatisticController.loadData();
                break;
            default:
                break;
        }
    }
}
