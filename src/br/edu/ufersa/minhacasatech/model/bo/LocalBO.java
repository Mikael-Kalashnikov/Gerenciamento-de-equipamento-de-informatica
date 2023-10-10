package br.edu.ufersa.minhacasatech.model.bo;

import br.edu.ufersa.minhacasatech.exception.InvalidInsertException;
import br.edu.ufersa.minhacasatech.exception.NotFoundException;
import br.edu.ufersa.minhacasatech.model.entity.Local;
import br.edu.ufersa.minhacasatech.model.dao.LocalDAO;
import java.util.List;

public class LocalBO implements BaseBO<Local> {

    private LocalDAO locdao;

    public LocalBO(LocalDAO locdao) {
        this.locdao = locdao;
    }

    @Override
    public void cadastrar(Local loc) throws InvalidInsertException {
        // validar os dados
        validarLocal(loc);

        // verificar se o local ja existe
        if (locdao.existeLocalPorNome(loc.getNome())) {
            throw new InvalidInsertException("Local já existe com o nome: " + loc.getNome());
        }

        // inserir o local no banco
        try {
            LocalDAO.getConnection();
            locdao.inserir(loc);
        } finally {
            LocalDAO.closeConnection();
        }

    }

    private void validarLocal(Local loc) {
    }

    @Override
    public void buscarPorId(Local loc) throws NotFoundException {

    }

    @Override
    public List<Local> listar() throws InvalidInsertException {
        return null;
    }

    @Override
    public void alterar(Local loc) throws InvalidInsertException {
        validarLocal(loc);

        if (!locdao.existeLocalPorId(loc.getId())) {
            try {
                throw new NotFoundException("Local não existe com ID: " + loc.getId());
            } catch (NotFoundException e) {
                e.printStackTrace();
            }
        }

        try {
            LocalDAO.getConnection();
            locdao.atualizar(loc);
        } finally {
            LocalDAO.closeConnection();
        }

    }

    @Override
    public void remover(Local loc) throws InvalidInsertException {

    }

}
