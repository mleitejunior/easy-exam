package app;

import models.Question;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class Scrapper {
    ArrayList<Question> databaseQuestions;
    String csvPath;
    String csvHeader;

    public Scrapper(ArrayList<Question> databaseQuestions, String csvPath, String csvHeader) {
        this.databaseQuestions = databaseQuestions;
        this.csvPath = csvPath;
        this.csvHeader = csvHeader;
    }

    public void scrapSoEspanhol () {
        System.setProperty("webdriver.chrome.driver","dependencies/chromedriver");
        WebDriver driver = new ChromeDriver();
//        WebDriverWait wait = new WebDriverWait(driver, 20);
        driver.manage().timeouts().pageLoadTimeout(20, TimeUnit.SECONDS);
        driver.manage().timeouts().implicitlyWait(120, TimeUnit.SECONDS);
        driver.get("https://www.soespanhol.com.br/simulados.php");
        try { Thread.sleep(4000); } catch (InterruptedException ignored) {  }


        for(int i = 0; i < 10000; i++){
            try{
                driver.navigate().to("https://www.soespanhol.com.br/simulados.php");
                try { Thread.sleep(4000); } catch (InterruptedException ignored) {  }

                driver.findElement(By.name("c-3")).click();
                driver.findElement(By.name("c-6")).click();
                driver.findElement(By.name("c-5")).click();
                driver.findElement(By.name("c-2")).click();

                WebElement clickEnvia = driver.findElement(By.name("envia"));
                clickEnvia.sendKeys(""); clickEnvia.click();
                try { Thread.sleep(5000); } catch (InterruptedException ignored) {  }

                // Instance of four Questions
                Question q1 = new Question();
                Question q2 = new Question();
                Question q3 = new Question();
                Question q4 = new Question();

                List<WebElement> questions = driver.findElements(By.xpath("//p[@class='vermelho2']"));
                q1.setQuestion(questions.get(0).getText());
                q2.setQuestion(questions.get(1).getText());
                q3.setQuestion(questions.get(2).getText());
                q4.setQuestion(questions.get(3).getText());

                String answersQ1 = driver.findElement(By.xpath("//form[@action='resultado.php']/p[2]")).getText();
                String[] linesQ1 = answersQ1.split("\\r?\\n");
                q1.setOptionA(linesQ1[0]);
                q1.setOptionB(linesQ1[1]);
                q1.setOptionC(linesQ1[2]);
                q1.setOptionD(linesQ1[3]);
                q1.setOptionE(linesQ1[4]);

                String answersQ2 = driver.findElement(By.xpath("//form[@action='resultado.php']/p[4]")).getText();
                String[] linesQ2 = answersQ2.split("\\r?\\n");
                q2.setOptionA(linesQ2[0]);
                q2.setOptionB(linesQ2[1]);
                q2.setOptionC(linesQ2[2]);
                q2.setOptionD(linesQ2[3]);
                q2.setOptionE(linesQ2[4]);

                String answersQ3 = driver.findElement(By.xpath("//form[@action='resultado.php']/p[6]")).getText();
                String[] linesQ3 = answersQ3.split("\\r?\\n");
                q3.setOptionA(linesQ3[0]);
                q3.setOptionB(linesQ3[1]);
                q3.setOptionC(linesQ3[2]);
                q3.setOptionD(linesQ3[3]);
                q3.setOptionE(linesQ3[4]);

                String answersQ4 = driver.findElement(By.xpath("//form[@action='resultado.php']/p[8]")).getText();
                String[] linesQ4 = answersQ4.split("\\r?\\n");
                q4.setOptionA(linesQ4[0]);
                q4.setOptionB(linesQ4[1]);
                q4.setOptionC(linesQ4[2]);
                q4.setOptionD(linesQ4[3]);
                q4.setOptionE(linesQ4[3]);

                WebElement clickCalcule = driver.findElement(By.xpath("//input[@value=' Calcule meu desempenho ']"));
                clickCalcule.sendKeys(""); clickCalcule.click();
                try { Thread.sleep(4000); } catch (InterruptedException ignored) {  }

                String rightAnswerQ1 = driver.findElement(
                        By.xpath("//div[@class='caixa']/p[3]"))
                        .getAttribute("innerHTML")
                        .replaceAll("\n", "")
                        .replaceAll("<img(\\s).*", "")
                        .replaceAll(".*<br>", "")
                        .trim();

                q1.setAnswer(rightAnswerQ1);

                String rightAnswerQ2 = driver.findElement(
                        By.xpath("//div[@class='caixa']/p[5]"))
                        .getAttribute("innerHTML")
                        .replaceAll("\n", "")
                        .replaceAll("<img(\\s).*", "")
                        .replaceAll(".*<br>", "")
                        .trim();

                q2.setAnswer(rightAnswerQ2);

                String rightAnswerQ3 = driver.findElement(
                        By.xpath("//div[@class='caixa']/p[7]"))
                        .getAttribute("innerHTML")
                        .replaceAll("\n", "")
                        .replaceAll("<img(\\s).*", "")
                        .replaceAll(".*<br>", "")
                        .trim();

                q3.setAnswer(rightAnswerQ3);

                String rightAnswerQ4 = driver.findElement(
                        By.xpath("//div[@class='caixa']/p[9]"))
                        .getAttribute("innerHTML")
                        .replaceAll("\n", "")
                        .replaceAll("<img(\\s).*", "")
                        .replaceAll(".*<br>", "")
                        .trim();

                q4.setAnswer(rightAnswerQ4);

                // Saving questions
                q1.setId(Integer.toString(i));
                q2.setId(Integer.toString(i));
                q3.setId(Integer.toString(i));
                q4.setId(Integer.toString(i));
                databaseQuestions.add(q1);
                databaseQuestions.add(q2);
                databaseQuestions.add(q3);
                databaseQuestions.add(q4);

                // Write current questions database in new Csv File
                try {
                    String novoCsvPath = csvPath.replace(".csv", "_scrapped.csv");
                    utils.CsvHandler.writeCsv(novoCsvPath, csvHeader, databaseQuestions);
                } catch (IOException e) {
                    System.out.println("Error writing new CSV file: " + e.getMessage());;
                }
                try { Thread.sleep(3000); } catch (InterruptedException ignored) {  }

                WebElement clickNovoSimulado = driver.findElement(By.xpath("//*[text()='[Novo simulado]']"));
                clickNovoSimulado.sendKeys(""); clickNovoSimulado.click();
            } catch (Exception ignored){
                System.out.println("Error, keep going...");
            }
        }

        // Change navigations:
        // driver.navigate().to("url");

        // driver.close();
        driver.quit();
    }
}
