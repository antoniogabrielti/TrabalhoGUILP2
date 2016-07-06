
package Controle;


import dominio.Sala;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.NodeOrientation;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import negocio.SalaNegocio;


public class AnchorPaneVisualizarSalaController implements Initializable {

   @FXML
    private TableView<Sala> tableViewSala;
    @FXML
    private TableColumn<Sala,String> tableColumnNumeroSala;
    @FXML
    private TableColumn<Sala,String> tableColumnCapacidadeSala;

    
    private List<Sala> listaSalas;
    private ObservableList<Sala> observableListSalas;
    SalaNegocio SN = new SalaNegocio();
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        carregarTableViewSalas();
    }  
    
        public void carregarTableViewSalas(){
        this.tableColumnNumeroSala.setCellValueFactory(new PropertyValueFactory<>("numero"));
        this.tableColumnCapacidadeSala.setCellValueFactory(new PropertyValueFactory<>("capacidade"));
        tableColumnCapacidadeSala.setStyle("-fx-alignment: CENTER;");
        tableColumnNumeroSala.setStyle("-fx-alignment: CENTER;");
        

        
        listaSalas=SN.listar();
        
        observableListSalas = FXCollections.observableArrayList(listaSalas);
        
        this.tableViewSala.setItems(observableListSalas);
        
    }
    
}
