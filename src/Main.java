import java.io.File;
import java.io.FileNotFoundException;  // Import this class to handle errors
import java.util.Scanner;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Vector;

import utils.*;

public class Main {
    private static final String NEW_LINE_SEPARATOR = "\n";
    private static final String FILE_HEADER = "Fecha|Hora|NombrePersona|Mensaje";

    public static void main(String[] args) {
        Vector<Message> messageList = new Vector();

        try {
            File file =
                    new File("_chat.txt");
            Scanner sc = new Scanner(file);
            String inputLine, dateTime, date, time, sender, text;
            String[] strArr, strArr2, strArr3; //String arrays

            while (sc.hasNextLine()){ //Loop through the file
                inputLine = sc.nextLine(); //Get the line

                strArr = inputLine.split("]", 2);
                dateTime = strArr[0];

                strArr2 = dateTime.split("[, ]", 2);

                date = strArr2[0].substring(1);

                if(strArr2[1].charAt(0) == ' ')
                    time = strArr2[1].substring(1);
                else
                    time = strArr2[1];

                strArr3 = strArr[1].split(":", 2);

                sender = strArr3[0].substring(1);
                text = strArr3[1].substring(1);

                if(date.charAt(0) == '[')
                    date = date.substring(1);

                Message message = new Message(date, time, sender, text);

                messageList.add(message);
            }

            FileWriter fileWriter = null;

            try {
                fileWriter = new FileWriter("_output.csv");

                fileWriter.append(FILE_HEADER); //Write the header
                fileWriter.append(NEW_LINE_SEPARATOR);

                for(int i = 0; i < messageList.size(); i++){
                    Message current = messageList.elementAt(i);
                    fileWriter.append(current.concat());
                    fileWriter.append(NEW_LINE_SEPARATOR);
                }

                System.out.println("CSV file created successfully");
            } catch (Exception e) {
                System.out.println("Error");
                e.printStackTrace();
            } finally {
                try {
                    fileWriter.flush();
                    fileWriter.close();
                } catch (IOException e) {
                    System.out.println("Error while flushing/closing fileWriter");
                    e.printStackTrace();
                }
            }
            sc.close();
        }catch (FileNotFoundException e) {
            System.out.println("File not found");
            e.printStackTrace();
        }
    }
}
