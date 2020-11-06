package pl.meklad.ipezput2k20;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class IpezPut2020Application {

    public static void main(String[] args) {
        SpringApplication.run(IpezPut2020Application.class, args);
    }

    @Bean
    @Qualifier(value = "DefaultMapper")
    public ModelMapper getModelMapper() {
        ModelMapper modelmapper =new ModelMapper();
        modelmapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
        return modelmapper;
    }
}
