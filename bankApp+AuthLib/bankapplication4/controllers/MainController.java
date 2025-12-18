package com.mycompany.bankapplication4.controllers;

import com.mycompany.authlib.controller.AuthController;
import com.mycompany.bankapplication4.models.users.User;
import com.mycompany.bankapplication4.models.cards.Card;
import com.mycompany.bankapplication4.models.users.factory.UserFactory;
import com.mycompany.bankapplication4.repositories.IAccountRepository;
import com.mycompany.bankapplication4.repositories.ICardRepository;
import com.mycompany.bankapplication4.views.ConsoleATMAuthView;
import com.mycompany.bankapplication4.views.ConsoleATMView;
import com.mycompany.authlib.views.ConsoleAuthView;
import com.mycompany.bankapplication4.views.ConsoleNetBankingView;
import com.mycompany.bankapplication4.views.IATMAuthView;
import com.mycompany.bankapplication4.views.IATMView;
import com.mycompany.authlib.views.IAuthView;
import com.mycompany.bankapplication4.views.INetBankingView;
import com.mycompany.authlib.repositories.IAuthenticatableUserRepository;

public class MainController implements IMainController {
    private final IAccountRepository accountRepository;
    private final ICardRepository cardRepository;
    private final IAuthenticatableUserRepository userRepository;

    public MainController(IAccountRepository accountRepository, ICardRepository cardRepository, IAuthenticatableUserRepository userRepository) {
        this.accountRepository = accountRepository;
        this.cardRepository = cardRepository;
        this.userRepository = userRepository;
    }

    @Override
    public void handleRegistration() {
        IAuthView authView = new ConsoleAuthView(new AuthController(userRepository, new UserFactory()));
        authView.handleRegistration();    
    }

    @Override
    public void handleLogin() {
        IAuthView authView = new ConsoleAuthView(new AuthController(userRepository, new UserFactory()));
        User user = (User) authView.handleLogin();
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