
package Controle;

import dominio.Filme;
import dominio.Sala;
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
import negocio.SalaNegocio;


public class AnchorPaneBuscaNumeroCapacidadeController implements Initializable {

    @FXML
    private TextField textFieldBuscaSala;
    @FXML
    private Button btnPesquisar;
    @FXML
    private TableView<Sala> tableViewSala;
    @FXML
    private TableColumn<Sala,String> tableColumnNumeroSala;
    @FXML
    private TableColumn<Sala,String> tableColumnCapacidadeSala;
    @FXML 
    private ToggleButton toggleBtnNumeroSala;
    @FXML 
    private ToggleButton toggleBtnCapacidadeSala;

    public void setToggleBtnNumeroSala() {
        this.toggleBtnNumeroSala.setSelected(true);
    }

    public void setToggleBtnCapacidadeSala() {
        this.toggleBtnCapacidadeSala.setSelected(true);
    }
    
    
    
    
    
private List<Sala> listaSalas;
private ObservableList<Sala> observableListSalas;
SalaNegocio SN = new SalaNegocio();

    @Override
    public void initialize(URL url, ResourceBundle rb) {

    }

    public void carregarTableViewNumeroSala(String NumeroSala){
        
        try {
            int numSala = Integer.parseInt(NumeroSala);
            if(numSala<=0){
                Alert alertSala = new Alert(Alert.AlertType.WARNING);
                    alertSala.setTitle("Falha na Atualização");
                    alertSala.setHeaderText("ATENÇÃO!!!");
                    alertSala.setContentText("O número da Sala para busca não pode ser Zero ou Número negativo, Tente Novamente!!!");
                    alertSala.showAndWait();
                    return; 
            }
            ArrayList<Sala> salaList = new ArrayList<Sala>();
            salaList.add(SN.procurarSalaPorNumero(numSala));
            listaSalas=salaList;
            if(!listaSalas.isEmpty() && listaSalas.get(0)!=null){
                this.tableColumnNumeroSala.setCellValueFactory(new PropertyValueFactory<>("numero"));
                this.tableColumnCapacidadeSala.setCellValueFactory(new PropertyValueFactory<>("capacidade"));
                tableColumnCapacidadeSala.setStyle("-fx-alignment: CENTER;");
                tableColumnNumeroSala.setStyle("-fx-alignment: CENTER;");
                
                observableListSalas = FXCollections.observableArrayList(listaSalas);
                this.tableViewSala.setItems(observableListSalas);
            }else{
                this.tableViewSala.setItems(null);
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("Resultado da Busca");
                alert.setHeaderText("");
                alert.setContentText("Nenhuma sala foi encontrada com o numero "+ NumeroSala +" ,tente novamente!!!");
                alert.showAndWait();
            }
        } catch (NegocioException ex) {
            Alert alertFilmeNegocio = new Alert(Alert.AlertType.ERROR);
            alertFilmeNegocio.setHeaderText("Falha na Busca por Nome");
            alertFilmeNegocio.setContentText(ex.getMessage());
            alertFilmeNegocio.show();
        } catch (NumberFormatException ex){
                  Alert alertVazio = new Alert(Alert.AlertType.ERROR);
                  alertVazio.setTitle("Falha na Atualização");
                  alertVazio.setHeaderText("Formato numérico inválido");
                  alertVazio.setContentText("você tem que digitar um número para a busca, Tente Novamente!!!"); 
                  alertVazio.showAndWait();
        }
    }
        public void carregarTableViewCapacidadeSalas(String capacidade){
        
        try {
            int capSala = Integer.parseInt(capacidade);
            if(capSala<=0){
                Alert alertSala = new Alert(Alert.AlertType.WARNING);
                    alertSala.setTitle("Falha na Atualização");
                    alertSala.setHeaderText("ATENÇÃO!!!");
                    alertSala.setContentText("A capacidade para busca não pode ser Zero ou Número negativo, Tente Novamente!!!");
                    alertSala.showAndWait();
                    return; 
            }
            listaSalas = SN.procurarPorCapacidade(capSala);
            if(!listaSalas.isEmpty()){
                this.tableColumnNumeroSala.setCellValueFactory(new PropertyValueFactory<>("numero"));
                this.tableColumnCapacidadeSala.setCellValueFactory(new PropertyValueFactory<>("capacidade"));
                tableColumnCapacidadeSala.setStyle("-fx-alignment: CENTER;");
                tableColumnNumeroSala.setStyle("-fx-alignment: CENTER;");
                
                observableListSalas = FXCollections.observableArrayList(listaSalas);
                this.tableViewSala.setItems(observableListSalas);
            }else{
                this.tableViewSala.setItems(null);
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("Resultado da Busca");
                alert.setHeaderText("");
                alert.setContentText("Nenhuma Sala foi econtrada dentro deste requisito de capacidade,tente novamente!!!");
                alert.showAndWait();
            }
        } catch (NegocioException ex) {
            Alert alertFilmeNegocio = new Alert(Alert.AlertType.ERROR);
            alertFilmeNegocio.setHeaderText("Falha na Busca por Código");
            alertFilmeNegocio.setContentText(ex.getMessage());
            alertFilmeNegocio.show();
        } catch (NumberFormatException ex){
                  Alert alertVazio = new Alert(Alert.AlertType.ERROR);
                  alertVazio.setTitle("Falha na Atualização");
                  alertVazio.setHeaderText("Formato numérico inválido");
                  alertVazio.setContentText("você tem que digitar um número para a busca, Tente Novamente!!!"); 
                  alertVazio.showAndWait();
        }
    }
    @FXML
        public void HandlebtnPesquisar(ActionEvent event) throws IOException{
            if(!this.toggleBtnNumeroSala.isSelected() && !this.toggleBtnCapacidadeSala.isSelected()){
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("Falha de Busca");
                alert.setHeaderText("");
                alert.setContentText("Você tem que  selecionar 1 opção para a busca, selecione por número ou por capacidade. Tente Novamente!!!");
                alert.showAndWait();
            }else{
                if(!this.textFieldBuscaSala.getText().isEmpty()){
                    if(toggleBtnNumeroSala.isSelected()){
                        this.carregarTableViewNumeroSala(this.textFieldBuscaSala.getText());
                    }else{
                        this.carregarTableViewCapacidadeSalas(this.textFieldBuscaSala.getText());                    
                    }
                }else{
                    this.tableViewSala.setItems(null);
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setHeaderText("Falha na Busca");
                    alert.setContentText("Você precisa informar um número ou a capacidade desejada para a busca!!!");
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
        public void HandlebtnToggleButtonNumeroSala(ActionEvent event) throws IOException{
            if(this.toggleBtnCapacidadeSala.isSelected()){
                this.toggleBtnCapacidadeSala.setSelected(false);
            }
        }
        @FXML
        public void HandlebtnToggleButtonCapacidadeSala(ActionEvent event) throws IOException{
            if(this.toggleBtnNumeroSala.isSelected()){
                this.toggleBtnNumeroSala.setSelected(false);
            }
        }
}
