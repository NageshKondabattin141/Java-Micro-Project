import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;
import javax.swing.table.*;
class Homepage extends JFrame
{
	JTabbedPane tp;
	JPanel home,addP,updateP,deleteP;
	JLabel l1,l2,l3,l4;
	Container c;
	Homepage(){
	setSize(588,450);
	setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	c=getContentPane();
	tp=new JTabbedPane();
	ImageIcon img1=new ImageIcon("medical4.jpg");
	ImageIcon img2=new ImageIcon("medical2.jpg");
    ImageIcon img3=new ImageIcon("medical3.jpg");
	ImageIcon img4=new ImageIcon("Medicalstore.jpg");
	home=new JPanel();
	addP=new JPanel();
	updateP=new JPanel();
	deleteP=new JPanel();
	l1=new JLabel("Home Page");
	l2=new JLabel("Add Page");
	l3=new JLabel("Update Page");
	l4=new JLabel("Delete Page");
	home.add(l1);
	addP.add(l2);
	updateP.add(l3);
	deleteP.add(l4);
	tp.addTab("HOME",img1,home);
	//tp.addTab("ADD",img2,addP);
	//tp.addTab("UPDATE",img3,updateP);
	tp.addTab("DELETE",img4,deleteP);
	c.add(tp);
	}	
}
class MP extends JFrame implements ActionListener
{
JButton log;
  JLabel user,pass,heading,background,invalid;
  JTextField ipuser;
  JPasswordField ippass;
  Container c; 
    
  MP()
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
	  MP m=new MP();
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
MP m=new MP();
m.setLocation(200,160);
}
}