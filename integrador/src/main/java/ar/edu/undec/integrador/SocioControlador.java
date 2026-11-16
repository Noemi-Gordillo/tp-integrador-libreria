package ar.edu.undec.integrador;

import org.springframework.web.bind.annotation.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;

@RestController
@RequestMapping("/api/socios")
public class SocioControlador {
    private final PrestamoServicio prestamoServicio;
    private final SocioRepository socioRepository;

    public SocioControlador(PrestamoServicio prestamoServicio, SocioRepository socioRepository) {
        this.prestamoServicio = prestamoServicio;
        this.socioRepository = socioRepository;
    }

    // GET /api/socios  → listar todos
    @GetMapping
    public ResponseEntity<List<Socio>> listarSocios() {
        return ResponseEntity.ok(socioRepository.listarTodos());
    }

    // POST /api/socios  → crear
    @PostMapping
    public ResponseEntity<?> crearSocio(@RequestBody Socio socio) {
        if (socio.getNombre() == null || socio.getNombre().trim().isEmpty()) {
            return ResponseEntity.badRequest().body("Nombre obligatorio");
        }
        if (socio.getEmail() == null || socio.getEmail().trim().isEmpty()) {
            return ResponseEntity.badRequest().body("Email obligatorio");
        }
        Socio socioGuardado = socioRepository.guardar(socio);
        return new ResponseEntity<>(socioGuardado, HttpStatus.CREATED);
    }

    // GET /api/socios/{id}  → buscar por id
    @GetMapping("/{id}")
    public ResponseEntity<?> buscarSocio(@PathVariable Long id) {
        Socio socioEncontrado = socioRepository.buscarPorId(id);
        if (socioEncontrado == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Socio inexistente");
        }
        return ResponseEntity.ok(socioEncontrado);
    }

    // GET /api/socios/{id}/prestamos  → préstamos del socio
    @GetMapping("/{id}/prestamos")
    public ResponseEntity<List<Prestamo>> obtenerPrestamosDelSocio(@PathVariable Long id) {
        List<Prestamo> listaPrestamos = prestamoServicio.listarTodosPrestamosPorSocioId(id);
        return ResponseEntity.ok(listaPrestamos);
    }

    // PUT /api/socios/{id}  → modificar
    @PutMapping("/{id}")
    public ResponseEntity<?> modificarSocio(@PathVariable Long id, @RequestBody Socio socio) {
        Socio socioExistente = socioRepository.buscarPorId(id);
        if (socioExistente == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Socio inexistente");
        }
        if (socio.getNombre() != null) {
            socioExistente.setNombre(socio.getNombre());
        }
        if (socio.getEmail() != null) {
            socioExistente.setEmail(socio.getEmail());
        }
        socioRepository.guardar(socioExistente);
        return ResponseEntity.ok(socioExistente);
    }

    // DELETE /api/socios/{id}  → borrar
    @DeleteMapping("/{id}")
    public ResponseEntity<?> borrarSocio(@PathVariable Long id) {
        Socio socioExistente = socioRepository.buscarPorId(id);
        if (socioExistente == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Socio inexistente");
        }
        socioRepository.borrar(id);
        return ResponseEntity.ok("Socio eliminado correctamente");
    }
}
