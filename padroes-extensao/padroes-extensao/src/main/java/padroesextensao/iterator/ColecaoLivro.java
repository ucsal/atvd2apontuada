package padroesextensao.iterator;

public interface ColecaoLivro {
    IteradorLivro criarIterador();
    void adicionarLivro(Livro livro);
    int totalLivros();
}