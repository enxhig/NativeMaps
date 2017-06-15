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
public class DataProxy {
    
    public static GoogleResponse getResponse(String address){
        return WebServiceCaller.getResponse(address, ServiceDefinitions.getGEOCODE_DEFINITION());
    }
}
