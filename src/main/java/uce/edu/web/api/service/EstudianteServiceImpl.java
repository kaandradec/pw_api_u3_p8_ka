package uce.edu.web.api.service;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import uce.edu.web.api.repository.IEstudianteRepo;
import uce.edu.web.api.repository.modelo.Estudiante;

import java.util.List;

@ApplicationScoped
public class EstudianteServiceImpl implements IEstudianteService {

    @Inject
    private IEstudianteRepo iEstudianteRepo;

    @Override
    public Estudiante buscarPorId(Integer id) {
        return this.iEstudianteRepo.seleccionarPorId(id);
    }

    @Override
    public List<Estudiante> buscarTodos() {
        return iEstudianteRepo.seleccionarTodos();
    }

}
