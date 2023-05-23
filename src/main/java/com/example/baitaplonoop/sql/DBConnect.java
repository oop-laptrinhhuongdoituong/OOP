package com.example.baitaplonoop.sql;

import java.sql.*;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import com.example.baitaplonoop.model.Choice;
public class DBConnect {
    public Connection con;
    public DBConnect() {
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            String url = "jdbc:sqlserver://DESKTOP-AP629KT\\SQLEXPRESS:1433;databaseName=Exam_Management2;"
                    + "encrypt=true;trustServerCertificate=true;sslProtocol=TLSv1.2";
            String username = "sa";
            String password = "hoangphuc0703";
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
    public int InsertQuestion(String[] stringSQL) {
        int rowInserted;
        String sql = "INSERT INTO Question(categoryID, questionID, questionText, questionMark, questionMedia) values(?,?,?,?,?)";
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
//    public int InsertQuestion(String[] stringSQL) {
//        int rowInserted = 0;
//        String sql = "INSERT INTO Question(categoryID, questionID, questionText) values(?,?,?)";
//        PreparedStatement statement;
//        try {
//            statement = con.prepareStatement(sql);
//            statement.setString(1, stringSQL[0]);
//            statement.setString(2, stringSQL[1]);
//            statement.setString(3, stringSQL[2]);
//            rowInserted = statement.executeUpdate();
//        } catch (SQLException e) {
//            throw new RuntimeException(e);
//        }
//        return rowInserted;
//    }
    public int InsertChoice(String[] stringSQL) {
        int rowInserted = 0;
        String sql = "INSERT INTO Choice(choiceText, choiceGrade, choiceID, questionID, isSelected, choiceMedia) values(?,?,?,?,?,?)";
        PreparedStatement statement;
        try {
            statement = con.prepareStatement(sql);
            statement.setString(1, stringSQL[0]);
            statement.setFloat(2, Float.parseFloat(stringSQL[1]));
            statement.setString(3, stringSQL[2]);
            statement.setString(4, stringSQL[3]);
            statement.setString(5, stringSQL[4]);
            statement.setString(6, stringSQL[5]);
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
        ResultSet result = getData(sql);
        if (result.next()) {
            categoryID = result.getString("categoryID");
        }
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
    public String[] FindQuestionInfo(String questionID) {
        String[] questionInfo = null;
        String sql = "SELECT questionText,questionMedia FROM Question WHERE questionID = ?";
        try (
                PreparedStatement statement = con.prepareStatement(sql);
        ) {
            statement.setString(1, questionID);
            ResultSet rs = statement.executeQuery();
            if (rs.next()) {
                String questionText = rs.getNString("questionText");
                String questionMedia = rs.getString("questionMedia");
                questionInfo = new String[]{questionText, questionMedia};
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return questionInfo;
    }
    public String[] FindChoiceInfo(String choiceID) {
        String[] choiceInfo = null;
        String sql = "SELECT choiceText, choiceGrade, choiceMedia FROM choice WHERE choiceID = ?";
        try (
                PreparedStatement statement = con.prepareStatement(sql);
        ) {
            statement.setString(1, choiceID);
            ResultSet rs = statement.executeQuery();
            if (rs.next()) {
                String choiceText = rs.getNString("choiceText");
                Float choiceGrade = rs.getFloat("choiceGrade");
                String choiceMedia = rs.getString("choiceMedia");
                choiceInfo = new String[]{choiceText, String.valueOf(choiceGrade), choiceMedia};
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return choiceInfo;
    }
    public int UpdateQuestion(String[] questionInfo) throws SQLException {
        int rowInserted = 0;
        String sql = "MERGE Question AS target USING (VALUES (?,?,?,?,?)) AS source (categoryID, questionID, questionText, questionMark, questionMedia)  ON target.questionID = source.questionID  WHEN MATCHED THEN  UPDATE SET questionText = source.questionText,  categoryID = source.categoryID, questionMedia = source.questionMedia  WHEN NOT MATCHED THEN  INSERT (questionID, questionText, categoryID, questionMedia)  VALUES (source.questionID, source.questionText, source.categoryID, source.questionMedia);";
        PreparedStatement statement;
        statement = con.prepareStatement(sql);
        statement.setString(1, questionInfo[0]);
        statement.setString(2, questionInfo[1]);
        statement.setString(3, questionInfo[2]);
        statement.setString(4, questionInfo[3]);
        statement.setString(5, questionInfo[4]);
        rowInserted = statement.executeUpdate();
        return rowInserted;
    }
    public int UpdateChoice(String[] choiceInfo) throws SQLException {
        int rowInserted = 0;
        String sql = "MERGE Choice AS target USING (VALUES (?,?,?,?,?)) AS source (choiceText, choiceGrade, choiceID, questionID, choiceMedia)  ON target.choiceID = source.choiceID  WHEN MATCHED THEN  UPDATE SET choiceText = source.choiceText,  choiceGrade = source.choiceGrade, choiceID = source.choiceID,questionID = source.questionID, choiceMedia = source.choiceMedia  WHEN NOT MATCHED THEN  INSERT (choiceText, choiceGrade, choiceID, questionID, choiceMedia)  VALUES (source.choiceText, source.choiceGrade, source.choiceID, source.questionID, source.choiceMedia);";
        PreparedStatement statement;
        statement = con.prepareStatement(sql);
        statement.setString(1, choiceInfo[0]);
        statement.setFloat(2, Float.parseFloat(choiceInfo[1]));
        statement.setString(3, choiceInfo[2]);
        statement.setString(4, choiceInfo[3]);
        statement.setString(5,  choiceInfo[4]);
        rowInserted = statement.executeUpdate();
        return rowInserted;
    }
    public boolean checkQuestionID(String questionID) throws SQLException {
        boolean result = false;
        String sql = "SELECT * FROM Question WHERE questionID = ?"; // Câu lệnh SQL truy vấn dữ liệu theo questionID
        PreparedStatement statement;
        statement = con.prepareStatement(sql);
        statement.setString(1, questionID); // Thiết lập giá trị cho tham số
        ResultSet rs = statement.executeQuery(); // Thực hiện câu lệnh và lấy kết quả trả về
        if (rs.next()) { // Kiểm tra xem kết quả có dòng nào hay không
            result = true; // Nếu có, nghĩa là questionID tồn tại
        }
        return result; // Trả về giá trị boolean
    }
}