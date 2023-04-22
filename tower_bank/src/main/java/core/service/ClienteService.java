package core.service;

import core.exception.CampoInvalidoExceptions;
import entity.Cliente;
import entity.Conta;

import java.io.IOException;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public interface ClienteService extends Serializable {

    void create(Connection con) throws Exception;

    void delete(Connection con) throws SQLException;

    void update(Connection con, Conta conta) throws Exception;

    List<Cliente> listAll(Connection con) throws SQLException;

    Cliente getById(Connection con, Integer id) throws Exception;

    void listByCidade(Connection connection) throws SQLException, IOException, CampoInvalidoExceptions;
}
