package padroesextensao.visitor;

public class VisitanteIPI implements VisitanteImposto {

    @Override
    public double visitar(Alimento a) { return 0.0; }

    @Override
    public double visitar(Eletronico e) { return e.getPreco() * 0.10; }

    @Override
    public double visitar(Vestuario v) { return v.getPreco() * 0.05; }

    @Override
    public double visitar(Livro l) { return 0.0; }

    @Override
    public double visitar(Bebida b) { return b.getPreco() * (b.isAlcoolica() ? 0.15 : 0.05); }
}