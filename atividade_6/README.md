# Projeto de Testes Unitários para Estoque e Produto

Este projeto tem como objetivo a implementação de testes unitários para duas classes pré-definidas: `Estoque` e `Produto`. A atividade foca em validar o comportamento dessas classes usando testes automatizados com o framework JUnit.

## Descrição das Classes

- **Produto**: Representa um produto com nome e preço, permitindo operações de comparação de preços e verificação de igualdade com base no nome.
- **Estoque**: Gerencia um conjunto de produtos, permitindo adicionar, remover, buscar e listar produtos.

## Testes Implementados

Os testes cobrem cenários principais para verificar o correto funcionamento das classes:

### ProdutoTest
1. **testCriacaoProduto**: Verifica se o produto é criado corretamente com os valores de nome e preço.
2. **testFuncaoEqualsProduto**: Verifica a função `equals` para produtos com o mesmo nome e com nomes diferentes.
3. **testFuncaoHashCodeProduto**: Garante que produtos com o mesmo nome gerem o mesmo `hashCode`.
4. **testFuncaoCompareToProduto**: Testa a comparação de preços entre produtos.

### EstoqueTest
1. **testAdicionarProduto**: Testa a adição de produtos ao estoque, incluindo a tentativa de adicionar um produto duplicado.
2. **testRemoverProduto**: Verifica a remoção de produtos existentes e inexistentes do estoque.
3. **testBuscarProduto**: Verifica se é possível buscar um produto pelo nome, retornando `null` se o produto não existir.
4. **testListarProduto**: Garante que a listagem de produtos contenha os produtos adicionados.

## Tecnologias Utilizadas

- **Java**: Linguagem principal do projeto.
- **JUnit 5**: Framework de testes unitários.

## Executando os Testes

Para executar os testes, basta rodar as classes `ProdutoTest` e `EstoqueTest` em sua IDE ou usar o comando Maven/Gradle apropriado, caso esteja usando um desses gerenciadores de dependência.
