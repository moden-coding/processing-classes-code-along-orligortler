import java.util.ArrayList;
import processing.core.*;

public class App extends PApplet {
    ArrayList<Bubble> bubbles;

    public static void main(String[] args) {
        PApplet.main("App");
    }

    public void setup() {
        bubbles = new ArrayList<>();
    }

    public void settings() {
        size(900, 600);
    }

    public void draw() {
        background(0);
        for (Bubble b : bubbles) {
            b.display();
            b.update();
        }
    }

    public void keyPressed() {
        if (key == ' ') {
            int x = (int) random(900);
            int y = (int) random(600);
            Bubble bubble = new Bubble(x, y, this);
            bubbles.add(bubble);
        }
    }
}
