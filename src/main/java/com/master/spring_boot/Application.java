package com.master.spring_boot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import java.util.concurrent.TimeUnit;

@SpringBootApplication
@EnableScheduling
@EnableAsync
public class Application {
	private boolean hasRun = false;

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

	@Scheduled(
			fixedRate = 5,
			timeUnit = TimeUnit.SECONDS
	)
	@Async
	public void sendEmail() throws InterruptedException {

		System.out.println("Start Sending Email 📨");
		Thread.sleep(2000);
		System.out.println("End Sending Email... 📪");

	}

	@Scheduled(
			fixedRate = 5,
			timeUnit = TimeUnit.SECONDS
	)
	@Async
	public void sendNotic() throws InterruptedException {
		System.out.println("Notice Email 📨");
		Thread.sleep(2000);
		Thread.currentThread().interrupt();
	}


}

//@Component
//class EmailScheduler {
//
//	private boolean hasRun = false;
//	@Scheduled(fixedDelay = Long.MAX_VALUE, initialDelay = 10000)
//	public void sendEmail() {
//		if (!hasRun) {
//			System.out.println("Start Sending Email 📨");
//			try {
//				Thread.sleep(2000);
//			} catch (InterruptedException e) {
//				Thread.currentThread().interrupt();
//			}
//			System.out.println("End Sending Email... 📪");
//			hasRun = true; // Ensure this is run only once
//		}
//	}
//}
