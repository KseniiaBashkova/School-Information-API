package SchoolInformationAPI.model;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.util.Set;

@Entity
public class Department extends AbstractEntity implements IEntity {

    private String code;
    private String name;

    /**
     * Set relationship.
     *
     * @autor   Bashkova Kseniia
     * @date    2019-11-16
     * @return  code
     */
    @OneToMany(mappedBy = "department")
    private Set<Teacher> teachers;
    /**
     * Set relationship.
     *
     * @autor   Bashkova Kseniia
     * @date    2019-11-16
     * @return  code
     */
    @OneToMany(mappedBy = "department")
    private Set<Course> courses;

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
