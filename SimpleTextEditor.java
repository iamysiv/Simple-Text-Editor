import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.print.PrinterException;
import java.io.*;


//Project name- Simple text editor
class SimpleTextEditor extends JFrame implements ActionListener {
   JFrame frame;
   JTextArea textArea;
   SimpleTextEditor(){
      // created the frame
      frame= new JFrame("Simple Text Editor");
      // created the text area.
      textArea= new JTextArea();
      frame.add(textArea);   // added the text area to frame
      // set size of the freame
      frame.setSize(800,800);
      // turned the visibility on
      frame.setVisible(true);
      // created the menuBar
      JMenuBar menuBar = new JMenuBar();
      // created file and edit option as menu.
      JMenu fileMenu= new JMenu("File Menu");
      JMenu editMenu= new JMenu("Edit Menu");
      // added menu to menubar
      menuBar.add(fileMenu);
      menuBar.add(editMenu);
      // created menu item for file menu
      JMenuItem open = new JMenuItem("Open File");
      JMenuItem save = new JMenuItem("Save File");
      JMenuItem print = new JMenuItem("Print File");
      JMenuItem newFile = new JMenuItem("New File");
      // added item to file menu
      fileMenu.add(open);
      fileMenu.add(save);
      fileMenu.add(print);
      fileMenu.add(newFile);
      // creating functionality for file menu items
      open.addActionListener(this);
      save.addActionListener(this);
      print.addActionListener(this);
      newFile.addActionListener(this);
      // created menu item edit menu
      JMenuItem cut = new JMenuItem("Cut");
      JMenuItem copy = new JMenuItem("Copy");
      JMenuItem paste = new JMenuItem("Paste");
      JMenuItem close = new JMenuItem("Close");
      // added item to edit menu
      editMenu.add(cut);
      editMenu.add(copy);
      editMenu.add(paste);
      editMenu.add(close);
      // adding functionality to the edit menu
      cut.addActionListener(this);
      copy.addActionListener(this);
      paste.addActionListener(this);
      close.addActionListener(this);

      frame.setJMenuBar(menuBar); //to add the menu bar in frame.
      frame.show();
      frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
   }

   public static void main(String[] args) {
      SimpleTextEditor simpleTextEditor = new SimpleTextEditor();
   }

   @Override
   public void actionPerformed(ActionEvent e) {
      String s= e.getActionCommand();  // this gets whatever action is performed to "s".
      if(s=="Cut"){
         textArea.cut();
      }else if(s=="Copy"){
         textArea.copy();
      }else if(s=="Paste"){
         textArea.paste();
      }else if(s== "Open File"){
         JFileChooser fileChooser = new JFileChooser();           //create object
         int ans= fileChooser.showOpenDialog(null);         // store the action if they chose this option
         if(ans==JFileChooser.APPROVE_OPTION){                     // if they choose it
            File file= fileChooser.getSelectedFile();              //store the file in "file"
            String path = file.getPath();                          //store the path of the stored file
            try{
               BufferedReader br = new BufferedReader(new FileReader(path));
               String s1="",s2="";
               while((s1=br.readLine())!=null){
                  s2+=s1+"\n";
               }
               textArea.setText(s2);
            } catch (FileNotFoundException ex) {
               ex.printStackTrace();
            } catch (IOException ex) {
               ex.printStackTrace();
            }
         }
      }else if(s== "Save File"){
         JFileChooser fileChooser = new JFileChooser("C:");
         int ans = fileChooser.showOpenDialog(null);
         if(ans==JFileChooser.APPROVE_OPTION){
           File file = new File(fileChooser.getSelectedFile().getAbsolutePath());
            BufferedWriter writer= null;
            try {
               writer = new BufferedWriter(new FileWriter(file,false));
            } catch (IOException ex) {
               ex.printStackTrace();
            }
            try {
               writer.write(textArea.getText());
            } catch (IOException ex) {
               ex.printStackTrace();
            }
            try {
               writer.flush();
            } catch (IOException ex) {
               ex.printStackTrace();
            }
            try {
               writer.close();
            } catch (IOException ex) {
               ex.printStackTrace();
            }
         }
      }else if(s=="Print File"){
         try {
            textArea.print();
         } catch (PrinterException ex) {
            ex.printStackTrace();
         }
      }else if(s=="New File") {
         textArea.setText("");
      }else if(s=="Close"){
         frame.setVisible(false);
      }
   }
}
