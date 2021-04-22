/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lab.smartics.conference.management;

import java.io.IOException;
import java.util.List;
import lab.smartics.conference.management.model.Conference;
import lab.smartics.conference.management.model.Event;

/**
 *
 * @author wduck
 */
public class Main {
    
    public static void main(String[] args) {

        ConferenceManager conferenceManager = new ConferenceManager();
        List<Event> talkList = null;
        // Fetch the input talk list.
        try {
            talkList = conferenceManager.fetchTalksListFromSource("input-talks.txt");
        } catch (IOException e){
            System.err.println(e.getMessage());
        }

        if(talkList == null || talkList.isEmpty())
            return;

        // Process and schedule talks into events and slots.
        Conference conference = conferenceManager.processAndScheduleEvent(talkList);

        // Output the conference events.
        try {
            conferenceManager.outputConferenceSchedule(conference);
        } catch (Exception e){
            System.err.println(e.getMessage());
        }

    }
}
