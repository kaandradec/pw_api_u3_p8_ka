package uce.edu.web.api.service.to;

import java.math.BigDecimal;
import java.net.URI;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

import jakarta.ws.rs.core.UriInfo;
import uce.edu.web.api.controller.ProfesorController;

public class ProfesorTo {
  private Integer id;
  private String nombre;
  private String apellido;
  private LocalDateTime fechaNacimiento;
  private String email;
  private BigDecimal salario;
  private String genero;
  public Map<String, String> _links = new HashMap<>();

  public ProfesorTo(Integer id, String nombre, String apellido, LocalDateTime fechaNacimiento,
      String email, BigDecimal salario, String genero, UriInfo uriInfo) {
    this.id = id;
    this.nombre = nombre;
    this.apellido = apellido;
    this.fechaNacimiento = fechaNacimiento;
    this.email = email;
    this.salario = salario;
    this.genero = genero;

    URI todosHijos = uriInfo.getBaseUriBuilder().path(ProfesorController.class)
        .path(ProfesorController.class, "obtenerHijosPorId").build(id);

    _links.put("hijos", todosHijos.toString());
  }

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public String getNombre() {
    return nombre;
  }

  public void setNombre(String nombre) {
    this.nombre = nombre;
  }

  public String getApellido() {
    return apellido;
  }

  public void setApellido(String apellido) {
    this.apellido = apellido;
  }

  public LocalDateTime getFechaNacimiento() {
    return fechaNacimiento;
  }

  public void setFechaNacimiento(LocalDateTime fechaNacimiento) {
    this.fechaNacimiento = fechaNacimiento;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public BigDecimal getSalario() {
    return salario;
  }

  public void setSalario(BigDecimal salario) {
    this.salario = salario;
  }

  public String getGenero() {
    return genero;
  }

  public void setGenero(String genero) {
    this.genero = genero;
  }
}
