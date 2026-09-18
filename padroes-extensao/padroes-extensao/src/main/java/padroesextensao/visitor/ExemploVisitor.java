package padroesextensao.visitor;

public class ExemploVisitor {

    public static void main(String[] args) {
        NotaFiscal nota = new NotaFiscal();
        nota.adicionarProduto(new Alimento("Cesta de Frutas Orgânicas", 45.00, true));
        nota.adicionarProduto(new Alimento("Pacote de Arroz", 22.50, false));
        nota.adicionarProduto(new Eletronico("Smartphone Samsung", 1899.00, "Samsung"));
        nota.adicionarProduto(new Eletronico("Notebook Lenovo", 3499.00, "Lenovo"));
        nota.adicionarProduto(new Vestuario("Camiseta Básica", 59.90, false));
        nota.adicionarProduto(new Vestuario("Jaqueta de Couro Importada", 450.00, true));
        nota.adicionarProduto(new Livro("Java – Guia do Programador", 120.00, true));
        nota.adicionarProduto(new Livro("Senhor dos Anéis – Edição Especial", 85.00, false));
        nota.adicionarProduto(new Bebida("Vinho Tinto Chileno", 65.00, true));
        nota.adicionarProduto(new Bebida("Suco de Laranja Natural", 8.50, false));

        System.out.println("=== Sistema de Impostos Fiscais – Padrão Visitor ===\n");
        nota.emitirRelatorio();

        System.out.println("\n=== Detalhamento por produto ===");
        VisitanteImposto icms = new VisitanteICMS();
        VisitanteImposto ipi  = new VisitanteIPI();
        VisitanteImposto pis  = new VisitantePIS();
        for (Produto p : nota.getItens()) {
            System.out.printf("  %-32s → ICMS: R$%6.2f | IPI: R$%5.2f | PIS: R$%4.2f%n",
                    p.getDescricao(), p.aceitar(icms), p.aceitar(ipi), p.aceitar(pis));
        }
    }
}