package SchoolInformationAPI.dao;

import SchoolInformationAPI.Exception.PersistenceException;
import SchoolInformationAPI.model.Student;
import org.springframework.stereotype.Repository;
import javax.persistence.Query;
import java.util.List;
import java.util.Objects;

@Repository
public class StudentDao extends BaseDao<Student> {

    protected StudentDao() {
        super(Student.class);
    }

    @Override
    public Student find(Integer id) {
        try {
            String sql = "SELECT id, password, id_user, email, id_faculty, first_name, second_name, username  FROM student JOIN school_user ON school_user.id = student.id_user WHERE id_user = ?";
            List<Student> students = em.createNativeQuery(sql, Student.class).setParameter(1, id).getResultList();

            return students.size() == 1 ? students.get(0) : null;

        } finally {
            em.close();
        }
    }

    @Override
    public List<Student> findAll() {
        try {
            return em.createNativeQuery("SELECT id_user, email, id_faculty, first_name, second_name, username  FROM student JOIN school_user ON school_user.id = student.id_user").getResultList();
        } finally {
            em.close();
        }
    }

    @Override
    public void persist(Student student) {
        Objects.requireNonNull(student);
        try {
            em.createNativeQuery("INSERT INTO student (id_user, email, id_faculty) VALUES (?,?,?)")
                    .setParameter(1, student.getIdUser())
                    .setParameter(2, student.getEmail())
                    .setParameter(3, student.getFaculty().getId())
                    .executeUpdate();
        } catch (RuntimeException e) {
            throw new PersistenceException(e);
        }
    }

    @Override
    public Student update(Student student) {
        Objects.requireNonNull(student);
        if (student.getId() == null || student.getIdFaculty() == null) {
            throw new IllegalArgumentException("Error, id_user or IdFaculty not found");
        }
        int id = student.getId();
        int idFaculty = student.getIdFaculty();
        Query querySchoolUser = em.createNativeQuery("UPDATE school_user SET first_name = ? , second_name = ?, username = ?, password = ? WHERE id = ?");
        querySchoolUser
                .setParameter(1, student.getFirstName())
                .setParameter(2, student.getSecondName())
                .setParameter(3, student.getUsername())
                .setParameter(4, student.getPassword())
                .setParameter(5, id);
        querySchoolUser.executeUpdate();
        Query queryStudent = em.createNativeQuery("UPDATE student SET email = ? , id_faculty = ? WHERE id_user = ?");
        queryStudent
                .setParameter(1, student.getEmail())
                .setParameter(2, idFaculty)
                .setParameter(3, id);
        queryStudent.executeUpdate();

        return student;
    }
    @Override
    public void remove(Student student) {
        Objects.requireNonNull(student);
        try {
            String sql = "DELETE FROM student WHERE id_user = ? ";
            em.createNativeQuery(sql, Student.class).setParameter(1, student.getIdUser()).executeUpdate();

        } catch (RuntimeException e) {
            throw new PersistenceException(e);
        }
    }

}
