package SchoolInformationAPI.model;

import javax.persistence.*;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@Table(name = "school_user")
public class SchoolUser extends AbstractEntity implements IEntity {

    private String username;
    private String password;
    private String first_name;
    private String second_name;


    /**
     * Returns username.
     *
     * @autor   Nazariy Shukatka
     * @date    2019-11-05
     * @return  username
     */
    public String getUsername() {
        return username;
    }

    /**
     * Sets username.
     *
     * @param   username
     * @autor   Nazariy Shukatka
     * @date    2019-11-05
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * Returns password.
     *
     * @autor   Nazariy Shukatka
     * @date    2019-11-05
     * @return  password
     */
    public String getPassword() {
        return password;
    }

    /**
     * Sets password.
     *
     * @param   password
     * @autor   Nazariy Shukatka
     * @date    2019-11-05
     */
    public void setPassword(String password) {
        this.password = password;
    }
    
    /**
     * Returns first_name.
     *
     * @autor   Nazariy Shukatka
     * @date    2019-11-05
     * @return  first_name
     */
    public String getFirstName() {
        return first_name;
    }

    /**
     * Sets first_name.
     *
     * @param   first_name
     * @autor   Nazariy Shukatka
     * @date    2019-11-05
     */
    public void setFirstName(String first_name) {
        this.first_name = first_name;
    }
    
    /**
     * Returns second_name.
     *
     * @autor   Nazariy Shukatka
     * @date    2019-11-05
     * @return  second_name
     */
    public String getSecondName() {
        return second_name;
    }

    /**
     * Sets second_name.
     *
     * @param   second_name
     * @autor   Nazariy Shukatka
     * @date    2019-11-05
     */
    public void setSecondName(String second_name) {
        this.second_name = second_name;
    }
}
