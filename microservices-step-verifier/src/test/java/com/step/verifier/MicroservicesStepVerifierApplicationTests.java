package com.step.verifier;

import com.step.verifier.services.SampleService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import java.time.Duration;

@SpringBootTest
class MicroservicesStepVerifierApplicationTests {

	@Autowired
	SampleService sampleService;
	@Test
	void testMono(){
		Mono<String> one = sampleService.findOne();
		StepVerifier.create(one)
				.expectNext("Hi!")
				.verifyComplete();
	}
	@Test
	void testAll(){
		Flux<String> all = sampleService.findAll();
		StepVerifier.create(all)
				.expectNext("Hi!")
				.expectNext("How")
				.expectNext("Are")
				.expectNext("You")
				.verifyComplete();
	}
	@Test
	void testAllSlowly(){
		Flux<String> allSlowly = sampleService.findAllSlowly();
		StepVerifier.create(allSlowly)
				.expectNext("Hi!")
				.thenAwait(Duration.ofSeconds(1))
				.expectNext("How")
				.thenAwait(Duration.ofSeconds(1))
				.expectNext("Are")
				.thenAwait(Duration.ofSeconds(1))
				.expectNext("You")
				.thenAwait(Duration.ofSeconds(1))
				.verifyComplete();
	}

}
