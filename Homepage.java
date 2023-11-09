import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import java.text.SimpleDateFormat;
import javax.swing.*;
import javax.swing.event.*;
import javax.swing.table.*;
import java.util.Date;
import java.util.Vector;
import oracle.jdbc.driver.OracleSQLException;

class Homepage extends JFrame implements ActionListener, FocusListener {
    JTextField pid, pname, qua, price, ptype, searchField;
    JLabel id, name, q, pri, type, searchdata;
    SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
    JButton ab, db, ub, hm, addB, delB, updB, sell, sellp, genBill;
    JTable database;
    Vector<Integer> selledId = new Vector<>();
    Vector<Integer> selledQty = new Vector<>();
    Container c;
    static PreparedStatement psa;
    static PreparedStatement psd;
    static PreparedStatement psu;
    static PreparedStatement pss;
    static PreparedStatement pssell;
    static PreparedStatement psbill;
    static Connection con;
    ResultSet res, res1;
    static DefaultTableModel dtm;
    JScrollPane js;

    Homepage() {
        setSize(800, 800);
        setLocation(500, 180);
        setLayout(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        c = getContentPane();
        searchField = new JTextField();
        searchField.setBounds(350, 50, 250, 30);
        c.add(searchField);
        searchdata = new JLabel("Search");
        searchdata.setBounds(260, 50, 80, 30);
        c.add(searchdata);

        ab = new JButton("ADD");
        db = new JButton("DELETE");
        ub = new JButton("UPDATE");
        hm = new JButton("SHOW");
        sell = new JButton("SELL");
        hm.setBounds(50, 200, 100, 30);
        ab.setBounds(50, 250, 100, 30);
        db.setBounds(50, 300, 100, 30);
        ub.setBounds(50, 350, 100, 30);
        sell.setBounds(50, 400, 100, 30);
        c.add(ab);
        c.add(db);
        c.add(ub);
        c.add(hm);
        c.add(sell);
        ab.addActionListener(this);
        db.addActionListener(this);
        ub.addActionListener(this);
        hm.addActionListener(this);
        sell.addActionListener(this);

        ab.addFocusListener(this);
        db.addFocusListener(this);
        ub.addFocusListener(this);
        hm.addFocusListener(this);
        sell.addFocusListener(this);
        sell.setBackground(Color.lightGray);

        c.setBackground(Color.ORANGE);

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
        sellp = new JButton("SELL Product");
        sellp.setBounds(400, 400, 150, 40);
        genBill = new JButton("Generate Bill");
        genBill.setBounds(590, 400, 150, 40);

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
        sellp.setVisible(false);
        c.add(sellp);
        genBill.setVisible(false);
        c.add(genBill);

        sellp.setBackground(Color.lightGray);
        genBill.setBackground(Color.lightGray);

        updB.setVisible(false);
        addB.addActionListener(this);
        updB.addActionListener(this);
        delB.addActionListener(this);
        sellp.addActionListener(this);
        genBill.addActionListener(this);

        addB.addFocusListener(this);
        updB.addFocusListener(this);
        delB.addFocusListener(this);
        pid.addFocusListener(this);
        pname.addFocusListener(this);
        qua.addFocusListener(this);
        price.addFocusListener(this);
        ptype.addFocusListener(this);
        sellp.addFocusListener(this);
        genBill.addFocusListener(this);

        dtm = new DefaultTableModel();
        database = new JTable(dtm) {
            public boolean isCellEditable(int row, int colheads) {
                return false;
            }
        };
        database.setBounds(250, 200, 500, 250);
        js = new JScrollPane(database);
        js.setBounds(250, 200, 500, 250);
        c.add(js);
        database.setVisible(true);
        js.setVisible(true);
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
        dtm.addColumn("PRICE(per piece)");

        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
            Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "system", "manager");
            Statement st = con.createStatement();
            res = st.executeQuery("select * from Medical");
            dtm.setRowCount(0);
            while (res.next()) {
                String name = res.getString(1);
                int idp = res.getInt(2);
                String tp = res.getString(3);
                int qty = res.getInt(4);
                int pricep = res.getInt(5);
                dtm.addRow(new Object[] { idp, name, tp, qty, pricep });
            }
        } catch (ClassNotFoundException e) {

        } catch (SQLException e) {
            System.out.println(e.getMessage());
            // JOptionPane.showConfirmDialog(null, "Product ID Already Exist");
        }

        // Search Functionality
        TableRowSorter sorter = new TableRowSorter<>(dtm);
        database.setRowSorter(sorter);
        searchField.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                search(searchField.getText());
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                search(searchField.getText());
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                search(searchField.getText());
            }

            public void search(String str) {
                if (str.length() == 0) {
                    sorter.setRowFilter(null);
                } else {
                    sorter.setRowFilter(RowFilter.regexFilter(str));
                }
            }

        });

        ab.setBackground(Color.lightGray);
        ub.setBackground(Color.lightGray);
        db.setBackground(Color.lightGray);
        hm.setBackground(Color.lightGray);
        addB.setBackground(Color.lightGray);
        updB.setBackground(Color.lightGray);
        delB.setBackground(Color.lightGray);
        // setBackground(Color.magenta);
        setVisible(true);

        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
            System.out.println("Driver Loaded");
            Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "system", "manager");
            System.out.println("Connected");
            psa = con.prepareStatement("insert into Medical values(?,?,?,?,?)");
            psbill = con.prepareStatement("insert into Bills values(?,?,?,?,?,?)");
            psu = con.prepareStatement("update Medical set qty=?,price=? where id=?");
            psd = con.prepareStatement("delete from Medical where id=?");
            pssell = con.prepareStatement("update Medical set qty=? where id=?");

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
        if (e.getSource() == sell) {
            sell.setBackground(Color.green);
        }
        if (e.getSource() == sellp) {
            sellp.setBackground(Color.green);
        }
        if (e.getSource() == genBill) {
            genBill.setBackground(Color.green);
        }
    }

    public void focusLost(FocusEvent e) {
        if (e.getSource() == ab) {
            ab.setBackground(Color.lightGray);
        }
        if (e.getSource() == db) {
            db.setBackground(Color.lightGray);
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
        if (e.getSource() == delB) {
            delB.setBackground(Color.lightGray);
        }
        if (e.getSource() == sell) {
            sell.setBackground(Color.lightGray);
        }
        if (e.getSource() == sellp) {
            sellp.setBackground(Color.lightGray);
        }
        if (e.getSource() == genBill) {
            genBill.setBackground(Color.green);
        }
    }

    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == ab) {
            try {
                searchField.setVisible(false);
                searchdata.setVisible(false);
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
                sellp.setVisible(false);
                genBill.setVisible(false);
                database.setVisible(false);
                js.setVisible(false);
            } catch (Exception e) {
                System.out.println("Error occures  : " + e);
            }
        } else if (ae.getSource() == hm) {
            searchField.setVisible(true);
            searchdata.setVisible(true);
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
            sellp.setVisible(false);
            genBill.setVisible(false);
            database.setVisible(true);
            js.setVisible(true);
            try {
                Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "system",
                        "manager");
                Statement st = con.createStatement();
                res = st.executeQuery("select * from Medical");
                dtm.setRowCount(0);
                while (res.next()) {
                    String name = res.getString(1);
                    int idp = res.getInt(2);
                    String tp = res.getString(3);
                    int qty = res.getInt(4);
                    int pricep = res.getInt(5);
                    dtm.addRow(new Object[] { idp, name, tp, qty, pricep });
                }
            } catch (Exception e) {
                System.out.println(e);
            }
        } else if (ae.getSource() == db) {
            searchField.setVisible(false);
            searchdata.setVisible(false);
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
            sellp.setVisible(false);
            genBill.setVisible(false);
        } else if (ae.getSource() == ub) {
            searchField.setVisible(false);
            searchdata.setVisible(false);
            id.setVisible(true);
            pid.setVisible(true);
            name.setVisible(false);
            pname.setVisible(false);
            q.setVisible(true);
            qua.setVisible(true);
            pri.setVisible(true);
            price.setVisible(true);
            type.setVisible(false);
            ptype.setVisible(false);
            addB.setVisible(false);
            delB.setVisible(false);
            updB.setVisible(true);
            database.setVisible(false);
            js.setVisible(false);
            genBill.setVisible(false);
            sellp.setVisible(false);

        } else if (ae.getSource() == addB) {
            try {
                int id_no = Integer.parseInt(pid.getText());
                String p_name = pname.getText();
                int quant = Integer.parseInt(qua.getText());
                int amt = Integer.parseInt(price.getText());
                String p_type = ptype.getText();

                if ((pname.getText()).equals("") || (ptype.getText()).equals("")) {

                    JOptionPane.showMessageDialog(null, "All Fields Must be Filled..");

                } else {
                    if (quant != 0) {
                        psa.setInt(2, id_no);
                        psa.setString(1, p_name);
                        psa.setInt(4, quant);
                        psa.setInt(5, amt);
                        psa.setString(3, p_type);
                        psa.executeUpdate();
                        JOptionPane.showMessageDialog(null, "Product ID " + id_no + " is Added Successfully..");
                    } else {
                        JOptionPane.showMessageDialog(null, "Invalid Quantity Entered..");
                    }
                }

            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "All Fields must be Filled..");
            } catch (SQLException e) {
                int ip = Integer.parseInt(pid.getText());
                JOptionPane.showMessageDialog(null, "Product ID " + ip + " is Already Exists..");
            }
        } else if (ae.getSource() == updB) {

            try {
                int quant = Integer.parseInt(qua.getText());
                int id_no = Integer.parseInt(pid.getText());
                int pr = Integer.parseInt(price.getText());
                Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "system",
                        "manager");
                Statement st = con.createStatement();
                ResultSet r = st.executeQuery("select * from Medical where id=" + id_no);
                if (!r.next()) {
                    JOptionPane.showMessageDialog(null, "Product ID " + id_no + " Not Found..");
                } else {
                    if (quant != 0) {
                        psu.setInt(3, id_no);
                        psu.setInt(1, quant);
                        psu.setInt(2, pr);
                        psu.executeUpdate();
                        JOptionPane.showMessageDialog(null, "Product ID " + id_no + " is Updated Successfully");
                    } else {
                        JOptionPane.showMessageDialog(null, "Invalid Quantity..");
                    }
                }
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "All Fields Must be Filled..");
            } catch (SQLException e) {
                System.out.println(e);
            }
        } else if (ae.getSource() == delB) {
            try {
                int id_no = Integer.parseInt(pid.getText());
                // if((pid.getText()).equals("")){
                Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "system",
                        "manager");
                Statement st = con.createStatement();
                ResultSet r = st.executeQuery("select * from Medical where id=" + id_no);
                if (!r.next()) {
                    JOptionPane.showMessageDialog(null, "Product ID " + id_no + " Not Found..");
                } else {
                    psd.setInt(1, id_no);
                    psd.executeUpdate();
                    JOptionPane.showMessageDialog(null, "Product ID " + id_no + " is Deleted Successfully..");
                }
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "ID Field Must be Filled..");
            } catch (SQLException e) {
                System.out.println(e);
            }
        } else if (ae.getSource() == sell) {
            searchField.setVisible(false);
            searchdata.setVisible(false);
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
            updB.setVisible(false);
            database.setVisible(false);
            genBill.setVisible(false);
            js.setVisible(false);
            sellp.setVisible(true);

        } else if (ae.getSource() == sellp) {
            try {
                Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "system",
                        "manager");
                Statement st = con.createStatement();
                int id_no = Integer.parseInt(pid.getText());
                int qtys = Integer.parseInt(qua.getText());
                ResultSet r = st.executeQuery("select * from Medical where id=" + id_no);
                if (!r.next()) {
                    JOptionPane.showMessageDialog(null, "Product ID " + id_no + " is Not Available...");
                } else {

                    String pn = r.getString(1);
                    int q = r.getInt(4);
                    int p_price = r.getInt(5);
                    int updQuant = (q - qtys);
                    pssell.setInt(1, updQuant);
                    pssell.setInt(2, id_no);
                    pssell.executeUpdate();
                    JOptionPane.showMessageDialog(null, "Product selled..Stocks Updated");
                    genBill.setVisible(true);
                    selledId.add(id_no);
                    selledQty.add(qtys);
                    st.executeQuery("delete from Medical where qty=0");
                }

            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "All Field Must be Filled..");
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
        } else if (ae.getSource() == genBill) {
            try {
                String str = "";
                int tb = 0;
                for (int i = 0; i < selledId.size(); i++) {
                    Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "system",
                            "manager");
                    Statement st = con.createStatement();
                    ResultSet r = st.executeQuery("select * from Medical where id=" + selledId.get(i));
                    if (!r.next()) {

                        JOptionPane.showMessageDialog(null, "Error While Generating Bill");
                    } else {
                        String name = r.getString(1);
                        int price_per = r.getInt(5);
                        int TotalBill = selledQty.get(i) * price_per;
                        String date = dateFormat.format(new Date());

                        psbill.setString(1, date);
                        psbill.setInt(2, selledId.get(i));
                        psbill.setString(3, name);
                        psbill.setInt(4, price_per);
                        psbill.setInt(5, selledQty.get(i));
                        psbill.setInt(6, TotalBill);
                        psbill.executeUpdate();

                        str = str + "\n\nDate: " + date + "\nProduct Name : " + name
                                + "\nPrice/piece : " + price_per + "\nQuantity : " + selledQty.get(i)
                                + "\nTotal Bill : "
                                + TotalBill;
                        tb = tb + TotalBill;
                    }
                }
                selledId.clear();
                selledQty.clear();
                JOptionPane.showMessageDialog(null, str + "\n\nOverAll Total Bill = " + tb);
                JOptionPane.showMessageDialog(null, "Bill Info Stored in Bills Table");
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
    }

    public static void main(String[] args) throws Exception {
        new Homepage();
    }
}
