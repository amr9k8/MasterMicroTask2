/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.amr9k8.task.topologyapi;

/**
 * This Class Used to Configure DataSouce Properties Like[ FilePath,ServerURL,DBData,Etc]
 * @author Amr
 */
public class DataStorageConfig {
    private String type;
    private String Filepath;
    
    
//  For Using MySQL As Storage  
//    private String mysqlHost;
//    private String mysqlUser;
//    private String mysqlPass;
//    private String mysqlDbName;

    /***
     * DataStorageType
     * @param type 
     */
     public DataStorageConfig(String type) {
        this.type = type;
     }
     public String  getType(String path){
         return this.type ;
     }
     public void setFilePath(String path){
         this.Filepath = path;
     }
     public String  getFilePath(){
         return this.Filepath ;
     }
                
    }
        
            

