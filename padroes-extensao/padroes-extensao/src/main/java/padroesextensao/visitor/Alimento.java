package padroesextensao.visitor;

public class Alimento implements Produto {
    private final String descricao;
    private final double preco;
    private final boolean organico;

    public Alimento(String descricao, double preco, boolean organico) {
        this.descricao = descricao;
        this.preco = preco;
        this.organico = organico;
    }

    public boolean isOrganico() { return organico; }
    public String getDescricao() { return descricao; }
    public double getPreco() { return preco; }

    @Override
    public double aceitar(VisitanteImposto visitante) { return visitante.visitar(this); }
}