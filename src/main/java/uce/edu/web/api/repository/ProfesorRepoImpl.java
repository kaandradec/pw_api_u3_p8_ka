package uce.edu.web.api.repository;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;
import uce.edu.web.api.repository.modelo.Profesor;

import java.util.List;

@Transactional // Indicates that this class is a component of the application and can be injected
@ApplicationScoped // Indicates that this class is a data repository
public class ProfesorRepoImpl implements IProfesorRepo {

  @PersistenceContext
  private EntityManager entityManager;

  @Override
  public Profesor seleccionarPorId(Integer id) {
    return this.entityManager.find(Profesor.class, id);
  }

  @Override
  public List<Profesor> seleccionarTodos() {
    TypedQuery<Profesor> myQuery = this.entityManager.createQuery("SELECT p FROM Profesor p", Profesor.class);
    return myQuery.getResultList();
  }

}