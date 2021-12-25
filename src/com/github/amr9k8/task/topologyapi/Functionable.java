/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.amr9k8.task.topologyapi;
import com.github.amr9k8.task.topologystructure.*;

import java.util.ArrayList;
import java.util.List;

/**
 * An Interface to define Main Functions OF Any TopologyApiClass
 * Regardless The API Data Source (JsonFiles,XmlFiles,TxtFiles,FromDatabase,etc..)  
 * @author Amr
 */
public interface Functionable  {
   void setDataStorageParams(DataStorageConfig d); //For Example " FilePath,DB Connection,etc..)
   DataStorageConfig getDataStorageParams();
   void LoadTopologiesintoMemory();//return  topolgies from datasource
   void saveTopologies(String topologyID ,String path);
   List<Topology> queryTopologies();//return current topolgies from global array
   Topology queryTopology(String TopologyId); 
   List<Component> queryComponent(String topologyID);
   List<DeviceDetail> queryDevices(String topologyID);
   List<Component> getComponentsWithNetListNode(String topologyID,String Node);
   void deleteTopology(String TopologyId); 
   void deleteAllTopologies(); 
  

}
