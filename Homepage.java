import java.awt.*;
import java.awt.event.*;

class Homepage extends Frame implements ActionListener 
{
        TextField search;
        Button ab,db,ub,sb,searchB;
        TextArea result;
    Homepage() {
        setSize(800, 800);
        setLayout(null);
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });
        search=new TextField();
        search.setBounds(150, 50, 250, 30);
        add(search);
        searchB=new Button("Search");
        searchB.setBounds(410, 50, 80, 30);
        add(searchB);
        ab=new Button("ADD");
        db=new Button("DELETE");
        ub=new Button("UPDATE");
        sb=new Button("SHOW");
        
        setVisible(true);
        
    }

    public void actionPerformed(ActionEvent e) {

    }

    public static void main(String[] args) {
    Homepage h=new Homepage();
    }
}