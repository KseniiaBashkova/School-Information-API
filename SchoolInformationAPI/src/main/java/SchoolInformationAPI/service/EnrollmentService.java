package SchoolInformationAPI.service;

import SchoolInformationAPI.dao.EnrollmentDao;
import SchoolInformationAPI.model.Enrollment;
import org.springframework.stereotype.Service;

@Service
public class  EnrollmentService extends BaseService<Enrollment, EnrollmentDao>{

    public EnrollmentService(EnrollmentDao dao) {
        super(dao);
    }
}
