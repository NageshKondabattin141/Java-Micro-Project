import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;
import javax.swing.table.*;
class Add_data extends JFrame implements ActionListener
{
	JTextField ippname,ippid,ipIN,ipOUT,ipExp,ipPrice;
	JLabel pname,pid,IN,OUT,Exp,Price,msg;
	JButton addB,back;
	Container c;
	JPanel p1;
	Add_data()
	{
		c=getContentPane();
		setSize(588,450);
		ImageIcon img=new ImageIcon("medical3.jpg");
	   p1=new JPanel();
       p1.setLayout(null);
	   setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	   pid=new JLabel("Product ID :");
	   pid.setBounds(80,50,100,30);
	   p1.add(pid);
	   ippid=new JTextField(10);
	   ippid.setBounds(190,50,100,30);
	   p1.add(ippid);
	   pname=new JLabel("Product Name :");
	   pname.setBounds(80,90,100,30);
	   p1.add(pname);
	   ippname=new JTextField(10);
	   ippname.setBounds(190,90,100,30);
	   p1.add(ippname);
	   IN=new JLabel("Stock IN :");
	   pname.setBounds(80,130,100,30);
	   p1.add(IN);
	   ipIN=new JTextField(10);
	   pname.setBounds(190,130,100,30);
	   p1.add(ipIN);
	   OUT=new JLabel("Stock OUT :");
	   pname.setBounds(80,170,100,30);
	   p1.add(OUT);
	   ipOUT=new JTextField(10);
	   pname.setBounds(190,170,100,30);
	   p1.add(ipOUT);
	   Exp=new JLabel("Stock Expired :");
	   pname.setBounds(80,210,100,30);
	   p1.add(Exp);
	   ipExp=new JTextField(10);
	   pname.setBounds(190,210,100,30);
	   p1.add(ipExp);
	   Price=new JLabel("Price :");
	   pname.setBounds(80,250,100,30);
	   p1.add(Price);
	   ipPrice=new JTextField(10);
	   pname.setBounds(190,250,100,30);
	   p1.add(ipPrice);
	   c.add(p1);
	   addB=new JButton("ADD");
	   pname.setBounds(145,290,65,30);
	   p1.add(addB);
	   back=new JButton("Back");
	   pname.setBounds(80,330,65,30);
	   p1.add(back);
	   }
}
class Homepage extends JFrame implements ActionListener
{
	JButton search,addData,updateData,deleteData;
	JTextField ipsearch;
	JTable datatable;
	JLabel hp;
	String col[]={"PID","PNAME","IN","OUT","Exp","PRICE"};
	Object row[][]={{123,"abc",32,20,15.30f},{124,"xyz",50,10,25.30f}};
	Container c;
	Homepage()
	{  setSize(588,450);
       setLayout(null);
       setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
       c=getContentPane();
		
		ImageIcon img2=new ImageIcon("medical4.jpg");
		hp=new JLabel("",img2,JLabel.CENTER);
		hp.setBounds(-9,-20,588,450);
		c.add(hp);
		ipsearch=new JTextField(10);
		ipsearch.setBounds(240,30,200,25);
		hp.add(ipsearch);
		search=new JButton("Search");
		search.setBounds(450,30,80,25);
		hp.add(search);
		addData=new JButton("ADD");
		addData.setBounds(30,120,80,25);
		hp.add(addData);
		updateData=new JButton("UPDATE");
		updateData.setBounds(30,170,90,25);
		hp.add(updateData);
		deleteData=new JButton("DELETE");
		deleteData.setBounds(30,220,90,25);
		hp.add(deleteData);
		datatable=new JTable(row,col);
		JScrollPane sp=new JScrollPane(datatable);
		hp.add(sp);  
		addData.addActionListener(this);
		updateData.addActionListener(this);
		deleteData.addActionListener(this);
	}
	public void actionPerformed(ActionEvent e){
		if(e.getSource()==addData){
			Homepage h=new Homepage();
			Add_data a=new Add_data();
			a.setVisible(true);
			a.setLocation(200,160);
			h.setVisible(false);
		}
		
	}
}

class Medical_Login extends JFrame implements ActionListener
{
  JButton log;
  JLabel user,pass,heading,background,invalid;
  JTextField ipuser;
  JPasswordField ippass;
  Container c; 
    
  Medical_Login()
{
  setSize(588,450);
  setLayout(null);
  setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
  c=getContentPane();
  ImageIcon img=new ImageIcon("medical.jpg");
  background=new JLabel("",img,JLabel.CENTER);
  background.setBounds(-9,-20,588,450);
  heading=new JLabel("MEDICAL  STOCK  LOGIN");
  heading.setBounds(217,130,250,30);
  background.add(heading);
  user=new JLabel("Username:");
  user.setBounds(212,190,80,30);
  background.add(user);
  ipuser=new JTextField(8);
  ipuser.setBounds(297,190,80,30);
  background.add(ipuser);
  pass=new JLabel("Password : ");
  pass.setBounds(212,240,80,30);
  background.add(pass);
  ippass=new JPasswordField(10);
  ippass.setBounds(297,240,80,30);
  background.add(ippass);
  log=new JButton("LOGIN");
  log.addActionListener(this);
  log.setBounds(252,290,80,30);
  background.add(log);
  add(background); 
    
   log.addMouseListener(new MouseAdapter()
      { public void mouseEntered(MouseEvent e)
	  {
		  log.setBackground(Color.green);
	  }
	  public void mouseExited(MouseEvent e)
	  {
		  log.setBackground(Color.white);
	  }
  });  
   log.addActionListener(this);
   invalid=new JLabel();
   invalid.setBounds(242,310,250,30);
   background.add(invalid);
  setVisible(true);
}
public void actionPerformed(ActionEvent e)
{
	if(ipuser.getText().equals("ADMIN")&&ippass.getText().equals("admin123"))
	{
	  Homepage h=new Homepage();
	  Medical_Login m=new Medical_Login();
	  h.setVisible(true);
	  h.setLocation(200,160);
	  m.setVisible(false);
    }
	else
	{
		String str="Invalid Username or Password ";
		invalid.setText(str);
	}
}

public static void main(String ar[])
{
Medical_Login m=new Medical_Login();
m.setLocation(200,160);
}
}
