package edu.upc.eetac.dsa.vargaft.hobbylist.api;

/**
 * Created by Vargaft on 29/05/2015.
 */
public class AppException extends Exception {
    public AppException() {
        super();
    }

    public AppException(String detailMessage) {
        super(detailMessage);
    }
}