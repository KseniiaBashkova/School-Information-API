package rest;

import SchoolInformationAPI.model.*;

public class SetTemplate {

    public static Administrator setAdministrator(Integer id,
                                                 String firstName, String secondName, String password, String username) {
        final Administrator admin = new Administrator();
        admin.setId(id);
        admin.setFirstName(firstName);
        admin.setSecondName(secondName);
        admin.setPassword(password);
        admin.setUsername(username);
        return admin;
    }

    public static Course setCourse(Integer id, String code, Integer credits, Integer id_department) {
        final Course course = new Course();
        course.setId(id);
        course.setCode(code);
        course.setCredits(credits);
        course.setId_department(id_department);
        return course;
    }

    public static Department setDepartment(Integer id, String code, String name) {
        final Department department = new Department();
        department.setId(id);
        department.setCode(code);
        department.setName(name);
        return department;
    }

    public static Enrollment setEnrollment(Integer id, char grade, int idStudent, int idCourse, int idSemestr) {
        final Enrollment enrollment = new Enrollment();
        enrollment.setId(id);
        enrollment.setGrade(grade);
        enrollment.setId_student(idStudent);
        enrollment.setId_course(idCourse);
        enrollment.setId_semestr(idSemestr);
        return enrollment;
    }

    public static Event setEvent(int id, int idSemester, int idCourse, int idTeacher, int idTimestamp) {
        final Event event = new Event();
        event.setId(id);
        event.setId_semestr(idSemester);
        event.setId_course(idCourse);
        event.setId_teacher(idTeacher);
        event.setDay_in_week(idTimestamp);
        return event;
    }

    public static Faculty setFaculty(Integer id, String code, String name) {
        final Faculty faculty = new Faculty();
        faculty.setId(id);
        faculty.setCode(code);
        faculty.setName(name);
        return faculty;
    }

    public static SchoolUser setSchoolUser(Integer id, String firstName, String secondName, String password, String username) {
        final SchoolUser schoolUser = new SchoolUser();
        schoolUser.setId(id);
        schoolUser.setFirstName(firstName);
        schoolUser.setSecondName(secondName);
        schoolUser.setPassword(password);
        schoolUser.setUsername(username);
        return schoolUser;
    }

    public static Semestr setSemester(Integer id, char halfYear, String code) {
        final Semestr semester = new Semestr();
        semester.setId(id);
        semester.setHalf_year(halfYear);
        semester.setCode(code);
        return semester;
    }

    public static Speciality setSpeciality(Integer id, String name, String code) {
        final Speciality speciality = new Speciality();
        speciality.setId(id);
        speciality.setName(name);
        speciality.setCode(code);
        return speciality;
    }

    public static Student setStudent(Integer id, Integer idFaculty,
                                     String email, String firstName, String secondName, String password, String username) {
        final Student student = new Student();
        student.setId(id);
        student.setIdFaculty(idFaculty);
        student.setEmail(email);
        student.setFirstName(firstName);
        student.setSecondName(secondName);
        student.setPassword(password);
        student.setUsername(username);
        return student;
    }

    public static Teacher setTeacher(Integer id, Integer idDepartment,
                                     String email, String firstName, String secondName, String password, String username) {
        final Teacher teacher = new Teacher();
        teacher.setId(id);
        teacher.setIdDepartment(idDepartment);
        teacher.setEmail(email);
        teacher.setFirstName(firstName);
        teacher.setSecondName(secondName);
        teacher.setPassword(password);
        teacher.setUsername(username);
        return teacher;
    }

}
