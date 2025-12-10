package com.mycompany.bankapplication3.repositories;
import com.mycompany.bankapplication3.models.accounts.BankAccount;
import java.util.List;

public interface IAccountRepository {
    void saveAccount(BankAccount account);
    BankAccount findAccount(String accNo);
    List<BankAccount> findAccountsByUserId(String userId);
}