/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import app.App;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.Timer;
import models.Timekeeping;
import services.TimekeepingService;
import views.TimekeepingView;

/**
 *
 * @author Admin
 */
public class TimekeepingController implements BaseController {

    private TimekeepingView timekeepingView;
    private TimekeepingService timekeepingService;
    private DateFormat dfDate;
    private DateFormat dfTime;

    public TimekeepingController() {
        timekeepingView = new TimekeepingView();
        timekeepingService = new TimekeepingService();
        dfDate = new SimpleDateFormat("dd/MM/yyyy");
        dfTime = new SimpleDateFormat("HH:mm:ss");

        timekeepingView.getBtnCreateTimekeeping().addActionListener(e -> createTimeKeeping());
    }

    @Override
    public JPanel getPanel() {
        return timekeepingView;
    }

    @Override
    public void loadData() {
        timekeepingView.getLblDate().setText(dfDate.format(new Date()));
        timekeepingView.getLblTime().setText(dfTime.format(new Date()));

        timekeepingView.getLblInTime().setText("");
        timekeepingView.getLblOutTime().setText("");

        setUpTimer();

        java.sql.Date currentDate = new java.sql.Date(new Date().getTime());
        Timekeeping timekeeping = timekeepingService.getByDate(currentDate, App.currentAccount.getId());

        if (timekeeping != null) {
            if (timekeeping.getInTime() != null) {
                timekeepingView.getLblInTime().setText(dfTime.format(timekeeping.getInTime()));
            }
            if (timekeeping.getOutTime() != null) {
                timekeepingView.getLblOutTime().setText(dfTime.format(timekeeping.getOutTime()));
            }
        }
    }

    private void setUpTimer() {
        Timer t = new Timer(1000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                timekeepingView.getLblTime().setText(dfTime.format(new Date()));
            }
        });
        t.start();
    }

    private void createTimeKeeping() {
        java.sql.Date currentDate = new java.sql.Date(new Date().getTime());
        Timekeeping timekeeping = timekeepingService.getByDate(currentDate, App.currentAccount.getId());
        if (timekeeping == null) {
            timekeeping = new Timekeeping();
            timekeeping.setAccountId(App.currentAccount.getId());
            timekeeping.setInTime(new Date());
            timekeepingService.insert(timekeeping);
            timekeepingView.getLblInTime().setText(dfTime.format(timekeeping.getInTime()));
        } else if (timekeeping.getInTime() != null && timekeeping.getOutTime() == null) {
            timekeeping.setOutTime(new Date());
            timekeepingService.update(timekeeping);
            timekeepingView.getLblOutTime().setText(dfTime.format(timekeeping.getOutTime()));
        } else {
            JOptionPane.showMessageDialog(null, "Bạn đã chấm đủ công ngày hôm nay");
        }
    }
}
