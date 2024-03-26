import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class QuizApp extends Application {

    private int score = 0;
    private int totalQuestions = 5;

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Cricket Quiz");

        VBox root = new VBox(10);
        root.setPadding(new Insets(10));

        Label[] questions = new Label[totalQuestions];
        ToggleGroup[] groups = new ToggleGroup[totalQuestions];
        RadioButton[][] options = new RadioButton[totalQuestions][3];

        String[] correctAnswers = {
            "Sachin Tendulkar",     
            "England",              
            "Sachin Tendulkar",     

        };

        for (int i = 0; i < totalQuestions; i++) {
            questions[i] = new Label("Question " + (i + 1) + ": " + getQuestion(i));
            groups[i] = new ToggleGroup();
            options[i][0] = new RadioButton("Option A: " + getOptionA(i));
            options[i][1] = new RadioButton("Option B: " + getOptionB(i));
            options[i][2] = new RadioButton("Option C: " + getOptionC(i));

            for (RadioButton option : options[i]) {
                option.setToggleGroup(groups[i]);
            }
        }

        Button submitButton = new Button("Submit");
        submitButton.setOnAction(e -> {
            score = 0;
            for (int i = 0; i < totalQuestions; i++) {
                if (evaluateAnswer(groups[i], i, correctAnswers[i])) {
                    score++;
                }
            }
            Label scoreLabel = new Label("Your score: " + score + "/" + totalQuestions);
            VBox scoreBox = new VBox(10); 
            scoreBox.getChildren().add(scoreLabel); 
            root.getChildren().add(scoreBox); 
            submitButton.setDisable(true); 
        });

        for (int i = 0; i < totalQuestions; i++) {
            root.getChildren().addAll(questions[i], options[i][0], options[i][1], options[i][2]);
        }
        root.getChildren().add(submitButton);

        Scene scene = new Scene(root, 400, 500);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private boolean evaluateAnswer(ToggleGroup group, int questionIndex, String correctAnswer) {
        RadioButton selected = (RadioButton) group.getSelectedToggle();
        if (selected != null && selected.getText().contains(correctAnswer)) {
            return true;
        }
        return false;
    }

    private String getQuestion(int questionIndex) {
        String[] questions = {
            "Who is known as the 'Master Blaster'?", 
            "Which country won the ICC Cricket World Cup 2019?",
            "Who is the highest run-scorer in international cricket?", 
        };
        if (questionIndex >= 0 && questionIndex < questions.length) {
            return questions[questionIndex];
        }
        return ""; 
    }

    private String getOptionA(int questionIndex) {
        String[] optionA = {
            "Sachin Tendulkar",
            "India",            
            "Sachin Tendulkar", 
        };
        if (questionIndex >= 0 && questionIndex < optionA.length) {
            return optionA[questionIndex];
        }
        return ""; 
    }

    private String getOptionB(int questionIndex) {
        String[] optionB = {
            "Virat Kohli",      
            "England",         
            "Virat Kohli",      
        };
        if (questionIndex >= 0 && questionIndex < optionB.length) {
            return optionB[questionIndex];
        }
        return ""; 
    }

    private String getOptionC(int questionIndex) {
        String[] optionC = {
            "Rahul Dravid",  
            "Australia",        
            "Brian Lara",       
        };
        if (questionIndex >= 0 && questionIndex < optionC.length) {
            return optionC[questionIndex];
        }
        return ""; 
    }}
