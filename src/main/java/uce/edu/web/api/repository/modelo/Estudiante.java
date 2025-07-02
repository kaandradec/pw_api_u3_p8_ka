package uce.edu.web.api.repository.modelo;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "estudiante")
public class Estudiante {
  @Id
  // @GeneratedValue(strategy = GenerationType.TABLE)
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "estu_id")
  private Integer id;
  @Column(name = "estu_nombre")
  private String nombre;
  @Column(name = "estu_apellido")
  private String apellido;
  @Column(name = "estu_fecha_nacimiento")
  private LocalDateTime fechaNacimiento;
  @Column(name = "estu_genero")
  private String genero;

  // SETTERS Y GETTERS

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

  public String getGenero() {
    return genero;
  }

  public void setGenero(String genero) {
    this.genero = genero;
  }

}
