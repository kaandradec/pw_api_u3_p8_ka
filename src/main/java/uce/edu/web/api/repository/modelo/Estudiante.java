package uce.edu.web.api.repository.modelo;

import jakarta.persistence.*;
import jakarta.xml.bind.annotation.XmlRootElement;
import jakarta.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import java.time.LocalDateTime;
import java.util.List;

@XmlRootElement
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
  @OneToMany(mappedBy = "estudiante")
  private List<Hijo> hijos;

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

  @XmlJavaTypeAdapter(LocalDateTimeAdapter.class)
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

  public List<Hijo> getHijos() {
    return hijos;
  }

  public void setHijos(List<Hijo> hijos) {
    this.hijos = hijos;
  }

}
