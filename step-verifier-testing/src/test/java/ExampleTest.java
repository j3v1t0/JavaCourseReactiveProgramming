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
    @Test
    public void StringFluxTesting(){
        Flux<String> stringFlux = Flux.just(
                "Jessica",
                "John",
                "Tomas",
                "Melissa",
                "Steve",
                "Megan",
                "Monica",
                "Henry")
                .filter(name -> name.length() <= 5) //
                .map(String::toUpperCase);

        StepVerifier.create(stringFlux)
                .expectNext("JOHN")
                .expectNext("TOMAS")
                .expectNextMatches(name -> name.startsWith("ST"))
                .expectNext("MEGAN")
                .expectNext("HENRY")
                .expectComplete()
                .verify();
    }
}
