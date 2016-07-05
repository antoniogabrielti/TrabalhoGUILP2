
package Controle;

import dominio.Filme;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import negocio.FilmeNegocio;
import negocio.NegocioException;


public class AnchorPaneCadastrarFilmeController implements Initializable {

    @FXML
    private TextField textFieldNomeFilme;
    @FXML
    private TextField textFieldGeneroFilme;
    @FXML
    private TextField textFieldSinopseFilme;
    @FXML
    private Button btnCadastrarFilme;
    
    FilmeNegocio FN = new FilmeNegocio();
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
        @FXML
        public void HandleBtnCadastrarFilme(ActionEvent event) throws IOException, NegocioException{
        
        if (!textFieldNomeFilme.getText().isEmpty() && !textFieldGeneroFilme.getText().isEmpty() && !textFieldSinopseFilme.getText().isEmpty()) {
            if(!validaNome(textFieldNomeFilme.getText())){
            Filme novo = new Filme(textFieldNomeFilme.getText(),textFieldGeneroFilme.getText(),textFieldSinopseFilme.getText());
            try {
                FN.salvar(novo);
                Alert alert = new Alert(AlertType.INFORMATION);
                alert.setTitle("Confirmação de Cadastro");
                alert.setHeaderText("CONFIRMAÇÃO");
                alert.setContentText("O filme "+textFieldNomeFilme.getText() +" foi cadastrado com sucesso na base de dados!!!");
                alert.showAndWait();
                this.textFieldNomeFilme.setText("");
                this.textFieldGeneroFilme.setText("");
                this.textFieldSinopseFilme.setText("");
            } catch (NegocioException ex) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setContentText(ex.getMessage());
                alert.show();
            }
         }else{
            Alert alertFilmeCadastrado = new Alert(Alert.AlertType.WARNING);
            alertFilmeCadastrado.setTitle("Confirmação de Cadastro");
            alertFilmeCadastrado.setHeaderText("ATENÇÃO!!!");
            alertFilmeCadastrado.setContentText("O filme "+textFieldNomeFilme.getText() +" já foi cadastrado, não é permitido filmes duplicados!!!");
            alertFilmeCadastrado.showAndWait();
          }
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Campos de cadastramento não podem ficar em branco!");
            alert.show();
        }
    }
        private boolean validaNome(String nome) throws NegocioException {
        List<Filme> listaFilmesNome = FN.procurarPorNomeFilme(nome);
        if(!listaFilmesNome.isEmpty()){
            for(Filme f : listaFilmesNome){
              if(f.getNome().equalsIgnoreCase(nome)){
                  return true;
              }  
            }
        }
        return false;
    }
        @FXML
        public void handleKeyEnter(KeyEvent ke) throws IOException, NegocioException{
            if (ke.getCode().equals(KeyCode.ENTER)){
                ActionEvent event = new ActionEvent();
                this.HandleBtnCadastrarFilme(event);
            }
        }
}
