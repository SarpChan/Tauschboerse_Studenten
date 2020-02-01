package de.hsrm.mi.swtpro.backend.model;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

/**
 * Each study program has one or more exam regulations
 * The exam regulation defines the curriculum, cp for each modules
 * and additional rules on examination
 * The CustomExamRegulation is used to send information to Clients
 */
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

    /**
     * Creates a CustomExamRegulation from a ExamRegulation
     * @param examRegulation the ExamRegulation to create a CustomExamRegulation from
     * @return CustomExamRegulation
     */
    static CustomExamRegulation fromOriginal(ExamRegulation examRegulation) {
        return CustomExamRegulation.builder()
                .id(examRegulation.getId())
                .date(examRegulation.getDate())
                .maxTerms(examRegulation.getCurriculums().toArray(new Curriculum[0])[0].getMaxTerms())
                .build();
    }
}
