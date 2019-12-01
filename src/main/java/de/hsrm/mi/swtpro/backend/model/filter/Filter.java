package de.hsrm.mi.swtpro.backend.model.filter;

import java.io.Serializable;

public class Filter<T> implements Serializable {
    String attribute;
    Comparator<T> comparator;
}
