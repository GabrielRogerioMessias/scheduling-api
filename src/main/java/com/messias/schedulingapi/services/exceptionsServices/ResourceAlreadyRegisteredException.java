package com.messias.schedulingapi.services.exceptionsServices;

public class ResourceAlreadyRegisteredException extends RuntimeException {

    public ResourceAlreadyRegisteredException(Class<?> className, String username) {
        super(className.getSimpleName() + " already registered with username " + username);
    }
}
