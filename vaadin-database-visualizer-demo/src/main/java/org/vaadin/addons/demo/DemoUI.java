package org.vaadin.addons.demo;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.annotation.WebServlet;

import org.vaadin.addons.DatabaseVisualizer;
import org.vaadin.addons.model.DatabaseVisualizerColumn;
import org.vaadin.addons.model.DatabaseVisualizerConstraint;
import org.vaadin.addons.model.DatabaseVisualizerDatabase;
import org.vaadin.addons.model.DatabaseVisualizerSchema;
import org.vaadin.addons.model.DatabaseVisualizerTable;

import com.vaadin.annotations.Theme;
import com.vaadin.annotations.Title;
import com.vaadin.annotations.VaadinServletConfiguration;
import com.vaadin.event.LayoutEvents.LayoutClickEvent;
import com.vaadin.event.LayoutEvents.LayoutClickListener;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinServlet;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;

@Theme("demo")
@Title("DatabaseVisualizer Add-on Demo")
@SuppressWarnings("serial")
public class DemoUI extends UI
{

    @WebServlet(value = "/*", asyncSupported = true)
    @VaadinServletConfiguration(productionMode = false, ui = DemoUI.class, widgetset = "org.vaadin.addons.demo.DemoWidgetSet")
    public static class Servlet extends VaadinServlet {
    }

    @Override
    protected void init(VaadinRequest request) {

        // Initialize our new UI component
        final DatabaseVisualizer visualizer = new DatabaseVisualizer();
        
        DatabaseVisualizerColumn column1 = new DatabaseVisualizerColumn();
        column1.setName("column1");
        
        DatabaseVisualizerConstraint constraint = new DatabaseVisualizerConstraint();
        constraint.setSchema("schema");
        constraint.setTable("table1");
        constraint.setName("constraint");
        List<String> columns = new ArrayList<String>();
        columns.add("column1");
        constraint.setColumns(columns);
        constraint.setIsConnectedLeft(false);
        constraint.setRSchema("schema");
        constraint.setRTable("table2");
        List<String> rColumns = new ArrayList<String>();
        rColumns.add("column2");
        constraint.setRColumns(rColumns);
        constraint.setIsConnectedLeftRef(true);
        
        DatabaseVisualizerTable table1 = new DatabaseVisualizerTable();
        table1.setTableId(2L);
        table1.setName("table1");
        table1.setLeft(10L);
        table1.setTop(10L);
        table1.addColumn(column1);
        table1.addConstraint(constraint);
        
        DatabaseVisualizerColumn column2 = new DatabaseVisualizerColumn();
        column2.setName("column2");
        
        DatabaseVisualizerTable table2 = new DatabaseVisualizerTable();
        table2.setTableId(3L);
        table2.setName("table2");
        table2.setLeft(250L);
        table2.setTop(10L);
        table2.addColumn(column2);
        
        DatabaseVisualizerSchema schema = new DatabaseVisualizerSchema();
        schema.setId(1L);
        schema.setName("schema");
        schema.addTable(table1);
        schema.addTable(table2);
        
        DatabaseVisualizerDatabase database = new DatabaseVisualizerDatabase();
        database.addSchema(schema);
        
        visualizer.setDatabase(database);

        // Show it in the middle of the screen
        final VerticalLayout layout = new VerticalLayout();
        layout.setStyleName("demoContentLayout");
        layout.setSizeFull();
        layout.addComponent(visualizer);
        layout.setComponentAlignment(visualizer, Alignment.MIDDLE_CENTER);
        setContent(layout);

    }

}
