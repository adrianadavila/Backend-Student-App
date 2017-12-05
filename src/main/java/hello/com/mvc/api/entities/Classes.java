package hello.com.mvc.api.entities;

import javax.persistence.*;
import java.io.Serializable;



@Entity

@Table(name ="student_classes1")

public class Classes implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="classes_id")
    private int classes_id;

    @Column(name="class_1")
    private String class_1;

    @Column(name="class_2")
    private String class_2;

    @Column(name="class_3")
    private String class_3;

    @Column(name="class_4")
    private String class_4;

    public void setId(int id){
        this.classes_id = id;
    }

    public int getId() {
        return classes_id;
    }

    public String getClass_1() {
        return class_1;
    }

    public void setClass_1(String cls1) {
        this.class_1 = cls1;
    }

    public String getClass_2() {
        return class_2;
    }

    public void setClass_2(String cls2) {
        this.class_2 = cls2;
    }

    public String getClass_3() {
        return class_3;
    }

    public void setClass_3(String cls3) {
        this.class_3 = cls3;
    }

    public String getClass_4() {
        return class_4;
    }

    public void setClass_4(String cls4) {
        this.class_4 = cls4;
    }


}
