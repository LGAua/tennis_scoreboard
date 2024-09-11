package com.lga.tennisscoreboard.repository;

import com.lga.tennisscoreboard.dto.Page;
import com.lga.tennisscoreboard.entity.Match;
import org.hibernate.Session;

import java.util.List;

public class MatchRepository extends BaseRepository<Long, Match> {

    private static final int PAGE_SIZE = 5;

    public MatchRepository() {
        super(Match.class);
    }

    public Page<Match> getMatches(String filterByPlayerName, int page)  {
        int offset = (page - 1) * PAGE_SIZE;

        try (Session session = getSessionFactory().openSession()) {
            session.beginTransaction();
            List<Match> matchList = session.createQuery("FROM Match m WHERE m.firstPlayer.name LIKE :playerName " +
                            "OR m.secondPlayer.name LIKE :playerName ", Match.class)
                    .setParameter("playerName", "%" + filterByPlayerName + "%")
                    .setFirstResult(offset)
                    .setMaxResults(PAGE_SIZE)
                    .list();
            session.getTransaction().commit();

            long totalElements = getTotalElements(filterByPlayerName);
            int totalPages = (int) Math.ceil((double) totalElements / PAGE_SIZE);
            return new Page<>(matchList, page, totalPages, totalElements);
        }
    }

    private long getTotalElements(String filterByPlayerName) {
        try (Session session = getSessionFactory().openSession()) {
            session.beginTransaction();
            List<Match> matchList = session.createQuery("FROM Match m WHERE m.firstPlayer.name LIKE :playerName " +
                            "OR m.secondPlayer.name LIKE :playerName ", Match.class)
                    .setParameter("playerName", "%" + filterByPlayerName + "%")
                    .list();
            session.getTransaction().commit();
            return matchList.size();
        }
    }
}