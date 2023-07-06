package core.service;

import core.exception.CampoInvalidoExceptions;
import entity.Cliente;
import entity.Conta;
import org.neo4j.driver.Driver;

import java.io.IOException;
import java.io.Serializable;
import java.sql.SQLException;
import java.util.List;

public interface ClienteService extends Serializable {

    void create(Driver con) throws Exception;

    void delete(Driver con) throws SQLException;

    void update(Driver con, Conta conta) throws Exception;

    List<Cliente> listAll(Driver con) throws SQLException;

    Cliente getById(Driver con, Integer id) throws Exception;

    void listByCidade(Driver driver) throws SQLException, IOException, CampoInvalidoExceptions;
}
