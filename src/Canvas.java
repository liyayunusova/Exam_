import javax.swing.*;
import javax.swing.event.ChangeListener;
import java.awt.*;

class Canvas extends JComponent {
    private double xMinValue = -100;
    private double xMaxValue = 100;
    private double yMinValue = -100;
    private double yMaxValue = 100;
    SpinnerModel models[] = new SpinnerNumberModel[4];
    JSpinner spinners[] = new JSpinner[4];
    Label labels[] = new Label[4];
    String text[] = {"xMin", "xMax", "yMin", "yMax"};
    double xMin = -10, xMax = 10, yMin = -10, yMax = 10;
    /*Метод, перерисовывающий элемент внутри окна
     *при обновлении*/
    double xDen = 0;
    double yDen = 0;
    JLabel l = new JLabel();
    double tMin = -10, tMax = 10;
    double h = 0.001;
    double h2 = (tMax-tMin)/1000;

    Canvas(JFrame w){
        for(int i =0; i < 4; i++){
            models[i] = new SpinnerNumberModel(xMinValue,xMinValue,xMaxValue,0.1);
            spinners[i] = new JSpinner(models[i]);
            spinners[i].setBounds(40+(i%2)*90, 10+((i>1)?1:0)*40, 45, 30);
            labels[i] = new Label(text[i]);
            labels[i].setBounds(10+(i%2)*90,10+((i>1)?1:0)*40, 45,30);
            w.add(spinners[i]);
            w.add(labels[i]);
        }
    }

    double funcX(double t){
        return 3*t/(1+Math.pow(t,2));
    }

    double funcY(double t){
        return Math.pow(t,2)/(1+Math.pow(t,2));
    }
    int xCrt2Src(double x){
        return (int)((-xMin + x) * xDen);
//return ((x + width.toDouble() / 2) / xDen).toInt()
    }
    int yCrt2Src(double y) {
//return (-y * yDen + height.toDouble() / 2).toInt()
        return (int)(yDen * (yMax - y));
    }

    public void paintComponent(Graphics g){
        super.paintComponents(g);
        Graphics2D g2d=(Graphics2D)g;

        xDen = this.getWidth() / (xMax - xMin);
        yDen = this.getHeight() / (yMax - yMin);

        g2d.setPaint(Color.black);
        g2d.setStroke(new BasicStroke((float) 1));

        //рисуем оси
        if (yMin <= 0 && yMax >= 0) {
            g2d.drawLine(0, yCrt2Src(0.0), this.getWidth(), yCrt2Src(0.0));
        }
        else {
            g2d.drawLine(0, 0, this.getWidth(), 0);
            g2d.drawLine(0, this.getHeight(), this.getWidth(), this.getHeight());
        }

        if (xMin <= 0 && xMax >= 0) {
            g2d.drawLine(xCrt2Src(0.0), 0, xCrt2Src(0.0), this.getHeight());
        } else {
            g2d.drawLine(0, 0, 0, this.getHeight());
            g2d.drawLine(this.getWidth(), 0, this.getWidth(), this.getHeight());
        }
        double h1 = (xMax - xMin)/10000;

        //1 функция
        for (int i =0; i <= 10000; i++){
            g2d.drawLine(xCrt2Src(xMin+i*h1), yCrt2Src((xMin+i*h1)*Math.sin(1.0/(xMin+i*h1))),
                    xCrt2Src(xMin+(i+1)*h1),
                    yCrt2Src((xMin+(i+1)*h1)*Math.sin(1.0/(xMin+(i+1)*h1))));
        }
        // 2 функция
        for (int i =0; i <= 1000; i++){
            g2d.drawLine(xCrt2Src(funcX(tMin+i*h2)), yCrt2Src(funcY(tMin+i*h2)), xCrt2Src(funcX(tMin+(i+1)*h2)),
                    yCrt2Src(funcY(tMin+(i+1)*h2)));
        }
        //spinners[0].addChangeListener(new ChangeListener());
        super.repaint();
    }
}
