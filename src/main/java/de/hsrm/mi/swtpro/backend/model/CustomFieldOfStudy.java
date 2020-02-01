package de.hsrm.mi.swtpro.backend.model;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;
import java.util.stream.Collectors;

/**
 * A field of study has a name and multiple studyPrograms
 * Many universities can have the same studyprogram
 * The CustomFieldOfStudy is used to send information to Clients
 */
@Builder
public class CustomFieldOfStudy {
    @Getter
    @Setter
    private long id;

    @Getter
    @Setter
    private String title;

    @Getter
    @Setter
    private Set<CustomStudyProgram> studyPrograms;

    /**
     * Creates a CustomFieldOfStudy from a FieldOfStudy
     * @param fieldOfStudy the FieldOfStudy to create a CustomFieldOfStudy from
     * @return CustomFieldOfStudy
     */
    public static CustomFieldOfStudy fromOriginal(FieldOfStudy fieldOfStudy) {
        return CustomFieldOfStudy.builder()
                .id(fieldOfStudy.getId())
                .title(fieldOfStudy.getTitle())
                .studyPrograms(fieldOfStudy.getStudyPrograms().stream()
                        .map(CustomStudyProgram::fromOriginal).collect(Collectors.toSet()))
                .build();
    }
}
