package ar.edu.undec.integrador;

import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LibroRepository {
    Libro buscarPorId(Long id);

    void guardar(Libro libro);

    Libro buscarPorIsbn(String isbn);

    List<Libro> listarTodos();

    List<Libro> listarDisponible();

    void borrar(Long id);
}
