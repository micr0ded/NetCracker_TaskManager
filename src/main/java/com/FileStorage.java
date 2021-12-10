package com;

import com.models.Task;
import org.springframework.stereotype.Component;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

@Component
public class FileStorage implements Storage{
    public void save(Task task){
        File file = new File("test.txt");
        FileWriter writer = null;
        try {
            writer = new FileWriter(file);
            writer.write(task.toString());
            writer.close();
        }
        catch (IOException e){
            e.printStackTrace();
        }
    }

    @Override
    public Task read() {
        Task task = null;
        try {
            Scanner scanner = new Scanner(new File("/Users/karmikfeels/IdeaProjects/NetCracker_TaskManager/test.txt"));
            String[] splitted;
            int id;
            String desc;
            long time;
            if (scanner.hasNext()) {
                splitted = scanner.nextLine().split(" ");
                id = Integer.parseInt(splitted[0]);
                desc = splitted[1];
                time = Long.parseLong(splitted[2]);
                task = new Task(id, desc, time);
            }
        }catch (FileNotFoundException e){
            e.printStackTrace();
        }
         return task;
    }

    @Override
    public List<Task> readAllTasks() {
        List<Task> tasks = new ArrayList<>();
        try {
            File file = new File("/Users/karmikfeels/IdeaProjects/NetCracker_TaskManager/test.txt");
            Scanner scanner = new Scanner(file);
            String[] splitted;
            int id;
            String desc;
            long time;
            while (scanner.hasNext()) {
                splitted = scanner.nextLine().split(" ");
                id = Integer.parseInt(splitted[0]);
                desc = splitted[1];
                time = Long.parseLong(splitted[2]);
                tasks.add(new Task(id, desc, time));
            }
        }
        catch (IOException e){
            e.printStackTrace();
        }
        return tasks;
    }

    @Override
    public Task getTask(int id) {
        Task task = null;
        try {
            Scanner scanner = new Scanner(new File("/Users/karmikfeels/IdeaProjects/NetCracker_TaskManager/test.txt"));
            String[] splitted;
            int ID;
            String desc;
            long time;
            while (scanner.hasNext()) {
                splitted = scanner.nextLine().split(" ");
                if(Integer.parseInt(splitted[0]) == id) {
                    ID = Integer.parseInt(splitted[0]);
                    desc = splitted[1];
                    time = Long.parseLong(splitted[2]);
                    task = new Task(ID, desc, time);
                }
            }
        }catch (FileNotFoundException e){
            e.printStackTrace();
        }
        return task;
    }


}
