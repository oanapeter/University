package com.example.a7;

import com.example.a7.Program;
import controller.Controller;
import exception.MyException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.Alert;
import model.ADT.dictionary.MyDictionary;
import model.ADT.heap.MyHeap;
import model.ADT.list.MyList;
import model.ADT.stack.MyStack;
import model.expression.*;
import model.programState.ProgramState;
import model.statement.*;
import model.type.BoolType;
import model.type.IntType;
import model.type.RefType;
import model.type.StringType;
import model.value.BoolValue;
import model.value.IntValue;
import model.value.StringValue;
import javafx.fxml.FXML;
import repository.IRepository;
import repository.Repository;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ProgramsList {

    private Program program;
    public void setProgramsList(Program program){
        this.program = program;
    }

    @FXML
    private ListView<IStatement> statements;
    private Button selectButton;
    @FXML
    public void initialize(){
        statements.setItems(getAllStatements());
        statements.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
    }
    public void setProgramController(Program program){
        this.program = program;
    }

    private ObservableList<IStatement> getAllStatements(){
        List<IStatement> allStatements = new ArrayList<>();

        IStatement ex1 = new CompStatement(new VariableDeclStatement("v", new IntType()), new CompStatement(new AssignStatement("v", new ValueExpression(new IntValue(2))), new PrintStatement(new VariableExpression("v"))));
        allStatements.add(ex1);

        IStatement ex2 = new CompStatement(new VariableDeclStatement("a", new IntType()), new CompStatement(new VariableDeclStatement("b", new IntType()),
                new CompStatement(new AssignStatement("a", new ArithmeticExpression(new ValueExpression(new IntValue(2)), new ArithmeticExpression(new ValueExpression(new IntValue(3)),
                        new ValueExpression(new IntValue(5)), '*'), '+')), new CompStatement(new AssignStatement("b", new ArithmeticExpression(new VariableExpression("a"),
                        new ValueExpression(new IntValue(1)), '+')), new PrintStatement(new VariableExpression("b"))))));
        allStatements.add(ex2);

        IStatement ex3 = new CompStatement(new VariableDeclStatement("a", new BoolType()), new CompStatement(new VariableDeclStatement("v", new IntType()),
                new CompStatement(new AssignStatement("a", new ValueExpression(new BoolValue(true))), new CompStatement(new IfStatement(new VariableExpression("a"), new AssignStatement("v",
                        new ValueExpression(new IntValue(2))), new AssignStatement("v", new ValueExpression(new IntValue(3)))), new PrintStatement(new VariableExpression("v"))))));
        allStatements.add(ex3);

        IStatement ex4 = new CompStatement(new VariableDeclStatement("varf", new StringType()), new CompStatement(new AssignStatement("varf", new ValueExpression(new StringValue("test.in"))),
                new CompStatement(new OpenRFile(new VariableExpression("varf")),new CompStatement(new VariableDeclStatement("varc",new IntType()), new CompStatement(new ReadFile(new VariableExpression("varf"), "varc"), new CompStatement(new PrintStatement(new VariableExpression("varc")),
                        new CompStatement(new ReadFile(new VariableExpression("varf"), "varc"), new CompStatement(new PrintStatement(new VariableExpression("varc")), new CloseRFile(new VariableExpression("varf"))))))))));
        allStatements.add(ex4);

        IStatement ex5 = new CompStatement(new VariableDeclStatement("a", new IntType()),
                new CompStatement(new VariableDeclStatement("b", new IntType()),
                        new CompStatement(new AssignStatement("a", new ValueExpression(new IntValue(5))),
                                new CompStatement(new AssignStatement("b", new ValueExpression(new IntValue(7))),
                                        new IfStatement(new RelationalExpression(new VariableExpression("a"),
                                                new VariableExpression("b"), ">"),new PrintStatement(new VariableExpression("a")),
                                                new PrintStatement(new VariableExpression("b")))))));
        allStatements.add(ex5);

        IStatement ex6 = new CompStatement(new VariableDeclStatement("v", new IntType()),
                new CompStatement(new AssignStatement("v", new ValueExpression(new IntValue(4))),
                        new CompStatement(new WhileStatement(new RelationalExpression(new VariableExpression("v"), new ValueExpression(new IntValue(0)),">"),
                                new CompStatement(new PrintStatement(new VariableExpression("v")), new AssignStatement("v",new ArithmeticExpression(new VariableExpression("v"), new ValueExpression(new IntValue(1)),'-')))),
                                new PrintStatement(new VariableExpression("v")))));
        allStatements.add(ex6);

        IStatement ex7 = new CompStatement(new VariableDeclStatement("v", new RefType(new IntType())),
                new CompStatement(new NewStatement("v", new ValueExpression(new IntValue(20))),
                        new CompStatement(new VariableDeclStatement("a", new RefType(new RefType(new IntType()))),
                                new CompStatement(new NewStatement("a", new VariableExpression("v")),
                                        new CompStatement(new PrintStatement(new VariableExpression("v")), new PrintStatement(new VariableExpression("a")))))));
        allStatements.add(ex7);

        IStatement ex8 = new CompStatement(new VariableDeclStatement("v", new RefType(new IntType())),
                new CompStatement(new NewStatement("v", new ValueExpression(new IntValue(20))),
                        new CompStatement(new VariableDeclStatement("a", new RefType(new RefType(new IntType()))),
                                new CompStatement(new NewStatement("a", new VariableExpression("v")),
                                        new CompStatement(new PrintStatement(new ReadHeapExpression(new VariableExpression("v"))),
                                                new PrintStatement(new ArithmeticExpression(new ReadHeapExpression(new ReadHeapExpression(new VariableExpression("a"))), new ValueExpression(new IntValue(5)),'+')))))));
        allStatements.add(ex8);

        IStatement ex9 = new CompStatement(new VariableDeclStatement("v", new RefType(new IntType())),
                new CompStatement(new NewStatement("v", new ValueExpression(new IntValue(20))),
                        new CompStatement( new PrintStatement(new ReadHeapExpression(new VariableExpression("v"))),
                                new CompStatement(new WriteHeapStatement("v", new ValueExpression(new IntValue(30))),
                                        new PrintStatement(new ArithmeticExpression( new ReadHeapExpression(new VariableExpression("v")), new ValueExpression(new IntValue(5)),'+'))))));
        allStatements.add(ex9);

        IStatement ex10 = new CompStatement(
                new VariableDeclStatement("v", new RefType(new IntType())),
                new CompStatement(
                        new NewStatement("v", new ValueExpression(new IntValue(20))),
                        new CompStatement(
                                new VariableDeclStatement("a", new RefType(new RefType(new IntType()))),
                                new CompStatement(
                                        new NewStatement("a", new VariableExpression("v")),
                                        new CompStatement(
                                                new NewStatement("v", new ValueExpression(new IntValue(30))),
                                                new PrintStatement(new ReadHeapExpression(new ReadHeapExpression(new VariableExpression("a"))))
                                        )

                                )
                        )
                )
        );
        allStatements.add(ex10);

        IStatement ex11 = new CompStatement(
                new VariableDeclStatement("v", new IntType()), new CompStatement(new VariableDeclStatement("a", new RefType(new IntType())), new CompStatement(new AssignStatement("v", new ValueExpression(new IntValue(10))),
                new CompStatement(new NewStatement("a", new ValueExpression(new IntValue(22))),
                        new CompStatement(new ForkStatement(new CompStatement(new WriteHeapStatement("a", new ValueExpression(new IntValue(30))),
                                new CompStatement(new AssignStatement("v", new ValueExpression(new IntValue(32))),
                                        new CompStatement(new PrintStatement(new VariableExpression("v")), new PrintStatement(new ReadHeapExpression(new VariableExpression("a"))))))),
                                new CompStatement(new PrintStatement(new VariableExpression("v")), new PrintStatement(new ReadHeapExpression(new VariableExpression("a")))))))));
        allStatements.add(ex11);

        IStatement ex12 = new CompStatement(
                new VariableDeclStatement("x", new BoolType()),
                new CompStatement(
                        new AssignStatement("x", new ValueExpression(new IntValue(5))),
                        new PrintStatement(new VariableExpression("x"))));
        allStatements.add(ex12);

        return FXCollections.observableArrayList(allStatements);



    }
    @FXML
    private void displayProgram(ActionEvent event){
        IStatement selectedStatement = statements.getSelectionModel().getSelectedItem();
        if(selectedStatement==null){
            Alert alert =  new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error!");
            alert.setContentText("No program selected!");
            alert.showAndWait();

        }
        else{
            int id = statements.getSelectionModel().getSelectedIndex();
            try{
                selectedStatement.typeCheck(new MyDictionary<>());
                ProgramState programState = new ProgramState(new MyStack<>(), new MyDictionary<>(), new MyList<>(), new MyDictionary<>(), new MyHeap(), selectedStatement);
                IRepository repository = new Repository(programState, "log" + (id+1) + ".txt");
                Controller controller = new Controller(repository);
                program.setController(controller);
            }catch (MyException e){
                Alert alert =  new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error!");
                alert.setContentText(e.getMessage());
                alert.showAndWait();
            }
        }
    }
}
