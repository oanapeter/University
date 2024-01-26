package view;

import controller.Controller;
import exception.MyException;
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
import repository.IRepository;
import repository.Repository;
import view.command.ExitCommand;
import view.command.RunExampleCommand;

public class Interpreter {
    public static void main(String[] args) {
        TextMenu menu = new TextMenu();
        menu.addCommand(new ExitCommand("0","exit"));
        IStatement ex1 = new CompStatement(new VariableDeclStatement("v", new IntType()), new CompStatement(new AssignStatement("v", new ValueExpression(new IntValue(2))), new PrintStatement(new VariableExpression("v"))));
        try{
            ex1.typeCheck(new MyDictionary<>());
            ProgramState program1 = new ProgramState(new MyStack<>(), new MyDictionary<>(), new MyList<>(), new MyDictionary<>(),new MyHeap(), ex1);
            IRepository repo1 = new Repository(program1, "log1.txt");
            Controller controller1 = new Controller(repo1);
            menu.addCommand(new RunExampleCommand("1", ex1.toString(), controller1));
        }catch (MyException e)
        {
            System.out.println(e.getMessage());
        }


        IStatement ex2 = new CompStatement(new VariableDeclStatement("a", new IntType()), new CompStatement(new VariableDeclStatement("b", new IntType()),
                new CompStatement(new AssignStatement("a", new ArithmeticExpression(new ValueExpression(new IntValue(2)), new ArithmeticExpression(new ValueExpression(new IntValue(3)),
                        new ValueExpression(new IntValue(5)), '*'), '+')), new CompStatement(new AssignStatement("b", new ArithmeticExpression(new VariableExpression("a"),
                        new ValueExpression(new IntValue(1)), '+')), new PrintStatement(new VariableExpression("b"))))));
        try{
            ex2.typeCheck(new MyDictionary<>());
            ProgramState program2 = new ProgramState(new MyStack<>(), new MyDictionary<>(), new MyList<>(), new MyDictionary<>(), new MyHeap(), ex2);
            IRepository repo2 = new Repository(program2, "log2.txt");
            Controller controller2 = new Controller(repo2);
            menu.addCommand(new RunExampleCommand("2", ex2.toString(), controller2));

        }catch (MyException e){
            System.out.println(e.getMessage());
        }


        IStatement ex3 = new CompStatement(new VariableDeclStatement("a", new BoolType()), new CompStatement(new VariableDeclStatement("v", new IntType()),
                new CompStatement(new AssignStatement("a", new ValueExpression(new BoolValue(true))), new CompStatement(new IfStatement(new VariableExpression("a"), new AssignStatement("v",
                        new ValueExpression(new IntValue(2))), new AssignStatement("v", new ValueExpression(new IntValue(3)))), new PrintStatement(new VariableExpression("v"))))));
        try{
            ex3.typeCheck(new MyDictionary<>());
            ProgramState program3 = new ProgramState(new MyStack<>(), new MyDictionary<>(), new MyList<>(), new MyDictionary<>(), new MyHeap(), ex3);
            IRepository repo3 = new Repository(program3, "log3.txt");
            Controller controller3 = new Controller(repo3);
            menu.addCommand(new RunExampleCommand("3", ex3.toString(), controller3));
        }catch (MyException e){
            System.out.println(e.getMessage());
        }


        IStatement ex4 = new CompStatement(new VariableDeclStatement("varf", new StringType()), new CompStatement(new AssignStatement("varf", new ValueExpression(new StringValue("test.in"))),
                new CompStatement(new OpenRFile(new VariableExpression("varf")),new CompStatement(new VariableDeclStatement("varc",new IntType()), new CompStatement(new ReadFile(new VariableExpression("varf"), "varc"), new CompStatement(new PrintStatement(new VariableExpression("varc")),
                        new CompStatement(new ReadFile(new VariableExpression("varf"), "varc"), new CompStatement(new PrintStatement(new VariableExpression("varc")), new CloseRFile(new VariableExpression("varf"))))))))));
        try{
            ex4.typeCheck(new MyDictionary<>());
            ProgramState program4 = new ProgramState(new MyStack<>(), new MyDictionary<>(), new MyList<>(), new MyDictionary<>(), new MyHeap(), ex4);
            IRepository repo4 = new Repository(program4, "log4.txt");
            Controller controller4 = new Controller(repo4);
            menu.addCommand(new RunExampleCommand("4", ex4.toString(), controller4));
        }catch (MyException e){
            System.out.println(e.getMessage());
        }

        IStatement ex5 = new CompStatement(new VariableDeclStatement("a", new IntType()),
                new CompStatement(new VariableDeclStatement("b", new IntType()),
                        new CompStatement(new AssignStatement("a", new ValueExpression(new IntValue(5))),
                                new CompStatement(new AssignStatement("b", new ValueExpression(new IntValue(7))),
                                        new IfStatement(new RelationalExpression(new VariableExpression("a"),
                                                new VariableExpression("b"), ">"),new PrintStatement(new VariableExpression("a")),
                                                new PrintStatement(new VariableExpression("b")))))));
        try{
            ex5.typeCheck(new MyDictionary<>());
            ProgramState prg5 = new ProgramState(new MyStack<>(), new MyDictionary<>(), new MyList<>(), new MyDictionary<>(), new MyHeap(), ex5);
            IRepository repo5 = new Repository(prg5, "log5.txt");
            Controller controller5 = new Controller(repo5);
            menu.addCommand(new RunExampleCommand("5", ex5.toString(), controller5));
        }catch (MyException e)
        {
            System.out.println(e.getMessage());
        }


        IStatement ex6 = new CompStatement(new VariableDeclStatement("v", new IntType()),
                new CompStatement(new AssignStatement("v", new ValueExpression(new IntValue(4))),
                        new CompStatement(new WhileStatement(new RelationalExpression(new VariableExpression("v"), new ValueExpression(new IntValue(0)),">"),
                                new CompStatement(new PrintStatement(new VariableExpression("v")), new AssignStatement("v",new ArithmeticExpression(new VariableExpression("v"), new ValueExpression(new IntValue(1)),'-')))),
                                new PrintStatement(new VariableExpression("v")))));
        try{
            ex6.typeCheck(new MyDictionary<>());
            ProgramState prg6 = new ProgramState(new MyStack<>(), new MyDictionary<>(), new MyList<>(), new MyDictionary<>(), new MyHeap(), ex6);
            IRepository repo6 = new Repository(prg6, "log6.txt");
            Controller controller6 = new Controller(repo6);
            menu.addCommand(new RunExampleCommand("6", ex6.toString(), controller6));
        }catch (MyException e){
            System.out.println(e.getMessage());
        }


        IStatement ex7 = new CompStatement(new VariableDeclStatement("v", new RefType(new IntType())),
                new CompStatement(new NewStatement("v", new ValueExpression(new IntValue(20))),
                        new CompStatement(new VariableDeclStatement("a", new RefType(new RefType(new IntType()))),
                                new CompStatement(new NewStatement("a", new VariableExpression("v")),
                                        new CompStatement(new PrintStatement(new VariableExpression("v")), new PrintStatement(new VariableExpression("a")))))));
        try{
            ex7.typeCheck(new MyDictionary<>());
            ProgramState prg7 = new ProgramState(new MyStack<>(), new MyDictionary<>(), new MyList<>(), new MyDictionary<>(), new MyHeap(), ex7);
            IRepository repo7 = new Repository(prg7, "log7.txt");
            Controller controller7 = new Controller(repo7);
            menu.addCommand(new RunExampleCommand("7", ex7.toString(), controller7));
        }catch (MyException e){
            System.out.println(e.getMessage());
        }


        IStatement ex8 = new CompStatement(new VariableDeclStatement("v", new RefType(new IntType())),
                new CompStatement(new NewStatement("v", new ValueExpression(new IntValue(20))),
                        new CompStatement(new VariableDeclStatement("a", new RefType(new RefType(new IntType()))),
                                new CompStatement(new NewStatement("a", new VariableExpression("v")),
                                        new CompStatement(new PrintStatement(new ReadHeapExpression(new VariableExpression("v"))),
                                                new PrintStatement(new ArithmeticExpression(new ReadHeapExpression(new ReadHeapExpression(new VariableExpression("a"))), new ValueExpression(new IntValue(5)),'+')))))));
        try{
            ex8.typeCheck(new MyDictionary<>());
            ProgramState prg8 = new ProgramState(new MyStack<>(), new MyDictionary<>(), new MyList<>(), new MyDictionary<>(), new MyHeap(), ex8);
            IRepository repo8 = new Repository(prg8, "log8.txt");
            Controller controller8 = new Controller(repo8);
            menu.addCommand(new RunExampleCommand("8", ex8.toString(), controller8));
        }catch (MyException e){
            System.out.println(e.getMessage());
        }


        IStatement ex9 = new CompStatement(new VariableDeclStatement("v", new RefType(new IntType())),
                new CompStatement(new NewStatement("v", new ValueExpression(new IntValue(20))),
                        new CompStatement( new PrintStatement(new ReadHeapExpression(new VariableExpression("v"))),
                                new CompStatement(new WriteHeapStatement("v", new ValueExpression(new IntValue(30))),
                                        new PrintStatement(new ArithmeticExpression( new ReadHeapExpression(new VariableExpression("v")), new ValueExpression(new IntValue(5)),'+'))))));
        try{
            ex9.typeCheck(new MyDictionary<>());
            ProgramState prg9 = new ProgramState(new MyStack<>(), new MyDictionary<>(), new MyList<>(), new MyDictionary<>(), new MyHeap(), ex9);
            IRepository repo9 = new Repository(prg9, "log9.txt");
            Controller controller9 = new Controller(repo9);
            menu.addCommand(new RunExampleCommand("9", ex9.toString(), controller9));
        }catch (MyException e)
        {
            System.out.println(e.getMessage());
        }


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
        try{
            ex10.typeCheck(new MyDictionary<>());
            ProgramState prg10 = new ProgramState(new MyStack<>(), new MyDictionary<>(), new MyList<>(), new MyDictionary<>(), new MyHeap(), ex10);
            IRepository repo10 = new Repository(prg10, "log10.txt");
            Controller controller10 = new Controller(repo10);
            menu.addCommand(new RunExampleCommand("10", ex10.toString(), controller10));
        }catch (MyException e ){
            System.out.println(e.getMessage());
        }


        IStatement ex11 = new CompStatement(
                new VariableDeclStatement("v", new IntType()), new CompStatement(new VariableDeclStatement("a", new RefType(new IntType())), new CompStatement(new AssignStatement("v", new ValueExpression(new IntValue(10))),
                new CompStatement(new NewStatement("a", new ValueExpression(new IntValue(22))),
                        new CompStatement(new ForkStatement(new CompStatement(new WriteHeapStatement("a", new ValueExpression(new IntValue(30))),
                                new CompStatement(new AssignStatement("v", new ValueExpression(new IntValue(32))),
                                        new CompStatement(new PrintStatement(new VariableExpression("v")), new PrintStatement(new ReadHeapExpression(new VariableExpression("a"))))))),
                                new CompStatement(new PrintStatement(new VariableExpression("v")), new PrintStatement(new ReadHeapExpression(new VariableExpression("a"))))))))
        );
        try{
            ex11.typeCheck(new MyDictionary<>());
            ProgramState prg11 = new ProgramState(new MyStack<>(), new MyDictionary<>(), new MyList<>(), new MyDictionary<>(), new MyHeap(), ex11);
            IRepository repo11 = new Repository(prg11, "log11.txt");
            Controller controller11 = new Controller(repo11);
            menu.addCommand(new RunExampleCommand("11", ex11.toString(), controller11));
        }catch (MyException e){
            System.out.println(e.getMessage());
        }

        IStatement ex12 = new CompStatement(
                new VariableDeclStatement("x", new IntType()),
                new CompStatement(
                        new AssignStatement("x", new ValueExpression(new IntValue(5))),
                        new PrintStatement(new VariableExpression("x"))));
        try{
            ex12.typeCheck(new MyDictionary<>());
            ProgramState prg12 = new ProgramState(new MyStack<>(), new MyDictionary<>(), new MyList<>(), new MyDictionary<>(), new MyHeap(), ex12);
            IRepository repo12 = new Repository(prg12, "log12.txt");
            Controller controller12 = new Controller(repo12);
            menu.addCommand(new RunExampleCommand("12", ex12.toString(), controller12));
        }catch (MyException e){
            System.out.println(e.getMessage());
        }


//        menu.addCommand(new ExitCommand("0", "Exit"));
//        menu.addCommand(new RunExampleCommand("1", ex1.toString(), controller1));
//        menu.addCommand(new RunExampleCommand("2", ex2.toString(), controller2));
//        menu.addCommand(new RunExampleCommand("3", ex3.toString(), controller3));
//        menu.addCommand(new RunExampleCommand("4", ex4.toString(), controller4));
//        menu.addCommand(new RunExampleCommand("5", ex5.toString(), controller5));
//        menu.addCommand(new RunExampleCommand("6", ex6.toString(), controller6));
//        menu.addCommand(new RunExampleCommand("7", ex7.toString(), controller7));
//        menu.addCommand(new RunExampleCommand("8", ex8.toString(), controller8));
//        menu.addCommand(new RunExampleCommand("9", ex9.toString(), controller9));
//        menu.addCommand(new RunExampleCommand("10", ex10.toString(), controller10));
//        menu.addCommand(new RunExampleCommand("11", ex11.toString(), controller11));
        menu.show();
    }
}
