import java.util.Scanner;

public class TestaCadastro
{
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        System.out.printf("Insira a quantidade máxima de pessoas no cadastramento: ");
        CadastroPessoas cadastro = new CadastroPessoas(scanner.nextInt());
        
        String nome;
        int dia;
        int mes;
        int ano;
        int codigo;
        double salario;
        String departamento;
        
        boolean continuar = true;
        while(continuar){
            System.out.println("\n--------------------------------------------------");
            System.out.println("Menu de opções:");
            System.out.println("1. Adicionar Pessoa");
            System.out.println("2. Imprimir Cadastro");
            System.out.println("3. Sair");
            System.out.printf("Escolha uma opção: ");
            int opcao = scanner.nextInt();
            scanner.nextLine();
            
            switch (opcao){
                case 1:
                    System.out.println("\nEscolha o tipo de pessoa para adicionar:");
                    System.out.println("1. Cliente");
                    System.out.println("2. Funcionario");
                    System.out.println("3. Gerente");
                    System.out.printf("Escolha uma opção: ");
                    int tipoPessoa = scanner.nextInt();
                    scanner.nextLine();
                    
                    switch (tipoPessoa){
                        case 1:
                            System.out.print("\n\nDigite o nome do cliente: ");
                            nome = scanner.nextLine();
                            System.out.printf("Digite o dia de nascimento: ");
                            dia = scanner.nextInt();
                            System.out.printf("Digite o mês de nascimento: ");
                            mes = scanner.nextInt();
                            System.out.printf("Digite o ano de nascimento: ");
                            ano = scanner.nextInt();
                            System.out.printf("Digite o código de cliente: ");
                            codigo = scanner.nextInt();
                        
                            Cliente cliente = new Cliente(nome, dia, mes, ano, codigo);
                            cadastro.cadastraPessoa(cliente);
                            System.out.println("Cliente adicionado");
                            break;
                        
                        case 2:
                            System.out.print("\n\nDigite o nome do funcionário: ");
                            nome = scanner.nextLine();
                            System.out.printf("Digite o dia de nascimento: ");
                            dia = scanner.nextInt();
                            System.out.printf("Digite o mês de nascimento: ");
                            mes = scanner.nextInt();
                            System.out.printf("Digite o ano de nascimento: ");
                            ano = scanner.nextInt();
                            System.out.printf("Digite o salário do funcionário: ");
                            salario = scanner.nextDouble();
                            scanner.nextLine();
                            
                            Funcionario funcionario = new Funcionario(nome, dia, mes, ano, (float) salario);
                            cadastro.cadastraPessoa(funcionario);
                            System.out.println("Funcionário adicionado");
                            break;

                        case 3:
                            System.out.printf("\n\nDigite o nome do gerente: ");
                            nome = scanner.nextLine();
                            System.out.printf("Digite o dia de nascimento: ");
                            dia = scanner.nextInt();
                            System.out.printf("Digite o mês de nascimento: ");
                            mes = scanner.nextInt();
                            System.out.printf("Digite o ano de nascimento: ");
                            ano = scanner.nextInt();
                            System.out.printf("Digite o salário do gerente: ");
                            salario = scanner.nextDouble();
                            scanner.nextLine();
                            System.out.printf("Digite o departamento do gerente: ");
                            departamento = scanner.nextLine();

                            Gerente gerente = new Gerente(nome, dia, mes, ano, (float) salario, departamento);
                            cadastro.cadastraPessoa(gerente);
                            System.out.println("Gerente adicionado");
                            break;
                    }
                    break;
                    
                case 2:
                    cadastro.imprimeCadastro();
                    break;
                    
                case 3:
                    continuar = false;
                    break;
                    
                default:
                    System.out.println("Opção inválida! Tente novamente.");
                    break;
            }
        }
        
        scanner.close();
    }
}
adicionado