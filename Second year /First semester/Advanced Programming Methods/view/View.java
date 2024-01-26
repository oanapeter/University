package view;//package view;
//
//import controller.Controller;
//import exception.MyException;
//import model.ADT.dictionary.MyDictionary;
//import model.ADT.dictionary.MyIDictionary;
//import model.ADT.list.MyIList;
//import model.ADT.list.MyList;
//import model.ADT.stack.MyIStack;
//import model.ADT.stack.MyStack;
//import model.expression.ArithmeticExpression;
//import model.expression.ValueExpression;
//import model.expression.VariableExpression;
//import model.programState.ProgramState;
//import model.statement.*;
//import model.type.BoolType;
//import model.type.IntType;
//import model.value.BoolValue;
//import model.value.IntValue;
//import model.value.Value;
//import repository.IRepository;
//import repository.Repository;
//
//import javax.management.ValueExp;
//import java.io.BufferedReader;
//import java.io.IOException;
//import java.util.Scanner;
//
//public class View {
//    public void showMenu() {
//        System.out.println("MENU:");
//        System.out.println("0.Exit");
//        System.out.println("1.Run the first program: int v; v=2; Print(v);");
//        System.out.println("2.Run the second program: int a; int b; a=2+3*5; b=a+1; Print(b);");
//        System.out.println("3.Run the third program: bool a; int v; a=true; (If a Then v=2 Else v=3); Print(v);");
//        System.out.println("Choose an option: ");
//    }
//
//    public void runProgram1() throws MyException, IOException {
//        IStatement ex1 = new CompStatement(new VariableDeclStatement("v", new IntType()), new CompStatement(new AssignStatement("v", new ValueExpression(new IntValue(2))), new PrintStatement(new VariableExpression("v"))));
//        runProgram(ex1);
//    }
//
//    public void runProgram2() throws MyException, IOException {
//        IStatement ex2 = new CompStatement(new VariableDeclStatement("a", new IntType()), new CompStatement(new VariableDeclStatement("b", new IntType()),
//                new CompStatement(new AssignStatement("a", new ArithmeticExpression(new ValueExpression(new IntValue(2)), new ArithmeticExpression(new ValueExpression(new IntValue(3)),
//                        new ValueExpression(new IntValue(5)), '*'), '+')), new CompStatement(new AssignStatement("b", new ArithmeticExpression(new VariableExpression("a"),
//                        new ValueExpression(new IntValue(1)), '+')), new PrintStatement(new VariableExpression("b"))))));
//        runProgram(ex2);
//    }
//
//    public void runProgram3() throws MyException, IOException {
//        IStatement ex3 = new CompStatement(new VariableDeclStatement("a", new BoolType()), new CompStatement(new VariableDeclStatement("v", new IntType()),
//                new CompStatement(new AssignStatement("a", new ValueExpression(new BoolValue(true))), new CompStatement(new IfStatement(new VariableExpression("a"), new AssignStatement("v",
//                        new ValueExpression(new IntValue(2))), new AssignStatement("v", new ValueExpression(new IntValue(3)))), new PrintStatement(new VariableExpression("v"))))));
//        runProgram(ex3);
//    }
//
//    public void runProgram(IStatement statement) throws MyException, IOException {
//        MyIStack<IStatement> exeStack = new MyStack<>();
//        MyIDictionary<String, Value> symTable = new MyDictionary<>();
//        MyIList<Value> output = new MyList<>();
//        MyIDictionary<String, BufferedReader> fileTable = new MyDictionary<>();
//        ProgramState program = new ProgramState(exeStack, symTable, output, fileTable, statement);
//        IRepository repo = new Repository(program, "log.txt");
//        Controller controller = new Controller(repo);
//        controller.allSteps();
//
//        System.out.println("Result is: " + program.getOut().toString().replace('[', ' ').replace(']', ' '));
//
//    }
//
//    public void start() {
//        boolean start = true;
//        while (start) {
//            try {
//                showMenu();
//                Scanner reader = new Scanner(System.in);
//                int option = reader.nextInt();
//                if (option == 1) {
//                    runProgram1();
//                } else if (option == 2) {
//                    runProgram2();
//                } else if (option == 3) {
//                    runProgram3();
//                    ;
//                } else if (option == 0) {
//                    start = false;
//                } else {
//                    System.out.println("Invalid input!");
//                }
//            } catch (Exception exception) {
//                System.out.println(exception.getMessage());
//            }
//        }
//    }
//}
