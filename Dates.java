package sample;

import javafx.scene.control.Label;
import javafx.scene.paint.Color;

import java.util.Calendar;
import java.util.GregorianCalendar;

public class Dates {
    private final String[] months = {"January", "February", "March", "April", "May", "June", "July", "August", "September",
            "October", "November", "December"};
    private Label [][] labels;
    private int year, month, date;
    private Calendar calendar;

    public Dates(Label[][] labels){
        calendar = new GregorianCalendar();

        //Creates 2d array to hold the dates of the month in labels
        this.labels = new Label[6][7];
        this.labels = labels;

        //Gets the current month and year
        this.year = calendar.get(Calendar.YEAR);
        this.month = calendar.get(Calendar.MONTH);

    }

    //Returns the dates in labels for the next month
    public Label[][] nextMonth(){
        //Start at the first of the month of next month
        date = 1;
        month++;

        //Sets the month and year
        calendar.set(year, month, date);

        //Gets the dates
        return getLabels();
    }

    //Returns the dates in labels for the prior month
    public Label[][] priorMonth(){
        //Start at the first of the month of next month
        date = 1;
        month--;

        //Sets the month and year
        calendar.set(year, month, date);

        //Gets the dates
        return getLabels();
    }

    //Returns the month
    public String  getMonth(){
        return months[calendar.get(Calendar.MONTH)];
    }

    //Returns the year
    public int getYear(){
        return calendar.get(Calendar.YEAR);
    }

    //Generates the dates in the calendar
    public Label[][] getLabels(){
        //CurrMonth and CurrYear stores the month and year that is being shown, also used for comparison for color code
        int currMonth = calendar.get(Calendar.MONTH), currYear = calendar.get(Calendar.YEAR);

        //date will store the first day of the week of the first week of the shown month, can be days other than 1
        date -= calendar.get(Calendar.DAY_OF_WEEK);

        //Calculates the dates to be shown and their "color" in a 6x7 matrix
        for(int row = 0; row < 6; row++){
            for(int column = 0; column < 7; column++){
                //Updates the day of the month and creates a new label to show it
                calendar.set(year, month, ++date);
                String day = String.valueOf(calendar.get(Calendar.DATE));
                labels[row][column] = new Label(day);

                //Checks if the date generated is part of the month/year if not set the text to grey
                if(calendar.get(Calendar.MONTH) != currMonth || calendar.get(Calendar.YEAR) != currYear){
                    labels[row][column].setTextFill(Color.LIGHTGRAY);
                }
            }
        }

        //Sets the date of the calendar making sure the correct dates go with the correct month
        calendar.set(year, month, 1);

        return this.labels;
    }
}
