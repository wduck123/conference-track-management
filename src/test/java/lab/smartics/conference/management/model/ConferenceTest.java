/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lab.smartics.conference.management.model;

import java.time.LocalDate;
import java.time.LocalTime;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * @author wduck
 */
public class ConferenceTest {
    
    public ConferenceTest() {
    }
    
    @BeforeAll
    public static void setUpClass() {
    }
    
    @AfterAll
    public static void tearDownClass() {
    }
    
    @BeforeEach
    public void setUp() {
    }
    
    @AfterEach
    public void tearDown() {
    }

    
    @Test
    public void testAddTracks() {        
        System.out.println("validate add Tracks");
        Conference instance = new Conference("Conference Test");
        Track track1 = new Track(1, null, LocalDate.now());
        instance.add(track1);
        Track track2 = new Track(2, null, LocalDate.now());
        instance.add(track2);
        System.out.println(instance.toString());
        assertEquals(2, instance.getTracks().size(), "Size not equals");
    }
    
    
    
    @Test
    public void testAddTrackRepeat() {
        System.out.println("validate add Tracks repeat");
        Conference instance = new Conference("Conference Test");
        Track track = new Track(1, null, LocalDate.now());
        instance.add(track);
        
        Exception exception = assertThrows(
                IllegalStateException.class, 
                () -> {
                    instance.add(track);
                }                
        );
        String expectedMessage = "Task " + track.getName() + " already exists in Conference!!";
        assertEquals(expectedMessage, exception.getMessage());
    }

    
}
