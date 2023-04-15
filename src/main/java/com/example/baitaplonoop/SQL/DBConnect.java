package com.example.baitaplonoop.SQL;

import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DBConnect {
    Connection con;
    public DBConnect() {
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            String url = "jdbc:sqlserver://Dat\\MSSQLSERVER01:1433;databaseName=SQLDBUI;"
                    + "encrypt=true;trustServerCertificate=true;sslProtocol=TLSv1.2";
            String username = "sa";
            String password = "123";
//            Connection connection = DriverManager
//                    .getConnection("jdbc:sqlserver://Dat\\MSSQLSERVER01:1433;databaseName=SQLDBUI","sa","123");
            con = DriverManager.getConnection(url, username, password);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(DBConnect.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(DBConnect.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public ResultSet getData(String stringSQL){
        ResultSet rs =  null;
        try{
            Statement state = con.createStatement();
            rs = state.executeQuery(stringSQL);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return rs;
    }
}
