package uce.edu.web.api.service.mapper;

import uce.edu.web.api.repository.modelo.Estudiante;
import uce.edu.web.api.service.to.EstudianteTo;

public class EstudianteMapper {
    public static EstudianteTo toTo(Estudiante estudiante) {
        EstudianteTo estTo = new EstudianteTo();
        estTo.setId(estudiante.getId());
        estTo.setNombre(estudiante.getNombre());
        estTo.setApellido(estudiante.getApellido());
        estTo.setFechaNacimiento(estudiante.getFechaNacimiento());
        estTo.setGenero(estudiante.getGenero());

        return estTo;
    }

    public static Estudiante toEntity(EstudianteTo estudianteTo) {
        Estudiante est = new Estudiante();
        est.setId(estudianteTo.getId());
        est.setNombre(estudianteTo.getNombre());
        est.setApellido(estudianteTo.getApellido());
        est.setFechaNacimiento(estudianteTo.getFechaNacimiento());
        est.setGenero(estudianteTo.getGenero());
        return est;
    }
}
