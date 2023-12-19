package database;

public class AttendanceMarks {
    String email, classroomCode;
    int totalClasses, totalAttendance;
    double attendanceMarks, totalAttendanceMarks;

    public AttendanceMarks(String email, String classroomCode, int totalClasses, int totalAttendance, double attendanceMarks, double totalAttendanceMarks) {
        this.email = email;
        this.classroomCode = classroomCode;
        this.totalClasses = totalClasses;
        this.totalAttendance = totalAttendance;
        this.attendanceMarks = attendanceMarks;
        this.totalAttendanceMarks = totalAttendanceMarks;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getClassroomCode() {
        return classroomCode;
    }

    public void setClassroomCode(String classroomCode) {
        this.classroomCode = classroomCode;
    }

    public int getTotalClasses() {
        return totalClasses;
    }

    public void setTotalClasses(int totalClasses) {
        this.totalClasses = totalClasses;
    }

    public int getTotalAttendance() {
        return totalAttendance;
    }

    public void setTotalAttendance(int totalAttendance) {
        this.totalAttendance = totalAttendance;
    }

    public double getAttendanceMarks() {
        return attendanceMarks;
    }

    public void setAttendanceMarks(double attendanceMarks) {
        this.attendanceMarks = attendanceMarks;
    }

    public double getTotalAttendanceMarks() {
        return totalAttendanceMarks;
    }

    public void setTotalAttendanceMarks(double totalAttendanceMarks) {
        this.totalAttendanceMarks = totalAttendanceMarks;
    }
    
}
