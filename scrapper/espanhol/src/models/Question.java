package models;

public class Question {
    private String id;
    private String question;
    private String optionA;
    private String optionB;
    private String optionC;
    private String optionD;
    private String optionE;
    private String answer;

    public Question(String id, String question, String optionA, String optionB, String optionC, String optionD, String optionE, String answer) {
        this.id = id;
        this.question = question;
        this.optionA = optionA;
        this.optionB = optionB;
        this.optionC = optionC;
        this.optionD = optionD;
        this.optionE = optionE;
        this.answer = answer;
    }

    public Question() {
        this.id = "";
        this.question = "";
        this.optionA = "";
        this.optionB = "";
        this.optionC = "";
        this.optionD = "";
        this.optionE = "";
        this.answer = "";
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question.replaceAll(",", "‚");
    }

    public String getOptionA() {
        return optionA;
    }

    public void setOptionA(String optionA) {
        this.optionA = optionA.replaceAll(",", "‚");
    }

    public String getOptionB() {
        return optionB;
    }

    public void setOptionB(String optionB) {
        this.optionB = optionB.replaceAll(",", "‚");
    }

    public String getOptionC() {
        return optionC;
    }

    public void setOptionC(String optionC) {
        this.optionC = optionC.replaceAll(",", "‚");
    }

    public String getOptionD() {
        return optionD;
    }

    public void setOptionD(String optionD) {
        this.optionD = optionD.replaceAll(",", "‚");
    }

    public String getOptionE() {
        return optionE;
    }

    public void setOptionE(String optionE) {
        this.optionE = optionE.replaceAll(",", "‚");
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        answer = answer.replaceAll(",", "‚");
        if (answer.equals(this.optionA)) {
            this.answer = "A";
        } else if (answer.equals(this.optionB)) {
            this.answer = "B";
        } else if (answer.equals(this.optionC)) {
            this.answer = "C";
        } else if (answer.equals(this.optionD)) {
            this.answer = "D";
        } else if (answer.equals(this.optionE)) {
            this.answer = "E";
        } else {
            this.answer = answer;
        }
    }
}
