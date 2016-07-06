
package Controle;

import dominio.Sala;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import negocio.NegocioException;
import negocio.SalaNegocio;


public class AnchorPaneCadastrarSalaController implements Initializable {

    @FXML
    private TextField textFieldNumeroSala;
    @FXML
    private TextField textFieldCapacidadeSala;
    @FXML
    private Button btnCadastrarSala;
    @FXML
    private Rectangle retangleMaior; 
    @FXML
    private Text textMaior;
    @FXML
    private Rectangle retangleMenor;
    @FXML
    private Text textMenor;
    
    SalaNegocio SN = new SalaNegocio();
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        textFieldCapacidadeSala.setText("20");
    }    
        
        @FXML
        public void handleBtnCadastrarSala(ActionEvent event) throws IOException{
            int numsala=-5,capacidade;
            boolean numero=false;
            if(!this.textFieldNumeroSala.getText().isEmpty() && !this.textFieldCapacidadeSala.getText().isEmpty()){
            try{
               numsala = Integer.parseInt(this.textFieldNumeroSala.getText());
               if(numsala<=0){
                  Alert alert = new Alert(Alert.AlertType.WARNING);
                  alert.setTitle("Número de Sala Inválido");
                  alert.setHeaderText("");
                  alert.setContentText("o número da Sala não pode ser Zero ou Valor Negativo, Tente Novamente!!!");
                  alert.showAndWait(); 
                  this.textFieldNumeroSala.setText("");
               }else{
                   if(SN.SalaExiste(numsala)){
                     Alert alert = new Alert(Alert.AlertType.WARNING);
                  alert.setTitle("Cadastro Duplicado");
                  alert.setHeaderText("");
                  alert.setContentText("o número da Sala já existe na base de dados, não é permitido cadastros duplicados, Tente Novamente!!!");
                  alert.showAndWait(); 
                  this.textFieldNumeroSala.setText(""); 
                  return;
                   }
                   numero=true;
               }
            } catch (NumberFormatException ex) {            
               Alert alert = new Alert(Alert.AlertType.ERROR);
               alert.setContentText("Você não informou um número de Sala em um formato válido, Tente Novamente!!!");
               alert.show();
               this.textFieldNumeroSala.setText("");
            }
            try{
                if(numero){
                    capacidade = Integer.parseInt(this.textFieldCapacidadeSala.getText());
                    if(capacidade>=20 && numsala!=-5){
                        Sala sala = new Sala(numsala,capacidade);
                        SN.salvar(sala);
                        Alert alert = new Alert(AlertType.INFORMATION);
                alert.setTitle("Confirmação de Cadastro");
                alert.setHeaderText("CONFIRMAÇÃO");
                alert.setContentText("A Sala número "+ numsala +" foi cadastrada com sucesso na base de dados!!!");
                alert.showAndWait();
                    }else{
                       Alert alert = new Alert(Alert.AlertType.WARNING);
                       alert.setTitle("Capacidade Inválida");
                       alert.setHeaderText("");
                       alert.setContentText("Capacidade mínima permitida é de 20 lugares!!!");
                       alert.showAndWait(); 
                       this.textFieldCapacidadeSala.setText("20");
                    }
                }
            }catch (NumberFormatException ex) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
               alert.setContentText("Você não informou a capacidade em um formato válido, Tente Novamente!!!");
               alert.show();
               this.textFieldCapacidadeSala.setText("20");
            }   catch (NegocioException ex) {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
               alert.setContentText(ex.getMessage());
               alert.show();
            }
          }else{
              Alert alert = new Alert(Alert.AlertType.ERROR);
              alert.setHeaderText("");
              alert.setContentText("Não pode haver campos em branco no cadastramento de uma sala!!!");
              alert.show();  
            }
        }
        
        @FXML
        public void handleKeyEnter(KeyEvent ke) throws IOException, NegocioException{
            if (ke.getCode().equals(KeyCode.ENTER)){
                ActionEvent event = new ActionEvent();
                this.handleBtnCadastrarSala(event);
            }
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
