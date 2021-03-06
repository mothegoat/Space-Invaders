package Controllers;

import Models.User;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;

public class Login {
    public Label errorLabel;
    public TextField password;
    public TextField username;

    public void loginAction(MouseEvent mouseEvent) throws Exception {
        String usernameText = username.getText();
        String passwordText = password.getText();
        if(usernameText.equals(""))
        {
            errorLabel.setText("Enter a username");
        }
        if(passwordText.equals(""))
        {
            errorLabel.setText("Enter a password");
        }
        if(!User.userExists(usernameText))
        {
            errorLabel.setText("This username does not exist!");
        }
        else
        {
            try {
                MainController.getInstance().login(usernameText,passwordText);
                errorLabel.setText("");
                Stage stage = (Stage)((Node)mouseEvent.getSource()).getScene().getWindow();
                Parent pane = FXMLLoader.load(getClass().getResource("/Views/MainMenu.fxml"));
                Scene mainMenuScene = new Scene(pane, 800, 800);
                MainController.getInstance().setMainMenuScene(mainMenuScene);
                stage.setScene(mainMenuScene);
                stage.show();
            }catch (Exception e)
            {
                errorLabel.setText(e.getMessage());
            }
        }
        username.clear();
        password.clear();
    }

    public void goBack(MouseEvent mouseEvent) throws IOException {
        username.clear();
        password.clear();
        errorLabel.setText("");
        Stage stage = (Stage)((Node)mouseEvent.getSource()).getScene().getWindow();
        stage.setScene(MainController.getInstance().getStartScene());
        stage.show();
    }
}
