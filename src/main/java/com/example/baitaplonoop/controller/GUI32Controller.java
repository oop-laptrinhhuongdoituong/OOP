package com.example.baitaplonoop.controller;

import com.example.baitaplonoop.util.CustomMedia;

import com.example.baitaplonoop.sql.DBConnect;
import com.example.baitaplonoop.util.AlertOOP;
import com.example.baitaplonoop.util.ChangeScene;
import com.example.baitaplonoop.util.showTreeViewCategory;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;

import java.io.*;
import java.net.URL;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.ResourceBundle;

import com.example.baitaplonoop.util.addValueComboBox;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.stage.FileChooser;

public class GUI32Controller implements Initializable {
    public TreeView<String> showCategory_tv;
    public TextArea questionText_tf;
    public TextField questionName_tf;
    public Label categoryName_lb;
    public Button addQuestion_btn;
    public TextField choice1_tf;
    public ComboBox<String> gradeChoice1_cb;
    public ComboBox<String> gradeChoice2_cb;
    public TextField choice2_tf;
    public TextField choice3_tf;
    public ComboBox<String> gradeChoice3_cb;
    public TextField choice4_tf;
    public ComboBox<String> gradeChoice4_cb;
    public TextField choice5_tf;
    public ComboBox<String> gradeChoice5_cb;
    public TextField choice6_tf;
    public ComboBox<String> gradeChoice6_cb;
    public Button createChoice_btn;
    public AnchorPane paneChoice2_ap;
    public AnchorPane buttonPane_ap;
    public AnchorPane paneInScrollPane_ap;
    public ImageView imageQuestion_iv;
    public Button imageQuestion_btn;
    public Slider timeSlider;
    public Button playVideo;
    public Button pause_btn;
    public Button videoQuestion_btn;
    public Label questionLabel_lb;
    public ComboBox<String> selectMedia_cb;
    public Button gifQuestion_btn;
    public AnchorPane videoPane_ap;
    public ImageView gifQuestion_iv;
    public Button addImageChoice3_btn;
    public ImageView imageChoice3_iv;
    public Button addImageChoice1_btn;
    public ImageView imageChoice1_iv;
    public Button addImageChoice2_btn;
    public ImageView imageChoice2_iv;
    public Button addImageChoice4_btn;
    public ImageView imageChoice4_iv;
    public Button addImageChoice5_btn;
    public ImageView imageChoice5_iv;
    public Button addImageChoice6_btn;
    public ImageView imageChoice6_iv;
    public Button editing_btn;
    public Button cancel_btn;
    @FXML
    private MediaView mediaQuestion_mv;
    boolean checkAddCategoryQuestion;
    String nameCategoryQuestion = "null";
    Double gradeChoice1 = 0.0, gradeChoice2 = 0.0, gradeChoice3 = 0.0, gradeChoice4 = 0.0, gradeChoice5 = 0.0, gradeChoice6 = 0.0;
    DBConnect db = new DBConnect();
    private MediaPlayer mediaPlayer;

    public Integer ChoiceNumberInQuestion() {
        int choiceNumber = 0;
        for (TextField textField : Arrays.asList(choice1_tf, choice2_tf, choice3_tf, choice4_tf, choice5_tf, choice6_tf)) {
            if (!textField.getText().trim().equals("")) {
                choiceNumber++;
            }
        }
        return choiceNumber;
    } // Return Number of TextField Choice is Edited

    // View in Editing Question From GUI21
    public void editingQuestionChoice(String categoryName, String questionID, String questionText, String questionMedia, String choiceText1, String choiceGrade1, String choiceMedia1, String choiceText2, String choiceGrade2, String choiceMedia2, String choiceText3, String choiceGrade3, String choiceMedia3, String choiceText4, String choiceGrade4, String choiceMedia4, String choiceText5, String choiceGrade5, String choiceMedia5, String choiceText6, String choiceGrade6, String choiceMedia6) throws FileNotFoundException {
        videoPane_ap.setVisible(false);
        paneChoice2_ap.setVisible(false);
        nameCategoryQuestion = categoryName;
        questionLabel_lb.setText("Editing a Multilple choice question");
        categoryName_lb.setText(categoryName);
        questionName_tf.setText(questionID);
        questionName_tf.setEditable(false);
        questionText_tf.setText(questionText);
        choice1_tf.setText(choiceText1);
        choice2_tf.setText(choiceText2);
        choice3_tf.setText(choiceText3);
        choice4_tf.setText(choiceText4);
        choice5_tf.setText(choiceText5);
        choice6_tf.setText(choiceText6);
        CustomMedia.loadFile(questionMedia, imageQuestion_iv, gifQuestion_iv, mediaQuestion_mv, videoPane_ap, playVideo, pause_btn, timeSlider);

        if (!choiceText1.trim().equals("")) {
            gradeChoice1_cb.setEditable(true);
            gradeChoice1_cb.getEditor().setText(Float.parseFloat(choiceGrade1) * 100 + "%");
            CustomMedia.loadImage(choiceMedia1, imageChoice1_iv);
        }
        if (!choiceText2.trim().equals("")) {
            gradeChoice2_cb.setEditable(true);
            gradeChoice2_cb.getEditor().setText(Float.parseFloat(choiceGrade2) * 100 + "%");
            CustomMedia.loadImage(choiceMedia2, imageChoice2_iv);
        }
        if (!choiceText3.trim().equals("")) {
            gradeChoice3_cb.setEditable(true);
            gradeChoice3_cb.getEditor().setText(Float.parseFloat(choiceGrade3) * 100 + "%");
            CustomMedia.loadImage(choiceMedia3, imageChoice3_iv);
        }
        if (!choiceText4.trim().equals("")) {
            gradeChoice4_cb.setEditable(true);
            gradeChoice4_cb.getEditor().setText(Float.parseFloat(choiceGrade4) * 100 + "%");
            CustomMedia.loadImage(choiceMedia4, imageChoice4_iv);
        }
        if (!choiceText5.trim().equals("")) {
            gradeChoice5_cb.setEditable(true);
            gradeChoice5_cb.getEditor().setText(Float.parseFloat(choiceGrade5) * 100 + "%");
            CustomMedia.loadImage(choiceMedia5, imageChoice5_iv);
        }
        if (!choiceText6.trim().equals("")) {
            gradeChoice6_cb.setEditable(true);
            gradeChoice6_cb.getEditor().setText(Float.parseFloat(choiceGrade6) * 100 + "%");
            CustomMedia.loadImage(choiceMedia6, imageChoice6_iv);
        }
        if (!choiceText4.trim().equals("") || !choiceText5.trim().equals("") || !choiceText6.trim().equals("")) {
            paneChoice2_ap.setTranslateY(239);
            buttonPane_ap.setTranslateY(239);
            paneChoice2_ap.setVisible(true);
        }
    }

    public String questionMediaPath() {
        if (imageQuestion_iv.getImage() != null)
            return CustomMedia.saveImage(imageQuestion_iv, "./src/main/resources/com/example/baitaplonoop/Media/Image/Question", questionName_tf.getText());
        else if (gifQuestion_iv.getImage() != null)
            return CustomMedia.saveGif(gifQuestion_iv, "./src/main/resources/com/example/baitaplonoop/Media/GIF/Question", questionName_tf.getText().trim());
        else if (mediaQuestion_mv.getMediaPlayer() != null)
            return CustomMedia.saveVideo(mediaQuestion_mv, "./src/main/resources/com/example/baitaplonoop/Media/Video", questionName_tf.getText().trim());
        else return null;
    }   // To return path to Media in Question, this prepare for save in the database

    public String getCategoryIDQuestion() {
        if (nameCategoryQuestion != null) {
            try {
                return db.FindCategoryID(nameCategoryQuestion);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        } else return null;
    } // Return categoryID in question

    public void AddQuestionIntoSQL() throws SQLException {
        if (questionName_tf.getText().trim().equals("") || questionText_tf.getText().trim().equals("") || ChoiceNumberInQuestion() < 2) {
            AlertOOP.mustFill("Add Question Status", "Add Question Fail", "You must fill in Question Name, Question Text and minimum 2 Choice");
        } else {
            boolean checkQuestionExist;
            try {
                checkQuestionExist = db.checkQuestionID(questionName_tf.getText().trim());
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
            if (checkQuestionExist & questionName_tf.isEditable())
                AlertOOP.mustFill("Add Question Status", "Add Question Fail", "Question Name exists");
            else {
                String pathMediaQuestion = questionMediaPath();
                String[] questionInfo = {getCategoryIDQuestion(), questionName_tf.getText().trim(), questionText_tf.getText().trim(), "1", pathMediaQuestion};
                try {
                    int addQuestion = db.UpdateQuestion(questionInfo);
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
                if (!choice1_tf.getText().trim().equals("")) {
                    if (imageChoice1_iv.getImage() != null) {
                        String pathMediaChoice = CustomMedia.saveImage(imageChoice1_iv, "./src/main/resources/com/example/baitaplonoop/Media/Image/Choice", questionName_tf.getText() + "1");
                        String[] addChoice1 = {choice1_tf.getText(), String.valueOf(gradeChoice1), questionName_tf.getText() + "1", questionName_tf.getText(), pathMediaChoice};
                        db.UpdateChoice(addChoice1);
                    } else {
                        String[] addChoice1 = {choice1_tf.getText(), String.valueOf(gradeChoice1), questionName_tf.getText() + "1", questionName_tf.getText(), null};
                        db.UpdateChoice(addChoice1);
                    }
                }
                if (!choice2_tf.getText().trim().equals("")) {
                    if (imageChoice2_iv.getImage() != null) {
                        String pathMediaChoice = CustomMedia.saveImage(imageChoice2_iv, "./src/main/resources/com/example/baitaplonoop/Media/Image/Choice", questionName_tf.getText() + "2");
                        String[] addChoice2 = {choice2_tf.getText(), String.valueOf(gradeChoice2), questionName_tf.getText() + "2", questionName_tf.getText(), pathMediaChoice};
                        db.UpdateChoice(addChoice2);
                    } else {
                        String[] addChoice2 = {choice2_tf.getText(), String.valueOf(gradeChoice2), questionName_tf.getText() + "2", questionName_tf.getText(), null};
                        db.UpdateChoice(addChoice2);
                    }
                }
                if (!choice3_tf.getText().trim().equals("")) {
                    if (imageChoice3_iv.getImage() != null) {
                        String pathMediaChoice = CustomMedia.saveImage(imageChoice3_iv, "./src/main/resources/com/example/baitaplonoop/Media/Image/Choice", questionName_tf.getText() + "3");
                        String[] addChoice3 = {choice3_tf.getText(), String.valueOf(gradeChoice3), questionName_tf.getText() + "3", questionName_tf.getText(), null, pathMediaChoice};
                        db.UpdateChoice(addChoice3);
                    } else {
                        String[] addChoice3 = {choice3_tf.getText(), String.valueOf(gradeChoice3), questionName_tf.getText() + "3", questionName_tf.getText(), null, null};
                        db.UpdateChoice(addChoice3);
                    }
                }
                if (!choice4_tf.getText().trim().equals("")) {
                    if (imageChoice4_iv.getImage() != null) {
                        String pathMediaChoice = CustomMedia.saveImage(imageChoice4_iv, "./src/main/resources/com/example/baitaplonoop/Media/Image/Choice", questionName_tf.getText() + "4");
                        String[] addChoice4 = {choice4_tf.getText(), String.valueOf(gradeChoice4), questionName_tf.getText() + "4", questionName_tf.getText(), null, pathMediaChoice};
                        db.UpdateChoice(addChoice4);
                    } else {
                        String[] addChoice4 = {choice4_tf.getText(), String.valueOf(gradeChoice4), questionName_tf.getText() + "4", questionName_tf.getText(), null, null};
                        db.UpdateChoice(addChoice4);
                    }
                }
                if (!choice5_tf.getText().trim().equals("")) {
                    if (imageChoice5_iv.getImage() != null) {
                        String pathMediaChoice = CustomMedia.saveImage(imageChoice5_iv, "./src/main/resources/com/example/baitaplonoop/Media/Image/Choice", questionName_tf.getText() + "5");
                        System.out.println(pathMediaChoice);
                        String[] addChoice5 = {choice5_tf.getText(), String.valueOf(gradeChoice5), questionName_tf.getText() + "5", questionName_tf.getText(), null, pathMediaChoice};
                        db.UpdateChoice(addChoice5);
                    } else {
                        String[] addChoice5 = {choice5_tf.getText(), String.valueOf(gradeChoice5), questionName_tf.getText() + "5", questionName_tf.getText(), null, null};
                        db.UpdateChoice(addChoice5);
                    }
                }
                if (!choice6_tf.getText().trim().equals("")) {
                    if (imageChoice6_iv.getImage() != null) {
                        String pathMediaChoice = CustomMedia.saveImage(imageChoice6_iv, "./src/main/resources/com/example/baitaplonoop/Media/Image/Choice", questionName_tf.getText() + "6");
                        String[] addChoice6 = {choice6_tf.getText(), String.valueOf(gradeChoice6), questionName_tf.getText() + "6", questionName_tf.getText(), null, pathMediaChoice};
                        db.UpdateChoice(addChoice6);
                    } else {
                        String[] addChoice6 = {choice6_tf.getText(), String.valueOf(gradeChoice6), questionName_tf.getText() + "6", questionName_tf.getText(), null, null};
                        db.UpdateChoice(addChoice6);
                    }
                }
                AlertOOP.AddDone("Add Question Status", "Add Question Done", "Done");
                showCategory_tv.setEditable(false);
                questionName_tf.setEditable(false);
                categoryName_lb.setMouseTransparent(false);
            }
        }
    }   // Add new Question into SQL

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        videoPane_ap.setVisible(false);
        paneChoice2_ap.setVisible(false);
        addValueComboBox.addValue(gradeChoice1_cb);
        addValueComboBox.addValue(gradeChoice2_cb);
        addValueComboBox.addValue(gradeChoice3_cb);
        addValueComboBox.addValue(gradeChoice4_cb);
        addValueComboBox.addValue(gradeChoice5_cb);
        addValueComboBox.addValue(gradeChoice6_cb);
        gradeChoice1_cb.setOnAction(gradeChoice1Event -> gradeChoice1 = addValueComboBox.convertStringToDouble(gradeChoice1_cb));
        gradeChoice2_cb.setOnAction(gradeChoice2Event -> gradeChoice2 = addValueComboBox.convertStringToDouble(gradeChoice2_cb));
        gradeChoice3_cb.setOnAction(gradeChoice3Event -> gradeChoice3 = addValueComboBox.convertStringToDouble(gradeChoice3_cb));
        gradeChoice4_cb.setOnAction(gradeChoice4Event -> gradeChoice4 = addValueComboBox.convertStringToDouble(gradeChoice4_cb));
        gradeChoice5_cb.setOnAction(gradeChoice5Event -> gradeChoice5 = addValueComboBox.convertStringToDouble(gradeChoice5_cb));
        gradeChoice6_cb.setOnAction(gradeChoice6Event -> gradeChoice6 = addValueComboBox.convertStringToDouble(gradeChoice6_cb));
        selectMedia_cb.getItems().addAll("Add Video", "Add Image", "Add Gif");
        //Chọn hiện button thêm ảnh, video, gif vào question
        selectMedia_cb.setOnAction(selectMediaEvent -> {
            String selectedItem = selectMedia_cb.getSelectionModel().getSelectedItem();
            switch (selectedItem) {
                case "Add Video" -> {
                    videoQuestion_btn.setVisible(true);
                    imageQuestion_btn.setVisible(false);
                    gifQuestion_btn.setVisible(false);
                }
                case "Add Image" -> {
                    videoQuestion_btn.setVisible(false);
                    imageQuestion_btn.setVisible(true);
                    gifQuestion_btn.setVisible(false);
                }
                case "Add Gif" -> {
                    videoQuestion_btn.setVisible(false);
                    imageQuestion_btn.setVisible(false);
                    gifQuestion_btn.setVisible(true);
                }
            }
        });
        // Event to show category in TreeView
        categoryName_lb.setOnMouseClicked(mouseEvent -> {
            if (questionName_tf.isEditable()) {
                showCategory_tv.setVisible(true);
                categoryName_lb.setVisible(false);
                checkAddCategoryQuestion = true;
                TreeItem<String> root = new TreeItem<>("Course IT:");
                showTreeViewCategory.setTreeViewImport("Select * from Category where parentID IS NULL", root);
                showCategory_tv.setRoot(root);
                showCategory_tv.getSelectionModel().selectedItemProperty().addListener((observableValue, oldValue, newValue) -> {
                    if (newValue != null) {
                        categoryName_lb.setText(newValue.getValue());
                        nameCategoryQuestion = newValue.getValue();
                    }
                    showCategory_tv.setVisible(false);
                    categoryName_lb.setVisible(true);
                });
            }
        });
        // Event to creat new 3 choice
        createChoice_btn.setOnMouseClicked(createChoiceEvent -> {// Event to creat new 3 choice
            paneChoice2_ap.setTranslateY(239);
            buttonPane_ap.setTranslateY(239);
            paneChoice2_ap.setVisible(true);
        });
        // Add image in Question Text (upload in ImageView not to save in Database)
        imageQuestion_btn.setOnAction(e -> CustomMedia.AddImageToImageView(imageQuestion_iv));
        addImageChoice1_btn.setOnAction(e -> CustomMedia.AddImageToImageView(imageChoice1_iv));
        addImageChoice2_btn.setOnAction(e -> CustomMedia.AddImageToImageView(imageChoice2_iv));
        addImageChoice3_btn.setOnAction(e -> CustomMedia.AddImageToImageView(imageChoice3_iv));
        addImageChoice4_btn.setOnAction(e -> CustomMedia.AddImageToImageView(imageChoice4_iv));
        addImageChoice5_btn.setOnAction(e -> CustomMedia.AddImageToImageView(imageChoice5_iv));
        addImageChoice6_btn.setOnAction(e -> CustomMedia.AddImageToImageView(imageChoice6_iv));
        // Add gif in Question Text (upload in ImageView not to save in Database)
        gifQuestion_btn.setOnAction(e -> {
            FileChooser fileChooser = new FileChooser();
            fileChooser.getExtensionFilters().add(
                    new FileChooser.ExtensionFilter("GIF files (*.gif)", "*.gif")
            );
            File file = fileChooser.showOpenDialog(null);
            if (file != null) {
                String urlGif = file.toURI().toString();
                Image image = new Image(urlGif);
                imageQuestion_iv.setImage(image);
            }
        });
        // Add video in Question Text (upload in MediaView not to save in Database)
        videoQuestion_btn.setOnAction(e -> {
            FileChooser fileChooser = new FileChooser();
            fileChooser.getExtensionFilters().addAll(
                    new FileChooser.ExtensionFilter("MP4 files (*.mp4)", "*.mp4"),
                    new FileChooser.ExtensionFilter("AVI files (*.avi)", "*.avi")
            );
            File file = fileChooser.showOpenDialog(null);
            if (file != null) {
                String urlVideoQuestion = file.toURI().toString();
                System.out.println(urlVideoQuestion);
                Media media = new Media(urlVideoQuestion);
                mediaPlayer = new MediaPlayer(media);
                mediaQuestion_mv.setMediaPlayer(mediaPlayer);
                videoPane_ap.setVisible(true);
                CustomMedia.setVideoPlay(mediaPlayer, timeSlider);
            }
        });
        playVideo.setOnAction(e -> {
            if (mediaPlayer != null) {
                mediaPlayer.play();
            }
        });
        pause_btn.setOnAction(e -> {
            if (mediaPlayer != null) {
                mediaPlayer.pause();
            }
        });
        addQuestion_btn.setOnMouseClicked(saveChangeEvent -> {
            try {
                AddQuestionIntoSQL();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
            ChangeScene.changeSceneUsingMouseEvent(this, "/com/example/baitaplonoop/GUI11.fxml", saveChangeEvent);
        });
        cancel_btn.setOnMouseClicked(cancelEvent -> ChangeScene.changeSceneUsingMouseEvent(this, "/com/example/baitaplonoop/GUI11.fxml", cancelEvent));
        editing_btn.setOnMouseClicked(saveChangeContinueEditEvent -> {
            try {
                AddQuestionIntoSQL();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        });
    }
}