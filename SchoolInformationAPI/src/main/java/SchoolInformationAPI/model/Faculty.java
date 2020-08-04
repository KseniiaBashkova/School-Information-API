package SchoolInformationAPI.model;

import javax.persistence.Entity;
import javax.persistence.NamedNativeQuery;
import javax.persistence.OneToMany;
import java.util.Set;

@Entity
@NamedNativeQuery(name = "Faculty.findCountOfStudentsByFacultyId", query = "SELECT id_user " +
        "FROM school_user LEFT OUTER JOIN student ON (school_user.id = student.id_user)\n" +
        "WHERE id_faculty = ?;")
public class Faculty extends AbstractEntity implements IEntity {

    private String code;
    private String name;

    /**
     * Set relationship.
     *
     * @autor   Bashkova Kseniia
     * @date    2019-11-16
     * @return  code
     */
    @OneToMany(mappedBy = "faculty")
    private Set<Speciality> specialities;

    /**
     * Set relationship.
     *
     * @autor   Bashkova Kseniia
     * @date    2019-11-16
     * @return  code
     */
    @OneToMany(mappedBy = "faculty")
    private Set<Student> students;

    /**
     * Returns code.
     *
     * @autor   Nazariy Shukatka
     * @date    2019-11-05
     * @return  code
     */
    public String getCode() {
        return code;
    }

    /**
     * Sets code.
     *
     * @param   code
     * @autor   Nazariy Shukatka
     * @date    2019-11-05
     */
    public void setCode(String code) {
        this.code = code;
    }

    /**
     * Returns name.
     *
     * @autor   Nazariy Shukatka
     * @date    2019-11-05
     * @return  name
     */
    public String getName() {
        return name;
    }

    /**
     * Sets name.
     *
     * @param   name
     * @autor   Nazariy Shukatka
     * @date    2019-11-05
     */
    public void setName(String name) {
        this.name = name;
    }
}
