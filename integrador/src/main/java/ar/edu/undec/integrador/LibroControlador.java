package ar.edu.undec.integrador;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
@RestController
@RequestMapping("/api/libros")
public class LibroControlador {
    private final LibroRepositoryImp libroRepository;
    public LibroControlador(LibroRepositoryImp libroRepository) 
{
    this.libroRepository = libroRepository;
}
@GetMapping
public ResponseEntity<List<Libro>> listarLibros(@RequestParam(required = false) Boolean disponible) {
    if (disponible != null && disponible) {
        List<Libro>librosDisponibles=
            libroRepository.listarDisponible();
            return ResponseEntity.ok(librosDisponibles);
    }
    return ResponseEntity.ok(libroRepository.listarTodos());
}
@GetMapping("/{id}")
public ResponseEntity<Libro> buscarLibroPorId(@PathVariable Long id)
{
    Libro libroEncontrado = libroRepository.buscarPorId(id);
    if (libroEncontrado == null)
{
    return new ResponseEntity<>(HttpStatus.NOT_FOUND);
}
    return new ResponseEntity<>(libroEncontrado,HttpStatus.OK);
}
@PostMapping
public ResponseEntity<Libro> crearLibro(@RequestBody Libro libro)
{
    if (libro.getIsbn() == null || libro.getIsbn().isBlank() || libro.getTitulo() == null || libro.getTitulo().isBlank())
    {
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }
    libroRepository.guardar(libro);
        return new ResponseEntity<>(libro,HttpStatus.CREATED); 
    }
    @PutMapping("/{id}")
    public ResponseEntity<Libro> modificarLibro(@PathVariable Long id,@RequestBody Libro libro)
    {
        Libro libroExistente = libroRepository.buscarPorId(id);
        if (libroExistente == null)
        {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        if(libro.getIsbn() == null || libro.getIsbn().isBlank() || libro.getTitulo() == null || libro.getTitulo().isBlank())
        {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        libroRepository.guardar(libro);
        return new ResponseEntity<>(libro, HttpStatus.OK);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> borrarLibro(@PathVariable Long id)
    {
        Libro libroExistente = libroRepository.buscarPorId(id);
        if (libroExistente == null)
        {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        libroRepository.borrar(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
} 