package padroesextensao.visitor;

import java.util.List;
import java.util.ArrayList;

public class NotaFiscal {

    private final List<Produto> itens = new ArrayList<>();

    public void adicionarProduto(Produto p) { itens.add(p); }
    public List<Produto> getItens() { return itens; }

    public double calcularImposto(VisitanteImposto v) {
        double total = 0.0;
        for (Produto p : itens) total += p.aceitar(v);
        return total;
    }

    public double calcularTotalProdutos() {
        double total = 0.0;
        for (Produto p : itens) total += p.getPreco();
        return total;
    }

    public void emitirRelatorio() {
        System.out.println("=======================================================");
        System.out.println("           N O T A   F I S C A L");
        System.out.println("=======================================================");
        for (Produto p : itens)
            System.out.printf("  %-30s R$ %8.2f%n", p.getDescricao(), p.getPreco());
        System.out.println("-------------------------------------------------------");
        System.out.printf("  Total dos produtos:            R$ %8.2f%n", calcularTotalProdutos());
        System.out.printf("  ICMS:                           R$ %8.2f%n", calcularImposto(new VisitanteICMS()));
        System.out.printf("  IPI:                            R$ %8.2f%n", calcularImposto(new VisitanteIPI()));
        System.out.printf("  PIS/COFINS:                     R$ %8.2f%n", calcularImposto(new VisitantePIS()));
        System.out.println("=======================================================");
        double impostos = calcularImposto(new VisitanteICMS()) + calcularImposto(new VisitanteIPI()) + calcularImposto(new VisitantePIS());
        System.out.printf("  TOTAL GERAL (produtos + impostos): R$ %8.2f%n", calcularTotalProdutos() + impostos);
        System.out.println("=======================================================");
    }
}