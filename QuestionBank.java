import java.util.List;
import java.util.ArrayList;


public class QuestionBank{

    public static List<Question> getQuestions() {

        List<Question> questions = new ArrayList<>();

        questions.add(new Question(
                "Java is developed by?",
                new String[]{"Microsoft", "Sun Microsystems", "Google", "Oracle"}, 1
        ));
        questions.add(new Question(
                "Which keyword is used to create an object?",
                new String[]{"class", "new", "create", "object"}, 1
        ));
        questions.add(new Question(
                "What is the entry point method of a Java program?",
                new String[]{"start()", "main()", "run()", "init()"}, 1
        ));
        questions.add(new Question(
                "Which data type stores true/false?",
                new String[]{"int", "boolean", "char", "float"}, 1
        ));
        questions.add(new Question(
                "Which concept allows code reuse in Java?",
                new String[]{"Polymorphism", "Inheritance", "Encapsulation", "Abstraction"}, 1
        ));
        questions.add(new Question(
                "Which interface allows sorting of objects?",
                new String[]{"Serializable", "Comparable", "Cloneable", "Runnable"}, 1
        ));
        questions.add(new Question(
                "Which collection does not allow duplicates?",
                new String[]{"List", "Set", "Map", "ArrayList"}, 1
        ));

        questions.add(new Question(
                "Which JVM memory area stores class metadata?",
                new String[]{"Heap", "Stack", "Method Area", "PC Register"}, 2
        ));
        questions.add(new Question(
                "Which design pattern ensures a single instance?",
                new String[]{"Factory", "Singleton", "Builder", "Prototype"}, 1
        ));
        questions.add(new Question(
                "Which Java 8 feature supports functional programming?",
                new String[]{"Generics", "Lambda Expressions", "Applet", "AWT"}, 1
        ));

        //System.out.println("Loaded Questions Count = " + questions.size());
        return questions;
    }
}
