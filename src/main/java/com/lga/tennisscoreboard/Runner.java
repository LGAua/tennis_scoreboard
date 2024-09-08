package com.lga.tennisscoreboard;

import com.lga.tennisscoreboard.entity.Player;
import com.lga.tennisscoreboard.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

public class Runner {
    public static void main(String[] args) {
        SessionFactory sessionFactory = HibernateUtil.buildSessionFactory();
        Session session = sessionFactory.openSession();
        session.beginTransaction();

        Player alex = Player.builder()
                .name("Alex")
                .build();

        session.persist(alex);

        session.getTransaction().commit();

    }
}
