package SchoolInformationAPI.dao;

import SchoolInformationAPI.Exception.PersistenceException;
import SchoolInformationAPI.model.Administrator;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Objects;


@Repository
public class AdministratorDao extends BaseDao<Administrator> {
    protected AdministratorDao() {
        super(Administrator.class);
    }

    @Override
    public List<Administrator> findAll() {
        try {
            return em.createNativeQuery("SELECT id_user, first_name, second_name, username  " +
                    "FROM administrator JOIN school_user ON school_user.id = administrator.id_user").getResultList();
        } finally {
            em.close();
        }
    }

    @Override
    public void persist(Administrator administrator) {
        Objects.requireNonNull(administrator);
        try {
            em.createNativeQuery("INSERT INTO administrator (id_user) VALUES (?)")
                    .setParameter(1, administrator.getIdUser())
                    .executeUpdate();
        } catch (RuntimeException e) {
            throw new PersistenceException(e);
        }
    }
}
