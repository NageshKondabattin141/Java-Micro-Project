import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import javax.swing.event.*;
class tabbedpane extends JFrame 
{
	JTabbedPane tb;
	JTextField ippid,ippn,ipIN,ipOUT,ipExp,ipPrice;
	JLabel pid,pn,IN,OUT,Exp,Price,msg;
	JButton addB,upd,del;
	ImageIcon i1,i2,i3,i4;
	JPanel homeP,addP,updateP,deleteP;
	JLabel l1,l2,l3,l4;
	Container c;
	tabbedpane()
	{
		setSize(780,580);
		setLocation(100,100);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		c=getContentPane();
		tb=new JTabbedPane();
		homeP=new JPanel();addP=new JPanel();updateP=new JPanel();deleteP=new JPanel();
		i1=new ImageIcon("medical4.jpg");i2=new ImageIcon("img2.jpg");
		i3=new ImageIcon("medical4.jpg");i4=new ImageIcon("medical4.jpg");
		l1=new JLabel("",i1,JLabel.CENTER);l2=new JLabel("",i2,JLabel.CENTER);
		l3=new JLabel("",i3,JLabel.CENTER);l4=new JLabel("",i4,JLabel.CENTER);
		homeP.add(l1);updateP.add(l3);deleteP.add(l4);
		pid=new JLabel("Product ID :");pn=new JLabel("Product Name :");IN=new JLabel("STOCK IN :");
		OUT=new JLabel("STOCK OUT :");Exp=new JLabel("STOCK EXP :");Price=new JLabel("Price :");
		ippid=new JTextField(10);ippn=new JTextField(10);ipIN=new JTextField(10);
		ipOUT=new JTextField(10);ipExp=new JTextField(10);ipPrice=new JTextField(10);
		addB=new JButton("ADD");upd=new JButton("Update");del=new JButton("Delete");
		pid.setBounds(300,50,120,30);
//		pid.setForeground(Color.white);pid.setBackground(Color.blue);
		ippid.setBounds(460,50,180,30);
		pn.setBounds(300,110,120,30);
//		pn.setForeground(Color.white);
		ippn.setBounds(460,110,180,30);
		IN.setBounds(300,170,120,30);
//		IN.setForeground(Color.white);

		ipIN.setBounds(460,170,180,30);
		OUT.setBounds(300,230,120,30);
//		OUT.setForeground(Color.white);
		ipOUT.setBounds(460,230,180,30);
		Exp.setBounds(300,290,120,30);
//		Exp.setForeground(Color.white);
		ipExp.setBounds(460,290,180,30);
		Price.setBounds(300,350,120,30);
//		Price.setForeground(Color.white);
		ipPrice.setBounds(460,350,180,30);
		addB.setBounds(387,420,80,30); upd.setBounds(267,420,80,30); del.setBounds(267,420,80,30);
//		pid.setForeground(Color.white);
		l2.add(pid); l2.add(ippid);l2.add(pn); l2.add(ippn);l2.add(IN); l2.add(ipIN);l2.add(OUT); 
		l2.add(ipOUT);l2.add(Exp); l2.add(ipExp);l2.add(Price); l2.add(ipPrice);l2.add(addB);
		addP.add(l2);
		upd=new JButton("Update");
//		l3.add(pid);l3.add(ippid);l3.add(pn);l3.add(ippn);l3.add(IN);l3.add(ipIN);l3.add(OUT);
//		l3.add(ipOUT);l3.add(Exp);l3.add(ipExp);l3.add(Price);l3.add(ipPrice);
l3.add(upd);
		updateP.add(l3);
		tb.addTab("Home",homeP);tb.addTab("ADD",addP);tb.addTab("Update",updateP);tb.addTab("Delete",deleteP);
		c.add(tb);
		setVisible(true);
	}
	public static void main(String ar[]){
		tabbedpane t=new tabbedpane();
	}
}