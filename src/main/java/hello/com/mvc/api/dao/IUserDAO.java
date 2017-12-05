package hello.com.mvc.api.dao;

import hello.com.mvc.api.entities.Student;
import hello.com.mvc.api.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

//public interface IUserDAO extends JpaRepository<User,Long> {
//    User findOneByUsername(String name);
//}

public interface IUserDAO {
    User getUserByUsername(String username);
}

