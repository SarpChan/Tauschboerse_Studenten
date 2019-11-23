package de.hsrm.mi.swtpro.backend.model;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Builder
@AllArgsConstructor
public class Curriculum {
    
    @Id
    @Getter @Setter
    @GeneratedValue
    private long id;

    @Getter @Setter
    private int term;

    @Getter @Setter
    @JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
    @ManyToOne
    private ExamRegulation examRegulation;

    @Getter @Setter
    @JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
    @ManyToMany
    private Set<Module> modules;

    @Deprecated
    public Curriculum(ExamRegulation examRegulation, int term) {
        this.examRegulation = examRegulation;
        this.term = term;
        this.modules = new HashSet<Module>();
    }

    /**
     * Constructor with Builder pattern
     * @param builder
     */
    @Deprecated
    private Curriculum(Builder builder) {
        this.examRegulation = builder.examRegulation;
        this.term = builder.term;
        this.modules = builder.modules;
    }

    /**
     * Builder class 
     * defines the parameters of the Curriculum object to be built
     */
    @Deprecated
    public static class Builder {
        private int term;
        private ExamRegulation examRegulation;
        private Set<Module> modules;

        public Builder(ExamRegulation examRegulation) {
            this.examRegulation = examRegulation;
            this.modules = new HashSet<Module>();
        }

        public Builder forTerm(int term) {
            this.term = term;
            return this;
        }

        public Builder hasModules(HashSet<Module> modules) {
            this.modules = modules;
            return this;
        }

        public Builder hasModule(Module module) {
            this.modules.add(module);
            return this;
        }

        public Curriculum build() {
            return new Curriculum(this);
        }
    }
}