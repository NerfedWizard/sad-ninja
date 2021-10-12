package application;

import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * Load the new stage in the controller and start the new controller to handle
 * the methods needed for the profileView
 */
public class StudentProfileView extends Application {
	@FXML
	private Stage studentView;
	@FXML
	private Scene studentScene;
	@FXML
	private AnchorPane root;

	public StudentProfileView() {

	}

	@Override
	public void start(Stage studentView) throws Exception {
		try {
			studentView = Main.getPrimaryStage();
			studentView.setTitle("Portal to Knowledge");
			root = (AnchorPane) FXMLLoader.load(getClass().getResource("StudentProfileView.fxml"));
			studentScene = new Scene(root);
			studentScene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			studentView.setScene(studentScene);
//			Main.primaryStage.close();
			studentView.show();
//			Main.getPrimaryStage().close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

//	public static void studentView() {
//		launch();
//
//	}
//	public static void main(String [] args) {
//		launch(args);
//	}
}
