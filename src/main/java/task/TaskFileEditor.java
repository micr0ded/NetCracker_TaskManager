package task;

import java.io.IOException;
import java.io.Reader;
import java.io.StreamTokenizer;
import java.io.Writer;

public class TaskFileEditor {
    public static void writeTask(Task task, Writer out){
        try{
            out.write(task.getID() + " " + task.getDescription() + " " + task.getTime() + "\n");
        }
        catch (IOException e){
            e.printStackTrace();
        }
    }

    public static Task readTask(Reader in){
        StreamTokenizer tokenizer = new StreamTokenizer(in);

        try{
            tokenizer.nextToken();
        }
        catch (IOException e){
            e.printStackTrace();
        }

        int id = (int)tokenizer.nval;

        if (id == 0){
            return null;
        }

        try {
            tokenizer.nextToken();
        }
        catch (IOException e){
            e.printStackTrace();
        }

        String desc = tokenizer.sval;

        try {
            tokenizer.nextToken();
        }
        catch (IOException e){
            e.printStackTrace();
        }

        long time = (long) tokenizer.nval;

        Task tmp = new Task(id, desc, time);

        return tmp;
    }

    public static void writeTaskBase(TaskBase base, Writer out){
        for(int i = 0; i < base.length(); i++){
            writeTask(base.getTask(i), out);
        }
    }
}
