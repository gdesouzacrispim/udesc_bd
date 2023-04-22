package dto;

import java.util.Date;

public class ExtratoDTO {

    private Date date;
    private String historico;
    private Double valor;

    public ExtratoDTO(Date date, String historico, Double valor) {
        this.date = date;
        this.historico = historico;
        this.valor = valor;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getHistorico() {
        return historico;
    }

    public void setHistorico(String historico) {
        this.historico = historico;
    }

    public Double getValor() {
        return valor;
    }

    public void setValor(Double valor) {
        this.valor = valor;
    }
}
