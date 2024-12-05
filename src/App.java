import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Scanner;

import processing.core.*;

public class App extends PApplet {
    ArrayList<Bubble> bubbles;
    double timer;
    int scene;
    double highScore;
    double gameStart;

    public static void main(String[] args) {
        PApplet.main("App");
    }

    public void setup() {
        readHighScore();
        bubbles = new ArrayList<>();
        for (int i = 0; i < 4; i++) {
            bubbleMaker();
        }
        scene = 0;
        gameStart = millis();
    }

    public void settings() {
        size(900, 600);
    }

    public void draw() {
        background(0);
        if (scene == 0) {
            for (Bubble b : bubbles) {
                b.display();
                b.update();
            }
            fill(225);
            textSize(50);
            timer = millis() - gameStart;
            timer = ((int) timer / 100) / 10.0;
            text("" + timer, width - 100, 50);
            if (bubbles.size() == 0) {
                scene = 1;
                if (highScore < timer) {
                    highScore = timer;
                }
            }
        } else {
            text("score: " + timer, 400, 100);
            text("highscore: " + highScore, 400, 250);
        }

    }

    public void readHighScore() {
        try (Scanner scanner = new Scanner(Paths.get("highscore.txt"))) {

            while (scanner.hasNextLine()) {
                String row = scanner.nextLine();
                highScore = Double.valueOf(row);
            }
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }

    }

    public void bubbleMaker() {
        int x = (int) random(900);
        int y = (int) random(600);
        Bubble bubble = new Bubble(x, y, this);
        bubbles.add(bubble);
    }

    public void keyPressed() {
        if (key == ' ') {
            if (scene == 0) {
                bubbles.clear();
            } else {
                setup();
            }

        }
    }

    public void mousePressed() {
        for (int i = 0; i < bubbles.size(); i++) {
            Bubble b = bubbles.get(i);
            if (b.checkTouch(mouseX, mouseY) == false) {
                bubbles.remove(b);
            }

        }
    }
}
