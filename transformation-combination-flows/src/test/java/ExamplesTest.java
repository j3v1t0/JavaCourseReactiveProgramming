import org.junit.jupiter.api.Test;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import java.time.Duration;
import java.util.Arrays;
import java.util.List;

public class ExamplesTest {
    @Test
    public void transformMap(){
        List<String> names = Arrays.asList("google", "abc","fb","stackoverflow");
        Flux<String> stringFlux = Flux.fromIterable(names)
                .filter(name -> name.length() > 5)
                .map(name -> name.toUpperCase())
                .log();

        StepVerifier.create(stringFlux)
                .expectNext("GOOGLE","STACKOVERFLOW")
                .verifyComplete();
    }

    @Test
    public void testTransformUsingFlatMap(){
        List<String> names = Arrays.asList("google", "abc","fb","stackoverflow");
        Flux<String> stringFlux = Flux.fromIterable(names)
                .filter(name -> name.length() > 5)
                .flatMap(name -> {
                    return Mono.just(name.toUpperCase());
                })
                .log();

        StepVerifier.create(stringFlux)
                .expectNext("GOOGLE","STACKOVERFLOW")
                .verifyComplete();
    }
    @Test
    public void testCombineFlowsUsingMerge(){
        Flux<String> flux01 = Flux.just("Blenders","Old","Johnnie");
        Flux<String> flux02 = Flux.just("Pride","Monk","Walker");

        Flux<String> fluxMerge = Flux.merge(flux01,flux02).log();

        StepVerifier.create(fluxMerge)
                .expectSubscription()
                .expectNext("Blenders","Old","Johnnie","Pride","Monk","Walker")
                .verifyComplete();
    }
    @Test
    public void testCombineFlowsUsingMergeWithDelay(){
        Flux<String> flux01 = Flux.just("Blenders","Old","Johnnie")
                .delayElements(Duration.ofSeconds(1));
        Flux<String> flux02 = Flux.just("Pride","Monk","Walker")
                .delayElements(Duration.ofSeconds(1));

        Flux<String> fluxMerge = Flux.merge(flux01,flux02).log();

        StepVerifier.create(fluxMerge)
                .expectSubscription()
                .expectNextCount(6)
                .verifyComplete();
    }
    @Test
    public void testCombineFlowsUsingConcatWithDelay(){
        Flux<String> flux01 = Flux.just("Blenders","Old","Johnnie")
                .delayElements(Duration.ofSeconds(1));
        Flux<String> flux02 = Flux.just("Pride","Monk","Walker")
                .delayElements(Duration.ofSeconds(1));

        Flux<String> fluxConcat = Flux.concat(flux01,flux02).log();

        StepVerifier.create(fluxConcat)
                .expectSubscription()
                .expectNext("Blenders","Old","Johnnie","Pride","Monk","Walker")
                .verifyComplete();
    }
    @Test
    public void testCombineFlowsUsingZipWithDelay(){
        Flux<String> flux01 = Flux.just("Blenders","Old","Johnnie")
                .delayElements(Duration.ofSeconds(1));
        Flux<String> flux02 = Flux.just("Pride","Monk","Walker")
                .delayElements(Duration.ofSeconds(1));

        Flux<String> fluxZip = Flux.zip(flux01,flux02,(f1,f2) ->{
            return f1.concat(" ").concat(f2);
        }).log();

        StepVerifier.create(fluxZip)
                .expectNext("Blenders Pride","Old Monk","Johnnie Walker")
                .verifyComplete();
    }
}
