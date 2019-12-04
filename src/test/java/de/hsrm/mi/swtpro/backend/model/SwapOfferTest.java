package de.hsrm.mi.swtpro.backend.model;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.sql.Timestamp;

import org.junit.Before;
import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class SwapOfferTest {
    private SwapOffer swapOffer;
    private Student student;
    private Group fromGroup;
    private Group toGroup;

    @Before
    public void setup(){
        student = Student.builder()
        .enrolementNumber(17)
        .mail("vorname.nachname@student.hs-rm.de")
        .build();
        
        fromGroup = Group.builder()
        .groupChar('A')
        .slots(17)
        .build();

        toGroup = Group.builder()
        .groupChar('B')
        .slots(17)
        .build();

        swapOffer = SwapOffer.builder()
        .date(new Timestamp(System.currentTimeMillis()))
        .student(student)
        .fromGroup(fromGroup)
        .toGroup(toGroup)
        .build();
    }

    @Test
    public void whenGetStudent_thenReturnStudent(){
        assertEquals(student, swapOffer.getStudent());
    }
    @Test
    public void whenSetStudent_thenSaveStudent(){
        Student newStudent = Student.builder()
        .enrolementNumber(20)
        .mail("vorname.nachname@student.hs-rm.de")
        .build();
        swapOffer.setStudent(newStudent);
        assertEquals(newStudent, swapOffer.getStudent());
    }
    @Test
    public void whenGetFromGroup_thenReturnFromGroup(){
        assertEquals(fromGroup, swapOffer.getFromGroup());
    }
    @Test
    public void whenSetFromGroup_thenSaveFromGroup(){
        Group newGroup = Group.builder()
        .groupChar('B')
        .slots(22)
        .build();
        swapOffer.setFromGroup(newGroup);
        assertEquals(newGroup, swapOffer.getFromGroup());
    }
    @Test
    public void whenGetToGroup_thenReturnToGroup(){
        assertEquals(toGroup, swapOffer.getToGroup());
    }
    @Test
    public void whenSetToGroup_thenSaveToGroup(){
        Group newGroup = Group.builder()
        .groupChar('B')
        .slots(22)
        .build();
        swapOffer.setToGroup(newGroup);
        assertEquals(newGroup, swapOffer.getToGroup());
    }
    
}