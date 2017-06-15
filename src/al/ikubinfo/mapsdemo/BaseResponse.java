package al.ikubinfo.mapsdemo;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

public class BaseResponse {

    protected boolean isSuccessful;
    protected String responseMessage;
    protected String message;
    protected String exceptionMessage;
    protected String token;

    private boolean isEmpty;
    private boolean isValid;
    private boolean isEmptyResponse;

    public boolean isIsSuccessful() {
        return isSuccessful;
    }

    public void setIsSuccessful(boolean isSuccessful) {
        this.isSuccessful = isSuccessful;
    }

    public String getResponseMessage() {
        return responseMessage;
    }

    public void setResponseMessage(String responseMessage) {
        this.responseMessage = responseMessage;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getExceptionMessage() {
        return exceptionMessage;
    }

    public void setExceptionMessage(String exceptionMessage) {
        this.exceptionMessage = exceptionMessage;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public boolean isIsEmpty() {
        return isEmpty;
    }

    public void setIsEmpty(boolean isEmpty) {
        this.isEmpty = isEmpty;
    }

    public boolean isIsValid() {
        return isValid;
    }

    public void setIsValid(boolean isValid) {
        this.isValid = isValid;
    }

    public boolean isReadyToProcess() {
        return isSuccessful && isValid;
    }

    public void isEmptyResponse(boolean isEmptyResponse) {
        this.isEmptyResponse = isEmptyResponse;
    }

    public boolean isIsEmptyRespnse() {
        return isEmptyResponse;
    }
}
