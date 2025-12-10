package com.mycompany.bankapplication3.repositories;

import com.mycompany.bankapplication3.models.User;
import com.mycompany.bankapplication3.models.accounts.BankAccount;
import com.mycompany.bankapplication3.models.cards.Card;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class InMemoryRepository implements IUserRepository, IAccountRepository, ICardRepository {
    
    Map<String, User> users = new HashMap<>();
    Map<String, BankAccount> accounts = new HashMap<>();
    Map<String, Card> cards = new HashMap<>();
    
    @Override
    public void saveUser(User user) {
        users.put(user.getUsername(), user);
    }

    @Override
    public User findUser(String username) {
        return users.get(username);
    }

    @Override
    public boolean isUsernameTaken(String username) {
        return users.containsKey(username);
    }

    @Override
    public void saveAccount(BankAccount account) {
        accounts.put(account.getAccNo(), account);
    }

    @Override
    public BankAccount findAccount(String accNo) {
        return accounts.get(accNo);
    }

    @Override
    public List<BankAccount> findAccountsByUserId(String userId) {
        List<BankAccount> userAccounts = new ArrayList<>();
        
        for(BankAccount account : accounts.values()) {
            if(account.getUserId().equals(userId)) {
                userAccounts.add(account);
            }
        }
        
        return userAccounts;
    }

    @Override
    public void saveCard(Card card) {
        cards.put(card.getCardNo(), card);
    }

    @Override
    public Card findCard(String cardNo) {
        return cards.get(cardNo);
    }
    
}