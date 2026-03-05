import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * Main application for the Data Analysis Mini‑Project.
 *
 * TODO:
 *  - Update the path to your dataset file
 *  - Read the CSV file using Scanner
 *  - Parse each row and extract the correct columns
 *  - Construct Data objects from each row
 *  - Store them in an array
 *  - Write methods to analyze the dataset (min, max, average, filters, etc.)
 *  - Print insights and answer your guiding question
 *  - Add Javadoc comments for any methods you create
 */
public class App {

    public static void main(String[] args) throws FileNotFoundException {

        // ====== configuration ======
        // path is relative to the working directory when the program runs;
        // in this project we keep the CSV in the top‑level data/ directory
        File file = new File("/workspaces/data-analysis-project-kaylaato/data/StateData2020-CDC-Census.csv");

        // create an array large enough for all states. we will remember how
        // many rows were actually read so we can ignore the unused slots.
        Data[] dataList = new Data[60];  // a bit of buffer for safety

        // TODO: Read file using Scanner
        // - Skip header if needed
        // - Loop through rows
        // - Split each line by commas
        // - Convert text to numbers when needed
        // - Create new Data objects
        // - Add to your array
        Scanner scanner = new Scanner(file); // create scanner to read file
        int index = 0;
        if (scanner.hasNextLine()) {
            scanner.nextLine(); // skip header
        }
        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            String[] columns = line.split(","); // split on comma

            if (columns.length < 7) {
                // skip malformed line but log it for debugging
                System.err.println("skipping malformed line: " + line);
                continue;
            }

            // columns (after header) should be:
            // 0 state, 1 population, 5 overdose‑rate, 6 overdose‑deaths
            String state = columns[0];
            String population = columns[1];
            String overdoseDeathRate = columns[5];
            String drugOverdoseDeaths = columns[6];

            dataList[index] = new Data(state, population, drugOverdoseDeaths, overdoseDeathRate);
            index++;
        }
        scanner.close();

        // create a trimmed array containing only the rows we actually loaded
        Data[] loadedData = java.util.Arrays.copyOf(dataList, index);

        // analysis methods should operate on the trimmed list
        Data maxData = findStateMaxOverdoseDeath(loadedData);
        Data[] statesOver25 = findStatesOver25(loadedData);

        // TODO: Print insights
        System.out.println(maxData.getState() + " has the highest overdose death total among the states in the dataset: "
                + maxData.getDrugOverdoseDeaths() + " deaths.");
        System.out.println("The following states have an overdose death rate greater than 25 per 100,000 people: ");
        for (Data state : statesOver25) {
            System.out.println("- " + state.getState());
        }


        // TODO: Print insights
        // - Number of rows loaded
        // - Min, max, average, or any other findings
        // - Final answer to your guiding question
        System.out.println(maxData.getState() + " has the highest overdose death total among the states in the dataset:" + maxData.getDrugOverdoseDeaths() + " deaths.");
        System.out.println("The following states have an overdose death rate greater than 25 per 100,000 people:");
        for (Data state : statesOver25) {
            System.out.println("- " + state.getState());
        }
    }

    public static Data findStateMaxOverdoseDeath(Data[] dataList) {
        Data maxData = dataList[0];
        for (int i = 1; i < dataList.length; i++) {
            if (dataList[i] != null && dataList[i].getDrugOverdoseDeaths() > maxData.getDrugOverdoseDeaths()) {
                maxData = dataList[i];
            }
        }
        return maxData;
    }

    public static Data[] findStatesOver25(Data[] dataList) {
        // Count how many states have rate > 25
        int count = 0;
        for (int i = 0; i < dataList.length; i++) {
            if (dataList[i] != null && dataList[i].getOverdoseDeathRate() > 25) {
                count++;
            }
        }

        // Create array of correct size and populate it
        Data[] statesOver25 = new Data[count];
        int index = 0;
        for (int i = 0; i < dataList.length; i++) {
            if (dataList[i] != null && dataList[i].getOverdoseDeathRate() > 25) {
                statesOver25[index] = dataList[i];
                index++;
            }
        }
        return statesOver25;
    }

}