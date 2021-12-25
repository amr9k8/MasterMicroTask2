/**
<h1>Introduction</h1
<p> This Topology API provides an easy way to deal with  TopologyObject</p>
<b> Let's Start By Understanding  The JsonStructure of Topology:<b>
<hr>
<pre>{@code  
  {
  "id": "TopologyID",
  "componentsArray": 
   [
    
        {
                "type": "component 1 Type",
                "id": "component 1 ID",
                "component1Name": {
                  "default": double value,
                  "min": double value,
                  "max": double value
                },
                "netlist": {
                  "String": "String",
                  "String": "String"
                }
        },
        {
                "type": "component 2 Type",
                "id": "component 2 ID",
                "component 2 Name": {
                  "default": double value,
                  "min": double value,
                  "max": double value
                },
                "netlist": {
                  "String": "String",
                  "String": "String"
                }
        }
  ]
}
 }</pre>
</p>
<hr>
<h3> As we See Above :</h3>
  <h4> 1) The TopologyJson Object Contain 2 items (String topID, JsonObject CompenentsList)</h4><br>
  <h4> 2) Each Component Contain 4 items (String compID,String compType, JsonObject compDetails, JsonObject compNetList)</h4><br>
  <h4> 3) compDetails  Contain 4 items (String DeviceName,Double Max Value, Double Min Value, Double Default Value)</h4><br>
  <h4> 4) compNetList Map Contain ( String key: String value )</h4><br>
  


<h1>What Can You Do </h1>
<p> this Api  can preform set of functions on Any topology.Json File</br>
<h2>Functions :</h2>
 <ul>
  <li>Read Topologies From JsonFile</li>
  <li>Convert a Topology object to JsonObject And Save it in JsonFile</li>
  <li>Search  in A JsonFile for A Topology with  ID </li>
  <li>Delete A Topology</li>
  <li>Get  Components of A Certain Topology </li>
  <li>Search For All Components in A Given  Topology That Are Connected To A Specific NetListNode </li>
  <li>Update Topology Data [id,type,details,netlist]</li>
</ul> 

<h2> For Github Link , visit:</h2>
<ul>
    <li><a href="http://www.javapractices.com/topic/TopicAction.do?Id=60">http://www.javapractices.com/topic/TopicAction.do?Id=60</a>
</ul>
@since 0.1
 */
package com.github.amr9k8.task.topologyapi;
