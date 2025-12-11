package com.mycompany.bankapplication3;
import com.mycompany.bankapplication3.controllers.ATMController;
import com.mycompany.bankapplication3.controllers.AuthController;
import com.mycompany.bankapplication3.controllers.MainController;
import com.mycompany.bankapplication3.controllers.NetBankingController;
import com.mycompany.bankapplication3.repositories.IAccountRepository;
import com.mycompany.bankapplication3.repositories.ICardRepository;
import com.mycompany.bankapplication3.repositories.IUserRepository;
import com.mycompany.bankapplication3.repositories.InMemoryRepository;
import com.mycompany.bankapplication3.views.ConsoleATMView;
import com.mycompany.bankapplication3.views.ConsoleAuthView;
import com.mycompany.bankapplication3.views.ConsoleMainView;
import com.mycompany.bankapplication3.views.ConsoleNetBankingView;
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
        
        IMainView mainView = new ConsoleMainView();
        IAuthView authView = new ConsoleAuthView();
        INetBankingView netBankingView = new ConsoleNetBankingView();
        IATMView atmView = new ConsoleATMView();
        
        ATMController atmController = new ATMController(atmView, cards);
        NetBankingController netBankingController = new NetBankingController(netBankingView, accounts, cards);
        AuthController authController = new AuthController(authView, users);
        
        MainController mainController = new MainController(mainView, atmController, netBankingController, authController);
        mainController.run();
    }
}