package projeto_CRUD;

import ch.qos.logback.core.CoreConstants;
import io.dropwizard.Application;
import io.dropwizard.Configuration;
import io.dropwizard.setup.Environment;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class RestApp extends Application<Configuration> {
    public static void main(String[] args) throws Exception {
        new RestApp().run(new String[] { "server" });
        
        
    }

    @Override
    public void run(Configuration configuration, Environment environment) {
        CarrosDAO dao = new CarrosDAO();
        environment.jersey().register(new CarrosResource(dao));
    }
}


