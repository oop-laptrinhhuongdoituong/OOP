package com.example.baitaplonoop.util;


import com.example.baitaplonoop.model.Question;
import com.example.baitaplonoop.sql.DBConnect;
import org.apache.poi.xwpf.extractor.XWPFWordExtractor;
import org.apache.poi.xwpf.usermodel.XWPFDocument;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

public class ImportFile {
    static DBConnect db = new DBConnect();
    static int ErrorLine;
    public static boolean checkFileDOCX(File file){
        try {
            FileInputStream fis = new FileInputStream(file);
            XWPFDocument doc = new XWPFDocument(fis);
            XWPFWordExtractor extractor = new XWPFWordExtractor(doc);
            String paragraph = extractor.getText();
            String[] part = paragraph.split("\n");
            boolean isQuestion = true;
            int countLineBetweenQuestion = 1;
            boolean isAnswer = false;
            int countAnswerNumber = 0;
            boolean isCorrectAnswer = false;
            int i = 0;
            while(i < part.length){
                if(isQuestion){
                    if(part[i].equals("")){
                        countLineBetweenQuestion ++;
                        if(countLineBetweenQuestion > 1){
                            ErrorLine = i;
                            return false;
                        }
                    }else{
                        if(countLineBetweenQuestion == 0){
                            ErrorLine = i;
                            return false;
                        }else if(part[i].charAt(1) == '.'){
                            ErrorLine = 1;
                            return false;
                        }else{
                            isQuestion = false;
                            isAnswer = true;
                        }
                    }
                }else if(isAnswer){

                }
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return true;
    }
    public static boolean checkFileTXT(){
        return true;
    }
    public static void importQuestionFromDocxFile(File file, String categoryID) {
        try {
            FileInputStream fis = new FileInputStream(file);
            XWPFDocument doc = new XWPFDocument(fis);
            XWPFWordExtractor extractor = new XWPFWordExtractor(doc);
            String paragraph = extractor.getText();
            String[] part = paragraph.split("\n");
            ArrayList<Line> start = new ArrayList<>();
            ArrayList<Line> end = new ArrayList<>();
            int i = 0;
            boolean isQuestion = true;
            while(i < part.length){
                if(!part[i].equals("")){
                    if(isQuestion){
                        start.add(new Line(i));
                        isQuestion = false;
                    }else{
                        String[] check = part[i].split(": ");
                        if(check[0].equals("ANSWER")){
                            isQuestion = true;
                            end.add(new Line(i));
                        }
                    }
                }
                i++;
            }
            for(int j = 0; j<start.size(); j++){
                String[] questionPart = part[start.get(j).LineNumber].split(": ", 2);
                String[] insertQuestion = {categoryID, questionPart[0], questionPart[1], "NULL", "1.00"};
                int questionRowInserted = db.InsertQuestion(insertQuestion);
                String[] answer = part[end.get(j).LineNumber].split(": ", 2);
                for(int k = start.get(j).LineNumber + 1; k < end.get(j).LineNumber; k++){
                    String choiceID = questionPart[0] + (k-start.get(j).LineNumber);
                    String partChoice[] = part[k].split(". ", 2);
                    if(partChoice[0].equals(answer[1])){
                        String insertChoice[] = {partChoice[1],"100",choiceID, questionPart[0], "0"};
                        int choiceRowInserted = db.InsertChoice(insertChoice);
                    }else{
                        String insertChoice[] = {partChoice[1], "0", choiceID, questionPart[0], "0"};
                        int choiceRowInserted = db.InsertChoice(insertChoice);
                    }
                }
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    public static void importQuestionFromTXTFile(File file){

    }
}
//    ArrayList<String> questionID = new ArrayList<>();
//    ArrayList<String> questionText = new ArrayList<>();
//    ArrayList<String> choice = new ArrayList<>();
//    ArrayList<String> correctChoice = new ArrayList<>();
//    boolean isQuestion = true;
//    boolean isChoice = false;
//    int i = 0;
//    String s = "INSERT INTO Category(categoryID, questionID, questionText, questionMark) values (" + categoryID + ",";
//            while(i < part.length){
//        if(isQuestion){
//        if(!part[i].equals("")) {
//        String[] questionPart = part[i].split(": ", 2);
//        questionID.add(questionPart[0]);
//        questionText.add(questionPart[1]);
//        s += questionPart[0] + "," + questionPart[1] + ", 1.00)" ;
//        isQuestion = false;
//        isChoice = true;
//        }
//        }else if(isChoice){
//        String[] check = part[i].split(": ");
//        if(check[0].equals("ANSWER")){
//        correctChoice.add(check[1]);
//        isQuestion = true;
//        isChoice = false;
//        }else{
//        choice.add(part[i]);
//        }
//        }
//        i++;
//        }
//        System.out.println("List question ID:");
//        for(int j = 0; j<questionID.size(); j++){
//        System.out.println(questionID.get(j));
//        }
//        System.out.println("List question:");
//        for(int j = 0; j<questionText.size(); j++){
//        System.out.println(questionText.get(j));
//        }
//        System.out.println("List choice:");
//        for(int j = 0; j<choice.size(); j++){
//        System.out.println(choice.get(j));
//        }
//        System.out.println("List correct answer:");
//        for(int j = 0; j<correctChoice.size(); j++){
//        System.out.println(correctChoice.get(j));
//        }