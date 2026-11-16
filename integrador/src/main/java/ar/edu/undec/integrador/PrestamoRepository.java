package ar.edu.undec.integrador;

import java.util.List;

public interface PrestamoRepository {
    Prestamo guardar(Prestamo prestamo);
    Prestamo buscarPorId(Long id);
    List<Prestamo> listarTodos();
    List<Prestamo> buscarActivosPorSocio(Long socioId);
    List<Prestamo> buscarTodosActivos();
    List<Prestamo> buscarTodosPorSocioId(Long socioId);
}
