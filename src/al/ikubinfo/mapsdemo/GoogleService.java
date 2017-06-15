/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package al.ikubinfo.mapsdemo;

import java.io.IOException;
import java.io.InputStream;
import com.codename1.processing.Result;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;

/**
 *
 * @author egorari
 */
public class GoogleService extends BaseService {

    GoogleResponse googleResponse;

    public GoogleService() {
        this.googleResponse = new GoogleResponse();
    }

    @Override
    protected void readResponse(InputStream input) throws IOException {
        result = Result.fromContent(input, Result.JSON);
        validateAndGetBaseResponse(result, getResponseCode(), googleResponse);
        if (googleResponse.isReadyToProcess()) {
            List<Results> response_array = new ArrayList<Results>();
            List<Hashtable> resultsArrayList = (List<Hashtable>) result.getAsArray("results");
            if (!resultsArrayList.isEmpty()) {
                for (Hashtable results : resultsArrayList) {
                    Results resultObject = new Results();
                    resultObject.setFormatted_address((String) results.get("formatted_address"));
                    Hashtable geo = (Hashtable) results.get("geometry");
                    Geometry geometry = new Geometry();
                    String location_type = (String) geo.get("location_type");
                    geometry.setLocation_type(location_type);
                    Location location = new Location();
                    Hashtable loc = (Hashtable) geo.get("location");
                    location.setLat((double) loc.get("lat"));
                    location.setLng((double) loc.get("lng"));
                    geometry.setLocation(location);
                    resultObject.setGeometry(geometry);
                    response_array.add(resultObject);
                }
            }
            googleResponse.setResults((ArrayList<Results>) response_array);
        }
    }

    @Override
    public void manageGenericErrors() {
    }

    @Override
    public BaseResponse getModelResponse() {
        return googleResponse;
    }

}
