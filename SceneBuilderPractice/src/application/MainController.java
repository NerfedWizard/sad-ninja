package application;


import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.control.PasswordField;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;

/**
 * @author Loel Nelson
 * 
 *         The main starting controller. Controller for the login window and
 *         directs users of the certain type to the correct view
 */
public class MainController implements Initializable {
	@FXML
	private PasswordField passwordTextField;
	@FXML
	private TextField usernameTextField;
	@FXML
	private Button submitButton;
	@FXML
	private Label textShowLabel;
	@FXML
	private RadioButton forgotPasswordRadioButton;
	@FXML
	private MenuButton userDropDownMenuButton;
	@FXML
	private MenuItem teacherMenuItem;
	@FXML
	private MenuItem studentMenuItem;
	@FXML
	private MenuItem guardianMenuItem;
	@FXML
	private MenuItem adminMenuItem;
	@FXML
	private MenuItem resetPasswordMenuItem;
	@FXML
	private MenuItem facultyMenuItem;
	private StudentProfileView studentView;
	private AdminView adminView;
	private TeacherView teacherView;

	private GuardianView guardianView;
	private ForgotInfoView forgot;

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		studentView = new StudentProfileView();
		adminView = new AdminView();
		teacherView = new TeacherView();

		guardianView = new GuardianView();
		forgot = new ForgotInfoView();

		passwordTextField.setOnKeyPressed(new EventHandler<KeyEvent>() {
			@Override
			public void handle(KeyEvent ke) {
				if (ke.getCode().equals(KeyCode.ENTER)) {
					userLogin();
				}
			}
		});

	}

	/**
	 * Method for the hyperlink on the bottom of the login window. Once clicked it
	 * sends you to a new window where you enter a verified email. After you have to
	 * exit the window manually. Tried using thread sleep after pushing submit
	 * button but then it wouldn't display the confirmation text that message was
	 * sent.
	 */
	public void forgotInfo() {
		Stage stage = (Stage) submitButton.getScene().getWindow();
		try {
			forgot.start(stage);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Method is called either by hitting enter in the password field or clicking
	 * the submit button which is done through the FXML file. Calls a method from
	 * the database to get username and passwords for every user and checks those
	 * next to the provided information. After successful login it queries the
	 * database again for the user_type and directs them to the proper view.
	 */

	public void userLogin() {
		Stage stage = (Stage) submitButton.getScene().getWindow();// same stage is used throughout just put a new scene
																	// over it.

		for (String s : MySQLAccess.getUsername()) {

			for (String p : MySQLAccess.getPassword()) {
				if (usernameTextField.getText().equals(s)) {
					if (passwordTextField.getText().equals(p)) {
						textShowLabel.setText("Success!");
						String userType = MySQLAccess
								.returnQuery("select user_type from user where username ='" + s + "'", 1).trim();
						if (userType.equals("Student")) {
							StudentController.setNameForTitle(s);

							try {
								studentView.start(stage);
							} catch (Exception e) {
								e.printStackTrace();
							}
						} else if (userType.equals("Admin")) {
							AdminController.setNameForTitle(s);
							try {
								adminView.start(stage);
							} catch (Exception e) {
								e.printStackTrace();
							}
						} else if (userType.equals("Teacher")) {
							TeacherController.setNameForTitle(s);
							try {
								teacherView.start(stage);
							} catch (Exception e) {
								e.printStackTrace();
							}
						} else if (userType.equals("Guardian")) {
							GuardianController.setNameForTitle(s);
							try {
								guardianView.start(stage);
							} catch (Exception e) {

								e.printStackTrace();
							}
						} else {
							textShowLabel.setText("Please Select A User");
						}

					} else {
						textShowLabel.setText("Invalid Password");// Does not pick up on this no matter what field is
																	// wrong it always defaults to invalid username;
					}
				} else {
					textShowLabel.setText("Invalid UserName");
				}
			}
		}
	}
}
