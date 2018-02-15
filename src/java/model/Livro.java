package model;


public class Livro {
    String titulo;
    String escritor;
    String estilo;
    int paginas;
    int id;


    public Livro(int id, String titulo, String escritor, String estilo, int paginas) {
        this.id = id;
        this.titulo = titulo;
        this.escritor = escritor;
        this.estilo = estilo;
        this.paginas = paginas;
    }
    
    public Livro(String titulo, String escritor, String estilo, int paginas) {
        this.titulo = titulo;
        this.escritor = escritor;
        this.estilo = estilo;
        this.paginas = paginas;
    }

    public int getId() {
        return id;
    }
    
    public String getTitulo() {
        return titulo;
    }

    public String getEscritor() {
        return escritor;
    }

    public String getEstilo() {
        return estilo;
    }

    public int getPaginas() {
        return paginas;
    }

}