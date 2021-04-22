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
public class EventTest {
    
    public EventTest() {
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

    

    /**
     * Test of validate method, of class Event.
     */
    @Test
    public void testValidateTitleOk() {
        System.out.println("validate Title Ok");
        Event instance = new Event("Microservices");
        instance.validate();
        assertEquals("Microservices", instance.getTitle(), "OK test");
    }
    
    @Test    
    public void testValidateTitleEmpty(){
        System.out.println("validate Title Empty");
        Event instance = new Event("");
        Exception exception = assertThrows(
                IllegalStateException.class, 
                () -> {
                    instance.validate();
                }                
        );
        String expectedMessage = "Event: title is null or empty";
        assertEquals(expectedMessage, exception.getMessage());
    }
    
    @Test    
    public void testValidateTitleNull(){
        System.out.println("validate Title Null");
        Event instance = new Event(null);
        Exception exception = assertThrows(
                IllegalStateException.class, 
                () -> {
                    instance.validate();
                }                
        );
        String expectedMessage = "Event: title is null or empty";
        assertEquals(expectedMessage, exception.getMessage());
    }
    
    @Test    
    public void testValidateTitleBlank(){
        System.out.println("validate Title Blank");
        Event instance = new Event("  ");
        Exception exception = assertThrows(
                IllegalStateException.class, 
                () -> {
                    instance.validate();
                }                
        );
        String expectedMessage = "Event: title is null or empty";
        assertEquals(expectedMessage, exception.getMessage());
    }
    
    @Test    
    public void testValidateTitleDigitPresent(){
        System.out.println("validate Title digit present");
        Event instance = new Event("M1cr0S3rvic3s");
        Exception exception = assertThrows(
                IllegalStateException.class, 
                () -> {
                    instance.validate();
                }                
        );
        String expectedMessage = "Event: " + instance.getTitle() + " contain digits";
        assertEquals(expectedMessage, exception.getMessage());
    }

    /**
     * Test of equals method, of class Event.
     */
    @Test
    public void testEquals() {
        System.out.println("equals");
        LocalTime timeNow = LocalTime.now();
        Object obj = new Event("Microservices", timeNow);
        Event instance = new Event("Microservices", timeNow);;
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
        Event instance = new Event("Microservices", LocalTime.now());
        String expResult = instance.toString();
        String result = instance.toString();
        assertEquals(expResult, result);        
    }
    
}
