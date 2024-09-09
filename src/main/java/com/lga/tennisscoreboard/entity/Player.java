package com.lga.tennisscoreboard.entity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Cascade;

import java.util.List;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "players")
public class Player implements BaseEntity<Long> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
}
//    @Transient
//    private List<Match> matches;
//
//    public List<Match> getMatches(EntityManager entityManager){
//        matches = fetchMatches(entityManager);
//        return matches;
//    }
//
//    private List<Match> fetchMatches(EntityManager entityManager){
//        return entityManager.createQuery("select m from matches m " +
//                        "where m.player_1 = :palyer " +
//                        "or m.player_2 = :palyer", Match.class)
//                .setParameter("palyer", this)
//                .getResultList();
//    }
