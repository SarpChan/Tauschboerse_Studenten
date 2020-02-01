package de.hsrm.mi.swtpro.backend.model;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.util.List;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DisplayCourseComponent {
    @Id
    @Getter @Setter
    @NotNull
    private long id;

    @Getter @Setter
    @NotNull
    private long courseId;

    @Getter @Setter
    @NotNull
    private String title;

    @Getter @Setter
    @NotNull
    private String type;

    @Getter @Setter
    @NotNull
    private char myGroupChar;

    @Getter @Setter
    @Min(value = 0)
    private int creditPoints;

    @Getter @Setter
    private List<DisplayGroup> groups;
}
