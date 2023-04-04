package core.service;

import entity.Agencia;

import java.io.Serializable;
import java.sql.Connection;
import java.util.List;

public interface AgenciaService extends Serializable {

    void create(Connection con);

    void delete(Connection con);

    void update(Connection con);

    List<Agencia> listAll(Connection con);

    Agencia getById(Connection con, Integer id);


}
