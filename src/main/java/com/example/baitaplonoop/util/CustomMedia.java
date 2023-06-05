package com.example.baitaplonoop.util;

import com.itextpdf.text.pdf.codec.GifImage;
import javafx.embed.swing.SwingFXUtils;
import javafx.scene.control.Button;
import javafx.scene.control.Slider;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.stage.FileChooser;
import javafx.util.Duration;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.net.URI;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

public class CustomMedia {
    // Load Media in Database when input is String in path (useful in load media in Question Text)
    public static void loadFile(String path, ImageView imageViewImage, ImageView imageViewGif, MediaView mediaViewVideo, AnchorPane mediaVideo_ap, Button playVideo, Button pause_btn, Slider timeSlider) {
        if (path == null || path.isEmpty()) return;
        int dotIndex = path.lastIndexOf(".");
        if (dotIndex == -1) return;
        String extension = path.substring(dotIndex);
        if (extension.equalsIgnoreCase(".png")) {
            Image image = new Image("file:" + path);
            imageViewImage.setImage(image);
            imageViewImage.setVisible(true);
            imageViewGif.setVisible(false);
            mediaViewVideo.setVisible(false);
        } else if (extension.equalsIgnoreCase(".gif")) {
            Image gif = new Image("file:" + path);
            imageViewGif.setImage(gif);
            imageViewGif.setVisible(true);
            imageViewImage.setVisible(false);
            mediaViewVideo.setVisible(false);
        } else if (extension.equalsIgnoreCase(".mp4")) {
            Path pathVideo = Paths.get(path);
            pathVideo = pathVideo.toAbsolutePath();
            Media media = new Media(pathVideo.toUri().toString());
            MediaPlayer mediaPlayer = new MediaPlayer(media);
            mediaViewVideo.setMediaPlayer(mediaPlayer);
            mediaVideo_ap.setVisible(true);
            imageViewImage.setVisible(false);
            imageViewGif.setVisible(false);
            setVideoPlay(mediaPlayer, timeSlider);
            playVideo.setOnAction(e -> mediaPlayer.play());
            pause_btn.setOnAction(e -> mediaPlayer.pause());
        }
    }

    //
    public static void setVideoPlay(MediaPlayer mediaPlayer, Slider timeSlider) {
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

    public static void loadImage(String path, ImageView imageView) {
        if (path == null || path.isEmpty()) {
            return;
        }
        try {
            Image image = new Image(new FileInputStream(path));
            imageView.setImage(image);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static void AddImageToImageView(ImageView imageView) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("JPG files (*.jpg)", "*.jpg"),
                new FileChooser.ExtensionFilter("PNG files (*.png)", "*.png")
        );
        File file = fileChooser.showOpenDialog(null);
        if (file != null) {
            String url1 = file.toURI().toString();
            Image image = new Image(url1);
            imageView.setImage(image);
        }
    }

    public static void AddGifToImageView(ImageView imageView) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.getExtensionFilters().add(
                new FileChooser.ExtensionFilter("JPG files (*.gif)", "*.gif")
        );
        File file = fileChooser.showOpenDialog(null);
        if (file != null) {
            String url1 = file.toURI().toString();
            Image image = new Image(url1);
            imageView.setImage(image);
        }
    }

    public static boolean CheckDuration(MediaView mediaView) {
        MediaPlayer mediaPlayer = mediaView.getMediaPlayer();
        Duration duration = mediaPlayer.getTotalDuration();
        double seconds = duration.toSeconds();
        return 1 <= seconds & seconds <= 20;
    } // Check Duration Video in Question between 1s and 10s

    public static String saveImage(ImageView imageView, String pathImage, String imageID) {
        String questionMediaPath = null;
        try {
            File target = new File(pathImage + File.separator + imageID + ".png");
            BufferedImage toWrite = SwingFXUtils.fromFXImage(imageView.getImage(), null);
            ImageIO.write(toWrite, "png", target);
            questionMediaPath = pathImage + File.separator + imageID + ".png";
        } catch (Exception x) {
            System.err.println("Failed to save image");
            x.printStackTrace();
        }
        return questionMediaPath;
    }

        public static String saveGif(ImageView imageView, String pathGIF, String questionID) {
        String questionGifPah = null;
        try {
            File target = new File(pathGIF + File.separator + questionID + ".gif");
            //questionGifPah = target.toURI().toString();

            BufferedImage toWrite = SwingFXUtils.fromFXImage(imageView.getImage(), null);
            ImageIO.write(toWrite, "gif", target);
            questionGifPah = target.getAbsolutePath();
        } catch (Exception x) {
            System.err.println("Failed to save gif");
            x.printStackTrace();
        }
        return questionGifPah;
    }   // Save GIF in the local memory


    public static String saveVideo(MediaView mediaView, String pathVideo, String questionID) {
        String questionVideoPath = null;
        if (CustomMedia.CheckDuration(mediaView)) {
            try {
                MediaPlayer mediaPlayer = mediaView.getMediaPlayer();
                Media media = mediaPlayer.getMedia();
                String source = media.getSource();
                File sourceFile = new File(new URI(source));
                File targetFile = new File(pathVideo + File.separator + questionID + ".mp4");
                Files.copy(sourceFile.toPath(), targetFile.toPath(), StandardCopyOption.REPLACE_EXISTING);
                questionVideoPath = pathVideo + File.separator + questionID + ".mp4";
            } catch (Exception e) {
                System.err.println("Failed to save video");
                e.printStackTrace();
            }
        }
        return questionVideoPath;
    }
//    public static String saveGifnew(ImageView imageView, String path, String name) {
//        String gifPath = null;
//        try {
//            // Chuyển đổi ảnh từ kiểu Image sang kiểu BufferedImage
//            BufferedImage image = SwingFXUtils.fromFXImage(imageView.getImage(), null);
//            // Nối tên file với đường dẫn của thư mục và thêm đuôi ".gif"
//            String fileName = path + File.separator + name + ".gif";
//            // Tạo một đối tượng File với tên file đã cho
//            File file = new File(fileName);
//            // Ghi ảnh vào file với định dạng gif
//            ImageIO.write(image, "gif", file);
//            // Lấy đường dẫn tuyệt đối của file
//            gifPath = file.getAbsolutePath();
//        } catch (Exception e) {
//            System.err.println("Failed to save gif");
//            e.printStackTrace();
//        }
//        return gifPath;
//    }

}
