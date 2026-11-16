package ar.edu.undec.integrador;

import java.time.LocalDate;

public class Prestamo {
    private Long id;
    private Long libroId;
    private Long socioId;
    private LocalDate fechaInicio;
    private LocalDate fechaDevolucion;
    private EstadoPrestamo estado;

    public Prestamo() {
    }

    public Prestamo(Long id, Long libroId, Long socioId, LocalDate fechaInicio, LocalDate fechaDevolucion) {
        this.id = id;
        this.libroId = libroId;
        this.socioId = socioId;
        this.fechaInicio = fechaInicio;
        this.fechaDevolucion = fechaDevolucion;
        this.estado = EstadoPrestamo.ACTIVO;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getLibroId() {
        return libroId;
    }

    public void setLibroId(Long libroId) {
        this.libroId = libroId;
    }

    public Long getSocioId() {
        return socioId;
    }

    public void setSocioId(Long socioId) {
        this.socioId = socioId;
    }

    public LocalDate getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(LocalDate fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public LocalDate getFechaDevolucion() {
        return fechaDevolucion;
    }

    public void setFechaDevolucion(LocalDate fechaDevolucion) {
        this.fechaDevolucion = fechaDevolucion;
    }

    public EstadoPrestamo getEstado() {
        return estado;
    }

    public void setEstado(EstadoPrestamo estado) {
        this.estado = estado;
    }
}
