package org.example.trackersystem.Controller;

import org.example.trackersystem.API.ApiResponse;
import org.example.trackersystem.Model.Project;
import org.springframework.web.bind.annotation.*;


import java.util.ArrayList;

@RestController
@RequestMapping("/api/v1/project")
public class ProjectTrackerController {

    ArrayList<Project> projects = new ArrayList<>();

    @GetMapping("/get")
    public ArrayList<Project> getProjects(){
        return projects;
    }


    @PostMapping("/add")
    public ApiResponse createProject(@RequestBody Project project){
        if(project == null){
            return new ApiResponse("Body is empty!");
        }
        for(Project p : projects){
            if(p.getID().equals(project.getID()))
                return new ApiResponse("Project With Given ID Already Exist.");
        }
        projects.add(project);
        return new ApiResponse("Project added Successfully");
    }


    @PutMapping("/update")
    public ApiResponse updateProject(@RequestBody Project project){
        if(project == null)
            return new ApiResponse("Body is Empty!");

        for (int i = 0; i < projects.size(); i++) {
            if(projects.get(i).getID().equals(project.getID())){
                projects.set(i, project);
                return new ApiResponse("Project Updated Successfully!");
            }
        }
        return new ApiResponse("No Project With Given Id Found!");
    }


    //delete
    @DeleteMapping("/delete/{ID}")
    public ApiResponse deleteProject(@PathVariable String ID){
        for(Project p : projects){
            if(p.getID().equals(ID)){
                projects.remove(p);
                return new ApiResponse("Project Removed Successfully!");
            }
        }
        return new ApiResponse("Project With given ID not found.");
    }




    //change project status to done, if not done only, if its done say its done
    @PutMapping("/status/{ID}")
    public ApiResponse updateStatus(@PathVariable String ID){
        for (Project p : projects){
            if(p.getID().equals(ID)){
                if(p.isStatus()){
                    return new ApiResponse("Project Already Done");
                }else {
                    p.setStatus(true);
                    return new ApiResponse("Status Changed To Done!");
                }
            }
        }
        return new ApiResponse("ID Not Found");
    }



    //search project by title
    @GetMapping("/search/{title}")
    public Object searchProject(@PathVariable String title){
        for (Project p : projects){
            if(p.getTitle().equalsIgnoreCase(title)){
                return p;
            }
        }
        return new ApiResponse("Project Not Found.");
    }

    //display all projects from a given company
    @GetMapping("/filter/{companyName}")
    public ArrayList<Project> displayByCompany(@PathVariable String companyName){
        ArrayList<Project> filteredProjects = new ArrayList<>();
        for(Project p : projects){
            if(p.getCompanyName().equals(companyName)){
                filteredProjects.add(p);
            }
        }
        return filteredProjects;

    }


}
