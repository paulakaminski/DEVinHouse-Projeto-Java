package domain.documento;

import repository.ListaDocumento;

import java.util.Date;

public class Documento {
    //atributos
    private static Integer contador = 1;
    private Integer id;
    private Integer idColabCriacao;
    private Integer idColabResponsavel;
    private String link;
    private String status;

    private Date dataCriacao;
    private Date dataTramitacao;

    Date now = new Date();

    //construtor padrão
    public Documento() {
    }

    //construtor com argumentos
    public Documento(Integer id, Integer idColabResponsavel, Integer idColabCriacao, String link, String status, Date dataCriacao, Date dataTramitacao) {
        this.id = contador++;
        this.idColabResponsavel = idColabResponsavel;
        this.idColabCriacao = idColabCriacao;
        this.link = link;
        this.status = status;
        this.dataCriacao = now;
        this.dataTramitacao = now;
        ListaDocumento.adicionar(this);
    }

    //getters e setters

    public Integer getId() {
        return id;
    }

    public Integer getIdColabResponsavel() {
        return idColabResponsavel;
    }

    public Integer getIdColabCriacao() {
        return idColabCriacao;
    }

    public String getLink() {
        return link;
    }

    public String getStatus() {
        return status;
    }

    public Date getDataCriacao() {
        return dataCriacao;
    }

    public Date getDataTramitacao() {
        return dataTramitacao;
    }

    public void setId(Integer id) {
        this.id = contador++;
    }

    public void setIdColabResponsavel(Integer idColabResponsavel) {
        this.idColabResponsavel = idColabResponsavel;
    }

    public void setIdColabCriacao(Integer idColabCriacao) {
        this.idColabCriacao = idColabCriacao;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setDataCriacao(Date dataCriacao) {
        this.dataCriacao = dataCriacao;
    }

    public void setDataTramitacao(Date dataTramitacao) {
        this.dataTramitacao = dataTramitacao;
    }

    //métodos

    @Override
    public String toString() {
        return "Documento{" +
                "identificador: " + id +
                ", idColaboradorCriacao: " + idColabCriacao +
                ", idColaboradorResponsável: " + idColabResponsavel +
                ", link: " + link +
                ", status: " + status +
                ", dataCriacao: " + dataCriacao +
                ", dataTramitacao: " + dataTramitacao +
                '}';
    }
}
