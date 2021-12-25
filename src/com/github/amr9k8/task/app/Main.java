/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.amr9k8.task.app;

import com.github.amr9k8.task.topologystructure.Component;
import com.github.amr9k8.task.topologystructure.Topology;
import com.github.amr9k8.task.topologystructure.DeviceDetail;
import com.github.amr9k8.task.topologyapi.TopologyWithJsonFiles;
import com.github.amr9k8.task.topologyapi.DataStorageConfig;

import java.util.List;
import java.util.Objects;
import java.util.Scanner;

/**
 * A Console APP to Consume The TopologiesJsonApi
 * @author Amr
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
 
       String operations =  "1. Read Topologies From JsonFile To Memory \n "
                          + "2. Write Toplogies To JsonFile\n "
                          + "3. Query Topologies\n 4."
                          + " Delete Topology\n "
                          + "5. Query Devices in a given Topology\n "
                          + "6. Query Devices with Netlist Node in a given Topology \n"
                          + " 7. Exit";
       
       Scanner  input = new Scanner(System.in);
       String choice = null;
       DataStorageConfig dataconfig =  new DataStorageConfig("file");
       TopologyWithJsonFiles topologyJsonApi = null ;
       while(true)
       {
            try {
                System.out.println(operations);
                choice = input.nextLine();
                if(choice.equals("1")){
                        System.out.println("Enter file path:");
                        String filePath = input.nextLine();
                        dataconfig.setFilePath(filePath);
                        topologyJsonApi = new TopologyWithJsonFiles(dataconfig);
                        topologyJsonApi.LoadTopologiesintoMemory();
                        System.out.println("File Read Successfully !");
                }
                       
                else if(choice.equals("2")){
                        System.out.println("Enter file path:");
                        String filePath = input.nextLine();
                        System.out.println("Enter Topology ID");
                        String id = input.nextLine();
                        topologyJsonApi.saveTopologies(id, filePath);
                        System.out.println("Topology Saved To Disk as jsonfile Successfully");
                     }
                    else if(choice.equals("3") ){
                         List<Topology> t_list =  topologyJsonApi.queryTopologies();
                         System.out.println("Number of Topologies in Memory :"+t_list.size()+"\nToplogies_ID: \n");
                         for(Topology t:t_list)
                              System.out.println("- "+t.getId());
                    }
                
                   else if(choice.equals("4")){
                        System.out.println("Enter Topology ID");
                        String id = input.nextLine();
                        Topology t =  topologyJsonApi.queryTopology(id);
                        if(Objects.isNull(t))
                            System.out.println("Topology not found  in Memory :");
                        else{          
                        topologyJsonApi.deleteTopology(id);
                        System.out.println("Topology  Deleted  Successfully :");
                        }
               
                   }
                  else if(choice.equals("5")){
                        System.out.println("Enter Topology ID");
                        String id = input.nextLine();
                        Topology t =  topologyJsonApi.queryTopology(id);
                        if(Objects.isNull(t))
                            System.out.println("Topology not found  in Memory :");
                        else {          
                        List<Component> components = t.getComponentList();
                        System.out.println("Number of Compomets Found in this Topology :"+components.size());
                        for(Component c : components){
                            System.out.println("ID :"+c.getId());
                            System.out.println("Type :"+c.getId());
                            }
                        }
                  }
                   else if(choice.equals("6")){
                        System.out.println("Enter Topology ID");
                        String TopologyID = input.nextLine();
                        Topology t =  topologyJsonApi.queryTopology(TopologyID);
                        if(Objects.isNull(t))
                            System.out.println("Topology not found  in Memory :");
                        else {      
                        System.out.println("Enter NetlistNodeID");
                        String netlistID = input.nextLine();
                        List<Component> components = topologyJsonApi.getComponentsWithNetListNode(TopologyID, netlistID);
                        System.out.println("Number of Componets in this Topology Connected With This NetlistNode :"+components.size());
                        for(Component c : components){
                            System.out.println("ID :"+c.getId());
                            System.out.println("Type :"+c.getId());
                            }
                        }
                   }
                   else if(choice.equals("7")){
                       System.out.println("Closed");
                       break;
                   }
                       
                
            } catch (Exception e) {
                System.out.println(e);
            }

           
           
           
           
        

       }
        
    }
    
    
 
        
        
        
        
    
    
}
