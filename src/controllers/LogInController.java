/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import app.App;
import java.util.ArrayList;
import javax.swing.JFrame;
import static javax.swing.WindowConstants.EXIT_ON_CLOSE;
import models.Account;
import services.AccountService;
import utils.CommonUltilities;
import views.LogInView;

/**
 *
 * @author Admin
 */
public class LogInController {

    private LogInView logInView;
    private JFrame logInFrame;
    private AccountService accountService;

    public LogInController() {
        accountService = new AccountService();
        this.logInView = new LogInView();

        logInView.getLblMessage().setText("");

        logInView.getBtnLogIn().addActionListener(e -> logInHandler());

        JFrame jframe = new JFrame();
        jframe.setSize(500, 300);
        jframe.setLocationRelativeTo(null);
        jframe.setSize(450, 350);
        jframe.add(logInView);
        jframe.setDefaultCloseOperation(EXIT_ON_CLOSE);

        logInFrame = jframe;
    }

    public void initController() {
        logInFrame.setVisible(true);
    }

    public JFrame getFrame() {
        return logInFrame;
    }

    private void logInHandler() {
        String email = logInView.getTxtEmail().getText();
        String password = new String(logInView.getTxtPassword().getPassword());
        logInView.getLblMessage().setText("");

        ArrayList<Account> accounts = accountService.getAll();
        for (Account account : accounts) {
            if (account.getEmail().equals(email) && account.getPassword().equals(CommonUltilities.generateSHA1(password))) {
                if (!account.isWorking()) {
                    logInView.getLblMessage().setText("This account is not available");
                    return;
                }
                logInFrame.setVisible(false);
                App.currentAccount = account;
                HomeController homeController = new HomeController();
                homeController.initController();
                return;
            }
        }

        logInView.getLblMessage().setText("Wrong email or password");
    }
}
