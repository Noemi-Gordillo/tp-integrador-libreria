package ar.edu.undec.integrador;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/prestamos")
public class PrestamoControlador {
    private final PrestamoServicio prestamoServicio;

    public PrestamoControlador(PrestamoServicio prestamoServicio) {
        this.prestamoServicio = prestamoServicio;
    }

    // GET /api/prestamos  → listar todos
    @GetMapping
    public ResponseEntity<List<Prestamo>> listarPrestamos() {
        return ResponseEntity.ok(prestamoServicio.listarTodos());
    }

    // GET /api/prestamos/{id}  → buscar por id
    @GetMapping("/{id}")
    public ResponseEntity<?> buscarPrestamo(@PathVariable Long id) {
        Prestamo prestamo = prestamoServicio.buscarPorId(id);
        if (prestamo == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Préstamo no encontrado");
        }
        return ResponseEntity.ok(prestamo);
    }

    // POST /api/prestamos  → crear
    @PostMapping
    public ResponseEntity<?> crear(@RequestBody Prestamo nuevoPrestamo) {
        try {
            Prestamo prestamoCreado = prestamoServicio.crearPrestamo(nuevoPrestamo);
            return new ResponseEntity<>(prestamoCreado, HttpStatus.CREATED);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.CONFLICT);
        }
    }

    // PATCH /api/prestamos/{id}/renovar  → +7 días
    @PatchMapping("/{id}/renovar")
    public ResponseEntity<?> renovarPrestamo(@PathVariable Long id) {
        try {
            Prestamo prestamoRenovado = prestamoServicio.renovarPrestamo(id);
            return ResponseEntity.ok(prestamoRenovado);
        } catch (IllegalStateException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    // PATCH /api/prestamos/{id}/devolver  → marcar devuelto
    @PatchMapping("/{id}/devolver")
    public ResponseEntity<?> devolverPrestamo(@PathVariable Long id) {
        try {
            Prestamo prestamoDevuelto = prestamoServicio.devolverPrestamo(id);
            return ResponseEntity.ok(prestamoDevuelto);
        } catch (IllegalStateException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }
}
