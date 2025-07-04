package uce.edu.web.api.service;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.core.UriInfo;
import uce.edu.web.api.repository.IEstudianteRepo;
import uce.edu.web.api.repository.modelo.Estudiante;
import uce.edu.web.api.service.to.EstudianteTo;

import java.util.List;

@ApplicationScoped
public class EstudianteServiceImpl implements IEstudianteService {

    @Inject
    private IEstudianteRepo iEstudianteRepo;

    @Override
    public EstudianteTo buscarPorId(Integer id, UriInfo uriInfo) {
        Estudiante e1 = this.iEstudianteRepo.seleccionarPorId(id);
        EstudianteTo estudianteTo = new EstudianteTo(e1.getId(), e1.getNombre(), e1.getApellido(),
                e1.getFechaNacimiento(), e1.getGenero(), uriInfo);

        return estudianteTo;
        // return this.iEstudianteRepo.seleccionarPorId(id);
    }

    @Override
    public List<Estudiante> buscarTodos(String genero) {
        return iEstudianteRepo.seleccionarTodos(genero);
    }

    @Override
    public void actualizarPorId(Estudiante estudiante) {
        this.iEstudianteRepo.actualizarPorId(estudiante);
    }

    @Override
    public void actualizarParcialPorId(Estudiante estudiante) {
        this.iEstudianteRepo.actualizarParcialPorId(estudiante);
    }

    @Override
    public void borrarPorId(Integer id) {
        this.iEstudianteRepo.borrarPorId(id);
    }

    @Override
    public void guardar(Estudiante estudiante) {
        this.iEstudianteRepo.insertar(estudiante);
    }

}
