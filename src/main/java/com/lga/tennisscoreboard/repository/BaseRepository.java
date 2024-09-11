package com.lga.tennisscoreboard.repository;

import com.lga.tennisscoreboard.entity.BaseEntity;
import com.lga.tennisscoreboard.util.DataImporter;
import com.lga.tennisscoreboard.util.HibernateUtil;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.internal.SessionImpl;
import org.hibernate.query.criteria.HibernateCriteriaBuilder;
import org.hibernate.query.criteria.JpaCriteriaQuery;
import org.hibernate.query.criteria.JpaRoot;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
public abstract class BaseRepository<K extends Serializable, E extends BaseEntity<K>> implements DaoCrud<K, E> {

    @Getter
    private static SessionFactory sessionFactory = HibernateUtil.buildSessionFactory();

    private final Class<E> clazz;

    @Override
    public Optional<E> findById(K entityId) {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            Optional<E> entity = Optional.ofNullable(session.find(clazz, entityId));
            session.getTransaction().commit();
            return entity;
        }
    }

    @Override
    public List<E> findAll() {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            HibernateCriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();

            JpaCriteriaQuery<E> query = criteriaBuilder.createQuery(clazz);
            JpaRoot<E> fromTable = query.from(clazz);
            query.select(fromTable);

            List<E> entityList = session.createQuery(query).list();
            session.getTransaction().commit();

            return entityList;
        }
    }

    @Override
    public E save(E entity) {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            session.persist(entity);
            session.getTransaction().commit();

            return entity;
        }
    }

    @Override
    public void delete(K entityId) {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            Optional<E> entity = findById(entityId);
            entity.ifPresent(e -> session.remove(e));
            session.getTransaction().commit();
        }
    }

    @Override
    public void update(E entity) {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            if (findById(entity.getId()).isPresent()){
                session.merge(entity);
            }
            session.getTransaction().commit();
        }
    }
}
