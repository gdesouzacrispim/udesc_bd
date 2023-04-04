package core.service;

import entity.Cidade;

import java.io.Serializable;
import java.sql.Connection;
import java.util.List;

public interface CidadeService extends Serializable {

    void cadastrar(Connection con);

    void delete(Connection con);

    List<Cidade> listAll(Connection con);

    Cidade getById(Connection con, Integer id);
}
