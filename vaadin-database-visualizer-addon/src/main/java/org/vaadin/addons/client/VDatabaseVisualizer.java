package org.vaadin.addons.client;

import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.Document;
import com.google.gwt.dom.client.Element;
import com.google.gwt.event.shared.EventHandler;
import com.google.gwt.user.client.DOM;
import com.google.gwt.user.client.ui.Widget;
import com.vaadin.client.VConsole;

import elemental.json.JsonObject;

// Extend any GWT Widget
public class VDatabaseVisualizer extends Widget {

	private static boolean injected = false;
	
	private boolean ready = false;

    private String jsonDatabase = "";
    
    private DatabaseVisualizerClientUpdateHandler updateSchemaHandler;
    private DatabaseVisualizerClientUpdateHandler updateTableHandler;
    private DatabaseVisualizerClientUpdateHandler updateConstraintHandler;

    /*
     * Constructor. Ensures that needed html templates are added and 
     * injects a <database-visualizer> element to the page. 
     */
    public VDatabaseVisualizer() {
        super();
        ensureHTMLImport();
        Element el = DOM.createElement("database-visualizer");
        setElement(el);
        addEventListeners(getElement());
    }

    /*
     * Injects the database-visualizer html template to page head section. 
     */
    private static void ensureHTMLImport() {
        if (!injected) {
            Element head = Document.get().getElementsByTagName("head")
                    .getItem(0);
            Element htmlImport = Document.get().createLinkElement();
            htmlImport.setAttribute("rel", "import");
            htmlImport.setAttribute("href", GWT.getModuleBaseForStaticFiles()
                    + "bower_components/database-visualizer/database-visualizer.html");
            head.appendChild(htmlImport);
            injected = true;
        }
    }
    
    /*
     * Sets all data and calls refresh to update database-visualizer image
     */
    public void setDatabase(String jsonDatabase) {
        this.jsonDatabase = jsonDatabase;
        if (this.ready) {
        	setDatabase(getElement(), this.jsonDatabase);
        } 
    }
    
    public final native void setDatabase(Element elem, String jsonDatabase) /*-{
    	setTimeout(
    		function(){
	    		elem.setAttribute("database", jsonDatabase);
    		},
    		200
    	);
     }-*/;
    
    public void setUpdateSchemaHandler(DatabaseVisualizerClientUpdateHandler updateSchemaHandler) {
    	this.updateSchemaHandler = updateSchemaHandler;
    }
    
    public void setUpdateTableHandler(DatabaseVisualizerClientUpdateHandler updateTableHandler) {
    	this.updateTableHandler = updateTableHandler;
    }
    
    public void setUpdateConstraintHandler(DatabaseVisualizerClientUpdateHandler updateConstraintHandler) {
    	this.updateConstraintHandler = updateConstraintHandler;
    }
    
    private final native void addEventListeners(Element elem) /*-{
	    var _this = this;
	    
	    elem.addEventListener("ready", function(event) {
	      _this.@org.vaadin.addons.client.VDatabaseVisualizer::ready()();
	    });
	    
        elem.addEventListener("update-schema", function(event) {
	      _this.@org.vaadin.addons.client.VDatabaseVisualizer::onUpdateSchema(Lelemental/json/JsonObject;)(event.detail);
	    });
	    
	    elem.addEventListener("update-table", function(event) {
	      _this.@org.vaadin.addons.client.VDatabaseVisualizer::onUpdateTable(Lelemental/json/JsonObject;)(event.detail);
	    });
	    
	    elem.addEventListener("update-constraint", function(event) {
	      _this.@org.vaadin.addons.client.VDatabaseVisualizer::onUpdateConstraint(Lelemental/json/JsonObject;)(event.detail);
	    });
	}-*/;
    
    private void ready() {
    	this.ready = true;
    	this.setDatabase(this.jsonDatabase);
    }
    
    private void onUpdateSchema(JsonObject jsonDetail) {
    	this.updateSchemaHandler.onUpdate(jsonDetail);
    }
    
    private void onUpdateTable(JsonObject jsonDetail) {
    	this.updateTableHandler.onUpdate(jsonDetail);
    }
    
    private void onUpdateConstraint(JsonObject jsonDetail) {
    	this.updateConstraintHandler.onUpdate(jsonDetail);
    }
    
    public static interface DatabaseVisualizerClientUpdateHandler extends EventHandler {
    	void onUpdate(JsonObject jsonDetail);
    }
}