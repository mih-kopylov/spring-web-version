package ru.mihkopylov.spring.version;

public enum VersioningType {
    /**
     * Version is read from path. It should be the first path element. Example: {@code /v1/users}.
     */
    PATH,
    /**
     * Version is read from query parameter.
     */
    QUERY,
    /**
     * Version is read from custom header.
     */
    HEADER,
    /**
     * Version is read from `Accept` header with specific version
     */
    ACCEPT
}
