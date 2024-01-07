package com.ous.auth2.sociallogin;

import com.ous.auth2.sociallogin.jwt.repo.UserRepository;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

@SpringBootApplication
@EnableJpaRepositories(basePackageClasses = UserRepository.class)
//@EnableScheduling @EnableAsync
public class SocialLoginApplication {

    public static void main(String[] args) {
        SpringApplication.run(SocialLoginApplication.class, args);
    }

    public record  Student(String name, String email, String major){}

//    @Async
////    @Scheduled(fixedDelayString = "PT02S", initialDelay = 1000)
//    @Scheduled(cron = "0 */2 * * * *")
//    public void logMe() throws InterruptedException {
//        Thread.sleep(1000);
//        System.out.println("Hello! I am a scheduler");
//        Thread.sleep(1000);
//        System.out.println("Hello! I am done");
//
//    }
}
