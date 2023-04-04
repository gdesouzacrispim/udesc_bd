package core.service;

import entity.Movimentacao;

import java.io.Serializable;
import java.sql.Connection;
import java.util.List;

public interface MovimentacaoService extends Serializable {

    void pagamento(Connection con);

    void depostio(Connection con);

    void saca(Connection con);

    void transferencia(Connection con);

    List<Movimentacao> extrato(Connection con);

    Movimentacao comprovamte(Connection con, Integer id);
}
