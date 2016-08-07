package org.vaadin.addons.model;

import java.util.ArrayList;
import java.util.List;

public class DatabaseVisualizerDatabase {
    private Long id;
    private List<DatabaseVisualizerSchema> schemas;

    public Long getId() {
        return this.id;
    }

    public void setId(final Long id) {
        this.id = id;
    }

    public List<DatabaseVisualizerSchema> getSchemas() {
        if (this.schemas == null) {
            this.schemas = new ArrayList<>();
        }
        return this.schemas;
    }

    public void setSchemas(final List<DatabaseVisualizerSchema> schemas) {
        this.schemas = schemas;
    }

    public void addSchema(final DatabaseVisualizerSchema schema) {
        getSchemas().add(schema);
    }
}
