package padroesextensao.iterator;

import java.util.List;
import java.util.ArrayList;

public class BibliotecaDigital implements ColecaoLivro {

    private final List<Livro> acervo = new ArrayList<>();

    @Override
    public void adicionarLivro(Livro livro) { acervo.add(livro); }

    @Override
    public IteradorLivro criarIterador() { return new IteradorPorAno(acervo); }

    public IteradorLivro criarIteradorPorGenero(String genero) { return new IteradorPorGenero(acervo, genero); }

    public IteradorLivro criarIteradorPorAutor(String autor) { return new IteradorPorAutor(acervo, autor); }

    @Override
    public int totalLivros() { return acervo.size(); }
}