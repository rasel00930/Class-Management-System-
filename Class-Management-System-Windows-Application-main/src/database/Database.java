package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import security.RandomCode;

public class Database {
    
    static Connection con = createConnection();
    Statement statement;

    public Database() throws SQLException {
        this.statement = con.createStatement();
    }
    
    static Connection createConnection(){
        try {
            //return DriverManager.getConnection("jdbc:mysql://sql6.freesqldatabase.com:3306/sql6402591", "sql6402591", "Rfg1eslmD2");
            //return DriverManager.getConnection("jdbc:mysql://localhost:3306/dbtest", "root", "");
            return DriverManager.getConnection("jdbc:mysql://sql6.freesqldatabase.com:3306/sql6402581", "sql6402581", "xT5qy5EpDP");
        } catch (SQLException ex) {
            Logger.getLogger(Database.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
    public ArrayList<Account> getAllAccounts() throws SQLException{
        ArrayList<Account> acList = new ArrayList();
        
        String query = "select * from accounts";
        
        ResultSet rs = statement.executeQuery(query);
        
        while(rs.next()){
            String email = rs.getString("Email");
            String password = rs.getString("Password");
            String accountType = rs.getString("AccountType");
            
            Account ac = new Account(email, password, accountType);
            acList.add(ac);
        }
        return acList;
    }
    
    public void createAccount(String email, String password, String accountType) throws SQLException{
        String query = "insert into accounts(Email, Password, AccountType) VALUES ('"+ email +"', MD5('" + password + "'), '" + accountType + "')";
        statement.execute(query);
        
        if(accountType.equals("student")){
            
            ArrayList<StudentAccount> acList = new ArrayList();
        
            String query1 = "select * from studentaccounts";

            ResultSet rs = statement.executeQuery(query1);

            while(rs.next()){
                String studentEmail = rs.getString("Email");
                String accountID = rs.getString("AccountID");
                
                StudentAccount ac = new StudentAccount(studentEmail, accountID);
                acList.add(ac);
            }
            
            String accountID;
            while(true){
                boolean matched = false;
                accountID = RandomCode.getRandomCode();

                for(StudentAccount ac:acList){
                    if(ac.getAccountID().equals(accountID)){
                        matched = true;
                        break;
                    }
                }
                if(!matched){
                    break;
                }
            }
            
            String query2 = "INSERT INTO `studentaccounts`(`Email`, `AccountID`) VALUES ('" + email + "','" + accountID + "')";
            String query3 = "CREATE TABLE s_" + accountID + " (ClassroomCode varchar(100), TotalClasses int, TotalAttendance int, AttendanceMarks double, TotalAttendanceMarks double, PRIMARY KEY (ClassroomCode))";
            statement.execute(query2);
            statement.execute(query3);
        }
    }
    
    public void changePassword(String email, String password) throws SQLException{
        String query = "update accounts set Password=MD5('" + password + "') where Email='" + email + "'";
        statement.executeUpdate(query);
    }
    
    public void insertTeacherDetails(TeacherDetails td) throws SQLException{
        
        String email = td.getEmail();
        String name = td.getName();
        String institution = td.getInstitution();
        String department = td.getDepartment();
        String designation = td.getDesignation();
        String contactNo = td.getContactNo();
        
        String query = "INSERT INTO `teacherdetails`(`Email`, `Name`, `Institution`, `Department`, `Designation`, `ContactNo`) VALUES ('" + email + "','" + name + "','" + institution + "','" + department + "','" + designation + "','" + contactNo + "')";
        statement.execute(query);
    }
    
    public TeacherDetails readTeacherDetails(String teacherEmail) throws SQLException{
        
        String query = "select * from teacherdetails where Email = '" + teacherEmail + "'";
        
        ResultSet rs = statement.executeQuery(query);
        
        rs.next();
        String name = rs.getString("Name");
        String institution = rs.getString("Institution");
        String department = rs.getString("Department");
        String designation = rs.getString("Designation");
        String contactNo = rs.getString("ContactNo");

        TeacherDetails td = new TeacherDetails(teacherEmail, name, institution, department, designation, contactNo);
        return td;
    }
    
    public void editTeacherDetails(TeacherDetails td) throws SQLException{
        
        String name = td.getName();
        String institution = td.getInstitution();
        String department = td.getDepartment();
        String designation = td.getDesignation();
        String contactNo = td.getContactNo();
        
        String query = "UPDATE `teacherdetails` SET `Name`='" + name + "',`Institution`='" + institution + "',`Department`='" + department + "',`Designation`='" + designation + "',`ContactNo`='" + contactNo + "' WHERE Email = '" + td.getEmail() + "'";
        statement.executeUpdate(query);
    }
    
    public void insertStudentDetails(StudentDetails sd) throws SQLException{
        
        String email = sd.getEmail();
        String name = sd.getName();
        String id = sd.getId();
        String institution = sd.getInstitution();
        String department = sd.getDepartment();
        String year = sd.getYear();
        String semester = sd.getSemester();
        String section = sd.getSection();
        String contactNo = sd.getContactNo();
        
        
        String query = "INSERT INTO `studentdetails`(`Email`, `Name`, `ID`, `Institution`, `Department`, `Year`, `Semester`, `Section`, `ContactNo`) VALUES ('" + email + "', '" + name + "', '" + id + "', '" + institution + "', '" + department + "', '" + year + "', '" + semester + "', '" + section + "', '" + contactNo + "')";
        statement.execute(query);
    }
    
    public StudentDetails readStudentDetails(String studentEmail) throws SQLException{
        
        String query = "select * from studentdetails where Email = '" + studentEmail + "'";
        
        ResultSet rs = statement.executeQuery(query);
        
        rs.next();
        String name = rs.getString("Name");
        String id = rs.getString("ID");
        String institution = rs.getString("Institution");
        String department = rs.getString("Department");
        String year = rs.getString("Year");
        String semester = rs.getString("Semester");
        String section = rs.getString("section");
        String contactNo = rs.getString("ContactNo");
            
        StudentDetails sd = new StudentDetails(studentEmail, name, id, institution, department, year, semester, section, contactNo);
        return sd;
    }
     
    public void editStudentDetails(StudentDetails sd) throws SQLException{
        
        String name = sd.getName();
        String id = sd.getId();
        String institution = sd.getInstitution();
        String department = sd.getDepartment();
        String year = sd.getYear();
        String semester = sd.getSemester();
        String section = sd.getSection();
        String contactNo = sd.getContactNo();
        
        String query = "UPDATE `studentdetails` SET `Name`='" + name + "',`ID`='" + id + "',`Institution`='" + institution + "',`Department`='" + department + "',`Year`='" + year + "',`Semester`='" + semester + "',`Section`='" + section + "',`ContactNo`='" + contactNo + "' WHERE Email='" + sd.getEmail() + "'";
        statement.executeUpdate(query);
    }
     
    public ArrayList<Classroom> getAllClassrooms() throws SQLException{
        ArrayList<Classroom> classroomList = new ArrayList();
        
        String query = "select * from classrooms";
        
        ResultSet rs = statement.executeQuery(query);
        
        while(rs.next()){
            String classroomCode = rs.getString("ClassroomCode");
            String classroomName = rs.getString("ClassroomName");
            String courseName = rs.getString("CourseName");
            String teacherEmail = rs.getString("TeacherEmail");
            String announcement = rs.getString("Announcement");
            
            Classroom classroom = new Classroom(classroomCode, classroomName, courseName, teacherEmail, announcement);
            classroomList.add(classroom);
        }
        return classroomList;
    }
    
    public void createClassroom(Classroom classroom) throws SQLException{
        String query1 = "INSERT INTO `classrooms`(`ClassroomCode`, `ClassroomName`, `CourseName`, `TeacherEmail`, `Announcement`) VALUES ('" + classroom.getClassroomCode() + "','" + classroom.getClassroomName() + "','" + classroom.getCourseName() + "','" + classroom.getTeacherEmail() + "','" + classroom.getAnnouncement() + "')";
        String query2 = "CREATE TABLE c_" + classroom.getClassroomCode() + " (StudentEmail varchar(100), AccountID varchar(100), PRIMARY KEY (StudentEmail))";
        statement.execute(query1);
        statement.execute(query2);
    }
    
    public ArrayList<StudentAccount> getAllStudentAccountsOfaClassroom(String ClassroomCode) throws SQLException{
        
        ArrayList<StudentAccount> acList = new ArrayList();
        
        String query = "select * from c_" + ClassroomCode;

        ResultSet rs = statement.executeQuery(query);

        while(rs.next()){
            String studentEmail = rs.getString("StudentEmail");
            String accountID = rs.getString("AccountID");

            StudentAccount ac = new StudentAccount(studentEmail, accountID);
            acList.add(ac);
        }
        
        return acList;
    }
    
    public String getStudentAccountID(String email) throws SQLException{
        String query = "select * from studentaccounts where Email='" + email + "'";

        ResultSet rs = statement.executeQuery(query);
        rs.next();
        String accountID = rs.getString("AccountID");
        return accountID;
    }
    
    public void joinClassroom(String classroomCode, StudentAccount ac) throws SQLException{
        String query1 = "INSERT INTO `s_" + ac.getAccountID() + "`(`ClassroomCode`, `TotalClasses`, `TotalAttendance`, `AttendanceMarks`, `TotalAttendanceMarks`) VALUES ('" + classroomCode + "', 0, 0, 0, 10)";
        String query2 = "INSERT INTO `c_" + classroomCode + "`(`StudentEmail`, `AccountID`) VALUES ('" + ac.getEmail() + "','" + ac.getAccountID() + "')";
        String query3 = "CREATE TABLE " + ac.getAccountID() + "_" + classroomCode + "_marks(ExamName varchar(100), Marks double, TotalMarks double, ConvertedMarks double, ConvertedTotalMarks double, PRIMARY KEY (ExamName))";
        String query4 = "CREATE TABLE " + ac.getAccountID() + "_" + classroomCode + "_attend(Date varchar(100), Attendance varchar(10), PRIMARY KEY (Date))";
        
        statement.execute(query1);
        statement.execute(query2);
        statement.execute(query3);
        statement.execute(query4);
    }
    
    public ArrayList<Classroom> getAllClassroomsOfaTeacher(String email) throws SQLException{
        ArrayList<Classroom> classroomList = new ArrayList();
        
        String query = "SELECT * FROM `classrooms` WHERE TeacherEmail = '" + email + "'";
        ResultSet rs = statement.executeQuery(query);
        
        while(rs.next()){
            String classroomCode = rs.getString("ClassroomCode");
            String classroomName = rs.getString("ClassroomName");
            String courseName = rs.getString("CourseName");
            String announcement = rs.getString("Announcement");
            
            Classroom classroom = new Classroom(classroomCode, classroomName, courseName, email, announcement);
            classroomList.add(classroom);
        }
        return classroomList;
    }
    
    public String getAnnouncement(String classroomCode) throws SQLException{
        String query = "SELECT * FROM `classrooms` WHERE ClassroomCode = '" + classroomCode + "'";
        ResultSet rs = statement.executeQuery(query);
        
        rs.next();
        String announcement = rs.getString("Announcement");
        
        return announcement;
    }
    
    public void editAnnouncement(String classroomCode, String announcement) throws SQLException{
        String query = "UPDATE `classrooms` SET `Announcement`= '" + announcement + "' WHERE ClassroomCode = '" + classroomCode + "'";
        statement.executeUpdate(query);
    }
    
    public ArrayList<StudentDetails> getAllStudentDetailsOfaClassroom(String classroomCode) throws SQLException{
        ArrayList<StudentAccount> acList = new ArrayList();
        
        String query1 = "select * from c_" + classroomCode;
        ResultSet rs = statement.executeQuery(query1);
        
        while(rs.next()){
            String studentEmail = rs.getString("StudentEmail");
            String accountID = rs.getString("AccountID");
            
            StudentAccount ac = new StudentAccount(studentEmail, accountID);
            acList.add(ac);
        }
        
        ArrayList<StudentDetails> studentsList = new ArrayList();
        
        for(StudentAccount ac:acList){
            
            String query2 = "select * from studentdetails where Email='" + ac.getEmail() + "'";
            rs = statement.executeQuery(query2);
            
            rs.next();
            String email = rs.getString("Email");
            String name = rs.getString("Name");
            String id = rs.getString("ID");
            String institution = rs.getString("Institution");
            String department = rs.getString("Department");
            String year = rs.getString("Year");
            String semester = rs.getString("Semester");
            String section = rs.getString("Section");
            String contactNo = rs.getString("ContactNo");

            StudentDetails sDetails = new StudentDetails(email, name, id, institution, department, year, semester, section, contactNo);
            studentsList.add(sDetails);
        }
        
        return studentsList;
    }
    
    public void removeStudent(String classroomCode, String studentEmail) throws SQLException{
        
        String accountID = getStudentAccountID(studentEmail);
        
        String query1 = "DELETE FROM `s_" + accountID + "` WHERE ClassroomCode='" + classroomCode + "'";
        String query2 = "DELETE FROM `c_" + classroomCode + "` WHERE StudentEmail='" + studentEmail + "'";
        String query3 = "DROP TABLE " + accountID + "_" + classroomCode + "_marks";
        String query4 = "DROP TABLE " + accountID + "_" + classroomCode + "_attend";
        
        statement.execute(query1);
        statement.execute(query2);
        statement.execute(query3);
        statement.execute(query4);
    }
    
    public ArrayList<Classroom> getAllClassroomsOfaStudent(String studentEmail) throws SQLException{
        
        String accountID = getStudentAccountID(studentEmail);
        ArrayList<String> classroomCodeList = new ArrayList();
        
        String query1 = "SELECT * FROM s_" + accountID;
        ResultSet rs = statement.executeQuery(query1);
        
        while(rs.next()){
            String classroomCode = rs.getString("ClassroomCode");
            classroomCodeList.add(classroomCode);
        }
        
        ArrayList<Classroom> classroomList = new ArrayList();
        
        for(String code:classroomCodeList){
            
            String query2 = "SELECT * FROM `classrooms` WHERE ClassroomCode= '" + code + "'";
            rs = statement.executeQuery(query2);

            rs.next();
            String classroomCode = rs.getString("ClassroomCode");
            String classroomName = rs.getString("ClassroomName");
            String courseName = rs.getString("CourseName");
            String teacherEmail = rs.getString("TeacherEmail");
            String announcement = rs.getString("Announcement");

            Classroom classroom = new Classroom(classroomCode, classroomName, courseName, teacherEmail, announcement);
            classroomList.add(classroom);
        }
        
        return classroomList;
    }
    
    public ArrayList<ExamMarks> getExamMarks(String studentEmail, String classroomCode) throws SQLException{
        String accountID = getStudentAccountID(studentEmail);
        ArrayList<ExamMarks> examMarksList = new ArrayList();
        
        String query = "select * from " + accountID + "_" + classroomCode + "_marks";
        ResultSet rs = statement.executeQuery(query);
        
        while(rs.next()){
            String examName = rs.getString("ExamName");
            double marks = rs.getDouble("Marks");
            double totalMarks = rs.getDouble("TotalMarks");
            double convertedMarks = rs.getDouble("ConvertedMarks");
            double convertedTotalMarks = rs.getDouble("ConvertedTotalMarks");
            
            ExamMarks em = new ExamMarks(studentEmail, examName, marks, totalMarks, convertedMarks, convertedTotalMarks);
            examMarksList.add(em);
        }
        return examMarksList;
    }
    
    public ArrayList<Attendance> getAttendance(String studentEmail, String classroomCode) throws SQLException{
        String accountID = getStudentAccountID(studentEmail);
        ArrayList<Attendance> attendanceList = new ArrayList();
        
        String query = "select * from " + accountID + "_" + classroomCode + "_attend";
        ResultSet rs = statement.executeQuery(query);
        
        while(rs.next()){
            String date = rs.getString("Date");
            String attendance = rs.getString("Attendance");
            
            Attendance a = new Attendance(studentEmail, date, attendance);
            attendanceList.add(a);
        }
        return attendanceList;
    }
    
    public AttendanceMarks getAttendanceMarks(String studentEmail, String classroomCode) throws SQLException{
        String accountID = getStudentAccountID(studentEmail);
        
        String query = "select * from s_" + accountID + " where ClassroomCode='" + classroomCode + "'";
        ResultSet rs = statement.executeQuery(query);
        
        rs.next();
        int totalClasses = rs.getInt("TotalClasses");
        int totalAttendance = rs.getInt("TotalAttendance");
        double attendanceMarks = rs.getDouble("AttendanceMarks");
        double totalAttendanceMarks = rs.getDouble("TotalAttendanceMarks");

        AttendanceMarks am = new AttendanceMarks(studentEmail, classroomCode, totalClasses, totalAttendance, attendanceMarks, totalAttendanceMarks);
        
        return am;
    }
    
    public ObservableList<ExamMarksSubmit> getAllStudentsExamMarksSubmitOfaClassroom(String classroomCode) throws SQLException{
        
        ObservableList<ExamMarksSubmit> emsList = FXCollections.observableArrayList();
        ArrayList<String> emailList = new ArrayList();
        
        String query1 = "select * from c_" + classroomCode;
        ResultSet rs1 = statement.executeQuery(query1);

        while(rs1.next()){
            String studentEmail = rs1.getString("StudentEmail");
            emailList.add(studentEmail);
        }
        for(String email:emailList){
            String query2 = "select * from studentdetails where Email='" + email + "'";
            ResultSet rs2 = statement.executeQuery(query2);

            rs2.next();
            String name = rs2.getString("Name");
            String id = rs2.getString("ID");

            ExamMarksSubmit ems = new ExamMarksSubmit(email, "", 0, 0, 0, 0, name, id);
            emsList.add(ems);
        }
        
        return emsList;
    }
    
    public void setAllStudentsExamMarksOfaClassroom(String classroomCode, ObservableList<ExamMarksSubmit> emsList, String examName, double totalMarks, double convertedTotalMarks) throws SQLException{
        for(ExamMarksSubmit ems:emsList){
            String accountID = getStudentAccountID(ems.getEmail());
            String query = "INSERT INTO `" + accountID + "_" + classroomCode + "_marks`(`ExamName`, `Marks`, `TotalMarks`, `ConvertedMarks`, `ConvertedTotalMarks`) VALUES ('" + examName + "'," + ems.getMarks() + "," + totalMarks + "," + ems.getConvertedMarks() + "," + convertedTotalMarks + ")";
            statement.execute(query);
        }
    }
    
    public ObservableList<AttendanceSubmit>getAllStudentsAttendanceSubmitOfaClassroom(String classroomCode) throws SQLException{
        
        ObservableList<AttendanceSubmit> asList = FXCollections.observableArrayList();
        ArrayList<String> emailList = new ArrayList();
        
        String query1 = "select * from c_" + classroomCode;
        ResultSet rs1 = statement.executeQuery(query1);

        while(rs1.next()){
            String studentEmail = rs1.getString("StudentEmail");
            emailList.add(studentEmail);
        }
        for(String email:emailList){
            String query2 = "select * from studentdetails where Email='" + email + "'";
            ResultSet rs2 = statement.executeQuery(query2);

            rs2.next();
            String name = rs2.getString("Name");
            String id = rs2.getString("ID");

            String accountID = getStudentAccountID(email);
            
            String query3 = "SELECT * FROM `s_" + accountID + "` WHERE ClassroomCode='" + classroomCode + "'";
            ResultSet rs3 = statement.executeQuery(query3);
            
            rs3.next();
            int totalClasses = rs3.getInt("TotalClasses");
            int totalAttendance = rs3.getInt("totalAttendance");
            double attendanceMarks = rs3.getDouble("AttendanceMarks");
            double totalAttendanceMarks = rs3.getDouble("TotalAttendanceMarks");
            
            AttendanceSubmit as = new AttendanceSubmit(name, id, "", "", email, classroomCode, totalClasses, totalAttendance, attendanceMarks, totalAttendanceMarks);
            asList.add(as);
        }
        return asList;
    }
    
    public void setAllStudentsAttendanceOfaClassroom(ObservableList<AttendanceSubmit> attendanceSubmitList) throws SQLException{
        for(AttendanceSubmit as:attendanceSubmitList){
            String accountID = getStudentAccountID(as.getEmail());
            
            String query1 = "INSERT INTO `" + accountID + "_" + as.getClassroomCode() + "_attend`(`Date`, `Attendance`) VALUES ('" + as.getDate() + "','" + as.getAttendance() + "')";
            statement.execute(query1);
            
            int totalClasses = as.getTotalClasses() + 1, totalAttendance = as.getTotalAttendance();
            double attendanceMarks, totalAttendanceMarks = as.getTotalAttendanceMarks();
            if(as.getAttendance().equals("P")){
                totalAttendance += 1;
            }
            String tcs = String.valueOf(totalClasses);
            String tas = String.valueOf(totalAttendance);
            double tc = Double.parseDouble(tcs);
            double ta = Double.parseDouble(tas);
            
            attendanceMarks = Math.round((ta / tc) * totalAttendanceMarks);
            
            String query2 = "UPDATE `s_" + accountID + "` SET `TotalClasses`=" + totalClasses + ",`TotalAttendance`=" + totalAttendance + ",`AttendanceMarks`=" + attendanceMarks + ",`TotalAttendanceMarks`=" + totalAttendanceMarks + " WHERE `ClassroomCode`='" + as.getClassroomCode() + "'";
            statement.execute(query2);
        }
    }

    public void changeAttendanceMarks(String classroomCode, double totalMarks) throws SQLException {
        ArrayList<String> accountIDList = new ArrayList();
        ArrayList<AttendanceMarks> AttendanceMarksList = new ArrayList();
        String query1 = "SELECT * FROM c_" + classroomCode;
        ResultSet rs1 = statement.executeQuery(query1);
        while(rs1.next()){
            String accountID = rs1.getString("AccountID");
            accountIDList.add(accountID);
        }
        for(String id:accountIDList){
            String query2 = "SELECT * FROM s_" + id;
            ResultSet rs2 = statement.executeQuery(query2);
            while(rs2.next()){
                int totalClasses = rs2.getInt("TotalClasses");
                int totalAttendance = rs2.getInt("TotalAttendance");
                AttendanceMarks am = new AttendanceMarks(id, classroomCode, totalClasses, totalAttendance, 0, totalMarks);
                AttendanceMarksList.add(am);
            }
        }
        for(AttendanceMarks am:AttendanceMarksList){
            String tcs = String.valueOf(am.getTotalClasses());
            String tas = String.valueOf(am.getTotalAttendance());
            double tc = Double.parseDouble(tcs);
            double ta = Double.parseDouble(tas);
            
            double attendanceMarks = Math.round((ta / tc) * totalMarks);
            
            String query3 = "UPDATE `s_" + am.getEmail() + "` SET `AttendanceMarks`=" + attendanceMarks + ",`TotalAttendanceMarks`= " + totalMarks + " WHERE `ClassroomCode`='" + am.getClassroomCode() + "'";
            statement.execute(query3);
        }
    }
    
}
