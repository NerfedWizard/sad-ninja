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
 */
public class ResetPasswordView extends Application {
	@FXML
	private AnchorPane root;
	@FXML
	private Scene scene;
	@FXML
	static Stage resetStage;

	@Override
	public void start(Stage rStage) throws Exception {
		resetStage = rStage;
		try {

			resetStage.setTitle("Reset Password Username");
			AnchorPane root = (AnchorPane) FXMLLoader.load(getClass().getResource("ResetPassword.fxml"));
			scene = new Scene(root);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			resetStage.setScene(scene);
			resetStage.show();
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
