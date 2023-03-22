package com.vti.backend.controller;

import com.vti.backend.service.AccountService;
import com.vti.entity.Account;

import java.util.List;

public class AccountController {
AccountService accountService = new AccountService();
public void createAccount(String username, String email, String password){
    accountService.createAccount(username,email,password);

    }


    public void updateAccount(int id, String oldPassword, String newPassword){
        accountService.updateAccount(id,oldPassword,newPassword);
    }

    public void deleteAccount(int id){
        accountService.deleteAccount(id);
    }

    public List<Account> findByEmail(String key){

        return accountService.findByEmail(key);
    }

    public List<Account> getAllAccount(){return accountService.getAllAccount();
    }

    public boolean login(String email, String password){
        return accountService.login(email,password);
    }


}