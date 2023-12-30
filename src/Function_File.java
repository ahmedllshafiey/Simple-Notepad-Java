import java.awt.FileDialog;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class Function_File {
    GUI gui;
    String fileName;
    String fileAdress;
    
    public Function_File(GUI gui) {
        this.gui = gui;
    }
    
    public void new_File() {
        gui.textArea.setText("");
        gui.window.setTitle("New Text");
        fileName = null;
        fileAdress = null;
    }
    
    public void open_File() {
        FileDialog fd = new FileDialog(gui.window, "Open File", FileDialog.LOAD);
        fd.setVisible(true);
        
        if(fd.getFile() != null) {
            fileName = fd.getFile();
            fileAdress = fd.getDirectory();
            gui.window.setTitle(fileName);
        }
        
        try {
            BufferedReader br = new BufferedReader(new FileReader(fileAdress+fileName));
            gui.window.setTitle("");
            String line;
            while((line=br.readLine())!=null){
                gui.textArea.append(line+"\n");
            }
        } catch(IOException e){
            System.out.println("FILE NOT OPENED");
        }
    }
    
    public void save_File() {
        if(fileName == null){
            save_File_As();
        }
        else {
                try {
                    try (FileWriter fw = new FileWriter(fileAdress+fileName)) {
                        fw.write(gui.textArea.getText());
                        gui.window.setTitle(fileName);
                    }
                } 
                catch(IOException e) {
                System.out.println("SOME THING WRONG HAPPENED");
            }
        }
    }
    
    public void save_File_As() {
        var fd = new FileDialog(gui.window, "Save As", FileDialog.SAVE);
        fd.setVisible(true);
        
        if(fd.getFile()!=null) {
            fileName = fd.getFile();
            fileAdress = fd.getDirectory();
            gui.window.setTitle(fileName);
        }
        
        try{
            try (FileWriter fw = new FileWriter(fileAdress+fileName)) {
                fw.write(gui.textArea.getText());
            }
        } catch(IOException e) {
            System.out.println("SOME THING WRONG HAPPENED");
        }
    }
    
    public void exit_File() {
        System.exit(0);
    }
}
