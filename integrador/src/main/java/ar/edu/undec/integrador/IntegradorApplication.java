package ar.edu.undec.integrador;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication; 
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class IntegradorApplication {

	public static void main(String[] args) {
		SpringApplication.run(IntegradorApplication.class, args);
	}
	@Bean 
	CommandLineRunner
	probarLibros(LibroRepository repo) {
		return args -> {
			Libro libroPrueba = new Libro(1L, "9789500765213", "cien años de soledad", "Edición 50 aniversario", "Sudamericana", "Gabriel García Marquez");
			repo.guardar(libroPrueba);
			System.out.println("Libro guardado en inventario bibloteca");
			Libro porId = repo.buscarPorId(1L);
			System.out.println("Busqueda por ID: " + (porId != null? porId.getTitulo(): "No encontrado"));
			Libro porIsbn =
			repo.buscarPorIsbn("9789500765213");
			System.out.println("Busqueda por ISBN: " + (porIsbn != null? porIsbn.getTitulo() : "No encontrado"));
			};
			}
		}