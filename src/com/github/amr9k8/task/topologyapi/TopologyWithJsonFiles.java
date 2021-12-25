/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.amr9k8.task.topologyapi;

import com.github.amr9k8.task.topologystructure.Component;
import com.github.amr9k8.task.topologystructure.Topology;
import com.github.amr9k8.task.topologystructure.DeviceDetail;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import java.io.BufferedReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

/**
 * API To Deal With JsonFiles 
 * @author Amr
 */
public class TopologyWithJsonFiles implements  Functionable{
    
     private  List<Topology> topologies = new ArrayList<>() ;
     private  DataStorageConfig storageConfig = new DataStorageConfig("file");
     
     
     /***
      * 
      * @param path 
      */
     public TopologyWithJsonFiles(DataStorageConfig storageConfig)
     {    String path = storageConfig.getFilePath();
          this.storageConfig.setFilePath(path);
          //System.out.println(this.storageConfig.getFilePath());
     }
     
     /***
      * Set File Path
      * @param d 
      */
     @Override
     public void setDataStorageParams(DataStorageConfig d){
        String newPath = d.getFilePath();
        this.storageConfig.setFilePath(newPath); 
     }
     
     @Override
      public DataStorageConfig getDataStorageParams()
      {
          return this.storageConfig;
      }
      
   /***
    * To Load Topolgies from JsonFile into Memory
    */
     @Override
     public void LoadTopologiesintoMemory(){
        JSONObject jsonTopology;
        BufferedReader file;
        JSONParser jsonParser = new JSONParser();
         //System.out.println(this.storageConfig.getFilePath());
        try {
            file = Files.newBufferedReader(Paths.get(this.storageConfig.getFilePath()), StandardCharsets.UTF_8);
            jsonTopology = (JSONObject) jsonParser.parse(file);
            file.close(); 
        }catch (Exception ex) {
            if(ex  instanceof IOException)
                    System.out.println("file not found Enter Valid File");
            else
                    System.out.println(ex);
            return;
           
        }
         String topologyID = (String) jsonTopology.get("id");
         JSONArray jsonComponents = (JSONArray) jsonTopology.get("components");
         ArrayList<Component> components = new ArrayList<>();
         
//LOOP ON EACH COMPONENT 
      for (int i = 0; i < jsonComponents.size(); i++){
        try{
            //cast ComponentObject to JsonComponentObject 
             JSONObject jsonComponent = (JSONObject) jsonComponents.get(i);
             
              Map<String, String> netlist = new HashMap<>();
              DeviceDetail details        = null;
              String deviceDetailsName    = null;
              //1)Get id
              String componentID = (String) jsonComponent.get("id");
              //2)Get Type
              String type = (String) jsonComponent.get("type");  
              //3) Get Netlist 
             JSONObject jsonNetlist = (JSONObject) jsonComponent.get("netlist");
             for(Object key : jsonNetlist.keySet()){  
                Object value = jsonNetlist.get(key);
                netlist.put((String)key,(String)value);
             }
             //4) Get DeviceDetails
             deviceDetailsName = (type.equals("resistor") )?"resistance":"m(l)";
             JSONObject jsonDetails = (JSONObject) jsonComponent.get(deviceDetailsName);
             double def = ((Number)jsonDetails.get("default")).doubleValue();
             double min = ((Number) jsonDetails.get("min")).doubleValue();
             double max = ((Number) jsonDetails.get("max")).doubleValue();           
             details = new DeviceDetail(deviceDetailsName,def,max,min);
             
             //Finally Creating Component Object
             Component component = new Component(componentID, type, netlist, details);
             
             //Make Array of Component
             components.add(component);
             
            }catch(Exception e){System.out.println(e);}
      }
      
       //return topology object contain id and array of components
       
        Topology t =  new Topology(topologyID, components);
        
        this.topologies.add(t);
   }
   
     /***
      * To Save Topolgies from Memory to JsonFile
      * @param topologyID
      * @param path 
      */
     @Override
     public void saveTopologies(String topologyID ,String path)
     {
        try {
        Topology topology = this.queryTopology(topologyID);
        
        //topologyjsonObject  contain id and array of components
        JSONObject topologyJson = new JSONObject();
        JSONArray componentsListJson = new JSONArray();   
        topologyJson.put("id", topology.getId());      
        
        //Get Each Component 
        for(Component component : topology.getComponentList())
        {
           //Each Component Contain {type,id,DeviceDetailsJsonObj, NetlistJsonObj}
            JSONObject componentJson = new JSONObject();
            JSONObject detialsJson = new JSONObject();
            JSONObject netlistJson = new JSONObject();
            
            //Fill DetailsJson
            DeviceDetail details = component.getDevice();
            detialsJson.put("default", details.getDefaultValue() );
            detialsJson.put("min", details.getMin());
            detialsJson.put("max", details.getMax());
            
           //Fill NetlistJson
            for(String key:component.getNetlist().keySet())
                netlistJson.put(key, component.getNetlist().get(key));
                
            //Fill ComponentJson 
            componentJson.put("type", component.getType());
            componentJson.put("id", component.getId());
            componentJson.put(details.getName(),detialsJson);
            componentJson.put("netlist",netlistJson );
            //Add Componentjson To JsonArray
            componentsListJson.add(componentJson);
        }
        //Put ComponentsArray to Our Topology
         topologyJson.put("components", componentsListJson);
         //Write to JsonFile
         FileWriter fileWriter = new FileWriter(path);
         fileWriter.write(topologyJson.toString());
         fileWriter.close();
          
        } catch (Exception e) {
             System.out.println(e);
             return;
        }

         
     }
     
     
     
     
    /***
    * To  Gets Current Topolgies in Memory
    * @return 
    */
     @Override
     public  List<Topology> queryTopologies(){
     
        return this.topologies;
     }
     
     
     /***
      * To get A Topology by it's id if it exist in memory
      * @param topologyID
      * @return 
      */
     @Override
     public  Topology queryTopology(String topologyID){
     for(Topology t :this.topologies){
         if(t.getId().equals(topologyID))
             return t;
     }
      return null;
     }
     
     /***
      * get components of topology
      * @param topologyID
      * @return 
      */
     @Override
    public List<Component> queryComponent(String topologyID)
    {
      Topology t = this.queryTopology(topologyID);//get the topology
      return t.getComponentList();
    }
    
    /***
     * get details of component in a topology
     * @param topologyID
     * @return 
     */
    @Override
    public List<DeviceDetail> queryDevices(String topologyID)
    {
      List<DeviceDetail> deviceList = new ArrayList<>();
      Topology t = this.queryTopology(topologyID);//get the topology
      List<Component> components = t.getComponentList(); //get its Components
      
      //get its detials of each component
      components.forEach((comp) -> {deviceList.add(comp.getDevice()); });
      return deviceList;
    }
    
     @Override
    public List<Component> getComponentsWithNetListNode(String topologyID,String Node)
    {
      Topology t = this.queryTopology(topologyID);//get the topology
      List<Component> components = new ArrayList<>(); //to save result
      
      //get each component netlist and check if node exist
      for (Component component : t.getComponentList())
        if( component.getNetlist().containsValue(Node))
            components.add(component);
      
      if (components.isEmpty()) 
        return null;
      
      return components;
    }
    
    /***
     * Delete Topology By ID From Memory
     * @param topologyID 
     */
    @Override
    public  void deleteTopology(String topologyID)
    {
    List<Topology> t_list = this.queryTopologies();
    int i =0;
    for(Topology t :t_list){
        if(t.getId().equals(topologyID))
            break;
        i++;
     }
     this.topologies.remove(i);
    }
     
     public  void deleteAllTopologies(){
      this.topologies.clear();
     }
    
}


