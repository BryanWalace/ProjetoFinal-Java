public class Produto {
    private static int proximoCodigoProduto = 1; // Variável estática para gerar o próximo código de produto automaticamente

    private int codigoProduto;
    private String nomeProduto;
    private Double precoUnitario;
    private int quantidadeEstoque;

    /* Construtor da classe produto */
    public Produto(int codigoProduto, String nomeProduto, Double precoUnitario, int quantidadeEstoque) {
        this.codigoProduto = codigoProduto;
        this.nomeProduto = nomeProduto;
        this.precoUnitario = precoUnitario;
        this.quantidadeEstoque = quantidadeEstoque;
    }

    /* Métodos para obter os atributos do produto */
    public int getCodigoProduto() {
        return codigoProduto;
    }

    public String getNomeProduto() {
        return nomeProduto;
    }

    public Double getPrecoUnitario() {
        return precoUnitario;
    }

    public int getQuantidadeEstoque() {
        return quantidadeEstoque;
    }

    /* Métodos para definir os atributos do produto */
    public void setNomeProduto(String nomeProduto) {
        this.nomeProduto = nomeProduto;
    }

    public void setPrecoUnitario(Double precoUnitario) {
        this.precoUnitario = precoUnitario;
    }

    public void setQuantidadeEstoque(int quantidadeEstoque) {
        this.quantidadeEstoque = quantidadeEstoque;
    }

    /* Sobrescreve o método toString para retornar uma representação em string do produto */
    @Override
    public String toString() {
        return "Código: " + codigoProduto + ", Produto: " + nomeProduto + ", Preço unitário: " + precoUnitario + ", Estoque: " + quantidadeEstoque;
    }

    /* Método para obter o próximo código de produto */
    public static int obterProximoCodigoProduto() {
        return proximoCodigoProduto++;
    }

    /* Método para definir o próximo código de produto */
    public static void setProximoCodigoProduto(int proximoCodigo) {
        proximoCodigoProduto = proximoCodigo;
    }
}
