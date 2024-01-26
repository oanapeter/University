package com.example.a7;

import controller.Controller;

import exception.MyException;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import model.ADT.dictionary.MyIDictionary;
import model.ADT.heap.MyIHeap;
import model.expression.ValueExpression;
import model.expression.VariableExpression;
import model.programState.ProgramState;
import model.statement.*;
import model.type.IntType;
import model.value.IntValue;
import model.value.Value;

import java.util.Map;
import java.util.stream.Collectors;


import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

class Pair<T1, T2>{
    T1 first;
    T2 second;
    public Pair(T1 first, T2 second){
        this.first = first;
        this.second = second;
    }
}

public class Program {
    private Controller controller;
    @FXML
    private TextField nrOfProgramStatesTextField;
    @FXML
    private TableView<Pair<Integer, Value>> heapTableView;
    @FXML
    private TableColumn<Pair<Integer, Value>, Integer> addressColumn;
    @FXML
    private TableColumn<Pair<Integer, Value>, String> valueColumn;
    @FXML
    private ListView<String> outputListView;
    @FXML
    private ListView<String> fileTableListView;
    @FXML
    private ListView<String> prgStateIDListView;
    @FXML
    private TableView<Pair<String, Value>> symbolTableView;
    @FXML
    private TableColumn<Pair<String, Value>, String> variableNameColumn;
    @FXML
    private TableColumn<Pair<String, Value>, String> variableValueColumn;
    @FXML
    private ListView<String> executionStackListView;
    @FXML
    private Button runOneStepButton;

    public void setController(Controller controller){
        this.controller = controller;
        populate();

    }
    public void initialize(){
        prgStateIDListView.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
        addressColumn.setCellValueFactory(p-> new SimpleIntegerProperty(p.getValue().first).asObject());
        valueColumn.setCellValueFactory(p-> new SimpleStringProperty(p.getValue().second.toString()));
        variableNameColumn.setCellValueFactory(p-> new SimpleStringProperty(p.getValue().first));
        variableValueColumn.setCellValueFactory(p-> new SimpleStringProperty(p.getValue().second.toString()));
    }

    private ProgramState getSelectedProgramState(){
        if(controller.getProgramStates().size() == 0)
            return null;
        else{
            int selectedId = prgStateIDListView.getSelectionModel().getSelectedIndex();
            if(selectedId == -1)
                return controller.getProgramStates().get(0);
            else
                return controller.getProgramStates().get(selectedId);

        }
    }
    private void populateNumberOfProgramStatesTextField(){
        List<ProgramState> programStates = controller.getProgramStates();
        nrOfProgramStatesTextField.setText(String.valueOf(programStates.size()));

    }

    private void populateHeapTableView(){
        ProgramState programState = getSelectedProgramState();
        MyIHeap heap = Objects.requireNonNull(programState).getHeap();
        ArrayList<Pair<Integer, Value>> heapTable = new ArrayList<>();
        for(Integer key: heap.getContent().keySet()){
            heapTable.add(new Pair<>(key, heap.getContent().get(key)));
        }
        heapTableView.setItems(FXCollections.observableArrayList(heapTable));
    }

    private void populateOutputListView(){
        ProgramState programState = getSelectedProgramState();
        List<String> output = new ArrayList<>();
        List<Value> outputList = Objects.requireNonNull(programState).getOut().getList();
        int index;
        for(index = 0; index < outputList.size(); index++){
            output.add(outputList.get(index).toString());
        }
        outputListView.setItems(FXCollections.observableArrayList(output));

    }
    private void populateFileTableListView(){
        ProgramState programState = getSelectedProgramState();
        List<String> files = new ArrayList<>(Objects.requireNonNull(programState).getFileTable().getContent().keySet());
        fileTableListView.setItems(FXCollections.observableList(files));
    }

    private void populatePrgStatesIdsListView(){
        List<ProgramState> programStates = controller.getProgramStates();
        var ids = programStates.stream().map(ps->ps.getId()).collect(Collectors.toList());
        prgStateIDListView.setItems(FXCollections.observableList(ids));
        populateNumberOfProgramStatesTextField();

    }
    private void populateSymbolTableView(){
        ProgramState programState = getSelectedProgramState();
        MyIDictionary<String, Value> symbolTable = Objects.requireNonNull(programState).getSymTable();
        ArrayList<Pair<String, Value>> symbolTableList = new ArrayList<>();
        for(Map.Entry<String, Value> entry: symbolTable.getContent().entrySet()){
            symbolTableList.add(new Pair<>(entry.getKey(), entry.getValue()));
        }
        symbolTableView.setItems(FXCollections.observableArrayList(symbolTableList));
    }

    private void populateExecutionStackListView(){
        ProgramState programState = getSelectedProgramState();
        List<String> executionStack = new ArrayList<>();
        if(programState != null)
            for(IStatement statement: programState.getExeStack().getReverse()){
                executionStack.add(statement.toString());
            }
        executionStackListView.setItems(FXCollections.observableList(executionStack));
    }

    public void populate(){
        populatePrgStatesIdsListView();
        populateHeapTableView();
        populateOutputListView();
        populateFileTableListView();
        populateSymbolTableView();
        populateExecutionStackListView();
    }
    @FXML
    private void changeProgramState(MouseEvent event){
        populateExecutionStackListView();
        populateSymbolTableView();
    }

    @FXML
    private void runOneStep(MouseEvent event){
        if(controller != null){
            try{
                List<ProgramState> programStates = Objects.requireNonNull(controller.getProgramStates());
                if(programStates.size() > 0){
                    controller.oneStep();
                    populate();
                    programStates = controller.removeCompletedPrg(controller.getProgramStates());
                    controller.setProgramStates(programStates);
                    populatePrgStatesIdsListView();
                }
                else{
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Error!");
                    alert.setHeaderText("An error occurred!");
                    alert.setContentText("There is nothing left to execute!");
                    alert.showAndWait();
                }

            }catch (InterruptedException | MyException e){
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error!");
                alert.setHeaderText("An error occurred!");
                alert.setContentText(e.getMessage());
                alert.showAndWait();
            }
        }
        else{
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error!");
            alert.setHeaderText("An error occurred!");
            alert.setContentText("No program selected!");
            alert.showAndWait();
        }
    }





}


