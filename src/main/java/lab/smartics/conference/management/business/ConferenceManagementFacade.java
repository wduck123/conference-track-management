/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lab.smartics.conference.management.business;

import java.time.format.DateTimeFormatter;
import lab.smartics.conference.management.model.Conference;
import lab.smartics.conference.management.model.Event;
import lab.smartics.conference.management.model.Session;
import lab.smartics.conference.management.model.Talk;
import lab.smartics.conference.management.model.Track;

/**
 *
 * @author wduck
 */
public class ConferenceManagementFacade {

    public Conference createConference(String title){
        return new Conference(title);
    }
    
    public void addTrackToConference(Conference conference, Track track){
        conference.add(track);
    }
    
    public void addSessionToTrack(Track track, Session session){
        track.add(session);
    }
    
    public boolean addEventToTrack(Track track, Event event){
        boolean isAdded = false;
        for (Session session : track.getSessions()) {            
            try {
                session.add(event);
                //System.out.println("----> added to session: " + event);
                isAdded = true;
                break;
            } catch(IllegalStateException ise){
                System.out.println("----> try next session..");
                isAdded = false;
            }
        }
        return isAdded;
    }
    
    public void printSchedule (Conference conference) {
        System.out.println("Conference " + conference.getTitle() + " -- Schedule :");
        System.out.println("--------------------------------------------------------");
        for(Track track : conference.getTracks()){
            System.out.println(track.getName() + " --> on " + track.getDate().format(DateTimeFormatter.ISO_DATE) + ":\n");            
            // Output the talks into tracks based on the totalTalks and the count of Talks.
            for (Session session : track.getSessions()) {
                for (Event event : session.getEvents()) {
                    // Print the prepared talk's title for this Track
                    StringBuilder eventStrBuilder = new StringBuilder(event.getInitTime().format(DateTimeFormatter.ISO_TIME));
                    eventStrBuilder.append(" ").append(event.getTitle());                    
                    if (event instanceof Talk){
                        eventStrBuilder.append(" -- ").append(((Talk)event).getDurationInMinutes()).append(" min");
                    }
                    eventStrBuilder.append(".");
                    
                    System.out.println(eventStrBuilder.toString());
                }
            }
            System.out.println("--------------------------------------------------------");
        }
    }
    
}
