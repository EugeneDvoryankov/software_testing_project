public class Estimate {
    private String name;
    private int estimate;

    public Estimate(String name, int estimate) {
        this.name = name;
        this.estimate = estimate;
    }

    public String getDeveloper() {
        return name;
    }

    public int getEstimate() {
        return estimate;
    }
}
