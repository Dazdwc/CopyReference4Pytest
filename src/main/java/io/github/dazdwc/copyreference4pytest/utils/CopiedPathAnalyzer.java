package io.github.dazdwc.copyreference4pytest.utils;


public class CopiedPathAnalyzer {

    public static PytestPathFormatter.PathType identifyPathType(String path) {
        if (path == null || path.isEmpty()) {
            return null;
        }

        if (path.endsWith(".py") && path.contains("/")) {
            return PytestPathFormatter.PathType.FILE;
        }

        if (path.contains(".py") && path.contains(":")) {
            return PytestPathFormatter.PathType.FILE_DIRTY;
        }

        if (path.contains(".") && !path.contains(".py") && !path.contains("/")) {
            return PytestPathFormatter.PathType.CLASS_OR_FUNCTION;
        }

        return PytestPathFormatter.PathType.DEFAULT;
    }
}
