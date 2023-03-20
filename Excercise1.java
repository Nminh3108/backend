package com.vti.backend;

import com.vti.utils.JdbcUtils;

import java.sql.SQLException;
import java.util.Scanner;

public class Excercise1 {
    public static void main(String[] args)  {
        Excercise1 excercise1 = new Excercise1();
        excercise1.question1();
    }

    public static void question1()  {
        System.out.println("Connect success");
        JdbcUtils.closeConnection();
    }


}
