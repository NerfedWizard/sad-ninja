package application;

import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class AdminView extends Application {

	@FXML
	private Scene scene;
	@FXML
	private AnchorPane root;

	public AdminView() {
	}

	@Override
	public void start(Stage adminStage) throws Exception {
		try {
			adminStage.setTitle(AdminController.getUserAdminNameForTitle());
			AnchorPane root = (AnchorPane) FXMLLoader.load(getClass().getResource("AdminView.fxml"));
			scene = new Scene(root);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			adminStage.setScene(scene);
			adminStage.show();
		} catch (Exception e) {
			e.printStackTrace(); 
		}
	}

	public static void main(String[] args) {
		launch(args);
	}

}
