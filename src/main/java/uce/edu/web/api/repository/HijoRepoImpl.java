package uce.edu.web.api.repository;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;
import uce.edu.web.api.repository.modelo.Hijo;

import java.util.List;

@ApplicationScoped
@Transactional
public class HijoRepoImpl implements IHijoRepo {

  @PersistenceContext
  private EntityManager entityManager;

  @Override
  public List<Hijo> buscarPorEstudianteId(Integer id) {
    TypedQuery<Hijo> myQuery = this.entityManager.createQuery("SELECT h FROM Hijo h WHERE h.estudiante.id =:id",
        Hijo.class);
    myQuery.setParameter("id", id);
    return myQuery.getResultList();
  }

  @Override
  public List<Hijo> buscarPorProfesorId(Integer id) {
    TypedQuery<Hijo> myQuery = this.entityManager.createQuery("SELECT h FROM Hijo h WHERE h.profesor.id =:id",
        Hijo.class);
    myQuery.setParameter("id", id);
    return myQuery.getResultList();
  }

}
