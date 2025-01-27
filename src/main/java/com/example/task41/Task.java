package com.example.task41;

import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.*;
import java.util.Scanner;

abstract class Task {
    Scanner sc = new Scanner(System.in);

    private String name_table;

    public void setName_table(String a) {
        name_table = a;
    }

    public String getName_table() {
        return name_table;
    }

    public void sql_create() throws SQLException {}

    public void sql_show(String url) throws SQLException {
        DriverManager.registerDriver(new org.h2.Driver());
        Connection con = DriverManager.getConnection(url, "sa", "password");
        System.out.println("Подключение прошло.\nВсе созданные таблицы:");
        Statement statement = con.createStatement();
        ResultSet resultSet = statement.executeQuery("SHOW TABLES");
        while (resultSet.next()) {
            System.out.println(resultSet.getString(1));
        }
        System.out.println();
    }

    public void excel(String h2Url) throws ClassNotFoundException, SQLException, IOException {
        try {
            String filename = "" + getName_table() + ".xls";
            HSSFWorkbook hwb = new HSSFWorkbook();
            HSSFSheet sheet = hwb.createSheet("new_sheet");
            DriverManager.registerDriver(new org.h2.Driver());
            Connection con = DriverManager.getConnection(h2Url, "sa", "password");
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery("SELECT * FROM " + getName_table());
            ResultSetMetaData rsmd = rs.getMetaData();
            HSSFRow rowhead = sheet.createRow((short) 0);
            int count_of_col = rsmd.getColumnCount();
            for (int i = 1; i <= count_of_col; i++) {
                rowhead.createCell((short) (i - 1)).setCellValue(rsmd.getColumnLabel(i));
                sheet.autoSizeColumn(i - 1);
            }
            int i = 1;
            while (rs.next()) {
                HSSFRow row = sheet.createRow((short) i);
                for (int j = 1; j <= count_of_col; j++) {
                    row.createCell((short) (j - 1)).setCellValue(rs.getString(rsmd.getColumnLabel(j)));
                }
                i++;
            }
            FileOutputStream fileOut = new FileOutputStream(filename);
            hwb.write(fileOut);
            fileOut.close();
            System.out.println("Данные успешно занесены в Excel");
        } catch (Exception ex) {
            System.out.println("Что-то пошло не так");
            System.out.println(ex.toString());
        }
    }

    public void drop(String url) throws SQLException {
        DriverManager.registerDriver(new org.h2.Driver());
        Connection con = DriverManager.getConnection(url, "sa", "password");
        System.out.println("Успешно подключились к БД");
        System.out.println("Все таблицы из БД:");
        sql_show(url);
        System.out.println("Введите название таблицы, которую хотите удалить:");
        String name = sc.nextLine();
        try {
            String query = "DROP TABLE " + name;
            PreparedStatement statement2 = con.prepareStatement(query);
            statement2.executeUpdate();
            System.out.println("Таблица " + name + " успешно удалена.");
        } catch (Exception e) {
            System.out.println("Такой таблицы нет или ошибка при удалении.");
        }
    }

    private static String str;
    private static String str1;

    public static String setStr(String s) {
        str = s;
        return s;
    }

    public static String getStr() {
        return str;
    }

    public static void setStr1(String s) {
        str1 = s;
    }

    public static String getStr1() {
        return str1;
    }
}
