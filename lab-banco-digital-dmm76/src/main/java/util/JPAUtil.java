package util;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.HashMap;
import java.util.Map;
public class JPAUtil {
    private static final EntityManagerFactory FACTORY;
    static {
        EnvLoader.load(); // carrega as variáveis do .env
        Map<String, String> props = new HashMap<>();
        props.put("javax.persistence.jdbc.user", System.getProperty("DB_USER"));
        props.put("javax.persistence.jdbc.password", System.getProperty("DB_PASSWORD"));
        FACTORY = Persistence.createEntityManagerFactory("dio_banco", props);
    }
    public static EntityManager getEntityManager() {
        return FACTORY.createEntityManager();
    }
}