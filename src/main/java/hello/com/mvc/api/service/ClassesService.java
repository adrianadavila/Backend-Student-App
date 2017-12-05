package hello.com.mvc.api.service;

import hello.com.mvc.api.dao.IClassesDAO;
import hello.com.mvc.api.entities.Classes;
import hello.com.mvc.api.entities.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClassesService implements IClassesService {
    @Autowired
    private IClassesDAO classesDAO;
    @Override
    public List<Classes> getAllClasses(){
        return classesDAO.getAllClasses();
    }
    @Override
    public Classes getClasses(int classesId) {
        Classes obj = classesDAO.getClasses(classesId);
        return obj;
    }
    @Override
    public synchronized boolean addClasses(Classes classes){
        //ADRIANA--THIS BOOLEAN LOGIC is ok...stems from DAO.java file
        if (classesDAO.classesExists(classes.getClass_1(), classes.getClass_2(),
                classes.getClass_3(), classes.getClass_4())) {
            return false;
        } else {
            classesDAO.addClasses(classes);
            return true;
        }
    }
    @Override
    public void updateClasses(Classes classes) {
        classesDAO.updateClasses(classes);
    }
    @Override
    public void deleteClasses(int classesId) {
        classesDAO.deleteClasses(classesId);
    }
}

