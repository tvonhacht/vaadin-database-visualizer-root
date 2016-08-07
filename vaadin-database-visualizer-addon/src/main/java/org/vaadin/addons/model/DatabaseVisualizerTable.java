package org.vaadin.addons.model;

import java.util.ArrayList;
import java.util.List;

public class DatabaseVisualizerTable extends NamedObjectImpl {

    private Long tableId;
    private Long left = 0L;
    private Long top = 0L;
    private List<DatabaseVisualizerColumn> columns;
    private List<DatabaseVisualizerConstraint> constraints;

    public Long getTableId() {
        return this.tableId;
    }

    public void setTableId(final Long tableId) {
        this.tableId = tableId;
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

    public List<DatabaseVisualizerColumn> getColumns() {
        if (this.columns == null) {
            this.columns = new ArrayList<>();
        }
        return this.columns;
    }

    public void setColumns(final List<DatabaseVisualizerColumn> columns) {
        this.columns = columns;
    }

    public List<DatabaseVisualizerConstraint> getConstraints() {
        if (this.constraints == null) {
            this.constraints = new ArrayList<>();
        }
        return this.constraints;
    }

    public void setConstraints(final List<DatabaseVisualizerConstraint> constraints) {
        this.constraints = constraints;
    }

    public void addColumn(final DatabaseVisualizerColumn column) {
        getColumns().add(column);
    }

    public void addConstraint(final DatabaseVisualizerConstraint constraint) {
        getConstraints().add(constraint);
    }

}
