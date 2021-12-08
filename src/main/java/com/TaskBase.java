package com;

import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class TaskBase {
    private List<Task> tasks;

    public TaskBase(){
        tasks = new ArrayList<Task>();
    }

    public TaskBase(List<Task> list){
        tasks = list;
    }

    public int length(){
        return tasks.size();
    }

    public void addTask(int taskID, long time, String taskDescription) {
        Task tmp = new Task(taskID, taskDescription, time);
        tasks.add(tmp);
    }

    public void addTask(Task task){
        tasks.add(task);
    }

    public Task getTask(int index){
        return tasks.get(index);
    }

    public void removeTask(int index) {
        tasks.remove(index);
    }

    public Task getTaskForDesc(String d){
        for (int i = 0; i < tasks.size(); i++){
            if (tasks.get(i).getDescription().equals(d)){
                return tasks.get(i);
            }
        }
        return null;
    }

    public Task getTaskForID(int id){
        for (int i = 0; i < tasks.size(); i++){
            if (tasks.get(i).getID() == id){
                return tasks.get(i);
            }
        }
        return null;
    }

    public List<Task> getTasksForTimePeriod(long start, long end){
        List<Task> tmpList = new ArrayList<Task>();

        for (int i = 0; i < tasks.size(); i++){
            if (tasks.get(i).getTime() > start && tasks.get(i).getTime() < end){
                tmpList.add(tasks.get(i));
            }
        }
        return tmpList;
    }

    public void removeTaskForID(int id){
        for (Task task: tasks){
            if (task.getID() == id){
                tasks.remove(task);
            }
        }
    }

    public void removeTaskForDesc(String desc){
        for (Task task: tasks){
            if (task.getDescription().equals(desc)){
                tasks.remove(task);
            }
        }
    }

    public void removeTaskForTime(long time){
        for (Task task: tasks){
            if (task.getTime() == time){
                tasks.remove(task);
            }
        }
    }

    public void removeTasksForTimePeriod(long start, long end){
        for (Task task: tasks){
            if (task.getTime() > start && task.getTime() < end){
                tasks.remove(task);
            }
        }
    }

    public void setTask(Task newTask, int id){
        for (Task task: tasks){
            if (task.getID() == id){
                task.setID(newTask.getID());
                task.setDescription(newTask.getDescription());
                task.setTime(newTask.getTime());
            }
        }
    }

    public void print(){
        for (int i = 0; i < tasks.size(); i++){
            System.out.println(tasks.get(i).toString());
        }
    }
}
