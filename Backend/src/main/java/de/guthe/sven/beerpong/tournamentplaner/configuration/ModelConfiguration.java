package de.guthe.sven.beerpong.tournamentplaner.configuration;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ModelConfiguration {

	@Bean
	public ModelMapper modelMapper() {
		return new ModelMapper();
	}

}