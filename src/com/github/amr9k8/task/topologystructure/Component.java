/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.amr9k8.task.topologystructure;
import java.util.Map;

import java.util.*;
/**
 * Contain Component Data Which Are  : [Component id,Component type,Component details, Component netlist]
 * @author Amr
 */
public class Component {
    
    private  String id;
    private  String type;
    private  DeviceDetail details ;
    private  Map<String, String> netlist;
    
   


/***
 *
 * @param id
 * @param type
 * @param netlist
 * @param details 
 */
    public Component(String id,  String type, Map<String, String> netlist, DeviceDetail details) {
        this.id = id;
        this.type = type;
        this.netlist = netlist;
        this.details = details;
    }
    
    public Component() {
    }



    /**
     * Get  netlist of a component as map
     *
     * @return netlist as a Map
     */
    public Map<String, String> getNetlist() {
        return this.netlist;
    }

    /**
     * Gets  details of a component as  DeviceDetailsClass object
     *
     * @return DeviceDetail Obj
     */
    public DeviceDetail getDevice() {
        return this.details;
    }

    
   /**
     * Gets component's ID
     *
     * @return id as a String
     */
    public String getId() {
        return id;
    }

/***
 *  Set id of Component
 * @param id 
 */
    public void setId(String id) {
        this.id = id;
    }

    
   /**
     * Gets component's type
     *
     * @return type as a String
     */
    public String getType() {
        return type;
    }

    
/***
 * Set Type of Component
 * @param type 
 */
    public void setType(String type) {
        this.type = type;
    }
    
    
}
