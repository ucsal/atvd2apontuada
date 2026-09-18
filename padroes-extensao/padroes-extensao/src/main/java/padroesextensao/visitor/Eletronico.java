package padroesextensao.visitor;

public class Eletronico implements Produto {
    private final String descricao;
    private final double preco;
    private final String marca;

    public Eletronico(String descricao, double preco, String marca) {
        this.descricao = descricao;
        this.preco = preco;
        this.marca = marca;
    }

    public String getMarca() { return marca; }
    public String getDescricao() { return descricao; }
    public double getPreco() { return preco; }

    @Override
    public double aceitar(VisitanteImposto visitante) { return visitante.visitar(this); }
}