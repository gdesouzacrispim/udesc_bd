package core.service;

import core.exception.CampoInvalidoExceptions;
import entity.Conta;
import org.neo4j.driver.Driver;

import java.io.Serializable;
import java.sql.SQLException;
import java.util.List;

public interface ContaService extends Serializable {

    void create(Driver con) throws Exception;

    void delete(Driver con) throws SQLException, CampoInvalidoExceptions;

    List<entity.Conta> listAll(Driver con);

    Conta getById(Driver con, Integer id);

    Conta findByPasswordAndNumber(Driver con, Integer number, Integer password) throws SQLException;

    Conta getByNumber(Driver con, Integer number) throws SQLException;
}
