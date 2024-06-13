public class Pedido {
    private static int proximoCodigoPedido = 1; // Variável estática para gerar o próximo código de pedido automaticamente

    private int numeroPedido;
    private int codigoProduto;
    private Double precoUnitario;
    private int quantidade;

    /* Construtor da classe Pedido */
    public Pedido(int numeroPedido, int codigoProduto, Double precoUnitario, int quantidade) {
        this.numeroPedido = numeroPedido;
        this.codigoProduto = codigoProduto;
        this.precoUnitario = precoUnitario;
        this.quantidade = quantidade;
    }

    /* Métodos para obter os atributos do pedido */
    public int getNumeroPedido() {
        return numeroPedido;
    }

    public int getCodigoProduto() {
        return codigoProduto;
    }

    public Double getPrecoUnitario() {
        return precoUnitario;
    }

    public int getQuantidade() {
        return quantidade;
    }

    /* Métodos para definir os atributos do pedido */
    public void setCodigoProduto(int codigoProduto) {
        this.codigoProduto = codigoProduto;
    }

    public void setPrecoUnitario(Double precoUnitario) {
        this.precoUnitario = precoUnitario;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    /* Sobrescreve o método toString para retornar uma representação em string do pedido */
    @Override
    public String toString() {
        return "Pedido: " + numeroPedido + ", Código do Produto: " + codigoProduto + ", Preço Unitário: " + precoUnitario + ", Quantidade: " + quantidade;
    }

    /* Método para obter o próximo código de pedido */
    public static int obterProximoCodigoPedido() {
        return proximoCodigoPedido++;
    }

    /* Método para definir o próximo código de pedido */
    public static void setProximoCodigoPedido(int proximoCodigo) {
        proximoCodigoPedido = proximoCodigo;
    }
}
