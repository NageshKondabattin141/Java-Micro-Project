import java.awt.*;
import java.awt.event.*;
class Homepage extends Frame implements ActionListener {
    TextField pid,pname,qua,price,ptype,search;
    Label id,name,q,pri,type;
    Button ab, db, ub, sb,hm,searchB,addB,delB,updB;
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
        result = new TextArea(25, 10);
        result.setBounds(250, 200, 530, 350);
        add(result);  result.setVisible(false);
        ab = new Button("ADD");
        db = new Button("DELETE");
        ub = new Button("UPDATE");
        sb = new Button("SHOW");
        hm = new Button("HOME");
        hm.setBounds(50, 200, 100, 30);
        ab.setBounds(50, 250, 100, 30);
        db.setBounds(50, 300, 100, 30);
        ub.setBounds(50, 350, 100, 30);
        sb.setBounds(50, 400, 100, 30);
        add(ab);
        add(db);
        add(ub);
        add(sb);
        add(hm);
        ab.addActionListener(this);
        db.addActionListener(this);
        ub.addActionListener(this);
        sb.addActionListener(this);
        hm.addActionListener(this);

        id=new Label("Product ID :");  pid=new TextField(5);
        name=new Label("Product Name :");  pname=new TextField(20);
        q=new Label("Quantity :");  qua=new TextField(20);
        pri=new Label("Price :");  price=new TextField(20);
        type=new Label("Product Type :");  ptype=new TextField(5);

        addB=new Button("ADD Product");  addB.setBounds(400, 400, 150, 40);
        delB=new Button("DELETE Product");  delB.setBounds(400, 400, 150, 40);
        updB=new Button("UPDATE Product");  updB.setBounds(400, 400, 150, 40);

        id.setBounds(250, 150, 150,30);  pid.setBounds(410, 150, 250,30);
        name.setBounds(250, 200, 150,30);  pname.setBounds(410, 200, 150,30);
        q.setBounds(250, 250, 150,30);   qua.setBounds(410, 250, 150,30);
        pri.setBounds(250, 300, 150,30);   price.setBounds(410, 300, 150,30);
        type.setBounds(250, 350, 150,30);   ptype.setBounds(410, 350, 150,30);

        
        add(id);    add(pid);           id.setVisible(false);    pid.setVisible(false);
        add(name);  add(pname);         name.setVisible(false);    pname.setVisible(false);
        add(q);     add(qua);       q.setVisible(false);    qua.setVisible(false);
        add(pri);   add(price);    pri.setVisible(false);    price.setVisible(false);
        add(type);  add(ptype);    type.setVisible(false);    ptype.setVisible(false);
        add(addB);          addB.setVisible(false);
        add(delB);          delB.setVisible(false);
        add(updB);          updB.setVisible(false);

        addB.addActionListener(this);
        setBackground(Color.cyan);
        setVisible(true);

    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == sb) {
             search.setVisible(true);
            searchB.setVisible(true);
            result.setVisible(true);
                   id.setVisible(false);    pid.setVisible(false);
                   name.setVisible(false);    pname.setVisible(false);
                   q.setVisible(false);    qua.setVisible(false);    
                  pri.setVisible(false);    price.setVisible(false);       
                  type.setVisible(false);    ptype.setVisible(false);
                  addB.setVisible(false);       delB.setVisible(false);
        }
        else if(e.getSource()==ab){
            search.setVisible(false);
            searchB.setVisible(false);
            result.setVisible(false);
                   id.setVisible(true);    pid.setVisible(true);
                   name.setVisible(true);    pname.setVisible(true);
                   q.setVisible(true);    qua.setVisible(true);    
                  pri.setVisible(true);    price.setVisible(true);       
                  type.setVisible(true);    ptype.setVisible(true);
                addB.setVisible(true);      delB.setVisible(false);
        }
         else if(e.getSource()==hm){
            search.setVisible(true);
            searchB.setVisible(true);
            result.setVisible(false);
                   id.setVisible(false);    pid.setVisible(false);
                   name.setVisible(false);    pname.setVisible(false);
                   q.setVisible(false);    qua.setVisible(false);    
                  pri.setVisible(false);    price.setVisible(false);       
                  type.setVisible(false);    ptype.setVisible(false);
                  addB.setVisible(false);  delB.setVisible(false);
        }
        else if(e.getSource()==db){
            search.setVisible(true);    
            searchB.setVisible(true);   
            result.setVisible(false);
                   id.setVisible(true);    pid.setVisible(true);  pid.setText("");
                   name.setVisible(false);    pname.setVisible(false);
                   q.setVisible(false);    qua.setVisible(false);    
                  pri.setVisible(false);    price.setVisible(false);
                  type.setVisible(false);    ptype.setVisible(false);
                  addB.setVisible(false);
                  delB.setVisible(true);
        }
    }

    public static void main(String[] args) {
        new Homepage();
    }
}
