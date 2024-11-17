package com.example.lab5_trackersystem.trackerSystemController;


import com.example.lab5_trackersystem.apiResponse.ApiResponse;
import com.example.lab5_trackersystem.model.Project;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping("/api/v1/projects-tracker")
public class TrackerSystemController {

    ArrayList<Project> projects = new ArrayList<>();

    @GetMapping("/get")
    public ArrayList<Project> getProjects() {
        return projects;
    }

    @PostMapping("/create")
    public ApiResponse createProject(@RequestBody Project project) {
        if (projects.contains(project)) {
            return new ApiResponse("Project already exists");
        }

        if (project.getStatus().equalsIgnoreCase("done") || project.getStatus().equalsIgnoreCase("not done")) {
            projects.add(project);
            return new ApiResponse("Project created");
        }else
            return new ApiResponse("project status must be done or not done");


    }


    @PutMapping("/update/{index}")
    public ApiResponse updateProject(@PathVariable int index,  @RequestBody Project project) {
        if (index < 0 || index >= projects.size()) {
            return new ApiResponse("Project does not exist");
        }
        projects.set(index, project);
        return new ApiResponse("Project updated");
    }


    @DeleteMapping("delete/{index}")
    public ApiResponse deleteProject(@PathVariable int index) {
        if (index < 0 || index >= projects.size() ) {
            return new ApiResponse("Invalid index");
        }
        projects.remove(index);
        return new ApiResponse("Project deleted");

    }

    @PutMapping("/update-status/{index}")
    public ApiResponse changeStatus(@PathVariable int index) {
        if (index < 0 || index >= projects.size() ) {
            return new ApiResponse("project does not exist");
        }

        if (projects.get(index).getStatus().equalsIgnoreCase("done")){
            projects.get(index).setStatus("not done");
        }else if( projects.get(index).getStatus().equalsIgnoreCase("not done")) {
            projects.get(index).setStatus("done");
        }else {
            return new ApiResponse("project status must be done or not done");
        }

        return new ApiResponse("Project status updated");
    }


    @GetMapping("search-by-title/{title}")
    private Project searchByTitle(@PathVariable String title) {
        for (Project project : projects) {
            if (project.getTitle().equalsIgnoreCase(title)) {
                return project;
            }
        }
        return null;
    }

    @GetMapping("search-by-company-name/{companyName}")
    public ArrayList<Project> displayProjectsByCompanyName (@PathVariable String companyName) {

        ArrayList<Project> projectsByCompanyName = new ArrayList<>();
        for (Project project : projects) {
            if (project.getCompanyName().equalsIgnoreCase(companyName)) {
                projectsByCompanyName.add(project);
            }
        }
        return projectsByCompanyName;
    }

}
