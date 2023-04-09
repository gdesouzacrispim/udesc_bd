package entity;

/**
 * @author crispim
 */
public enum TipoConta {
    POUPANCA(106, "poupança"),
    CORRENTE(105, "corrente");

    private int cod;
    private String descricao;

    TipoConta(int cod, String descricao) {
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