package de.hsrm.mi.swtpro.backend.model;

import java.sql.Date;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
class ExamRegulation {
    @Id@GeneratedValue
    private long id;
    private Date date;
    private StudyProgram studyProgram;

    
}
