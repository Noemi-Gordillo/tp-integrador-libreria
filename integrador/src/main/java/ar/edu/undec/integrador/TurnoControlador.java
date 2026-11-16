package ar.edu.undec.integrador;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/turnos")
public class TurnoControlador {
    private final TurnoService turnoService;

    public TurnoControlador(TurnoService turnoService) {
        this.turnoService = turnoService;
    }

    // GET /api/turnos  → listar todos
    @GetMapping
    public ResponseEntity<List<Turno>> listarTurnos() {
        return ResponseEntity.ok(turnoService.listarTodos());
    }

    // GET /api/turnos/{id}  → buscar por id
    @GetMapping("/{id}")
    public ResponseEntity<Turno> buscarTurnoPorId(@PathVariable Long id) {
        Turno turno = turnoService.consultarTurnoPorId(id);
        if (turno == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.ok(turno);
    }

    // POST /api/turnos  → crear
    @PostMapping
    public ResponseEntity<Turno> crearTurno(@RequestBody Turno turno) {
        if (turno.getNombre() == null || turno.getNombre().isBlank()) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        turnoService.guardarTurno(turno);
        return new ResponseEntity<>(turno, HttpStatus.CREATED);
    }
}
