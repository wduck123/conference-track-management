/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lab.smartics.conference.management;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.Month;
import java.util.Iterator;
import java.util.List;
import lab.smartics.conference.management.business.ConferenceManagementFacade;
import lab.smartics.conference.management.io.ConferenceFileSourceManager;
import lab.smartics.conference.management.model.Conference;
import lab.smartics.conference.management.model.Event;
import lab.smartics.conference.management.model.Session;
import lab.smartics.conference.management.model.Track;

/**
 *
 * @author wduck
 */
public class ConferenceManager {

    private ConferenceManagementFacade facade;

    public ConferenceManager() {
        facade = new ConferenceManagementFacade();
    }

    public Conference processAndScheduleEvent(List<Event> events) {

        Conference conference = facade.createConference("FISA JavaDay");
        Track track1 = new Track(1, null, LocalDate.of(2021, Month.APRIL, 30));
        Track track2 = new Track(2, null, LocalDate.of(2021, Month.APRIL, 30));

        facade.addTrackToConference(conference, track1);
        facade.addTrackToConference(conference, track2);

        fillTrackDefault(track1);
        fillTrackDefault(track2);

        System.out.println("Events in: " + events.size());
        // run this loop till all the events are scheduled.
        Integer count = 0;
        while (!events.isEmpty()) {
            
            for (Iterator<Event> eventsIterator = events.iterator(); eventsIterator.hasNext();) {
                count++;
                System.out.println("process ===== " + count.toString());
                Event e = eventsIterator.next();               
                for (Track track : conference.getTracks()) {                                        
                    boolean isAdded = facade.addEventToTrack(track, e);
                    if (isAdded)    
                        break;
                };
                eventsIterator.remove();
            }
        }

        return conference;
    }

    public List<Event> fetchTalksListFromSource(String fileName) throws IOException {
        ConferenceFileSourceManager sourceManager = new ConferenceFileSourceManager();
        try {
            return sourceManager.fetchTalks(fileName);
        } catch (FileNotFoundException e) {
            System.err.println(e.getMessage());
            return null;
        }

    }

    public void outputConferenceSchedule(Conference conference) {
        facade.printSchedule(conference);
    }

    private void fillTrackDefault(Track track) {
        Session morningSession = new Session(LocalTime.of(9, 0), LocalTime.of(12, 0));
        Session lunchSession = new Session(LocalTime.of(12, 0), LocalTime.of(13, 0));
        Session afternoonSession = new Session(LocalTime.of(13, 0), LocalTime.of(17, 0));
        Session networkingSession = new Session(LocalTime.of(17, 0), LocalTime.of(18, 0));

        Event lunchEvent = new Event("Lunch");
        Event networkingEvent = new Event("Networking Event");

        facade.addSessionToTrack(track, morningSession);
        facade.addSessionToTrack(track, lunchSession);
        facade.addSessionToTrack(track, afternoonSession);
        facade.addSessionToTrack(track, networkingSession);

        lunchSession.add(lunchEvent);
        networkingSession.add(networkingEvent);
    }

}
