package util;

import com.lga.tennisscoreboard.entity.Match;
import com.lga.tennisscoreboard.entity.Player;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

public class TestDataImporter {
    private static final SessionFactory sessionFactory = HibernateUtil.buildSessionFactory();

    public static void importData() {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();

            Player rogerFederer = savePlayer(session, "Roger Federer");
            Player rafaelNadal = savePlayer(session, "Rafael Nadal");
            Player serenaWilliams = savePlayer(session, "Serena Williams");

            saveMatch(session,rogerFederer,rafaelNadal,rafaelNadal);
            saveMatch(session,rogerFederer,serenaWilliams,serenaWilliams);
            saveMatch(session,serenaWilliams,rafaelNadal,rafaelNadal);

            session.getTransaction().commit();
        }
    }

    public static void cleanData() {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();

            session.createNativeQuery("TRUNCATE TABLE matches RESTART IDENTITY CASCADE", Match.class).executeUpdate();
            session.createNativeQuery("TRUNCATE TABLE players RESTART IDENTITY CASCADE", Player.class).executeUpdate();

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
