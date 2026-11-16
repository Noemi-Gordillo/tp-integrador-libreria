package ar.edu.undec.integrador;

import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TurnoRepository {
    Turno buscarPorId(Long id);
    void guardar(Turno turno);
    Turno buscarPorNombre(String nombre);
    List<Turno> listarTodos();
}
