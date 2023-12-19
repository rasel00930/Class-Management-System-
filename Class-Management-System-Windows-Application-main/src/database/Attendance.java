package database;

public class Attendance {
    String email, date, attendance;

    public Attendance(String email, String date, String attendance) {
        this.email = email;
        this.date = date;
        this.attendance = attendance;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
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
