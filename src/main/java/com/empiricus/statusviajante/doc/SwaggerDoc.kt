package com.empiricus.statusviajante.doc

import io.swagger.v3.oas.models.ExternalDocumentation
import io.swagger.v3.oas.models.OpenAPI
import io.swagger.v3.oas.models.info.Contact
import io.swagger.v3.oas.models.info.Info
import io.swagger.v3.oas.models.info.License
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
open class SwaggerDoc {
    @Bean
    open fun springBlogPessoalOpenAPI(): OpenAPI {
        return OpenAPI()
            .info(
                Info().apply{
                    title = "Projeto Statu$: Viajante"
                    description = "Projeto Integrador Java/Kotlin"
                    version = "v0.0.2"
                }
                    .license(
                        License().apply{
                            name = "Statu$:Viajante"
                            url = "www.statusviajante.com.br"
                        })
                    .contact(
                        Contact().apply {
                            name = "Admin"
                            email = "admin@gmail.com"
                        }
            )
            )
            .externalDocs(
                ExternalDocumentation().apply{
                    description = "GitHub"
                    url = "https://github.com/felipemorilho/StatusViajante"
                }

            )
    }
}