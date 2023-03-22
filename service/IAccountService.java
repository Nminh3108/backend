package com.vti.backend.service;

import com.vti.entity.Account;

import java.util.List;

public interface IAccountService {
        void createAccount(String username, String email,String password);

        void updateAccount(int id, String oldPassword,String newPassword);

        void deleteAccount(int id);

        List<Account> findByEmail(String key);

        List<Account> getAllAccount();

        boolean login(String email, String password);
}
