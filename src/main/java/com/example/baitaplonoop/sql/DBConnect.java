package com.example.baitaplonoop.sql;

import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DBConnect {
    public Connection con;

    public DBConnect() {
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            String url = "jdbc:sqlserver://DAT\\MSSQLSERVER01:1433;databaseName=Exam_Management2;"
                    + "encrypt=true;trustServerCertificate=true;sslProtocol=TLSv1.2";
            String username = "sa";
            String password = "123";
            con = DriverManager.getConnection(url, username, password);
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(DBConnect.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public ResultSet getData(String stringSQL) {
        ResultSet rs = null;
        try {
            Statement state = con.createStatement();
            rs = state.executeQuery(stringSQL);
        } catch (SQLException ex) {
            Logger.getLogger(DBConnect.class.getName()).log(Level.SEVERE, null, ex);
        }
        return rs;
    }

    public int InsertQuestion(String[] stringSQL, byte[] pic) {
        int rowInserted;
        String sql = "INSERT INTO Question(categoryID, questionID, questionText, questionMark, questionImage) values(?,?,?,?,?)";
        PreparedStatement statement;
        try {
            statement = con.prepareStatement(sql);
            statement.setString(1, stringSQL[0]);
            statement.setString(2, stringSQL[1]);
            statement.setString(3, stringSQL[2]);
            statement.setString(4, stringSQL[3]);
            statement.setBytes(5, pic);
            rowInserted = statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return rowInserted;
    }

    public int InsertQuestion(String[] stringSQL) {
        int rowInserted = 0;
        String sql = "INSERT INTO Question(categoryID, questionID, questionText) values(?,?,?)";
        PreparedStatement statement;
        try {
            statement = con.prepareStatement(sql);
            statement.setString(1, stringSQL[0]);
            statement.setString(2, stringSQL[1]);
            statement.setString(3, stringSQL[2]);
            rowInserted = statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return rowInserted;
    }

    public int InsertChoice(String[] stringSQL) {
        int rowInserted = 0;
        String sql = "INSERT INTO Choice(choiceText, choiceGrade, choiceID, questionID, isSelected) values(?,?,?,?,?)";
        PreparedStatement statement;
        try {
            statement = con.prepareStatement(sql);
            statement.setString(1, stringSQL[0]);
            statement.setFloat(2, Float.parseFloat(stringSQL[1]));
            statement.setString(3, stringSQL[2]);
            statement.setString(4, stringSQL[3]);
            statement.setString(5, stringSQL[4]);
            rowInserted = statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return rowInserted;
    }

    public int InsertQuestionInQuiz(String[] stringSQL) throws SQLException {
        int rowInserted = 0;
        String sql = "insert into QuestionInQuiz(questionID,quizName,yourMark) values(?,?,?)";
        PreparedStatement statement;
        statement = con.prepareStatement(sql);
        statement.setString(1, stringSQL[0]);
        statement.setString(2, stringSQL[1]);
        statement.setString(3, stringSQL[2]);
        rowInserted = statement.executeUpdate();
        return rowInserted;
    }

    public String FindCategoryID(String categoryName) throws SQLException {
        String categoryID = null;
        String sql = "SELECT categoryID FROM Category WHERE categoryName = N'" + categoryName + "'";
//        PreparedStatement statement = con.prepareStatement(sql);
//        statement.setString(1, categoryName);
        ResultSet result = getData(sql);

        // Nếu tìm thấy categoryName, lấy categoryID từ kết quả truy vấn
        if (result.next()) {
            categoryID = result.getString("categoryID");
        }

        // Đóng kết nối và trả về categoryID

        return categoryID;
    }


    public int InsertCategory(String[] stringSQL) {
        int rowInserted = 0;
        String sql = "INSERT INTO Category(parentID, categoryName, categoryID, categoryinfo) values (?, ?, ?, ?)";
        PreparedStatement statement;
        try {
            statement = con.prepareStatement(sql);
            statement.setString(1, stringSQL[0]);
            statement.setString(2, stringSQL[1]);
            statement.setString(3, stringSQL[2]);
            statement.setString(4, stringSQL[3]);
            rowInserted = statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return rowInserted;
    }

    public int AddNewQuiz(String[] stringSQL) {
        int rowInserted = 0;
        String sql = "INSERT INTO Quiz(quizName, Descript, openTime, closeTime, timeLimit) values (?, ?, ?, ?, ?)";
        PreparedStatement statement;
        try {
            statement = con.prepareStatement(sql);
            statement.setString(1, stringSQL[0]);
            statement.setString(2, stringSQL[1]);
            statement.setString(3, stringSQL[2]);
            statement.setString(4, stringSQL[3]);
            statement.setString(5, stringSQL[4]);
            rowInserted = statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return rowInserted;
    }

    public String FindQuestionText(String questionID) {
        String questionText = null;
        String sql = "SELECT questionText FROM Question WHERE questionID = ?";
        try (
                PreparedStatement statement = con.prepareStatement(sql);
        ) {
            statement.setString(1, questionID);

            ResultSet rs = statement.executeQuery();
            if (rs.next()) questionText = rs.getNString("questionText");
        }catch (SQLException e) {
            // Xử lý các ngoại lệ SQL
            e.printStackTrace();
        }
        return questionText;
    }
}
