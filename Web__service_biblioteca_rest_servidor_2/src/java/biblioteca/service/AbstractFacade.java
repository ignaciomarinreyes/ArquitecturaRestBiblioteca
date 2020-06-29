/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package biblioteca.service;

import java.util.List;
import javax.persistence.EntityManager;

/**
 *
 * @author Ignacio
 */
public abstract class AbstractFacade<T> {

    private Class<T> entityClass;

    public AbstractFacade(Class<T> entityClass) {
        this.entityClass = entityClass;
    }

    protected abstract EntityManager getEntityManager();

    public void create(T entity) {
        getEntityManager().persist(entity);
    }

    public void edit(T entity) {
        getEntityManager().merge(entity);
    }

    public void remove(T entity) {
        getEntityManager().remove(getEntityManager().merge(entity));
    }

    public T find(Object id) {
        return getEntityManager().find(entityClass, id);
    }

    public List<T> findAll() {
        javax.persistence.criteria.CriteriaQuery cq = getEntityManager().getCriteriaBuilder().createQuery();
        cq.select(cq.from(entityClass));
        return getEntityManager().createQuery(cq).getResultList();
    }

    public List<T> findRange(int[] range) {
        javax.persistence.criteria.CriteriaQuery cq = getEntityManager().getCriteriaBuilder().createQuery();
        cq.select(cq.from(entityClass));
        javax.persistence.Query q = getEntityManager().createQuery(cq);
        q.setMaxResults(range[1] - range[0] + 1);
        q.setFirstResult(range[0]);
        return q.getResultList();
    }

    public int count() {
        javax.persistence.criteria.CriteriaQuery cq = getEntityManager().getCriteriaBuilder().createQuery();
        javax.persistence.criteria.Root<T> rt = cq.from(entityClass);
        cq.select(getEntityManager().getCriteriaBuilder().count(rt));
        javax.persistence.Query q = getEntityManager().createQuery(cq);
        return ((Long) q.getSingleResult()).intValue();
    }

    public List<T> findBook(Object obj) {
        javax.persistence.TypedQuery cq = getEntityManager().createQuery(
                "SELECT m FROM Libros m WHERE m.nombre = '" + obj + "' OR m.editorial = '" + obj + "' OR m.autor = '" + obj + "' OR m.editorial = '" + obj + "'", entityClass);
        return cq.getResultList();
    }

    public List<T> findBookYear(Object obj) {
        javax.persistence.TypedQuery cq = getEntityManager().createQuery(
                "SELECT m FROM Libros m WHERE m.ano = '" + obj + "'", entityClass);
        return cq.getResultList();
    }

    public List<T> findBook_HT(Object obj) {
        javax.persistence.TypedQuery cq = getEntityManager().createQuery(
                "SELECT m FROM Libros m WHERE m.cantidad > '" + obj + "'", entityClass);
        return cq.getResultList();
    }

    public List<T> findBook_LT(Object obj) {
        javax.persistence.TypedQuery cq = getEntityManager().createQuery(
                "SELECT m FROM Libros m WHERE m.cantidad < '" + obj + "'", entityClass);
        return cq.getResultList();
    }

    public List<T> findActiveClient(Object obj) {
        javax.persistence.TypedQuery cq = getEntityManager().createQuery(
                "SELECT m FROM Clientes m WHERE m.membresia == True", entityClass);
        return cq.getResultList();
    }

    public List<T> findClient(Object obj) {
        javax.persistence.TypedQuery cq = getEntityManager().createQuery(
                "SELECT m FROM Clientes m WHERE m.nombre == '" + obj + "' OR correo == '" + obj + "'", entityClass);
        return cq.getResultList();
    }

    public List<T> findDelivered(Object obj) {
        javax.persistence.TypedQuery cq = getEntityManager().createQuery(
                "SELECT m FROM Pedido m WHERE m.entregado == True", entityClass);
        return cq.getResultList();
    }

    public List<T> findAdmin(Object obj) {
        javax.persistence.TypedQuery cq = getEntityManager().createQuery(
                "SELECT m FROM Administrador m WHERE m.usuario == '" + obj + "'", entityClass);
        return cq.getResultList();
    }

}
