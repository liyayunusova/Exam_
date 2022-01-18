import java.awt.*;
import javax.swing.*;


public class Main {
    public static void main(String[] args) {
        JFrame w = new JFrame();
        w.setSize(400, 400); //размер окна
        w.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        w.setLayout(new BorderLayout(1, 1));
        Canvas canv=new Canvas(w);
        w.add(canv);
        w.setVisible(true);

    }
}


