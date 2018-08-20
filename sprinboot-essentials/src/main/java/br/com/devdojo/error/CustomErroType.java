package br.com.devdojo.error;

/**
 * Created by ricardors on 05/08/2018.
 */
public class CustomErroType {
    private String errorMessage;

    public CustomErroType(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public String  getString() {
        return errorMessage;
    }
}
