package dto;

import entity.TipoConta;

public class ClienteCidadeDTO {

    private String nomeCliente;
    private Integer numeroDaConta;
    private Integer tipoDaConta;
    private String nomeCidade;

    public String getNomeCliente() {
        return nomeCliente;
    }

    public void setNomeCliente(String nomeCliente) {
        this.nomeCliente = nomeCliente;
    }

    public Integer getNumeroDaConta() {
        return numeroDaConta;
    }

    public void setNumeroDaConta(Integer numeroDaConta) {
        this.numeroDaConta = numeroDaConta;
    }

    public Integer getTipoDaConta() {
        return tipoDaConta;
    }

    public void setTipoDaConta(Integer tipoDaConta) {
        this.tipoDaConta = tipoDaConta;
    }

    public String getNomeCidade() {
        return nomeCidade;
    }

    public void setNomeCidade(String nomeCidade) {
        this.nomeCidade = nomeCidade;
    }

    public ClienteCidadeDTO() {
    }

    public ClienteCidadeDTO(String nomeCliente, Integer numeroDaConta, Integer tipoDaConta, String nomeCidade) {
        this.nomeCliente = nomeCliente;
        this.numeroDaConta = numeroDaConta;
        this.tipoDaConta = tipoDaConta;
        this.nomeCidade = nomeCidade;
    }

    @Override
    public String toString() {
        return String.format("%-30s%-15s%-15s%s\n\n", nomeCliente, numeroDaConta, tipoConta(tipoDaConta), nomeCidade);
    }

    private String tipoConta(Integer tc) {
        TipoConta tipoConta = null;
        for (TipoConta tipo : TipoConta.values()) {
            if (tipo.getCod() == tc) {
                tipoConta = tipo;
                break;
            }
        }
        return tipoConta.getDescricao();
    }
}
