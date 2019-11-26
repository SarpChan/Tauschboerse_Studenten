package de.hsrm.mi.swtpro.backend.model;

import org.junit.Before;
import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.Assert.assertEquals;

@SpringBootTest
public class StudentPriorizesGroupTest {

    private StudentPriorizesGroup studentPriorizesGroup;

    @Before
    public void setUp(){

        Group group = Group.builder()
                .id(46465)
                .build();

        Student student = Student.builder()
                .enrolementNumber(65253)
                .build();

        studentPriorizesGroup = StudentPriorizesGroup.builder()
                .id(17)
                .student(student)
                .group(group)
                .priority(1)
                .build();
    }

    @Test
    public void whenGetId_thenReturnId(){
        assertEquals(17,studentPriorizesGroup.getId());
    }

    @Test
    public void whenGetStudent_thenReturnStudent(){
        assertEquals(65253,studentPriorizesGroup.getStudent().getEnrolementNumber());
    }

    @Test
    public void whenGetGroup_thenReturnGroup(){
        assertEquals(46465,studentPriorizesGroup.getGroup().getId());
    }

    @Test
    public void whenGetPriority_thenReturnPriority(){
        assertEquals(1,studentPriorizesGroup.getPriority());
    }

    @Test
    public void whenSetId_thenSaveId(){
        studentPriorizesGroup.setId(65);
        assertEquals(65,studentPriorizesGroup.getId());
    }

    @Test
    public void whenSetStudent_thenReturnStudent(){
        Student student = Student.builder()
                .enrolementNumber(1711717)
                .build();
        studentPriorizesGroup.setStudent(student);

        assertEquals(1711717,studentPriorizesGroup.getStudent().getEnrolementNumber());
    }

    @Test
    public void whenSetGroup_thenSaveGroup(){
        Group group = Group.builder()
                .id(77777)
                .build();
        studentPriorizesGroup.setGroup(group);

        assertEquals(77777,studentPriorizesGroup.getGroup().getId());
    }

    @Test
    public void whenSetPriority_thenSavePriority(){
       studentPriorizesGroup.setPriority(2);
       assertEquals(2, studentPriorizesGroup.getPriority());
    }
}
