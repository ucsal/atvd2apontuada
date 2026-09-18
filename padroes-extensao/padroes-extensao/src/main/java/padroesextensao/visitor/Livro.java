package padroesextensao.visitor;

public class Livro implements Produto {
    private final String descricao;
    private final double preco;
    private final boolean didatico;

    public Livro(String descricao, double preco, boolean didatico) {
        this.descricao = descricao;
        this.preco = preco;
        this.didatico = didatico;
    }

    public boolean isDidatico() { return didatico; }
    public String getDescricao() { return descricao; }
    public double getPreco() { return preco; }

    @Override
    public double aceitar(VisitanteImposto visitante) { return visitante.visitar(this); }
}