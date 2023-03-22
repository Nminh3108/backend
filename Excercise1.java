package com.vti.backend;

import com.vti.utils.JdbcUtils;

import java.sql.*;
import java.util.Scanner;

public class Excercise1 {
    private int id;

    public static void main(String[] args)  {
        Excercise1 excercise1 = new Excercise1();
        excercise1.question1();
    }

    public static void question1()  {
        System.out.println("Connect success");
        JdbcUtils.closeConnection();
    }

    public void question2() throws ClassNotFoundException, SQLException {
        String sql = "SELECT * FROM position;";
        Connection connection = JdbcUtils.getConnection();
        Statement statement = connection.createStatement();
        ResultSet result = statement.executeQuery(sql);
        System.out.println("Thong tin cua position: ");
        String leftAlignFormat = "| %-6d | %-21s |%n";
        System.out.format("+--------+-----------------------+%n");
        System.out.format("| iD | PositionName |%n");
        System.out.format("+--------+-----------------------+%n");
        while (result.next()) {
            System.out.format(leftAlignFormat, result.getInt(1),

                    result.getString(2));
        }
        System.out.format("+--------+-----------------------+%n");
    }

    public void question5() throws ClassNotFoundException, SQLException {
        String sql = "DELETE FROM jdbc.Account Where position _id = ?";
        Connection connection = JdbcUtils.getConnection();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            int rs = preparedStatement.executeUpdate();
            if (rs == 1) {
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
    }

