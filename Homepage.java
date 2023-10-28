import java.awt.*;
import java.awt.event.*;

class Homepage extends Frame implements ActionListener 
{
        TextField c;
    Homepage() {
        setSize(400, 400);
        setLayout(null);
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });

    }

    public void actionPerformed(ActionEvent e) {

    }

    public static void main(String[] args) {

    }
}