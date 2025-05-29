package util;
import java.io.BufferedReader;
import java.io.InputStreamReader;
public class EnvLoader {
    public static void load() {
        try (BufferedReader reader = new BufferedReader(
                new InputStreamReader(EnvLoader.class.getClassLoader().getResourceAsStream(".env")))) {
            String line;
            while ((line = reader.readLine()) != null) {
                if (!line.contains("=")) continue;
                String[] parts = line.split("=", 2);
                if (parts.length == 2) {
                    System.setProperty(parts[0].trim(), parts[1].trim());
                }
            }
        } catch (Exception e) {
            System.err.println("Erro ao carregar .env do classpath: " + e.getMessage());
        }
    }
}