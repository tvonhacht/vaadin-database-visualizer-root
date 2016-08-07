package org.vaadin.addons;

import java.util.ArrayList;
import java.util.List;

import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.annotate.JsonSerialize.Inclusion;
import org.vaadin.addons.client.DatabaseVisualizerServerRpc;
import org.vaadin.addons.client.DatabaseVisualizerState;
import org.vaadin.addons.model.DatabaseVisualizerDatabase;
import org.vaadin.addons.model.Position;

import com.vaadin.ui.AbstractJavaScriptComponent;

import elemental.json.JsonArray;
import elemental.json.JsonObject;

// This is the server-side UI component that provides public API 
// for MyComponent
public class DatabaseVisualizer extends AbstractJavaScriptComponent {
	private static final long serialVersionUID = 1L;
	
	private UpdateSchemaListener updateSchemaListener = null;
	private UpdateTableListener updateTableListener = null;
	private UpdateConstraintListener updateConstraintListener = null;
	
	
	// To process events from the client, we implement ServerRpc
	private DatabaseVisualizerServerRpc rpc = new DatabaseVisualizerServerRpc() {
		private static final long serialVersionUID = 1L;

		@Override
		public void updateSchema(JsonObject jsonDetail) {
			if (updateSchemaListener != null) {
				Long id = getJsonObjectLongKey(jsonDetail, "dvSchemaId");
				Long left = getJsonObjectLongKey(jsonDetail, "left");
				Long top = getJsonObjectLongKey(jsonDetail, "top");
				Long width = getJsonObjectLongKey(jsonDetail, "width");
				Long height = getJsonObjectLongKey(jsonDetail, "height");
				
				updateSchemaListener.updateSchema(id, left, top, width, height);
			}
		}

		@Override
		public void updateTable(JsonObject jsonDetail) {
			if (updateTableListener != null) {
				Long id = getJsonObjectLongKey(jsonDetail, "dvTableId");
				Long left = getJsonObjectLongKey(jsonDetail, "left");
				Long top = getJsonObjectLongKey(jsonDetail, "top");
				
				updateTableListener.updateTable(id, left, top);
			}
		}

		@Override
		public void updateConstraint(JsonObject jsonDetail) {
			if (updateConstraintListener != null) {
				Long id = getJsonObjectLongKey(jsonDetail, "dvConstraintId");
				Boolean isConnectorLeft = getJsonObjectBooleanKey(jsonDetail, "isConnectorLeft");
				Boolean isConnectorLeftRef = getJsonObjectBooleanKey(jsonDetail, "isConnectorLeftRef");
				List<Position> positions = getJsonObjectListPositionKey(jsonDetail, "positions");
				
				updateConstraintListener.updateConstraint(id, isConnectorLeft, isConnectorLeftRef, positions);
			}
		}

	};

	public DatabaseVisualizer() {

		// To receive events from the client, we register ServerRpc
		registerRpc(rpc);
	}

	// We must override getState() to cast the state to MyComponentState
	@Override
	protected DatabaseVisualizerState getState() {
		return (DatabaseVisualizerState) super.getState();
	}
	
    public void setDatabase(DatabaseVisualizerDatabase database) {
    	getState().jsonDatabase = genJsonStr(database);
    }
    
    private String genJsonStr(final Object data) {
        String result = null;
        try {
            final ObjectMapper mapper = new ObjectMapper();
            mapper.setSerializationInclusion(Inclusion.NON_NULL);
            result = mapper.writeValueAsString(data);
        }
        catch (final Exception e) {
        	System.out.println(e);
        }
        return result;
    }
    
    private static Long getJsonObjectLongKey(final JsonObject object, final String key) {
    	if (!object.hasKey(key)) {
        	return null;
        }
    	
		return new Double(object.getNumber(key)).longValue();
    }
    
    public static Boolean getJsonObjectBooleanKey(final JsonObject object, final String key) {
        if (!object.hasKey(key)) {
            return null;
        }
        
        return object.getBoolean(key);
    }
    
    public static List<Position> getJsonObjectListPositionKey(final JsonObject mainObj, final String string) {
        List<Position> array = new ArrayList<Position>();
        JsonArray jsonArray = mainObj.getArray(string);
      
        for (int i = 0; i < jsonArray.length(); i++) {
        	JsonObject jsonPosition = jsonArray.getObject(i);
        	Long x = getJsonObjectLongKey(jsonPosition, "x");
        	Long y = getJsonObjectLongKey(jsonPosition, "y");
            array.add(new Position(x, y));
        }
        
        return array;
    }
    
    public static interface UpdateSchemaListener {
    	void updateSchema(Long id, Long left, Long top, Long width, Long height);
    }
    
    public static interface UpdateTableListener {
    	void updateTable(Long id, Long left, Long top);
    }
    
    public static interface UpdateConstraintListener {
    	void updateConstraint(Long id, Boolean isConnectorLeft, Boolean isConnectorLeftRef, List<Position> positions);
    }
}
