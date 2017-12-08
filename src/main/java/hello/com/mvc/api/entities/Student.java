package hello.com.mvc.api.entities;


import javax.persistence.*;
import java.io.Serializable;


@Entity
@Table(name ="student_info")

public class Student implements Serializable {

    private static final long serialVersionUIDialVersionUID = 1L;


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="id")
    private int id; //student_id in the textbook entity, foreign key

    @Column(name="name")
    private String name;

    @Column(name="major")
    private String major;

    public void setId(int id){
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMajor() {
        return major;
    }

    public void setMajor(String major) {
        this.major = major;
    }

}