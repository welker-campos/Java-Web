package projeto_CRUD;

import io.dropwizard.Application;
import io.dropwizard.Configuration;
import io.dropwizard.setup.Environment;

/**
 *
 * @author F0124929
 */


public class WebRest extends Application<Configuration> {

    public static void main(String[] args) throws Exception {
        new WebRest().run(new String[]{"server"});
    }

    @Override
    public void run(Configuration configuration, Environment environment) {
        environment.jersey().register(new JogoDAO());
    }
}
