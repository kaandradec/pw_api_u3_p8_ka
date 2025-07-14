package uce.edu.web.api.service.mapper;

import uce.edu.web.api.repository.modelo.Profesor;
import uce.edu.web.api.service.to.ProfesorTo;

public class ProfesorMapper {
  public static ProfesorTo toTo(Profesor profesor) {
    ProfesorTo profTo = new ProfesorTo();
    profTo.setId(profesor.getId());
    profTo.setNombre(profesor.getNombre());
    profTo.setApellido(profesor.getApellido());
    profTo.setFechaNacimiento(profesor.getFechaNacimiento());
    profTo.setEmail(profesor.getEmail());
    profTo.setSalario(profesor.getSalario());
    profTo.setGenero(profesor.getGenero());

    return profTo;
  }

  public static Profesor toEntity(ProfesorTo profesorTo) {
    Profesor prof = new Profesor();
    prof.setId(profesorTo.getId());
    prof.setNombre(profesorTo.getNombre());
    prof.setApellido(profesorTo.getApellido());
    prof.setFechaNacimiento(profesorTo.getFechaNacimiento());
    prof.setEmail(profesorTo.getEmail());
    prof.setSalario(profesorTo.getSalario());
    prof.setGenero(profesorTo.getGenero());
    return prof;
  }
}
