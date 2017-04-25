import java.io.*;
import java.util.*;

public class ReadAndSortFile {
    public static void main(String [] args) {

        // The name of the file to open.
        String fileNameRead = "lorem.txt";

        // The name of the file to write to.
        String fileNameForwards = "forwards.txt";
        String fileNameBackwards = "reverse.txt";


        // This will reference one line at a time
        String line = null;

        try {
            // FileReader reads text files in the default encoding.
            FileReader fileReader =
                new FileReader(fileNameRead);

            // Always wrap FileReader in BufferedReader.
            BufferedReader bufferedReader =
                new BufferedReader(fileReader);

            BufferedWriter bwf = new BufferedWriter(new FileWriter(fileNameForwards));

            List<String> tmp = new ArrayList<String>();

            while((line = bufferedReader.readLine()) != null) {
              bwf.write(line+"\n");
              tmp.add(line);
            }

            try (BufferedWriter bwb = new BufferedWriter(new FileWriter(fileNameBackwards))) {

              for(int i=tmp.size()-1;i>=0;i--) {
                  bwb.write(tmp.get(i)+"\n");
              }

            // no need to close it.
            //bw.close();

            System.out.println("Done");

          } catch (IOException e) {

            e.printStackTrace();

          }

            // Always close files.
            bufferedReader.close();
        }
        catch(FileNotFoundException ex) {
            System.out.println(
                "Unable to open file '" +
                fileNameForwards + "'");
        }
        catch(IOException ex) {
            System.out.println(
                "Error reading file '"
                + fileNameForwards + "'");
            // Or we could just do this:
            // ex.printStackTrace();
        }
    }
}
