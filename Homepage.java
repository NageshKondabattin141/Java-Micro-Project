import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import javax.swing.*;
import javax.swing.table.*;

class Homepage extends JFrame implements ActionListener, FocusListener {
    JTextField pid, pname, qua, price, ptype, search;
    JLabel id, name, q, pri, type, ans;
    JButton ab, db, ub, sb, hm, searchB, addB, delB, updB;
    JTable database;
   // String colheads[] = { "PID", "PNAME", "Quantity", "PRICE", "PTYPE" };
   // Object row[][] = { { 01, "Dolo", 200, 05, "Tablet" }, { 02, "Dry_Cough", 250, 150, "Syrup" } };
    Container c;
    static PreparedStatement psa;
    static PreparedStatement psd;
    static PreparedStatement psu;
    static PreparedStatement pss;
    static Connection con;
     ResultSet res, res1;
    static DefaultTableModel dtm;
    JScrollPane js;

    Homepage() {
        setSize(800, 800);
        setLocation(500,180);
        setLayout(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        c = getContentPane();
        search = new JTextField();
        search.setBounds(150, 50, 250, 30);
        c.add(search);
        searchB = new JButton("Search");
        searchB.setBounds(410, 50, 80, 30);
        c.add(searchB);
        ans = new JLabel("");
        ans.setBounds(240, 300, 200, 30);
        c.add(ans);
        ab = new JButton("ADD");
        db = new JButton("DELETE");
        ub = new JButton("UPDATE");
        sb = new JButton("SHOW");
        hm = new JButton("HOME");
        hm.setBounds(50, 200, 100, 30);
        ab.setBounds(50, 250, 100, 30);
        db.setBounds(50, 300, 100, 30);
        ub.setBounds(50, 350, 100, 30);
        sb.setBounds(50, 400, 100, 30);
        c.add(ab);
        c.add(db);
        c.add(ub);
        c.add(sb);
        c.add(hm);
        ab.addActionListener(this);
        db.addActionListener(this);
        ub.addActionListener(this);
        sb.addActionListener(this);
        hm.addActionListener(this);

        ab.addFocusListener(this);
        db.addFocusListener(this);
        ub.addFocusListener(this);
        sb.addFocusListener(this);
        hm.addFocusListener(this);

        id = new JLabel("Product ID :");
        pid = new JTextField(5);
        name = new JLabel("Product Name :");
        pname = new JTextField(40);
        q = new JLabel("Quantity :");
        qua = new JTextField(40);
        pri = new JLabel("Price :");
        price = new JTextField(40);
        type = new JLabel("Product Type :");
        ptype = new JTextField(40);

        addB = new JButton("ADD Product");
        addB.setBounds(400, 400, 150, 40);
        delB = new JButton("DELETE Product");
        delB.setBounds(400, 400, 150, 40);
        updB = new JButton("UPDATE Product");
        updB.setBounds(400, 400, 150, 40);

        id.setBounds(250, 150, 150, 30);
        pid.setBounds(410, 150, 250, 30);
        name.setBounds(250, 200, 150, 30);
        pname.setBounds(410, 200, 150, 30);
        q.setBounds(250, 250, 150, 30);
        qua.setBounds(410, 250, 150, 30);
        pri.setBounds(250, 300, 150, 30);
        price.setBounds(410, 300, 150, 30);
        type.setBounds(250, 350, 150, 30);
        ptype.setBounds(410, 350, 150, 30);

        c.add(id);
        c.add(pid);
        id.setVisible(false);
        pid.setVisible(false);
        c.add(name);
        c.add(pname);
        name.setVisible(false);
        pname.setVisible(false);
        c.add(q);
        c.add(qua);
        q.setVisible(false);
        qua.setVisible(false);
        c.add(pri);
        c.add(price);
        pri.setVisible(false);
        price.setVisible(false);
        c.add(type);
        c.add(ptype);
        type.setVisible(false);
        ptype.setVisible(false);
        c.add(addB);
        addB.setVisible(false);
        c.add(delB);
        delB.setVisible(false);
        c.add(updB);
        updB.setVisible(false);
        addB.addActionListener(this);
        updB.addActionListener(this);
        delB.addActionListener(this);
        searchB.addActionListener(this);

        addB.addFocusListener(this);
        updB.addFocusListener(this);
        delB.addFocusListener(this);
        searchB.addFocusListener(this);
        pid.addFocusListener(this);
        pname.addFocusListener(this);
        qua.addFocusListener(this);
        price.addFocusListener(this);
        ptype.addFocusListener(this);

        dtm=new DefaultTableModel();
        database = new JTable(dtm) {
            public boolean isCellEditable(int row, int colheads) {
                return false;
            }
        };
        database.setBounds(250, 200, 500, 250);
        js = new JScrollPane(database);
        js.setBounds(250, 200, 500, 250);
        c.add(js);
        database.setVisible(false);
        js.setVisible(false);
        JTableHeader th = database.getTableHeader();
        database.setBackground(Color.yellow);
        th.setBackground(Color.cyan);
        database.setRowSelectionAllowed(true);
        database.setColumnSelectionAllowed(true);
        database.setSelectionBackground(Color.ORANGE);
        dtm.addColumn("ID");
        dtm.addColumn("NAME");
        dtm.addColumn("TYPE");
        dtm.addColumn("Quantity");
        dtm.addColumn("PRICE");
        

        ab.setBackground(Color.lightGray);
        ub.setBackground(Color.lightGray);
        db.setBackground(Color.lightGray);
        sb.setBackground(Color.lightGray);
        hm.setBackground(Color.green);
        addB.setBackground(Color.lightGray);
        updB.setBackground(Color.lightGray);
        delB.setBackground(Color.lightGray);
        searchB.setBackground(Color.lightGray);
        // setBackground(Color.magenta);
        setVisible(true);

        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
            System.out.println("Driver Loaded");
            Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "system", "manager");
            System.out.println("Connected");
            psa = con.prepareStatement("insert into Medical values(?,?,?,?,?)");
            psu = con.prepareStatement("update Medical set qty=? where id=?");
            psd = con.prepareStatement("delete from Medical where id=?");

        } catch (Exception e) {
            System.out.println("Error Occurs" + e);
        }
    }

    public void focusGained(FocusEvent e) {
        if (e.getSource() == ab) {
            ab.setBackground(Color.green);
        }
        if (e.getSource() == db) {
            db.setBackground(Color.green);
        }
        if (e.getSource() == sb) {
            sb.setBackground(Color.green);
        }
        if (e.getSource() == hm) {
            hm.setBackground(Color.green);
        }
        if (e.getSource() == ub) {
            ub.setBackground(Color.green);
        }
        if (e.getSource() == addB) {
            addB.setBackground(Color.green);
        }
        if (e.getSource() == updB) {
            updB.setBackground(Color.green);
        }
        if (e.getSource() == searchB) {
            searchB.setBackground(Color.green);
        }
        if (e.getSource() == delB) {
            delB.setBackground(Color.green);
        }
        if (e.getSource() == pid) {
            pid.setText("");
        }
        if (e.getSource() == pname) {
            pname.setText("");
        }
        if (e.getSource() == qua) {
            qua.setText("");
        }
        if (e.getSource() == price) {
            price.setText("");
        }
        if (e.getSource() == ptype) {
            ptype.setText("");
        }
    }

    public void focusLost(FocusEvent e) {
        if (e.getSource() == ab) {
            ab.setBackground(Color.lightGray);
        }
        if (e.getSource() == db) {
            db.setBackground(Color.lightGray);
        }
        if (e.getSource() == sb) {
            sb.setBackground(Color.lightGray);
        }
        if (e.getSource() == hm) {
            hm.setBackground(Color.lightGray);
        }
        if (e.getSource() == ub) {
            ub.setBackground(Color.lightGray);
        }
        if (e.getSource() == addB) {
            addB.setBackground(Color.lightGray);
        }
        if (e.getSource() == updB) {
            updB.setBackground(Color.lightGray);
        }
        if (e.getSource() == searchB) {
            searchB.setBackground(Color.lightGray);
        }
        if (e.getSource() == delB) {
            delB.setBackground(Color.lightGray);
        }
    }

    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == sb) {
            search.setVisible(true);
            searchB.setVisible(true);
            id.setVisible(false);
            pid.setVisible(false);
            name.setVisible(false);
            pname.setVisible(false);
            q.setVisible(false);
            qua.setVisible(false);
            pri.setVisible(false);
            price.setVisible(false);
            type.setVisible(false);
            ptype.setVisible(false);
            addB.setVisible(false);
            delB.setVisible(false);
            updB.setVisible(false);
            database.setVisible(true);
            js.setVisible(true);
             try {
             Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "system", "manager");  
             Statement st = con.createStatement();
             res = st.executeQuery("select * from Medical");
             dtm.setRowCount(0);
             while(res.next()){
               String name=res.getString(1);
               int idp=res.getInt(2);
               String tp=res.getString(3);
               int qty=res.getInt(4);
               int pricep=res.getInt(5);
                dtm.addRow(new Object[]{idp,name,tp,qty,pricep});
            

             }
             JOptionPane.showMessageDialog(null,"Data Retrived Successfully");
             } catch (Exception e) {
             System.out.println(e);
             }
        } else if (ae.getSource() == ab) {
            try {
                search.setVisible(false);
                searchB.setVisible(false);
                id.setVisible(true);
                pid.setVisible(true);
                name.setVisible(true);
                pname.setVisible(true);
                q.setVisible(true);
                qua.setVisible(true);
                pri.setVisible(true);
                price.setVisible(true);
                type.setVisible(true);
                ptype.setVisible(true);
                addB.setVisible(true);
                delB.setVisible(false);
                updB.setVisible(false);
                database.setVisible(false);
                js.setVisible(false);
            } catch (Exception e) {
                System.out.println("Error occures  : " + e);
            }
        } else if (ae.getSource() == hm) {
            search.setVisible(true);
            searchB.setVisible(true);
            id.setVisible(false);
            pid.setVisible(false);
            name.setVisible(false);
            pname.setVisible(false);
            q.setVisible(false);
            qua.setVisible(false);
            pri.setVisible(false);
            price.setVisible(false);
            type.setVisible(false);
            ptype.setVisible(false);
            addB.setVisible(false);
            delB.setVisible(false);
            updB.setVisible(false);
            database.setVisible(false);
            js.setVisible(false);
        } else if (ae.getSource() == db) {
            search.setVisible(false);
            searchB.setVisible(false);
            id.setVisible(true);
            pid.setVisible(true);
            name.setVisible(false);
            pname.setVisible(false);
            q.setVisible(false);
            qua.setVisible(false);
            pri.setVisible(false);
            price.setVisible(false);
            type.setVisible(false);
            ptype.setVisible(false);
            addB.setVisible(false);
            delB.setVisible(true);
            updB.setVisible(false);
            database.setVisible(false);
            js.setVisible(false);
        } else if (ae.getSource() == ub) {
            search.setVisible(false);
            searchB.setVisible(false);
            id.setVisible(true);
            pid.setVisible(true);
            name.setVisible(false);
            pname.setVisible(false);
            q.setVisible(true);
            qua.setVisible(true);
            pri.setVisible(false);
            price.setVisible(false);
            type.setVisible(false);
            ptype.setVisible(false);
            addB.setVisible(false);
            delB.setVisible(false);
            updB.setVisible(true);
            database.setVisible(false);
            js.setVisible(false);

        } else if (ae.getSource() == addB) {
            int id_no = Integer.parseInt(pid.getText());
            String p_name = pname.getText();
            int quant = Integer.parseInt(qua.getText());
            int amt = Integer.parseInt(price.getText());
            String p_type = ptype.getText();
            try {
                psa.setInt(2, id_no);
                psa.setString(1, p_name);
                psa.setInt(4, quant);
                psa.setInt(5, amt);
                psa.setString(3, p_type);
                psa.executeUpdate();
                JOptionPane.showMessageDialog(null,"Product Added Successfully");
            } catch (Exception e) {
                System.out.println(e);
            }
        } else if (ae.getSource() == updB) {
            try {
                int amt = Integer.parseInt(qua.getText());
                psu.setInt(1, amt);
                int id_no = Integer.parseInt(pid.getText());
                psu.setInt(2, id_no);
                psu.executeUpdate();
                JOptionPane.showMessageDialog(null,"Product Updated Successfully");

            } catch (Exception e) {
                System.out.println(e);
            }
        } else if (ae.getSource() == delB) {
            try {
                int id_no = Integer.parseInt(pid.getText());
                psd.setInt(1, id_no);
                psd.executeUpdate();
                JOptionPane.showMessageDialog(null,"Product Deleted Successfully");
            } catch (Exception e) {
                System.out.println(e);
            }
        } else if (ae.getSource() == searchB) {
            // int i2=Integer.parseInt(search.getText());
            String name_p = search.getText();
            // String type_p=search.getText();
            try {
                // pss.setInt(1, i2);
                // pss.setString(1, type_p);
                pss.setString(1, name_p);
                res1 = pss.executeQuery();

            } catch (Exception e) {
                System.out.println(e);
            }

        }
    }

    public static void main(String[] args) throws Exception {
        new Homepage();
        // String sql="create table JavaMp4(id number(4),name varchar(20),qty
        // number(4),price number(4,2),type varchar(20))";
    }
}
