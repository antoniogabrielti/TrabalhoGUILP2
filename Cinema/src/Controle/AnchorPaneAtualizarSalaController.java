
package Controle;


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
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import negocio.NegocioException;
import negocio.SalaNegocio;


public class AnchorPaneAtualizarSalaController implements Initializable {

@FXML
private TableView<Sala> tableViewSala;
@FXML
private TableColumn<Sala,String> tableColumnNumeroSala;
@FXML
private TableColumn<Sala,String> tableColumnCapacidadeSala;
@FXML
private TextField textFieldNumeroSala;
@FXML
private TextField textFieldCapacidadeSala;
@FXML
private Text textMaior;
@FXML
private Text textMenor;
@FXML
private Rectangle retangleMaior;
@FXML
private Rectangle retangleMenor;
@FXML
private Button btnAtualizarSala;
@FXML
private Sala salaSelecionadaAtualizar;

private List<Sala> listaSalas;
private ObservableList<Sala> observableListSalas;
SalaNegocio SN = new SalaNegocio();

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        carregarTableViewSalas();
        this.tableViewSala.getSelectionModel().selectedItemProperty().addListener((observable,oldValue,newValue)->selecionarItensTableViewSalas(newValue));
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
    public void selecionarItensTableViewSalas(Sala salaSelecionada){
        if(salaSelecionada!=null){
            this.textFieldNumeroSala.setText(String.valueOf(salaSelecionada.getNumero()));
            this.textFieldCapacidadeSala.setText(String.valueOf(salaSelecionada.getCapacidade()));
            this.setSalaSelecionadaAtualizar(salaSelecionada);
        }else{
            this.textFieldNumeroSala.setText("");
            this.textFieldCapacidadeSala.setText("");
        }
    }  

    public void setSalaSelecionadaAtualizar(Sala salaSelecionadaAtualizar) {
        this.salaSelecionadaAtualizar = salaSelecionadaAtualizar;
    }
    
        @FXML
        public void HandleBtnAtualizarSala(ActionEvent event) throws IOException{
        
        if (this.salaSelecionadaAtualizar != null) {
            
            if (!this.textFieldNumeroSala.getText().isEmpty() && !this.textFieldCapacidadeSala.getText().isEmpty()) {
               try{
                   int numSala = Integer.parseInt(this.textFieldNumeroSala.getText());
                   if(numSala<=0){
                    Alert alertSala = new Alert(Alert.AlertType.WARNING);
                    alertSala.setTitle("Falha na Atualização");
                    alertSala.setHeaderText("ATENÇÃO!!!");
                    alertSala.setContentText("O número da Sala não pode ser Zero ou Número negativo, Tente Novamente!!!");
                    alertSala.showAndWait();
                    return;   
                   }
                   
               }catch (NumberFormatException ex){
                  Alert alertVazio = new Alert(AlertType.ERROR);
                  alertVazio.setTitle("Falha na Atualização");
                  alertVazio.setHeaderText("Formato numérico inválido");
                  alertVazio.setContentText("O campo Numero da Sala e o campo Capacidade da Sala só aceitam Números, Tente Novamente!!!"); 
                  alertVazio.showAndWait();
                  return;
                }
                
                
                if(this.ValidarAtualizacaoRepetida(this.salaSelecionadaAtualizar)){
                Alert alertFilmeCadastrado = new Alert(Alert.AlertType.WARNING);
                alertFilmeCadastrado.setTitle("Falha na Atualização");
                alertFilmeCadastrado.setHeaderText("ATENÇÃO!!!");
                alertFilmeCadastrado.setContentText("A sala "+textFieldNumeroSala.getText() +" já existe, não é permitido Salas duplicadas!!!");
                alertFilmeCadastrado.showAndWait();
                return;
            }
                try {
                    int numeroSala = Integer.parseInt(this.textFieldNumeroSala.getText());
                    int capacidadeSala = Integer.parseInt(this.textFieldCapacidadeSala.getText());
                    if(capacidadeSala<20){
                        Alert alertVazio = new Alert(AlertType.WARNING);
                        alertVazio.setTitle("Falha na Atualização");
                        alertVazio.setHeaderText("");
                        alertVazio.setContentText("A capacidade minima permitida é de 20 lugares!!!"); 
                        alertVazio.showAndWait(); 
                        return;
                    }
                    if(this.salaSelecionadaAtualizar.getNumero()!=numeroSala){
                        salaSelecionadaAtualizar.setNumero(numeroSala);
                    }
                    if(this.salaSelecionadaAtualizar.getCapacidade()!=capacidadeSala){
                        salaSelecionadaAtualizar.setCapacidade(capacidadeSala);
                    }

                    
                    SN.atualizar(salaSelecionadaAtualizar);
                 this.carregarTableViewSalas();
                Alert alert = new Alert(AlertType.INFORMATION);
                alert.setTitle("Atualização");
                alert.setHeaderText("Sala Atualizada");
                alert.setContentText("A Sala "+ textFieldNumeroSala.getText() +" foi atualizada com sucesso na base de dados!!!");
                alert.showAndWait();
                this.textFieldNumeroSala.setText("");
                this.textFieldCapacidadeSala.setText("");
                salaSelecionadaAtualizar=null;
                } catch (NegocioException ex) {
                Alert alertFilmeNegocio = new Alert(AlertType.ERROR);
                alertFilmeNegocio.setHeaderText("Falha na Atualização");
                alertFilmeNegocio.setContentText(ex.getMessage());
                } catch (NumberFormatException ex){
                  Alert alertVazio = new Alert(AlertType.ERROR);
                  alertVazio.setTitle("Falha na Atualização");
                  alertVazio.setHeaderText("Formato numérico inválido");
                  alertVazio.setContentText("O campo Numero da Sala e o campo Capacidade da Sala só aceitam Números, Tente Novamente!!!"); 
                  alertVazio.showAndWait();  
                }

            }else{
                Alert alertVazio = new Alert(AlertType.ERROR);
                alertVazio.setHeaderText("Falha na Atualização");
                alertVazio.setContentText("Não pode haver campos em branco para a atualização!!!"); 
                alertVazio.showAndWait();
            }
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Por favor, escolha uma Sala na Tabela para atualizar!");
            alert.show();
        }
    }
        
        @FXML
        public void handleKeyEnter(KeyEvent ke) throws IOException{
            if (ke.getCode().equals(KeyCode.ENTER)){
                ActionEvent event = new ActionEvent();
                this.HandleBtnAtualizarSala(event);
            }
        }
        public boolean ValidarAtualizacaoRepetida(Sala salaSelec){
            List<Sala> TodosSala = SN.listar();
            ArrayList<Sala> SalasRestantes = new ArrayList<Sala>();
            boolean valida=false;
            for(Sala s : TodosSala){
                if(s.getNumero()!=salaSelec.getNumero()){
                    SalasRestantes.add(s);
                }
            }
            try{
            int numeroSala = Integer.parseInt(this.textFieldNumeroSala.getText());
            for(Sala salas : SalasRestantes){
                if(salas.getNumero()==numeroSala){
                    valida=true;
                }
            }
            } catch (NumberFormatException ex){
                  return false;
            }
            return valida;
        }
            @FXML
        public void handleClickMais(MouseEvent event) throws IOException{
            if(this.textFieldCapacidadeSala.getText().isEmpty()){
                this.textFieldCapacidadeSala.setText("20");
            }
            EventHandler<? super MouseEvent> btnMaior1 = this.retangleMaior.getOnMouseClicked();
            EventHandler<? super MouseEvent> btnMaior2 = this.textMaior.getOnMouseClicked();
            if(btnMaior1!=null && btnMaior2!=null){
                try{
                    int oldvalue = Integer.parseInt(this.textFieldCapacidadeSala.getText());
                    int newvalue = oldvalue+1;
                    if(newvalue>=20){
                    this.textFieldCapacidadeSala.setText(String.valueOf(newvalue));
                    }else{
                        Alert alert = new Alert(Alert.AlertType.WARNING);
                       alert.setTitle("Capacidade Inválida");
                       alert.setHeaderText("");
                       alert.setContentText("Capacidade mínima permitida é de 20 lugares!!!");
                       alert.showAndWait();
                        this.textFieldCapacidadeSala.setText("20");
                    }
                } catch (NumberFormatException ex) {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setContentText("Capacidade em formato invalido!!!");
                    alert.show();
                    this.textFieldCapacidadeSala.setText("20");
                }
            }
        }
        @FXML
        public void handleClickMenos(MouseEvent event) throws IOException{
            if(this.textFieldCapacidadeSala.getText().isEmpty()){
                this.textFieldCapacidadeSala.setText("20");
                return;
            }
            EventHandler<? super MouseEvent> btnMenor1 = this.retangleMaior.getOnMouseClicked();
            EventHandler<? super MouseEvent> btnMenor2 = this.textMaior.getOnMouseClicked();
            if(btnMenor1!=null && btnMenor2!=null){
                try{
                    int oldvalue = Integer.parseInt(this.textFieldCapacidadeSala.getText());
                    int newvalue = oldvalue-1;
                    if(newvalue>=20){
                        this.textFieldCapacidadeSala.setText(String.valueOf(newvalue));
                    }else{
                       Alert alert = new Alert(Alert.AlertType.WARNING);
                       alert.setTitle("Capacidade Inválida");
                       alert.setHeaderText("");
                       alert.setContentText("Capacidade mínima permitida é de 20 lugares!!!");
                       alert.showAndWait();
                       this.textFieldCapacidadeSala.setText("20");
                    }                   
                } catch (NumberFormatException ex) {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setContentText("Capacidade em formato invalido!!!");
                    alert.show();
                    this.textFieldCapacidadeSala.setText("20");
                }
            }
        }
}
