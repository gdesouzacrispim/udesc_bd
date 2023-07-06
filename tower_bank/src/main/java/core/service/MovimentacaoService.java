package core.service;

import entity.Conta;
import entity.Movimentacao;
import org.neo4j.driver.Driver;

import java.io.Serializable;
import java.util.List;

public interface MovimentacaoService extends Serializable {

    void deposito(Driver con, Conta conta) throws Exception;

    void saca(Driver con, Conta conta) throws Exception;

    void transferencia(Driver con, Conta conta) throws Exception;

    List<Movimentacao> extrato(Driver con, Conta conta) throws Exception;
}
