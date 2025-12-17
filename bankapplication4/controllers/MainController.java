package com.mycompany.bankapplication4.controllers;

import com.mycompany.bankapplication4.models.User;
import com.mycompany.bankapplication4.models.cards.Card;
import com.mycompany.bankapplication4.repositories.IAccountRepository;
import com.mycompany.bankapplication4.repositories.ICardRepository;
import com.mycompany.bankapplication4.repositories.IUserRepository;
import com.mycompany.bankapplication4.views.ConsoleATMAuthView;
import com.mycompany.bankapplication4.views.ConsoleATMView;
import com.mycompany.bankapplication4.views.ConsoleAuthView;
import com.mycompany.bankapplication4.views.ConsoleNetBankingView;
import com.mycompany.bankapplication4.views.IATMAuthView;
import com.mycompany.bankapplication4.views.IATMView;
import com.mycompany.bankapplication4.views.IAuthView;
import com.mycompany.bankapplication4.views.INetBankingView;

public class MainController implements IMainController {
    private final IAccountRepository accountRepository;
    private final ICardRepository cardRepository;
    private final IUserRepository userRepository;

    public MainController(IAccountRepository accountRepository, ICardRepository cardRepository, IUserRepository userRepository) {
        this.accountRepository = accountRepository;
        this.cardRepository = cardRepository;
        this.userRepository = userRepository;
    }

    @Override
    public void handleRegistration() {
        IAuthView authView = new ConsoleAuthView(new AuthController(userRepository));
        authView.handleRegistration();    
    }

    @Override
    public void handleLogin() {
        IAuthView authView = new ConsoleAuthView(new AuthController(userRepository));
        User user = authView.handleLogin();
        if(user == null) return;
        INetBankingView netBankingView = new ConsoleNetBankingView(new NetBankingController(user, accountRepository, cardRepository));
        netBankingView.runNetBankingView();
    }

    @Override
    public void handleCard() {
        IATMAuthView atmAuthView = new ConsoleATMAuthView(new CardAuthController(cardRepository));
        Card card = atmAuthView.handleATMAuth();
        if(card == null) return;
        IATMView atmView = new ConsoleATMView(new ATMController(card));
        atmView.runATMView();
    }
    
}