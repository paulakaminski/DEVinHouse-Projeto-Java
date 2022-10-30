package servico;

import domain.Colaborador;
import domain.Funcionario;
import domain.Gerente;
import domain.Supervisor;
import domain.documento.Documento;
import repository.ListaColaborador;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Scanner;

public class Servico {
    public void cadastrarUsuario() {
        Scanner scanner = new Scanner(System.in);

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        System.out.print("Informe o seu nome: ");
        String nome = scanner.nextLine();
        System.out.print("Informe o seu sobrenome: ");
        String sobrenome = scanner.nextLine();
        System.out.print("Informe sua data de nascimento (dd/mm/aaaa): ");
        LocalDate dataNascimento = LocalDate.parse(scanner.nextLine(), formatter);
        System.out.print("Informe o seu CPF (apenas números): ");
        String cpf = scanner.nextLine();
        System.out.print("Informe o seu endereço: ");
        String endereco = scanner.nextLine();
        System.out.print("Informe o seu cargo: ");
        String cargo = scanner.next();

        //verificar tipo acesso
        System.out.print("Informe o tipo do seu acesso: " +
                "\n 1 - Funcionário" +
                "\n 2 - Supervisor" +
                "\n 3 - Gerente" +
                "\n Entre a opção escolhida: ");
        Integer tipoAcesso = scanner.nextInt();

        switch (tipoAcesso) {
            case 1:
                Funcionario func = new Funcionario(0, nome, sobrenome, dataNascimento, cpf, endereco, cargo, tipoAcesso);
                func.validaCpf(cpf);
                break;
            case 2:
                Supervisor sup = new Supervisor(0, nome, sobrenome, dataNascimento, cpf, endereco, cargo, tipoAcesso);
                sup.validaCpf(cpf);
                break;
            case 3:
                Gerente ger = new Gerente(0, nome, sobrenome, dataNascimento, cpf, endereco, cargo, tipoAcesso);
                ger.validaCpf(cpf);
                break;
        }

        System.out.println("Usuário cadastrado com sucesso");
        System.out.println(ListaColaborador.retornarUltimo().toString());
    }

    public void cadastrarDocumentoTela(Colaborador usuario) {
        Scanner scanner = new Scanner(System.in);

        Date now = new Date();

        System.out.print("Insira o link do documento: ");
        String link = scanner.next();

        Documento documento = new Documento(0, usuario.getId(), usuario.getId(), link, "Ativo", now, now);
        usuario.cadastrarDocumento(documento);
        System.out.println("Documento cadastrado com sucesso.");
        System.out.println(documento);
    }

    public void tramitarDocumentoTela(Colaborador usuario) {
        Scanner scanner = new Scanner(System.in);

        try {
            System.out.print("Informe o id do documento que você deseja enviar para aprovação: ");
            Integer idDocumento = scanner.nextInt();
            System.out.print("Informe o id do aprovador: ");
            Integer idAprovador = scanner.nextInt();

            usuario.tramitarDocumento(idDocumento, idAprovador);
        } catch (ClassCastException exception) {
            System.out.println("ERRO: você só pode tramitar um documento para aprovação do próximo nível (supervisão/gerência).");
        }

    }

    public void reprovarDocumentoTela(Colaborador usuario) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Informe o id do documento reprovado: ");
        Integer idDocumento = scanner.nextInt();
        System.out.print("Informe o id do colaborador criador do documento: ");
        Integer idCriador = scanner.nextInt();

        usuario.reprovarDocumento(idDocumento, idCriador);
    }

    public void aprovarDocumentoTela(Colaborador usuario) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Informe o id do documento a ser aprovado/arquivado: ");
        Integer idDocumento = scanner.nextInt();

        usuario.aprovarDocumento(idDocumento);
    }

    public void retornarDocumentoArquivadoTela(Colaborador usuario) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Informe o id do documento a ser retornado: ");
        Integer idDocumento = scanner.nextInt();

        usuario.retornarDocumentoArquivado(idDocumento);
    }
}
