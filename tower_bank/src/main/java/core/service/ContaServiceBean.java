package core.service;

import entity.Conta;

import java.sql.Connection;
import java.util.List;

public class ContaServiceBean implements ContaService {
    @Override
    public void create(Connection con) {

    }

    @Override
    public void delete(Connection con) {

    }

    @Override
    public List<Conta> listAll(Connection con) {
        return null;
    }

    @Override
    public Conta getById(Connection con, Integer id) {
        return null;
    }

    @Override
    public Conta findByPasswordAndNumber(Connection con, Integer number, Integer password) {
        return null;
    }
}
