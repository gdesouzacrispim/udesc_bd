package core.service;

import entity.Cliente;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public interface ClienteService extends Serializable {

    void create(Connection con) throws Exception;

    void delete(Connection con) throws SQLException;

    void update(Connection con);

    List<Cliente> listAll(Connection con) throws SQLException;

    Cliente getById(Connection con, Integer id) throws Exception;
}
