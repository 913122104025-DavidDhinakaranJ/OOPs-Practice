package com.mycompany.bankapplication3.controllers;
import com.mycompany.bankapplication3.enums.MainOption;
import com.mycompany.bankapplication3.models.User;
import com.mycompany.bankapplication3.views.IMainView;

public class MainController {
    private final IMainView mainView;
    
    private final AuthController authController;
    private final NetBankingController netBankingController;
    private final ATMController atmController;

    public MainController(IMainView mainView, ATMController atmController, NetBankingController netBankingController, AuthController authController) {
        this.mainView = mainView;
        
        this.authController = authController;
        this.netBankingController = netBankingController;
        this.atmController = atmController;
    }
    
    public void run() {
        boolean running = true;
        while(running) {
            MainOption choice = mainView.getMainChoice();
            
            switch(choice) {
                case REGISTER -> authController.handleRegistration();
                case LOGIN -> {
                    User user = authController.handleLogin();
                    if(user != null) netBankingController.runNetBanking(user);
                }
                case CARD -> atmController.runATM();
                case EXIT -> running = false;
                default -> mainView.displayError("Invalid Choice");
                
            }
        }
    }
}