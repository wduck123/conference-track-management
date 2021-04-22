/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lab.smartics.conference.management.io;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import lab.smartics.conference.management.model.Event;
import lab.smartics.conference.management.model.Talk;

/**
 *
 * @author wduck
 */
public class ConferenceFileSourceManager {
    
    public List<Event> fetchTalks(String fileName) throws FileNotFoundException, IOException{
        FileInputStream fstream = null;
        List<Event> talkList = new ArrayList<>();

        try {
            fstream = new FileInputStream(fileName);
        } catch (FileNotFoundException e) {
            System.err.println("Input file specified not found : " + fileName + ". Make sure the file exists");
            throw e;
        }
        BufferedReader br = new BufferedReader(new InputStreamReader(fstream));

        String strLine;
        int intMinutes;

        // Read Input File Line By Line
        try {
            while ((strLine = br.readLine()) != null) {
                // handle comments or empty lines.
                if(strLine.contains("//") || strLine.isEmpty())
                    continue;

                String title = strLine.substring(0, strLine.lastIndexOf(" "));
                String minutesString = strLine.substring(strLine.lastIndexOf(" ") + 1);
                // get only the integers as string from the line.
                String minutes = strLine.replaceAll("\\D+", "");                
                try {
                    intMinutes = Integer.parseInt(minutes);
                } catch (NumberFormatException e) {
                    System.err.println("Could not parse the line : " + strLine);
                    intMinutes = 60;
                    //throw e;
                }
                
                Talk singleTalk = new Talk(title, intMinutes);
                talkList.add(singleTalk);

            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                fstream.close();
                br.close();
            } catch (IOException e){
                System.err.println(e.getMessage());
            }
        }
        return talkList;
    }

}
