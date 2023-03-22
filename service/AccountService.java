package com.vti.backend.service;

import com.vti.backend.repository.AccountRepository;
import com.vti.entity.Account;

import java.util.List;

public class AccountService implements IAccountService {
    private AccountRepository repository = new AccountRepository();

    @Override
    public void createAccount(String username, String email, String password) {

    }

    @Override
    public void updateAccount(int id, String oldPassword, String newPassword) {

    }

    @Override
    public void deleteAccount(int id) {

    }

    @Override
    public List<Account> findByEmail(String key) {
        return null;
    }

    @Override
    public List<Account> getAllAccount() {
        return null;
    }

    @Override
    public boolean login(String email, String password) {
        return false;
    }
}