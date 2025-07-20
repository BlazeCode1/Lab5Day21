package org.example.trackersystem.Model;


import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Project {
    private String ID;
    private String title;
    private String description;
    private boolean status;
    private String companyName;
}
