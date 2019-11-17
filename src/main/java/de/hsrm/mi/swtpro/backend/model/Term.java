package de.hsrm.mi.swtpro.backend.model;

import java.io.Serializable;
import java.sql.Date;

/**
 * Academic Term
 * the time interval in which an education facility holds classes
 * most common cycles are 2 terms per year, known as semester
 */
public class Term implements Serializable {
    private int year;
    private String name;
    private Date start;
    private Date end;

    public Term(int year, String name, Date start, Date end) {
        this.year = year;
        this.name = name;
        this.start = start;
        this.end = end;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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
}
