package org.example.studentsq1.Controller;


import org.example.studentsq1.Api.ApiResponse;
import org.example.studentsq1.Model.Student;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;


@RestController
@RequestMapping("/api/v1/student")
public class StudentController {
    ArrayList<Student> students = new ArrayList<>();

    @GetMapping("/get")
    public ArrayList<Student> getStudents(){

        return students;
    }

    @PostMapping("/add")
    public ApiResponse addStudent(@RequestBody Student student){
        for (Student s : students){
            if(s.getID().equals(student.getID())){
                return new ApiResponse("ID already Registered!");
            }
        }

        students.add(student);
        return new ApiResponse("Student Added Successfully");
    }


    @PutMapping("/update/{index}")
    public ApiResponse updateStudent(@PathVariable int index,@RequestBody Student student){
        if(student != null) {
            students.set(index, student);
            return new ApiResponse("Student Updated Successfully!.");
        }
        return new ApiResponse("Body/index Is Empty..");

    }


    @DeleteMapping("/delete/{ID}")
    public ApiResponse deleteStudent(@PathVariable String ID){

        for (int i = 0; i < students.size(); i++) {
            if(students.get(i).getID().equals(ID)){
              students.remove(students.get(i));
              return new ApiResponse("Deletion Successfully!");
            }
        }
        return new ApiResponse("No Student With that ID found!");
    }



    @GetMapping("/honor/{ID}")
    public ApiResponse studentHonorCategory(@PathVariable String ID) {

        for (Student s : students){
            if (ID.equals(s.getID())){
                if (s.getGPA() > 4.75 && s.getGPA() <= 5) {
                    return new ApiResponse("First Class Honor");
                } else if (s.getGPA() > 4.25 && s.getGPA() <= 4.74) {
                    return new ApiResponse("Second Class Honor");
                } else if (s.getGPA() > 3.75 && s.getGPA() <= 4.24) {
                    return new ApiResponse("Third Class Honor");
                } else {
                    return new ApiResponse("No Class Honors...");
                }
            }
        }
        return new ApiResponse("Please Provide A registered ID.");
    }

    @GetMapping("/above-average")
    public ArrayList<Student> greaterThanAverage(){
        ArrayList<Student> greaterThanAverage = new ArrayList<>();
        double sum = 0;
        for (Student student : students) {
            sum += student.getGPA();
        }
        double averageGPA = sum / students.size();
        for (Student s : students){
            if(s.getGPA() > averageGPA){
                greaterThanAverage.add(s);
            }


        }
        return greaterThanAverage;

    }

}
