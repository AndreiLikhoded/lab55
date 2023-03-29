package com.example.lab_55.entity;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor

public class Task {
    private int id;
    private String header;

    private String description;

    private String datePerform;

    private String taskOwner;

    private String newTask;
    private String taskInProcess;
    private String taskDone;

}
