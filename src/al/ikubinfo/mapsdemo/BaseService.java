/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package al.ikubinfo.mapsdemo;

import com.codename1.io.ConnectionRequest;
import com.codename1.io.Log;
import com.codename1.processing.Result;
import com.codename1.ui.Display;
import com.codename1.util.StringUtil;
import java.io.IOException;
import java.text.SimpleDateFormat;
import ca.weblite.codename1.json.JSONException;
import ca.weblite.codename1.json.JSONObject;

public abstract class BaseService extends ConnectionRequest {

    public final static String TOKEN_STRING = "TokenString";
    public final static String TOKEN = "Token";
    public final static String EXPIRY_TIME = "ExpiryTime";
    public final static String SUCCESSFUL = "IsSuccessful";
    public static final String RESPONSE_MESSAGE = "ResponseMessage";
    public static final String ISUCCEESSFUL = "IsSuccessful";
    public static final String RESPONSEMESSAGE = "ResponseMessage";
    public static final String REQUEST_LANGUAGE = "languageID";
    public static final String REQUEST_TOKEN = "Token";
    public static final String VALID_TOKEN_FIELD = "IsValid";
    public static final String SERVICE_NOT_AVAILABLE = "Sherbimi nuk eshte i disponueshem per momentin!";
    public static final String EMPTY_LIST_STRING = "Result is empty";
    public static final String CURRENT_PAGE = "CurrentPage";
    public static final String TOTAL_ELEMENTS = "TotalElements";
    public static final String ELEMENTS_PER_PAGE = "ElementsPerPage";
    public static final String PAGE_SIZE = "PageSize";
    protected static final String APP_VERSION = "AppVersion";
    protected static final String OS_VERSION = "OSVersion";
    public boolean duplicateSupported = true;
    public int timeout = 15000;
    Result result;
    protected static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("MM.dd.yyyy");
    public static final int DEBUG = 1;
    public static final int INFO = 2;
    public static final int WARNING = 3;
    public static final int ERROR = 4;

    /**
     * Handles a server response code that is not 200 and not a redirect (unless
     * redirect handling is disabled)
     */
    @Override
    protected void handleErrorResponseCode(int code, String message) {
        Log.p("Code: " + code + " Internal server error: " + message);
        manageGenericErrors();
    }

    /**
     * Handles an exception thrown when performing a network operation
     */
    @Override
    protected void handleRuntimeException(RuntimeException err) {
        Log.p("Handle Runtime Exception " + err);
        manageGenericErrors();
    }

    /**
     * Handles IOException thrown when performing a network operation
     */
    @Override
    protected void handleIOException(IOException err) {
        Log.p("Handle IOException, when network is down! " + err);
        manageGenericErrors();
    }

    /**
     * Handles an exception thrown when performing a network operation, the
     * default implementation shows a retry dialog.
     */
    @Override
    protected void handleException(Exception err) {
        Log.p("Exception: " + err);
        manageGenericErrors();
    }

    /**
     * Allows reading the headers from the connection by calling the getHeader()
     * method when a response that isn't 200 OK is sent.
     */
    @Override
    protected void readErrorCodeHeaders(Object connection) {
        Log.p("Read Error Code Headers ");
        manageGenericErrors();
    }

    /**
     * @return JSONObject that is made in base service with request language and
     * the token , that every request is build with it
     *
     * @throws JSONException
     */
    protected JSONObject buildBaseRequest() throws JSONException {

        String appVersion = Display.getInstance().getProperty("AppVersion", "1.0");
        String platformName = Display.getInstance().getPlatformName();
        String OS = Display.getInstance().getProperty("OS", "UNKNOWN");
        String OSVer = Display.getInstance().getProperty("OSVer", "UNKNOWN");
        String OSInfo = platformName + "-" + OS + "-" + OSVer;

        return (new JSONObject()).put(REQUEST_TOKEN, getToken()).put(REQUEST_LANGUAGE, getLanguage()).put(APP_VERSION, appVersion).put(OS_VERSION, OSInfo);
    }

    /**
     *
     * @return the token of the user that is authenticated
     */
    private String getToken() {
        //    return UserManager.getInstance().getToken();
        return null;
    }

    /**
     *
     * @return the language of the user that is authenticated
     */
    public int getLanguage() {
        //    return UserManager.getInstance().getLanguage();
        return 0;
    }

    /**
     * <h4> Abstract method that every subclass has to implement </h4>
     */
    public abstract void manageGenericErrors();

    /**
     * <h4> Every subclass has to override this method and also give the model
     * response BaseResponse or the Response that extends it</h4>
     *
     * @return subclass model response
     */
    public abstract BaseResponse getModelResponse();

    /**
     * <h4> Validate the result , response code , and the response for
     * subclasses that extend it </h4>
     *
     * @param result gives the result after
     * @param respCode the response code that is returned from response input
     * stream
     * @param response the web service response that has come back
     */
    public void validateAndGetBaseResponse(Result result, int respCode, BaseResponse response) {
        System.out.println(result);
        System.out.println(respCode);
        System.out.println(response);
        try {
            if (respCode != 200) {
                response.setIsSuccessful(false);
                manageGenericErrors(response, SERVICE_NOT_AVAILABLE);
                Log.p("Response Code is " + respCode);
            } else if (respCode == 204) {
                response.setIsSuccessful(true);
                response.setIsValid(true);
                response.setIsEmpty(true);

            } else {
                response.setIsSuccessful(true);
                response.setIsValid(true);
System.out.println("success");
            }
        } catch (Exception ex) {
            Log.e(ex);
            System.out.println("vjen ketu");
            manageGenericErrors(response, SERVICE_NOT_AVAILABLE);
        }
    }

    /**
     * <h4> Parses the content disposition </h4>
     * <p>
     * Subclasses that extend and their response is a content , is used to parse
     * the content disposition </p>
     *
     * @param contentDisposition a string that contains content disposition
     * @return pathName
     */
    public String parseContentDisposition(String contentDisposition) {
        String removeStartString = StringUtil.replaceAll(contentDisposition, "attachment; filename=", "");
        String removeQuotes = StringUtil.replaceAll(removeStartString, "\"", "");
        String pathName = StringUtil.replaceAll(removeQuotes, " ", "");
        Log.p("Name of file " + pathName, INFO);

        return pathName;
    }

    /**
     * <h4>Manages the error messages</h4>
     *
     * @param response that the message will be set to
     * @param message that will be set to the response
     */
    public void manageGenericErrors(BaseResponse response, String message) {
        response.setIsSuccessful(false);
        if (message != null && !message.equals("")) {
            response.setResponseMessage(message);
        } else {
            response.setResponseMessage(SERVICE_NOT_AVAILABLE);
        }
    }

}
