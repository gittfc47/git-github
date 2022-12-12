package edu.hccs.RestStudent;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@RestController
public class StudentController {

    /***
     * Read the student.txt file
     * @return all the students
     * @throws IOException
     */
    /**
     *   URL :  http://localhost:8080/students
     * @return all students
     * @throws IOException
     */
    @GetMapping("/students")
    public List<Student> readData() throws IOException {
        FileReader fileReader = new FileReader("C:\\Users\\TFCHEN\\Desktop\\student.txt");
        BufferedReader bufferedReader = new BufferedReader(fileReader);

        List<Student> students = new ArrayList();

        String header = bufferedReader.readLine(); // read the header
        //String[] values = header.split(",");
        //System.out.println(values[0] + values[1] + values[2] + values[3] + values[4]);

        String line = bufferedReader.readLine(); // read the first line

        while (line != null) {
            String[] data = line.split(",");// split each read line by comma
            Student student = new Student(Integer.parseInt(data[0]), data[1], Double.parseDouble(data[2]), data[3], data[4]);
            students.add(student);
            line = bufferedReader.readLine();
        }
        return students;
    }

    /***
     * http://localhost:8080/student/Aida
     * @param first_name
     * @return Student
     * @throws IOException
     */
    @GetMapping("/student/{first_name}")
    public Student  student(@PathVariable String first_name) throws IOException {
        System.out.println("search student by first_name : "+first_name);
        List<Student>  students = readData();
        for(Student student : students){
            if( student.getFirst_name().equalsIgnoreCase(first_name)){
                System.out.println("found student "+student);
                return student;
            }
        }
        System.out.println(" No student found for first_name "+first_name);
        return null;
    }

    //  http://localhost:8080/studentb?gpa=3.4&gender=Male
    @GetMapping("/studentb")
    public Student  student(@RequestParam double gpa, @RequestParam String gender) throws IOException {
        System.out.println("search student by gpa : " + gpa + " gender : "+gender);
        List<Student>  students = readData();
        for(Student student : students){
            if( student.getGpa() == gpa && student.getGender().equalsIgnoreCase(gender)){
                System.out.println("found student "+student);
                return student;
            }
        }
        System.out.println(" No student found for gpa and gender "+ gpa + gender);
        return null;
    }

    /**
     *   URL :  http://localhost:8080/average
     * @return average gpa
     * @throws IOException
     */
    @GetMapping("/average")
    public double aveGpa() throws IOException {
        List<Student>  students = readData();
        double total = 0;
        for (Student student:students) {
            total = total + student.getGpa();
        }
        double ave = total/ students.size();
        return ave;
    }



}
