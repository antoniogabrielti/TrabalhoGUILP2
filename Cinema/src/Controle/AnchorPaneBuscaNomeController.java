
package Controle;

import dominio.Filme;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
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
import javafx.scene.control.ToggleButton;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
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
    @FXML 
    private ToggleButton toggleBtnCodigoFilme;
    @FXML 
    private ToggleButton toggleBtnNomeFilme;

    public void setToggleBtnCodigoFilme() {
        this.toggleBtnCodigoFilme.setSelected(true);
    }

    public void setToggleBtnNomeFilme() {
        this.toggleBtnNomeFilme.setSelected(true);
    }
    
    
    
    
    
private List<Filme> listaFilmes;
private ObservableList<Filme> observableListFilmes;
FilmeNegocio FN = new FilmeNegocio();

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        toggleBtnCodigoFilme.isSelected();
       //toggleBtnCodigoFilme.setSelected(true);  aqui eu seto para selecionado
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
        public void carregarTableViewFilmes(int codigo){
        
        try {
            Filme filme = FN.procurarFilmePorCod(codigo);
            if(filme!=null){
                ArrayList listaFilmeCod = new ArrayList<Filme>();
                listaFilmeCod.add(filme);
                this.listaFilmes=listaFilmeCod;
            }
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
                alert.setContentText("Nenhum filme foi encontrado com o código "+ codigo +" ,tente novamente!!!");
                alert.showAndWait();
            }
        } catch (NegocioException ex) {
            Alert alertFilmeNegocio = new Alert(Alert.AlertType.ERROR);
            alertFilmeNegocio.setHeaderText("Falha na Busca por Código");
            alertFilmeNegocio.setContentText(ex.getMessage());
            alertFilmeNegocio.show();
        }             
    }
    @FXML
        public void HandlebtnPesquisar(ActionEvent event) throws IOException{
            if(!toggleBtnNomeFilme.isSelected() && !toggleBtnCodigoFilme.isSelected()){
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("Falha de Busca");
                alert.setHeaderText("");
                alert.setContentText("Você tem que  selecionar 1 opção para a busca, selecione por nome ou por código. Tente Novamente!!!");
                alert.showAndWait();
            }else{
                if(!textFieldBuscaNome.getText().isEmpty()){
                    if(toggleBtnNomeFilme.isSelected()){
                        this.carregarTableViewFilmes(textFieldBuscaNome.getText());
                    }else{
                        int codigo;
                        try{
                            codigo = Integer.parseInt(textFieldBuscaNome.getText());
                            this.carregarTableViewFilmes(codigo);
                        } catch (NumberFormatException ex) {
                            this.textFieldBuscaNome.setText("");
                            Alert alert = new Alert(Alert.AlertType.ERROR);
                            alert.setContentText("Você não informou o código em um formato válido, Tente Novamente!!!");
                            alert.show();
                        }
                        
                    }
                }else{
                    this.tableViewFilme.setItems(null);
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setHeaderText("Falha na Busca");
                    alert.setContentText("Você precisa informar um nome ou um código para busca!!!");
                    alert.show(); 
                }
              }
            }
        @FXML
        public void handleKeyEnter(KeyEvent ke) throws IOException{
            if (ke.getCode().equals(KeyCode.ENTER)){
                ActionEvent event = new ActionEvent();
                this.HandlebtnPesquisar(event);
            }
        }
        @FXML
        public void HandlebtnToggleButton(ActionEvent event) throws IOException{
            if(this.toggleBtnNomeFilme.isSelected()){
                this.toggleBtnNomeFilme.setSelected(false);
            }
        }
        @FXML
        public void HandlebtnToggleButtonNome(ActionEvent event) throws IOException{
            if(this.toggleBtnCodigoFilme.isSelected()){
                this.toggleBtnCodigoFilme.setSelected(false);
            }
        }
}
