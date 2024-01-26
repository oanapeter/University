package repository;

import exception.MyException;
import model.programState.ProgramState;

import java.io.IOException;
import java.util.List;

public interface IRepository {

    void logProgramStateExec(ProgramState programState) throws MyException, IOException;
    List<ProgramState> getProgramList();
    void setProgramList(List<ProgramState> programStatesList);


}
