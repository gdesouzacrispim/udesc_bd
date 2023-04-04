package core.service;

import entity.Conta;

import java.io.Serializable;
import java.sql.Connection;
import java.util.List;

public interface ContaService extends Serializable {

    void create(Connection con);

    void delete(Connection con);

    List<entity.Conta> listAll(Connection con);

    Conta getById(Connection con, Integer id);

    Conta findByPasswordAndNumber(Connection con, Integer number, Integer password);
}
