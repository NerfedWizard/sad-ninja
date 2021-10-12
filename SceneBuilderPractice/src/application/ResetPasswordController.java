package application;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

/**
 * @author loeln
 *
 */
public class ResetPasswordController implements Initializable {
	@FXML
	private Label oldEntry;
	@FXML
	private Label newEntry;
	@FXML
	private Label newEntry2;
	@FXML
	private Label titleText;
	@FXML
	private TextField toBeChanged;
	@FXML
	private PasswordField newInsert;
	@FXML
	private PasswordField newInsert2;
	@FXML
	private Button submit;

	@FXML
	private MenuItem passwordItem;
	private String query = "";
	static Boolean flag = false;
	private Main main;
	static String userName = "";

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		main = new Main();
		oldEntry.setVisible(false);
		newEntry.setVisible(false);
		newEntry2.setVisible(false);
	}

	/**
	 * @param user
	 */
	public static void setUser(String user) {
		userName = user;
	}

	/**
	 * For changing password
	 */
	public void buttonSubmit() {

		if (newInsert.getText().length() == 8) {
			if (newInsert.getText().equals(newInsert2.getText())) {
				this.query = "UPDATE user SET password ='" + newInsert.getText() + "'where password ='"
						+ toBeChanged.getText() + "'and username ='" + userName + "';";
				MySQLAccess.noReturnQuery(this.query);
				flag = true;

			} else {
				newEntry.setVisible(true);
				newEntry.setText("Passwords do not match");
				flag = false;
			}
		} else {
			flag = false;
			newEntry.setVisible(true);
			newEntry.setText("Password must be 8 length");
		}

		if (flag) {
			try {
				main.start(Main.logStage);// shows you can go to any view from any view if needed
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
}
