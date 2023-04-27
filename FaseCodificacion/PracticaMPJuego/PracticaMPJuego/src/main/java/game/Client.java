package game;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;


public class Client extends User {
    
    private String name;
    private int id = 0;
    private int warnings = 0;
    private List<Double> scores = new ArrayList<>();
    private double globalScore = 0;
    public Client(String nick, String password, String name, int id) {
        super(nick, password);
        this.name = name;
        this.id = id;
    }



    public int getId() {
        return id;
    }

    public double getGlobalScore() {
        return globalScore;
    }

    public void receiveScore(double score) {
        scores.add(score);
        updateGlobalScore();
    }

    private void updateGlobalScore(){
        double average = 0;
        int i = 0;
        
        for (Double score: scores){
            average += score;
            i++;
        }
        
        globalScore = average / i;
    }

    @Override
    public String getClassName() {
        return "Client";
    }
}
