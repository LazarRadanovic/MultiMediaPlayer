package application;

import java.io.File;
import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.util.Duration;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.scene.media.MediaPlayer.Status;
import javafx.scene.paint.Color;
import javafx.scene.control.Button;

public class Controller {

	@FXML
	ImageView ivFile;
	@FXML
	MediaView mvFile;
	@FXML
	Button PlayButton;

	@FXML
	public void open_image_file(ActionEvent e) {
		mvFile.setMediaPlayer(null);
		FileChooser fileChooser = new FileChooser();
		fileChooser.setTitle("My file chooser:");
		fileChooser.setInitialDirectory(new File(System.getProperty("user.home")));
		fileChooser.getExtensionFilters().clear();
		fileChooser.getExtensionFilters()
				.add(new FileChooser.ExtensionFilter("Image Files", "*.jpg*", "*.png*", "*,gif*"));
		File f = fileChooser.showOpenDialog(null);
		if (f != null) {
			ivFile.setImage(new Image(f.toURI().toString()));
//			ivFile.setFitWidth(544.0);
//			ivFile.setFitHeight(310.0);
			ivFile.autosize();

		} else {
			System.out.println("Invalid file");

		}

	}

	@FXML
	public void open_video_file(ActionEvent e) {
		mvFile.setMediaPlayer(null);
		ivFile.setImage(null);
		FileChooser fileChooser = new FileChooser();
		fileChooser.setTitle("My file chooser:");
		fileChooser.setInitialDirectory(new File(System.getProperty("user.home")));
		fileChooser.getExtensionFilters().clear();
		fileChooser.getExtensionFilters()
				.add(new FileChooser.ExtensionFilter("Video Files", "*.mp4*", "*.avi*", "*.wmv*", "*.mov*", "*.mkv*"));
		File f = fileChooser.showOpenDialog(null);
//		if (status == status.PLAYING) {
//			mvFile.getMediaPlayer().stop();
//		} else
		if (f != null) {
			Media media = new Media(f.toURI().toString());
			MediaPlayer mp = new MediaPlayer(media);
			mvFile.setMediaPlayer(mp);
			mvFile.autosize();
			mp.play();
		} else {
			System.out.println("Invalid file");
		}

	}

	@FXML
	public void open_music_file(ActionEvent e) {
		ivFile.setImage(null);
		FileChooser fileChooser = new FileChooser();
		fileChooser.setTitle("My file chooser:");
		fileChooser.setInitialDirectory(new File(System.getProperty("user.home")));
		fileChooser.getExtensionFilters().clear();
		fileChooser.getExtensionFilters().add(
				new FileChooser.ExtensionFilter("Music Files", "*.mp3*", "*.wav*", "*.flac*", "*.aiff*", "*.ogg*"));
		File f = fileChooser.showOpenDialog(null);
		if (f != null) {
			ivFile.setImage(new Image(getClass().getResourceAsStream("projectMusic.jpg")));
//			ivFile.setFitWidth(544.0);
//			ivFile.setFitHeight(310.0);
			ivFile.autosize();
			Media media = new Media(f.toURI().toString());
			MediaPlayer mp = new MediaPlayer(media);
			mvFile.setMediaPlayer(mp);
			mp.setVolume(100.0);
			mp.play();

		} else {
			System.out.println("Invalid file");

		}
	}

	@FXML
	public void mute(ActionEvent e) {
		mvFile.getMediaPlayer().setVolume(0);
	}

	@FXML
	public void unMute(ActionEvent e) {
		mvFile.getMediaPlayer().setVolume(100.0);
	}

	public void handle(ActionEvent e) {
		Status status = mvFile.getMediaPlayer().getStatus(); // To get the status of Player
		if (status == status.PLAYING) {
			if (mvFile.getMediaPlayer().getCurrentTime()
					.greaterThanOrEqualTo(mvFile.getMediaPlayer().getTotalDuration())) {
				mvFile.getMediaPlayer().seek(mvFile.getMediaPlayer().getStartTime()); // Restart the video
				mvFile.getMediaPlayer().play();
			} else {
				mvFile.getMediaPlayer().pause();

				PlayButton.setText(">");
			}
		}
		if (status == Status.HALTED || status == Status.STOPPED || status == Status.PAUSED) {
			mvFile.getMediaPlayer().play();
			PlayButton.setText("||");
		}
	}

}