package com.polytech.todolist.data;

import com.polytech.todolist.application.Authorities;
import com.polytech.todolist.application.Users;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

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
        entityManager.persist(new Authorities(user.getUsername(),"User"));
    }

    @Override
    public boolean getUser(Users user) {
        Query query = entityManager.createQuery("SELECT u.password from Users u where u.username = :username");
        query.setParameter("username",user.getUsername());
        if( query.getResultList().size() != 1 ){
            return false;
        } else {
          /*  BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
            if(encoder.matches(user.getPassword(), user.getPassword())){*/
                return true;
            /*} else {
                return false;
            }*/
        }
    }

    @Override
    public Object checkUsername(String username) {
        Query query = entityManager.createQuery("SELECT u from Users u where u.username = :username");
        query.setParameter("username",username);
        Object o= query.getSingleResult();
        return o;
    }
}
