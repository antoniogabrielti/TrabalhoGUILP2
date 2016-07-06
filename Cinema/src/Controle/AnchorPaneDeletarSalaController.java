
package Controle;

import cinema.Cinema;
import dominio.Sala;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;
import static javax.management.Query.value;
import negocio.NegocioException;
import negocio.SalaNegocio;
import static javax.management.Query.value;


public class AnchorPaneDeletarSalaController implements Initializable {
@FXML
private TableView<Sala> tableViewSala;
@FXML
private TableColumn<Sala,String> TableColumnNumeroSala;
@FXML
private TableColumn<Sala,String> TableColumnCapacidadeSala;
@FXML
private Button BtnDeletaSala;
@FXML
private Label LabelNumeroSala;
@FXML
private Label LabelCapacidadeSala;



private List<Sala> listaSalas;
private ObservableList<Sala> observableListSalas;
SalaNegocio SN = new SalaNegocio();

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        carregarTableViewSala();
        this.tableViewSala.getSelectionModel().selectedItemProperty().addListener((observable,oldValue,newValue)->selecionarItensTableViewSala(newValue));
    }

    public void carregarTableViewSala(){
        this.TableColumnNumeroSala.setCellValueFactory(new PropertyValueFactory<>("numero"));
        this.TableColumnCapacidadeSala.setCellValueFactory(new PropertyValueFactory<>("capacidade"));
        TableColumnCapacidadeSala.setStyle("-fx-alignment: CENTER;");
        TableColumnNumeroSala.setStyle("-fx-alignment: CENTER;");
        
        listaSalas=SN.listar();
        
        observableListSalas = FXCollections.observableArrayList(listaSalas);
        this.tableViewSala.setItems(observableListSalas);
    }
    public void selecionarItensTableViewSala(Sala salaSelecionada){
        if(salaSelecionada!=null){
            LabelNumeroSala.setText(String.valueOf(salaSelecionada.getNumero()));
            this.LabelCapacidadeSala.setText(String.valueOf(salaSelecionada.getCapacidade()));
            
           
        }else{
            LabelNumeroSala.setText("");
            this.LabelCapacidadeSala.setText("");
        }
    }
    
    @FXML
        public void HandleAlertDelete(ActionEvent event) throws IOException, NegocioException{
        Sala salaSelc = this.tableViewSala.getSelectionModel().getSelectedItem();
        if (salaSelc != null) {
            boolean buttonConfirmarClicked = showFXMLAlertDeleteFilme(salaSelc);
            if (buttonConfirmarClicked) {
                SN.deletar(salaSelc);
                this.carregarTableViewSala();
                Alert alert = new Alert(AlertType.INFORMATION);
                alert.setTitle("Sala Excluida");
                alert.setHeaderText("Exclusão de Sala");
                alert.setContentText("A sala "+salaSelc.getNumero() +" foi excluida com sucesso da base de dados!!!");

                alert.showAndWait();
            }
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Por favor, escolha uma sala na Tabela!");
            alert.show();
        }
    }

    public boolean showFXMLAlertDeleteFilme(Sala sala) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(AlertDeleteFilmeController.class.getResource("/view/AlertDeleteSala.fxml"));
        Parent page = (Parent) loader.load();

        // Criando um Estágio de Diálogo (Stage Dialog)
        Stage dialogStage = new Stage();
        dialogStage.setTitle("Excluir Sala");
        Scene scene = new Scene(page);
        dialogStage.setScene(scene);

        // Setando o cliente no Controller.
        AlertDeleteSalaController controller = loader.getController();
        controller.setDialogStage(dialogStage);
        controller.setSalaSelecionada(sala);

        // Mostra o Dialog e espera até que o usuário o feche
        dialogStage.showAndWait();

        return controller.isButtonConfirmationClicked();
    }
        
}
