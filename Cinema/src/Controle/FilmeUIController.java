
package Controle;

import cinema.Cinema;
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
import javafx.scene.control.MenuItem;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;


public class FilmeUIController implements Initializable {

    @FXML
    private Button BtnVoltarPrincipal;
    @FXML
    private Button MenuItemCadastroFilme;
    @FXML
    private Button MenuItemListaFilme;
    @FXML
    private MenuItem MenuItemBuscaFilmeNome;
    @FXML
    private MenuItem MenuItemBuscaFilmeCod;
    @FXML
    private Button MenuItemDeletaFilme;
    @FXML
    private Button MenuItemAtualizaFilme;
    @FXML
    private AnchorPane AnchorPaneFilme;
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        Stage stage = new Stage();
        Parent root=null;
        try {
            root = FXMLLoader.load(Cinema.class.getResource("/view/AnchorPaneBackgroundFilme.fxml"));
        } catch (IOException ex) {
            Logger.getLogger(FilmeUIController.class.getName()).log(Level.SEVERE, null, ex);
        }
        AnchorPaneFilme.getChildren().setAll(root);
    }    
        @FXML
    public void HandleBtnVoltar(ActionEvent event) throws IOException{
        Node node = (Node) event.getSource();

        Stage stage = (Stage) node.getScene().getWindow();
        Parent root = null;
    try {
        root = FXMLLoader.load(getClass().getResource("/view/MainUI.fxml"));
    } catch (IOException ex) {
        Logger.getLogger(FilmeUIController.class.getName()).log(Level.SEVERE, null, ex);
    }
    Scene scene = new Scene(root);
    stage.setScene(scene);
    stage.show();
    }
    public void HandleMenuItemDeletar(ActionEvent event) throws IOException{
        Stage stage = new Stage();
        Parent root = FXMLLoader.load(Cinema.class.getResource("/view/AnchorPaneDeletarFilme.fxml"));
        AnchorPaneFilme.getChildren().setAll(root);
    }
    public void HandleMenuItemVisualizar(ActionEvent event) throws IOException{
        Stage stage = new Stage();
        Parent root = FXMLLoader.load(Cinema.class.getResource("/view/AnchorPaneVisualizarFilme.fxml"));
        AnchorPaneFilme.getChildren().setAll(root);
    }
    public void HandleMenuItemCadastrar(ActionEvent event) throws IOException{
        Stage stage = new Stage();
        Parent root = FXMLLoader.load(Cinema.class.getResource("/view/AnchorPaneCadastrarFilme.fxml"));
        AnchorPaneFilme.getChildren().setAll(root);
    }
    public void HandleMenuItemAtualizar(ActionEvent event) throws IOException{
        Stage stage = new Stage();
        Parent root = FXMLLoader.load(Cinema.class.getResource("/view/AnchorPaneAtualizarFilme.fxml"));
        AnchorPaneFilme.getChildren().setAll(root);
    }
    public void HandleMenuItemBuscaPorNome(ActionEvent event) throws IOException{
        Stage stage = new Stage();
        Parent root = FXMLLoader.load(Cinema.class.getResource("/view/AnchorPaneBuscaNome.fxml"));
        AnchorPaneFilme.getChildren().setAll(root);
    }
}
