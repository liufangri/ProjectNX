/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.teamnx.model;

import java.util.HashMap;
import net.sf.json.JSONObject;

/**
 *
 * @author Y400
 */
public class JSONNode extends HashMap<String, Object> {

    private String id;
    private String name;
    private JSONNode[] nodes;

    public JSONNode() {
        super();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
        put("id", id);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
        put("name", name);
    }

    public JSONNode[] getNode() {
        return nodes;
    }

    public void setNode(JSONNode[] node) {
        this.nodes = node;
        put("node", node);
    }

    public static void main(String[] args) {

    }
}
