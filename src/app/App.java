/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app;

import controllers.LogInController;
import javax.swing.JFrame;
import static javax.swing.WindowConstants.EXIT_ON_CLOSE;
import models.Account;
import views.LogInView;

/**
 *
 * @author Admin
 */

public class App {

    /**
     * @param args the command line arguments
     */
    
    public static Account currentAccount = null;
    
    public static void main(String[] args) {
        // TODO code application logic here
        LogInController logInController = new LogInController();
        logInController.initController();
        
    }
}
