import org.junit.Test;
import reactor.core.publisher.Flux;
import reactor.test.StepVerifier;

public class ExampleTest {

    @Test
    public void testFlux(){
        Flux<Integer> integerFlux = Flux.just(1,2,3,4,5);

        //Create the Flux test
        StepVerifier.create(integerFlux)
                .expectNext(1)
                .expectNext(2)
                .expectNext(3)
                .expectNext(4)
                .expectNext(5)
                .expectComplete()
                .verify();
    }
}
