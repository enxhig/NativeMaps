/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package al.ikubinfo.mapsdemo;

import org.codehaus.jackson.annotate.JsonIgnore;

/**
 *
 * @author egorari
 */
public class Results {

    private String formatted_address;

    private Geometry geometry;

    @JsonIgnore
    private Object address_components;

    @JsonIgnore
    private Object types;

    @JsonIgnore
    private String place_id;

    public String getFormatted_address() {
        return formatted_address;
    }

    public void setFormatted_address(String formatted_address) {
        this.formatted_address = formatted_address;
    }
    
    public Geometry getGeometry() {
        return geometry;
    }

    public void setGeometry(Geometry geometry) {
        this.geometry = geometry;
    }

    public Object getAddress_components() {
        return address_components;
    }

    public void setAddress_components(Object address_components) {
        this.address_components = address_components;
    }

    public Object getTypes() {
        return types;
    }

    public void setTypes(Object types) {
        this.types = types;
    }

}
