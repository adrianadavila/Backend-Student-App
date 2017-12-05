package hello.com.mvc.api.entities;


import javax.persistence.*;
import java.io.Serializable;




@Entity

@Table(name ="student_info1")

public class Student implements Serializable {

    //idk what this is about but we are going to put this here cuz the tut says so
    private static final long serialVersionUIDialVersionUID = 1L;


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="id")
    private int id;

    @Column(name="name")
    private String name;

    @Column(name="major")
    private String major;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "classes_id", referencedColumnName = "classes_id")
    private Classes classes; //makes ref to classes so when make new classes you set classes in here

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

    public Classes getClasses() {
        return classes;
    }

    public void setClasses(Classes classes) {
        this.classes = classes;
    }
}