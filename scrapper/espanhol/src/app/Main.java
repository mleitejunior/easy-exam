package app;

import models.Question;

import javax.swing.*;
import java.io.IOException;
import java.util.ArrayList;
import java.io.File;

public class Main {
    public static void main(String[] args) {
        ArrayList<Question> databaseQuestions = new ArrayList<>();
        Scrapper scrapper;
        String csvPath;
        String csvHeader = "Id,Question,OptionA,OptionB,OptionC,OptionD,OptionE,Answer\n";
        int questionQuantity = 0;

        // Find CSV file
        JFileChooser chooser = new JFileChooser("/home/mleitejunior/Documents/Dev/ProvaFacil/test");
        chooser.showOpenDialog(null);
        File f = chooser.getSelectedFile();

        // Read questions from CSV
        if (f == null) {
            return;
        } else if (f.getAbsoluteFile().toString().contains(".csv")){
            try {
                csvPath = f.getCanonicalPath();
                databaseQuestions = utils.CsvHandler.readQuestions(csvPath, csvHeader);
                questionQuantity = databaseQuestions.size();
                System.out.println("Database loaded, " + questionQuantity + " questions");

                // Scrapping new Questions
                scrapper = new Scrapper(databaseQuestions, csvPath, csvHeader);
                scrapper.scrapSoEspanhol();


            } catch (IOException ex) {
                System.out.println("Error, incorrect CSV path: " + ex.getMessage());
            }
        } else {
            System.out.println("Error, incorrect CSV file");
        }

        int scrappedQuestions = databaseQuestions.size() - questionQuantity;

        System.out.println("Scrapping complete, " + scrappedQuestions + " new questions");


        System.out.println("Program finished");
    }
}
