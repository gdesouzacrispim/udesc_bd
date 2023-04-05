package core.service;

import core.dao.CidadeDAO;
import core.exception.CampoInvalidoExceptions;
import core.utils.Utils;
import entity.Cidade;

import javax.swing.JOptionPane;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class CidadeServiceBean implements CidadeService{
    @Override
    public void cadastrar(Connection con) throws CampoInvalidoExceptions, SQLException {

        String municipio = JOptionPane.showInputDialog("Digite o nome da cidade");
        String uf = JOptionPane.showInputDialog("Digite a UF da cidade");

        Utils.validateGenericInput(municipio);
        Utils.validateGenericInput(uf);

        Cidade cidade = new Cidade(municipio, uf);
        CidadeDAO.create(con, cidade);
    }

    @Override
    public void delete(Connection con) {

    }

    @Override
    public List<Cidade> listAll(Connection con) throws SQLException {
        return CidadeDAO.listAll(con);
    }

    @Override
    public Cidade getById(Connection con, Integer id) throws Exception {

        Utils.validateGenericInput(id);
        return CidadeDAO.findById(id, con);

    }
}
