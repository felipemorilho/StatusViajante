package com.empiricus.statusviajante.doc;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.ExternalDocumentation;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;

@Configuration
public class SwaggerDoc {

    @Bean
    public OpenAPI springBlogPessoalOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("Projeto Statu$: Viajante")
                        .description("Projeto Integrador Java/Kotlin")
                        .version("v0.0.1")
                        .license(new License()
                                .name("Statu$:Viajante")
                                .url("www.statusviajante.com.br"))
                        .contact(new Contact()
                                .name("Admin")
                                .email("admin@gmail.com")))
                .externalDocs(new ExternalDocumentation()
                        .description("Github")
                        .url("https://github.com/felipemorilho/StatusViajante"));
    }
}
