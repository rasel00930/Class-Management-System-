package database;

public class AttendanceSubmit extends AttendanceMarks{
    String name, StudentID, date, attendance;

    public AttendanceSubmit(String name, String StudentID, String date, String attendance, String email, String classroomCode, int totalClasses, int totalAttendance, double attendanceMarks, double totalAttendanceMarks) {
        super(email, classroomCode, totalClasses, totalAttendance, attendanceMarks, totalAttendanceMarks);
        this.name = name;
        this.StudentID = StudentID;
        this.date = date;
        this.attendance = attendance;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStudentID() {
        return StudentID;
    }

    public void setStudentID(String StudentID) {
        this.StudentID = StudentID;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getAttendance() {
        return attendance;
    }

    public void setAttendance(String attendance) {
        this.attendance = attendance;
    }
    
}
