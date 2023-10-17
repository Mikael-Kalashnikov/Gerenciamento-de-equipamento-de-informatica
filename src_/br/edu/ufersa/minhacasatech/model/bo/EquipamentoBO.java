package br.edu.ufersa.minhacasatech.model.bo;

import br.edu.ufersa.minhacasatech.exception.AlreadyExistsException;
import br.edu.ufersa.minhacasatech.exception.InvalidInsertException;
import br.edu.ufersa.minhacasatech.exception.NotFoundException;
import br.edu.ufersa.minhacasatech.model.dao.EquipamentoDAO;
import br.edu.ufersa.minhacasatech.model.entity.Equipamento;
import java.util.List;

public class EquipamentoBO implements BaseBO<Equipamento> {
    
    EquipamentoDAO eqdao;

    @Override
    public Equipamento cadastrar(Equipamento e) throws InvalidInsertException, AlreadyExistsException {
        eqdao = new EquipamentoDAO();
        if (eqdao.buscarPorSerial(e) != null) {
            throw new AlreadyExistsException("Este equipamento já está cadastrado!");
        }
        else {
            e.setId(eqdao.inserir(e));
        }
        return e;
    }
    
    @Override
    public void alterar(Equipamento e) throws InvalidInsertException {
        eqdao = new EquipamentoDAO();
    }
    
    @Override
    public void remover(Equipamento e) {
        eqdao = new EquipamentoDAO();
    }
    
    @Override
    public Equipamento buscarPorId(Equipamento e) throws NotFoundException {
        eqdao = new EquipamentoDAO();
        return null;
    }
    
    @Override
    public List<Equipamento> listar() {
        eqdao = new EquipamentoDAO();
        return eqdao.listar();
    }
    
}
