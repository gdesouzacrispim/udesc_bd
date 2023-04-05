package entity;

/**
 * @author crispim
 */
public class Cidade {

    private Integer id;
    private String nome;
    private String UF;

    public Cidade(Integer id, String nome, String UF) {
        this.id = id;
        this.nome = nome;
        this.UF = UF;
    }

    public Cidade(String nome, String UF) {
        this.nome = nome;
        this.UF = UF;
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

    public String getUF() {
        return UF;
    }

    public void setUF(String UF) {
        this.UF = UF;
    }
}
