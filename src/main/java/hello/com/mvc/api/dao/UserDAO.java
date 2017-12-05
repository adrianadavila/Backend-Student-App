package hello.com.mvc.api.dao;

import com.sun.org.apache.bcel.internal.generic.IUSHR;
import hello.com.mvc.api.entities.Student;
import hello.com.mvc.api.entities.User;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;


@Transactional
@Repository
public class UserDAO implements IUserDAO {
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public User getUserByUsername(String username){
        return entityManager.createQuery(
                "SELECT u from User u WHERE u.username = :username", User.class).
                setParameter("username", username).getSingleResult();
    }
}
