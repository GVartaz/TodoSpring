package com.polytech.todolist.data;


import com.polytech.todolist.application.Task;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.sql.DataSource;
import java.util.List;

public class JpaTaskRepository implements TaskRepository{

    @PersistenceContext
    EntityManager entityManager;

    private DataSource dataSource;

    public JpaTaskRepository(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public void save(Task task) {
        entityManager.persist(task);
    }


    @Override
    public List<Task> findAll(String username) {
        //JPQL
        Query query = entityManager.createQuery("SELECT t from Task t where t.username = :username");
        query.setParameter("username",username);
        return query.getResultList();
    }

    @Override
    public void deleteTask(int id) {
        Query query = entityManager.createQuery("SELECT t from Task t where t.id = :id");
        query.setParameter("id",id);
        Object o= query.getSingleResult();
        entityManager.remove(o);
    }

}
