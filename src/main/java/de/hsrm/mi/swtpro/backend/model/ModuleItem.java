package de.hsrm.mi.swtpro.backend.model;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Set;

@Entity
@NoArgsConstructor
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
@AllArgsConstructor
@Builder
public class ModuleItem {

    @Id
    @Getter @Setter
    @GeneratedValue
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

    @Singular("timetableModule")
    @Getter @Setter
    @OneToMany(mappedBy = "module")
    private Set<TimetableModule> timetableModules ;

}
