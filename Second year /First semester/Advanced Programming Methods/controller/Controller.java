package controller;

import exception.MyException;
import model.programState.ProgramState;
import model.value.RefValue;
import model.value.Value;
import repository.IRepository;

import java.io.IOException;
import java.util.*;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.Collectors;
import java.util.stream.Stream;

class Pair {
    final ProgramState first;
    final MyException second;

    public Pair(ProgramState first, MyException second) {
        this.first = first;
        this.second = second;
    }
}

public class Controller {
    IRepository repo;
    boolean displayFlag = true;
    public ExecutorService executor;
    public void setDisplayFlag(boolean value){
        this.displayFlag=value;
    }
    public Controller(IRepository repo){
        this.repo = repo;
    }

    public void allSteps() throws MyException, IOException, InterruptedException, ExecutionException
    {
        executor = Executors.newFixedThreadPool(2);
        List<ProgramState> programStates = removeCompletedPrg(repo.getProgramList());
        while (programStates.size() > 0){
            conservativeGarbageCollector(programStates);
            oneStepForAllPrograms(programStates);
            //programStates = removeCompletedPrg(repo.getProgramList());
        }
        executor.shutdownNow();
        //repo.setProgramList(programStates);
    }
    private void display(ProgramState programState)
    {
        if(displayFlag){
            System.out.println(programState.toString());
        }
    }
    Map<Integer, Value> unsafeGarbageCollector(List<Integer> symTableAddr, Map<Integer, Value> heap){
        return heap.entrySet().stream()
                .filter(e->symTableAddr.contains(e.getKey()))
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
    }

    public Map<Integer, Value> safeGarbageCollector(List<Integer> symTableAddr, List<Integer> heapAddr, Map<Integer, Value> heap){
        return heap.entrySet().stream()
                .filter(e -> (symTableAddr.contains(e.getKey()) || heapAddr.contains(e.getKey())))
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
    }
    public void conservativeGarbageCollector(List<ProgramState> programStates){
        List<Integer> symTableAddresses = Objects.requireNonNull(programStates.stream()
                        .map(p -> getAddrFromSymTable(p.getSymTable().values()))
                        .map(Collection::stream)
                        .reduce(Stream::concat).orElse(null))
                .collect(Collectors.toList());
        programStates.forEach(p -> {
            p.getHeap().setContent((HashMap<Integer, Value>) safeGarbageCollector(symTableAddresses, getAddrFromHeap(p.getHeap().getContent().values()), p.getHeap().getContent()));
        });
    }
    List<Integer> getAddrFromSymTable(Collection<Value> symTableValues){
        return symTableValues.stream()
                .filter(v->v instanceof RefValue)
                .map(v->{RefValue v1= (RefValue)v; return v1.getAddress(); })
                .collect((Collectors.toList()));
    }
    public List<Integer> getAddrFromHeap(Collection<Value> heapValues) {
        return heapValues.stream()
                .filter(v -> v instanceof RefValue)
                .map(v -> {RefValue v1 = (RefValue) v; return v1.getAddress();})
                .collect(Collectors.toList());
    }
    public List <ProgramState> removeCompletedPrg(List<ProgramState> inPrgList){
        return inPrgList.stream().filter(p -> p.isNotCompleted()).collect(Collectors.toList());
    }
    void oneStepForAllPrograms(List<ProgramState> programList) throws MyException, InterruptedException {
        programList.forEach(programState -> {
            try{
                repo.logProgramStateExec(programState);
                display(programState);
            }catch (IOException | MyException e){
                System.out.println(e.getMessage());
            }
        });
        List<Callable<ProgramState>> callList = programList.stream()
                .map((ProgramState p) -> (Callable<ProgramState>)(p::oneStep))
                .collect(Collectors.toList());

        List<Pair> newProgramList = executor.invokeAll(callList).stream()
                .map( future -> {
                    try{
                        return new Pair(future.get(), null);
                    }catch (ExecutionException | InterruptedException e ){
                        if(e.getCause() instanceof MyException)
                            return new Pair(null, (MyException)e.getCause());
                    }
                    return null;
                }).filter(Objects::nonNull)
                .filter(pair -> pair.first != null || pair.second != null)
                .collect(Collectors.toList());
        for(Pair error:newProgramList)
            if(error.second != null)
                throw error.second;
        programList.addAll(newProgramList.stream().map(pair-> pair.first).collect(Collectors.toList()));

        programList.forEach(programState -> {
            try{
                repo.logProgramStateExec(programState);
            }catch (IOException | MyException e) {
                System.out.println(e.getMessage());
            }
        });
        repo.setProgramList(programList);
    }

    public List<ProgramState> getProgramStates(){
        return repo.getProgramList();
    }

    public void oneStep() throws InterruptedException, MyException{
        executor = Executors.newFixedThreadPool(2);
        List<ProgramState> programStates = removeCompletedPrg(repo.getProgramList());
        oneStepForAllPrograms(programStates);
        conservativeGarbageCollector(programStates);
        executor.shutdownNow();

    }
    public void setProgramStates(List<ProgramState> programStates){
        repo.setProgramList(programStates);
    }
}
