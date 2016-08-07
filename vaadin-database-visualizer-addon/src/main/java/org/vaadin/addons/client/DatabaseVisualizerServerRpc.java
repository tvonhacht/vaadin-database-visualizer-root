package org.vaadin.addons.client;

import com.vaadin.shared.communication.ServerRpc;

import elemental.json.JsonObject;

// ServerRpc is used to pass events from client to server
public interface DatabaseVisualizerServerRpc extends ServerRpc {

	public void updateSchema(JsonObject jsonDetail);

	public void updateTable(JsonObject jsonDetail);

	public void updateConstraint(JsonObject jsonDetail);

}
