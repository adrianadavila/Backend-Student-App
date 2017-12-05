package hello.com.mvc.api.dao;

import hello.com.mvc.api.entities.Classes;
import hello.com.mvc.api.entities.Student;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Transactional
@Repository

public class ClassesDAO implements IClassesDAO{
    @PersistenceContext
    private EntityManager entityManager;
    @Override
    public List<Classes> getAllClasses() {
        String hql = "select c from Classes c"; //Student b/c it is the 'entity'
        return (List<Classes>) entityManager.createQuery(hql).getResultList();
    }
    @Override
    public Classes getClasses(int classesId) {
        return entityManager.find(Classes.class, classesId);
    }
    @SuppressWarnings("unchecked")

    @Override
    public void addClasses(Classes classes) {
        entityManager.persist(classes);
    }
    @Override
    public void updateClasses(Classes classes) {
        Classes cls = getClasses(classes.getId());
        cls.setClass_1(classes.getClass_1());
        cls.setClass_2(classes.getClass_2());
        cls.setClass_3(classes.getClass_3());
        cls.setClass_4(classes.getClass_4());
        entityManager.flush();
    }
    @Override
    public void deleteClasses(int classesId) {
        entityManager.remove(getClasses(classesId));
    }
    @Override
    public boolean classesExists(String class1, String class2, String class3, String class4) {

        String hql = "FROM Classes C WHERE C.class_1 = ? and C.class_2 = ? and C.class_3 = ? and C.class_4 = ?";
        int count = entityManager.createQuery(hql).setParameter(1, class1)
                .setParameter(2, class2)
                .setParameter(3, class3)
                .setParameter(4, class4)
                .getResultList().size();
        return count > 0 ? true : false;


    }
}
