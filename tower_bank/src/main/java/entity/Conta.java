package entity;

public class Conta {

    private Integer id;
    private Integer cliente;
    private Integer agencia;
    private Integer tipo;
    private Double saldo;
    private Integer numero;
    private Integer senha;

    public Conta(Integer id, Integer cliente, Integer agencia, Integer tipo, Double saldo, Integer numero) {
        this.id = id;
        this.cliente = cliente;
        this.agencia = agencia;
        this.tipo = tipo;
        this.saldo = saldo;
        this.numero = numero;
    }

    public Conta(Integer id, Integer cliente, Integer agencia, Integer tipo, Double saldo, Integer numero, Integer senha) {
        this.id = id;
        this.cliente = cliente;
        this.agencia = agencia;
        this.tipo = tipo;
        this.saldo = saldo;
        this.numero = numero;
        this.senha = senha;
    }

    public Conta(Integer cliente, Integer agencia, Integer tipo, Double saldo, Integer numero, Integer senha) {
        this.cliente = cliente;
        this.agencia = agencia;
        this.tipo = tipo;
        this.saldo = saldo;
        this.numero = numero;
        this.senha = senha;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getCliente() {
        return cliente;
    }

    public void setCliente(Integer cliente) {
        this.cliente = cliente;
    }

    public Integer getAgencia() {
        return agencia;
    }

    public void setAgencia(Integer agencia) {
        this.agencia = agencia;
    }

    public Integer getTipo() {
        return tipo;
    }

    public void setTipo(Integer tipo) {
        this.tipo = tipo;
    }

    public Double getSaldo() {
        return saldo;
    }

    public void setSaldo(Double saldo) {
        this.saldo = saldo;
    }

    public Integer getNumero() {
        return numero;
    }

    public void setNumero(Integer numero) {
        this.numero = numero;
    }

    public Integer getSenha() {
        return senha;
    }

    public void setSenha(Integer senha) {
        this.senha = senha;
    }
}
