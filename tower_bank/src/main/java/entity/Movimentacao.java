package entity;

import java.util.Date;

/**
 * @author crispim
 */
public class Movimentacao {

    private Integer id;
    private Integer autor;
    private int operacao;
    private Integer contaDestino;
    private Integer agencia;
    private Date data;
    private Double valor;
    private String descricao;

    public Movimentacao(Date data, Double valor, String descricao) {
        this.data = data;
        this.valor = valor;
        this.descricao = descricao;
    }

    public Movimentacao(Integer autor, int operacao, Integer contaDestino, Integer agencia, Date data, Double valor, String descricao) {
        this.autor = autor;
        this.operacao = operacao;
        this.contaDestino = contaDestino;
        this.agencia = agencia;
        this.data = data;
        this.valor = valor;
        this.descricao = descricao;
    }

    public Double getValor() {
        return valor;
    }

    public void setValor(Double valor) {
        this.valor = valor;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getAutor() {
        return autor;
    }

    public void setAutor(Integer autor) {
        this.autor = autor;
    }

    public int getOperacao() {
        return operacao;
    }

    public void setOperacao(int operacao) {
        this.operacao = operacao;
    }

    public Integer getContaDestino() {
        return contaDestino;
    }

    public void setContaDestino(Integer contaDestino) {
        this.contaDestino = contaDestino;
    }

    public Integer getAgencia() {
        return agencia;
    }

    public void setAgencia(Integer agencia) {
        this.agencia = agencia;
    }

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    @Override
    public String toString() {
        return String.format("%-30s%-50s%10.2f\n\n", data, descricao, valor);
    }
}
