package padroesextensao.iterator;

public interface IteradorLivro {
    boolean hasNext();
    Livro next();
    void reset();
}