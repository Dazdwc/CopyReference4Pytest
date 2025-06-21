package io.github.dazdwc.copyreference4pytest.utils;

public class PytestPathFormatter {
    public enum PathType {
        FILE,
        CLASS_OR_FUNCTION,
        DEFAULT,
        FILE_DIRTY,
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
        // lógica para clases o funciones (convertir a formato pytest)
        // Ej: mi.paquete.TestClase.test_funcion -> mi/paquete.py::TestClase::test_funcion
        return "ReferencePath(Función o clase)"; // TODO
    }

    public static String formatFileDirtyPath(String path) {
        return "isDirty";

    }
}
