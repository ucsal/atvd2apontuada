package padroesextensao.visitor;

public class VisitanteICMS implements VisitanteImposto {

    @Override
    public double visitar(Alimento a) { return a.getPreco() * (a.isOrganico() ? 0.04 : 0.07); }

    @Override
    public double visitar(Eletronico e) { return e.getPreco() * 0.18; }

    @Override
    public double visitar(Vestuario v) { return v.getPreco() * (v.isImportado() ? 0.25 : 0.12); }

    @Override
    public double visitar(Livro l) { return l.isDidatico() ? 0.0 : l.getPreco() * 0.03; }

    @Override
    public double visitar(Bebida b) { return b.getPreco() * (b.isAlcoolica() ? 0.20 : 0.12); }
}