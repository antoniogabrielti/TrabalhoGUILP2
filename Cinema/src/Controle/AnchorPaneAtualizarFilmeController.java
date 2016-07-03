
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
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import negocio.FilmeNegocio;
import negocio.NegocioException;


public class AnchorPaneAtualizarFilmeController implements Initializable {

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
private TextField textFieldNomeFilme;
@FXML
private TextField textFieldGeneroFilme;
@FXML
private TextField textFieldSinopseFilme;
@FXML
private Button btnAtualizarFilme;
@FXML
private Filme filmeSelecionadoAtualizar;

private List<Filme> listaFilmes;
private ObservableList<Filme> observableListFilmes;
FilmeNegocio FN = new FilmeNegocio();

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        carregarTableViewFilmes();
        this.tableViewFilme.getSelectionModel().selectedItemProperty().addListener((observable,oldValue,newValue)->selecionarItensTableViewFilmes(newValue));
    }

    public void carregarTableViewFilmes(){
        this.tableColumnCodigoFilme.setCellValueFactory(new PropertyValueFactory<>("codigo"));
        this.tableColumnNomeFilme.setCellValueFactory(new PropertyValueFactory<>("nome"));
        this.tableColumnGeneroFilme.setCellValueFactory(new PropertyValueFactory<>("genero"));
        this.tableColumnSinopseFilme.setCellValueFactory(new PropertyValueFactory<>("sinopse"));
        listaFilmes=FN.listar();
        
        observableListFilmes = FXCollections.observableArrayList(listaFilmes);
        this.tableViewFilme.setItems(observableListFilmes);
    }
    public void selecionarItensTableViewFilmes(Filme filmeSelecionado){
        if(filmeSelecionado!=null){
            this.textFieldNomeFilme.setText(filmeSelecionado.getNome());
            this.textFieldGeneroFilme.setText(filmeSelecionado.getGenero());
            this.textFieldSinopseFilme.setText(filmeSelecionado.getSinopse());
            this.setFilmeSelecionadoAtualizar(filmeSelecionado);
        }else{
            this.textFieldNomeFilme.setText("");
            this.textFieldGeneroFilme.setText("");
            this.textFieldSinopseFilme.setText("");
        }
    }  

    public void setFilmeSelecionadoAtualizar(Filme filmeSelecionadoAtualizar) {
        this.filmeSelecionadoAtualizar = filmeSelecionadoAtualizar;
    }
    
        @FXML
        public void HandleBtnAtualizarFilme(ActionEvent event) throws IOException{
        
        if (filmeSelecionadoAtualizar != null) {
            if (!this.textFieldNomeFilme.getText().isEmpty() && !this.textFieldGeneroFilme.getText().isEmpty() && !this.textFieldSinopseFilme.getText().isEmpty()) {
                
                try {
                    if(!filmeSelecionadoAtualizar.getNome().equalsIgnoreCase(textFieldNomeFilme.getText())){
                        this.filmeSelecionadoAtualizar.setNome(textFieldNomeFilme.getText());
                    }
                    if(!filmeSelecionadoAtualizar.getGenero().equalsIgnoreCase(textFieldGeneroFilme.getText())){
                        this.filmeSelecionadoAtualizar.setGenero(textFieldGeneroFilme.getText());
                    }
                    if(!filmeSelecionadoAtualizar.getSinopse().equalsIgnoreCase(textFieldSinopseFilme.getText())){
                        this.filmeSelecionadoAtualizar.setSinopse(textFieldSinopseFilme.getText());
                    }
                    
                    FN.atualizar(filmeSelecionadoAtualizar);
                } catch (NegocioException ex) {
                Alert alertFilmeNegocio = new Alert(AlertType.ERROR);
                alertFilmeNegocio.setHeaderText("Falha na Atualização");
                alertFilmeNegocio.setContentText(ex.getMessage());
                }
                this.carregarTableViewFilmes();
                Alert alert = new Alert(AlertType.INFORMATION);
                alert.setTitle("Atualização");
                alert.setHeaderText("Filme Atualizado");
                alert.setContentText("O filme "+ textFieldNomeFilme.getText() +" foi atualizado com sucesso na base de dados!!!");
                alert.showAndWait();
                this.textFieldNomeFilme.setText("");
                this.textFieldGeneroFilme.setText("");
                this.textFieldSinopseFilme.setText("");
                filmeSelecionadoAtualizar=null;
            }else{
                Alert alertVazio = new Alert(AlertType.ERROR);
                alertVazio.setHeaderText("Falha na Atualização");
                alertVazio.setContentText("Não pode haver campos em branco para a atualização!!!"); 
                alertVazio.showAndWait();
            }
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Por favor, escolha um filme na Tabela para atualizar!");
            alert.show();
        }
    }
    
}
