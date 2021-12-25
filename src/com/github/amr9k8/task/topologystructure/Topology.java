/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.amr9k8.task.topologystructure;
import java.util.ArrayList;
import java.util.List;

/**
 * Contain Topology's Data Which Are :[Topology ID,ComponentList]
 * @author Amr
 */
public class Topology {
    
  private String id;
  private  List<Component> components = new ArrayList<>()  ;


  
  /***
   *
   * @param id
   * @param components 
   */
    public Topology( String id,List<Component> components) {
        this.id = id;
        this.components = components;
    }
    public Topology() {
    }
    
    /***
     * Gets topology's ID
     * @return 
     */
    public String getId() {
        return id;
    }

    /***
     * Set topology's ID
     * @param id 
     */
    public void setId(String id) {
        this.id = id;
    }

    /***
     * Get All Components in a Topology OBJECT
     * @return 
     */
    public List<Component> getComponentList() {
        return components;
    }
}