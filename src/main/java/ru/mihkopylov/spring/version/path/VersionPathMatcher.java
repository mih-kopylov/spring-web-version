package ru.mihkopylov.spring.version.path;

import java.util.Comparator;
import java.util.Map;
import org.springframework.util.AntPathMatcher;
import org.springframework.util.PathMatcher;

public class VersionPathMatcher extends AntPathMatcher implements PathMatcher {

    protected static final String VERSION_PREFIX = "/v*";

    @Override
    public boolean isPattern(String path) {
        return super.isPattern(path);
    }

    @Override
    public boolean match(String pattern, String path) {
        return super.match(VERSION_PREFIX + pattern, path);
    }

    @Override
    public boolean matchStart(String pattern, String path) {
        return super.matchStart(pattern, path);
    }

    @Override
    public String extractPathWithinPattern(String pattern, String path) {
        return super.extractPathWithinPattern(VERSION_PREFIX + pattern, path);
    }

    @Override
    public Map<String, String> extractUriTemplateVariables(String pattern, String path) {
        if (match(pattern, path)) {
            return super.extractUriTemplateVariables(VERSION_PREFIX + pattern, path);
        }
        return Map.of();
    }

    @Override
    public String combine(String pattern1, String pattern2) {
        return super.combine(VERSION_PREFIX + pattern1, VERSION_PREFIX + pattern2);
    }

    @Override
    public Comparator<String> getPatternComparator(String path) {
        return super.getPatternComparator(VERSION_PREFIX + path);
    }
}
