package domain;

import domain.documento.Documento;
import repository.ListaColaborador;

import java.time.LocalDate;

public abstract class Colaborador extends Pessoa {
    //atributos
    private String cargo;
    private Integer tipoAcesso;
    //tipos: 1 - funcionário; 2 - supervisão; 3 - gerência


    //construtor padrão
    public Colaborador() {
        super();
        ListaColaborador.adicionar(this);
    }

    //construtor com argumentos
    public Colaborador(Integer identificador, String nome, String sobrenome, LocalDate dataNascimento, String cpf, String endereco, String cargo, Integer tipoAcesso) {
        super(identificador, nome, sobrenome, dataNascimento, cpf, endereco);
        this.cargo = cargo;
        this.tipoAcesso = tipoAcesso;
        ListaColaborador.adicionar(this);
    }

    //getters e setters
    public String getCargo() {
        return cargo;
    }

    public void setCargo(String cargo) {
        this.cargo = cargo;
    }

    public Integer getTipoAcesso() {
        return tipoAcesso;
    }

    public void setTipoAcesso(Integer tipoAcesso) {
        this.tipoAcesso = tipoAcesso;
    }

    //métodos

    public abstract void cadastrarDocumento(Documento documento);

    public abstract void listarDocumentos(Colaborador usuario);

    public abstract void tramitarDocumento(Integer idDocumento, Integer idSupervisor);

    public abstract void reprovarDocumento(Integer idDocumento, Integer idFuncionario);

    public abstract void aprovarDocumento(Integer idDocumento);

    public abstract void retornarDocumentoArquivado(Integer idDocumento);

    @Override
    public String toString() {
        return "Colaborador{" +
                "id: " + getId() +
                ", nome: " + getNome() +
                ", sobrenome: " + getSobrenome() +
                ", dataNascimento: " + getDataNascimento() +
                ", cpf: " + getCpf() +
                ", endereco: " + getEndereco() +
                ", cargo: " + cargo +
                ", perfilAcesso: " + tipoAcesso +
                '}';
    }

}


