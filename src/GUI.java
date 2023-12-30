import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.event.UndoableEditEvent;
import javax.swing.event.UndoableEditListener;
import javax.swing.undo.UndoManager;

public final class GUI implements ActionListener{
    
    JFrame window;
    // Textarea
    JTextArea textArea;
    JScrollPane scrollPane;
    boolean wordWrapOn = false;
    // Top Menu
    JMenuBar menuBar;
    JMenu menuFile, menuEdit, menuFormat, menuColor;
    // File Menu
    JMenuItem iNew, iOpen, iSave, iSaveAs, iExit;
    // Edit Menu
    JMenuItem iWrap, iFontArial, iFontCSMS, iFontTNR, iFontSize8, iFontSize12, iFontSize16, iFontSize20, iFontSize24, iFontSize28;
    JMenu iFont, iFontSize;
    // Color Menu
    JMenuItem iColor1, iColor2, iColor3;
    //Edit Menu
    JMenuItem iUndo, iRedo;
    
    Function_File file = new Function_File(this);
    Function_Format format = new Function_Format(this);
    Function_Color color = new Function_Color(this);
    Function_Edit edit = new Function_Edit(this);
    
    UndoManager um = new UndoManager();
    
    public static void main(String[] args) {
        
        new GUI();
    }
    
    public GUI(){
        
        createWindow();
        createTextArea();
        createMenuBar();
        fileItemMenu();
        create_Edit_Menu();
        createColorMenu();
        createEditMenu();
        
        // Set Defualt font 
        format.selectedFont = "Arial";
        format.createFont(16);
        format.wordWrap();
        
        // Set Default color
        color.changeColor("White");
        
        window.setVisible(true);
    }
    
    public void createWindow() {
        
        window = new JFrame("Notepad");
        window.setSize(800,600);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
    
    public void createTextArea() {
        
        textArea = new JTextArea();
        textArea.getDocument().addUndoableEditListener(
                new UndoableEditListener() {
                    @Override
                    public void undoableEditHappened(UndoableEditEvent e) {
                        um.addEdit(e.getEdit());
                    }
                }
            );
        scrollPane = new JScrollPane(textArea, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setBorder(BorderFactory.createEmptyBorder());
        window.add(scrollPane);
    }
    
    public void createMenuBar() {
        
        menuBar = new JMenuBar();
        window.setJMenuBar(menuBar);
        
        menuFile = new JMenu("File");
        menuBar.add(menuFile);
        
        menuEdit = new JMenu("Edit");
        menuBar.add(menuEdit);
        
        menuFormat = new JMenu("Format");
        menuBar.add(menuFormat);
        
        menuColor = new JMenu("Color");
        menuBar.add(menuColor);
    }
    
    public void fileItemMenu() {
        
        iNew = new JMenuItem("New");
        iNew.addActionListener(this);
        iNew.setActionCommand("New");
        menuFile.add(iNew);
        
        iOpen = new JMenuItem("Open");
        iOpen.addActionListener(this);
        iOpen.setActionCommand("Open");
        menuFile.add(iOpen);
        
        iSave = new JMenuItem("Save");
        iSave.addActionListener(this);
        iSave.setActionCommand("Save");
        menuFile.add(iSave);
        
        iSaveAs = new JMenuItem("Save As");
        iSaveAs.addActionListener(this);
        iSaveAs.setActionCommand("Save As");
        menuFile.add(iSaveAs);
        
        iExit = new JMenuItem("Exit");
        iExit.addActionListener(this);
        iExit.setActionCommand("Exit");
        menuFile.add(iExit);
    }
    
    public void create_Edit_Menu() {
        
        iWrap = new JMenuItem("Word Wrap: Off");
        iWrap.addActionListener(this);
        iWrap.setActionCommand("Word Wrap");
        menuFormat.add(iWrap);
        
        iFont = new JMenu("Font");
        menuFormat.add(iFont);
        
        iFontCSMS = new JMenuItem("Comic Sans MS");
        iFontCSMS.addActionListener(this);
        iFontCSMS.setActionCommand("Comic Sans MS");
        iFont.add(iFontCSMS);
        
        iFontArial = new JMenuItem("Arial");
        iFontArial.addActionListener(this);
        iFontArial.setActionCommand("Arial");
        iFont.add(iFontArial);
        
        iFontTNR = new JMenuItem("Times New Roman");
        iFontTNR.addActionListener(this);
        iFontTNR.setActionCommand("Times New Roman");
        iFont.add(iFontTNR);
        
        iFontSize = new JMenu("Font Size");
        menuFormat.add(iFontSize);
        
        iFontSize8 = new JMenuItem("8");
        iFontSize8.addActionListener(this);
        iFontSize8.setActionCommand("Size 8");
        iFontSize.add(iFontSize8);
        
        iFontSize12 = new JMenuItem("12");
        iFontSize12.addActionListener(this);
        iFontSize12.setActionCommand("Size 12");
        iFontSize.add(iFontSize12);
        
        iFontSize16 = new JMenuItem("16");
        iFontSize16.addActionListener(this);
        iFontSize16.setActionCommand("Size 16");
        iFontSize.add(iFontSize16);
        
        iFontSize20 = new JMenuItem("20");
        iFontSize20.addActionListener(this);
        iFontSize20.setActionCommand("Size 20");
        iFontSize.add(iFontSize20);
        
        iFontSize24 = new JMenuItem("24");
        iFontSize24.addActionListener(this);
        iFontSize24.setActionCommand("Size 24");
        iFontSize.add(iFontSize24);
        
        iFontSize28 = new JMenuItem("28");
        iFontSize28.addActionListener(this);
        iFontSize28.setActionCommand("Size 28");
        iFontSize.add(iFontSize28);
        
    }
    
    public void createEditMenu() {
        iUndo = new JMenuItem("Undo");
        iUndo.addActionListener(this);
        iUndo.setActionCommand("Undo");
        menuEdit.add(iUndo);
        
        iRedo = new JMenuItem("Redo");
        iRedo.addActionListener(this);
        iRedo.setActionCommand("Redo");
        menuEdit.add(iRedo);
    }
    
    public void createColorMenu() {
        
        iColor1 = new JMenuItem("White");
        iColor1.addActionListener(this);
        iColor1.setActionCommand("White");
        menuColor.add(iColor1);
        
        iColor2 = new JMenuItem("Black");
        iColor2.addActionListener(this);
        iColor2.setActionCommand("Black");
        menuColor.add(iColor2);
        
        iColor3 = new JMenuItem("Blue");
        iColor3.addActionListener(this);
        iColor3.setActionCommand("Blue");
        menuColor.add(iColor3);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        
        String command = e.getActionCommand();
        
        switch(command) {
            
            case("New") -> {
                
                file.new_File(); break;
            }
            case("Open") -> {
                
                file.open_File(); break;
            }
            case("Save As") -> {
                
                file.save_File_As(); break;
            }
            case("Save") -> {
                
                file.save_File(); break;
            }
            case("Exit") -> {
                
                file.exit_File(); break;
            }
            case("Word Wrap") -> {
                format.wordWrap(); break;
            }
            case("Size 8") -> {
                format.createFont(8); break;
            }
            case("Size 12") -> {
                format.createFont(12); break;
            }
            case("Size 16") -> {
                format.createFont(16); break;
            }
            case("Size 20") -> {
                format.createFont(20); break;
            }
            case("Size 24") -> {
                format.createFont(24); break;
            }
            case("Size 28") -> {
                format.createFont(28); break;
            }
            case("Arial") -> {
                format.setFont(command); break;
            }
            case("Comic Sans MS") -> {
                format.setFont(command); break;
            }
            case("Times New Roman") -> {
                format.setFont(command); break;
            }
            case("White") -> {
                color.changeColor(command); break;
            }
            case("Black") -> {
                color.changeColor(command); break;
            }
            case("Blue") -> {
                color.changeColor(command); break;
            }
            case("Undo") -> {
                edit.undo(); break;
            }
            case("Redo") -> {
                edit.redo(); break;
            }
        }
    }
}