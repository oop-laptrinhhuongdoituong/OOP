package com.example.baitaplonoop;

import com.example.baitaplonoop.SQL.DBConnect;

import java.sql.ResultSet;
import java.sql.SQLException;

public class main {
    public static void main(String[] args) {
        DBConnect db = new DBConnect();
        ResultSet rs = db.getData("SELECT * FROM HocSinh");
            try {
                while(rs.next()){
                    System.out.println(rs.getString("MaHS"));
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
    }
}
