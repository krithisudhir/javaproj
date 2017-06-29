package cyberwork;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.*;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;

class IDException extends Exception{
    
    String msg=new String("INVALID ID!");
    IDException(){}
   
    @Override
    public String toString(){
    return "IDException["+msg+"]\n\n";
    }
}

abstract class Customer{  
                        //BASE CLASS AND ATTRIBUTES
             
            static String Name; //Every customer has Name and ID
            static int ID;
            static String type;//Students or staff?
            Scanner in=new Scanner(System.in);
            abstract void enterID() throws IDException;//Since digits in ID differ 
            
            Customer(){     //Constructor to read name
                System.out.println("Enter Name:");
                Name=in.next();
                      }
            
            int read(){     //Common method to read ID
                ID=in.nextInt(); return ID;
                       }
       
            void display()  //Common display method
            {
                System.out.println("You have been allocated a system, you can now use"
                    + "it"+"\n\n");
            }
          
             static String dispcost(){  //common format to display bill
             return 
                "   "+"ID: "+ID+"            "+
                "Name :"+Name+"         " +"User:"+type+ "          ";
                 }
}


interface calculate{                        /*an interface which define standard rates
                                              and a method definition to calc cost*/
         double enggstudcost=3.75;
         double mtechstudcost=4.00;
         double staffcost=5.25;
         public String cost(int mins);
 
                   } 


class Student extends Customer{ //derived from customer
        String disc;    //which discipline?
        Student(){      //constructor to read name
        super();
        type="Student";
                }
        public void enterID() throws IDException{   //abstract method implemented
          
        System.out.printf("\nEnter 4 digit Student ID: \n");
        read();
        if(String.valueOf(ID).length()!=4) throw new IDException();
        //display();
        
        }
        
}
    
class EnggStu extends Student implements calculate{//derived from student class
    
    EnggStu(){
        super();//reads name
       // try{enterID();}catch(IDException e){return;}//reads ID from superclass
        disc="Engineering";
            }
  
       public String cost(int mins){//cost based on mins used
       double amt=enggstudcost*mins;
       return "cost:"+amt+"         "+"Time used:"+mins+"          "+"discipline:"+disc+"          ";
                                    }
        
 }
class MtechStu extends Student implements calculate{
        MtechStu(){
        super();
        /*try{enterID();}catch(IDException e){return;}*/
        disc="Mtech";
                  }

     public String cost(int mins){
     double amt=enggstudcost*mins;
     return "cost:"+amt+"           "+"Time used:"+mins+"           "+"discipline:"+disc+"          ";}
    
                                 }

   class Staff extends Customer implements calculate{
            Staff(){
            super();
            type="Staff";
                   }
           
            public void enterID() throws IDException{
     
                System.out.printf("Enter 6 digit Staff ID: \n");
                read();//derived from base
                if(String.valueOf(ID).length()!=6)
                throw new IDException();
                
                display();
                                 
            }
     
        public String cost(int mins){
        double amt=staffcost*mins;
        return "cost:"+amt+"\n"+"Time used:"+mins+"\n";
                              }       
}

class operations
{  
   
   public  String ss;//The string to be written to file,shud be accessible by all methods in the class
   public LinkedList LL=new LinkedList<String>();//Should be accessible by all methods in the class
   
    public int op(){
        
        Scanner k=new Scanner(System.in);
        int s=1;char c;int res=0;long startTime=0,elapsedTime=0;
        System.out.printf("Time started!\n"); 
        startTime = System.currentTimeMillis();
        for(;;){
        elapsedTime = System.currentTimeMillis() - startTime;
        long elapsedSeconds = elapsedTime / 1000;
        long secondsDisplay = elapsedSeconds % 60;
        long elapsedMinutes = elapsedSeconds / 60;
        if (elapsedMinutes==1){
        res=JOptionPane.showConfirmDialog(null,"1minute up!Do you want to continue?","Welcome",JOptionPane.YES_NO_OPTION);
        if(res==JOptionPane.NO_OPTION) return s;
        else {
                startTime=System.currentTimeMillis();s+=1;
        }
                                
        
                }
        
    
        }
    }
    
    

     void billing(String source,String source1) throws IOException{
     
 
                System.out.println("GENERATING INVOICE...");
                 
                ss=source1.concat(source);LL.add(ss);
                //concatenates for storing in file     
                byte buff[]=ss.getBytes();
                OutputStream ff=new FileOutputStream("fileTele.txt");
                ff.write(buff);
                ff.close();
                Runtime rt= Runtime.getRuntime();
                String file="C:\\Users\\Krithi\\Documents\\Cyberwork\\fileTele.txt";
                Process p=rt.exec("notepad "+ file);
            } 
    
     //void createList(String ss){
      //linked list to store recent users
         // LL.add(ss);
   // }
    void dispList(){
        //to the list
        System.out.println(LL);
                            }

}




 class Cyberwork{
    Scanner k=new Scanner(System.in);
    String pass="1234";
    operations ob=new operations();
   
    
    void meth(){
    JButton ok = new JButton( "OK"); 
    JFrame guiFrame = new JFrame();
    guiFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    guiFrame.setTitle("MAIN MENU"); 
    guiFrame.setSize(300,250); 
    guiFrame.setLocationRelativeTo(null);  //Options for the JComboBox 
    String[] PersonOptions = {"","User","Administrator","Exit"}; 
    final JPanel comboPanel = new JPanel(); 
    JLabel comboLbl = new JLabel("Select:"); 
    JComboBox ppl = new JComboBox(PersonOptions); 
    comboPanel.add(comboLbl); 
    comboPanel.add(ppl);
    
    ok.addActionListener(new ActionListener() { 
    @Override
    public void actionPerformed(ActionEvent event) {
       
    System.out.println("Entered: "+ ppl.getSelectedItem());
    guiFrame.dispose();
    
    if(ppl.getSelectedItem()=="User"){
    String source,source1;
    EnggStu stu1;
    MtechStu stu2;
    Staff staf;
   
    calculate ref;//calculate is an interface
    int c;
    
    for(;;){
    System.out.printf("Select choice: 1.Student use   2.Profesional use  3.Exit\n");
     int ch=k.nextInt();
    switch(ch){
        
    case 1:
            System.out.printf("1.Engineering  2.Mtech\n");
            c=k.nextInt();
             if(c==1){ stu1=new EnggStu();
                    try{
            stu1.enterID();
    
           
            }catch(IDException e){System.out.print(e);break;}
                   ref=stu1;}
    else if(c==2) {
            stu2=new MtechStu();
            try{
            stu2.enterID();
            }catch(IDException e){System.out.print(e);break;}
            ref=stu2;}
        else {System.out.printf("invalid\n");break;}
        int res=JOptionPane.showConfirmDialog(null,"Do you want to begin?","Welcome",JOptionPane.YES_NO_OPTION);
        if(res==JOptionPane.NO_OPTION) {break;}
    
                
                c= ob.op();//returns mins used
                source1=new String(Customer.dispcost());//string that displays billing msg
                source=new String(ref.cost(c));//calculated amount, also in a string
    
        try {
            ob.billing(source,source1);//billing method concatenates both strings and puts it into file
        } catch (IOException ex) {
            Logger.getLogger(Cyberwork.class.getName()).log(Level.SEVERE, null, ex);
        }
    
                //ob.createList(source1.concat(source));break;//store in LL
                //Myt1 thr=new Myt1();
                //thr.nwait();
                break;
 
    case 2:
        staf=new Staff();
        try{
            staf.enterID();
            }catch(IDException e){System.out.print(e);break;}
                
               //ob.prepgui();
             res=JOptionPane.showConfirmDialog(null,"Do you want to begin?","Welcome",JOptionPane.YES_NO_OPTION);
             if(res==JOptionPane.NO_OPTION) meth();
    
                      
                c=ob.op();
                ref=staf;//reference of interface cost point to staff
                source1=new String(Customer.dispcost());
                source=new String(ref.cost(c));
    
        try {
            ob.billing(source,source1);
        } catch (IOException ex) {
            Logger.getLogger(Cyberwork.class.getName()).log(Level.SEVERE, null, ex);
        }
    
                //ob1.createList(source1.concat(source));
                 break;
 
    
    default:meth();
    }
    break;
    }
    meth();
    }
    else if(ppl.getSelectedItem()=="Administrator"){
        
    class PasswordDemo extends JPanel implements ActionListener{
    String OK = "ok";
    String GO_BACK = "go back";
    int flag=-1,ch=0;
    
 
    JFrame controllingFrame; //needed for dialogs
    JPasswordField passwordField;
    
 
    public PasswordDemo(JFrame f) {
        //Use the default FlowLayout.
        controllingFrame = f;
 
        //Create everything.
        passwordField = new JPasswordField(10);
        passwordField.setActionCommand(OK);
      
        passwordField.addActionListener(this);
      
 
        JLabel label = new JLabel("Enter the password: ");
        label.setLabelFor(passwordField);
 
        JComponent buttonPane = createButtonPanel();
 
        //Lay out everything.
        JPanel textPane = new JPanel(new FlowLayout(FlowLayout.TRAILING));
        textPane.add(label);
        textPane.add(passwordField);
 
        add(textPane);
        add(buttonPane);
    }
 
    protected JComponent createButtonPanel() {
        JPanel p = new JPanel(new GridLayout(0,1));
        JButton okButton = new JButton("OK");
        JButton gobackButton = new JButton("GO BACK");
 
        okButton.setActionCommand(OK);
        gobackButton.setActionCommand(GO_BACK);
        okButton.addActionListener(this);
        gobackButton.addActionListener(this);
      
      
 
        p.add(okButton);
       p.add(gobackButton);
 
        return p;
    }
 
    public void actionPerformed(ActionEvent e) {
        String cmd = e.getActionCommand();
 
        if (OK.equals(cmd)) { //Process the password.
            char[] input = passwordField.getPassword();
            if (isPasswordCorrect(input)) {
                JOptionPane.showMessageDialog(controllingFrame,
                    "Success! You typed the right password.");flag=0;
            } else {
                JOptionPane.showMessageDialog(controllingFrame,
                    "Invalid password. Try again.",
                    "Error Message",
                    JOptionPane.ERROR_MESSAGE);flag=1;
            }
 
            //Zero out the possible password, for security.
            Arrays.fill(input, '0');
            passwordField.selectAll();
            resetFocus();
        } 
   
        else flag=2;//for going back
        
            if(flag==0){
                controllingFrame.dispose();//correct password
                 for(;;){
                    System.out.println("1.View Recent users 2.Exit");
                    ch=k.nextInt();
                    if(ch==1){
                        ob.dispList();
                    
                        
                    }else {meth();break;}//go back
                }
            } else if(flag==2) {controllingFrame.dispose();meth();}
            else{}
    }
    
 
 boolean isPasswordCorrect(char[] input) {
        boolean isCorrect = true;
        char[] correctPassword = { 'b', 'u', 'g', 'a', 'b', 'o', 'o' };
 
        if (input.length != correctPassword.length) {
            isCorrect = false;
        } else {
            isCorrect = Arrays.equals (input, correctPassword);
        }
 
        //Zero out the password.
        Arrays.fill(correctPassword,'0');
 
        return isCorrect;
    }
 
  
    protected void resetFocus() {
        passwordField.requestFocusInWindow();
    }
   
    
        }
        
 
        //Create and set up the window.
        JFrame frame = new JFrame("PasswordDemo");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
 
        //Create and set up the content pane.
        PasswordDemo newContentPane = new PasswordDemo(frame);
        newContentPane.setOpaque(true); //content panes must be opaque
        frame.setContentPane(newContentPane);
 
       
        frame.addWindowListener(new WindowAdapter() {
            public void windowActivated(WindowEvent e) {
                newContentPane.resetFocus();
            }
        });
 
        //Display the window.
        frame.pack();
        frame.setVisible(true);
      
      SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                //Turn off metal's use of bold fonts
        //UIManager.put("swing.boldMetal", Boolean.FALSE);
        PasswordDemo pwd=new PasswordDemo(frame);
       
            }
        });
      
        
            
       
 
    
    /*System.out.print("EnterPassword\n");
    String scan=k.next();int ch;
    if(scan.compareTo(pass)==0) {
        System.out.println("You are authorized\n");
                for(;;){
                    System.out.println("1.View Recent users 2.Exit");
                    ch=k.nextInt();
                    if(ch==1){
                        ob.dispList();
                        
                    }else break;
                }meth();
    }
    else {System.out.println("Try again!");meth();}*/
    }
    
    
    
    else if(ppl.getSelectedItem()=="Exit"){System.exit(0);}
    else {}
   
       }
    });
  guiFrame.add(comboPanel, BorderLayout.NORTH); 
   
   guiFrame.add(ok,BorderLayout.SOUTH);  

   guiFrame.setVisible(true);
     }
     
                                            //main class
    public static void main(String args[]) throws Exception{
   
    Cyberwork cyb=new Cyberwork();cyb.meth();
    
   }
      
    }   
 

    
    
    

