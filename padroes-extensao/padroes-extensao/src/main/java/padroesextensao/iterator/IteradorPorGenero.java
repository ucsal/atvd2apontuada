package padroesextensao.iterator;

import java.util.List;
import java.util.ArrayList;

public class IteradorPorGenero implements IteradorLivro {

    private final List<Livro> livrosFiltrados;
    private int posicao = 0;

    public IteradorPorGenero(List<Livro> todosLivros, String genero) {
        this.livrosFiltrados = new ArrayList<>();
        for (Livro l : todosLivros) {
            if (l.getGenero().equalsIgnoreCase(genero)) {
                livrosFiltrados.add(l);
            }
        }
    }

    @Override
    public boolean hasNext() { return posicao < livrosFiltrados.size(); }

    @Override
    public Livro next() {
        if (!hasNext()) throw new java.util.NoSuchElementException();
        return livrosFiltrados.get(posicao++);
    }

    @Override
    public void reset() { posicao = 0; }
}