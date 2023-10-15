package br.edu.ufersa.minhacasatech.controller;

import br.edu.ufersa.minhacasatech.exception.AlreadyExistsException;
import br.edu.ufersa.minhacasatech.exception.InvalidInsertException;
import br.edu.ufersa.minhacasatech.model.bo.LocalBO;
import br.edu.ufersa.minhacasatech.model.entity.Local;
import br.edu.ufersa.minhacasatech.view.Telas;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

public class TelaLocalController extends TelaPrincipalController implements Initializable {
    
    @FXML private TableView<Local> tabelaLocais;
    
    @FXML private TableColumn<Local, Long> idColumn;
    @FXML private TableColumn<Local, String> nomeColumn;
    @FXML private TableColumn<Local, String> compartimentoColumn;
    @FXML private TableColumn<Local, String> dataColumn;
    
    private ObservableList<Local> locais;
    private FilteredList<Local> filteredData;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // inicializa a tabela apenas se nao for um pop-up, para nao dar conflito
        if (init) {
            LocalBO locbo = new LocalBO();
            locais = FXCollections.observableArrayList();
            locais.addAll(locbo.listar());

            idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
            nomeColumn.setCellValueFactory(new PropertyValueFactory<>("nome"));
            compartimentoColumn.setCellValueFactory(new PropertyValueFactory<>("nomeCompartimento"));
            dataColumn.setCellValueFactory(new PropertyValueFactory<>("dataCadastro"));

            tabelaLocais.setItems(locais);

            filteredData = new FilteredList<>(tabelaLocais.getItems(), local -> true);
        }
    }
    
    @FXML private TextField dataInicio;
    @FXML private TextField dataFim;
    @FXML private TextField pesquisarLocal;
    
    @FXML
    private void buscarLocal() {
        filteredData.setPredicate((Local local) -> {
            String iniData = dataInicio.getText();
            String fimData = dataFim.getText();
            String pesquisa = pesquisarLocal.getText().toLowerCase();
            boolean atendeNome, atendeCompartimento, atendeDataInicio, atendeDataFim;
            // verifica se o usuario digitou a data inicial
            if (iniData == null || iniData.isEmpty())
                atendeDataInicio = true;
            else
                atendeDataInicio = local.getDataCadastro().compareTo(iniData) >= 0;
            //verifica se o usuario digitou a data final
            if (fimData == null || fimData.isEmpty())
                atendeDataFim = true;
            else
                atendeDataFim = local.getDataCadastro().compareTo(fimData) <= 0;
            // verifica se o usuario digitou algo no campo de pesquisa
            if (pesquisa == null || pesquisa.isEmpty()) {
                atendeNome = true;
                atendeCompartimento = true;
            }
            else {
                atendeNome = local.getNome().toLowerCase().contains(pesquisa);
                atendeCompartimento = local.getNomeCompartimento().toLowerCase().contains(pesquisa);
            }
            
            // retorna somente os locais filtrados
            return atendeDataInicio && atendeDataFim && (atendeNome || atendeCompartimento);
        });
        
        // define os valores filtrados para a tabela
        tabelaLocais.setItems(filteredData);
    }
    
    @FXML private TextField nome;
    @FXML private TextField compartimento;
    
    @FXML
    private void cadastrarLocal() {
        try {
            Local local = new Local(nome.getText(), compartimento.getText());
            LocalBO locbo = new LocalBO();
            locbo.cadastrar(local);
            FrontController.callDialogPane("Message", "Local cadastrado com sucesso!");
        } catch (InvalidInsertException | AlreadyExistsException ex) {
            Logger.getLogger(TelaLocalController.class.getName()).log(Level.SEVERE, null, ex);
            
            // exibe tela de erro de local
            FrontController.callDialogPane("Error", ex.getMessage());
        }
    }
    
    @FXML
    private void telaCadastrarLocal() {
        // cria um novo pop-up para a tela de cadastro
        init = false;
        newStage = Telas.newScene("/br/edu/ufersa/minhacasatech/view/ve/TelaCadastrarLocal.fxml");
    }
    
    @FXML
    private void telaEditarLocal() {
        // cria um novo pop-up para a tela de editar
        init = false;
        newStage = Telas.newScene("/br/edu/ufersa/minhacasatech/view/ve/TelaEditarLocal.fxml");
    }
    
    @FXML
    private void telaExcluirLocal() {
        // gerar a tela de confirmacao de exclusao
    }
    
    @FXML
    private void close() {
        // fecha o pop-up
        init = true;
        newStage.close();
        if (user.getIsResponsavel()) {
            telaLocais();
        } else {
            telaLocaisFunc();
        }
    }
    
}
