package de.hsrm.mi.swtpro.backend.model;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;
import java.util.stream.Collectors;

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

    public static CustomFieldOfStudy fromOriginal(FieldOfStudy fieldOfStudy) {
        return CustomFieldOfStudy.builder()
                .id(fieldOfStudy.getId())
                .title(fieldOfStudy.getTitle())
                .studyPrograms(fieldOfStudy.getStudyPrograms().stream()
                        .map(CustomStudyProgram::fromOriginal).collect(Collectors.toSet()))
                .build();
    }
}
