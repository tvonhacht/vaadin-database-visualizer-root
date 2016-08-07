package org.vaadin.addons.model;

public class Position {

    private Long x;
    private Long y;
    
    public Position() {}
    
    public Position(Long x, Long y) {
    	setX(x);
    	setY(y);
	}

    public Long getX() {
        return this.x;
    }

    public void setX(final Long x) {
        this.x = x;
    }

    public Long getY() {
        return this.y;
    }

    public void setY(final Long y) {
        this.y = y;
    }

}