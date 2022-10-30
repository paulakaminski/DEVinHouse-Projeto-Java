package domain;

import domain.documento.Documento;
import repository.ListaColaborador;
import repository.ListaDocumento;
import repository.ListaFuncionario;

import java.time.LocalDate;
import java.util.*;

public class Funcionario extends Colaborador {
    protected List<Documento> documentosCriados = new ArrayList<>();

    //construtor
    public Funcionario(Integer identificador, String nome, String sobrenome, LocalDate dataNascimento, String cpf, String endereco, String cargo, Integer tipoAcesso) {
        super(identificador, nome, sobrenome, dataNascimento, cpf, endereco, cargo, tipoAcesso);
        ListaFuncionario.adicionar(this);
    }

    //métodos
    @Override
    public void cadastrarDocumento(Documento documento) {
        documentosCriados.add(documento);
    }

    public void listarDocumentos(Colaborador usuario) {
        if (documentosCriados.isEmpty()) {
            System.out.println("Você não possui documentos pendentes para envio.");
        } else {
            System.out.println("Documentos cadastrados por você - não enviados para aprovação: ");
            for (Documento documento : documentosCriados) {
                System.out.println(documento);
            }
        }
    }

    @Override
    public void tramitarDocumento(Integer idDocumento, Integer idAprovador) {
        Supervisor aprovador = (Supervisor) ListaColaborador.retornar(idAprovador);
        Documento documentoTramimtar = ListaDocumento.retornar(idDocumento);
        Date now = new Date();

        Iterator<Documento> iterator = documentosCriados.iterator();

        while (iterator.hasNext()) {
            if (iterator.next().equals(documentoTramimtar)) {
                documentoTramimtar.setIdColabResponsavel(idAprovador);
                documentoTramimtar.setDataTramitacao(now);
                aprovador.documentosAprovacaoNivel1.add(documentoTramimtar);
                System.out.println("Documento enviado para aprovação da supervisão.");
                System.out.println(documentoTramimtar);
                iterator.remove();
            }
        }
    }

    @Override
    public void reprovarDocumento(Integer idDocumento, Integer idFuncionario) {
    }

    @Override
    public void aprovarDocumento(Integer idDocumento) {
    }

    @Override
    public void retornarDocumentoArquivado(Integer idDocumento) {
    }
}
