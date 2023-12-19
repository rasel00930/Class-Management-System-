package database;

public class ExamMarks {
    String email, examName;
    double marks, totalMarks, convertedMarks, convertedTotalMarks;

    public ExamMarks(String email, String examName, double marks, double totalMarks, double convertedMarks, double convertedTotalMarks) {
        this.email = email;
        this.examName = examName;
        this.marks = marks;
        this.totalMarks = totalMarks;
        this.convertedMarks = convertedMarks;
        this.convertedTotalMarks = convertedTotalMarks;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getExamName() {
        return examName;
    }

    public void setExamName(String examName) {
        this.examName = examName;
    }

    public double getMarks() {
        return marks;
    }

    public void setMarks(double marks) {
        this.marks = marks;
    }

    public double getTotalMarks() {
        return totalMarks;
    }

    public void setTotalMarks(double totalMarks) {
        this.totalMarks = totalMarks;
    }

    public double getConvertedMarks() {
        return convertedMarks;
    }

    public void setConvertedMarks(double convertedMarks) {
        this.convertedMarks = convertedMarks;
    }

    public double getConvertedTotalMarks() {
        return convertedTotalMarks;
    }

    public void setConvertedTotalMarks(double convertedTotalMarks) {
        this.convertedTotalMarks = convertedTotalMarks;
    }
    
}
