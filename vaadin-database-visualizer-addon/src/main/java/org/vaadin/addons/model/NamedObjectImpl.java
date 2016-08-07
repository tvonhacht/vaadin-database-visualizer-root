package org.vaadin.addons.model;

public class NamedObjectImpl extends Entity implements NamedObject {

    private String name;

    public NamedObjectImpl() {
        super();
    }

    public NamedObjectImpl(final Long id, final String name) {
        super();
        setId(id);
        this.name = name;
    }

    @Override
    public String getName() {
        return this.name;
    }

    public void setName(final String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return getName();
    }
}
