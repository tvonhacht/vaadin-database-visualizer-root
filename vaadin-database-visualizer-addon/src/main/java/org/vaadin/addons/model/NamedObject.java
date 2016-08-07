package org.vaadin.addons.model;

import java.util.Comparator;

public interface NamedObject extends Identifiable {

    public static class NameComparator implements Comparator<NamedObject> {

        @Override
        public int compare(final NamedObject o1, final NamedObject o2) {
            return o1.getName().compareToIgnoreCase(o2.getName());
        }
    }

    /**
     * Returns the name of this object.
     * 
     * @return the name
     */
    String getName();

}
