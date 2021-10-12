package application;

import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * @author loeln
 *
 *         Teacher view
 */
public class TeacherView extends Application {
	@FXML
	private Stage teacherView;

	@FXML
	private AnchorPane root;
	@FXML
	private Scene scene;

	/**
	 * 
	 */
	public TeacherView() {

	}

	@Override
	public void start(Stage teacherView) throws Exception {
		try {
			teacherView.setTitle(TeacherController.getUserTeacherNameForTitle());
			AnchorPane root = (AnchorPane) FXMLLoader.load(getClass().getResource("TeacherView.fxml"));
			scene = new Scene(root);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			teacherView.setScene(scene);
			teacherView.setX(300);
			teacherView.setY(100);
			teacherView.show();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		launch(args);
	}
}
