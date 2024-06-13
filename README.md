# Sistema Simples de Gerenciamento de Produtos e Pedidos

Este é um projeto desenvolvido para demonstrar um sistema básico de gerenciamento de produtos e pedidos em Java, implementado como parte de um projeto final para uma disciplina acadêmica.

## Funcionalidades

O sistema oferece as seguintes funcionalidades principais:

### Menu Principal

Um menu inicial que permite ao usuário escolher entre gerenciar produtos, pedidos ou sair do sistema.

### Menu Produtos

Permite realizar operações como inclusão, edição (quantidade e preço), exclusão e listagem de produtos.

### Menu Pedidos

Permite realizar operações como criação de novos pedidos e listagem dos pedidos existentes.

## Estrutura das Classes

### Classe Produto

Representa a estrutura de dados para um produto, contendo atributos como código, nome, preço unitário e quantidade em estoque.

### Classe Pedido

Representa a estrutura de dados para um pedido, contendo atributos como número do pedido, código do produto, preço unitário, e quantidade.

## Arquivos de Texto

Utiliza arquivos de texto (`produtos.txt` e `pedidos.txt`) para armazenar os dados dos produtos e pedidos, respectivamente.

## Funcionalidades Importantes

- Geração automática de códigos para produtos e pedidos.
- Verificação de existência de produtos antes de realizar operações como edição e exclusão.
- Controle de estoque durante a inclusão de novos pedidos.

## Formatos de Dados

O sistema utiliza formatos específicos para armazenar e apresentar os dados:

- **Cadastro de Produtos**: Cada produto é registrado com seu código, nome, preço unitário e quantidade em estoque em um formato específico.
- **Listagem de Produtos**: Apresenta uma tabela com os produtos cadastrados, exibindo código, nome, preço unitário e quantidade em estoque.
- **Cadastro de Pedidos**: Cada pedido é registrado com o número do pedido, código do produto, preço unitário e quantidade em um formato específico.
- **Listagem de Pedidos**: Apresenta uma tabela com os pedidos realizados, mostrando produtos associados a cada pedido, seus detalhes e subtotal.

Para visualizar o código fonte completo e detalhado, consulte o repositório [aqui](https://github.com/BryanWalace/ProjetoFinal-Java).
