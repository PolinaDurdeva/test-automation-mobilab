package de.mobilab.api.configurations;

import org.aeonbits.owner.Config;
import org.aeonbits.owner.Config.LoadPolicy;
import org.aeonbits.owner.Config.LoadType;
import org.aeonbits.owner.Config.Sources;

@LoadPolicy(LoadType.MERGE)
@Sources({"system:properties",
        "classpath:apitest.properties"})
public interface TestConfiguration extends Config {

    @Key("app.url")
    String apiUrl();

}
