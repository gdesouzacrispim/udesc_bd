package core.service;

import core.exception.CampoInvalidoExceptions;
import entity.Agencia;
import org.neo4j.driver.Driver;

import java.io.IOException;
import java.io.Serializable;
import java.sql.SQLException;
import java.util.List;

public interface AgenciaService extends Serializable {

    void create(Driver con) throws Exception;

    void delete(Driver con) throws SQLException, CampoInvalidoExceptions;

    void update(Driver con) throws Exception;

    List<Agencia> listAll(Driver con) throws SQLException;

    Agencia getById(Driver con, Integer id) throws CampoInvalidoExceptions, SQLException;

    void listByCidade(Driver con) throws SQLException, IOException, CampoInvalidoExceptions;
}
