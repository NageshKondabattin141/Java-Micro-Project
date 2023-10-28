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
        ab.setBounds(50, 250, 100,30 );
        db.setBounds(50, 300, 100,30 );
        ub.setBounds(50, 350, 100,30 );
        sb.setBounds(50, 400, 100,30 );
        add(ab);
        add(db);
        add(ub);
        add(sb);
        ab.addActionListener(this);
        db.addActionListener(this);
        ub.addActionListener(this);
        sb.addActionListener(this);
        setVisible(true);
        
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource()==sb)
        {
            result=new TextArea(25, 10);
            result.setBounds(280, 200, 450, 250);
            add(result);
        }
    }

    public static void main(String[] args) {
    Homepage h =new Homepage();
    }
}
