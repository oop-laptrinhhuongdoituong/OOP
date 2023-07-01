package com.example.baitaplonoop.util;

import com.example.baitaplonoop.sql.DBConnect;
import org.apache.poi.xwpf.extractor.XWPFWordExtractor;
import org.apache.poi.xwpf.usermodel.*;
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ImportFile {
    static DBConnect db = new DBConnect();
    public static int ErrorLine;
    public static int numberOfQuestion;

    public static boolean checkFileDOCX(File file) {
        try {
            FileInputStream fis = new FileInputStream(file);
            XWPFDocument doc = new XWPFDocument(fis);
            XWPFWordExtractor extractor = new XWPFWordExtractor(doc);
            String paragraph = extractor.getText();
            String[] part = paragraph.split("\n");
            boolean isQuestion = true;
            int countLineBetweenQuestion = 1;
            boolean isChoice = false;
            int countChoice = 0;
            boolean isCorrectAnswer = false;
            int i = 0;
            ArrayList<String> listChoice = new ArrayList<>();
            while (i < part.length) {
                if (isQuestion) {
                    if (part[i].equals("")) {
                        countLineBetweenQuestion++;
                        if (countLineBetweenQuestion > 1) {
                            ErrorLine = i+1;
                            return false;
                        }
                    } else {
                        if (countLineBetweenQuestion == 0) {
                            ErrorLine = i+1;
                            return false;
                        } else if (part[i].charAt(1) == '.') {
                            ErrorLine = i+1;
                            return false;
                        }else {
                            countLineBetweenQuestion = 0;
                            isQuestion = false;
                            isChoice = true;
                        }
                    }
                } else if (isChoice) {
                    if(part[i].equals("")){
                        ErrorLine = i+1;
                        return false;
                    }else if(part[i].indexOf(". ") == 1){
                        String partChoice[] = part[i].split(". ", 2);
                        listChoice.add(partChoice[0]);
                        if(part[i].charAt(0) >= 65 && part[i].charAt(0) <= 90){
                            if(partChoice[1].trim().equals("")){
                                ErrorLine = i+1;
                                return false;
                            }else{
                                countChoice ++;
                                if((part[i+1].indexOf(". ") != 1) && (!part[i+1].equals("")) && (part[i+1].charAt(1) != '.') && (part[i+1].charAt(1) != ' ')){
                                    if(countChoice < 2){
                                        ErrorLine = i+1;
                                        return false;
                                    }
                                    countChoice = 0;
                                    isChoice = false;
                                    isCorrectAnswer = true;
                                }
                            }
                        }else{
                            ErrorLine = i+1;
                            return false;
                        }
                    }else {
                        ErrorLine = i+1;
                        return false;
                    }
                }else if(isCorrectAnswer){
                    if(part[i].equals("")){
                        ErrorLine = i+1;
                        return false;
                    }else if(part[i].indexOf(": ") == 6){
                        String partAnswer[] = part[i].split(": ", 2);
                        if(!partAnswer[0].equals("ANSWER")){
                            ErrorLine = i+1;
                            return false;
                        }else{
                            if(!listChoice.contains(partAnswer[1])){
                                ErrorLine = i+1;
                                return false;
                            }else{
                                listChoice.clear();
                                isCorrectAnswer = false;
                                isQuestion = true;
                            }
                        }
                    }else{
                        ErrorLine = i+1;
                        return false;
                    }
                }
                i++;
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return true;
    }

    public static boolean checkFileTXT(File file) {
        try {
            List<String> part = Files.readAllLines(file.toPath(), StandardCharsets.UTF_8);
            boolean isQuestion = true;
            int countLineBetweenQuestion = 1;
            boolean isChoice = false;
            int countChoice = 0;
            boolean isCorrectAnswer = false;
            int i = 0;
            ArrayList<String> listChoice = new ArrayList<>();
            while (i < part.size()) {
                if (isQuestion) {
                    if (part.get(i).equals("")) {
                        countLineBetweenQuestion ++;
                        if (countLineBetweenQuestion > 1) {
                            ErrorLine = i+1;
                            return false;
                        }
                    } else {
                        if (countLineBetweenQuestion == 0) {
                            ErrorLine = i+1;
                            return false;
                        } else if (part.get(i).charAt(1) == '.') {
                            ErrorLine = i+1;
                            return false;
                        }else {
                            countLineBetweenQuestion = 0;
                            isQuestion = false;
                            isChoice = true;
                        }
                    }
                } else if (isChoice) {
                    if(part.get(i).equals("")){
                        ErrorLine = i+1;
                        return false;
                    }else if(part.get(i).indexOf(". ") == 1){
                        String partChoice[] = part.get(i).split(". ", 2);
                        listChoice.add(partChoice[0]);
                        if(part.get(i).charAt(0) >= 65 && part.get(i).charAt(0) <= 90){
                            if(partChoice[1].trim().equals("")){
                                ErrorLine = i+1;
                                return false;
                            }else{
                                countChoice ++;
                                if((part.get(i+1).indexOf(". ") != 1) && (!part.get(i+1).equals("")) && (part.get(i+1).charAt(1) != '.') && (part.get(i+1).charAt(1) != ' ')){
                                    if(countChoice < 2){
                                        ErrorLine = i+1;
                                        return false;
                                    }
                                    countChoice = 0;
                                    isChoice = false;
                                    isCorrectAnswer = true;
                                }
                            }
                        }else{
                            ErrorLine = i+1;
                            return false;
                        }
                    }else {
                        ErrorLine = i+1;
                        return false;
                    }
                }else if(isCorrectAnswer){
                    if(part.get(i).equals("")){
                        ErrorLine = i+1;
                        return false;
                    }else if(part.get(i).indexOf(": ") == 6){
                        String partAnswer[] = part.get(i).split(": ", 2);
                        if(!partAnswer[0].equals("ANSWER")){
                            ErrorLine = i+1;
                            return false;
                        }else{
                            if(!listChoice.contains(partAnswer[1])){
                                ErrorLine = i+1;
                                return false;
                            }else{
                                listChoice.clear();
                                isCorrectAnswer = false;
                                isQuestion = true;
                            }
                        }
                    }else{
                        ErrorLine = i+1;
                        return false;
                    }
                }
                i++;
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return true;
    }

    public static void importQuestionFromDocxFile(File file, String categoryID) {
        try {
            numberOfQuestion = 0;
            FileInputStream fis = new FileInputStream(file);
            XWPFDocument doc = new XWPFDocument(fis);
            XWPFWordExtractor extractor = new XWPFWordExtractor(doc);
            String paragraph = extractor.getText();
            String[] part = paragraph.split("\n");
            List<XWPFParagraph> paragraphs = doc.getParagraphs();
            ArrayList<Line> start = new ArrayList<>();
            ArrayList<Line> end = new ArrayList<>();
            int i = 0;
            boolean isQuestion = true;
            while (i < part.length) {
                if (!part[i].equals("")) {
                    if (isQuestion) {
                        start.add(new Line(i));
                        isQuestion = false;
                    } else {
                        String[] check = part[i].split(": ");
                        if (check[0].equals("ANSWER")) {
                            isQuestion = true;
                            end.add(new Line(i));
                            numberOfQuestion ++;
                        }
                    }
                }
                i++;
            }
            for (int j = 0; j < start.size(); j++) {
                List<XWPFRun> runs = paragraphs.get(start.get(j).LineNumber).getRuns();
                byte[] bytepic = null;
                if(runs != null){
                    for(XWPFRun r : runs){
                        List<XWPFPicture> listPic = r.getEmbeddedPictures();
                        if(listPic != null && listPic.size() != 0){
                            bytepic = listPic.get(0).getPictureData().getData();
                        }
                    }
                }
                String[] questionPart = part[start.get(j).LineNumber].split(": ", 2);
                if(bytepic != null){
                    String imagePath = "./src/main/resources/com/example/baitaplonoop/Media/Image/Question/" + questionPart[0] + ".png";
                    FileOutputStream os = new FileOutputStream(new File(imagePath));
                    os.write(bytepic);
                    String[] insertQuestion = {categoryID, questionPart[0], questionPart[1], "1.00", imagePath};
                    int questionRowInserted = db.InsertQuestion(insertQuestion);
                }else{
                    String[] insertQuestion = {categoryID, questionPart[0], questionPart[1], "1.00", null};
                    int questionRowInserted = db.InsertQuestion(insertQuestion);
                }
                String[] answer = part[end.get(j).LineNumber].split(": ", 2);
                for (int k = start.get(j).LineNumber + 1; k < end.get(j).LineNumber; k++) {
                    List<XWPFRun> runChoice = paragraphs.get(k).getRuns();
                    byte[] bytepicChoice = null;
                    if(runChoice != null){
                        for(XWPFRun r : runChoice){
                            List<XWPFPicture> listPic = r.getEmbeddedPictures();
                            if(listPic != null && listPic.size() != 0){
                                bytepicChoice = listPic.get(0).getPictureData().getData();
                            }
                        }
                    }
                    String choiceID = questionPart[0] + (k - start.get(j).LineNumber);
                    String partChoice[] = part[k].split(". ", 2);
                    if(bytepicChoice != null) {
                        String imagePath = "./src/main/resources/com/example/baitaplonoop/Media/Image/Choice/" + choiceID + ".png";
                        FileOutputStream os = new FileOutputStream(new File(imagePath));
                        os.write(bytepicChoice);
                        if (partChoice[0].equals(answer[1])) {
                            String insertChoice[] = {partChoice[1], "1.00", choiceID, questionPart[0], "0", imagePath};
                            int choiceRowInserted = db.InsertChoice(insertChoice);
                        } else {
                            String insertChoice[] = {partChoice[1], "0", choiceID, questionPart[0], "0", imagePath};
                            int choiceRowInserted = db.InsertChoice(insertChoice);
                        }
                    }else{
                        if (partChoice[0].equals(answer[1])) {
                            String insertChoice[] = {partChoice[1], "1.00", choiceID, questionPart[0], "0", null};
                            int choiceRowInserted = db.InsertChoice(insertChoice);
                        } else {
                            String insertChoice[] = {partChoice[1], "0", choiceID, questionPart[0], "0", null};
                            int choiceRowInserted = db.InsertChoice(insertChoice);
                        }
                    }
                }
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void importQuestionFromTXTFile(File file, String categoryID) {
        try {
            numberOfQuestion = 0;
            List<String> part = Files.readAllLines(file.toPath(), StandardCharsets.UTF_8);
            ArrayList<Line> start = new ArrayList<>();
            ArrayList<Line> end = new ArrayList<>();
            int i = 0;
            boolean isQuestion = true;
            while (i < part.size()) {
                if (!part.get(i).equals("")) {
                    if (isQuestion) {
                        start.add(new Line(i));
                        isQuestion = false;
                    } else {
                        String[] check = part.get(i).split(": ");
                        if (check[0].equals("ANSWER")) {
                            numberOfQuestion ++;
                            isQuestion = true;
                            end.add(new Line(i));
                        }
                    }
                }
                i++;
            }
            for (int j = 0; j < start.size(); j++) {
                String[] questionPart = part.get(start.get(j).LineNumber).split(": ", 2);
                String[] insertQuestion = {categoryID, questionPart[0], questionPart[1], "1.00", null};
                int questionRowInserted = db.InsertQuestion(insertQuestion);
                String[] answer = part.get(end.get(j).LineNumber).split(": ", 2);
                for (int k = start.get(j).LineNumber + 1; k < end.get(j).LineNumber; k++) {
                    String choiceID = questionPart[0] + (k - start.get(j).LineNumber);
                    String partChoice[] = part.get(k).split(". ", 2);
                    if (partChoice[0].equals(answer[1])) {
                        String insertChoice[] = {partChoice[1], "1.00", choiceID, questionPart[0], "0"};
                        int choiceRowInserted = db.InsertChoice(insertChoice);
                    } else {
                        String insertChoice[] = {partChoice[1], "0", choiceID, questionPart[0], "0"};
                        int choiceRowInserted = db.InsertChoice(insertChoice);
                    }
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
//