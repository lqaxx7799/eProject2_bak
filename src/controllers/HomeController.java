 /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import controllers.managements.ManagementController;
import controllers.statistics.StatisticController;
import app.App;
import java.awt.Frame;
import javax.swing.JFrame;
import javax.swing.JTabbedPane;
import static javax.swing.WindowConstants.EXIT_ON_CLOSE;
import javax.swing.event.ChangeEvent;
import views.HomeView;

/**
 *
 * @author Admin
 */
public class HomeController {

    private HomeView homeView;
    private JFrame homeFrame;

    private ManagementController managementController;
    private TimekeepingController timekeepingController;
    private ChangePasswordController changePasswordController;
    private LogOutController logOutController;
    private StatisticController statisticController;
    private IngredientImportController ingredientImportController;
    private AwaitedDishListChefController awaitedDishListChefController;
    private AwaitedDishListWaiterController awaitedDishListWaiterController;
    private OrderController orderController;
    private SalaryReportController salaryReportController;
    private AccountSettingController accountSettingController;


    public HomeController() {
        this.homeView = new HomeView();

        managementController = new ManagementController();
        timekeepingController = new TimekeepingController();
        changePasswordController = new ChangePasswordController();
        logOutController = new LogOutController(this);
        statisticController = new StatisticController();
        ingredientImportController = new IngredientImportController();
        awaitedDishListChefController = new AwaitedDishListChefController();
        awaitedDishListWaiterController = new AwaitedDishListWaiterController();
        orderController = new OrderController();
        salaryReportController = new SalaryReportController();
        accountSettingController = new AccountSettingController();

        JFrame jframe = new JFrame();
        jframe.setLocationRelativeTo(null);
        jframe.setExtendedState(Frame.MAXIMIZED_BOTH);
        jframe.add(homeView);
        jframe.setDefaultCloseOperation(EXIT_ON_CLOSE);

        homeFrame = jframe;

        homeView.getTabHome().addChangeListener(l -> changeTab(l));
    }

    public void initController() {
        homeFrame.setVisible(true);
        generateTabByRole();
    }

    public JFrame getFrame() {
        return homeFrame;
    }

    private void generateTabByRole() {
        //1 - chef
        //2 - cashier
        //3 - waiter
        //4 - owner
        switch (App.currentAccount.getRoleId()) {
            case 1: {
                homeView.getTabHome().addTab("Timekeeping", timekeepingController.getPanel());
                homeView.getTabHome().addTab("Change Password", changePasswordController.getPanel());
                homeView.getTabHome().addTab("Import Ingredient", ingredientImportController.getPanel());
                homeView.getTabHome().addTab("Waiting Orders", awaitedDishListChefController.getPanel());
                homeView.getTabHome().addTab("Salary", salaryReportController.getPanel());
                homeView.getTabHome().addTab("Account Setting", accountSettingController.getPanel());
                homeView.getTabHome().addTab("Log Out", logOutController.getPanel());
                break;
            }
            case 2: {
                homeView.getTabHome().addTab("Cashier", orderController.getPanel());
                homeView.getTabHome().addTab("Timekeeping", timekeepingController.getPanel());
                homeView.getTabHome().addTab("Change Password", changePasswordController.getPanel());
                homeView.getTabHome().addTab("Salary", salaryReportController.getPanel());
                homeView.getTabHome().addTab("Account Setting", accountSettingController.getPanel());
                homeView.getTabHome().addTab("Log Out", logOutController.getPanel());
                break;
            }
            case 3: {
                homeView.getTabHome().addTab("Waiting Orders", awaitedDishListWaiterController.getPanel());
                homeView.getTabHome().addTab("Timekeeping", timekeepingController.getPanel());
                homeView.getTabHome().addTab("Change Password", changePasswordController.getPanel());
                homeView.getTabHome().addTab("Salary", salaryReportController.getPanel());
                homeView.getTabHome().addTab("Account Setting", accountSettingController.getPanel());
                homeView.getTabHome().addTab("Log Out", logOutController.getPanel());
                break;
            }
            case 4: {
                homeView.getTabHome().addTab("Management", managementController.getPanel());
                homeView.getTabHome().addTab("Statistics", statisticController.getPanel());
                homeView.getTabHome().addTab("Change Password", changePasswordController.getPanel());
                homeView.getTabHome().addTab("Account Setting", accountSettingController.getPanel());
                homeView.getTabHome().addTab("Log Out", logOutController.getPanel());
                break;
            }
            default: {
            }
        }
    }

    private void changeTab(ChangeEvent l) {
        JTabbedPane sourceTabbedPane = (JTabbedPane) l.getSource();
        int index = sourceTabbedPane.getSelectedIndex();
        switch (App.currentAccount.getRoleId()) {
            case 1: {
                switch (index) {
                    case 0:
                        timekeepingController.loadData();
                        break;
                    case 1:
                        changePasswordController.loadData();
                        break;
                    case 2:
                        ingredientImportController.loadData();
                        break;
                    case 3:
                        awaitedDishListChefController.loadData();
                        break;
                    case 4:
                        salaryReportController.loadData();
                        break;
                    case 5:
                        accountSettingController.loadData();
                        break;
                    case 6:
                        logOutController.loadData();
                        break;
                    default:
                        break;
                }
                break;
            }
            case 2: {
                switch (index) {
                    case 0:
                        orderController.loadData();
                        break;
                    case 1:
                        timekeepingController.loadData();
                        break;
                    case 2:
                        changePasswordController.loadData();
                        break;
                    case 3:
                        salaryReportController.loadData();
                        break;
                    case 4:
                        accountSettingController.loadData();
                        break;
                    case 5:
                        logOutController.loadData();
                        break;
                    default:
                        break;
                }
                break;
            }
            case 3: {
                switch (index) {
                    case 0:
                        awaitedDishListWaiterController.loadData();
                        break;
                    case 1:
                        timekeepingController.loadData();
                        break;
                    case 2:
                        changePasswordController.loadData();
                        break;
                    case 3:
                        salaryReportController.loadData();
                        break;
                    case 4:
                        accountSettingController.loadData();
                        break;
                    case 5:
                        logOutController.loadData();
                        break;
                    default:
                        break;
                }
                break;
            }
            case 4: {
                switch (index) {
                    case 0:
                        managementController.loadData();
                        break;
                    case 1:
                        statisticController.loadData();
                        break;
                    case 2:
                        changePasswordController.loadData();
                        break;
                    case 3:
                        accountSettingController.loadData();
                        break;
                    case 4:
                        logOutController.loadData();
                        break;
                    default:
                        break;
                }
                break;
            }
        }
    }
}
