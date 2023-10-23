import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;
import javax.swing.table.*;


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
