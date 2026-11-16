package ar.edu.undec.integrador;

import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class PrestamoRepositoryEnDBImp implements PrestamoRepository {

    private final Map<Long, Prestamo> prestamos = new HashMap<>();

    @Override
    public Prestamo guardar(Prestamo prestamo) {
        prestamos.put(prestamo.getId(), prestamo);
        return prestamo;
    }

    @Override
    public Prestamo buscarPorId(Long id) {
        return prestamos.get(id);
    }

    @Override
    public List<Prestamo> listarTodos() {
        return new ArrayList<>(prestamos.values());
    }

    @Override
    public List<Prestamo> buscarActivosPorSocio(Long socioId) {
        List<Prestamo> activos = new ArrayList<>();
        for (Prestamo p : prestamos.values()) {
            if (p.getSocioId().equals(socioId) && p.getEstado() != EstadoPrestamo.DEVUELTO) {
                activos.add(p);
            }
        }
        return activos;
    }

    @Override
    public List<Prestamo> buscarTodosActivos() {
        List<Prestamo> activos = new ArrayList<>();
        for (Prestamo p : prestamos.values()) {
            if (p.getEstado() != EstadoPrestamo.DEVUELTO) {
                activos.add(p);
            }
        }
        return activos;
    }

    @Override
    public List<Prestamo> buscarTodosPorSocioId(Long socioId) {
        List<Prestamo> lista = new ArrayList<>();
        for (Prestamo p : prestamos.values()) {
            if (p.getSocioId().equals(socioId)) {
                lista.add(p);
            }
        }
        return lista;
    }
}
