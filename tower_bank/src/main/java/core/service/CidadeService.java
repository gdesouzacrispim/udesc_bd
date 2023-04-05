package core.service;

import core.exception.CampoInvalidoExceptions;
import entity.Cidade;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public interface CidadeService extends Serializable {

    void cadastrar(Connection con) throws CampoInvalidoExceptions, SQLException;

    void delete(Connection con);

    List<Cidade> listAll(Connection con) throws SQLException;

    Cidade getById(Connection con, Integer id) throws Exception;
}
