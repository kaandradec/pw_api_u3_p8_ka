package uce.edu.web.api.repository;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;
import uce.edu.web.api.repository.modelo.Estudiante;

import java.util.List;

@Transactional // Indica que esta clase es un componente de la aplicación y puede ser inyectada
@ApplicationScoped // Indica que esta clase es un repositorio de datos
public class EstudianteRepoImpl implements IEstudianteRepo {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public Estudiante seleccionarPorId(Integer id) {
        return this.entityManager.find(Estudiante.class, id);
    }

    @Override
    public List<Estudiante> seleccionarTodos() {
        TypedQuery<Estudiante> myQuery = this.entityManager.createQuery("SELECT e FROM Estudiante e", Estudiante.class);
        return myQuery.getResultList();
    }

}
