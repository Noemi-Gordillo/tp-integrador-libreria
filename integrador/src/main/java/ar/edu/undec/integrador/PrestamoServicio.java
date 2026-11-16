package ar.edu.undec.integrador;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PrestamoServicio {
    private final PrestamoRepository prestamoRepository;
    private final SocioRepository socioRepository;
    private final LibroRepository libroRepository;

    public PrestamoServicio(PrestamoRepository prestamoRepository,
                            SocioRepository socioRepository,
                            LibroRepository libroRepository) {
        this.prestamoRepository = prestamoRepository;
        this.socioRepository = socioRepository;
        this.libroRepository = libroRepository;
    }

    public List<Prestamo> listarTodos() {
        return prestamoRepository.listarTodos();
    }

    public Prestamo buscarPorId(Long id) {
        return prestamoRepository.buscarPorId(id);
    }

    public Prestamo crearPrestamo(Prestamo nuevoPrestamo) {
        Socio socio = socioRepository.buscarPorId(nuevoPrestamo.getSocioId());
        Libro libro = libroRepository.buscarPorId(nuevoPrestamo.getLibroId());

        if (socio == null || libro == null) {
            throw new RuntimeException("El socio o el libro no existen");
        }

        // Regla 1: máximo 3 préstamos activos por socio
        List<Prestamo> prestamosActivosSocio = prestamoRepository.buscarActivosPorSocio(socio.getId());
        if (prestamosActivosSocio.size() >= 3) {
            throw new RuntimeException("El socio ya tiene 3 préstamos activos");
        }

        // Regla 2: el libro no puede estar prestado por nadie
        boolean libroOcupado = prestamoRepository.buscarTodosActivos()
                .stream()
                .anyMatch(p -> p.getLibroId().equals(nuevoPrestamo.getLibroId()));
        if (libroOcupado) {
            throw new RuntimeException("El libro ya se encuentra prestado");
        }

        return prestamoRepository.guardar(nuevoPrestamo);
    }

    public List<Prestamo> listarTodosPrestamosPorSocioId(Long socioId) {
        return prestamoRepository.buscarTodosPorSocioId(socioId);
    }

    public Prestamo devolverPrestamo(Long idPrestamo) {
        Prestamo prestamo = prestamoRepository.buscarPorId(idPrestamo);
        if (prestamo == null) {
            throw new RuntimeException("El préstamo solicitado no existe");
        }
        if (prestamo.getEstado() == EstadoPrestamo.DEVUELTO) {
            throw new IllegalStateException("El préstamo ya fue devuelto");
        }
        prestamo.setEstado(EstadoPrestamo.DEVUELTO);
        return prestamoRepository.guardar(prestamo);
    }

    public Prestamo renovarPrestamo(Long id) {
        Prestamo prestamo = prestamoRepository.buscarPorId(id);
        if (prestamo == null) {
            throw new RuntimeException("El préstamo no existe");
        }
        if (!prestamo.getEstado().equals(EstadoPrestamo.ACTIVO)) {
            throw new IllegalStateException("El préstamo no está activo");
        }
        prestamo.setFechaDevolucion(prestamo.getFechaDevolucion().plusDays(7));
        prestamoRepository.guardar(prestamo);
        return prestamo;
    }
}
