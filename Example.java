package com.vti.backend;

import com.vti.entity.Account;
import com.vti.utils.JdbcUtils;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Example {
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
                    account.setPassword(resultSet.getString("Password"));

                    accounts.add(account);
                }
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
            return accounts;
        }
}
