import java.awt.*;
import java.awt.event.*;
class Homepage extends Frame implements ActionListener {
    TextField pid,pname,qua,price,ptype,search;
    Label id,name,q,pri,type;
    Button ab, db, ub, sb, searchB,addB;
    TextArea result;

    Homepage() {
        setSize(800, 800);
        setLayout(null);
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });
        search = new TextField();
        search.setBounds(150, 50, 250, 30);
        add(search);
        searchB = new Button("Search");
        searchB.setBounds(410, 50, 80, 30);
        add(searchB);
        ab = new Button("ADD");
        db = new Button("DELETE");
        ub = new Button("UPDATE");
        sb = new Button("SHOW");
        ab.setBounds(50, 250, 100, 30);
        db.setBounds(50, 300, 100, 30);
        ub.setBounds(50, 350, 100, 30);
        sb.setBounds(50, 400, 100, 30);
        add(ab);
        add(db);
        add(ub);
        add(sb);
        ab.addActionListener(this);
        db.addActionListener(this);
        ub.addActionListener(this);
        sb.addActionListener(this);

        id=new Label("Product ID :");  pid=new TextField(5);
        name=new Label("Product Name :");  pname=new TextField(20);
        q=new Label("Quantity :");  qua=new TextField(20);
        pri=new Label("Price :");  price=new TextField(20);
        type=new Label("Product Type :");  ptype=new TextField(5);

        addB=new Button("ADD Product");  addB.setBounds(400, 400, 150, 40);

        id.setBounds(250, 150, 150,30);  pid.setBounds(410, 150, 250,30);
        name.setBounds(250, 200, 150,30);  pname.setBounds(410, 200, 150,30);
        q.setBounds(250, 250, 150,30);   qua.setBounds(410, 250, 150,30);
        pri.setBounds(250, 300, 150,30);   price.setBounds(410, 300, 150,30);
        type.setBounds(250, 350, 150,30);   ptype.setBounds(410, 350, 150,30);

        
        add(id);    add(pid);
        add(name);  add(pname);
        add(q);     add(qua);
        add(pri);   add(price);
        add(type);  add(ptype);
        add(addB);

        addB.addActionListener(this);

        setVisible(true);

    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == sb) {
            result = new TextArea(25, 10);
            result.setBounds(250, 200, 530, 350);
            add(result);
        }
        else if(e.getSource()==ab){
            
        }
    }

    public static void main(String[] args) {
        Homepage h = new Homepage();
    }
}
