package application;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.control.PasswordField;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class MainController implements Initializable {
	@FXML
	private PasswordField passwordTextField;
	@FXML
	private TextField usernameTextField;
	@FXML
	private Hyperlink forgotPasswordUsernameHyperLink;
	@FXML
	private Button submitButton;
	@FXML
	private TextField successFailTextField;
	@FXML
	private Label textShowLabel;
	@FXML
	private RadioButton forgotPasswordRadioButton;
	@FXML
	private Label wrongTryLabel;
	@FXML
	private MenuButton userDropDownMenuButton;
	@FXML
	private MenuItem teacherMenuItem;
	@FXML
	private MenuItem studentMenuItem;
	@FXML
	private MenuItem guardianMenuItem;
	@FXML
	private MenuItem resetPasswordMenuItem;
	private String userSelection = "";
	@FXML
	private Stage primaryStage;
	@FXML
	private Stage resetStage;
	@FXML
	private Scene resetScene;
	@FXML
	private Stage profileStage;
	@FXML
	private AnchorPane resetRoot;
	@FXML
	private AnchorPane profilePane;
	@FXML
	private Scene loginScene;
	@FXML
	private Scene profileScene;
	private boolean successfulLogin = false;

//	private ArrayList<String> userNames = new ArrayList<String>(); 

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		primaryStage = Main.getPrimaryStage();
		loginScene = Main.getScene();
		textShowLabel.setText("Enter Information");
		EventHandler<ActionEvent> event1 = new EventHandler<ActionEvent>() {
			public void handle(ActionEvent e) {
				setUserSelection(((MenuItem) e.getSource()).getText());

			}
		};

		studentMenuItem.setOnAction(event1);
		guardianMenuItem.setOnAction(event1);
		teacherMenuItem.setOnAction(event1);
	/** Announcements Unofficial */

	}


	public void userLogin() {

		if (usernameTextField.getText().equals("Loel")) {
			if (passwordTextField.getText().equals("123")) {
				textShowLabel.setText("Success!");
				profileView();
//				setSuccessfulLogin(true);

			} else {
				textShowLabel.setText("Invalid Password");

			}
		} else {
			textShowLabel.setText("Invalid UserName");
		}

	}

	public String getUserSelection() {
		return userSelection;
	}

	public void profileView() {
		
		StudentProfileView spv = new StudentProfileView();
		try {
			spv.start(profileStage);
		} catch (Exception e) {
			
			e.printStackTrace();
		}

	}

	public void setUserSelection(String userSelection) {
		this.userSelection = userSelection;
	}

	public boolean isSuccessfulLogin() {
		return successfulLogin;
	}

	public void setSuccessfulLogin(boolean successfulLogin) {
		this.successfulLogin = successfulLogin;
	}
}
