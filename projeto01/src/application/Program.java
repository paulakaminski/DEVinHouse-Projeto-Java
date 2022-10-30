package application;

import domain.Colaborador;
import domain.Funcionario;
import domain.Gerente;
import domain.Supervisor;
import repository.ListaDocumento;
import repository.*;
import servico.Servico;

import java.time.LocalDate;
import java.util.Scanner;

public class Program {
    public static void main(String[] args) {
        Funcionario func = new Funcionario(
                1,
                "Oliver",
                "Rezende",
                LocalDate.of(1989, 4, 1),
                "56261653712",
                "Rua São Vicente, 918",
                "Desenvolvedor Jr.",
                1);
        Funcionario func2 = new Funcionario(
                2,
                "Augusto",
                "Silva",
                LocalDate.of(1986, 7, 24),
                "57414533760",
                "Rua Carlos Willy Boehm, 615",
                "Desenvolvedor Jr.",
                1);
        Supervisor sup = new Supervisor(
                3,
                "Victor",
                "Rocha",
                LocalDate.of(1980, 7, 16 ),
                "21999558936",
                "Rua Lino Rocha Coutinho, 629",
                "Supervisor de Desenvolvimento",
                2);
        Supervisor sup2 = new Supervisor(
                4,
                "Vanessa",
                "Rocha",
                LocalDate.of(1978, 10, 3),
                "04847154967",
                "Rua Santa Rita, 687",
                "Supervisor de Desenvolvimento",
                2);
        Gerente ger = new Gerente(
                5,
                "João",
                "Silva",
                LocalDate.of(1972, 10, 22),
                "53442006929",
                "Rua Duvoisin, 846",
                "Gerente de TI",
                3);
        Gerente ger2 = new Gerente(
                6,
                "Benedita",
                "Carvalho",
                LocalDate.of(1973, 10, 24),
                "73794715977",
                "Rua dos Baicaras, 198",
                "Gerente Geral",
                3);

        Scanner scanner = new Scanner(System.in);

        Servico servico = new Servico();

        boolean executa = true;
        boolean continua = true;

        Integer idUsuario = 1;

        Colaborador usuario = ListaColaborador.retornar(idUsuario);

        System.out.println("Olá, seja bem vindo ao DOCin!");

        while (executa) {
            System.out.print("Se você já possui cadastro, digite 1 para entrar, caso não possua, digite 2 para criar seu usuário. \nEntre a opção escolhida: ");
            Integer acesso = scanner.nextInt();

            switch (acesso) {
                case 1:
                    System.out.print("Informe o seu id: ");
                    idUsuario = scanner.nextInt();

                    try {
                        usuario = ListaColaborador.retornar(idUsuario);
                        System.out.println("Usuário " + idUsuario + " logado!");
                        System.out.println(usuario);
                    } catch (Exception exception) {
                        System.out.println("Usuário não cadastrado. Refaça a operação e informe um usuário válido, ou realize o cadastro, antes de efetuar o login.");
                        continua = false;
                    }

                    break;

                case 2:
                    servico.cadastrarUsuario();

                    System.out.print("Para logar, informe o seu id: ");
                    idUsuario = scanner.nextInt();

                    try {
                        usuario = ListaColaborador.retornar(idUsuario);
                        System.out.println("Usuário " + idUsuario + " logado!");
                        System.out.println(usuario);
                    } catch (Exception exception) {
                        System.out.println("Usuário não cadastrado. Refaça a operação e informe um usuário válido, ou realize o cadastro, antes de efetuar o login.");
                        continua = false;
                    }

                    break;

                default:
                    System.out.println("Refaça a operação e insira uma operação válida (1 - entrar ou 2 - criar usuário).");
                    continua = false;
            }

            //verifica se o usário logou, para dar continuidade no sistema
            if (continua) {

                boolean continuaOperacao = true;

                //verifica o tipo de acesso de acordo com o perfil do colaborador
                //acesso perfil funcionário
                if (usuario.getTipoAcesso() == 1) {
                    do {
                        System.out.print("Qual operação você deseja realizar? " +
                                "\n 1 - Cadastrar novo documento" +
                                "\n 2 - Listar documentos" +
                                "\n 3 - Tramitar documento" +
                                "\n 4 - Sair e logar com outro usuário" +
                                "\n 5 - Encerrar" +
                                "\n Entre a opção escolhida: ");
                        Integer operacao = scanner.nextInt();

                        switch (operacao) {
                            case 1:
                                servico.cadastrarDocumentoTela(usuario);
                                break;
                            case 2:
                                usuario.listarDocumentos(usuario);
                                break;
                            case 3:
                                servico.tramitarDocumentoTela(usuario);
                                break;
                            case 4:
                                continuaOperacao = false;
                                break;
                            case 5:
                                continuaOperacao = false;
                                continua = false;
                                executa = false;
                                break;
                            default:
                                System.out.println("Operação inválida. Refaça a operação e selecione uma opção válida.");
                                continua = false;
                                executa = false;
                        }
                    } while (continuaOperacao);
                }

                //acesso perfil supervisor
                if (usuario.getTipoAcesso() == 2) {
                    do {
                        System.out.print("Qual operação você deseja realizar? " +
                                "\n 1 - Cadastrar novo documento" +
                                "\n 2 - Listar documentos" +
                                "\n 3 - Tramitar documento" +
                                "\n 4 - Reprovar documento" +
                                "\n 5 - Sair e logar com outro usuário" +
                                "\n 6 - Encerrar" +
                                "\n Entre a opção escolhida: ");
                        Integer operacao = scanner.nextInt();

                        switch (operacao) {
                            case 1:
                                servico.cadastrarDocumentoTela(usuario);
                                break;
                            case 2:
                                usuario.listarDocumentos(usuario);
                                break;
                            case 3:
                                servico.tramitarDocumentoTela(usuario);
                                break;
                            case 4:
                                servico.reprovarDocumentoTela(usuario);
                                break;
                            case 5:
                                continuaOperacao = false;
                                break;
                            case 6:
                                continuaOperacao = false;
                                continua = false;
                                executa = false;
                                break;
                            default:
                                System.out.println("Operação inválida. Refaça a operação e selecione uma opção válida.");
                                continua = false;
                                executa = false;
                        }
                    } while (continuaOperacao);
                }

                //acesso perfil gerente
                if (usuario.getTipoAcesso() == 3) {
                    do {
                        System.out.print("Qual operação você deseja realizar? " +
                                "\n 1 - Listar documentos" +
                                "\n 2 - Aprovar documento" +
                                "\n 3 - Reverter aprovação documento" +
                                "\n 4 - Sair e logar com outro usuário" +
                                "\n 5 - Encerrar" +
                                "\n Entre a opção escolhida: ");
                        Integer operacao = scanner.nextInt();

                        switch (operacao) {

                            case 1:
                                usuario.listarDocumentos(usuario);
                                break;
                            case 2:
                                servico.aprovarDocumentoTela(usuario);
                                break;
                            case 3:
                                servico.retornarDocumentoArquivadoTela(usuario);
                                break;
                            case 4:
                                continuaOperacao = false;
                                break;
                            case 5:
                                continuaOperacao = false;
                                continua = false;
                                executa = false;
                                break;
                            default:
                                System.out.println("Operação inválida. Refaça a operação e selecione uma opção válida.");
                                continua = false;
                                executa = false;
                        }
                    } while (continuaOperacao);
                }

            } else {
                executa = false;
            }
        }
        System.out.println("Lista de colaboradores (todos): ");
        System.out.println(ListaColaborador.retornarTodos().toString());
        System.out.println("Lista de Funcionários: ");
        System.out.println(ListaFuncionario.retornarTodos().toString());
        System.out.println("Lista de supervisores");
        System.out.println(ListaSupervisor.retornarTodos().toString());
        System.out.println("Lista de gerentes:");
        System.out.println(ListaGerente.retornarTodos().toString());
        System.out.println("Lista de documentos: ");
        System.out.println(ListaDocumento.retornarTodos().toString());
        System.out.println("Total de documentos tramitados: " + (long) ListaDocumento.retornarTodos().size());
    }
}