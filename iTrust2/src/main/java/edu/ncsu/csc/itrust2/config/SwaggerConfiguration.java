package edu.ncsu.csc.itrust2.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfiguration {
    @Bean
    public OpenAPI api() {
        var info =
                new Info()
                        .description(
                                """
                        ### 인증

                        1. 다른 탭에 iTrust2를 띄웁니다.
                        2. 로그인합니다.
                        3. 끝입니다. 로그인 정보가 그대로 적용됩니다.

                        [GET /api/v1/role](/iTrust2/swagger-ui/index.html#/api-user-controller/getRole) 을 실행해보시면 로그인한 계정의 권한이 그대로 표시되는 것을 확인하실 수 있습니다.
                        """
                                        .trim());

        return new OpenAPI().info(info);
    }
}
