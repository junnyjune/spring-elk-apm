package com.example.springapm;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

  @Bean
  public OpenAPI openAPI() {
    Info info =
            new Info()
                    .title("Moneyball")
                    .version("1.0.0")
                    .description("모든 API는 토큰기반 사용자 인식 및 보안기능을 포함하고 있습니다.");

    return new OpenAPI().components(new Components()).info(info);
  }
}