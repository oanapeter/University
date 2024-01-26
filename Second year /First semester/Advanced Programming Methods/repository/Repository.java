package repository;

import exception.MyException;
import model.programState.ProgramState;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

public class Repository implements IRepository{
    List<ProgramState> repo;
    private String logFilePath;
    public Repository(ProgramState programState, String logFilePath)
    {
        repo = new ArrayList<>();
        this.logFilePath = logFilePath;
        repo.add(programState);
    }
    public void logProgramStateExec(ProgramState programState) throws MyException, IOException {
        PrintWriter logFile;
        logFile = new PrintWriter(new BufferedWriter(new FileWriter(logFilePath, true)));
        logFile.println(programState.programStateToString());
        logFile.close();
    }
    public List<ProgramState> getProgramList(){
        return this.repo;
    }

    @Override
    public void setProgramList(List<ProgramState> programStatesList) {
        this.repo = programStatesList;
    }

}
