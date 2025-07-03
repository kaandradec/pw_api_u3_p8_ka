package uce.edu.web.api.repository;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;
import uce.edu.web.api.repository.modelo.Profesor;

import java.util.List;

@Transactional // Indicates that this class is a component of the application and can be
               // injected
@ApplicationScoped // Indicates that this class is a data repository
public class ProfesorRepoImpl implements IProfesorRepo {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public Profesor seleccionarPorId(Integer id) {
        return this.entityManager.find(Profesor.class, id);
    }

    @Override
    public List<Profesor> seleccionarTodos(String genero) {
        TypedQuery<Profesor> myQuery = this.entityManager
                .createQuery("SELECT p FROM Profesor p WHERE p.genero =:genero", Profesor.class);
        myQuery.setParameter("genero", genero);
        return myQuery.getResultList();
    }

    @Override
    public void insertar(Profesor profesor) {
        this.entityManager.persist(profesor);
    }

    @Override
    public void actualizarPorId(Profesor profesor) {
        this.entityManager.merge(profesor);
    }

    @Override
    public void actualizarParcialPorId(Profesor profesor) {
        this.entityManager.merge(profesor);
    }

    @Override
    public void borrarPorId(Integer id) {
        Profesor p = this.seleccionarPorId(id);

        if (p == null)
            throw new IllegalArgumentException("No se encontró el profesor con ID: " + id);

        this.entityManager.remove(p);
    }

}