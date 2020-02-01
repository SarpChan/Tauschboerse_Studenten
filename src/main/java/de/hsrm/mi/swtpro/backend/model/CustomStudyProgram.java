package de.hsrm.mi.swtpro.backend.model;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;
import java.util.stream.Collectors;

/**
 * Course of Studies, defines the degree
 * The CustomStudyProgram is used to send information to Clients
 */
@Builder
public class CustomStudyProgram {

    @Getter @Setter
    private long id;

    @Getter @Setter
    private String title;

    @Getter @Setter
    private String degree;

    @Getter @Setter
    private Set<CustomExamRegulation> examRegulations;

    /**
     * Creates a CustomStudyProgram from a StudyProgram
     * @param studyProgram the StudyProgram to create a CustomStudyProgram from
     * @return CustomStudyProgram
     */
    static CustomStudyProgram fromOriginal(StudyProgram studyProgram) {
        return CustomStudyProgram.builder()
                .id(studyProgram.getId())
                .title(studyProgram.getTitle())
                .degree(studyProgram.getDegree())
                .examRegulations(studyProgram.getExamRegulations().stream()
                        .map(CustomExamRegulation::fromOriginal).collect(Collectors.toSet()))
                .build();
    }
}
