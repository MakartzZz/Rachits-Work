package com.mycompany.journeytounemployment;

import java.net.URL;
import javafx.scene.image.Image;
import javafx.scene.media.Media;

public final class ResourceLoader {

    private ResourceLoader() {
    }

    public static Image loadImage(String path) {
        return new Image(toExternalForm(path));
    }

    public static Media loadMedia(String path) {
        return new Media(toExternalForm(path));
    }

    public static String toExternalForm(String path) {
        String normalizedPath = normalize(path);
        URL resource = ResourceLoader.class.getResource(normalizedPath);
        if (resource == null) {
            throw new IllegalArgumentException("No se encontro el recurso: " + path);
        }
        return resource.toExternalForm();
    }

    private static String normalize(String path) {
        String normalizedPath = path.replace("\\", "/").trim();
        if (normalizedPath.startsWith("file:")) {
            normalizedPath = normalizedPath.substring("file:".length());
        }
        if (normalizedPath.startsWith("src/main/resources")) {
            normalizedPath = normalizedPath.substring("src/main/resources".length());
        }
        if (!normalizedPath.startsWith("/")) {
            normalizedPath = "/" + normalizedPath;
        }
        return normalizedPath;
    }
}
