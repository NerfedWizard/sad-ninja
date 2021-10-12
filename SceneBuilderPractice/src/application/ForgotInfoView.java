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
 *The forgot password view 
 */
public class ForgotInfoView extends Application{

	@FXML
	private AnchorPane root;
	@FXML
	private Scene scene;
	@FXML
	static Stage forgotStage;

	@Override
	public void start(Stage fStage) throws Exception {
		forgotStage = fStage;
		try {
			
			forgotStage.setTitle("Forgot Username/Password");
			AnchorPane root = (AnchorPane) FXMLLoader.load(getClass().getResource("ForgotInfoView.fxml"));
			scene = new Scene(root);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			forgotStage.setScene(scene);
			forgotStage.show();
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
