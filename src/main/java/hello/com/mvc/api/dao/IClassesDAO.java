package hello.com.mvc.api.dao;

import hello.com.mvc.api.entities.Classes;

import java.util.List;

public interface IClassesDAO {
    List<Classes> getAllClasses();

    Classes getClasses(int classesId);//returns all classes for this id, aka a row

    void addClasses(Classes classes); //adds all classes for this id, aka inserts row

    void updateClasses(Classes newCls); //allows for the opp to update each class in the classes obj

    void deleteClasses(int classesId); //deletes classes row

    //checks each class, if one class exists then the Classes instance exists. it only needs one class
    //in the class schedule to exist
    boolean classesExists(String cls1, String cls2, String cls3, String cls4);
}
