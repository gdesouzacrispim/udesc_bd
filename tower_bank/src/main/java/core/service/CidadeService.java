package core.service;

import core.exception.CampoInvalidoExceptions;
import entity.Cidade;
import org.neo4j.driver.Driver;

import java.io.Serializable;
import java.sql.SQLException;
import java.util.List;

public interface CidadeService extends Serializable {

    void cadastrar(Driver con) throws CampoInvalidoExceptions, SQLException;

    void delete(Driver con) throws SQLException, CampoInvalidoExceptions;

    List<Cidade> listAll(Driver con) throws SQLException;

    Cidade getById(Driver con, Integer id) throws Exception;
}
