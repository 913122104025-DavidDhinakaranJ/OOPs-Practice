package com.mycompany.bankapplication4.contexts;

import com.mycompany.authlib.users.factory.AuthenticatableUserFactory;
import com.mycompany.bankapplication4.models.users.factory.UserFactory;
import com.mycompany.bankapplication4.repositories.IAccountRepository;
import com.mycompany.bankapplication4.repositories.ICardRepository;
import com.mycompany.bankapplication4.repositories.IUserRepository;
import com.mycompany.bankapplication4.repositories.InMemoryRepository;

public class ApplicationContext {

    private static ApplicationContext instance = new ApplicationContext();
    
    private final IUserRepository userRepository;
    private final IAccountRepository accountRepository;
    private final ICardRepository cardRepository;
    private final AuthenticatableUserFactory userFactory;
    
    private ApplicationContext() {
        this.userRepository = InMemoryRepository.getInMemoryRepository();
        this.accountRepository = InMemoryRepository.getInMemoryRepository();
        this.cardRepository = InMemoryRepository.getInMemoryRepository();
        this.userFactory = new UserFactory();
    }
    
    public static ApplicationContext getInstance() {
        return instance;
    }
    
    public IUserRepository getUserRepository() {
        return userRepository;
    }

    public IAccountRepository getAccountRepository() {
        return accountRepository;
    }

    public ICardRepository getCardRepository() {
        return cardRepository;
    }

    public AuthenticatableUserFactory getUserFactory() {
        return userFactory;
    }
}