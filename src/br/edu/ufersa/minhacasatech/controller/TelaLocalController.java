package br.edu.ufersa.minhacasatech.controller;

import br.edu.ufersa.minhacasatech.model.bo.LocalBO;
import br.edu.ufersa.minhacasatech.model.entity.Local;
import br.edu.ufersa.minhacasatech.view.Telas;
import java.net.URL;
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

public class TelaLocalController implements Initializable {
    
    @FXML private TableView<Local> tabelaLocais;
    
    @FXML private TableColumn<Local, Long> idColumn;
    @FXML private TableColumn<Local, String> nomeColumn;
    @FXML private TableColumn<Local, String> compartimentoColumn;
    @FXML private TableColumn<Local, String> dataColumn;
    
    private ObservableList<Local> locais;
    private FilteredList<Local> filteredData;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
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
    
    @FXML private TextField dataInicio;
    @FXML private TextField dataFim;
    @FXML private TextField pesquisarLocal;
    
    @FXML
    private void buscarLocal() {
        filteredData.setPredicate(local -> {
            String iniData = dataInicio.getText();
            String fimData = dataFim.getText();
            String pesquisa = pesquisarLocal.getText().toLowerCase();
            
            boolean atendeNome, atendeCompartimento, atendeDataInicio, atendeDataFim;
            
            // verifica se o usuario digitou a data inicial
            if (iniData == null || iniData.isEmpty())
                atendeDataInicio = true;
            else
                atendeDataInicio = local.getDataCadastro().compareTo(iniData) >= 0;
            
            //verifica se o usuario digitou a data de final
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
    
    @FXML
    private void telaEquipamentos() {
        Telas.switchScene("/br/edu/ufersa/minhacasatech/view/ve/TelaEquipamentos.fxml");
    }
    
    @FXML
    private void telaVendas() {
        Telas.switchScene("/br/edu/ufersa/minhacasatech/view/ve/TelaVendas.fxml");
    }
    
    @FXML
    private void telaLocais() {
        Telas.switchScene("/br/edu/ufersa/minhacasatech/view/ve/TelaLocais.fxml");
    }

    @FXML
    private void telaClientes() {
        Telas.switchScene("/br/edu/ufersa/minhacasatech/view/ve/TelaClientes.fxml");
    }
    
    @FXML
    private void telaFuncionarios() {
        Telas.switchScene("/br/edu/ufersa/minhacasatech/view/ve/TelaFuncionarios.fxml");
    }

    @FXML
    private void logout() {
        Telas.switchScene("/br/edu/ufersa/minhacasatech/view/ve/TelaLogin.fxml");
    }

    @FXML
    private void telaCadastrarLocal() {
        Telas.switchScene("/br/edu/ufersa/minhacasatech/view/ve/TelaCadastrarLocal.fxml");
    }
    
    @FXML
    private void telaEditarLocal() {
        Telas.switchScene("/br/edu/ufersa/minhacasatech/view/ve/TelaEditarLocal.fxml");
    }

}
