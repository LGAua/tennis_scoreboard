package com.lga.tennisscoreboard.util;

import com.lga.tennisscoreboard.entity.Match;
import com.lga.tennisscoreboard.entity.Player;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

public class DataImporter {
    private static final SessionFactory sessionFactory = HibernateUtil.buildSessionFactory();

    public static void importData() {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();

            Player rogerFederer = savePlayer(session, "Roger Federer");
            Player rafaelNadal = savePlayer(session, "Rafael Nadal");
            Player serenaWilliams = savePlayer(session, "Serena Williams");
            Player lebronJames = savePlayer(session, "Lebron James");
            Player cristianoRonaldo = savePlayer(session, "Cristiano Ronaldo");
            Player muhamedAli = savePlayer(session, "Muhamed Ali");
            Player dwayneWade = savePlayer(session, "Dwayne Wade");
            Player stephenCurry = savePlayer(session, "Stephen Curry");
            Player lionelMessi = savePlayer(session, "Lionel Messi");

            saveMatch(session,rogerFederer,rafaelNadal,rafaelNadal);
            saveMatch(session,rogerFederer,serenaWilliams,serenaWilliams);
            saveMatch(session,serenaWilliams,rafaelNadal,rafaelNadal);
            saveMatch(session,lebronJames,cristianoRonaldo,cristianoRonaldo);
            saveMatch(session,lebronJames,muhamedAli,muhamedAli);
            saveMatch(session,cristianoRonaldo,rafaelNadal,rafaelNadal);
            saveMatch(session,cristianoRonaldo,muhamedAli,cristianoRonaldo);
            saveMatch(session,serenaWilliams,muhamedAli,muhamedAli);
            saveMatch(session,lebronJames,cristianoRonaldo,cristianoRonaldo);
            saveMatch(session,lebronJames,stephenCurry,stephenCurry);
            saveMatch(session,cristianoRonaldo,rafaelNadal,rafaelNadal);
            saveMatch(session,cristianoRonaldo,lionelMessi,cristianoRonaldo);
            saveMatch(session,dwayneWade,muhamedAli,dwayneWade);

            session.getTransaction().commit();
        }
    }

    private static Player savePlayer(Session session, String name) {
        Player player = Player.builder().name(name).build();
        session.persist(player);
        return player;
    }

    private static Match saveMatch(Session session, Player firstPlayer, Player secondPlayer, Player winner) {
        Match match = Match.builder()
                .firstPlayer(firstPlayer)
                .secondPlayer(secondPlayer)
                .winner(winner)
                .build();
        session.persist(match);
        return match;
    }
}
