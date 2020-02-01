package de.hsrm.mi.swtpro.backend.model;

import lombok.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * Object that will be serialized and send to a client
 * and contains information about a module and its course components
 */
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ModuleSelectionItem {

    @Getter @Setter
    @NotNull
    private long id;

    @Getter @Setter
    @NotEmpty(message = "Modulname fehlt")
    private String title;

    @Getter @Setter
    @Min(value = 0)
    private int creditPoints;

    @Getter @Setter
    @NotNull
    private int semester;
    // in welchem semester ist das modul im curiculum vorgesehen

    @Getter @Setter
    private List<CourseType>moduleTypes;

    @Getter @Setter
    private List<TimetableModule> timetableModules ;

}
