package bitcamp.bootapp.config;

import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

//@Configuration
// 이 클래스는 설정에 관련된 일을 하는 클래스임을 선언한다
// 즉 스프링 컴포넌트로 표시하는 것이다
// spring IoC 컨테이너는 이 설정이 붙은 클래스의 인스턴스를 자동 생성한다

// @EnableWebMvc
// spring 프레임워크에서 WebMVC 기능을 사용할 수 있도록 관련 객체를 준비시키라고 선언한다
// WebMVC 관련 객체가 완전하게 구비되어야 제대로 된 웹 애플리케이션을 작성할 수 있다
// Spring Boot 의 경우 이 annotation 을 생략해도 된다

public class WebConfig implements WebMvcConfigurer{
  // 이 클래스는 WebMvcConfigurer 규칙에 따라 메서드를 만들었음을 선언한다
  // 단 모든 메서드를 정의할 필요는 없고 고객 입맛에 맞춰 설정한다
  // spring WebMVC 프레임워크는 이 클래스의 정의된 메서드를 호출하여 설정을 완성한다

  public WebConfig() {
    System.out.println("WebConfig 객체 생성됨");
  }

  // CrossOrigin 관련해서 기본 값 외에 추가로 설정할 게 있다면 이 메서드를 정의한다
  // SpringBoot 가 시작되면 이 메서드를 호출하여 CrossOrigin 을 설정한다
  @Override
  public void addCorsMappings(CorsRegistry registry) {
    registry.addMapping("/**")
    .allowedOrigins("http://localhost:5500", "http://127.0.0.1:5500")
    .allowedMethods("*");
  }
}
