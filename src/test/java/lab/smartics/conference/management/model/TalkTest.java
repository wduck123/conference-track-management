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
public class TalkTest {
    
    public TalkTest() {
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
    public void testValidateDurationOk() {
        System.out.println("validate Duration Ok");
        Talk instance = new Talk("Microservices", 45, LocalTime.of(9, 00));
        instance.validate();
        assertEquals("Microservices", instance.getTitle(), "OK test");
    }
    
    @Test    
    public void testValidateDurationHours(){
        System.out.println("validate Duration Hours");
        Talk instance = new Talk("Microservices", 61, LocalTime.of(9, 00));
        Exception exception = assertThrows(
                IllegalStateException.class, 
                () -> {
                    instance.validate();
                }                
        );
        String expectedMessage = "Talk: " + instance.getDurationInMinutes() + " should be between 6 and 60 minutes";
        assertEquals(expectedMessage, exception.getMessage());
    }
    
    @Test    
    public void testValidateDurationTimeInsignificant(){
        System.out.println("validate Duration Time Insignificant");
        Talk instance = new Talk("Microservices", 5, LocalTime.of(9, 00));
        Exception exception = assertThrows(
                IllegalStateException.class, 
                () -> {
                    instance.validate();
                }                
        );
        String expectedMessage = "Talk: " + instance.getDurationInMinutes() + " should be between 6 and 60 minutes";
        assertEquals(expectedMessage, exception.getMessage());
    }

    
    /**
     * Test of toString method, of class Event.
     */
    @Test
    public void testToString() {
        System.out.println("toString");
        Talk instance = new Talk("Microservices", 45, LocalTime.now());
        String expResult = instance.toString();
        String result = instance.toString();
        assertEquals(expResult, result);        
    }
    
}
