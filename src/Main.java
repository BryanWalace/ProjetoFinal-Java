import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Main {
    private static final String ARQUIVO_PEDIDOS = "pedidos.txt"; /* Nome do arquivo de texto */
    private static ArrayList<Pedido> pedidos = new ArrayList<>(); /* Lista para armazenar os pedidos */

    private static final String ARQUIVO_PRODUTOS = "produtos.txt"; /* Nome do arquivo de texto */
    private static ArrayList<Produto> produtos = new ArrayList<>(); /* Lista para armazenar os produtos */

    public static void main(String[] args) {
        /* Carrega os contatos do arquivo */
        carregarPedidos();
        carregarProdutos();

        Scanner scanner = new Scanner(System.in);
        int opcao;

        /* Loop para exibir o menu e receber opcoes do usuario */
        while (true) {
            System.out.println("# Menu principal #");
            System.out.println("[1] Produtos");
            System.out.println("[2] Pedidos");
            System.out.println("[3] Sair");
            opcao = scanner.nextInt(); /* Le a opcao do usuario */
            scanner.nextLine(); /* Limpa a quebra de linha após receber um número inteiro */
            switch (opcao) {
                case 1:
                    menuProdutos(scanner); /* Chama o método produtos */
                    break;
                case 2:
                    menuPedidos(scanner); /* Chama o método pedido */
                    break;
                case 3:
                    System.out.println("Salvando dados...");
                    salvar(); /* Chama o método para salvar os dados no arquivo */
                    System.exit(0); /* Encerra o programa */
            }
        }
    }

    /* Método para carregar os produtos do arquivo */
    private static void carregarProdutos() {
        try (BufferedReader reader = new BufferedReader(new FileReader(ARQUIVO_PRODUTOS))) {
            String linha;
            while ((linha = reader.readLine()) != null) { /* Le cada linha do arquivo */
                String[] line = linha.split(";"); /* Divide a linha nos atributos do produto */
                int codigoProduto = Integer.parseInt(line[0]);
                String nomeProduto = line[1];
                Double precoUnitario = Double.valueOf(line[2]);
                int quantidadeEstoque = Integer.parseInt(line[3]);
                Produto produto = new Produto(codigoProduto, nomeProduto, precoUnitario, quantidadeEstoque); /* Cria um objeto Produto */
                produtos.add(produto); /* Adiciona o produto à lista */
            }
            /* Atualiza o próximo código de produto baseado nos produtos carregados */
            Produto.setProximoCodigoProduto(produtos.size() + 1);
        } catch (IOException e) { /* Trata erros de leitura do arquivo */
            System.out.println("Erro ao carregar arquivo: " + e.getMessage());
        }
    }

    /* Método para carregar os pedidos do arquivo */
    private static void carregarPedidos() {
        try (BufferedReader reader = new BufferedReader(new FileReader(ARQUIVO_PEDIDOS))) {
            String linha;
            while ((linha = reader.readLine()) != null) { /* Le cada linha do arquivo */
                String[] line = linha.split(";"); /* Divide a linha nos atributos do pedido */
                int numeroPedido = Integer.parseInt(line[0]);
                int codigoProduto = Integer.parseInt(line[1]);
                Double precoUnitario = Double.valueOf(line[2]);
                int quantidade = Integer.parseInt(line[3]);
                Pedido pedido = new Pedido(numeroPedido, codigoProduto, precoUnitario, quantidade); /* Cria um objeto Pedido */
                pedidos.add(pedido); /* Adiciona o pedido à lista */
            }
            /* Atualiza o próximo código de pedido baseado nos pedidos carregados */
            Pedido.setProximoCodigoPedido(pedidos.size() + 1);
        } catch (IOException e) { /* Trata erros de leitura do arquivo */
            System.out.println("Erro ao carregar arquivo: " + e.getMessage());
        }
    }


    public static void menuProdutos(Scanner scanner) {
        int opcao;
        while (true) {
            System.out.println("# Produtos #");
            System.out.println("[1] Incluir produto");
            System.out.println("[2] Editar quantidade estoque produto");
            System.out.println("[3] Editar preco unitario produto");
            System.out.println("[4] Excluir produto");
            System.out.println("[5] Listagem produtos");
            System.out.println("[6] Voltar ao menu principal");
            opcao = scanner.nextInt(); /* Le a opcao do usuario */
            scanner.nextLine(); /* Limpa a quebra de linha após receber um número inteiro */
            switch (opcao) {
                case 1:
                    incluirProduto(scanner);
                    break;
                case 2:
                    alterarQuantidadeEstoque(scanner);
                    break;
                case 3:
                    alterarPrecoUnitario(scanner);
                    break;
                case 4:
                    excluirProduto(scanner);
                    break;
                case 5:
                    listarProdutos();
                    break;
                case 6:
                    return; /* Volta ao menu principal */
            }
        }
    }

    /* Método para incluir produto */
    private static void incluirProduto(Scanner scanner) {
        System.out.print("Digite o nome do produto: ");
        String nomeProduto = scanner.nextLine();
        System.out.print("Informe o preco unitario do produto: ");
        Double precoUnitario = Double.valueOf(scanner.nextLine());
        System.out.print("Digite a quantidade no estoque: ");
        Integer quantidadeEstoque = Integer.valueOf(scanner.nextLine());
        int codigoProduto = Produto.obterProximoCodigoProduto(); // Gera automaticamente o código do produto
        Produto produto = new Produto(codigoProduto, nomeProduto, precoUnitario, quantidadeEstoque); /* Cria um novo objeto Produto */
        produtos.add(produto); /* Adiciona o produto à lista */
        System.out.println("Produto incluido com sucesso!");
        salvar();
    }

    /* Método para alterar a quantidade estoque */
    private static void alterarQuantidadeEstoque(Scanner scanner) {
        System.out.print("Digite o código do produto: ");
        int codigoProduto = Integer.parseInt(scanner.nextLine());
        for (Produto produto : produtos) { /* Para cada produto na lista */
            if (produto.getCodigoProduto() == codigoProduto) { /* Se o código do produto for encontrado */
                System.out.print("Digite a quantidade atualizada: ");
                int novaQuantidadeEstoque = Integer.parseInt(scanner.nextLine());
                produto.setQuantidadeEstoque(novaQuantidadeEstoque);
                System.out.println("Quantidade alterada com sucesso!");
                salvar();
                return;
            }
        }
        System.out.println("Produto nao encontrado!");
    }

    /* Método para alterar o preco unitario produto */
    private static void alterarPrecoUnitario(Scanner scanner) {
        System.out.print("Digite o código do produto: ");
        int codigoProduto = Integer.parseInt(scanner.nextLine());
        for (Produto produto : produtos) { /* Para cada produto na lista */
            if (produto.getCodigoProduto() == codigoProduto) { /* Se o código do produto for encontrado */
                System.out.print("Digite o preco unitario atualizado: ");
                Double novoPrecoUnitario = Double.valueOf(scanner.nextLine());
                produto.setPrecoUnitario(novoPrecoUnitario);
                System.out.println("Preco alterado com sucesso!");
                salvar();
                return;
            }
        }
        System.out.println("Produto nao encontrado!");
    }

    /* Método para excluir um produto */
    private static void excluirProduto(Scanner scanner) {
        System.out.print("Digite o código do produto: ");
        int codigoProduto = Integer.parseInt(scanner.nextLine());
        for (Produto produto : produtos) { /* Para cada produto na lista */
            if (produto.getCodigoProduto() == codigoProduto) { /* Se o código do produto for encontrado */
                produtos.remove(produto); /* Remove o produto da lista */
                System.out.println("Produto excluido com sucesso!");
                salvar();
                return;
            }
        }
        System.out.println("Produto nao encontrado!");
    }

    /* Método para listar todos os produtos */
    public static void listarProdutos() {
        if (produtos.isEmpty()) { /* Se nao houver produtos na lista */
            System.out.println("Nenhum produto cadastrado.");
        } else {
            System.out.printf("%-10s%-50s%-18s%-8s%n", "Código", "Nome", "Preço unitário", "Quantidade estoque");
            System.out.println("------------------------------------------------------------------------------------------------");
            for (Produto produto : produtos) { /* Para cada produto na lista */
                System.out.printf("%-10d%-58s%-18.2f%10d%n", produto.getCodigoProduto(), produto.getNomeProduto(), produto.getPrecoUnitario(), produto.getQuantidadeEstoque());
            }
            System.out.println("------------------------------------------------------------------------------------------------");

            // Cálculo do valor total do estoque
            double valorTotalEstoque = calcularValorTotalEstoque();
            System.out.printf("Valor total estoque: %.2f%n", valorTotalEstoque);
        }
    }

    /* Método para calcular o valor total do estoque */
    private static double calcularValorTotalEstoque() {
        double total = 0.0;
        for (Produto produto : produtos) {
            total += produto.getPrecoUnitario() * produto.getQuantidadeEstoque();
        }
        return total;
    }

    public static void menuPedidos(Scanner scanner) {
        int opcao;
        while (true) {
            System.out.println("# Pedidos #");
            System.out.println("[1] Novo pedido");
            System.out.println("[2] Listagem pedidos");
            System.out.println("[3] Voltar ao Menu Principal");
            opcao = scanner.nextInt(); /* Le a opcao do usuario */
            scanner.nextLine(); /* Limpa a quebra de linha após receber um número inteiro */
            switch (opcao) {
                case 1:
                    incluirPedido(scanner);
                    break;
                case 2:
                    listarPedidos();
                    break;
                case 3:
                    return; /* Volta ao menu principal */
            }
        }
    }

    /* Método para incluir pedido */
    private static void incluirPedido(Scanner scanner) {
        int numeroPedido = Pedido.obterProximoCodigoPedido();
        ArrayList<Pedido> itensPedido = new ArrayList<>();
        double valorTotalPedido = 0.0;
        while (true) {
            System.out.print("Digite o código do produto (ou 0 para encerrar): ");
            int codigoProduto = Integer.parseInt(scanner.nextLine());
            if (codigoProduto == 0) {
                break; // Encerra a inclusão de itens e finaliza o pedido
            }
            Produto produto = null;
            for (Produto p : produtos) {
                if (p.getCodigoProduto() == codigoProduto) {
                    produto = p;
                    break;
                }
            }
            if (produto == null) {
                System.out.println("Produto não encontrado!");
                continue;
            }
            System.out.print("Digite a quantidade do pedido: ");
            int quantidade = Integer.parseInt(scanner.nextLine());
            if (quantidade > produto.getQuantidadeEstoque()) {
                System.out.println("Quantidade solicitada indisponível no estoque!");
                continue;
            }
            Pedido pedido = new Pedido(numeroPedido, codigoProduto, produto.getPrecoUnitario(), quantidade);
            itensPedido.add(pedido);
            valorTotalPedido += produto.getPrecoUnitario() * quantidade;
            produto.setQuantidadeEstoque(produto.getQuantidadeEstoque() - quantidade); // Atualiza a quantidade em estoque
        }
        if (!itensPedido.isEmpty()) {
            pedidos.addAll(itensPedido); // Adiciona todos os itens do pedido à lista de pedidos
            System.out.println("Pedido incluído com sucesso!");
            System.out.printf("Valor total do pedido: %.2f%n", valorTotalPedido);
            salvar();
        } else {
            System.out.println("Nenhum item foi adicionado ao pedido.");
        }
    }

    /* Método para listar todos os pedidos */
    private static void listarPedidos() {
        if (pedidos.isEmpty()) {
            System.out.println("Nenhum pedido cadastrado.");
        } else {
            // Agrupa os pedidos por número de pedido
            pedidos.stream()
                    .collect(Collectors.groupingBy(Pedido::getNumeroPedido))
                    .forEach((numeroPedido, listaPedidos) -> {
                        System.out.printf("Pedido número: %d%n", numeroPedido);
                        System.out.printf("%-40s%-19s%-13s%-8s%n", "Produto", "Preço unitário", "Quantidade", "Subtotal");
                        System.out.println("--------------------------------------------------------------------------------");

                        double valorTotalPedido = 0.0;
                        for (Pedido pedido : listaPedidos) {
                            Produto produto = encontrarProdutoPorCodigo(pedido.getCodigoProduto());
                            if (produto != null) {
                                double subtotal = pedido.getPrecoUnitario() * pedido.getQuantidade();
                                System.out.printf("%-50s%-18.2f%-7d%-8.2f%n", produto.getNomeProduto(), pedido.getPrecoUnitario(), pedido.getQuantidade(), subtotal);
                                valorTotalPedido += subtotal;
                            }
                        }

                        System.out.println("--------------------------------------------------------------------------------");
                        System.out.printf("Valor total pedido: %.2f%n", valorTotalPedido);
                        System.out.println();
                    });
        }
    }

    /* Método para encontrar um produto pelo código */
    private static Produto encontrarProdutoPorCodigo(int codigoProduto) {
        for (Produto produto : produtos) {
            if (produto.getCodigoProduto() == codigoProduto) {
                return produto;
            }
        }
        return null;
    }

    /* Método para salvar os dados (stub, implementar conforme necessário) */
    private static void salvar() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(ARQUIVO_PRODUTOS))) {
            for (Produto produto : produtos) { /* Para cada produto na lista */
                writer.write(produto.getCodigoProduto() + ";" + produto.getNomeProduto() + ";" + produto.getPrecoUnitario() + ";" + produto.getQuantidadeEstoque()); /* Escreve no arquivo */
                writer.newLine(); /* Adiciona uma quebra de linha */
            }
        } catch (IOException e) { /* Trata erros de escrita no arquivo */
            System.out.println("Erro ao salvar arquivo: " + e.getMessage());
        }

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(ARQUIVO_PEDIDOS))) {
            for (Pedido pedido : pedidos) { /* Para cada pedido na lista */
                writer.write(pedido.getNumeroPedido() + ";" + pedido.getCodigoProduto() + ";" + pedido.getPrecoUnitario() + ";" + pedido.getQuantidade()); /* Escreve no arquivo */
                writer.newLine(); /* Adiciona uma quebra de linha */
            }
        } catch (IOException e) { /* Trata erros de escrita no arquivo */
            System.out.println("Erro ao salvar arquivo: " + e.getMessage());
        }
    }
}
