package de.hsrm.mi.swtpro.backend.model;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Builder
public class CustomExamRegulation {

    @Getter
    @Setter
    private long id;

    @Getter
    @Setter
    private Date date;

    @Getter
    @Setter
    private int maxTerms;

    static CustomExamRegulation fromOriginal(ExamRegulation examRegulation) {
        return CustomExamRegulation.builder()
                .id(examRegulation.getId())
                .date(examRegulation.getDate())
                .maxTerms(examRegulation.getCurriculums().toArray(new Curriculum[0])[0].getMaxTerms())
                .build();
    }
}
