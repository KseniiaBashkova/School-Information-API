package SchoolInformationAPI.model;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import java.util.Set;

@Entity
public class Semestr extends AbstractEntity implements IEntity {
    
    private char half_year;
    private String code;



    @OneToMany(mappedBy = "semestr", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Set<Event> events;



    @OneToMany(mappedBy = "semestr", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Set<Enrollment> enrollments;
    /**
     * Returns half_year.
     *
     * @autor   Nazariy Shukatka
     * @date    2019-11-05
     * @return  half_year
     */
    public char getHalf_year() {
        return half_year;
    }

    /**
     * Sets half_year.
     *
     * @param   half_year
     * @autor   Nazariy Shukatka
     * @date    2019-11-05
     */
    public void setHalf_year(char half_year) {
        this.half_year = half_year;
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
}
