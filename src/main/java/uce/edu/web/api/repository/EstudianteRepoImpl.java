package uce.edu.web.api.repository;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import uce.edu.web.api.repository.modelo.Estudiante;

@Transactional // Indica que esta clase es un componente de la aplicación y puede ser inyectada
@ApplicationScoped // Indica que esta clase es un repositorio de datos
public class EstudianteRepoImpl implements IEstudianteRepo {

  @PersistenceContext
  private EntityManager entityManager;

  @Override
  public Estudiante seleccionarPorId(Integer id) {
    return this.entityManager.find(Estudiante.class, id);
  }

}
