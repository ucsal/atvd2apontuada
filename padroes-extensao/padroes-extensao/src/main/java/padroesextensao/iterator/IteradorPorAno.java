package padroesextensao.iterator;

import java.util.List;
import java.util.ArrayList;
import java.util.Comparator;

public class IteradorPorAno implements IteradorLivro {

    private final List<Livro> livrosOrdenados;
    private int posicao = 0;

    public IteradorPorAno(List<Livro> todosLivros) {
        this.livrosOrdenados = new ArrayList<>(todosLivros);
        this.livrosOrdenados.sort(Comparator.comparingInt(Livro::getAnoPublicacao));
    }

    @Override
    public boolean hasNext() { return posicao < livrosOrdenados.size(); }

    @Override
    public Livro next() {
        if (!hasNext()) throw new java.util.NoSuchElementException();
        return livrosOrdenados.get(posicao++);
    }

    @Override
    public void reset() { posicao = 0; }
}