package org.example;

import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        GerenciadorTabelas.verificarOuCriarTabelas();
        UsuarioRepository usuarioDB = new UsuarioRepository();
        CidadesVisitadasRepository cidadesVisitadasDB = new CidadesVisitadasRepository();
        Scanner scanner = new Scanner(System.in);

        Usuario usuario;
        String cpf, senha, email, empresa;

        int opcao;
        do {
            System.out.println("\nMenu:");
            System.out.println("1. Adicionar Usuário");
            System.out.println("2. Editar Usuário");
            System.out.println("3. Excluir Usuário");
            System.out.println("4. Listar Usuarios");
            System.out.println("5. Listar Cidades por Usuário");
            System.out.println("6. Fazer Login (Necessário para Gerenciar Cidades)");
            System.out.println("0. Sair");
            System.out.print("Escolha uma opção: ");
            opcao = scanner.nextInt();
            scanner.nextLine();

            switch (opcao) {
                case 1:
                    System.out.print("Digite o CPF (Apenas Números): ");
                    cpf = scanner.nextLine();
                    System.out.print("Digite a Senha: ");
                    senha = scanner.nextLine();
                    System.out.print("Digite o Email: ");
                    email = scanner.next();
                    scanner.nextLine();
                    System.out.print("Digite a Empresa: ");
                    empresa = scanner.nextLine();
                    usuario = new Usuario(cpf, senha, email, empresa);
                    usuarioDB.adicionarUsuario(usuario);
                    System.out.println();
                    break;

                case 2:
                    System.out.print("Digite o CPF do Usuário que será Editado (Apenas Números): ");
                    cpf = scanner.nextLine();
                    System.out.print("Digite a Nova Senha: ");
                    String nova_senha = scanner.nextLine();
                    System.out.print("Digite o Novo Email: ");
                    String novo_email = scanner.next();
                    scanner.nextLine();
                    System.out.print("Digite a Nova Empresa: ");
                    String nova_empresa = scanner.nextLine();
                    usuarioDB.alterarUsuario(cpf, nova_senha, novo_email, nova_empresa);
                    System.out.println();
                    break;

                case 3:
                    System.out.print("Digite o CPF do Usuário que Deseja Excluir (Apenas Números): ");
                    cpf = scanner.next();
                    usuarioDB.excluirUsuario(cpf);
                    System.out.println();
                    break;

                case 4:
                    List<Usuario> usuarios = usuarioDB.listarUsuarios();
                    for (Usuario u : usuarios) {
                        System.out.println("---------------------------------------------------------------");
                        System.out.println("CPF: " + u.getCpf());
                        System.out.println("Senha: " + u.getSenha());
                        System.out.println("Email: " + u.getEmail());
                        System.out.println("Empresa: " + u.getEmpresa());
                        System.out.println("---------------------------------------------------------------");
                    }
                    break;

                case 5:
                    usuarioDB.imprimirCidadesVisitadasPorUsuario();
                    break;

                case 6:
                    System.out.print("Digite o CPF (Apenas Números): ");
                    cpf = scanner.next();
                    System.out.print("Digite a Senha: ");
                    senha = scanner.next();
                    if(usuarioDB.autenticarUsuario(cpf, senha)) {
                        CidadesVisitadas cidadesVisitadas;
                        String cidade, pais;
                        int ano;

                        int opcao_cidades;
                        do {
                            System.out.println("\nMenu Cidades:");
                            System.out.println("(As seguintes ações serão implementadas no usuário logado)");
                            System.out.println("1. Adicionar Cidade Visitada");
                            System.out.println("2. Editar Ano de Visitção de Cidade");
                            System.out.println("3. Excluir Visitação de Cidade");
                            System.out.println("4. Exluir Cidade");
                            System.out.println("5. Listar Cidades Visitadas");
                            System.out.println("0. Voltar ao Menu de Usuários");
                            System.out.print("Escolha uma opção: ");
                            opcao_cidades = scanner.nextInt();
                            scanner.nextLine();

                            switch (opcao_cidades) {

                                case 1:
                                    System.out.print("Digite o Nome da Cidade: ");
                                    cidade = scanner.nextLine();
                                    System.out.print("Digite o País: ");
                                    pais = scanner.nextLine();
                                    System.out.print("Digite o Ano da Visitação: ");
                                    ano = scanner.nextInt();
                                    scanner.nextLine();
                                    cidadesVisitadas = new CidadesVisitadas(cidade, pais, ano);
                                    cidadesVisitadasDB.adicionarCidadeVisitada(cidadesVisitadas, cpf);
                                    System.out.println();
                                    break;

                                case 2:
                                    System.out.print("Digite o Nome da Cidade: ");
                                    cidade = scanner.nextLine();
                                    System.out.print("Digite o País: ");
                                    pais = scanner.nextLine();
                                    System.out.print("Digite o Novo Ano da Visitação: ");
                                    int novo_ano = scanner.nextInt();
                                    scanner.nextLine();
                                    cidadesVisitadasDB.alterarAnoVisita(cpf, cidade, pais, novo_ano);
                                    System.out.println();
                                    break;

                                case 3:
                                    System.out.print("Digite o Nome da Cidade: ");
                                    cidade = scanner.nextLine();
                                    System.out.print("Digite o País: ");
                                    pais = scanner.nextLine();
                                    System.out.print("Digite o Ano da Visitação: ");
                                    ano = scanner.nextInt();
                                    scanner.nextLine();
                                    cidadesVisitadasDB.deletarVisita(cpf, cidade, pais, ano);
                                    System.out.println();
                                    break;

                                case 4:
                                    System.out.print("Digite o Nome da Cidade: ");
                                    cidade = scanner.nextLine();
                                    System.out.print("Digite o País: ");
                                    pais = scanner.nextLine();
                                    cidadesVisitadasDB.deletarCidade(cidade, pais);
                                    System.out.println();
                                    break;

                                case 5:
                                    List<CidadesVisitadas> cidades = cidadesVisitadasDB.listarCidadesVisitadas(cpf);
                                    for (CidadesVisitadas c : cidades) {
                                        System.out.println("---------------------------------------------------------------");
                                        System.out.println("Cidade: " + c.getNome());
                                        System.out.println("Pais: " + c.getPais());
                                        System.out.println("Ano: " + c.getAno());
                                        System.out.println("---------------------------------------------------------------");
                                    }
                                    break;

                                case 0:
                                    System.out.println("\nVoltando...");
                                    break;

                                default:
                                    System.out.println("Opção Invalida!");
                            }

                        } while (opcao_cidades != 0);
                    }
                    break;

                case 0:
                    System.out.println("\nSaindo...");
                    break;

                default:
                    System.out.println("Opção Invalida!");
            }

        } while (opcao != 0);
    }
}