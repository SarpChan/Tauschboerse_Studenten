package de.hsrm.mi.swtpro.backend.model;

import java.util.HashSet;
import java.util.Set;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;

@Entity
public class Curriculum {
    @Id
    @GeneratedValue
    private long id;
    private int term;
    
    @ManyToOne
    private ExamRegulation examRegulation;

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
    private Curriculum(Builder builder) {
        this.examRegulation = builder.examRegulation;
        this.term = builder.term;
        this.modules = builder.modules;
    }

    /**
     * Builder class 
     * defines the parameters of the Curriculum object to be built
     */
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

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public int getTerm() {
        return term;
    }

    public void setTerm(int term) {
        this.term = term;
    }

    public ExamRegulation getExamRegulation() {
        return examRegulation;
    }

    public void setExamRegulation(ExamRegulation examRegulation) {
        this.examRegulation = examRegulation;
    }

    public Set<Module> getModules() {
        return modules;
    }

    public void setModules(Set<Module> modules) {
        this.modules = modules;
    }
}