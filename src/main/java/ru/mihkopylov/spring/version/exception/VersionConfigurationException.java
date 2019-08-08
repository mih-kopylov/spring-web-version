package ru.mihkopylov.spring.version.exception;

import lombok.NonNull;

public class VersionConfigurationException extends RuntimeException {
    private static final long serialVersionUID = 1L;

    public VersionConfigurationException( @NonNull String messageTemplate, @NonNull Object... variables ) {
        super( String.format( messageTemplate, variables ) );
    }
}
