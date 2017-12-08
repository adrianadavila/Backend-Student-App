package hello.com.mvc.api.entities;
import javax.persistence.*;
import java.io.Serializable;


@Entity
@Table(name ="textbook")
public class Textbook implements Serializable{
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="idtextbook")
    private int idtextbook;

    @Column(name="text_title")
    private String text_title;

    //foreign key that links one student to many textbooks
    @Column(name="student_id")
    private int student_id;

    public int getIdtextbook() {
        return idtextbook;
    }

    public void setIdtextbook(int idtextbook) {
        this.idtextbook = idtextbook;
    }

    public String getText_title() {
        return text_title;
    }

    public void setText_title(String text_title) {
        this.text_title = text_title;
    }

    public int getStudent_id() {
        return student_id;
    }

    public void setStudent_id(int student_id) {
        this.student_id = student_id;
    }
}
