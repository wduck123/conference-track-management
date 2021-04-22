/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lab.smartics.conference.management.model;

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
public class SessionTest {
    
    public SessionTest() {
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
    public void testAddFirst() {
        System.out.println("validate add when event is empty");
        Session instance = new Session(LocalTime.of(9, 0), LocalTime.of(13, 0));
        Talk talk = new Talk("Microservices", 45);
        instance.add(talk);
        System.out.println(instance.toString());
        assertEquals(1, instance.getEvents().size(), "OK test");
    }
    
    @Test
    public void testAddTask() {
        System.out.println("validate add when event is not empty");
        Session instance = new Session(LocalTime.of(9, 0), LocalTime.of(13, 0));
        Talk talk = new Talk("Microservices Concept", 45);
        instance.add(talk);
        Talk talk2 = new Talk("Microservices with Quarkus", 45);
        instance.add(talk2);
        System.out.println(instance.toString());
        assertEquals(2, instance.getEvents().size(), "OK test");
    }
    
    @Test
    public void testAddRepeatEvent() {
        System.out.println("validate add when event already");
        Session instance = new Session(LocalTime.of(9, 0), LocalTime.of(13, 0));        
        Event event = new Event("Microservices");
        instance.add(event);
        Event event2 = new Event("Microservices", LocalTime.of(9, 0));
        
        Exception exception = assertThrows(
                IllegalStateException.class, 
                () -> {
                    instance.add(event2);
                }                
        );
        System.out.println(instance.toString());
        String expectedMessage = "Event can not add in session already or over time";
        assertEquals(expectedMessage, exception.getMessage());
    }
    
    @Test
    public void testAddRepeatTask() {
        System.out.println("validate add when task already");
        Session instance = new Session(LocalTime.of(9, 0), LocalTime.of(13, 0));
        Event event = new Talk("Microservices", 45);        
        instance.add(event);
        Event event2 = new Talk("Microservices", 45, LocalTime.of(9, 0));
        
        Exception exception = assertThrows(
            IllegalStateException.class, 
            () -> {
                instance.add(event2);
            }                
        );
        System.out.println(instance.toString());
        String expectedMessage = "Event can not add in session already or over time";
        assertEquals(expectedMessage, exception.getMessage());
    }
    
    

    /**
     * Test of equals method, of class Event.
     */
    @Test
    public void testEquals() {
        System.out.println("equals");
        LocalTime timeNow = LocalTime.now();
        Object obj = new Session(timeNow, LocalTime.of(13, 0));
        Session instance = new Session(timeNow, LocalTime.of(13, 0));
        boolean expResult = true;
        boolean result = instance.equals(obj);
        assertEquals(expResult, result);        
    }

    /**
     * Test of toString method, of class Event.
     */
    @Test
    public void testToString() {
        System.out.println("toString");
        Session instance = new Session(LocalTime.now(), LocalTime.of(13, 0));
        String expResult = instance.toString();
        String result = instance.toString();
        assertEquals(expResult, result);        
    }
    
}
