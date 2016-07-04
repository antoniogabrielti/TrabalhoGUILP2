
package Controle;

import dominio.Filme;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import negocio.FilmeNegocio;
import negocio.NegocioException;


public class AnchorPaneBuscaNomeController implements Initializable {

    @FXML
    private TextField textFieldBuscaNome;
    @FXML
    private Button btnPesquisar;
    @FXML
    private TableView<Filme> tableViewFilme;
    @FXML
    private TableColumn<Filme,String> tableColumnCodigoFilme;
    @FXML
    private TableColumn<Filme,String> tableColumnNomeFilme;
    @FXML
    private TableColumn<Filme,String> tableColumnGeneroFilme;
    @FXML
    private TableColumn<Filme,String> tableColumnSinopseFilme;
    
private List<Filme> listaFilmes;
private ObservableList<Filme> observableListFilmes;
FilmeNegocio FN = new FilmeNegocio();

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
    }

    public void carregarTableViewFilmes(String NomeFilme){
        
        try {
            listaFilmes=FN.procurarPorNomeFilme(NomeFilme);
            if(!listaFilmes.isEmpty()){
                this.tableColumnCodigoFilme.setCellValueFactory(new PropertyValueFactory<>("codigo"));
                this.tableColumnNomeFilme.setCellValueFactory(new PropertyValueFactory<>("nome"));
                this.tableColumnGeneroFilme.setCellValueFactory(new PropertyValueFactory<>("genero"));
                this.tableColumnSinopseFilme.setCellValueFactory(new PropertyValueFactory<>("sinopse"));
                
                observableListFilmes = FXCollections.observableArrayList(listaFilmes);
                this.tableViewFilme.setItems(observableListFilmes);
            }else{
                this.tableViewFilme.setItems(null);
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("Resultado da Busca");
                alert.setHeaderText("");
                alert.setContentText("Nenhum filme foi encontrado com o nome "+ NomeFilme +" ,tente novamente!!!");
                alert.showAndWait();
            }
        } catch (NegocioException ex) {
            Alert alertFilmeNegocio = new Alert(Alert.AlertType.ERROR);
            alertFilmeNegocio.setHeaderText("Falha na Busca por Nome");
            alertFilmeNegocio.setContentText(ex.getMessage());
            alertFilmeNegocio.show();
        }             
    }
    @FXML
        public void HandlebtnPesquisar(ActionEvent event) throws IOException{
            if(!textFieldBuscaNome.getText().isEmpty()){
                this.carregarTableViewFilmes(textFieldBuscaNome.getText());
            }else{
               this.tableViewFilme.setItems(null);
               Alert alert = new Alert(Alert.AlertType.ERROR);
               alert.setHeaderText("Falha na Busca por Nome");
               alert.setContentText("Você precisa informar um nome para busca!!!");
               alert.show(); 
            }
        }
}
