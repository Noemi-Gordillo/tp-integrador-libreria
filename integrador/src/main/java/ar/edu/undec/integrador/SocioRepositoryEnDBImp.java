package ar.edu.undec.integrador;

import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class SocioRepositoryEnDBImp implements SocioRepository {

    private final Map<Long, Socio> socios = new HashMap<>();

    @Override
    public Socio guardar(Socio socio) {
        socios.put(socio.getId(), socio);
        return socio;
    }

    @Override
    public Socio buscarPorId(Long id) {
        return socios.get(id);
    }

    @Override
    public List<Socio> listarTodos() {
        return new ArrayList<>(socios.values());
    }

    @Override
    public void borrar(Long id) {
        socios.remove(id);
    }
}
