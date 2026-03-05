/**
 * Represents one row from your dataset.
 *
 * TODO:
 *  - Rename the class to match your dataset (e.g., Pokemon, StateData, CountryStat)
 *  - Add at least 3 private attributes based on your CSV columns
 *  - Write a constructor that initializes all attributes
 *  - Add getter methods for the attributes you need in your analysis
 *  - Override toString() to display the object's data
 *  - Add Javadoc comments for the class and all methods
 */
public class Data {

    // TODO: Add at least 3 private attributes
    // Example:
    // private String name;
    // private int population;
    // private double value;
    private String state; //state name
    private int population; //state population
    private int drugOverdoseDeaths; //number of drug overdose deaths in the state
    private double overdoseDeathRate; //calculated overdose death rate per 100,000 people

    // TODO: Create a constructor that takes all attributes as parameters
    public Data(String state, String population, String drugOverdoseDeaths, String overdoseDeathRate) { 
        this.state = state;
        this.population = Integer.parseInt(population);
        this.drugOverdoseDeaths = Integer.parseInt(drugOverdoseDeaths);
        this.overdoseDeathRate = Double.parseDouble(overdoseDeathRate);
    }

    // TODO: Add getters for attributes you need
    /**
     * Getter for the state name.
     * @return state name as a String
     */
    public String getState() { //
        return state;
    }

    /**
     * Getter for the population.
     * @return population as an int
     */
    public int getPopulation() {
        return population;
    }

    /**
     * Getter for the number of drug overdose deaths.
     * @return number of drug overdose deaths as an int
     */
    public int getDrugOverdoseDeaths() {
        return drugOverdoseDeaths;
    }

    /**
     * Getter for the overdose death rate per 100,000 people.  
     * @return overdose death rate per 100,000 people
     */
    public double getOverdoseDeathRate() {
        return overdoseDeathRate;
    }

    // TODO: Add other data analysis methods
    /**
     * Check if the overddose death rate is aboove a certain number
     * @param number
     * @return true if the overdose death rate is above the given number, false otherwise
     */
    public boolean hasHighOverdoseDeathRate(double number) {
        return overdoseDeathRate > number;
    }

    
    // TODO: Override toString() to return a readable representation of your object
    /**
     * Prints out object to be readable
     */
    @Override
    public String toString() {
        return "Data{" +
                "state='" + state + '\'' +
                ", population=" + population +
                ", drugOverdoseDeaths=" + drugOverdoseDeaths +
                '}';
    }

}