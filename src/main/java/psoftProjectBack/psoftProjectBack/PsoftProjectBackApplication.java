package psoftProjectBack.psoftProjectBack;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;

import psoftProjectBack.psoftProjectBack.filtros.FiltroToken;

@SpringBootApplication
public class PsoftProjectBackApplication {

	@Bean
	public FilterRegistrationBean<FiltroToken> filtroJwt() {

		FilterRegistrationBean<FiltroToken> filterRB = new FilterRegistrationBean<FiltroToken>();

		filterRB.setFilter(new FiltroToken());
		filterRB.addUrlPatterns("/campanha/*");
		return filterRB;

	}

	public static void main(String[] args) {
		SpringApplication.run(PsoftProjectBackApplication.class, args);
	}

}
