import java.awt.*;
import java.awt.event.*;

class Homepage extends Frame implements ActionListener 
{
        TextField search;
        Button ab,db,ub,sb,searchB;
        TextArea result;
    Homepage() {
        setSize(400, 400);
        setLayout(null);
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });
        search=new TextField(10);
        search.setLocation(50, 50);
        add(search);
        
    }

    public void actionPerformed(ActionEvent e) {

    }

    public static void main(String[] args) {
    Homepage h=new Homepage();
    }
}