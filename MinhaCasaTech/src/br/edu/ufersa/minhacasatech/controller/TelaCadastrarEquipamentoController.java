package br.edu.ufersa.minhacasatech.controller;

import br.edu.ufersa.minhacasatech.exception.AlreadyExistsException;
import br.edu.ufersa.minhacasatech.exception.InvalidInsertException;
import br.edu.ufersa.minhacasatech.model.bo.EquipamentoBO;
import br.edu.ufersa.minhacasatech.model.bo.FuncionarioBO;
import br.edu.ufersa.minhacasatech.model.bo.LocalBO;
import br.edu.ufersa.minhacasatech.model.entity.Equipamento;
import br.edu.ufersa.minhacasatech.model.entity.Local;
import java.util.logging.Logger;
import java.util.logging.Level;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Dialog;
import javafx.scene.control.TextField;

public class TelaCadastrarEquipamentoController extends TelaPrincipalController implements Initializable {
    
    @FXML private TextField nome;
    @FXML private TextField serial;
    @FXML private TextField quantidade;
    @FXML private TextField preco;
    @FXML private ChoiceBox local;
    @FXML private ChoiceBox responsavel;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // inicializa choiceBox de local
        LocalBO locbo = new LocalBO();
        local.getItems().setAll(locbo.listarNomes());
        
        // inicializa choiceBox de responsavel
        FuncionarioBO funcbo = new FuncionarioBO();
        responsavel.getItems().setAll(funcbo.listarNomesResponsavel());        
    }
    
    @FXML
    private void cadastrarEquipamento() {
        try {
            Equipamento eq = new Equipamento();
            eq.setNome(nome.getText());
            eq.setSerial(serial.getText());
            
            // valida o campo preco
            if (preco.getText() != null && !preco.getText().isEmpty()) {
                eq.setPreco(Double.parseDouble(preco.getText().replace(",", ".")));
            } else {
                throw new InvalidInsertException("Preço inválido!");
            }
            
            // valida o campo quantidade
            if (quantidade.getText() != null && !quantidade.getText().isEmpty()) {
                eq.setQuantidade(Integer.parseInt(quantidade.getText()));
            } else {
                throw new InvalidInsertException("Quantidade inválida!");
            }
            
            String nomeCompartimento = local.getSelectionModel().getSelectedItem().toString();
            String n = nomeCompartimento.split(", ")[0];
            String c = nomeCompartimento.split(", ")[1];
            
            System.out.println(n);
            System.out.println(c);
            
            LocalBO locbo = new LocalBO();
            Local loc = locbo.buscarPorNomeCompartimento(new Local());
            eq.setLocal(loc);
            
            EquipamentoBO eqbo = new EquipamentoBO();
            eqbo.cadastrar(eq);
            Dialog success = FrontController.callDialogPane("Message", "Equipamento cadastrado com sucesso!");
            success.showAndWait();
        } catch (InvalidInsertException | AlreadyExistsException ex) {
            Logger.getLogger(TelaFuncionarioController.class.getName()).log(Level.SEVERE, null, ex);
            Dialog dialog =  FrontController.callDialogPane("Error", ex.getMessage());
            dialog.showAndWait();
        }
    }
    
    @FXML
    private void close() {
        TelaEquipamentoController.newStage.close();
        if (user.getIsResponsavel()) {
            telaEquipamentos();
        } else {
            telaEquipamentosFunc(); 
        }
    }
    
}
