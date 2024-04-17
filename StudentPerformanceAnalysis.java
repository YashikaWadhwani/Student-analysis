import java.io.*;
import java.util.*;
import java.util.LinkedList;

class Student {
    private String rollNo;
    private String name;
    private int[][] subjectGrades;
    private List<String> extracurricularActivities;
    private String totalAttendance; 

    public Student(String rollNo, String name, int numSubjects) {
        this.rollNo = rollNo;
        this.name = name;
        this.subjectGrades = new int[numSubjects][2];
        this.extracurricularActivities = new LinkedList<>();
        this.totalAttendance = "N/A"; 
    }

    public String getRollNo() {
        return rollNo;
    }
 
    public String getName() {
        return name;
    }

    public int[][] getSubjectGrades() {
        return subjectGrades;
    }

   public void setAllSubjectGrades(int[] grades) {
    if (grades.length == subjectGrades.length) {
        List<String> subjectList = getSubjectList();
        for (int i = 0; i < grades.length; i++) {
            int subjectIndex = i; // Subject index should be the loop variable
            subjectGrades[i][0] = subjectIndex;
            subjectGrades[i][1] = grades[i];
        }
    }
}


    public void addSubjectGrade(int subjectIndex, int grade) {
        if (subjectIndex >= 0 && subjectIndex < subjectGrades.length) {
            subjectGrades[subjectIndex][0] = subjectIndex;
            subjectGrades[subjectIndex][1] = grade;
        }
    }

    public int getSubjectGrade(int subjectIndex) {
        if (subjectIndex >= 0 && subjectIndex < subjectGrades.length) {
            return subjectGrades[subjectIndex][1];
        }
        return -1;
    }

   public void addAttendance(String rollNoToUpdate, String attendance) {
    if (rollNoToUpdate.equals(rollNo)) {
        totalAttendance = attendance;
    }
}

    public String getTotalAttendance() {
        return totalAttendance;
    }

    public void addExtracurricularActivity(String activity) {
    extracurricularActivities.clear(); // Clear existing activities
    extracurricularActivities.add(activity);
}


    public List<String> getExtracurricularActivities() {
        return extracurricularActivities;
    }

    public List<String> getSubjectList() {
        List<String> subjects = new ArrayList<>();
        String[] subjectNames = {
            "Data Structure",
            "DE&CA",
            "Computer Networks",
            "Cryptography",
            "Discrete Maths"
        };
        for (int i = 0; i < subjectNames.length; i++) {
            subjects.add((i + 1) + "- " + subjectNames[i]);
        }
        return subjects;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Roll Number: ").append(rollNo).append("\n");
        sb.append("Student Name: ").append(name).append("\n");
        sb.append("Subject Grades:\n");
        for (int[] grade : subjectGrades) {
            sb.append("Subject: ").append(getSubjectName(grade[0])).append(", Grade: ").append(grade[1]).append("\n");
        }
        sb.append("Total Attendance: ").append(totalAttendance).append("\n");
        sb.append("Extracurricular Activities: ").append(extracurricularActivities).append("\n");
        return sb.toString();
    }

    private String getSubjectName(int subjectIndex) {
        String[] subjectNames = {
            "Data Structure",
            "DE&CA",
            "Computer Networks",
            "Cryptography",
            "Discrete Maths"
        };
        if (subjectIndex >= 0 && subjectIndex < subjectNames.length) {
            return subjectNames[subjectIndex];
        }
        return "Unknown";
    }
}

public class StudentPerformanceAnalysis {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        List<Student> students = new LinkedList<>();
        int numSubjects = 5; // Number of subjects

        loadStudentData(students); // Load student data from the file
        
        
        while (true) {
            System.out.println("1. Add Student");
            System.out.println("2. Add Subject Grade");
            System.out.println("3. Add Attendance");
            System.out.println("4. Add Extracurricular Activity");
            System.out.println("5. Display Student Details");
            System.out.println("6. Save Student Data");
            System.out.println("7. Clear All Data");
            System.out.println("8. Display All Student Details");
            System.out.println("9. Update Subject Grade"); 
            System.out.println("10. Exit"); 
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    System.out.print("Enter student name: ");
                    String name = scanner.next();
                    System.out.print("Enter roll number: ");
                    String rollNo = scanner.next();
                    students.add(new Student(rollNo, name, numSubjects));
                    break;
                case 2:
                    System.out.print("Enter student roll number: ");
                    String rollNoGrade = scanner.next();
                    
                    // Find the student by roll number
                    Student targetStudent = null;
                    for (Student student : students) {
                        if (student.getRollNo().equals(rollNoGrade)) {
                            targetStudent = student;
                            break;
                        }
                    }
                    
                    if (targetStudent != null) {
                        System.out.println("Enter grades for all 5 subjects separated by spaces:");
                        List<String> subjectList = targetStudent.getSubjectList();
                        for (int i = 0; i < 5; i++) {
                            System.out.print(subjectList.get(i) + ": ");
                            int grade = scanner.nextInt();
                            targetStudent.addSubjectGrade(i, grade);
                        }
                        
                        System.out.println("Subject grades added for " + targetStudent.getName());
                    } else {
                        System.out.println("Student not found with the provided roll number.");
                    }
                    break;
                
                case 3:
                System.out.print("Enter student roll number: ");
                String rollNoAttendance = scanner.next();
                System.out.print("Enter attendance percentage (e.g., 70%): ");
                String attendance = scanner.next();
                
                for (Student student : students) {
                    student.addAttendance(rollNoAttendance, attendance);
                }
                    break;
                case 4:
                    System.out.print("Enter student roll number: ");
                    String rollNoActivity = scanner.next();
                    System.out.print("Enter extracurricular activity: ");
                    String activity = scanner.next();
                    for (Student student : students) {
                        if (student.getRollNo().equals(rollNoActivity)) {
                            student.addExtracurricularActivity(activity);
                        }
                    }
                    break;
                case 5:
                    System.out.print("Enter student roll number: ");
                    String rollNoDisplay = scanner.next();
                    for (Student student : students) {
                        if (student.getRollNo().equals(rollNoDisplay)) {
                            System.out.println(student.toString());
                        }
                    }
                    break;
                case 6:
                    saveStudentData(students); // Save student data to the file
                    System.out.println("Student data saved.");
                    break;
                 case 7:
                    if (students.isEmpty()) {
                        System.out.println("No student data available. The file is empty.");
                    } else {
                        System.out.println("Clearing all student data.");
                        students.clear();
                        System.out.println("All student data has been cleared.");
                    }
                    break;
                
                case 8:
                    if (students.isEmpty()) {
                        System.out.println("No student data available. The file is empty.");
                    } else {
                        System.out.println("Displaying all student details:");
                        for (Student student : students) {
                            System.out.println(student.toString());
                        }
                    }
                    break;
                case 9:
                    System.out.print("Enter student roll number: ");
                    String rollNoUpdate = scanner.next();
                    
                    // Find the student by roll number
                    Student studentToUpdate = null;
                    for (Student student : students) {
                        if (student.getRollNo().equals(rollNoUpdate)) {
                            studentToUpdate = student;
                            break;
                        }
                    }
                    
                    if (studentToUpdate != null) {
                        List<String> subjectList = studentToUpdate.getSubjectList();
                        System.out.println("Subject List:");
                        for (int i = 0; i < subjectList.size(); i++) {
                            System.out.println(subjectList.get(i));
                        }
                
                        System.out.print("Enter the subject index (1-5) to update: ");
                        int subjectIndexToUpdate = scanner.nextInt();
                        
                        if (subjectIndexToUpdate >= 1 && subjectIndexToUpdate <= 5) {
                            System.out.print("Enter the new grade for " + subjectList.get(subjectIndexToUpdate - 1) + ": ");
                            int newGrade = scanner.nextInt();
                            
                            studentToUpdate.addSubjectGrade(subjectIndexToUpdate - 1, newGrade);
                            System.out.println("Subject grade updated for " + studentToUpdate.getName());
                        } else {
                            System.out.println("Invalid subject index. Subject index must be between 1 and 5.");
                        }
                    } else {
                        System.out.println("Student not found with the provided roll number.");
                    }
                    break;
                
                case 10:
                    System.out.println("Exiting the program.");
                    System.exit(0);
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private static void saveStudentData(List<Student> students) {
        try (PrintWriter writer = new PrintWriter(new FileWriter("student_data.txt"))) {
            for (Student student : students) {
                writer.println(student.getRollNo());
                writer.println(student.getName());
                int[][] subjectGrades = student.getSubjectGrades();
                for (int[] grade : subjectGrades) {
                    writer.println(grade[0] + " " + grade[1]);
                }
                writer.println(student.getTotalAttendance()); // Save attendance
                for (String activity : student.getExtracurricularActivities()) {
                    writer.println(activity); // Save extracurricular activities
                }
                writer.println(); // Separate student 
            }
        } catch (IOException e) {
            System.out.println("Error while saving data to the file.");
        }
    }
    

    private static void loadStudentData(List<Student> students) {
    try (BufferedReader reader = new BufferedReader(new FileReader("student_data.txt"))) {
        String line;
        while ((line = reader.readLine()) != null) {
            String rollNo = line;
            String name = reader.readLine();
            int numSubjects = 5; // Number of subjects
            Student student = new Student(rollNo, name, numSubjects);

            while ((line = reader.readLine()) != null && !line.isEmpty()) {
                String[] parts = line.split(" ");
                if (parts.length == 2) {
                    int subjectIndex = Integer.parseInt(parts[0]);
                    int grade = Integer.parseInt(parts[1]);
                    student.addSubjectGrade(subjectIndex, grade);
                }
            }

            if (line != null && line.startsWith("Total Attendance: ")) {
                String attendance = line.substring("Total Attendance: ".length());
                student.addAttendance(student.getRollNo(), attendance); // Load attendance
            }
            
            List<String> activities = new ArrayList<>();
            while ((line = reader.readLine()) != null && !line.isEmpty()) {
                 activities.add(line);
                student.addExtracurricularActivity(line); // Load extracurricular activities
            }

            students.add(student);
        }
    } catch (IOException e) {
        // File doesn't exist or is empty, so no data to load
    }
}
}