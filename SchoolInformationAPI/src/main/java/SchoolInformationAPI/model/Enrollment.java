package SchoolInformationAPI.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;

@Entity
@Table(name = "Enrollment")
@JsonIgnoreProperties(ignoreUnknown = true, value = {"hibernateLazyInitializer", "handler", "student"})
public class Enrollment extends AbstractEntity implements IEntity {

    private Character grade;

    private Integer id_student;
    private Integer id_course;
    private Integer id_semestr;
    /**
     * set relationship.
     *
     * @autor   Bashkova Kseniia
     * @date    2019-11-16
     * @return  code
     */
    @ManyToOne (fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "id_student", insertable = false, updatable = false)
    private Student student;

    /**
     * set relationship.
     *
     * @autor   Bashkova Kseniia
     * @date    2019-11-16
     * @return  code
     */
    @ManyToOne (fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "id_course", insertable = false, updatable = false)
    private Course course;



    @ManyToOne (fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "id_semestr", insertable = false, updatable = false)
    private Semestr semestr;
    /**
     * Returns grade.
     *
     * @autor   Nazariy Shukatka
     * @date    2019-11-05
     * @return  grade
     */
    public Character getGrade() {
        return grade;
    }

    /**
     * Sets grade.
     *
     * @param   grade
     * @autor   Nazariy Shukatka
     * @date    2019-11-05
     */
    public void setGrade(Character grade) {
        this.grade = grade;
    }

    /**
     * return student.
     *
     * @autor   Bashkova Kseniia
     * @date    2019-11-05
     */


    /**
     * return course.
     *
     * @autor   Bashkova Kseniia
     * @date    2019-11-05
     */
    public Course getCourse(){
        return course;
    }

    /**
     * set course.
     *
     * @autor   Bashkova Kseniia
     * @date   2019-11-16
     */
    public void setCourse( Course course){
        this.course = course;
    }


    public Integer getId_course() {
        return id_course;
    }

    public void setId_course(Integer id_course) {
        this.id_course = id_course;
    }

    public Integer getId_semestr() {
        return id_semestr;
    }

    public void setId_semestr(Integer id_semestr) {
        this.id_semestr = id_semestr;
    }


    public Integer getId_student() {
        return id_student;
    }

    public void setId_student(Integer id_student) {
        this.id_student = id_student;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }
}
