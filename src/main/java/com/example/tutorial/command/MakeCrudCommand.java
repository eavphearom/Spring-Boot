package com.example.tutorial.command;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class MakeCrudCommand {

    private static final String BASE_PACKAGE = "com.example.tutorial";

    private static final String JAVA_PATH =
            "src/main/java/com/example/tutorial";

    private static final String STUB_PATH =
            "src/main/resources/stubs";


    public static void main(String[] args) {

        // Example:
        // make-crud Product
        // make-crud Role --simple

        if (args.length == 0) {
            System.out.println("Feature name is required.");
            System.out.println("Example: make-crud Product");
            return;
        }

        String name = args[0];

        boolean simple = false;

        for (String arg : args) {
            if ("--simple".equalsIgnoreCase(arg)) {
                simple = true;
                break;
            }
        }

        System.out.println("Generating CRUD for: " + name);

        if (simple) {
            System.out.println("Mode: Simple Entity + JpaRepository");
        } else {
            System.out.println("Mode: BaseEntity + BaseRepository");
        }

        generateEntity(name, simple);
        generateRepository(name, simple);
        generateRequest(name);
        generateResponse(name);

        generateService(name);
        generateServiceImpl(name, simple);
        generateController(name);

        System.out.println(
                "\nCRUD for " + name + " generated successfully."
        );
    }


    /**
     * Generate Entity
     */
    private static void generateEntity(
            String name,
            boolean simple
    ) {

        try {

            // Choose stub
            String stubName = simple
                    ? "entity-simple.stub"
                    : "entity.stub";

            Path stubPath = Paths.get(
                    STUB_PATH,
                    stubName
            );

            // Check stub exists
            if (!Files.exists(stubPath)) {
                System.out.println(
                        "Stub not found: " + stubPath
                );
                return;
            }

            // Read stub
            String content = Files.readString(stubPath);

            // ProductCategory -> product_categories
            String tableName = toTableName(name);

            // Replace placeholders
            content = content
                    .replace("{{package}}", BASE_PACKAGE)
                    .replace("{{name}}", name)
                    .replace("{{tableName}}", tableName);

            // Output:
            // entity/Product.java

            Path outputPath = Paths.get(
                    JAVA_PATH,
                    "entity",
                    name + ".java"
            );

            // Don't overwrite existing entity
            if (Files.exists(outputPath)) {
                System.out.println(
                        "Entity already exists: " + outputPath
                );
                return;
            }

            // Make directory if missing
            Files.createDirectories(
                    outputPath.getParent()
            );

            // Create file
            Files.writeString(
                    outputPath,
                    content
            );

            System.out.println(
                    "Created: " + outputPath
            );

        } catch (IOException e) {

            throw new RuntimeException(
                    "Failed to generate entity: " + name,
                    e
            );
        }
    }

    private static void generateRepository(
            String name,
            boolean simple
    ) {

        try {

            String stubName = simple
                    ? "repository-simple.stub"
                    : "repository.stub";

            Path stubPath = Paths.get(
                    STUB_PATH,
                    stubName
            );

            if (!Files.exists(stubPath)) {
                System.out.println(
                        "Stub not found: " + stubPath
                );
                return;
            }

            String content = Files.readString(stubPath);

            content = content
                    .replace("{{package}}", BASE_PACKAGE)
                    .replace("{{name}}", name);

            Path outputPath = Paths.get(
                    JAVA_PATH,
                    "repository",
                    name + "Repository.java"
            );

            if (Files.exists(outputPath)) {
                System.out.println(
                        "Repository already exists: " + outputPath
                );
                return;
            }

            Files.createDirectories(
                    outputPath.getParent()
            );

            Files.writeString(
                    outputPath,
                    content
            );

            System.out.println(
                    "Created: " + outputPath
            );

        } catch (IOException e) {

            throw new RuntimeException(
                    "Failed to generate repository: " + name,
                    e
            );
        }
    }

    private static void generateRequest(String name) {
        generateFromStub(
                "request.stub",
                Paths.get(
                        JAVA_PATH,
                        "dto",
                        "Request",
                        name + "Request.java"
                ),
                name
        );
    }

    private static void generateResponse(String name) {
        generateFromStub(
                "response.stub",
                Paths.get(
                        JAVA_PATH,
                        "dto",
                        "Response",
                        name + "Response.java"
                ),
                name
        );
    }

    private static void generateFromStub(
            String stubName,
            Path outputPath,
            String name
    ) {
        try {

            String route = toRouteName(name);

            Path stubPath = Paths.get(
                    STUB_PATH,
                    stubName
            );

            if (!Files.exists(stubPath)) {
                System.out.println(
                        "Stub not found: " + stubPath
                );
                return;
            }

            if (Files.exists(outputPath)) {
                System.out.println(
                        "Already exists: " + outputPath
                );
                return;
            }

            String content = Files.readString(stubPath);

            content = content
                    .replace("{{package}}", BASE_PACKAGE)
                    .replace("{{name}}", name)
                    .replace("{{nameLower}}", lowerFirst(name))
                    .replace("{{route}}", route);

            Files.createDirectories(
                    outputPath.getParent()
            );

            Files.writeString(
                    outputPath,
                    content
            );

            System.out.println(
                    "Created: " + outputPath
            );

        } catch (IOException e) {
            throw new RuntimeException(
                    "Failed to generate: " + outputPath,
                    e
            );
        }
    }

    private static String lowerFirst(String value) {
        return Character.toLowerCase(value.charAt(0))
                + value.substring(1);
    }

    private static void generateService(String name) {
        generateFromStub(
                "service.stub",
                Paths.get(
                        JAVA_PATH,
                        "service",
                        name + "Service.java"
                ),
                name
        );
    }

    private static void generateServiceImpl(
            String name,
            boolean simple
    ) {

        String stubName = simple
                ? "service-impl-simple.stub"
                : "service-impl.stub";

        generateFromStub(
                stubName,
                Paths.get(
                        JAVA_PATH,
                        "service",
                        "impl",
                        name + "ServiceImpl.java"
                ),
                name
        );
    }

    private static void generateController(String name) {

        generateFromStub(
                "controller.stub",
                Paths.get(
                        JAVA_PATH,
                        "controller",
                        name + "Controller.java"
                ),
                name
        );
    }

    private static String toRouteName(String name) {

        return name
                .replaceAll(
                        "([a-z0-9])([A-Z])",
                        "$1-$2"
                )
                .toLowerCase();
    }

    /**
     * Convert class name to table name
     * <p>
     * Product       -> products
     * CustomerType  -> customer_types
     * SalePerson    -> sale_persons
     */
    private static String toTableName(String name) {

        String snakeCase = name
                .replaceAll(
                        "([a-z0-9])([A-Z])",
                        "$1_$2"
                )
                .toLowerCase();

        return snakeCase + "s";
    }
}