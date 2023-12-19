package database;


public class Classroom {
    String classroomCode, classroomName, courseName, teacherEmail, announcement;

    public Classroom(String classroomCode, String classroomName, String courseName, String teacherEmail, String announcement) {
        this.classroomCode = classroomCode;
        this.classroomName = classroomName;
        this.courseName = courseName;
        this.teacherEmail = teacherEmail;
        this.announcement = announcement;
    }

    public String getClassroomCode() {
        return classroomCode;
    }

    public void setClassroomCode(String classCode) {
        this.classroomCode = classCode;
    }

    public String getClassroomName() {
        return classroomName;
    }

    public void setClassroomName(String className) {
        this.classroomName = className;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public String getTeacherEmail() {
        return teacherEmail;
    }

    public void setTeacherEmail(String teacherEmail) {
        this.teacherEmail = teacherEmail;
    }

    public String getAnnouncement() {
        return announcement;
    }

    public void setAnnouncement(String announcement) {
        this.announcement = announcement;
    }
    
}
