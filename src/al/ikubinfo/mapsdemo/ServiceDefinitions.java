/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package al.ikubinfo.mapsdemo;

/**
 *
 * @author egorari
 */
public class ServiceDefinitions {
    
    private static final String GEOCODE = "http://maps.googleapis.com/maps/api/geocode/json?address=";
    private static final ServiceDefinition GEOCODE_DEFINITION = new ServiceDefinition(GEOCODE, false, "application/json;charset=UTF-8");

    public static String getGEOCODE() {
        return GEOCODE;
    }

    public static ServiceDefinition getGEOCODE_DEFINITION() {
        return GEOCODE_DEFINITION;
    }
     
}
