package entity;

public class Agencia {

    private Integer id;
    private String nome;
    private String endereco;
    private String cnpj;
    private Integer codigoCidade;

    public Agencia() {
    }

    public Agencia(Integer id, String nome, String cnpj) {
        this.id = id;
        this.nome = nome;
        this.cnpj = cnpj;
    }

    public Agencia(Integer id, String nome, String endereco, String cnpj, Integer codigoCidade) {
        this.id = id;
        this.nome = nome;
        this.endereco = endereco;
        this.cnpj = cnpj;
        this.codigoCidade = codigoCidade;
    }

    public Agencia(String nome, String endereco, String cnpj, Integer codigoCidade) {
        this.nome = nome;
        this.endereco = endereco;
        this.cnpj = cnpj;
        this.codigoCidade = codigoCidade;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }

    public Integer getCodigoCidade() {
        return codigoCidade;
    }

    public void setCodigoCidade(Integer codigoCidade) {
        this.codigoCidade = codigoCidade;
    }
}
