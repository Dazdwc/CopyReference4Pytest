package io.github.dazdwc.copyreference4pytest.utils;

public class PythonReferenceTranslator {

    public static String traslateToPytest(String path) {

        String pytestPath = "lo detecta bien" + path;
        return pytestPath;
    }

    public static String identifyPathType(String path) {

        // Reference of file
        if (path.endsWith(".py") && path.contains("/")) {
            return "file" + path;
        }

        // Reference of class or function
        if (path.contains(".") && !path.contains(".py") && !path.contains("/")) {
            return traslateToPytest(path);
        }


        return path;
    }
}
