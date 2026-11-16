package ar.edu.undec.integrador;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
@Primary
public class TurnoRepositoryMySQL implements TurnoRepository {

    private final Map<Long, Turno> tabla = new HashMap<>();

    @Override
    public Turno buscarPorId(Long id) {
        return tabla.get(id);
    }

    @Override
    public void guardar(Turno turno) {
        tabla.put(turno.getId(), turno);
    }

    @Override
    public Turno buscarPorNombre(String nombre) {
        return tabla.values().stream()
                .filter(t -> t.getNombre().equals(nombre))
                .findFirst()
                .orElse(null);
    }

    @Override
    public List<Turno> listarTodos() {
        return new ArrayList<>(tabla.values());
    }
}
