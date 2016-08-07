package org.vaadin.addons.client;

import org.vaadin.addons.DatabaseVisualizer;
import org.vaadin.addons.client.VDatabaseVisualizer.DatabaseVisualizerClientUpdateHandler;

import com.vaadin.client.communication.RpcProxy;
import com.vaadin.client.communication.StateChangeEvent;
import com.vaadin.client.ui.AbstractComponentConnector;
import com.vaadin.shared.ui.Connect;

import elemental.json.JsonObject;

// Connector binds client-side widget class to server-side component class
// Connector lives in the client and the @Connect annotation specifies the
// corresponding server-side component
@Connect(DatabaseVisualizer.class)
public class DatabaseVisualizerConnector extends AbstractComponentConnector {
	private static final long serialVersionUID = 1L;
	
	// ServerRpc is used to send events to server. Communication implementation
	// is automatically created here
	DatabaseVisualizerServerRpc rpc = RpcProxy.create(DatabaseVisualizerServerRpc.class, this);

	public DatabaseVisualizerConnector() {
		
		// To receive RPC events from server, we register ClientRpc implementation 
		registerRpc(DatabaseVisualizerClientRpc.class, new DatabaseVisualizerClientRpc() {
			private static final long serialVersionUID = 1L;
		});
		
		getWidget().setUpdateSchemaHandler(new DatabaseVisualizerClientUpdateHandler() {
			@Override
			public void onUpdate(JsonObject jsonDetail) {
				rpc.updateSchema(jsonDetail);
			}
		});
		
		getWidget().setUpdateTableHandler(new DatabaseVisualizerClientUpdateHandler() {
			@Override
			public void onUpdate(JsonObject jsonDetail) {
				rpc.updateTable(jsonDetail);
			}
		});
		
		getWidget().setUpdateConstraintHandler(new DatabaseVisualizerClientUpdateHandler() {
			@Override
			public void onUpdate(JsonObject jsonDetail) {
				rpc.updateConstraint(jsonDetail);
			}
		});

	}

	// We must implement getWidget() to cast to correct type 
	// (this will automatically create the correct widget type)
	@Override
	public VDatabaseVisualizer getWidget() {
		return (VDatabaseVisualizer) super.getWidget();
	}

	// We must implement getState() to cast to correct type
	@Override
	public DatabaseVisualizerState getState() {
		return (DatabaseVisualizerState) super.getState();
	}

	// Whenever the state changes in the server-side, this method is called
	@Override
	public void onStateChanged(StateChangeEvent stateChangeEvent) {
		super.onStateChanged(stateChangeEvent);

		// State is directly readable in the client after it is set in server
		getWidget().setDatabase(getState().jsonDatabase);
	}

}