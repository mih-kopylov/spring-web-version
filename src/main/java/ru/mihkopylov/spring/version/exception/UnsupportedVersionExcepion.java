package ru.mihkopylov.spring.version.exception;

/**
 * Exception is thrown when request version is less than configured API version.
 */
public class UnsupportedVersionExcepion extends RuntimeException {
    private static final long serialVersionUID = 1L;

    public UnsupportedVersionExcepion( int apiMinVersion, int requestVersion ) {
        super( String.format( "Request has version %s while minimal API version is %s", requestVersion,
                apiMinVersion ) );
    }
}
