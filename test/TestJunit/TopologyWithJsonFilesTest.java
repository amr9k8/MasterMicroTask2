/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TestJunit;
import com.github.amr9k8.task.topologystructure.Component;
import com.github.amr9k8.task.topologystructure.Topology;
import com.github.amr9k8.task.topologystructure.DeviceDetail;
import com.github.amr9k8.task.topologyapi.TopologyWithJsonFiles;
import com.github.amr9k8.task.topologyapi.DataStorageConfig;
import java.util.List;
import org.junit.After;
import org.junit.Test;
import static  org.junit.Assert.*;
import static org.junit.Assert.assertEquals;
import org.junit.Before;
/**
 *
 * @author Amr
 */
public class TopologyWithJsonFilesTest {
    
 private  DataStorageConfig dataConfig = new DataStorageConfig("file");
    
    
   @Before public void BeforeEachTest()
   {   String ValidFilePath = "F:\\Amr\\SW_tasks_shared\\topology.json";
       this.dataConfig.setFilePath(ValidFilePath);
   }
   
    @Test
   public void TestReadValidJsonFileInToTopologyList() {
        TopologyWithJsonFiles topologyJsonApi = new TopologyWithJsonFiles(this.dataConfig); 
        topologyJsonApi.LoadTopologiesintoMemory();//read topologies into memory
        List<Topology> t_list = topologyJsonApi.queryTopologies();
        assertEquals("Assert Not Null",true,t_list.size() > 0); 
   }    
   
   
   @Test
   public void TestReadInvalidJsonFileInToTopologyList() {
        this.dataConfig.setFilePath("xxxxx");
        TopologyWithJsonFiles topologyJsonApi = new TopologyWithJsonFiles(this.dataConfig); 
        topologyJsonApi.LoadTopologiesintoMemory();//read non exist file into memory
        List<Topology> t_list2 = topologyJsonApi.queryTopologies();
        assertEquals("Assert Empty List",true,t_list2.size()==0); 
   } 
   
   
   
   @Test
   public void TestWriteTopologiesToJsonFile (){
    
        TopologyWithJsonFiles topologyJsonApi = new TopologyWithJsonFiles(this.dataConfig); 
        topologyJsonApi.LoadTopologiesintoMemory();
        Topology t1 = topologyJsonApi.queryTopology("top1"); //save it to compare it later
        
       // 1) Write topology from memory  to Disk
        String newjsonFile = "F:\\Amr\\SW_tasks_shared\\topologyTest2.json" ;
        topologyJsonApi.saveTopologies("top1" , newjsonFile);
        topologyJsonApi.deleteAllTopologies();
         
       // 2) read from newjsonFile
        this.dataConfig.setFilePath(newjsonFile);
        topologyJsonApi.LoadTopologiesintoMemory();
        Topology t2 = topologyJsonApi.queryTopology("top1");
        //3) Test if they are equal 
        assertEquals("Assert both Topologies Are Equal",t1.getId() ,t2.getId());        
   } 
  
   @Test
   public void TestQueryTopology(){
      
       TopologyWithJsonFiles topologyJsonApi = new TopologyWithJsonFiles(this.dataConfig); 
       topologyJsonApi.LoadTopologiesintoMemory();
       assertNotNull("Should not be null", topologyJsonApi.queryTopology("top1"));
       assertNull("Should be null", topologyJsonApi.queryTopology("top5"));
   }
   
   @Test
   public void TestQueryDevicesOfToplogy (){
     
      TopologyWithJsonFiles topologyJsonApi = new TopologyWithJsonFiles(this.dataConfig);  
      topologyJsonApi.LoadTopologiesintoMemory();
      List<DeviceDetail> deviceList = topologyJsonApi.queryDevices("top1");
      assertEquals("Assert there are 2 device in this topology",2 ,deviceList.size());        
   }
   
   
   
   @Test
   public void TestQueryDevicesConnectedWithNodeList (){
     
       TopologyWithJsonFiles topologyJsonApi = new TopologyWithJsonFiles(this.dataConfig); 
      int deviceSize =0;
      topologyJsonApi.LoadTopologiesintoMemory();
      deviceSize = topologyJsonApi.getComponentsWithNetListNode("top1","n1").size();
      assertEquals("Assert there are 2 device Connected",2 ,deviceSize); 
      deviceSize = topologyJsonApi.getComponentsWithNetListNode("top1","vin").size();
      assertEquals("Assert there are 1 device Connected",1 ,deviceSize); 
      deviceSize = topologyJsonApi.getComponentsWithNetListNode("top1","vdd").size();
      assertEquals("Assert there are 1 device Connected",1 ,deviceSize); 
      assertNull("Assert there are 0 device Connected",topologyJsonApi.getComponentsWithNetListNode("top1","xxx")); 
   }
   
   @Test
   public void TestDeleteTopology (){
      TopologyWithJsonFiles topologyJsonApi = new TopologyWithJsonFiles(this.dataConfig); 
      topologyJsonApi.LoadTopologiesintoMemory();
      assertEquals("Assert there are 1 Topology",1 ,topologyJsonApi.queryTopologies().size()); 
      topologyJsonApi.deleteTopology("top1");
      assertEquals("Assert there are 0 Topology",0 ,topologyJsonApi.queryTopologies().size()); 
   }
   
    
}
