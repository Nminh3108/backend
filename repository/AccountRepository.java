package com.vti.backend.repository;

import com.vti.entity.Account;
import com.vti.utils.JdbcUtils;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AccountRepository {
    public void creareAccount(String username , String email, String password){
        //Tao 1 cau query tuong ung voi chuc nang muon su dung
        String sql = "INSERT INTO jdbc.Account(full_name,email,password) VALUE(?,?,?)";
        //Ket noi toi database de tao 1 phien lam viec
        Connection connection = JdbcUtils.getConnection();
        try {
            //Tao statement tuong ung voi cau query
            // Khong co bien truyen vao: Statement
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1,username);
            preparedStatement.setString(2,email);
            preparedStatement.setString(3,password);
            // Execute cau query va lay (result)
            int resultSet = preparedStatement.executeUpdate();
            //Kiem tra su thanh cong va thong bao
            if (resultSet== 0){
                System.out.println("Them moi that bai");
            }else {
                System.out.println("Them moi thanh cong");
            }
            JdbcUtils.closeConnection();
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
    }

    public List<Account> getAllAccount(){
        List<Account> accounts = new ArrayList<>();
        String sql = "SELECT * FROM jdbc.Account";
        //Ket noi toi database de tao 1 phien lam viec
        Connection connection = JdbcUtils.getConnection();
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);

            while (resultSet.next()){
                //Lay gia tri  tung hang gan vao doi tuong account tuong ung
                Account account = new Account();
                int accountId = resultSet.getInt("account_id");
                account.setAccountId(accountId);

                account.setFullName(resultSet.getString("full_name"));
                account.setEmail(resultSet.getString("email"));
                account.setPassword(resultSet.getString("password"));

                accounts.add(account);
            } JdbcUtils.closeConnection();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return accounts;
    }

    public void updateAccount( int id, String oldPassword, String newPassword ){
        String sql = " UPDATE jdbc.Account  Set password = ? where account_id = ? and password =? " ;
        try{
            Connection connection = JdbcUtils.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1,newPassword);
            preparedStatement.setInt(2,id);
            preparedStatement.setString(3,oldPassword);
            int rs = preparedStatement.executeUpdate();
            if (rs== 0){
                System.err.println("Thay doi password that bai");
            }else {
                System.out.println("Thay doi password thanh cong");
            } JdbcUtils.closeConnection();
        }catch (Exception e){
            System.err.println(e.getMessage());
        }
    }

    public void deleteAccount(int id ){
        String sql = "DELETE FROM jdbc.Account Where account_id = ?";
        Connection connection = JdbcUtils.getConnection();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, id);
            int rs = preparedStatement.executeUpdate();
            if (rs == 0) {
                System.err.println("XOA THAT BAI!");
            } else {
                System.out.println("XOA THANH CONG");
            }
        }catch (Exception e){
            System.out.println(e.getMessage());
        }finally {
            JdbcUtils.closeConnection();
        }
    }

    public List<Account> findAllByEmail(String key) {
        String sql = "SELECT * FROM jdbc.Account where email like ?";
        String words = "%" + key + "%";
        Connection connection = JdbcUtils.getConnection();
        List<Account> accountList = new ArrayList<>();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, words);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Account account = new Account();
                int accountId = resultSet.getInt("account_id");
                account.setAccountId(accountId);
                account.setEmail(resultSet.getString("full_name"));
                account.setPassword(resultSet.getString("password"));
                account.setFullName(resultSet.getString("full_name"));
                accountList.add(account);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }return accountList;
    }

    public boolean login(String email, String password){
        String sql = "SELECT * FROM jdbc.Account where email = ? and password = ?";
        try {
            PreparedStatement preparedStatement = JdbcUtils.getConnection().prepareStatement(sql);
            preparedStatement.setString(1, email);
            preparedStatement.setString(2, password);
            ResultSet resultSet = preparedStatement.executeQuery();
            return resultSet.next();
        }catch (Exception e){
            System.out.println(e.getMessage());
        }finally {
            JdbcUtils.closeConnection();
        }return false;
    }
}

