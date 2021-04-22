/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lab.smartics.conference.management.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 *
 * @author wduck
 */
public class Conference implements Serializable{
    
    private List<Track> tracks;
    private String title;
    
    public Conference(){
        this.tracks = new ArrayList();
    }

    public Conference(String title) {
        this();
        this.title = title;
    }
    
    public void add(Track instance){
        if (!this.tracks.contains(instance)){
            this.tracks.add(instance);
        } else {
            throw new IllegalStateException("Task " + instance.getName() + " already exists in Conference!!");
        }
    }

    public List<Track> getTracks() {
        return tracks;
    }

    public void setTracks(List<Track> tracks) {
        this.tracks = tracks;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 97 * hash + Objects.hashCode(this.title);
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
        final Conference other = (Conference) obj;
        return Objects.equals(this.title, other.title);
    }

    @Override
    public String toString() {
        return "Conference{" + "title=" + title + '}';
    }
    
    
    
    
    
}
