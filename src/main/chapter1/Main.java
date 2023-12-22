import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        PlanningPoker planningPoker = new PlanningPoker();
        List<Estimate> listOfDevelopers = new ArrayList<>();
        listOfDevelopers.add(new Estimate("Ted", 16));
        listOfDevelopers.add(new Estimate("Barney", 8));
        listOfDevelopers.add(new Estimate("Lily", 2));
        listOfDevelopers.add(new Estimate("Robin",4));

        List<Estimate> nullPointerDevelopers = new ArrayList<>();
        nullPointerDevelopers.add(new Estimate("Ross",2));
        nullPointerDevelopers.add(new Estimate("Phoebe",4));
        nullPointerDevelopers.add(new Estimate("Monica", 8));
        nullPointerDevelopers.add(new Estimate("Chandler", 16));

        List<Estimate> nullList = null;
        List<Estimate> emptyList = new ArrayList<>();
        List<Estimate> oneElementList = new ArrayList<>();
        oneElementList.add(new Estimate("Ross",2));

        //System.out.println(planningPoker.identifyExtremes(nullList).toString());
        //System.out.println(planningPoker.identifyExtremes(emptyList).toString());
        System.out.println(planningPoker.identifyExtremes(nullPointerDevelopers).toString());
    }
}