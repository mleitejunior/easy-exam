package utils;

import com.opencsv.CSVWriter;
import models.Question;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;

public class CsvHandler {
    public static ArrayList<Question> readQuestions(String csvPath, String csvHeader) throws IOException {
//        int tableIndex = 0;
        ArrayList<Question> arrayQuestions = new ArrayList<>();
        BufferedReader reader = new BufferedReader(
                new InputStreamReader(
                        new FileInputStream(csvPath), StandardCharsets.UTF_8
                )
        );
        String line;

        while ((line = reader.readLine()) != null) {
            String[] dados = line.split(",");
//            if(tableIndex > 0) {
                String id = dados[0].replaceAll("\"","");
                String question = dados[1].replaceAll("\"","");
                String optionA = dados[2].replaceAll("\"","");
                String optionB = dados[3].replaceAll("\"","");
                String optionC = dados[4].replaceAll("\"","");
                String optionD = dados[5].replaceAll("\"","");
                String optionE = dados[6].replaceAll("\"","");
                String answer = dados[7].replaceAll("\"","");
                Question newQuestion = new Question(id, question, optionA, optionB, optionC, optionD, optionE, answer);
                for (int i = 0; i < dados.length; i++) {
                    System.out.println(dados[i]);
                }
                arrayQuestions.add(newQuestion);
//            }
//            tableIndex++;
        }
        return arrayQuestions;
    }

    public static void writeCsv(String csvPath, String csvHeader, ArrayList<Question> questions) throws IOException {
        CSVWriter csvWriter = new CSVWriter(new FileWriter(csvPath));
//        csvWriter.writeNext((csvHeader).split(","));
//        System.out.println(csvHeader);

        for(Question q : questions) {
            String csvLine = q.getId()+","+
                    q.getQuestion()+","+
                    q.getOptionA()+","+
                    q.getOptionB()+","+
                    q.getOptionC()+","+
                    q.getOptionD()+","+
                    q.getOptionE()+","+
                    q.getAnswer();
            csvWriter.writeNext(csvLine.split(","));
        }
        System.out.println("WROTE CSV");
        csvWriter.close();
    }
}
