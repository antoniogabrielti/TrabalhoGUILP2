
package Controle;

import Controle.AnchorPaneBuscaNomeController;
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
import javafx.scene.control.ToggleButton;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;


public class SalaUIController implements Initializable {

    @FXML
    private Button BtnVoltarPrincipal;
    @FXML
    private Button MenuItemCadastroSala;
    @FXML
    private Button MenuItemListaSala;
    @FXML
    private MenuItem MenuItemBuscaSalaNumero;
    @FXML
    private MenuItem MenuItemBuscaSalaCapacidade;
    @FXML
    private Button MenuItemDeletaSala;
    @FXML
    private Button MenuItemAtualizaSala;
    @FXML
    private AnchorPane AnchorPaneSala;
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        Stage stage = new Stage();
        Parent root=null;
        try {
            root = FXMLLoader.load(Cinema.class.getResource("/view/AnchorPaneBackgroundSala.fxml"));
        } catch (IOException ex) {
            Logger.getLogger(SalaUIController.class.getName()).log(Level.SEVERE, null, ex);
        }
        AnchorPaneSala.getChildren().setAll(root);
    }    
        @FXML
    public void HandleBtnVoltar(ActionEvent event) throws IOException{
        Node node = (Node) event.getSource();

        Stage stage = (Stage) node.getScene().getWindow();
        Parent root = null;
    try {
        root = FXMLLoader.load(getClass().getResource("/view/MainUI.fxml"));
    } catch (IOException ex) {
        Logger.getLogger(SalaUIController.class.getName()).log(Level.SEVERE, null, ex);
    }
    Scene scene = new Scene(root);
    stage.setScene(scene);
    stage.show();
    }
    public void HandleMenuItemDeletar(ActionEvent event) throws IOException{
        Stage stage = new Stage();
        Parent root = FXMLLoader.load(Cinema.class.getResource("/view/AnchorPaneDeletarSala.fxml"));
        AnchorPaneSala.getChildren().setAll(root);
    }
    public void HandleMenuItemVisualizar(ActionEvent event) throws IOException{
        Stage stage = new Stage();
        Parent root = FXMLLoader.load(Cinema.class.getResource("/view/AnchorPaneVisualizarSala.fxml"));
        AnchorPaneSala.getChildren().setAll(root);
    }
    public void HandleMenuItemCadastrar(ActionEvent event) throws IOException{
        Stage stage = new Stage();
        Parent root = FXMLLoader.load(Cinema.class.getResource("/view/AnchorPaneCadastrarSala.fxml"));
        AnchorPaneSala.getChildren().setAll(root);
    }
    public void HandleMenuItemAtualizar(ActionEvent event) throws IOException{
        Stage stage = new Stage();
        Parent root = FXMLLoader.load(Cinema.class.getResource("/view/AnchorPaneAtualizarFilme.fxml"));
        AnchorPaneSala.getChildren().setAll(root);
    }
    public void HandleMenuItemBuscaPorNome(ActionEvent event) throws IOException{
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(AnchorPaneBuscaNomeController.class.getResource("/view/AnchorPaneBuscaNome.fxml"));
        Parent page = (Parent) loader.load();

        // Criando um Estágio de Diálogo (Stage Dialog)
        Stage dialogStage = new Stage();
        dialogStage.setTitle("Excluir Filme");
        Scene scene = new Scene(page);
        dialogStage.setScene(scene);

        // Setando o cliente no Controller.
        AnchorPaneBuscaNomeController controller = loader.getController();
        controller.setToggleBtnNomeFilme();
        AnchorPaneSala.getChildren().setAll(page);
    }
        public void HandleMenuItemBuscaPorCodigo(ActionEvent event) throws IOException{
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(AnchorPaneBuscaNomeController.class.getResource("/view/AnchorPaneBuscaNome.fxml"));
        Parent page = (Parent) loader.load();

        // Criando um Estágio de Diálogo (Stage Dialog)
        Stage dialogStage = new Stage();
        dialogStage.setTitle("Excluir Filme");
        Scene scene = new Scene(page);
        dialogStage.setScene(scene);

        // Setando o cliente no Controller.
        AnchorPaneBuscaNomeController controller = loader.getController();
        controller.setToggleBtnCodigoFilme();
        AnchorPaneSala.getChildren().setAll(page);
    }
}
