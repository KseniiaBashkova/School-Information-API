package SchoolInformationAPI.model;

import javax.persistence.*;

@Entity
@DiscriminatorValue("Administrator")
public class Administrator extends SchoolUser implements IEntity {

    private Integer id_user;

    /**
     * Returns id_user.
     *
     * @author  Nikita Grigoryev
     * @date    2019-11-13
     * @return  id_user
     */
    public Integer getIdUser() { return id_user; }

    /**
     * Sets id_user.
     *
     * @param   id_user
     * @author  Nikita Grigoryev
     * @date    2019-11-13
     */
    public void setIdUser(Integer id_user) {
        this.id_user = id_user;
    }
}
