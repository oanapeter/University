package view.command;
import controller.Controller;
import exception.MyException;

import java.io.IOException;
import java.util.concurrent.ExecutionException;

public class RunExampleCommand extends Command{
    private Controller controller;
    public RunExampleCommand(String key, String description, Controller controller){
        super(key, description);
        this.controller = controller;}
    public void execute(){
        try{
            controller.allSteps();
        }catch(MyException | InterruptedException | ExecutionException | IOException e){
            System.out.println(e.getMessage());
        }

    }


}
