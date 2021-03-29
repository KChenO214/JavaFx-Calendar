package sample;

import javafx.application.Application;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import javafx.scene.control.Label;


public class Main extends Application {
    private final String[] weekDates = {"", "Sunday", "Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday"};

    @Override
    public void start(Stage primaryStage) throws Exception{
        primaryStage.setTitle("Calendar");

        //Grid to display the calender
        GridPane grid = new GridPane();
        grid.setPrefSize(500, 300);
        grid.setAlignment(Pos.CENTER);
        grid.setVgap(10);

        //Keeps the column the same size in the gridpane
        for (int i = 0; i < 7; i++) {
            ColumnConstraints column = new ColumnConstraints(75);
            grid.getColumnConstraints().add(column);
        }

        //Creates an hbox to position the two buttons
        HBox hbox = new HBox(6);

        //BorderPane that holds both the gridpane and the hbox
        BorderPane border = new BorderPane();
        border.setCenter(grid);
        border.setBottom(hbox);

        //Stores the labels that displays the calender dates of the month
        Label[][] labels = new Label[6][7];
        //Calculates the dates
        Dates dates = new Dates(labels);

        //Calendar label
        Label monthYear = new Label(dates.getMonth() + ", " + dates.getYear());

        //Prior and next buttons that allow us to traverse the months
        Button next = new Button("Next");
        next.setOnAction(nextYear -> {
            //Clears the grid, gets the next month dates, and prints it out
            clear(grid);
            dates.nextMonth();
            printDates(grid, labels);
            monthYear.setText(dates.getMonth() + ", " + dates.getYear());
        });

        Button prior = new Button("Prior");
        prior.setOnAction(priorYear -> {
            //Clears the grid, gets the prior month dates, and prints it out
            clear(grid);
            dates.priorMonth();
            printDates(grid, labels);
            monthYear.setText(dates.getMonth() + ", " + dates.getYear());
        });

        //Set the two buttons onto the hbox
        hbox.setAlignment(Pos.CENTER);
        hbox.getChildren().addAll(prior, next);
        BorderPane.setMargin(hbox, new Insets(10, 10, 10, 10));

        //Sets the title on the borderpane
        border.setTop(monthYear);
        BorderPane.setAlignment(monthYear, Pos.CENTER);
        BorderPane.setMargin(monthYear, new Insets(10, 10, 10, 10));

        primaryStage.setScene(new Scene(border, 600, 350));

        //Clears the grid, gets the dates, and prints it out
        clear(grid);
        dates.getLabels();
        printDates(grid, labels);

        primaryStage.show();
    }

    private void printDates(GridPane grid, Label[][] labels){
        //Prints out the dates retrieved by the dates class
        for(int row = 1; row < 7; row++){
            for(int column = 0; column < 7; column++){
                GridPane.setHalignment(labels[row - 1][column], HPos.CENTER);
                grid.add(labels[row - 1][column], column, row);
            }
        }
    }

    private void clear(GridPane grid){
        //Rids of all labels on the grid
        grid.getChildren().clear();

        //Prints out the week days
        for(int i = 0; i < 7; i++){
            Label date = new Label(weekDates[i + 1]);
            GridPane.setHalignment(date, HPos.CENTER);
            grid.add(date, i, 0);
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}
