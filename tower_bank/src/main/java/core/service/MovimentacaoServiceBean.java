package core.service;

import entity.Movimentacao;

import java.sql.Connection;
import java.util.List;

public class MovimentacaoServiceBean implements MovimentacaoService{
    @Override
    public void pagamento(Connection con) {

    }

    @Override
    public void depostio(Connection con) {

    }

    @Override
    public void saca(Connection con) {

    }

    @Override
    public void transferencia(Connection con) {

    }

    @Override
    public List<Movimentacao> extrato(Connection con) {
        return null;
    }

    @Override
    public Movimentacao comprovamte(Connection con, Integer id) {
        return null;
    }
}
