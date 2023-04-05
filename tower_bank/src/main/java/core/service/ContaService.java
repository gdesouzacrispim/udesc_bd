package core.service;

import core.exception.CampoInvalidoExceptions;
import entity.Conta;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public interface ContaService extends Serializable {

    void create(Connection con) throws Exception;

    void delete(Connection con) throws SQLException, CampoInvalidoExceptions;

    List<entity.Conta> listAll(Connection con);

    Conta getById(Connection con, Integer id);

    Conta findByPasswordAndNumber(Connection con, Integer number, Integer password);
}
