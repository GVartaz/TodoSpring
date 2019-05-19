package com.polytech.todolist.data;

import com.polytech.todolist.application.Users;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.sql.DataSource;
import javax.transaction.Transactional;

@Transactional
public class JpaLoginRepository implements LoginRepository{

    @PersistenceContext
    EntityManager entityManager;

    private DataSource dataSource;

    public JpaLoginRepository(DataSource dataSource) {
        this.dataSource = dataSource;
    }


    @Override
    public void register(Users user) {
        entityManager.persist(user);
    }

    @Override
    public Object getUser(String username) {
        Query query = entityManager.createQuery("SELECT u from Users u where u.username = :username");
        query.setParameter("username",username);
        Object o= query.getSingleResult();
        return o;
    }
}
