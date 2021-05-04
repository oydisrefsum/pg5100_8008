package org.tsdes.backend.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.tsdes.backend.entity.*;

import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 * File adapted from file in repo https://github.com/arcuri82/testing_security_development_enterprise_systems/blob/master/intro/exercise-solutions/quiz-game/part-11/backend/src/test/java/org/tsdes/intro/exercises/quizgame/backend/service/ResetService.java
 */
@Service
@Transactional
public class ResetService {

    @PersistenceContext
    private EntityManager em;

    public void resetDatabase(){

        deleteEntities(User.class);
        deleteEntities(Movie.class);
        deleteEntities(Review.class);

    }

    private void deleteEntities(Class<?> entity){

        if(entity == null || entity.getAnnotation(Entity.class) == null){
            throw new IllegalArgumentException("Invalid non-entity class");
        }

        String name = entity.getSimpleName();
        Query query = em.createQuery("delete from " + name);
        query.executeUpdate();
    }

}
