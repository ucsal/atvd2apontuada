package padroesextensao.visitor;

public class Bebida implements Produto {
    private final String descricao;
    private final double preco;
    private final boolean alcoolica;

    public Bebida(String descricao, double preco, boolean alcoolica) {
        this.descricao = descricao;
        this.preco = preco;
        this.alcoolica = alcoolica;
    }

    public boolean isAlcoolica() { return alcoolica; }
    public String getDescricao() { return descricao; }
    public double getPreco() { return preco; }

    @Override
    public double aceitar(VisitanteImposto visitante) { return visitante.visitar(this); }
}