

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class FileParser {
    private String fileName;
    private List<List<String>> entriesList;
    private final String EOF = "-EOF-";
    private int expectedFileds;

    public FileParser(String fileName) {
        this.fileName = fileName;
        this.entriesList = new ArrayList<>();
        processFile();
    }

    private void processFile() {
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(new File(fileName)))) {
            boolean isFirstLine = true;

            String s = "";
            
            while (true) {

                String entry = getEntry(bufferedReader, s);

                //skip the first line of the file
                if (isFirstLine == true) {
                    isFirstLine = false;
                    this.expectedFileds = entry.split(",").length;
                    continue;
                }
                if (entry.equals(EOF)) break;

                List<String> singleEntryList = getSingleEntryList(entry, expectedFileds);


                entriesList.add(singleEntryList);

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

    private String getEntry(BufferedReader bufferedReader, String s) {
        try {
            if ((s = bufferedReader.readLine()) != null) {

            } else {
                s = EOF;
            }
        } catch (IOException e) {
            System.out.println("Issue while processing single entry");
            e.printStackTrace();
        }

        return s;
    }

    private static List<String> getSingleEntryList(String entry, int expectedFileds) {
        String[] tokenisedEntry = entry.split(",");
        String[] processedList = Arrays.copyOf(tokenisedEntry, expectedFileds);

        List entryList = Arrays.asList(processedList);

         System.out.println(entryList);

        return entryList;
    }

}
    