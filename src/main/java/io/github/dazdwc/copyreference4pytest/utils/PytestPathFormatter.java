package io.github.dazdwc.copyreference4pytest.utils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class PytestPathFormatter {
    public enum PathType {
        FILE,
        CLASS_OR_FUNCTION,
        DEFAULT,
        FILE_DIRTY,
    }

    public static int obtainLastUppercaseIndex(String path) {
        List<String> parts = Arrays.asList(path.split("\\."));
        int lastIndex = -1;

        for (int i = 0; i < parts.size(); i++) {
            String part = parts.get(i);
            if (!part.isEmpty() && Character.isUpperCase(part.charAt(0))) {
                lastIndex = i;
            }
        }

        return lastIndex;
    }

    public static String convert(String path, PathType type) {
        if (path == null || path.isEmpty()) {
            return "";
        }

        return switch (type) {
            case FILE -> formatFilePath(path);
            case FILE_DIRTY -> formatFileDirtyPath(path);
            case CLASS_OR_FUNCTION -> formatReferencePath(path);
            case DEFAULT -> path;
        };
    }

    private static String formatFilePath(String path) {
        // lógica para archivos (convertir dots a slashes, añadir .py si falta)
        return "File";
    }

    private static String formatReferencePath(String path) {
        List<String> parts = Arrays.asList(path.split("\\."));
        int indexOfClass = obtainLastUppercaseIndex(path);
        StringBuilder finalPath = new StringBuilder();
        for (int i = 0; i < parts.size(); i++) {
            String part = parts.get(i);
            if (i==(indexOfClass -1) && !part.endsWith(".py")) {
                part = part + ".py" ;
            }if (i >= indexOfClass) {
                part = "::" + part;
            }else if (i > 0) {
                part = "/" + part;
            }
            finalPath.append(part);
        }

        return String.valueOf(finalPath);
    }

    public static String formatFileDirtyPath(String path) {
        return "isDirty";

    }
}
