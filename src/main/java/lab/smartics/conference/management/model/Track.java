/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lab.smartics.conference.management.model;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Random;

/**
 *
 * @author wduck
 */
public class Track implements Serializable{
    

    private Integer id;  
    private String name;
    private LocalDate date;
    
    private List<Session> sessions;
    
    public Track() {
        this(null, null, null);
    }

    public Track(Integer id, String name, LocalDate date) {        
        this.id = (id == null) ? generateRandomId() : id ;
        this.name = (name != null && !name.isEmpty() && !name.isBlank()) ? name : "Track " + this.id;
        this.date = (date != null) ? date : LocalDate.now();
        this.sessions = new ArrayList<>();
    }
    
    public void add(Session session){
        if (!this.sessions.contains(session)){
            this.sessions.add(session);
        } else {
            throw new IllegalStateException("Session " + session.getStartTime() + " already exists!!");
        }
    }
    
    private int generateRandomId(){
        Random random = new Random(100);
        return random.nextInt();
    }

    public List<Session> getSessions() {
        return sessions;
    }

    public void setSessions(List<Session> sessions) {
        this.sessions = sessions;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 97 * hash + Objects.hashCode(this.id);
        hash = 97 * hash + Objects.hashCode(this.name);
        hash = 97 * hash + Objects.hashCode(this.date);
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
        final Track other = (Track) obj;
        if (!Objects.equals(this.name, other.name)) {
            return false;
        }
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        if (!Objects.equals(this.date, other.date)) {
            return false;
        }
        return true;
    }

    

    @Override
    public String toString() {
        return "Track{" + "id=" + id + ", name=" + name + ", startDate=" + date + ", sessions=" + sessions + '}';
    }

    
}
