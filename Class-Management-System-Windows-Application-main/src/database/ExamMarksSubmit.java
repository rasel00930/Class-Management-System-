package database;


public class ExamMarksSubmit extends ExamMarks{
    String name, studentID;

    public ExamMarksSubmit(String email, String examName, double marks, double totalMarks, double convertedMarks, double convertedTotalMarks, String name, String studentID) {
        super(email, examName, marks, totalMarks, convertedMarks, convertedTotalMarks);
        this.name = name;
        this.studentID = studentID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStudentID() {
        return studentID;
    }

    public void setStudentID(String studentID) {
        this.studentID = studentID;
    }

}
