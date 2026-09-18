package padroesextensao.visitor;

public class Vestuario implements Produto {
    private final String descricao;
    private final double preco;
    private final boolean importado;

    public Vestuario(String descricao, double preco, boolean importado) {
        this.descricao = descricao;
        this.preco = preco;
        this.importado = importado;
    }

    public boolean isImportado() { return importado; }
    public String getDescricao() { return descricao; }
    public double getPreco() { return preco; }

    @Override
    public double aceitar(VisitanteImposto visitante) { return visitante.visitar(this); }
}