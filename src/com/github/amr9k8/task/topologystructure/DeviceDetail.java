/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.amr9k8.task.topologystructure;

/**
 * Contain Component's Details Which Are  : [name,defaultValue, max value ,min value]
 * @author Amr
 */
public class DeviceDetail {
    private transient String name;
    private double defaultValue;
    private double max;
    private double min;
    
   public DeviceDetail() {
    }
   
    public DeviceDetail( String name,  double defaultValue, double max,  double min) {
     this.name = name;
     this.max = max;
     this.min = min;
     this.defaultValue = defaultValue;
    }
    
    
    /**
     * Get name from DeviceDetail
     *
     * @return name as a String
     */
    public String getName() {
        return name;
    }

    /**
     * Gets the default value from DeviceDetail
     *
     * @return defaultValue as a double
     */
    public double getDefaultValue() {
        return defaultValue;
    }

    /**
     * Sets the default value of  DeviceDetail
     *
     * @param defaultValue as a double
     */
    public void setDefaultValue(final double defaultValue) {
        this.defaultValue = defaultValue;
    }

    /**
     * Gets maximum value from DeviceDetail
     *
     * @return max as a double
     */
    public double getMax() {
        return max;
    }

    /**
     * Sets the maximum value of  DeviceDetail
     *
     * @param max as a double
     */
    public void setMax(final double max) {
        this.max = max;
    }

    /**
     * Gets minimum value from DeviceDetail
     *
     * @return min as a double
     */
    public double getMin() {
        return min;
    }

    /**
     * Sets the minimum value of  DeviceDetail
     *
     * @param min as a double
     */
    public void setMin(final double min) {
        this.min = min;
    }


  
}
