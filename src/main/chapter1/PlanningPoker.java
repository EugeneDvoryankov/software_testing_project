import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import net.jqwik.api.*;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.*;

//<!-- https://mvnrepository.com/artifact/org.assertj/assertj-core -->

public class
PlanningPoker {

    // Listing 1.4 Test cases for null, an empty list, and a one-element list
    @Test
    void rejectNullInput() {
        //asserts that this assertion is an IllegalArgumentException
        assertThatThrownBy(
                () -> new PlanningPoker().identifyExtremes(null)
        ).isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void rejectEmptyList(){
    /**
     * Similar to the earlier test,
     * ensures that the program throws an exception
     * if an empty list of estimates is passed as input
     */
        assertThatThrownBy(() -> {
            List<Estimate> emptyList = Collections.emptyList();
            new PlanningPoker().identifyExtremes(emptyList);
        }).isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void rejectSingleEstimate() {
        /**
         * Ensures that the program throws an exception
         * if a list with a single estimate is passed
         */
        assertThatThrownBy(() -> {
            List<Estimate> list = Arrays.asList(new Estimate("Eleanor", 1));
            new PlanningPoker().identifyExtremes(list);
        }).isInstanceOf(IllegalArgumentException.class);
    }

    //-------------------------------------------------------------------

    // Listing 1.5 Test case for a list with two elements
    @Test
    void twoEstimates(){
        List<Estimate> list = Arrays.asList(
                new Estimate("Mauricio", 10),
                new Estimate("Frank", 5)
        );

        List<String> devs = new PlanningPoker()
                .identifyExtremes(list);

        assertThat(devs)
                .containsExactlyInAnyOrder("Mauricio","Frank");
    }

    @Test
    void manyEstimates(){
        List<Estimate> list = Arrays.asList(
                new Estimate("Mauricio", 10),
                new Estimate("Arie", 5),
                new Estimate("Frank", 7)
        );

        List<String> devs = new PlanningPoker()
                .identifyExtremes(list);

        assertThat(devs)
                .containsExactlyInAnyOrder("Mauricio", "Arie");
    }
    //-------------------------------------------------------------------

    //Listing 1.6 Property-based testing for multiple estimates
    @Property
    void inAnyOrder(@ForAll("estimates") List<Estimate> estimates){
        estimates.add(new Estimate("MrLowEstimate", 1));
        estimates.add(new Estimate("MsHighEstimate", 100));

        Collections.shuffle(estimates);

        List<String> dev = new PlanningPoker().identifyExtremes(estimates);

        assertThat(dev)
                .containsExactlyInAnyOrder("MrLowEstimate", "MsHighEstimate");
    }

    /**
     * //Method that provides a random list of estimates for the property-based test
     * @return random list of estimates for the property-based test
     */
    @Provide
    Arbitrary<List<Estimate>> estimates(){

        Arbitrary<String> names = Arbitraries.strings()
                .withCharRange('a', 'z').ofLength(5);

        Arbitrary<Integer> values = Arbitraries.integers().between(2,99);

        Arbitrary<Estimate> estimates = Combinators.combine(names,values)
                .as((name, value) -> new Estimate(name, value));

        return estimates.list().ofMinSize(1);
    }
    //-------------------------------------------------------------------

    // Listing 1.7 Ensuring that the first duplicate developer is returned
    /*
    Declares a list of estimates
    with repeated estimate values
     */
    @Test
    void developersWithSameEstimates(){
        List<Estimate> list = Arrays.asList(
                new Estimate("Mauricio", 10),
                new Estimate("Arie", 5),
                new Estimate("Andy", 10),
                new Estimate("Frank", 7),
                new Estimate("Annibale", 5)
        );
        List<String> devs = new PlanningPoker().identifyExtremes(list);

        assertThat(devs)
                .containsExactlyInAnyOrder("Mauricio", "Arie");
    }
    //-------------------------------------------------------------------

    // Listing 1.9 Testing for an empty list if the estimates are all the same
    @Test
    void allDevelopersWithTheSameEstimate(){
        List<Estimate> list = Arrays.asList(
                new Estimate("Mauricio", 10),
                new Estimate("Arie", 10),
                new Estimate("Andy", 10),
                new Estimate("Frank", 10),
                new Estimate("Annibale", 10)
        );

        List<String> devs = new PlanningPoker().identifyExtremes(list);

        assertThat(devs).isEmpty();
    }

    //-------------------------------------------------------------------

    public List<String> identifyExtremes(List<Estimate> estimates) {
        /* Listing 1.3 Adding validation to prevent invalid inputs
        */
        if(estimates == null){
            throw new IllegalArgumentException("Estimates cannot be null");
        }
        if(estimates.size() <= 1){
            throw new IllegalArgumentException("There has to be more than 1 " +
                    "estimate in the list");
        }

        Estimate lowestEstimate = null;
        Estimate highestEstimate = null;

        for(Estimate estimate : estimates){
            if(highestEstimate == null ||
                estimate.getEstimate() > highestEstimate.getEstimate()){
                highestEstimate = estimate;
            }
            if(lowestEstimate == null ||
                estimate.getEstimate() < lowestEstimate.getEstimate()){
                lowestEstimate = estimate;
            }
        }

        // Listing 1.8 Returning an empty list if all estimates are the same
        if(lowestEstimate.equals(highestEstimate)){
            return Collections.emptyList();
        }

        return Arrays.asList(
                lowestEstimate.getDeveloper(),
                highestEstimate.getDeveloper()
        );
    }
}
