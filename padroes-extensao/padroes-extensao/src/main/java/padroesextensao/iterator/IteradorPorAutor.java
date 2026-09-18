package padroesextensao.iterator;

import java.util.List;
import java.util.ArrayList;

public class IteradorPorAutor implements IteradorLivro {

    private final List<Livro> livrosFiltrados;
    private int posicao = 0;

    public IteradorPorAutor(List<Livro> todosLivros, String autor) {
        this.livrosFiltrados = new ArrayList<>();
        for (Livro l : todosLivros) {
            if (l.getAutor().equalsIgnoreCase(autor)) {
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