package com.example.baitaplonoop.controller;

import com.example.baitaplonoop.sql.DBConnect;
import com.example.baitaplonoop.util.AlertOOP;
import com.example.baitaplonoop.util.showTreeViewCategory;
import javafx.embed.swing.SwingFXUtils;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;

import java.awt.image.BufferedImage;
import java.io.*;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

import com.example.baitaplonoop.util.addValueComboBox;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.stage.FileChooser;
import javafx.util.Duration;

import javax.imageio.ImageIO;


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
    @FXML
    private MediaView mediaQuestion_mv;
    boolean checkAddCategoryQuestion;
    String nameCategoryQuestion;
    Double gradeChoice1, gradeChoice2, gradeChoice3, gradeChoice4, gradeChoice5, gradeChoice6;
    DBConnect db = new DBConnect();
    private MediaPlayer mediaPlayer;

    public void editingQuestionChoice(String categoryName, String questionID, String questionText, String choiceText1, String choiceGrade1, String choiceText2, String choiceGrade2, String choiceText3, String choiceGrade3, String choiceText4, String choiceGrade4, String choiceText5, String choiceGrade5, String choiceText6, String choiceGrade6) {
        videoPane_ap.setVisible(false);
        paneChoice2_ap.setVisible(false);
        questionLabel_lb.setText("Editing a Multilple choice question");
        categoryName_lb.setText(categoryName);
        questionName_tf.setText(questionID);
        questionText_tf.setText(questionText);
        choice1_tf.setText(choiceText1);
        choice2_tf.setText(choiceText2);
        choice3_tf.setText(choiceText3);
        choice4_tf.setText(choiceText4);
        choice5_tf.setText(choiceText5);
        choice6_tf.setText(choiceText6);
        if (!choiceText1.trim().equals("")) {
            gradeChoice1_cb.setEditable(true);
            gradeChoice1_cb.getEditor().setText(Float.parseFloat(choiceGrade1) * 100 + "%");
        }
        if (!choiceText2.trim().equals("")) {
            gradeChoice2_cb.setEditable(true);
            gradeChoice2_cb.getEditor().setText(Float.parseFloat(choiceGrade2) * 100 + "%");
        }
        if (!choiceText3.trim().equals("")) {
            gradeChoice3_cb.setEditable(true);
            gradeChoice3_cb.getEditor().setText(Float.parseFloat(choiceGrade3) * 100 + "%");
        }
        if (!choiceText4.trim().equals("")) {
            gradeChoice4_cb.setEditable(true);
            gradeChoice4_cb.getEditor().setText(Float.parseFloat(choiceGrade4) * 100 + "%");
        }
        if (!choiceText5.trim().equals("")) {
            gradeChoice5_cb.setEditable(true);
            gradeChoice5_cb.getEditor().setText(Float.parseFloat(choiceGrade5) * 100 + "%");
        }
        if (!choiceText6.trim().equals("")) {
            gradeChoice6_cb.setEditable(true);
            gradeChoice6_cb.getEditor().setText(Float.parseFloat(choiceGrade6) * 100 + "%");
        }
        if (choiceText4.trim().equals("") || !choiceText5.trim().equals("") || !choiceText6.trim().equals("")) {
            paneChoice2_ap.setTranslateY(239);
            buttonPane_ap.setTranslateY(239);
            paneChoice2_ap.setVisible(true);
        }
    }

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
        selectMedia_cb.setOnAction(event -> {
            // Get the selected item
            String selectedItem = selectMedia_cb.getSelectionModel().getSelectedItem();
            // Show the corresponding button and hide the others
            switch (selectedItem) {
                case "Add Video":
                    videoQuestion_btn.setVisible(true);
                    imageQuestion_btn.setVisible(false);
                    gifQuestion_btn.setVisible(false);
                    break;
                case "Add Image":
                    videoQuestion_btn.setVisible(false);
                    imageQuestion_btn.setVisible(true);
                    gifQuestion_btn.setVisible(false);
                    break;
                case "Add Gif":
                    videoQuestion_btn.setVisible(false);
                    imageQuestion_btn.setVisible(false);
                    gifQuestion_btn.setVisible(true);
                    break;
            }
        });
        // Event to show treeView
        categoryName_lb.setOnMouseClicked(mouseEvent -> {
            showCategory_tv.setVisible(true);
            categoryName_lb.setVisible(false);
            checkAddCategoryQuestion = true;
            TreeItem<String> root = new TreeItem<>("Course IT:");
            showTreeViewCategory.setTreeViewImport("Select * from Category where parentID IS NULL", root);
            showCategory_tv.setRoot(root);
            showCategory_tv.getSelectionModel().selectedItemProperty().addListener((observableValue, oldValue, newValue) -> {
                categoryName_lb.setText(newValue.getValue());
                nameCategoryQuestion = newValue.getValue();
            });

        });
        // Event to creat new 3 choice
        createChoice_btn.setOnMouseClicked(createChoiceEvent -> {
            paneChoice2_ap.setTranslateY(239);
            buttonPane_ap.setTranslateY(239);
            paneChoice2_ap.setVisible(true);
        });
        imageQuestion_btn.setOnAction(e -> {
            FileChooser fileChooser = new FileChooser();
            fileChooser.getExtensionFilters().addAll(
                    new FileChooser.ExtensionFilter("JPG files (*.jpg)", "*.jpg"),
                    new FileChooser.ExtensionFilter("PNG files (*.png)", "*.png")
            );
            File file = fileChooser.showOpenDialog(null);
            if (file != null) {
                String url1 = file.toURI().toString();
                Image image = new Image(url1);
                imageQuestion_iv.setImage(image);
            }
        });
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

        videoQuestion_btn.setOnAction(e -> {
            FileChooser fileChooser = new FileChooser();
            fileChooser.getExtensionFilters().addAll(
                    new FileChooser.ExtensionFilter("MP4 files (*.mp4)", "*.mp4"),
                    new FileChooser.ExtensionFilter("AVI files (*.avi)", "*.avi")
            );
            File file = fileChooser.showOpenDialog(null);
            if (file != null) {
                String urlVideoQuestion = file.toURI().toString();
                Media media = new Media(urlVideoQuestion);
                mediaPlayer = new MediaPlayer(media);
                mediaQuestion_mv.setMediaPlayer(mediaPlayer);
                videoPane_ap.setVisible(true);
                mediaPlayer.currentTimeProperty().addListener((obs, oldTime, newTime) -> {
                    if (!timeSlider.isValueChanging()) {
                        timeSlider.setValue(newTime.toSeconds() / mediaPlayer.getTotalDuration().toSeconds() * 100);
                    }
                });
                timeSlider.valueProperty().addListener((obs, oldValue, newValue) -> {
                    if (timeSlider.isValueChanging()) {
                        mediaPlayer.seek(mediaPlayer.getTotalDuration().multiply(newValue.doubleValue() / 100));
                    }
                });
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
            if ( questionName_tf.getText() == null || questionText_tf.getText() == null) {
                AlertOOP.mustFill("Add question status", "Add Question field", "You must fill in questionID, questionText");
            } else if (!checkAddCategoryQuestion) {
                if (imageQuestion_iv != null) {
                    String questionMediaPath = saveImage(imageQuestion_iv, "./src/main/resources/com/example/baitaplonoop/Media/Image/Question", questionName_tf.getText());
                    String[] questionInfo = {null, questionName_tf.getText(), questionText_tf.getText(), "1", questionMediaPath};
                    try {
                        int addQuestion = db.UpdateQuestion(questionInfo);
                    } catch (SQLException e) {
                        throw new RuntimeException(e);
                    }
                    AlertOOP.AddDone("Add question status", "Add Question Done", "Add question successfully!");
                } else if (gifQuestion_iv != null) {
                    String questionMediaPath = saveGif(gifQuestion_iv, "./src/main/resources/com/example/baitaplonoop/Gif", questionName_tf.getText());
                    String[] questionInfo = {null, questionName_tf.getText(), questionName_tf.getText(), "1", questionMediaPath};
                    try {
                        int addQuestion = db.UpdateQuestion(questionInfo);
                    } catch (SQLException e) {
                        throw new RuntimeException(e);
                    }
                } else {

                }
            } else {
                String IDCategoryQuestion;
                try {
                    IDCategoryQuestion = String.valueOf(db.FindCategoryID(nameCategoryQuestion));
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
                if (imageQuestion_iv != null) {
                    String questionMediaPath = saveImage(imageQuestion_iv, "./src/main/resources/com/example/baitaplonoop/Media/Image/Question", questionName_tf.getText());
                    String[] questionInfo = {IDCategoryQuestion, questionName_tf.getText(), questionName_tf.getText(), "1"};
                    db.InsertQuestionMedia(questionInfo, questionMediaPath);

                }

                // To add Choice into Database
                if (!choice1_tf.getText().trim().equals("")) {
                    String[] addChoice1 = {choice1_tf.getText(), String.valueOf(gradeChoice1), questionName_tf.getText() + "1", questionName_tf.getText(), null};
                    db.InsertChoice(addChoice1);
                }
                if (!choice2_tf.getText().trim().equals("")) {
                    String[] addChoice2 = {choice2_tf.getText(), String.valueOf(gradeChoice2), questionName_tf.getText() + "2", questionName_tf.getText(), null};
                    db.InsertChoice(addChoice2);
                }
                if (!choice3_tf.getText().trim().equals("")) {
                    String[] addChoice3 = {choice3_tf.getText(), String.valueOf(gradeChoice3), questionName_tf.getText() + "3", questionName_tf.getText(), null};
                    db.InsertChoice(addChoice3);
                }
                if (!choice4_tf.getText().trim().equals("")) {
                    String[] addChoice4 = {choice4_tf.getText(), String.valueOf(gradeChoice4), questionName_tf.getText() + "4", questionName_tf.getText(), null};
                    db.InsertChoice(addChoice4);
                }
                if (!choice5_tf.getText().trim().equals("")) {
                    String[] addChoice5 = {choice5_tf.getText(), String.valueOf(gradeChoice5), questionName_tf.getText() + "5", questionName_tf.getText(), null};
                    db.InsertChoice(addChoice5);
                }
                if (!choice6_tf.getText().trim().equals("")) {
                    String[] addChoice6 = {choice1_tf.getText(), String.valueOf(gradeChoice1), questionName_tf.getText() + "6", questionName_tf.getText(), null};
                    db.InsertChoice(addChoice6);
                }
                AlertOOP.AddDone("Add Question Status", "Add question Done", "");
            }
        });
    }
    public void CheckDuration(MediaView mediaView) {
        MediaPlayer mediaPlayer = mediaView.getMediaPlayer();
        // Lấy độ dài của media tính bằng Duration
        Duration duration = mediaPlayer.getTotalDuration();
        // Chuyển đổi Duration sang giây
        double seconds = duration.toSeconds();
        System.out.println(seconds);
    }
    public String saveImage(ImageView imageView, String pathImage, String questionID) {
        String questionMediaPath = null;
        try {
            File target = new File(pathImage + File.separator + questionID + ".jpg");
            BufferedImage toWrite = SwingFXUtils.fromFXImage(imageView.getImage(), null);
            ImageIO.write(toWrite, "jpg", target);
            questionMediaPath = target.getAbsolutePath();
        } catch (Exception x) {
            System.err.println("Failed to save image");
            x.printStackTrace();
        }
        return questionMediaPath;
    }
    public String saveGif(ImageView imageView, String pathGIF, String questionID) {
        String questionGifPah = null;
        try {
            File target = new File(pathGIF + File.separator + questionID + "gif");
            BufferedImage toWrite = SwingFXUtils.fromFXImage(imageView.getImage(), null);
            ImageIO.write(toWrite, "gif", target);
            questionGifPah = target.getAbsolutePath();
        } catch (Exception x) {
            System.err.println("Failed to save gif");
            x.printStackTrace();
        }
        return questionGifPah;
    }


}


