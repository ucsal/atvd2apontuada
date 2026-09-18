package padroesextensao.iterator;

public class ExemploIterator {

    public static void main(String[] args) {
        BibliotecaDigital biblioteca = new BibliotecaDigital();

        biblioteca.adicionarLivro(new Livro("O Senhor dos Anéis", "J.R.R. Tolkien", "Fantasia", 1954));
        biblioteca.adicionarLivro(new Livro("Duna", "Frank Herbert", "Ficção Científica", 1965));
        biblioteca.adicionarLivro(new Livro("O Hobbit", "J.R.R. Tolkien", "Fantasia", 1937));
        biblioteca.adicionarLivro(new Livro("Neuromancer", "William Gibson", "Ficção Científica", 1984));
        biblioteca.adicionarLivro(new Livro("Fundação", "Isaac Asimov", "Ficção Científica", 1951));
        biblioteca.adicionarLivro(new Livro("O Silmarillion", "J.R.R. Tolkien", "Fantasia", 1977));
        biblioteca.adicionarLivro(new Livro("Crime e Castigo", "Fiódor Dostoiévski", "Drama", 1866));
        biblioteca.adicionarLivro(new Livro("O Homem Duplo", "Fiódor Dostoiévski", "Drama", 1864));

        System.out.println("=== Biblioteca Digital – Padrão Iterator ===");
        System.out.println("Total de livros: " + biblioteca.totalLivros() + "\n");

        System.out.println("--- Livros ordenados por ano ---");
        IteradorLivro itAno = biblioteca.criarIterador();
        while (itAno.hasNext()) System.out.println("  " + itAno.next());

        System.out.println("\n--- Livros de Ficção Científica ---");
        IteradorLivro itGenero = biblioteca.criarIteradorPorGenero("Ficção Científica");
        while (itGenero.hasNext()) System.out.println("  " + itGenero.next());

        System.out.println("\n--- Livros de J.R.R. Tolkien ---");
        IteradorLivro itAutor = biblioteca.criarIteradorPorAutor("J.R.R. Tolkien");
        while (itAutor.hasNext()) System.out.println("  " + itAutor.next());

        System.out.println("\n--- Demo: reset do iterador ---");
        IteradorLivro itFantasia = biblioteca.criarIteradorPorGenero("Fantasia");
        System.out.println("  Primeiro: " + itFantasia.next());
        itFantasia.reset();
        System.out.println("  Após reset: " + itFantasia.next());
    }
}