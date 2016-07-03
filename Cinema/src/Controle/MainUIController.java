
package Controle;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;


public class MainUIController implements Initializable {
     @FXML
     private Button BtnMenuFilme;
     @FXML
     private Button BtnMenuSala;
     @FXML
     private Button BtnMenuSecao;
     @FXML
     private Button BtnMenuVenda;
     @FXML
     private AnchorPane anchorPane;
   
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    @FXML
    public void HandleBtnMenuFilme(ActionEvent event) throws IOException{
        Node node = (Node) event.getSource();

        Stage stage = (Stage) node.getScene().getWindow();
        Parent root = null;
    try {
        root = FXMLLoader.load(getClass().getResource("/view/FilmeUI.fxml"));
    } catch (IOException ex) {
        Logger.getLogger(FilmeUIController.class.getName()).log(Level.SEVERE, null, ex);
    }
    Scene scene = new Scene(root);
    stage.setScene(scene);
    stage.show();
    }
}
