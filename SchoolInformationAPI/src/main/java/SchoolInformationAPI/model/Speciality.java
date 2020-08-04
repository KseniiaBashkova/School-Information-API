package SchoolInformationAPI.model;

import javax.persistence.*;
import java.util.Set;

@Entity
public class Speciality extends AbstractEntity implements IEntity {
    
    private String name;
    private String code;
    private Integer id_faculty;
    /**
     * set relationship.
     *
     * @autor   Bashkova Kseniia
     * @date    2019-16-05
     * @return  code
     */
    @ManyToOne
    @JoinColumn(name = "id_faculty",  insertable = false, updatable = false)
    private Faculty faculty;



    /**
     * Returns id_faculty.
     *
     * @autor   Nazariy Shukatka
     * @date    2019-11-05
     * @return  id_faculty
     */
    public Faculty getFaculty() {
        return faculty;
    }

    /**
     * Sets id_faculty.
     *
     * @autor   Nazariy Shukatka
     * @date    2019-11-05
     */
    public void setFaculty(Faculty faculty) {
        this.faculty = faculty;
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


    public Integer getId_faculty() {
        return id_faculty;
    }

    public void setId_faculty(Integer id_faculty) {
        this.id_faculty = id_faculty;
    }
}
