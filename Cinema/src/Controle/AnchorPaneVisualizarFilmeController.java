
package Controle;

import dominio.Filme;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import negocio.FilmeNegocio;


public class AnchorPaneVisualizarFilmeController implements Initializable {

   @FXML
    private TableView<Filme> tableViewFilme;
    @FXML
    private TableColumn<Filme,String> tableColumnCodFilme;
    @FXML
    private TableColumn<Filme,String> tableColumnNomeFilme;
    @FXML
    private TableColumn<Filme,String> tableColumnGeneroFilme;
    @FXML
    private TableColumn<Filme,String> tableColumnSinopseFilme;
    @FXML
    private AnchorPane anchorPaneVisualizar;
    
    private List<Filme> listaFilmes;
    private ObservableList<Filme> observableListFilmes;
    FilmeNegocio FN = new FilmeNegocio();
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        carregarTableViewFilmes();
    }  
    
        public void carregarTableViewFilmes(){
        this.tableColumnCodFilme.setCellValueFactory(new PropertyValueFactory<>("codigo"));
        this.tableColumnNomeFilme.setCellValueFactory(new PropertyValueFactory<>("nome"));
        this.tableColumnGeneroFilme.setCellValueFactory(new PropertyValueFactory<>("genero"));
        this.tableColumnSinopseFilme.setCellValueFactory(new PropertyValueFactory<>("sinopse"));
        
        listaFilmes=FN.listar();
        
        observableListFilmes = FXCollections.observableArrayList(listaFilmes);
        this.tableViewFilme.setItems(observableListFilmes);
    }
    
}
