
package Controle;

import cinema.Cinema;
import dominio.Filme;
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
import negocio.FilmeNegocio;
import negocio.NegocioException;


public class AnchorPaneDeletarFilmeController implements Initializable {
@FXML
private TableView<Filme> tableViewFilmes;
@FXML
private TableColumn<Filme,String> TableColumnFilmeCod;
@FXML
private TableColumn<Filme,String> TableColumnFilmeNome;
@FXML
private TableColumn<Filme,String> TableColumnFilmeGenero;
@FXML
private Button BtnDeletaFilme;
@FXML
private Label LabelFilmeCod;
@FXML
private Label LabelFilmeNome;
@FXML
private Label LabelFilmeGenero;


private List<Filme> listaFilmes;
private ObservableList<Filme> observableListFilmes;
FilmeNegocio FN = new FilmeNegocio();

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        carregarTableViewFilmes();
        this.tableViewFilmes.getSelectionModel().selectedItemProperty().addListener((observable,oldValue,newValue)->selecionarItensTableViewFilmes(newValue));
    }

    public void carregarTableViewFilmes(){
        this.TableColumnFilmeCod.setCellValueFactory(new PropertyValueFactory<>("codigo"));
        this.TableColumnFilmeNome.setCellValueFactory(new PropertyValueFactory<>("nome"));
        this.TableColumnFilmeGenero.setCellValueFactory(new PropertyValueFactory<>("genero"));
        
        listaFilmes=FN.listar();
        
        observableListFilmes = FXCollections.observableArrayList(listaFilmes);
        this.tableViewFilmes.setItems(observableListFilmes);
    }
    public void selecionarItensTableViewFilmes(Filme filmeSelecionado){
        if(filmeSelecionado!=null){
            LabelFilmeCod.setText(String.valueOf(filmeSelecionado.getCodigo()));
            this.LabelFilmeNome.setText(filmeSelecionado.getNome());
            this.LabelFilmeGenero.setText(filmeSelecionado.getGenero());
            //FilmeParaExclusao(filmeSelecionado);
        }else{
            LabelFilmeCod.setText("");
            this.LabelFilmeNome.setText("");
            this.LabelFilmeGenero.setText("");
        }
    }
    
    @FXML
        public void HandleAlertDelete(ActionEvent event) throws IOException, NegocioException{
        Filme filmeSelc = this.tableViewFilmes.getSelectionModel().getSelectedItem();
        if (filmeSelc != null) {
            boolean buttonConfirmarClicked = showFXMLAlertDeleteFilme(filmeSelc);
            if (buttonConfirmarClicked) {
                FilmeNegocio FN = new FilmeNegocio();
                FN.deletar(filmeSelc);
                this.carregarTableViewFilmes();
                Alert alert = new Alert(AlertType.INFORMATION);
                alert.setTitle("Filme Excluido");
                alert.setHeaderText("Exclusão de Filme");
                alert.setContentText("O filme "+filmeSelc.getNome() +" foi excluido com sucesso da base de dados!!!");

                alert.showAndWait();
            }
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Por favor, escolha um filme na Tabela!");
            alert.show();
        }
    }

    public boolean showFXMLAlertDeleteFilme(Filme filme) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(AlertDeleteFilmeController.class.getResource("/view/AlertDeleteFilme.fxml"));
        Parent page = (Parent) loader.load();

        // Criando um Estágio de Diálogo (Stage Dialog)
        Stage dialogStage = new Stage();
        dialogStage.setTitle("Excluir Filme");
        Scene scene = new Scene(page);
        dialogStage.setScene(scene);

        // Setando o cliente no Controller.
        AlertDeleteFilmeController controller = loader.getController();
        controller.setDialogStage(dialogStage);
        controller.setFilmeSelecionado(filme);

        // Mostra o Dialog e espera até que o usuário o feche
        dialogStage.showAndWait();

        return controller.isButtonConfirmationClicked();
    }
        
}
