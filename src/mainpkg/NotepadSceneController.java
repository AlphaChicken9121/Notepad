/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package mainpkg;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.Scanner;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextArea;
import javafx.scene.layout.BorderPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;


/**
 * FXML Controller class
 *
 * @author ASUS
 */
public class NotepadSceneController implements Initializable {

    @FXML private Label statusBarLabel;
    @FXML private TextArea notepadTextArea;
    private File currentFile;
    @FXML
    public MenuItem closeButton;
    @FXML
    private BorderPane borderPane;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
    @FXML
    void openFileChooserOnMouseClicked() {
        FileChooser fc = new FileChooser();
        fc.getExtensionFilters().add(new FileChooser.ExtensionFilter("Text files (*.txt)", "*.txt"));
        File f = fc.showOpenDialog(null);
        currentFile = f;
        Stage stage = (Stage) borderPane.getScene().getWindow();
        stage.setTitle(currentFile.getName() + " - notepad");
        if(f != null){
            try{
                Scanner sc = new Scanner(f);
                String str = "";
                
                int lineNumber = 0, wordCount = 0;
                while (sc.hasNextLine()){
                    str += sc.nextLine()+"\n";
                    lineNumber += 1;
                    String[] words = str.split("\\s+");
                    wordCount = words.length;
                }
                notepadTextArea.setText(str);
                statusBarLabel.setText("Word Count: "+Integer.toString(wordCount)+". Line count: " + Integer.toString(lineNumber)+".");
            }
            catch (FileNotFoundException ex){
                
            }
        }
        
    }

    @FXML
    private void saveFileOnMouseClicked(ActionEvent event) throws IOException {
        if (currentFile!= null){
            FileWriter fw = new FileWriter(currentFile);
            String str = "";
            try {
                str += notepadTextArea.getText().replaceAll("\n", System.getProperty("line.separator"));
                fw.write(str);
                fw.close();
            }
            catch (Exception ex){
            
            }
        } else{
            FileChooser fc = new FileChooser();
            fc.getExtensionFilters().add(new FileChooser.ExtensionFilter("Text files (*.txt)", "*.txt"));
            File f = fc.showSaveDialog(null);
            FileWriter fw = new FileWriter(f);
            String str = "";
            try {
                str += notepadTextArea.getText().replaceAll("\n", System.getProperty("line.separator"));
                fw.write(str);
                fw.close();
            }
            catch (Exception ex){
            
            }
        }
    }

    @FXML
    private void saveNewFileOnMouseClicked(ActionEvent event) throws IOException {
        FileChooser fc = new FileChooser();
        fc.getExtensionFilters().add(new FileChooser.ExtensionFilter("Text files (*.txt)", "*.txt"));
        File f = fc.showSaveDialog(null);
        FileWriter fw = new FileWriter(f);
        String str = "";
        try {
            str += notepadTextArea.getText().replaceAll("\n", System.getProperty("line.separator"));
            fw.write(str);
            fw.close();
        }
        catch (Exception ex){
            
        }
    }

    @FXML
    private void clearNotepadOnMouseClicked(ActionEvent event) {
       notepadTextArea.clear();
        statusBarLabel.setText("Word Count: 0. Line Count: 0.");
        currentFile = null;
    }

    @FXML
    private void closeNotepadOnMouseClicked(ActionEvent event) {
        Stage stage = (Stage) borderPane.getScene().getWindow();
        stage.close();
    }
    
}
