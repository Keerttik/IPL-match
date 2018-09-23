import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class FileParser {
    private String fileName;
    private List<List<String>> entriesList;
    private final String EOF = "-EOF-";
    private int expectedFileds;
	private static final String COMMA_DELIMITER = ",";

    public FileParser(String fileName) {
        this.fileName = fileName;
        this.entriesList = new ArrayList<>();
        processFile();
    }

    private void processFile() {
        try (
       	BufferedReader bufferedReader = new BufferedReader(new FileReader(new File(fileName)))) {
            boolean isFirstLine = true;

            String s = "";
            
            while (true) {

                String entry = getEntry(bufferedReader, s);

                //skip the 1st line as they are headers
                if (isFirstLine == true) {
                    isFirstLine = false;
                    this.expectedFileds = entry.split(",").length;
                    continue;
                }
                if (entry.equals(EOF)) break;

                List<String> singleEntryList = getSingleEntryList(entry, expectedFileds);


                entriesList.add(singleEntryList);/*

        		Scanner scanner = null;
        		try {
        			//Get the scanner instance
        			scanner = new Scanner(new File(filename));
        			//Use Delimiter as COMMA
        			scanner.useDelimiter(COMMA_DELIMITER);
        			while(scanner.hasNext())
        			{
        					System.out.print(scanner.next()+"   ");
        			*/
        		
    
    }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }


    }

    public List<List<String>> getEntriesList() {
        return entriesList;
    }

    private String getEntry(BufferedReader bufferedReader, String s) {	//for reading the file
        try {
            if ((s = bufferedReader.readLine()) != null) {

            } else {
                s = EOF;
            }
        } catch (IOException e) {
            System.out.println("try again");
            e.printStackTrace();
        }

        return s;
    }

    private static List<String> getSingleEntryList(String entry, int expectedFileds) {
        String[] tokenisedEntry = entry.split(COMMA_DELIMITER);
        String[] processedList = Arrays.copyOf(tokenisedEntry, expectedFileds);

        List entryList = Arrays.asList(processedList);

      //   System.out.println(entryList); prints all file

        return entryList;
    }

}
    