package com.example.lab5_trackersystem.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Project {

    private int ID;
    private String title;
    private String description;
    private String status;
    private String companyName;


}
