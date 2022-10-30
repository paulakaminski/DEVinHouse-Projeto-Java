package domain;

import domain.documento.Documento;
import repository.ListaColaborador;
import repository.ListaDocumento;
import repository.ListaSupervisor;

import java.time.LocalDate;
import java.util.*;

public class Supervisor extends Colaborador {
    protected List<Documento> documentosCriados = new ArrayList<>();
    protected List<Documento> documentosAprovacaoNivel1 = new ArrayList<>();

    public Supervisor(Integer identificador, String nome, String sobrenome, LocalDate dataNascimento, String cpf, String endereco, String cargo, Integer tipoAcesso) {
        super(identificador, nome, sobrenome, dataNascimento, cpf, endereco, cargo, tipoAcesso);
        ListaSupervisor.adicionar(this);
    }

    //métodos
    @Override
    public void cadastrarDocumento(Documento documento) {
        documentosCriados.add(documento);
    }

    @Override
    public void listarDocumentos(Colaborador usuario) {
        if (documentosAprovacaoNivel1.isEmpty()) {
            System.out.println("Você não possui documentos pendentes para aprovação.");
        } else {
            System.out.println("Documentos enviados para sua aprovaçao (pendentes): ");
            for (Documento documento : documentosAprovacaoNivel1) {
                if (Objects.equals(documento.getIdColabResponsavel(), usuario.getId())) {
                    System.out.println(documento);
                }
            }
        }

        if (!documentosCriados.isEmpty()) {
            System.out.println("Documentos cadastrados por você - não enviados para aprovação: ");
            for (Documento documento : documentosCriados) {
                if (Objects.equals(documento.getIdColabCriacao(), usuario.getId())
                        && documento.getIdColabResponsavel() == usuario.getId()) {
                    System.out.println(documento);
                }
            }
        }
    }

    @Override
    public void tramitarDocumento(Integer idDocumento, Integer idAprovador) {
        Gerente aprovador = (Gerente) ListaColaborador.retornar(idAprovador);
        Documento documentoTramitar = ListaDocumento.retornar(idDocumento);
        Date now = new Date();

        //tramitar documento criado pelo próprio supervisor
        Iterator<Documento> iterator1 = documentosCriados.iterator();

        while (iterator1.hasNext()) {
            if (iterator1.next().equals(documentoTramitar)) {
                documentoTramitar.setIdColabResponsavel(idAprovador);
                documentoTramitar.setDataTramitacao(now);
                aprovador.documentosAprovacaoNivel2.add(documentoTramitar);
                System.out.println("Documento enviado para aprovação da gerência.");
                System.out.println(documentoTramitar);
                iterator1.remove();
            }
        }

        //tramitar documento recebido de funcionário
        Iterator<Documento> iterator2 = documentosAprovacaoNivel1.iterator();

        while (iterator2.hasNext()) {
            if (iterator2.next().equals(documentoTramitar)) {
                documentoTramitar.setIdColabResponsavel(idAprovador);
                documentoTramitar.setDataTramitacao(now);
                aprovador.documentosAprovacaoNivel2.add(documentoTramitar);
                System.out.println("Documento enviado para aprovação da gerência.");
                System.out.println(documentoTramitar);
                iterator2.remove();
            }
        }
    }

    public void reprovarDocumento(Integer idDocumento, Integer idFuncionario) {
        Funcionario criador = (Funcionario) ListaColaborador.retornar(idFuncionario);
        Documento documentoReprovar = ListaDocumento.retornar(idDocumento);
        Date now = new Date();

        Iterator<Documento> iterator = documentosAprovacaoNivel1.iterator();

        while (iterator.hasNext()) {
            if (iterator.next().equals(documentoReprovar)) {
                documentoReprovar.setIdColabResponsavel(idFuncionario);
                documentoReprovar.setDataTramitacao(now);
                criador.documentosCriados.add(documentoReprovar);
                System.out.println("Documento reprovado e devolvido para o colaborador.");
                System.out.println(documentoReprovar);
                iterator.remove();
            }
        }
    }

    @Override
    public void aprovarDocumento(Integer idDocumento) {
    }

    @Override
    public void retornarDocumentoArquivado(Integer idDocumento) {
    }
}