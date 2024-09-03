# Bomba de Combustível - Java

Este repositório contém a implementação de um programa em Java que simula o funcionamento de uma bomba de combustível. O sistema permite ao usuário realizar diversas operações como abastecimento, alteração de valores e tipo de combustível, além de fornecer informações detalhadas sobre o estado da bomba.

## Funcionalidades

O programa possui as seguintes funcionalidades:

1. **Abastecer por Valor**: Permite ao usuário abastecer o veículo com um valor específico de combustível.
2. **Abastecer por Litro**: Permite ao usuário abastecer o veículo com uma quantidade específica de litros de combustível.
3. **Alterar Valor do Litro**: Permite alterar o valor do litro de combustível na bomba.
4. **Alterar Tipo de Combustível**: Permite alterar o tipo de combustível disponível na bomba.
5. **Alterar Quantidade de Combustível**: Permite alterar a quantidade de combustível disponível na bomba.
6. **Informações da Bomba**: Exibe informações sobre o tipo de combustível, valor por litro e a quantidade de combustível disponível.
7. **Sair do Sistema**: Encerra o programa.

## Estrutura do Código

O código está dividido em duas classes principais:

### 1. `BombaCombustivel`

Esta classe representa a bomba de combustível e contém os seguintes atributos e métodos:

- **Atributos**:
  - `tipoCombustivel`: Tipo de combustível disponível na bomba (String).
  - `valorLitro`: Valor do litro de combustível (double).
  - `quantidadeCombustivel`: Quantidade de combustível disponível na bomba (double).

- **Métodos**:
  - `abastecerPorValor(double valor)`: Abastece o veículo com uma quantidade de combustível correspondente ao valor especificado.
  - `abastecerPorLitro(double litro)`: Abastece o veículo com a quantidade de litros especificada.
  - `alterarValor(double novoValor)`: Altera o valor do litro de combustível na bomba.
  - `alterarCombustivel(String novoTipo)`: Altera o tipo de combustível disponível na bomba.
  - `alterarQuantidadeCombustivel(double novaQuantidade)`: Altera a quantidade de combustível disponível na bomba.
  - `infoBomba()`: Exibe informações sobre a bomba de combustível.

### 2. `Main`

Esta classe contém o método `main` e é responsável por interagir com o usuário, fornecendo um menu de opções para realizar as operações mencionadas. O menu é implementado usando um loop `do-while`, permitindo ao usuário escolher as operações até que opte por sair do sistema.

## Exemplo de Uso

Ao iniciar o programa, o usuário verá um menu com as opções disponíveis. A partir daí, ele pode escolher uma opção, inserir os valores necessários, e o programa realizará a operação correspondente, exibindo o resultado na tela.
