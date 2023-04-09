package dto;

public class AgenciaCidadeDTO {

    private String nomeAgencia;
    private String enderecoAgencia;
    private String CNPJAgencia;
    private String nomeCidade;

    public AgenciaCidadeDTO(String nomeAgencia, String enderecoAgencia, String CNPJAgencia, String nomeCidade) {
        this.nomeAgencia = nomeAgencia;
        this.enderecoAgencia = enderecoAgencia;
        this.CNPJAgencia = CNPJAgencia;
        this.nomeCidade = nomeCidade;
    }

    public String getNomeAgencia() {
        return nomeAgencia;
    }

    public void setNomeAgencia(String nomeAgencia) {
        this.nomeAgencia = nomeAgencia;
    }

    public String getEnderecoAgencia() {
        return enderecoAgencia;
    }

    public void setEnderecoAgencia(String enderecoAgencia) {
        this.enderecoAgencia = enderecoAgencia;
    }

    public String getCNPJAgencia() {
        return CNPJAgencia;
    }

    public void setCNPJAgencia(String CNPJAgencia) {
        this.CNPJAgencia = CNPJAgencia;
    }

    public String getNomeCidade() {
        return nomeCidade;
    }

    public void setNomeCidade(String nomeCidade) {
        this.nomeCidade = nomeCidade;
    }

    @Override
    public String toString() {
        return "\n" +
                " nome Agencia='" + nomeAgencia + '\'' +
                "\n endereço da agência='" + enderecoAgencia + '\'' +
                "\n CNPJ da Agância='" + CNPJAgencia + '\'' +
                "\n nome da cidade='" + nomeCidade + '\'' +
                "\n ..................................";
    }
}
