package ar.edu.undec.integrador;

import java.util.List;

public interface SocioRepository {
    Socio guardar(Socio socio);
    Socio buscarPorId(Long id);
    List<Socio> listarTodos();
    void borrar(Long id);
}
