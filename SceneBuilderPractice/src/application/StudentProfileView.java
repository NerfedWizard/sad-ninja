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
//	@FXML
//	private Stage studentView;
	
	@FXML
	private AnchorPane root;
	@FXML
	private Scene scene;

	public StudentProfileView() {

	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		try { 
			primaryStage.setTitle(StudentController.getUserStudentNameForTitle());
			AnchorPane root = (AnchorPane) FXMLLoader.load(getClass().getResource("StudentProfileView.fxml"));
			scene = new Scene(root);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			primaryStage.setScene(scene);
			primaryStage.setX(300);
			primaryStage.setY(100);
			primaryStage.show();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		launch(args); 
	}
}
