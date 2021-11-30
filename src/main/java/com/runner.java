package com;

import java.io.*;
import java.util.Date;

public class runner {
    public static void main(String[] args) {
        TaskBase test = new TaskBase();

        Task task1 = new Task(1, "Hello my name Sergey", 111111111);

        System.out.println(task1.toString());

        Date date1 = new Date(task1.getTime());

        System.out.println(date1);

        test.addTask(task1);
        test.print();

        File testF = new File("test.txt");

        try (Writer writer = new FileWriter(testF);
        Reader reader = new FileReader(testF)){
            TaskFileEditor.writeTaskBase(test, writer);

            Task task2 = TaskFileEditor.readTask(reader);

            System.out.println(task2);
        }catch (IOException e){
            e.printStackTrace();
        }

    }
}
