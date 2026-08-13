package com.example.tutorial.command;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class DestroyCrudCommand {

    private static final String JAVA_PATH =
            "src/main/java/com/example/tutorial";

    public static void main(String[] args) {

        if (args.length == 0) {
            System.out.println("Feature name is required.");
            System.out.println("Example: destroy-crud Product");
            return;
        }

        String name = args[0];

        System.out.println("Destroying CRUD for: " + name);

        deleteFile(
                Paths.get(
                        JAVA_PATH,
                        "entity",
                        name + ".java"
                )
        );

        deleteFile(
                Paths.get(
                        JAVA_PATH,
                        "repository",
                        name + "Repository.java"
                )
        );

        deleteFile(
                Paths.get(
                        JAVA_PATH,
                        "dto",
                        "Request",
                        name + "Request.java"
                )
        );

        deleteFile(
                Paths.get(
                        JAVA_PATH,
                        "dto",
                        "Response",
                        name + "Response.java"
                )
        );

        deleteFile(
                Paths.get(
                        JAVA_PATH,
                        "service",
                        name + "Service.java"
                )
        );

        deleteFile(
                Paths.get(
                        JAVA_PATH,
                        "service",
                        "impl",
                        name + "ServiceImpl.java"
                )
        );

        deleteFile(
                Paths.get(
                        JAVA_PATH,
                        "controller",
                        name + "Controller.java"
                )
        );

        System.out.println(
                "\nDestroy CRUD for " + name + " completed."
        );
    }

    private static void deleteFile(Path path) {

        try {

            if (!Files.exists(path)) {
                System.out.println(
                        "Skipped (not found): " + path
                );
                return;
            }

            Files.delete(path);

            System.out.println(
                    "Deleted: " + path
            );

        } catch (IOException e) {
            throw new RuntimeException(
                    "Failed to delete: " + path,
                    e
            );
        }
    }
}