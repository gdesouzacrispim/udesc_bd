package entity;

/**
 * @author crispim
 */
public enum OperacaoCaixaEletronico {
    SAQUE(1, "saque"),
    DEPOSITO(2, "depósito"),
    PAGAMENTO(3, "pagamento"),
    TRANSFERENCIA(4, "transferência");

    private int cod;
    private String descricao;

    OperacaoCaixaEletronico(int cod, String descricao) {
        this.cod = cod;
        this.descricao = descricao;
    }

    public int getCod() {
        return cod;
    }

    public String getDescricao() {
        return descricao;
    }
}