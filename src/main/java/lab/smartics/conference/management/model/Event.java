/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lab.smartics.conference.management.model;

import java.io.Serializable;
import java.time.LocalTime;
import java.util.Objects;

/**
 *
 * @author wduck
 */
public class Event implements Serializable{
        
    private String title;
    private LocalTime initTime;   

    public Event(String title) {
        this.title = title;
    }

    public Event(String title, LocalTime initTime) {
        this(title);
        this.initTime = initTime;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public LocalTime getInitTime() {
        return initTime;
    }

    public void setInitTime(LocalTime initTime) {
        this.initTime = initTime;
    }
    
    public void validate(){        
        if (title != null && !title.isBlank()){
            boolean hasDigit = title.matches(".*\\d+.*");
            if (hasDigit){
                throw new IllegalStateException(Event.class.getSimpleName() + ": " + title + " contain digits");
            }
        } else {
            throw new IllegalStateException(Event.class.getSimpleName() + ": " + "title is null or empty");            
        } 
    
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 67 * hash + Objects.hashCode(this.initTime);
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
        final Event other = (Event) obj;
        if (!Objects.equals(this.initTime, other.initTime)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Event{" + "title=" + title + ", initTime=" + initTime + '}';
    }

    
}
