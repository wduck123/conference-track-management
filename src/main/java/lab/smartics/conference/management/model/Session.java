/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lab.smartics.conference.management.model;

import java.io.Serializable;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 *
 * @author wduck
 */
public class Session implements Serializable{
    
    private LocalTime startTime; 
    private LocalTime endTime;
    private int remainingDuration;
    
    private List<Event> events;

    public Session() {
        events = new ArrayList<>();
    }

    public Session(LocalTime startTime, LocalTime endTime) {
        this();
        this.startTime = startTime;
        this.endTime = endTime;
        remainingDuration = initRemainingDuration();        
    }
    
    private int initRemainingDuration(){        
        int seconds = this.endTime.toSecondOfDay() - this.startTime.toSecondOfDay();
        return seconds / 60;
    }
    
    public void add(Event obj){
        int remainingDurationDeafult = 60;    
        if (events.isEmpty()){
            obj.setInitTime(startTime);                               
        } else {
            int lastElement = events.size() - 1;
            Event last = events.get(lastElement);
            if (last instanceof Talk){
                Talk talkLast = (Talk)last;
                obj.setInitTime(talkLast.getInitTime().plusMinutes(talkLast.getDurationInMinutes()));
            }                        
        }
        if (!events.contains(obj) && hasRoomFor(obj)){
            events.add(obj);
            if (obj instanceof Talk){
                remainingDurationDeafult = ((Talk)obj).getDurationInMinutes();
            }
            this.remainingDuration -= remainingDurationDeafult;            
        } else {
            obj.setInitTime(null);
            throw new IllegalStateException("Event can not add in session already or over time");
        }         
    }
    
    // check if the talk can be accommodated in the current slot.
    public boolean hasRoomFor(Event obj) {        
        if (obj instanceof Talk){            
            return remainingDuration >= ((Talk)obj).getDurationInMinutes();
        }
        return true;
    }

    public LocalTime getStartTime() {
        return startTime;
    }

    public LocalTime getEndTime() {
        return endTime;
    }

    public List<Event> getEvents() {
        return events;
    }

    public void setEvents(List<Event> events) {
        this.events = events;
    }

    @Override
    public String toString() {
        return "Session{" + 
                "\n\tstartTime=" + startTime +
                ",\n\tendTime=" + endTime +
                ",\n\tevents=" + events + '}';
    }


    @Override
    public int hashCode() {
        int hash = 5;
        hash = 19 * hash + Objects.hashCode(this.startTime);       
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Session other = (Session) obj;
        if (!Objects.equals(this.startTime, other.startTime)) {
            return false;
        }        
        return true;
    }
    
    
    
}
