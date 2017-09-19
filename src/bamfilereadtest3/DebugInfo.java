package bamfilereadtest3;

/*
 * This class creates 1 or more files and prints out information it passed
 * from other points in this project. It is used for testing and debugging
 */
import java.io.*; // library for imput/ouptut
/**
 *
 * @author anwar01
 */
public class DebugInfo {    
    private static String[] text = new String [500];
    private static int currentPosition = 0; // the crrent position to write to in the array
    
    
    public DebugInfo() { // constructor of the object records the name of the file
        //fileName = name;
    }
    
    public static void addText(String txt) {
        text[currentPosition] = txt; // put the text sent to the method into the array        
        currentPosition = currentPosition + 1; // data writen to next element next time
    }
    
    public static void createFile(String name) { // method to create the file
        try{
            PrintWriter outputStream = new PrintWriter 
          ("C:/Users/anwar01/Documents/BamFileReadProject/Output/Notes/" +name+ ".txt", "UTF-8"); // creates the file
            
            for (int i=0; i<currentPosition; i++) {
                outputStream.println(text[i]); // print line for each array element
            }
            
            //Arrays.fill(text, ""); // fills the arry with banks to empty it
            for (int i=0; i<currentPosition; i++) {
                text[i] = ""; // empty the array so more info can be put in
            }
            
            currentPosition = 0; // reset the counter to the array fill from beginning 
            
            outputStream.close(); // close the file
            
        } catch (IOException e) {
            System.out.println("ERROR producing file: " +e);
        }
    }
}
