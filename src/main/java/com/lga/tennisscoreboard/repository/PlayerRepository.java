package com.lga.tennisscoreboard.repository;

import com.lga.tennisscoreboard.entity.Player;
import org.hibernate.Session;

import java.util.Optional;

public class PlayerRepository extends BaseRepository<Long, Player> {

    public PlayerRepository() {
        super(Player.class);
    }

    public Optional<Player> findPlayerByName(String playerName) {
        try (Session session = getSessionFactory().openSession()) {
            session.beginTransaction();

            Player player = session.createQuery("select p from Player p where p.name = :name", Player.class)
                    .setParameter("name", playerName).getSingleResultOrNull();

            session.getTransaction().commit();
            return Optional.ofNullable(player);
        }
    }

}
