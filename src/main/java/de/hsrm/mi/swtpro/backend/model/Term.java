package de.hsrm.mi.swtpro.backend.model;

import java.sql.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * Academic Term
 * the time interval in which an education facility holds classes
 * most common cycles are 2 terms per year, known as semester
 */
@Entity
class Term {
    
    @Id
    @GeneratedValue
    private long id;
    private Date start;
    private Date end;
    private int period;


    public Term(Date start, Date end, int period) {
        this.start = start;
        this.end = end;
        this.period = period;
    }


    public Date getStart() {
        return start;
    }

    public void setStart(Date start) {
        this.start = start;
    }

    public Date getEnd() {
        return end;
    }

    public void setEnd(Date end) {
        this.end = end;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public int getPeriod() {
        return period;
    }

    public void setPeriod(int period) {
        this.period = period;
    }
}