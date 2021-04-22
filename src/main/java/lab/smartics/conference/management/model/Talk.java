/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lab.smartics.conference.management.model;

import java.time.LocalTime;
import java.util.Objects;

/**
 *
 * @author wduck
 */
public class Talk extends Event{
    
    private int durationInMinutes;   

    public Talk(String title, int durationInMinutes) {
        super(title);
        this.durationInMinutes = durationInMinutes;
    }
    
    public Talk(String title, int durationInMinutes, LocalTime time) {
        super(title, time);
        this.durationInMinutes = durationInMinutes;
        
    }

    public int getDurationInMinutes() {
        return durationInMinutes;
    }

    public void setDurationInMinutes(int durationInMinutes) {
        this.durationInMinutes = durationInMinutes;
    }

    @Override
    public void validate(){        
        super.validate();        
        if (durationInMinutes > 60 || durationInMinutes < 6){
            throw new IllegalStateException(Talk.class.getSimpleName() + ": " + durationInMinutes + " should be between 6 and 60 minutes");
        }
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 53 * hash + Objects.hashCode(this.durationInMinutes);
        hash = 53 * hash + Objects.hashCode(getTitle());
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
        final Talk other = (Talk) obj;
        if (this.durationInMinutes != other.durationInMinutes) {
            return false;
        }
        return !(getTitle() == null ? other.getTitle() != null : !getTitle().equals(other.getTitle()));
    }

    
    
    
}
