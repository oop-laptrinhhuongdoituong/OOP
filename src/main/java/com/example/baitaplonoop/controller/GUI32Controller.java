package com.example.baitaplonoop.controller;

import com.example.baitaplonoop.sql.DBConnect;
import com.example.baitaplonoop.util.showTreeViewCategory;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.image.PixelFormat;
import javafx.scene.layout.AnchorPane;

import java.awt.image.BufferedImage;
import java.io.*;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

import com.example.baitaplonoop.util.addValueComboBox;
import javafx.scene.layout.Pane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.stage.FileChooser;
import javafx.util.Duration;

import javax.imageio.ImageIO;
import javax.swing.*;


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
    boolean checkAddCategoryQuestion;


    String nameCategoryQuestion;
    Double gradeChoice1, gradeChoice2, gradeChoice3, gradeChoice4, gradeChoice5, gradeChoice6;

    DBConnect db = new DBConnect();
    @FXML
    private MediaView mediaQuestion_mv;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {


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
        // Event to add Question into Database
        addQuestion_btn.setOnMouseClicked(saveChangeEvent -> {
            Alert alertAddCategory = new Alert(Alert.AlertType.INFORMATION);
            alertAddCategory.setTitle("Add Category Information");
            ButtonType btnContinue = new ButtonType("Oke", ButtonBar.ButtonData.YES);
            ButtonType btnBack = new ButtonType("Home page", ButtonBar.ButtonData.NO);
            alertAddCategory.getButtonTypes().setAll(btnContinue, btnBack);

            if (!checkAddCategoryQuestion || questionName_tf.getText() == null || questionText_tf.getText() == null) {
                alertAddCategory.setContentText("Please fill the blank field");
                alertAddCategory.setHeaderText("You must fill all field!");
            } else {
                String IDCategoryQuestion;
                try {
                    IDCategoryQuestion = String.valueOf(db.FindCategoryID(nameCategoryQuestion));
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
                // To add Question into database

                if (imageQuestion_iv.getImage() == null & mediaQuestion_mv.getMediaPlayer() == null) {
                    String[] addQuestion = {IDCategoryQuestion, questionName_tf.getText(), questionText_tf.getText(), "1"};
                    db.InsertQuestion(addQuestion);
                } else if (mediaQuestion_mv.getMediaPlayer() != null) {
                    MediaPlayer mediaPlayer = mediaQuestion_mv.getMediaPlayer();
                    var ref = new Object() {
                        byte[] buffer = new byte[1000000000];
                    };
                    mediaPlayer.setOnReady(() -> {
                        Duration duration = mediaPlayer.getMedia().getDuration();
                        // Chuyển đổi đối tượng MediaPlayer thành mảng byte[]
                        ref.buffer = convertMediaPlayerToByteArray(mediaPlayer);


                    });
                    String[] addQuestion = {IDCategoryQuestion, questionName_tf.getText(), questionText_tf.getText(), "1"};

                    db.InsertQuestion(addQuestion, ref.buffer);
                    //saveBytesToFile(ref.buffer, "./src/main/resources/com/example/baitaplonoop/media/ShortVideo/Choice/output.mp4");
                    CheckDuration(mediaQuestion_mv);

                } else {
                    Image image = imageQuestion_iv.getImage();
                    int image_width = (int) image.getWidth();
                    int image_height = (int) image.getHeight();
                    byte[] buffer = new byte[image_height * image_width * 4];
                    image.getPixelReader().getPixels(0, 0, image_width, image_height, PixelFormat.getByteBgraInstance(), buffer, 0, image_width * 4);
                    String[] addQuestion = {IDCategoryQuestion, questionName_tf.getText(), questionText_tf.getText(), "1"};
                    db.InsertQuestion(addQuestion, buffer);
                }

                // To add Choice into Database
                if (!choice1_tf.getText().equals("")) {
                    String[] addChoice1 = {choice1_tf.getText(), String.valueOf(gradeChoice1), questionName_tf.getText() + "1", questionName_tf.getText(), null};
                    db.InsertChoice(addChoice1);
                }
                if (!choice2_tf.getText().equals("")) {
                    String[] addChoice2 = {choice2_tf.getText(), String.valueOf(gradeChoice2), questionName_tf.getText() + "2", questionName_tf.getText(), null};
                    db.InsertChoice(addChoice2);
                }
                if (!choice3_tf.getText().equals("")) {
                    String[] addChoice3 = {choice3_tf.getText(), String.valueOf(gradeChoice3), questionName_tf.getText() + "3", questionName_tf.getText(), null};
                    db.InsertChoice(addChoice3);
                }
                if (!choice4_tf.getText().equals("")) {
                    String[] addChoice4 = {choice4_tf.getText(), String.valueOf(gradeChoice4), questionName_tf.getText() + "4", questionName_tf.getText(), null};
                    db.InsertChoice(addChoice4);
                }
                if (!choice5_tf.getText().equals("")) {
                    String[] addChoice5 = {choice5_tf.getText(), String.valueOf(gradeChoice5), questionName_tf.getText() + "5", questionName_tf.getText(), null};
                    db.InsertChoice(addChoice5);
                }
                if (!choice6_tf.getText().equals("")) {
                    String[] addChoice6 = {choice1_tf.getText(), String.valueOf(gradeChoice1), questionName_tf.getText() + "6", questionName_tf.getText(), null};
                    db.InsertChoice(addChoice6);
                }


                alertAddCategory.setContentText("Add Question Done!");
                alertAddCategory.setHeaderText(null);
            }
            alertAddCategory.showAndWait();
        });
        // Event to creat new 3 choice
        createChoice_btn.setOnMouseClicked(createChoiceEvent -> {
            paneChoice2_ap.setTranslateY(239);
            buttonPane_ap.setTranslateY(239);
        });

    }

    private byte[] convertMediaPlayerToByteArray(MediaPlayer mediaPlayer) {
        try {
            // Tạo đối tượng ByteArrayOutputStream để ghi dữ liệu video vào
            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();

            // Đọc dữ liệu từ MediaPlayer và ghi vào ByteArrayOutputStream
            FileInputStream inputStream = new FileInputStream(mediaPlayer.getMedia().getSource());
            byte[] buffer = new byte[1024];
            int bytesRead;
            while ((bytesRead = inputStream.read(buffer)) != -1) {
                outputStream.write(buffer, 0, bytesRead);
            }

            // Trả về mảng byte[] của video
            return outputStream.toByteArray();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    private void saveBytesToFile(byte[] videoBytes, String outputPath) {
        try {
            // Tạo đối tượng FileOutputStream để ghi mảng byte[] vào file
            FileOutputStream outputStream = new FileOutputStream(outputPath);
            outputStream.write(videoBytes);
            outputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void loadImageQuestion(ActionEvent event) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Chọn ảnh");

        FileChooser.ExtensionFilter extFilterJPG = new FileChooser.ExtensionFilter("JPG files (*.jpg)", "*.JPG");
        FileChooser.ExtensionFilter extFilterPNG = new FileChooser.ExtensionFilter("PNG files (*.png)", "*.PNG");
        fileChooser.getExtensionFilters().addAll(extFilterJPG, extFilterPNG);

        // Show open file dialog
        File file = fileChooser.showOpenDialog(null);

        if (file != null) {
            try {
                // Tạo đối tượng Image từ file được chọn
                Image image = new Image(file.toURI().toString());
                // Tạo một đối tượng ImageView mới
                ImageView imageChoice_iv = new ImageView(image);
                // Đặt kích thước và tỉ lệ cho ImageView
                imageChoice_iv.setFitWidth(75);
                imageChoice_iv.setFitHeight(100);
                imageChoice_iv.setPreserveRatio(true);
                // Lấy đối tượng Button từ tham số event
                Button button = (Button) event.getSource();
                // Đặt ImageView bên cạnh button gọi hàm
                imageChoice_iv.setLayoutX(button.getLayoutX() + button.getWidth() + 10);
                imageChoice_iv.setLayoutY(button.getLayoutY());
                // Hiển thị ImageView
                imageChoice_iv.setVisible(true);
                // Thêm ImageView vào cùng pane với button
                Pane pane = (Pane) button.getParent();
                pane.getChildren().add(imageChoice_iv);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public void loadImageChoice(ActionEvent event) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Chọn ảnh");

        FileChooser.ExtensionFilter extFilterJPG = new FileChooser.ExtensionFilter("JPG files (*.jpg)", "*.JPG");
        FileChooser.ExtensionFilter extFilterPNG = new FileChooser.ExtensionFilter("PNG files (*.png)", "*.PNG");
        fileChooser.getExtensionFilters().addAll(extFilterJPG, extFilterPNG);

        // Show open file dialog
        File file = fileChooser.showOpenDialog(null);

        if (file != null) {
            try {
                // Tạo đối tượng Image từ file được chọn
                Image image = new Image(file.toURI().toString());
                // Hiển thị ảnh trong ImageView
                ImageView imageChoice_iv = new ImageView();
                imageChoice_iv.setImage(image);
                // Đặt ImageView bên cạnh button chọn sự kiện
                Button button = (Button) event.getSource();
                // Đặt ImageView bên cạnh button gọi hàm
                imageChoice_iv.setFitWidth(75);
                imageChoice_iv.setFitHeight(100);
                imageChoice_iv.setPreserveRatio(true);
                imageChoice_iv.setLayoutX(85);
                imageChoice_iv.setLayoutY(100);
                imageChoice_iv.setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

//    public void loadImageChoice(ActionEvent event) {
//        FileChooser fileChooser = new FileChooser();
//        fileChooser.setTitle("Chọn ảnh");
//
//        FileChooser.ExtensionFilter extFilterJPG = new FileChooser.ExtensionFilter("JPG files (*.jpg)", "*.JPG");
//        FileChooser.ExtensionFilter extFilterPNG = new FileChooser.ExtensionFilter("PNG files (*.png)", "*.PNG");
//        fileChooser.getExtensionFilters().addAll(extFilterJPG, extFilterPNG);
//
//        // Show open file dialog
//        File file = fileChooser.showOpenDialog(null);
//
//        if (file != null) {
//            try {
//                // Tạo đường dẫn tới thư mục "anh"
//                String imagePath = "./src/main/resources/com/example/baitaplonoop/media/Image/Choice" + file.getName();
//
//                // Đọc dữ liệu ảnh vào File
//                FileInputStream fis = new FileInputStream(file);
//                FileOutputStream fos = new FileOutputStream(imagePath);
//
//                // Đọc và ghi dữ liệu ảnh vào file ảnh trong thư mục "anh"
//                byte[] buffer = new byte[1024];
//                int length;
//                while ((length = fis.read(buffer)) > 0) {
//                    fos.write(buffer, 0, length);
//                }
//
//                // Đóng input/output stream
//                fis.close();
//                fos.close();
//
//                // Hiển thị ảnh trong ImageView
//                Image image = new Image(new File(imagePath).toURI().toString());
//                imageQuestion_iv.setImage(image);
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//        }
//    }

    public void loadGifQuestion(ActionEvent event) {
        // Tạo đối tượng FileChooser để chọn file ảnh
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Chọn file gif");

        // Thiết lập bộ lọc cho các loại file ảnh gif
        FileChooser.ExtensionFilter extFilterGIF = new FileChooser.ExtensionFilter("GIF files (*.gif)", "*.GIF");
        fileChooser.getExtensionFilters().add(extFilterGIF);

        // Hiển thị hộp thoại mở file ảnh
        File file = fileChooser.showOpenDialog(null);

        if (file != null) {
            try {
                // Tạo đối tượng Image từ file được chọn
                Image image = new Image(file.toURI().toString());
                // Hiển thị ảnh trong ImageView
                imageQuestion_iv.setImage(image);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }



    public void loadVideo(ActionEvent event) {
        // Tạo đối tượng FileChooser để chọn file video
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Chọn file video");

        // Thiết lập bộ lọc cho các loại file video mp4
        FileChooser.ExtensionFilter extFilterMP4 = new FileChooser.ExtensionFilter("MP4 files (*.mp4)", "*.MP4");
        fileChooser.getExtensionFilters().add(extFilterMP4);

        // Hiển thị hộp thoại mở file video
        File file = fileChooser.showOpenDialog(null);

        if (file != null) {
            try {
                // Tạo đối tượng Media từ file được chọn
                javafx.scene.media.Media media = new javafx.scene.media.Media(file.toURI().toString());
                // Tạo đối tượng MediaPlayer từ đối tượng Media
                MediaPlayer mediaPlayer = new MediaPlayer(media);
                // Hiển thị video trong MediaView
                mediaQuestion_mv.setMediaPlayer(mediaPlayer);

                // Điều chỉnh kích thước và vị trí của MediaView
                mediaQuestion_mv.setFitWidth(200); // Thiết lập chiều rộng của MediaView là 400 pixel
                mediaQuestion_mv.setFitHeight(100); // Thiết lập chiều cao của MediaView là 300 pixel
                mediaQuestion_mv.setPreserveRatio(true); // Giữ nguyên tỷ lệ khung hình của video

                // Bind the time slider to the media player's current time
                timeSlider.valueProperty().addListener((observable, oldValue, newValue) -> {
                    if (timeSlider.isValueChanging()) {
                        mediaPlayer.seek(Duration.seconds(newValue.doubleValue()));
                    }
                });
                mediaPlayer.currentTimeProperty().addListener((observable, oldValue, newValue) -> {
                    if (!timeSlider.isValueChanging()) {
                        timeSlider.setValue(newValue.toSeconds());
                    }
                });
                mediaPlayer.setOnReady(() -> timeSlider.setMax(mediaPlayer.getMedia().getDuration().toSeconds()));

                // Sử dụng phương thức setOnAction của nút playVideo để gọi phương thức play của MediaPlayer
                playVideo.setOnAction(e -> {
                    mediaPlayer.play();
                });

                pause_btn.setOnAction(event1 -> {
                    mediaPlayer.pause();
                });

                // Sử dụng phương thức setOnEndOfMedia của MediaPlayer để thiết lập hành động khi video kết thúc
                mediaPlayer.setOnEndOfMedia(() -> {
                    mediaPlayer.seek(Duration.ZERO); // Quay lại từ đầu
                    mediaPlayer.pause();
                });
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

//    public void loadVideo(ActionEvent event) {
//        // Tạo đối tượng FileChooser để chọn file video
//        FileChooser fileChooser = new FileChooser();
//        fileChooser.setTitle("Chọn file video");
//
//        // Thiết lập bộ lọc cho các loại file video mp4
//        FileChooser.ExtensionFilter extFilterMP4 = new FileChooser.ExtensionFilter("MP4 files (*.mp4)", "*.MP4");
//        fileChooser.getExtensionFilters().add(extFilterMP4);
//
//        // Hiển thị hộp thoại mở file video
//        File file = fileChooser.showOpenDialog(null);
//
//        if (file != null) {
//            try {
//                // Tạo đường dẫn tới thư mục "video"
//                String videoPath = "./src/main/resources/com/example/baitaplonoop/media/ShortVideo/Question/" + file.getName();
//                // Đọc dữ liệu video từ file vào đối tượng FileInputStream
//                FileInputStream fis = new FileInputStream(file);
//
//                // Ghi dữ liệu video vào file video trong thư mục "video"
//                FileOutputStream fos = new FileOutputStream(videoPath);
//                byte[] buffer = new byte[1024];
//                int length;
//                while ((length = fis.read(buffer)) > 0) {
//                    fos.write(buffer, 0, length);
//                }
//
//                // Đóng input/output stream
//                fis.close();
//                fos.close();
//
//                // Hiển thị video trong MediaView
//                javafx.scene.media.Media media = new javafx.scene.media.Media(new File(videoPath).toURI().toString());
//                MediaPlayer mediaPlayer = new MediaPlayer(media);
//                mediaQuestion_mv.setMediaPlayer(mediaPlayer);
//
//                // Điều chỉnh kích thước và vị trí của MediaView
//                mediaQuestion_mv.setFitWidth(200); // Thiết lập chiều rộng của MediaView là 400 pixel
//                mediaQuestion_mv.setFitHeight(100); // Thiết lập chiều cao của MediaView là 300 pixel
//                mediaQuestion_mv.setPreserveRatio(true); // Giữ nguyên tỷ lệ khung hình của video
//
//                // Bind the time slider to the media player's current time
//                timeSlider.valueProperty().addListener((observable, oldValue, newValue) -> {
//                    if (timeSlider.isValueChanging()) {
//                        mediaPlayer.seek(Duration.seconds(newValue.doubleValue()));
//                    }
//                });
//                mediaPlayer.currentTimeProperty().addListener((observable, oldValue, newValue) -> {
//                    if (!timeSlider.isValueChanging()) {
//                        timeSlider.setValue(newValue.toSeconds());
//                    }
//                });
//                mediaPlayer.setOnReady(() -> timeSlider.setMax(mediaPlayer.getMedia().getDuration().toSeconds()));
//
//                // Sử dụng phương thức setOnAction của nút playVideo để gọi phương thức play của MediaPlayer
//                playVideo.setOnAction(e -> {
//                    mediaPlayer.play();
//                });
//
//                pause_btn.setOnAction(event1 -> {
//                    mediaPlayer.pause();
//                });
//
//                // Sử dụng phương thức setOnEndOfMedia của MediaPlayer để thiết lập hành động khi video kết thúc
//                mediaPlayer.setOnEndOfMedia(() -> {
//                    mediaPlayer.seek(Duration.ZERO); // Quay lại từ đầu
//                    mediaPlayer.pause();
//                });
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//        }
//    }

    public void CheckDuration(MediaView mediaView) {
        MediaPlayer mediaPlayer = mediaView.getMediaPlayer();
        // Lấy độ dài của media tính bằng Duration
        Duration duration = mediaPlayer.getTotalDuration();
        // Chuyển đổi Duration sang giây
        double seconds = duration.toSeconds();
        System.out.println(seconds);
    }
}
