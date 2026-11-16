package ar.edu.undec.integrador;

public class Libro {
    private Long id;
    private String isbn;
    private String titulo;
    private String edicion;
    private String editorial;
    private String autores;
    private boolean disponible;

    public Libro() {
    }

    public Libro(Long id, String isbn, String titulo, String edicion, String editorial, String autores) {
        this.id = id;
        this.isbn = isbn;
        this.titulo = titulo;
        this.edicion = edicion;
        this.editorial = editorial;
        this.autores = autores;
        this.disponible = true;
    }

    public Libro(Long id, String isbn, String titulo, String edicion, String editorial, String autores, boolean disponible) {
        this.id = id;
        this.isbn = isbn;
        this.titulo = titulo;
        this.edicion = edicion;
        this.editorial = editorial;
        this.autores = autores;
        this.disponible = disponible;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getEdicion() {
        return edicion;
    }

    public void setEdicion(String edicion) {
        this.edicion = edicion;
    }

    public String getEditorial() {
        return editorial;
    }

    public void setEditorial(String editorial) {
        this.editorial = editorial;
    }

    public String getAutores() {
        return autores;
    }

    public void setAutores(String autores) {
        this.autores = autores;
    }

    public boolean isDisponible() {
        return disponible;
    }

    public void setDisponible(boolean disponible) {
        this.disponible = disponible;
    }
}
