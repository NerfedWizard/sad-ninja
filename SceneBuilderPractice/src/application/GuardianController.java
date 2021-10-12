package application;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextInputDialog;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;

/**
 * @author LoelN
 * 
 * 
 *         Same features as the student but with added benefit of checking on
 *         multiple students
 */
public class GuardianController implements Initializable {
	static String nameForTitle = "";
	private ResetPasswordView rpv;
	private Main main;
	@FXML
	private MenuItem logout;
	@FXML
	private MenuItem changLogin;
	@FXML
	private MenuItem checkGrades;
	@FXML
	private MenuItem classList;
	@FXML
	private MenuItem checkMessage;
	@FXML
	private MenuItem sendMessage;
	@FXML
	private TextArea textAreaLeft;
	@FXML
	private AnchorPane anchor;
	@FXML
	private Button messBtn;
	@FXML
	private ListView<String> students = new ListView<String>();
	private String sentUser = "";
	private TextInputDialog messagePopup;
	private TextArea messageArea;
	private static String guardianUsername;
	private Text textForFlowLeft = new Text();// For Output to the user
	private static String gID = "";

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		rpv = new ResetPasswordView();
		main = new Main();
		anchor.setVisible(false);

	}

	/**
	 * Gets the students linked to this guardian
	 */
	public void getStudents() {
		ObservableList<String> studList = FXCollections.observableArrayList();
		String student = MySQLAccess.returnQuery(
				"select username from user, gsr where student_id = user_id and guardian_id ='" + gID + "'", 1);
		for (String s : student.split("\\s+")) {
			studList.add(s);

		}
		students.setItems(studList);
	}

	/**
	 * @param name
	 */
	public static void setNameForTitle(String name) {

		guardianUsername = name;
		nameForTitle = MySQLAccess.getFirstName(name);
		gID = MySQLAccess.returnQuery("SELECT user_id from user where username ='" + guardianUsername + "'", 1);

	}

	/**
	 * @return NameForTitle
	 */
	public static String getUserGuardianNameForTitle() {
		return nameForTitle;
	}

	/**
	 * Change Login
	 */
	public void changeLogin() {
		try {
			ResetPasswordController.setUser(guardianUsername);
			rpv.start(Main.logStage);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Log out
	 */
	public void logout() {
		try {
			main.start(Main.logStage);// shows you can go to any view from any view if needed
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/** Checks the current selected students grade */
	public void checkGrades() {
		String score = "Score";
		String grade = "Grade";
		textForFlowLeft.setText(String.format("%-4s %-3s\n", score, grade) + MySQLAccess
				.returnQuery("select score, grade from assignment,user where user_id = student_id  and username ='"
						+ students.getSelectionModel().getSelectedItem() + "'", 2));
		changeTextFlow(textForFlowLeft);
	}

	/**
	 * Checks messages
	 */
	public void checkMessages() {
		String check = "\n" + MySQLAccess
				.returnQuery("SELECT date_received,cast(message_text as NCHAR) FROM message WHERE username ='"
						+ guardianUsername + "' order by date_received DESC limit 10", 2);
		textForFlowLeft.setText(check);
		changeTextFlow(textForFlowLeft);
	}

	/**
	 * Sends a message
	 */
	public void sendMess() {
		anchor.setVisible(true);
		messagePopup = new TextInputDialog();
		messagePopup.setHeaderText("Enter the Username you would like to send message to.");
		messageArea = new TextArea();
		messageArea.setWrapText(true);
		messageArea.setPromptText("Enter Message Here");
		messageArea.setPrefSize(307, 376);
		anchor.getChildren().add(messageArea);
		messagePopup.showAndWait();
		sentUser = messagePopup.getEditor().getText();
		if (sentUser.length() > 8 || sentUser.length() < 8) {
			messagePopup.setHeaderText("This is not a valid Username Please Try Again");
			messagePopup.showAndWait();
		}

	}

	/**
	 * Confirm message sent
	 */
	public void messageSent() {
//		sentUser = sentUser + "@p2k.com";
		String messageF = "FROM: " + guardianUsername + "\n" + messageArea.getText() + "\n\n";
		MySQLAccess.noReturnQuery(
				"insert into message (username,message_text) values('" + sentUser + "','" + messageF + "')");
		textForFlowLeft.setText("Message Sent\n" + sentUser);
		changeTextFlow(textForFlowLeft);
		messageArea.clear();
		anchor.setVisible(false);
	}

	/**
	 * Gets the current courses for the linked students
	 */
	public void courseList() {
		String course = MySQLAccess.returnQuery(
				"select course_name, assignment_name from assignment a, course c, user where student_id = user_id and c.course_id = a.course_id and username = '"
						+ students.getSelectionModel().getSelectedItem() + "'",
				2);

		ArrayList<String> splitCourse = new ArrayList<String>();
		for (String s : course.split("\\s+")) {
			splitCourse.add(s);
		}
		int i = splitCourse.get(1).length();
		System.out.println("Length of first entry: " + i + "\nThe string at index 0: " + splitCourse.get(0)
				+ "\nand the contents of course string: " + course + "\nSize of the list: " + splitCourse.size());
		textForFlowLeft.setText(course);
		changeTextFlow(textForFlowLeft);
	}

	/**
	 * Still needed for setting the text to user
	 * 
	 * @param textLeft
	 */
	public void changeTextFlow(Text textLeft) {
		textAreaLeft.setText(textLeft.getText());
	}

}
