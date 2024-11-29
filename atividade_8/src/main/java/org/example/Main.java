package org.example;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        ProdutoController controller = new ProdutoController();
        Scanner scanner = new Scanner(System.in);

        // Inserindo produtos iniciais
        controller.adicionarProduto(new Produto(1, "Informática", "Notebook Dell", 2.5, 10, UnidadeMedida.KG));
        controller.adicionarProduto(new Produto(2, "Limpeza", "Detergente Líquido", 1.0, 50, UnidadeMedida.LITRO));
        controller.adicionarProduto(new Produto(3, "Construção", "Corda", 3.0, 5, UnidadeMedida.METRO));
        controller.adicionarProduto(new Produto(4, "Alimentação", "Pão Integral", 0.5, 100, UnidadeMedida.KG));
        controller.adicionarProduto(new Produto(5, "Ferramentas", "Martelo", 1.2, 20, UnidadeMedida.KG));

        int opcao;
        do {
            System.out.println("\nMenu:");
            System.out.println("1. Listar produtos");
            System.out.println("2. Adicionar produto");
            System.out.println("3. Alterar produto");
            System.out.println("4. Excluir produto");
            System.out.println("5. Exibir detalhes de um produto");
            System.out.println("0. Sair");
            System.out.print("Escolha uma opção: ");
            opcao = scanner.nextInt();

            switch (opcao) {
                case 1:
                    System.out.println("\nProdutos cadastrados:");
                    for (Produto produto : controller.listarProdutos()) {
                        System.out.println(produto);
                    }
                    break;
                case 2:
                    System.out.print("\nDigite o ID: ");
                    int id = scanner.nextInt();
                    scanner.nextLine(); // Consumir nova linha
                    System.out.print("Digite o tipo: ");
                    String tipo = scanner.nextLine();
                    System.out.print("Digite a descrição: ");
                    String descricao = scanner.nextLine();
                    System.out.print("Digite o peso: ");
                    double peso = scanner.nextDouble();
                    System.out.print("Digite a quantidade: ");
                    int quantidade = scanner.nextInt();
                    scanner.nextLine(); // Consumir nova linha
                    System.out.print("Digite a unidade de medida (KG, LITRO, METRO, METRO_QUADRADO): ");
                    String unidade = scanner.nextLine().toUpperCase();
                    UnidadeMedida unidadeMedida = UnidadeMedida.valueOf(unidade);
                    Produto novoProduto = new Produto(id, tipo, descricao, peso, quantidade, unidadeMedida);
                    controller.adicionarProduto(novoProduto);
                    break;
                case 3:
                    System.out.print("\nDigite o ID do produto a ser alterado: ");
                    int idAlterar = scanner.nextInt();
                    scanner.nextLine(); // Consumir nova linha
                    Produto produtoAlterado = controller.buscarProdutoPorId(idAlterar);
                    if (produtoAlterado != null) {
                        System.out.println("\nProduto encontrado: " + produtoAlterado);
                        System.out.print("Digite o novo tipo: ");
                        String novoTipo = scanner.nextLine();
                        System.out.print("Digite a nova descrição: ");
                        String novaDescricao = scanner.nextLine();
                        System.out.print("Digite o novo peso: ");
                        double novoPeso = scanner.nextDouble();
                        System.out.print("Digite a nova quantidade: ");
                        int novaQuantidade = scanner.nextInt();
                        scanner.nextLine(); // Consumir nova linha
                        System.out.print("Digite a nova unidade de medida (KG, LITRO, METRO, METRO_QUADRADO): ");
                        String novaUnidade = scanner.nextLine().toUpperCase();
                        UnidadeMedida novaUnidadeMedida = UnidadeMedida.valueOf(novaUnidade);
                        produtoAlterado.setTipo(novoTipo);
                        produtoAlterado.setDescricao(novaDescricao);
                        produtoAlterado.setPeso(novoPeso);
                        produtoAlterado.setQuantidade(novaQuantidade);
                        produtoAlterado.setUnidadeMedida(novaUnidadeMedida);
                        controller.alterarProduto(idAlterar, produtoAlterado);
                    } else {
                        System.out.println("Produto não encontrado!");
                    }
                    break;
                case 4:
                    System.out.print("\nDigite o ID do produto a ser excluído: ");
                    int idExcluir = scanner.nextInt();
                    controller.excluirProduto(idExcluir);
                    break;
                case 5:
                    System.out.print("\nDigite o ID do produto: ");
                    int idDetalhe = scanner.nextInt();
                    Produto produtoDetalhe = controller.buscarProdutoPorId(idDetalhe);
                    if (produtoDetalhe != null) {
                        System.out.println("\nProduto encontrado: " + produtoDetalhe);
                    } else {
                        System.out.println("Produto não encontrado!");
                    }
                    break;
                case 0:
                    System.out.println("\nSaindo...");
                    break;
                default:
                    System.out.println("Opção inválida!");
            }
        } while (opcao != 0);
    }
}