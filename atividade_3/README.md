# Sistema de Controle de Estacionamento

Este projeto implementa um sistema de controle e bilhetagem de um edifício estacionamento utilizando orientação a objetos em Java. O sistema permite a entrada de diversos tipos de veículos, como motocicletas, carros de passeio, caminhonetes e furgões, e calcula o valor a ser cobrado por hora estacionada com base nas características do veículo, como peso e volume.

## Funcionalidades

- Cadastro de veículos para estacionamento.
- Classificação de veículos com base no tipo e peso.
- Cálculo do valor por hora estacionada.
- Verificação de elegibilidade dos veículos para estacionar.

## Regras de Classificação de Veículos

Os veículos são classificados com base no tipo e no peso, conforme a tabela abaixo:

| Tipo                      | Peso                      | Preço por Hora |
|---------------------------|---------------------------|----------------|
| Motocicleta leve           | até 100 kg                | R$ 2,00        |
| Motocicleta padrão         | entre 100,1 e 299,9 kg    | R$ 4,00        |
| Motocicleta pesada         | a partir de 300 kg        | R$ 10,00       |
| Carro de passeio hatchback | até 2 ton                 | R$ 13,00       |
| Carro de passeio sedan     | até 2 ton                 | R$ 15,00       |
| SUV (Carro de passeio maior) | até 2 ton              | R$ 20,00       |
| Caminhonete não carregada  | até 3 ton                 | R$ 25,00       |
| Caminhonete carregada      | entre 3.1 e 6 ton         | R$ 50,00       |
| Furgão não carregado       | até 3 ton                 | R$ 25,00       |
| Furgão carregado           | entre 3.1 e 6 ton         | R$ 50,00       |


### Observações

- Carros de passeio com mais de 2 toneladas serão classificados como furgões.
- Caminhonetes e furgões não podem exceder 6 toneladas de PBT.
- Motocicletas com mais de 400 kg não podem estacionar no edifício.

## Estrutura de Classes

### `Main`
- **Descrição**: Classe principal que contém o método `main` e gerencia a interação com o usuário. Permite a seleção do tipo de veículo, coleta informações sobre o peso do veículo e fornece opções para mostrar informações, calcular o preço por hora e o valor total a pagar.
- **Métodos Principais**:
  - `main(String[] args)`: Gerencia o fluxo principal do programa, incluindo a entrada de dados do usuário e a execução das opções disponíveis.

### `Estacionamento`
- **Descrição**: Classe base para todos os tipos de veículos. Contém atributos comuns e métodos para calcular o tipo de veículo e o preço por hora. Define a estrutura geral e operações comuns para os veículos.
- **Atributos**:
  - `peso`: Peso do veículo.
  - `veiculo`: Nome do veículo.
  - `tipo`: Tipo do veículo.
- **Métodos**:
  - `CalcularTipo(double peso)`: Retorna o tipo do veículo baseado no peso.
  - `VerificarPeso(double peso, double peso_max)`: Verifica se o peso do veículo está dentro do limite permitido.
  - `InformarDados()`: Exibe as informações do veículo.
  - `CalcularPrecoHora()`: Calcula o preço por hora do estacionamento.
  - `ImprimirPrecoHora()`: Exibe o preço por hora.
  - `SimularPagamento()`: Calcula o valor total a pagar com base no tempo de estacionamento.

### `Motocicleta`
- **Descrição**: Classe que representa uma motocicleta. Estende `Estacionamento` e define o tipo e o preço por hora específicos para motocicletas.
- **Métodos**:
  - `CalcularTipo(double peso)`: Determina o tipo de motocicleta (Leve, Padrão ou Pesada) com base no peso.
  - `CalcularPrecoHora()`: Calcula o preço por hora baseado no tipo da motocicleta.

### `Carro`
- **Descrição**: Classe que representa um carro. Estende `Estacionamento` e permite a seleção do tipo específico de carro (Hatchback, Sedan ou SUV). Se o peso exceder o limite para carros, o veículo é registrado como um furgão.
- **Métodos**:
  - `CalcularTipo(double peso)`: Permite ao usuário selecionar o tipo de carro e retorna o tipo selecionado.
  - `CalcularPrecoHora()`: Calcula o preço por hora baseado no tipo do carro.

### `Caminhonete`
- **Descrição**: Classe que representa uma caminhonete. Estende `Estacionamento` e define o tipo e o preço por hora específicos para caminhonetes.
- **Métodos**:
  - `CalcularTipo(double peso)`: Determina o tipo de caminhonete (Não Carregado ou Carregado) com base no peso.
  - `CalcularPrecoHora()`: Calcula o preço por hora baseado no tipo da caminhonete.

### `Furgao`
- **Descrição**: Classe que representa um furgão. Estende `Estacionamento` e define o tipo e o preço por hora específicos para furgões.
- **Métodos**:
  - `CalcularTipo(double peso)`: Determina o tipo de furgão (Não Carregado ou Carregado) com base no peso.
  - `CalcularPrecoHora()`: Calcula o preço por hora baseado no tipo do furgão.

## Funcionamento

1. **Entrada do Usuário**: O usuário seleciona o tipo de veículo e fornece o peso. O sistema verifica se o peso está dentro dos limites permitidos e registra o veículo.
2. **Opções**: O usuário pode visualizar informações do veículo, o preço por hora, calcular o valor total a pagar, ou inserir um novo veículo.
3. **Cálculo do Preço**: O preço é calculado com base no tipo do veículo e no tempo de estacionamento.
