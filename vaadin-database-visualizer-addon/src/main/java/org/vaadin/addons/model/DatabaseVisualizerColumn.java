package org.vaadin.addons.model;

public class DatabaseVisualizerColumn extends NamedObjectImpl {

    private Long orderId;
    private Long columnId;
    private String keyType;
    private Boolean nullable;
    private String type;
    private String defaultValue;

    public Long getOrderId() {
        return this.orderId;
    }

    public void setOrderId(final Long orderId) {
        this.orderId = orderId;
    }

    public Long getColumnId() {
        return this.columnId;
    }

    public void setColumnId(final Long columnId) {
        this.columnId = columnId;
    }

    public String getKeyType() {
        return this.keyType;
    }

    public void setKeyType(final String keyType) {
        this.keyType = keyType;
    }

    public Boolean getNullable() {
        return this.nullable;
    }

    public void setNullable(final Boolean nullable) {
        this.nullable = nullable;
    }

    public String getType() {
        return this.type;
    }

    public void setType(final String type) {
        this.type = type;
    }

    public String getDefaultValue() {
        return this.defaultValue;
    }

    public void setDefaultValue(final String defaultValue) {
        this.defaultValue = defaultValue;
    }
}
