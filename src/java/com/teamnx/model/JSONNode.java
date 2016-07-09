/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.teamnx.model;

import java.util.ArrayList;
import java.util.HashMap;
import net.sf.json.JSONObject;

/**
 *
 * @author Y400
 */
public class JSONNode extends HashMap<String, Object> {

    private String id;
    private String name;
    private ArrayList<JSONNode> node;

    public JSONNode() {
	super();
    }

    public String getId() {
	return id;
    }

    public void setId(String id) {
	this.id = id;
	put("mapId", id);
    }

    public String getName() {
	return name;
    }

    public void setName(String name) {
	this.name = name;
	put("text", name);
    }

    public ArrayList<JSONNode> getNode() {
	return node;
    }

    public void setNode(ArrayList<JSONNode> node) {
	this.node = node;
	put("nodes", node);
    }

    public static void main(String[] args) {
	JSONNode jsonNode = new JSONNode();
	jsonNode.setId("123123");
	jsonNode.setName("456456");
	ArrayList<JSONNode> jsonns = new ArrayList<JSONNode>();
	JSONNode jsonn = new JSONNode();
	jsonn.setId("what");
	jsonns.add(jsonn);
	jsonNode.setNode(jsonns);
	JSONObject jsonObject = JSONObject.fromObject(jsonNode);
	System.out.println(jsonObject);
    }
}
