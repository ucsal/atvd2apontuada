package padroesextensao.visitor;

public class VisitantePIS implements VisitanteImposto {

    private static final double TAXA_PIS = 0.0165;

    @Override
    public double visitar(Alimento a) { return a.getPreco() * 0.0082; }

    @Override
    public double visitar(Eletronico e) { return e.getPreco() * TAXA_PIS; }

    @Override
    public double visitar(Vestuario v) { return v.getPreco() * TAXA_PIS; }

    @Override
    public double visitar(Livro l) { return 0.0; }

    @Override
    public double visitar(Bebida b) { return b.getPreco() * TAXA_PIS; }
}