package pl.meklad.ipezput2k20.config;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Create by dev on 13.10.2020
 */
@Configuration
public class ModelMapperConfig {
    @Bean
    @Qualifier(value = "DefaultMapper")
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }
}
