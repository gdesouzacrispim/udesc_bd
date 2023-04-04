package core.service;

import java.io.Serializable;
import java.sql.Connection;
import java.util.List;

public interface ClienteService extends Serializable {

    void create(Connection con) throws Exception;

    void delete(Connection con);

    void update(Connection con);

    List<ClienteService> listAll(Connection con);

    ClienteService getById(Connection con, Integer id);
}
