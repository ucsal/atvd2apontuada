package padroesextensao.iterator;

public class Livro {
    private final String titulo;
    private final String autor;
    private final String genero;
    private final int anoPublicacao;

    public Livro(String titulo, String autor, String genero, int anoPublicacao) {
        this.titulo = titulo;
        this.autor = autor;
        this.genero = genero;
        this.anoPublicacao = anoPublicacao;
    }

    public String getTitulo()      { return titulo; }
    public String getAutor()       { return autor; }
    public String getGenero()      { return genero; }
    public int getAnoPublicacao()  { return anoPublicacao; }

    @Override
    public String toString() {
        return String.format("\"%s\" – %s (%d) [%s]", titulo, autor, anoPublicacao, genero);
    }
}