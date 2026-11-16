package ar.edu.undec.integrador;

public class Socio {
    private Long id;
    private String nombre;
    private String email;
    private boolean tieneMultaSinPagar;

    public Socio() {
    }

    public Socio(Long id, String nombre, String email) {
        this.id = id;
        this.nombre = nombre;
        this.email = email;
        this.tieneMultaSinPagar = false;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public boolean tieneMultaSinPagar() {
        return tieneMultaSinPagar;
    }

    public void setTieneMultaSinPagar(boolean tieneMultaSinPagar) {
        this.tieneMultaSinPagar = tieneMultaSinPagar;
    }
}
