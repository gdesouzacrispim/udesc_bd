package core.service;

import entity.Conta;
import entity.Movimentacao;

import java.io.Serializable;
import java.sql.Connection;
import java.util.List;

public interface MovimentacaoService extends Serializable {

    void deposito(Connection con, Conta conta) throws Exception;

    void saca(Connection con, Conta conta) throws Exception;

    void transferencia(Connection con, Conta conta) throws Exception;

    List<Movimentacao> extrato(Connection con, Conta conta) throws Exception;
}
