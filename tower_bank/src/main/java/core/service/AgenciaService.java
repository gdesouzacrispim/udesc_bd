package core.service;

import core.exception.CampoInvalidoExceptions;
import entity.Agencia;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public interface AgenciaService extends Serializable {

    void create(Connection con) throws Exception;

    void delete(Connection con);

    void update(Connection con);

    List<Agencia> listAll(Connection con);

    Agencia getById(Connection con, Integer id) throws CampoInvalidoExceptions, SQLException;


}
