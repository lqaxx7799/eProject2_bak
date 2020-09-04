/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import app.App;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import views.LogOutView;

/**
 *
 * @author Admin
 */
public class LogOutController implements BaseController{
    private LogOutView logOutView;
    private HomeController homeController;
    
    public LogOutController(HomeController homeController){
        logOutView = new LogOutView();
        this.homeController = homeController;
        
        logOutView.getBtnLogOut().addActionListener(l -> logOutHandler());
    }
    
    @Override
    public JPanel getPanel() {
        return logOutView;
    }

    @Override
    public void loadData() {
    }
    
    private void logOutHandler(){
        int dialogButton = JOptionPane.YES_NO_OPTION;
        int dialogResult = JOptionPane.showConfirmDialog(null, "Do you really want to log out?", "Alert", dialogButton);
        if (dialogResult == JOptionPane.YES_OPTION) {
            App.currentAccount = null;
            homeController.getFrame().setVisible(false);
            LogInController logInController = new LogInController();
            logInController.initController();
        }
    }
}
