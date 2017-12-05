package hello.com.mvc.api.service;
import hello.com.mvc.api.entities.Classes;

import java.util.List;


public interface IClassesService {
    public List<Classes> getAllClasses();
    Classes getClasses(int classesId);
    //different return type from student dao b/c should let client know if was able to add or not
    boolean addClasses(Classes classes);

    void updateClasses(Classes classes);

    void deleteClasses(int classesId);
}
