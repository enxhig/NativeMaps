/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package al.ikubinfo.mapsdemo;

import com.codename1.components.InfiniteProgress;
import com.codename1.io.NetworkManager;
import com.codename1.ui.Dialog;

/**
 *
 * @author egorari
 */
public class WebServiceCaller {


    static GoogleResponse getResponse(String fullAddress, ServiceDefinition serviceDefinition) {
        GoogleService googleService = new GoogleService();
        initializePostService(fullAddress, googleService, serviceDefinition);
        NetworkManager.getInstance().addToQueueAndWait(googleService);
        return (GoogleResponse) googleService.getModelResponse();
    }

    public static void initializePostService(String address,BaseService service, ServiceDefinition definition) {
        // NetworkManager.getInstance().setTimeout(100000);
        service.setTimeout(6000);
        service.setDuplicateSupported(true);
        service.setUrl(definition.getUrl()+address);
        service.setContentType(definition.getContentType());     
        service.setPost(definition.isPost());
        InfiniteProgress ip = new InfiniteProgress();
        Dialog dlg = ip.showInifiniteBlocking();
        service.setDisposeOnCompletion(dlg);
    }
}
