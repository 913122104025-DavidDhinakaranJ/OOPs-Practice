package com.mycompany.bankapplication3;
import com.mycompany.bankapplication3.controllers.ATMController;
import com.mycompany.bankapplication3.controllers.AuthController;
import com.mycompany.bankapplication3.controllers.MainController;
import com.mycompany.bankapplication3.controllers.NetBankingController;
import com.mycompany.bankapplication3.repositories.IAccountRepository;
import com.mycompany.bankapplication3.repositories.ICardRepository;
import com.mycompany.bankapplication3.repositories.IUserRepository;
import com.mycompany.bankapplication3.repositories.InMemoryRepository;
import com.mycompany.bankapplication3.views.ConsoleBankView;
import com.mycompany.bankapplication3.views.IATMView;
import com.mycompany.bankapplication3.views.IAuthView;
import com.mycompany.bankapplication3.views.IMainView;
import com.mycompany.bankapplication3.views.INetBankingView;

public class BankApplication3 {
    public static void main(String[] args) {
        InMemoryRepository inMemoryRepository = new InMemoryRepository();
        
        IUserRepository users = inMemoryRepository;
        IAccountRepository accounts = inMemoryRepository;
        ICardRepository cards = inMemoryRepository;
        
        ConsoleBankView bankView = new ConsoleBankView();
        
        IMainView mainView = bankView;
        IAuthView authView = bankView;
        INetBankingView netBankingView = bankView;
        IATMView atmView = bankView;
        
        ATMController atmController = new ATMController(atmView, cards);
        NetBankingController netBankingController = new NetBankingController(netBankingView, accounts, cards);
        AuthController authController = new AuthController(authView, users);
        
        MainController mainController = new MainController(mainView, atmController, netBankingController, authController);
        mainController.run();
    }
}