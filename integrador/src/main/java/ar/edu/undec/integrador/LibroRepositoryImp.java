package ar.edu.undec.integrador;

import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class LibroRepositoryImp implements LibroRepository {

    private final Map<Long, Libro> libros = new HashMap<>();

    @Override
    public Libro buscarPorId(Long id) {
        return libros.get(id);
    }

    @Override
    public void guardar(Libro libro) {
        libros.put(libro.getId(), libro);
    }

    @Override
    public Libro buscarPorIsbn(String isbn) {
        return libros.values()
                .stream()
                .filter(libro -> libro.getIsbn().equals(isbn))
                .findFirst()
                .orElse(null);
    }

    @Override
    public List<Libro> listarTodos() {
        return new ArrayList<>(libros.values());
    }

    @Override
    public List<Libro> listarDisponible() {
        List<Libro> disponibles = new ArrayList<>();
        for (Libro libro : libros.values()) {
            if (libro.isDisponible()) {
                disponibles.add(libro);
            }
        }
        return disponibles;
    }

    @Override
    public void borrar(Long id) {
        libros.remove(id);
    }
}
