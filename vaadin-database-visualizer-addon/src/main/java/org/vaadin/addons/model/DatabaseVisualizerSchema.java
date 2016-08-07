package org.vaadin.addons.model;

import java.util.ArrayList;
import java.util.List;

public class DatabaseVisualizerSchema extends NamedObjectImpl {

    private Long schemaId;
    private Long left = 0L;
    private Long top = 0L;
    private Long width = 200L;
    private Long height = 200L;
    private List<DatabaseVisualizerTable> tables;

    public Long getSchemaId() {
        return this.schemaId;
    }

    public void setSchemaId(final Long schemaId) {
        this.schemaId = schemaId;
    }

    public Long getLeft() {
        return this.left;
    }

    public void setLeft(final Long left) {
        this.left = left;
    }

    public Long getTop() {
        return this.top;
    }

    public void setTop(final Long top) {
        this.top = top;
    }

    public Long getWidth() {
        return this.width;
    }

    public void setWidth(final Long width) {
        this.width = width;
    }

    public Long getHeight() {
        return this.height;
    }

    public void setHeight(final Long height) {
        this.height = height;
    }

    public List<DatabaseVisualizerTable> getTables() {
        if (this.tables == null) {
            this.tables = new ArrayList<>();
        }
        return this.tables;
    }

    public void setTables(final List<DatabaseVisualizerTable> tables) {
        this.tables = tables;
    }

    public void addTable(final DatabaseVisualizerTable table) {
        getTables().add(table);
    }
}
