package domain;

import domain.documento.Documento;
import repository.ListaDocumento;
import repository.ListaGerente;

import java.time.LocalDate;
import java.util.*;

public class Gerente extends Colaborador {
    protected List<Documento> documentosAprovacaoNivel2 = new ArrayList<>();
    protected List<Documento> documentosAprovados = new ArrayList<>();

    public Gerente(Integer identificador, String nome, String sobrenome, LocalDate dataNascimento, String cpf, String endereco, String cargo, Integer tipoAcesso) {
        super(identificador, nome, sobrenome, dataNascimento, cpf, endereco, cargo, tipoAcesso);
        ListaGerente.adicionar(this);
    }

    @Override
    public void cadastrarDocumento(Documento documento) {
    }

    //métodos
    @Override
    public void listarDocumentos(Colaborador usuario) {
        if (documentosAprovacaoNivel2.isEmpty()) {
            System.out.println("Você não possui documentos pendentes para aprovação.");
        } else {
            System.out.println("Documentos enviados para sua aprovaçao (pendentes): ");
            for (Documento documento : documentosAprovacaoNivel2) {
                if (Objects.equals(documento.getStatus(), "Ativo")) {
                    System.out.println(documento);
                }
            }
        }

        if (!documentosAprovados.isEmpty()) {
            System.out.println("Documentos aprovados e arquivados: ");
            for (Documento documento : documentosAprovados) {
                System.out.println(documento);
            }
        }

        System.out.println("Relação de todos os documentos: ");
        System.out.println(ListaDocumento.retornarTodos().toString());
    }

    @Override
    public void tramitarDocumento(Integer idDocumento, Integer idSupervisor) {
    }

    @Override
    public void reprovarDocumento(Integer idDocumento, Integer idFuncionario) {
    }

    @Override
    public void aprovarDocumento(Integer idDocumento) {
        Documento documentoAprovar = ListaDocumento.retornar(idDocumento);
        Date now = new Date();

        Iterator<Documento> iterator = documentosAprovacaoNivel2.iterator();

        while (iterator.hasNext()) {
            if (iterator.next().equals(documentoAprovar)) {
                documentoAprovar.setStatus("Arquivado");
                documentoAprovar.setDataTramitacao(now);
                documentosAprovados.add(documentoAprovar);
                iterator.remove();
                System.out.println("Documento aprovado e arquivado.");
                System.out.println(documentoAprovar);
            }
        }
    }

    @Override
    public void retornarDocumentoArquivado(Integer idDocumento) {
        Documento documentoRetornar = ListaDocumento.retornar(idDocumento);
        Date now = new Date();

        Iterator<Documento> iterator = documentosAprovados.iterator();

        while (iterator.hasNext()) {
            if (iterator.next().equals(documentoRetornar)) {
                documentoRetornar.setStatus("Ativo");
                documentoRetornar.setDataTramitacao(now);
                documentosAprovacaoNivel2.add(documentoRetornar);
                iterator.remove();
                System.out.println("Documento retornado para situação 'ativo'.");
                System.out.println(documentoRetornar);
            }
        }
    }
}
