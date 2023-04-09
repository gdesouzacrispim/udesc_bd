package core.service;

import core.exception.CampoInvalidoExceptions;
import entity.Agencia;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public interface AgenciaService extends Serializable {

    void create(Connection con) throws Exception;

    void delete(Connection con) throws SQLException, CampoInvalidoExceptions;

    void update(Connection con) throws Exception;

    List<Agencia> listAll(Connection con) throws SQLException;

    Agencia getById(Connection con, Integer id) throws CampoInvalidoExceptions, SQLException;

    void listByCidade(Connection con) throws SQLException, IOException;
}
