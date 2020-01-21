package de.hsrm.mi.swtpro.backend.model;

import org.junit.Before;
import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.Assert.assertEquals;

@SpringBootTest
public class StudentPrioritizesGroupTest {

    private StudentPrioritizesGroup studentPrioritizesGroup;

    @Before
    public void setUp(){

        Group group = Group.builder()
                .id(46465)
                .build();

        Student student = Student.builder()
                .enrollmentNumber(65253)
                .build();

        studentPrioritizesGroup = StudentPrioritizesGroup.builder()
                .id(17)
                .student(student)
                .group(group)
                .priority(1)
                .build();
    }

    @Test
    public void whenGetId_thenReturnId(){
        assertEquals(17, studentPrioritizesGroup.getId());
    }

    @Test
    public void whenGetStudent_thenReturnStudent(){
        assertEquals(65253, studentPrioritizesGroup.getStudent().getEnrollmentNumber());
    }

    @Test
    public void whenGetGroup_thenReturnGroup(){
        assertEquals(46465, studentPrioritizesGroup.getGroup().getId());
    }

    @Test
    public void whenGetPriority_thenReturnPriority(){
        assertEquals(1, studentPrioritizesGroup.getPriority());
    }

    @Test
    public void whenSetId_thenSaveId(){
        studentPrioritizesGroup.setId(65);
        assertEquals(65, studentPrioritizesGroup.getId());
    }

    @Test
    public void whenSetStudent_thenReturnStudent(){
        Student student = Student.builder()
                .enrollmentNumber(1711717)
                .build();
        studentPrioritizesGroup.setStudent(student);

        assertEquals(1711717, studentPrioritizesGroup.getStudent().getEnrollmentNumber());
    }

    @Test
    public void whenSetGroup_thenSaveGroup(){
        Group group = Group.builder()
                .id(77777)
                .build();
        studentPrioritizesGroup.setGroup(group);

        assertEquals(77777, studentPrioritizesGroup.getGroup().getId());
    }

    @Test
    public void whenSetPriority_thenSavePriority(){
       studentPrioritizesGroup.setPriority(2);
       assertEquals(2, studentPrioritizesGroup.getPriority());
    }
}
