package ru.mihkopylov.spring.version.path;

import java.util.Comparator;
import java.util.Map;
import lombok.NonNull;
import org.springframework.util.AntPathMatcher;
import org.springframework.util.PathMatcher;

public class VersionPathMatcher extends AntPathMatcher implements PathMatcher {
    @NonNull
    private final String pathVersionPrefix;

    public VersionPathMatcher( String pathVersionPrefix ) {
        this.pathVersionPrefix = "/" + pathVersionPrefix + "*";
    }

    @Override
    public boolean isPattern( String path ) {
        return super.isPattern( path );
    }

    @Override
    public boolean match( String pattern, String path ) {
        return super.match( pathVersionPrefix + pattern, path );
    }

    @Override
    public boolean matchStart( String pattern, String path ) {
        return super.matchStart( pattern, path );
    }

    @Override
    public String extractPathWithinPattern( String pattern, String path ) {
        return super.extractPathWithinPattern( pathVersionPrefix + pattern, path );
    }

    @Override
    public Map<String, String> extractUriTemplateVariables( String pattern, String path ) {
        if (match( pattern, path )) {
            return super.extractUriTemplateVariables( pathVersionPrefix + pattern, path );
        }
        return Map.of();
    }

    @Override
    public String combine( String pattern1, String pattern2 ) {
        return super.combine( pathVersionPrefix + pattern1, pathVersionPrefix + pattern2 );
    }

    @Override
    public Comparator<String> getPatternComparator( String path ) {
        return super.getPatternComparator( pathVersionPrefix + path );
    }
}
