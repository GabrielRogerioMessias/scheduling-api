package com.messias.schedulingapi.services.exceptionsServices;

public class ResourceNotFoundException extends RuntimeException {

    public ResourceNotFoundException(Class<?> className, Object id) {
        super(className.getSimpleName() + " not found with id: " + id);
    }
}
