package padroesextensao.visitor;

public interface VisitanteImposto {
    double visitar(Alimento alimento);
    double visitar(Eletronico eletronico);
    double visitar(Vestuario vestuario);
    double visitar(Livro livro);
    double visitar(Bebida bebida);
}