package org.vaadin.addons.model;

import java.util.List;

public class DatabaseVisualizerConstraint extends NamedObjectImpl {

    private String schema;
    private String table;
    private List<String> columns;
    private String rSchema;
    private String rTable;
    private List<String> rColumns;
    private Boolean isConnectedLeft;
    private Boolean isConnectedLeftRef;
    private List<DatabaseVisualizerConstraintPosition> positions;

    public String getSchema() {
        return this.schema;
    }

    public void setSchema(final String schema) {
        this.schema = schema;
    }

    public String getTable() {
        return this.table;
    }

    public void setTable(final String table) {
        this.table = table;
    }

    public List<String> getColumns() {
        return this.columns;
    }

    public void setColumns(final List<String> columns) {
        this.columns = columns;
    }

    public String getRSchema() {
        return this.rSchema;
    }

    public void setRSchema(final String rSchema) {
        this.rSchema = rSchema;
    }

    public String getRTable() {
        return this.rTable;
    }

    public void setRTable(final String rTable) {
        this.rTable = rTable;
    }

    public List<String> getRColumns() {
        return this.rColumns;
    }

    public void setRColumns(final List<String> rColumns) {
        this.rColumns = rColumns;
    }

    public List<DatabaseVisualizerConstraintPosition> getPositions() {
        return this.positions;
    }

    public void setPositions(final List<DatabaseVisualizerConstraintPosition> positions) {
        this.positions = positions;
    }

    public Boolean getIsConnectedLeft() {
        return this.isConnectedLeft;
    }

    public void setIsConnectedLeft(final Boolean isConnectedLeft) {
        this.isConnectedLeft = isConnectedLeft;
    }

    public Boolean getIsConnectedLeftRef() {
        return this.isConnectedLeftRef;
    }

    public void setIsConnectedLeftRef(final Boolean isConnectedLeftRef) {
        this.isConnectedLeftRef = isConnectedLeftRef;
    }

}
