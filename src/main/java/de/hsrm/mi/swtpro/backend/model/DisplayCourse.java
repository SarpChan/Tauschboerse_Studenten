package de.hsrm.mi.swtpro.backend.model;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Set;
import lombok.*;

/**
 * Used to send information about a course to clients
 */
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DisplayCourse {
    @Id
    @Getter @Setter
    @NotNull
    private long id;

    @NotEmpty
    @Getter @Setter
    private String title;

    @Getter @Setter
    @NotNull
    private User owner;

    @Singular("courseComponent")
    @Getter @Setter
    private Set<DisplayCourseComponent> courseComponents;
}
