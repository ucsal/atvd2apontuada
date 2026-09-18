
## 1. Decorator

### Conceito

O padrão **Decorator** permite adicionar responsabilidades a um objeto **dinamicamente**, sem alterar a classe original. Funciona como uma camada que envolve o objeto, estendendo seu comportamento. Cada decorador implementa a mesma interface do componente base e delega a chamada ao objeto envolvido, acrescentando sua própria lógica.

**Quando usar:**
- Quando você precisa adicionar funcionalidades a objetos de forma dinâmica e transparente.
- Quando a herança seria inviável (explosão de subclasses para cada combinação).
- Quando você deseja compor comportamentos em vez de herdar.

**Vantagens:**
- Adiciona funcionalidades sem criar subclasses infinitas.
- Permite combinar comportamentos livremente.
- Segue o princípio da responsabilidade única.

**Desvantagens:**
- Pode gerar muitos objetos pequenos.
- Pode dificultar a depuração (muitas camadas).


### Mini-projeto: Sistema de Notificações

**Problema real:** Um sistema precisa enviar notificações por diferentes canais (e-mail, SMS, Push, WhatsApp). O canal base é sempre o e-mail, mas o sistema deve permitir combinar canais livremente — sem criar uma subclasse para cada combinação.

**Solução com Decorator:** Cada canal é um decorador que envolve a notificação anterior, acrescentando seu envio e custo.

### Código-fonte

#### Notificacao.java

```java
package padroesextensao.decorator;

public interface Notificacao {
    String enviar(String destinatario);
    double custo();
}
```

#### NotificacaoSimples.java

```java
package padroesextensao.decorator;

public class NotificacaoSimples implements Notificacao {

    @Override
    public String enviar(String destinatario) {
        return "📧 E-mail enviado para " + destinatario;
    }

    @Override
    public double custo() {
        return 0.0;
    }
}
```

#### NotificacaoDecorator.java

```java
package padroesextensao.decorator;

public abstract class NotificacaoDecorator implements Notificacao {

    protected Notificacao notificacaoEnvolvida;

    public NotificacaoDecorator(Notificacao notificacao) {
        this.notificacaoEnvolvida = notificacao;
    }

    @Override
    public String enviar(String destinatario) {
        return notificacaoEnvolvida.enviar(destinatario);
    }

    @Override
    public double custo() {
        return notificacaoEnvolvida.custo();
    }
}
```

#### DecoradorSMS.java

```java
package padroesextensao.decorator;

public class DecoradorSMS extends NotificacaoDecorator {

    private static final double CUSTO_SMS = 0.05;

    public DecoradorSMS(Notificacao notificacao) { super(notificacao); }

    @Override
    public String enviar(String destinatario) {
        return super.enviar(destinatario) + "\n📱 SMS enviado para " + destinatario;
    }

    @Override
    public double custo() { return super.custo() + CUSTO_SMS; }
}
```

#### DecoradorPush.java

```java
package padroesextensao.decorator;

public class DecoradorPush extends NotificacaoDecorator {

    private static final double CUSTO_PUSH = 0.02;

    public DecoradorPush(Notificacao notificacao) { super(notificacao); }

    @Override
    public String enviar(String destinatario) {
        return super.enviar(destinatario) + "\n🔔 Push enviado para " + destinatario;
    }

    @Override
    public double custo() { return super.custo() + CUSTO_PUSH; }
}
```

#### DecoradorWhatsApp.java

```java
package padroesextensao.decorator;

public class DecoradorWhatsApp extends NotificacaoDecorator {

    private static final double CUSTO_WHATSAPP = 0.03;

    public DecoradorWhatsApp(Notificacao notificacao) { super(notificacao); }

    @Override
    public String enviar(String destinatario) {
        return super.enviar(destinatario) + "\n💬 WhatsApp enviado para " + destinatario;
    }

    @Override
    public double custo() { return super.custo() + CUSTO_WHATSAPP; }
}
```

#### DecoradorLog.java

```java
package padroesextensao.decorator;

public class DecoradorLog extends NotificacaoDecorator {

    public DecoradorLog(Notificacao notificacao) { super(notificacao); }

    @Override
    public String enviar(String destinatario) {
        String resultado = super.enviar(destinatario);
        System.out.println("[LOG] Notificação registrada para " + destinatario
                         + " às " + java.time.LocalDateTime.now());
        return resultado + "\n📝 Log registrado";
    }

    @Override
    public double custo() { return super.custo(); }
}
```

#### ExemploDecorator.java

```java
package padroesextensao.decorator;

public class ExemploDecorator {

    public static void main(String[] args) {
        System.out.println("=== Sistema de Notificações – Padrão Decorator ===\n");

        Notificacao simples = new NotificacaoSimples();
        System.out.println("--- Notificação Simples (E-mail) ---");
        System.out.println(simples.enviar("joao@email.com"));
        System.out.println("Custo: R$ " + String.format("%.2f", simples.custo()) + "\n");

        Notificacao emailSms = new DecoradorSMS(simples);
        System.out.println("--- E-mail + SMS ---");
        System.out.println(emailSms.enviar("maria@email.com"));
        System.out.println("Custo: R$ " + String.format("%.2f", emailSms.custo()) + "\n");

        Notificacao emailSmsPush = new DecoradorPush(emailSms);
        System.out.println("--- E-mail + SMS + Push ---");
        System.out.println(emailSmsPush.enviar("carlos@email.com"));
        System.out.println("Custo: R$ " + String.format("%.2f", emailSmsPush.custo()) + "\n");

        Notificacao completa = new DecoradorWhatsApp(emailSmsPush);
        System.out.println("--- E-mail + SMS + Push + WhatsApp ---");
        System.out.println(completa.enviar("ana@email.com"));
        System.out.println("Custo: R$ " + String.format("%.2f", completa.custo()) + "\n");

        Notificacao emailComLog = new DecoradorLog(simples);
        System.out.println("--- E-mail + Log ---");
        System.out.println(emailComLog.enviar("pedro@email.com"));
        System.out.println("Custo: R$ " + String.format("%.2f", emailComLog.custo()) + "\n");

        Notificacao comboTotal = new DecoradorLog(new DecoradorWhatsApp(
            new DecoradorPush(new DecoradorSMS(new NotificacaoSimples()))));
        System.out.println("--- Combo: E-mail + SMS + Push + WhatsApp + Log ---");
        System.out.println(comboTotal.enviar("lucas@email.com"));
        System.out.println("Custo: R$ " + String.format("%.2f", comboTotal.custo()) + "\n");
    }
}
```

### Saída esperada

```
=== Sistema de Notificações – Padrão Decorator ===

--- Notificação Simples (E-mail) ---
📧 E-mail enviado para joao@email.com
Custo: R$ 0,00

--- E-mail + SMS ---
📧 E-mail enviado para maria@email.com
📱 SMS enviado para maria@email.com
Custo: R$ 0,05

--- E-mail + SMS + Push ---
📧 E-mail enviado para carlos@email.com
📱 SMS enviado para carlos@email.com
🔔 Push enviado para carlos@email.com
Custo: R$ 0,07

--- E-mail + SMS + Push + WhatsApp ---
📧 E-mail enviado para ana@email.com
📱 SMS enviado para ana@email.com
🔔 Push enviado para ana@email.com
💬 WhatsApp enviado para ana@email.com
Custo: R$ 0,10

[LOG] Notificação registrada para pedro@email.com às 2026-09-18T...
--- E-mail + Log ---
📧 E-mail enviado para pedro@email.com
📝 Log registrado
Custo: R$ 0,00

[LOG] Notificação registrada para lucas@email.com às 2026-09-18T...
--- Combo: E-mail + SMS + Push + WhatsApp + Log ---
📧 E-mail enviado para lucas@email.com
📱 SMS enviado para lucas@email.com
🔔 Push enviado para lucas@email.com
💬 WhatsApp enviado para lucas@email.com
📝 Log registrado
Custo: R$ 0,10
```

### Explicação da aplicação do padrão

1. **Componente base (`Notificacao`):** Interface comum que define `enviar()` e `custo()`.
2. **Componente concreto (`NotificacaoSimples`):** Implementação base que envia e-mail.
3. **Decorador base (`NotificacaoDecorator`):** Classe abstrata que implementa `Notificacao`, mantém referência ao objeto envolvido e repassa chamadas.
4. **Decoradores concretos (`DecoradorSMS`, `DecoradorPush`, `DecoradorWhatsApp`, `DecoradorLog`):** Cada um adiciona um canal ou funcionalidade ao comportamento do objeto envolvido.
5. **Composição:** O cliente monta a cadeia de decoradores conforme necessidade. Por exemplo, `new DecoradorLog(new DecoradorWhatsApp(new DecoradorPush(new DecoradorSMS(new NotificacaoSimples()))))` combina 5 comportamentos em tempo de execução, sem subclasses para cada combinação.

---

## 2. Iterator

### Conceito

O padrão **Iterator** fornece uma maneira de acessar sequencialmente os elementos de uma coleção **sem expor sua representação interna**. Separa a lógica de travessia da estrutura de dados, permitindo criar diferentes formas de percorrer a mesma coleção.

**Quando usar:**
- Quando você precisa percorrer uma coleção sem expor sua estrutura interna.
- Quando precisa de múltiplas formas de travessia sobre a mesma coleção.
- Quando quer fornecer uma interface uniforme para percorrer diferentes estruturas de dados.

**Vantagens:**
- Separa a lógica de iteração da coleção.
- Permite criar múltiplos iteradores simultâneos.
- Facilita trocar a estrutura interna sem afetar o cliente.

**Desvantagens:**
- Pode ser excessivo para coleções simples.
- O iterador não garante que a coleção não seja modificada durante a iteração.


### Mini-projeto: Biblioteca Digital

**Problema real:** Uma biblioteca digital precisa oferecer diferentes formas de navegação no acervo: por ordem cronológica, por gênero e por autor, sem expor a lista interna de livros.

**Solução com Iterator:** A biblioteca atua como *Aggregate* e fornece métodos-fábrica para criar iteradores especializados. Cada iterador encapsula sua lógica de filtragem/ordenação.

### Código-fonte

#### Livro.java

```java
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
```

#### IteradorLivro.java

```java
package padroesextensao.iterator;

public interface IteradorLivro {
    boolean hasNext();
    Livro next();
    void reset();
}
```

#### ColecaoLivro.java

```java
package padroesextensao.iterator;

public interface ColecaoLivro {
    IteradorLivro criarIterador();
    void adicionarLivro(Livro livro);
    int totalLivros();
}
```

#### IteradorPorAno.java

```java
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
```

#### IteradorPorGenero.java

```java
package padroesextensao.iterator;

import java.util.List;
import java.util.ArrayList;

public class IteradorPorGenero implements IteradorLivro {

    private final List<Livro> livrosFiltrados;
    private int posicao = 0;

    public IteradorPorGenero(List<Livro> todosLivros, String genero) {
        this.livrosFiltrados = new ArrayList<>();
        for (Livro l : todosLivros) {
            if (l.getGenero().equalsIgnoreCase(genero))
                livrosFiltrados.add(l);
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
```

#### IteradorPorAutor.java

```java
package padroesextensao.iterator;

import java.util.List;
import java.util.ArrayList;

public class IteradorPorAutor implements IteradorLivro {

    private final List<Livro> livrosFiltrados;
    private int posicao = 0;

    public IteradorPorAutor(List<Livro> todosLivros, String autor) {
        this.livrosFiltrados = new ArrayList<>();
        for (Livro l : todosLivros) {
            if (l.getAutor().equalsIgnoreCase(autor))
                livrosFiltrados.add(l);
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
```

#### BibliotecaDigital.java

```java
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
```

#### ExemploIterator.java

```java
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
```

### Saída esperada

```
=== Biblioteca Digital – Padrão Iterator ===
Total de livros: 8

--- Livros ordenados por ano ---
  "O Homem Duplo" – Fiódor Dostoiévski (1864) [Drama]
  "Crime e Castigo" – Fiódor Dostoiévski (1866) [Drama]
  "O Hobbit" – J.R.R. Tolkien (1937) [Fantasia]
  "Fundação" – Isaac Asimov (1951) [Ficção Científica]
  "O Senhor dos Anéis" – J.R.R. Tolkien (1954) [Fantasia]
  "Duna" – Frank Herbert (1965) [Ficção Científica]
  "O Silmarillion" – J.R.R. Tolkien (1977) [Fantasia]
  "Neuromancer" – William Gibson (1984) [Ficção Científica]

--- Livros de Ficção Científica ---
  "Duna" – Frank Herbert (1965) [Ficção Científica]
  "Neuromancer" – William Gibson (1984) [Ficção Científica]
  "Fundação" – Isaac Asimov (1951) [Ficção Científica]

--- Livros de J.R.R. Tolkien ---
  "O Senhor dos Anéis" – J.R.R. Tolkien (1954) [Fantasia]
  "O Hobbit" – J.R.R. Tolkien (1937) [Fantasia]
  "O Silmarillion" – J.R.R. Tolkien (1977) [Fantasia]

--- Demo: reset do iterador ---
  Primeiro: "O Senhor dos Anéis" – J.R.R. Tolkien (1954) [Fantasia]
  Após reset: "O Senhor dos Anéis" – J.R.R. Tolkien (1954) [Fantasia]
```

### Explicação da aplicação do padrão

1. **Interface Iterator (`IteradorLivro`):** Define `hasNext()`, `next()` e `reset()`.
2. **Interface Aggregate (`ColecaoLivro`):** Define como uma coleção cria iteradores.
3. **Iteradores concretos:** Cada um encapsula uma estratégia diferente — por ano (ordem cronológica), por gênero (filtro) e por autor (filtro).
4. **Coleção concreta (`BibliotecaDigital`):** Mantém o acervo e fornece métodos-fábrica para criar iteradores. O cliente nunca acessa a lista diretamente.
5. **Independência:** O cliente usa apenas `IteradorLivro`, sem saber como os livros estão armazenados internamente.

---

## 3. Visitor

### Conceito

O padrão **Visitor** permite adicionar operações a objetos de diferentes tipos **sem modificar suas classes**. Funciona separando o algoritmo da estrutura de dados: cada tipo de elemento possui um método `aceitar()` que chama o método correspondente no visitante, realizando o **double dispatch** (duplo despacho).

**Quando usar:**
- Quando você precisa executar operações variadas sobre objetos de tipos diferentes sem alterar suas classes.
- Quando as operações mudam com frequência, mas a estrutura dos objetos é estável.
- Quando você quer agrupar operações relacionadas em uma única classe.

**Vantagens:**
- Adiciona operações sem modificar as classes dos elementos.
- Agrupa lógica relacionada em um visitante.
- Facilita criar novas operações — basta criar um novo visitante.

**Desvantagens:**
- Dificulta adicionar novos tipos de elementos (exige mudança em todos os visitantes).
- O double dispatch pode parecer contraintuitivo.


### Mini-projeto: Sistema de Impostos Fiscais

**Problema real:** Um sistema de notas fiscais precisa calcular diferentes impostos (ICMS, IPI, PIS/COFINS) sobre produtos variados (alimentos, eletrônicos, vestuário, livros, bebidas), com alíquotas diferentes. Colocar os cálculos dentro de cada classe de produto geraria forte acoplamento.

**Solução com Visitor:** Cada imposto é um visitante que sabe calcular a tributação para cada tipo de produto. Para adicionar um novo imposto, basta criar um novo visitante.

### Código-fonte

#### Produto.java

```java
package padroesextensao.visitor;

public interface Produto {
    double aceitar(VisitanteImposto visitante);
    String getDescricao();
    double getPreco();
}
```

#### VisitanteImposto.java

```java
package padroesextensao.visitor;

public interface VisitanteImposto {
    double visitar(Alimento alimento);
    double visitar(Eletronico eletronico);
    double visitar(Vestuario vestuario);
    double visitar(Livro livro);
    double visitar(Bebida bebida);
}
```

#### Alimento.java

```java
package padroesextensao.visitor;

public class Alimento implements Produto {
    private final String descricao;
    private final double preco;
    private final boolean organico;

    public Alimento(String descricao, double preco, boolean organico) {
        this.descricao = descricao;
        this.preco = preco;
        this.organico = organico;
    }

    public boolean isOrganico() { return organico; }
    public String getDescricao() { return descricao; }
    public double getPreco() { return preco; }

    @Override
    public double aceitar(VisitanteImposto v) { return v.visitar(this); }
}
```

#### Eletronico.java

```java
package padroesextensao.visitor;

public class Eletronico implements Produto {
    private final String descricao;
    private final double preco;
    private final String marca;

    public Eletronico(String descricao, double preco, String marca) {
        this.descricao = descricao;
        this.preco = preco;
        this.marca = marca;
    }

    public String getMarca() { return marca; }
    public String getDescricao() { return descricao; }
    public double getPreco() { return preco; }

    @Override
    public double aceitar(VisitanteImposto v) { return v.visitar(this); }
}
```

#### Vestuario.java

```java
package padroesextensao.visitor;

public class Vestuario implements Produto {
    private final String descricao;
    private final double preco;
    private final boolean importado;

    public Vestuario(String descricao, double preco, boolean importado) {
        this.descricao = descricao;
        this.preco = preco;
        this.importado = importado;
    }

    public boolean isImportado() { return importado; }
    public String getDescricao() { return descricao; }
    public double getPreco() { return preco; }

    @Override
    public double aceitar(VisitanteImposto v) { return v.visitar(this); }
}
```

#### Livro.java

```java
package padroesextensao.visitor;

public class Livro implements Produto {
    private final String descricao;
    private final double preco;
    private final boolean didatico;

    public Livro(String descricao, double preco, boolean didatico) {
        this.descricao = descricao;
        this.preco = preco;
        this.didatico = didatico;
    }

    public boolean isDidatico() { return didatico; }
    public String getDescricao() { return descricao; }
    public double getPreco() { return preco; }

    @Override
    public double aceitar(VisitanteImposto v) { return v.visitar(this); }
}
```

#### Bebida.java

```java
package padroesextensao.visitor;

public class Bebida implements Produto {
    private final String descricao;
    private final double preco;
    private final boolean alcoolica;

    public Bebida(String descricao, double preco, boolean alcoolica) {
        this.descricao = descricao;
        this.preco = preco;
        this.alcoolica = alcoolica;
    }

    public boolean isAlcoolica() { return alcoolica; }
    public String getDescricao() { return descricao; }
    public double getPreco() { return preco; }

    @Override
    public double aceitar(VisitanteImposto v) { return v.visitar(this); }
}
```

#### VisitanteICMS.java

```java
package padroesextensao.visitor;

public class VisitanteICMS implements VisitanteImposto {

    @Override
    public double visitar(Alimento a) { return a.getPreco() * (a.isOrganico() ? 0.04 : 0.07); }

    @Override
    public double visitar(Eletronico e) { return e.getPreco() * 0.18; }

    @Override
    public double visitar(Vestuario v) { return v.getPreco() * (v.isImportado() ? 0.25 : 0.12); }

    @Override
    public double visitar(Livro l) { return l.isDidatico() ? 0.0 : l.getPreco() * 0.03; }

    @Override
    public double visitar(Bebida b) { return b.getPreco() * (b.isAlcoolica() ? 0.20 : 0.12); }
}
```

#### VisitanteIPI.java

```java
package padroesextensao.visitor;

public class VisitanteIPI implements VisitanteImposto {

    @Override
    public double visitar(Alimento a) { return 0.0; }

    @Override
    public double visitar(Eletronico e) { return e.getPreco() * 0.10; }

    @Override
    public double visitar(Vestuario v) { return v.getPreco() * 0.05; }

    @Override
    public double visitar(Livro l) { return 0.0; }

    @Override
    public double visitar(Bebida b) { return b.getPreco() * (b.isAlcoolica() ? 0.15 : 0.05); }
}
```

#### VisitantePIS.java

```java
package padroesextensao.visitor;

public class VisitantePIS implements VisitanteImposto {

    private static final double TAXA_PIS = 0.0165;

    @Override
    public double visitar(Alimento a) { return a.getPreco() * 0.0082; }

    @Override
    public double visitar(Eletronico e) { return e.getPreco() * TAXA_PIS; }

    @Override
    public double visitar(Vestuario v) { return v.getPreco() * TAXA_PIS; }

    @Override
    public double visitar(Livro l) { return 0.0; }

    @Override
    public double visitar(Bebida b) { return b.getPreco() * TAXA_PIS; }
}
```

#### NotaFiscal.java

```java
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
        double impostos = calcularImposto(new VisitanteICMS())
                       + calcularImposto(new VisitanteIPI())
                       + calcularImposto(new VisitantePIS());
        System.out.printf("  TOTAL GERAL (produtos + impostos): R$ %8.2f%n", calcularTotalProdutos() + impostos);
        System.out.println("=======================================================");
    }
}
```

#### ExemploVisitor.java

```java
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
```

### Saída esperada

```
=== Sistema de Impostos Fiscais – Padrão Visitor ===

=======================================================
           N O T A   F I S C A L
=======================================================
  Cesta de Frutas Orgânicas       R$    45.00
  Pacote de Arroz                 R$    22.50
  Smartphone Samsung              R$  1899.00
  Notebook Lenovo                 R$  3499.00
  Camiseta Básica                 R$    59.90
  Jaqueta de Couro Importada      R$   450.00
  Java – Guia do Programador      R$   120.00
  Senhor dos Anéis – Edição Esp.  R$    85.00
  Vinho Tinto Chileno             R$    65.00
  Suco de Laranja Natural         R$     8.50
-------------------------------------------------------
  Total dos produtos:            R$  6253.90
  ICMS:                           R$  1111.24
  IPI:                            R$   560.85
  PIS/COFINS:                     R$   101.89
=======================================================
  TOTAL GERAL (produtos + impostos): R$  8027.88
=======================================================

=== Detalhamento por produto ===
  Cesta de Frutas Orgânicas       → ICMS: R$  1.80 | IPI: R$ 0.00 | PIS: R$0.37
  Pacote de Arroz                 → ICMS: R$  1.58 | IPI: R$ 0.00 | PIS: R$0.18
  Smartphone Samsung              → ICMS: R$341.82 | IPI: R$189.90 | PIS: R$31.33
  Notebook Lenovo                 → ICMS: R$629.82 | IPI: R$349.90 | PIS: R$57.73
  Camiseta Básica                 → ICMS: R$  7.19 | IPI: R$ 3.00 | PIS: R$0.99
  Jaqueta de Couro Importada      → ICMS: R$112.50 | IPI: R$22.50 | PIS: R$7.43
  Java – Guia do Programador      → ICMS: R$  0.00 | IPI: R$ 0.00 | PIS: R$0.00
  Senhor dos Anéis – Edição Esp.  → ICMS: R$  2.55 | IPI: R$ 0.00 | PIS: R$0.00
  Vinho Tinto Chileno             → ICMS: R$ 13.00 | IPI: R$ 9.75 | PIS: R$1.07
  Suco de Laranja Natural         → ICMS: R$  1.02 | IPI: R$ 0.43 | PIS: R$0.14
```

### Explicação da aplicação do padrão

1. **Interface Element (`Produto`):** Define `aceitar(VisitanteImposto)`.
2. **Interface Visitor (`VisitanteImposto`):** Declara `visitar()` sobrecarregado para cada tipo de produto.
3. **Elementos concretos (`Alimento`, `Eletronico`, `Vestuario`, `Livro`, `Bebida`):** Cada um implementa `aceitar()` chamando `visitante.visitar(this)`, realizando o double dispatch.
4. **Visitantes concretos (`VisitanteICMS`, `VisitanteIPI`, `VisitantePIS`):** Cada um encapsula a lógica de um imposto, com alíquotas diferenciadas por tipo de produto.
5. **Object Structure (`NotaFiscal`):** Percorre os produtos e aplica qualquer visitante para calcular o imposto total.
6. **Extensibilidade:** Para adicionar ISS, basta criar `VisitanteISS` — nenhuma classe de produto precisa ser alterada.

---

## Como executar

```bash
# Compilar
javac -d target/classes $(find src -name "*.java")

# Executar
java -cp target/classes padroesextensao.decorator.ExemploDecorator
java -cp target/classes padroesextensao.iterator.ExemploIterator
java -cp target/classes padroesextensao.visitor.ExemploVisitor
```

---

## Estrutura do projeto

```
padroes-extensao/
├── README.md
├── pom.xml
└── src/main/java/padroesextensao/
    ├── decorator/
    │   ├── Notificacao.java
    │   ├── NotificacaoSimples.java
    │   ├── NotificacaoDecorator.java
    │   ├── DecoradorSMS.java
    │   ├── DecoradorPush.java
    │   ├── DecoradorWhatsApp.java
    │   ├── DecoradorLog.java
    │   └── ExemploDecorator.java
    ├── iterator/
    │   ├── Livro.java
    │   ├── IteradorLivro.java
    │   ├── ColecaoLivro.java
    │   ├── IteradorPorAno.java
    │   ├── IteradorPorGenero.java
    │   ├── IteradorPorAutor.java
    │   ├── BibliotecaDigital.java
    │   └── ExemploIterator.java
    └── visitor/
        ├── Produto.java
        ├── VisitanteImposto.java
        ├── Alimento.java
        ├── Eletronico.java
        ├── Vestuario.java
        ├── Livro.java
        ├── Bebida.java
        ├── VisitanteICMS.java
        ├── VisitanteIPI.java
        ├── VisitantePIS.java
        ├── NotaFiscal.java
        └── ExemploVisitor.java
```

