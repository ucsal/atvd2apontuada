package padroesextensao.visitor;

public interface Produto {
    double aceitar(VisitanteImposto visitante);
    String getDescricao();
    double getPreco();
}