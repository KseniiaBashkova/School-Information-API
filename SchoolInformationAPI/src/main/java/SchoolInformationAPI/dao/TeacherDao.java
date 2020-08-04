package SchoolInformationAPI.dao;

import SchoolInformationAPI.Exception.PersistenceException;
import SchoolInformationAPI.model.Teacher;
import org.springframework.stereotype.Repository;
import javax.persistence.Query;
import java.util.List;
import java.util.Objects;

@Repository
public class TeacherDao extends BaseDao<Teacher> {
    protected TeacherDao() {
        super(Teacher.class);
    }

    @Override
    public Teacher find(Integer id) {
        try {
            String sql = "SELECT id, password, id_user, email, id_department, first_name, second_name, username  FROM teacher JOIN school_user ON school_user.id = teacher.id_user WHERE id_user = ?";
            List<Teacher> teachers = em.createNativeQuery(sql, Teacher.class).setParameter(1, id).getResultList();

            return teachers.size() == 1 ? teachers.get(0) : null;

        } finally {
            em.close();
        }
    }

    @Override
    public List<Teacher> findAll() {
        try {
            return em.createNativeQuery("SELECT id_user, email, id_department, first_name, second_name, username  " +
                    "FROM teacher JOIN school_user ON school_user.id = teacher.id_user").getResultList();
        } finally {
            em.close();
        }
    }

    @Override
    public void persist(Teacher teacher) {
        Objects.requireNonNull(teacher);
        try {
            em.createNativeQuery("INSERT INTO teacher (id_user, email, id_department) VALUES (?,?,?)")
                    .setParameter(1, teacher.getIdUser())
                    .setParameter(2, teacher.getEmail())
                    .setParameter(3, teacher.getIdDepartment())
                    .executeUpdate();
        } catch (RuntimeException e) {
            throw new PersistenceException(e);
        }
    }

    @Override
    public Teacher update(Teacher teacher) {
        Objects.requireNonNull(teacher);
        if (teacher.getId() == null || teacher.getIdDepartment() == null) {
            throw new IllegalArgumentException("Error, id_user or Id Departmnent not found");
        }
        int id = teacher.getId();
        int idDepartment = teacher.getIdDepartment();
        Query querySchoolUser = em.createNativeQuery("UPDATE school_user SET first_name = ? , second_name = ?, username = ?, password = ? WHERE id = ?");
        querySchoolUser
                .setParameter(1, teacher.getFirstName())
                .setParameter(2, teacher.getSecondName())
                .setParameter(3, teacher.getUsername())
                .setParameter(4, teacher.getPassword())
                .setParameter(5, id);
        querySchoolUser.executeUpdate();
        Query queryTeacher = em.createNativeQuery("UPDATE teacher SET email = ? , id_department = ? WHERE id_user = ?");
        queryTeacher
                .setParameter(1, teacher.getEmail())
                .setParameter(2, idDepartment)
                .setParameter(3, id);
        queryTeacher.executeUpdate();

        return teacher;
    }

    @Override
    public void remove(Teacher teacher) {
        Objects.requireNonNull(teacher);
        try {
            String sql = "DELETE FROM teacher WHERE id_user = ? ";
            em.createNativeQuery(sql, Teacher.class).setParameter(1, teacher.getIdUser()).executeUpdate();

        } catch (RuntimeException e) {
            throw new PersistenceException(e);
        }
    }

}
