package br.com.vfs.api.cdc.shared.errors;

public class NotFoundException extends RuntimeException {

    public NotFoundException(String message){
        super(message);
    }
}
