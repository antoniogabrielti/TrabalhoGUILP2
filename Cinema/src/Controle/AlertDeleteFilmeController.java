
package Controle;

import dominio.Filme;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;


public class AlertDeleteFilmeController implements Initializable {

   @FXML
   private Label nomeExclusaoLabel;
   @FXML
   private Button OpSim;
   @FXML
   private Button OpNao;
   
   private Stage dialogStage;
   private boolean buttonConfirmationClicked=false;
   private Filme filmeSelecionado;

    public Stage getDialogStage() {
        return dialogStage;
    }

    public void setDialogStage(Stage dialogStage) {
        this.dialogStage = dialogStage;
    }

    public boolean isButtonConfirmationClicked() {
        return buttonConfirmationClicked;
    }

    public void setButtonConfirmationClicked(boolean buttonConfirmationClicked) {
        this.buttonConfirmationClicked = buttonConfirmationClicked;
    }

    public Filme getFilmeSelecionado() {
        return filmeSelecionado;
    }

    public void setFilmeSelecionado(Filme filmeSelecionado) {
        this.filmeSelecionado = filmeSelecionado;
        this.nomeExclusaoLabel.setText(this.filmeSelecionado.getNome());
    }
   
   
   
   
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       
    } 
    public void HandleOpSimDeletar(ActionEvent event) throws IOException {
        this.buttonConfirmationClicked=true;
        this.dialogStage.close();
     }
    public void HandleOpNaoDeletar(ActionEvent event) throws IOException {
        dialogStage.close();
    }
}
