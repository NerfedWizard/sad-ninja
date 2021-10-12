package application;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextInputDialog;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;

/**
 * @author Loel Nelson
 * 
 *         Controller to be used for the student profile Handles all the
 *         functionality of the student view with redirects to changing password
 *         and logging out. Both ultimately bring you back to the login.
 */
public class StudentController implements Initializable {

	@FXML
	private MenuItem materialsMenuItem;
	@FXML
	private TextFlow textFlow;
	@FXML
	private TextArea textAreaLeft;
	@FXML
	private MenuItem changePass;
	@FXML
	private Menu courseMenu;
	@FXML
	private Button sendMessage;
	@FXML
	private AnchorPane anchor;

	private String sentUser = "";
	static String firstName;
	static String userType;
	static String sID = "";
	private static String studUsername;

	private TextArea messageArea;
	private TextInputDialog messagePopup;
	private Text textForFlowLeft = new Text();// For Output to the user

	private Main main;
	private ResetPasswordView rpv;

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		main = new Main();
		rpv = new ResetPasswordView();
		anchor.setVisible(false);
	}

	/**
	 * The following 2 methods are shared throughout looking back I would rather
	 * make a separate class the calls these methods. Maybe when updated I will
	 * change these around
	 */

	/** Starting the change password window */
	public void updateLogin() {
		try {
			ResetPasswordController.setUser(studUsername);
			rpv.start(Main.logStage);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/** Send student back to login */
	public void studentLogout() {
		try {
			main.start(Main.logStage);// shows you can go to any view from any view if needed
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Setting the title for the Students window
	 * 
	 * @param userName - the username of the student is needed to get first name for
	 *                 title of student view. This method is called when the user
	 *                 signs in from the login window
	 */
	public static void setNameForTitle(String userName) {
		StudentController.setUsername(userName);
		StudentController.firstName = MySQLAccess
				.returnQuery("SELECT first_name FROM user where username='" + userName + "'", 1);
	}

	/**
	 * Checks the last 10 messages sent to the user in chronological order. A simple
	 * delete of the limit 10 would allow receiving all the messages.
	 */
	public void checkMessages() {
		String check = "\n" + MySQLAccess
				.returnQuery("SELECT date_received,cast(message_text as NCHAR) FROM message WHERE username ='"
						+ studUsername + "' order by date_received DESC limit 10", 2);
		textForFlowLeft.setText(check);
		changeTextFlow(textForFlowLeft);
	}

	/**
	 * Start of sending a message. It opens up the AnchorPane where the TextArea is
	 * and a popup asks for the username you wish to send the message to. The popup
	 * was not needed I could have had a textfield but I found the popup while
	 * looking for other JavaFX built ins and thought it would be fun. This only
	 * gathers the information, the actual sending of the message doesn't happen
	 * until you hit send and that calls the method message sent
	 **/
	public void sendMessage() {
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
	 * Sends the message to the user and prompts the user when message is sent in
	 * the main TextArea
	 */
	public void messageSent() {
		String messageF = "FROM: " + studUsername + "\n" + messageArea.getText() + "\n\n";
		MySQLAccess.noReturnQuery(
				"insert into message (username,message_text) values('" + sentUser + "','" + messageF + "')");
		textForFlowLeft.setText("Message Sent\n" + sentUser);
		changeTextFlow(textForFlowLeft);
		messageArea.clear();
		anchor.setVisible(false);
	}

	/**
	 * Adds the first name of user to the top of the window. No real purpose
	 * strictly personal preference
	 * 
	 * @return StudentController.firstName
	 */
	public static String getUserStudentNameForTitle() {
		return StudentController.firstName;
	}

	/**
	 * Used for sending the text back to the user via TextArea, which I made to look
	 * like a Linux terminal just because I could.
	 * 
	 * @param textLeft
	 */
	public void changeTextFlow(Text textLeft) {
		textAreaLeft.setText(textLeft.getText());
	}

	/** Prints the students score percent and letter grade to the TextArea */
	public void getGrades() {
		anchor.setVisible(false);
		String score = "Score";
		String grade = "Grade";
		textForFlowLeft.setText(String.format("%-4s %-3s\n", score, grade)
				+ MySQLAccess.returnQuery("select score, grade from assignment a, course c where a.student_id ='" + sID
						+ "' and a.course_id = c.course_id", 2));
		changeTextFlow(textForFlowLeft);
	}

	/** Returns the assignment to the student */
	public void getAssignments() {
		String name = "Name";
		String dueDate = "Due Date";
		anchor.setVisible(false);
		textForFlowLeft.setText(String.format("%-15s %4s\n", name, dueDate) + MySQLAccess
				.returnQuery("select assignment_name,deadline from assignment where student_id ='" + sID + "'", 2));
		changeTextFlow(textForFlowLeft);
	}

	/**
	 * Gets the course description and prints back to user
	 */
	public void getMaterials() {
		String x = course();
		String materials = MySQLAccess
				.returnQuery("select cast(overview as NCHAR) from course where course_name = '" + x + "'", 1);
		textForFlowLeft.setText(course() + " Course Description:\n" + materials);
		changeTextFlow(textForFlowLeft);

	}

	/**
	 * For returning the username of the student currently signed in
	 * 
	 * @return studUsername
	 */
	public static String getUsername() {
		return studUsername;
	}

	/**
	 * Sets the username and provides the students ID
	 * 
	 * @param username
	 */
	public static void setUsername(String username) {
		StudentController.studUsername = username;
		sID = MySQLAccess.returnQuery("SELECT user_id from user where username ='" + username + "'", 1);
	}

	/**
	 * Gets the current active course for the student
	 * 
	 * @return courseQ.trim() - course trimmed of the whitespace
	 */
	public String course() {
		String courseQ = MySQLAccess.returnQuery(
				"select course_name from course c, assignment a where c.course_id = a.course_id and student_id = "
						+ StudentController.sID,
				1);
		return courseQ.trim();
	}

	/**
	 * Used for updating the the menu item to the current course name instead of the
	 * generic course
	 */
	public void checkCourse() {
		String courseQ = "select course_name from course c, assignment a where c.course_id = a.course_id and student_id = "
				+ StudentController.sID;
		courseMenu.setOnShown(new EventHandler<Event>() {
			@Override
			public void handle(Event arg0) {
				courseMenu.setText(MySQLAccess.returnQuery(courseQ, 1));
			}
		});
	}
}
