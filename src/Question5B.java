import java.util.ArrayList;

//Assume an electric vehicle must go from source city s to destination city d. You can locate many service centers
//        along the journey that allow for the replacement of batteries; however, each service center provides batteries with a
//        specific capacity. You are given a 2D array in which servicecenter[i]=[xi,yj] indicates that the ith service center is
//        xi miles from the source city and offers yj miles after the automobile can travel after replacing batteries at specific
//        service centers. Return the number of times the car's batteries need to be replaced before arriving at the destination.
//        Input: serviceCenters = [{10,60},{20,30},{30,30},{60,40}], targetMiles= 100, startChargeCapacity = 10
//        Output: 2
//        Explanation: The car can go 10 miles on its initial capacity; after 10 miles, the car replaces batteries with a
//        capacity of 60 miles; and after travelling 50 miles, at position 60 we change batteries with a capacity of 40 miles;
//        and ultimately, we may arrive at our destination.

import java.util.ArrayList;

public class Question5B {

    // This method takes in a 2D array of service center distances and capacities,
    // a target number of miles the car needs to travel, and the initial charge
    // capacity of the car's battery. It calculates the number of times the car's
    // batteries need to be replaced in order to travel the target number of miles.
    public int numBatteryReplacements(int[][] serviceCenters, int targetMiles, int startChargeCapacity) {
        int count = 0;
        int currentMiles = startChargeCapacity;
        ArrayList<Integer> distances = new ArrayList<>();
        ArrayList<Integer> capacities = new ArrayList<>();

        // Extract the distances and capacities from the service center array
        for (int[] serviceCenter : serviceCenters) {
            distances.add(serviceCenter[0]);
            capacities.add(serviceCenter[1]);
        }

        // Iterate through the service center distances and compare them to
        // the current number of miles the car can travel on its current battery
        // charge. If the distance is greater, replace the battery and increment
        // the count.
        for (int i = 0; i < distances.size(); i++) {
            if (distances.get(i) > currentMiles) {
                currentMiles = capacities.get(i - 1);
                count++;
            }
        }

        // If the car still needs to travel more miles and the battery charge
        // is not enough, replace the battery one more time.
        if (currentMiles < targetMiles) {
            count++;
        }

        // Return the number of battery replacements needed.
        return count;
    }


    public static void main(String[] args) {
        // Sample input service center array, target miles, and initial charge capacity
        int [][] serviceCenterList={{10,60},{20,30},{30,30},{60,40}};
        int targetMiles = 100;
        int startChargeCapacity = 10;

        // Create a new instance of the Question5B class and calculate the
        // number of battery replacements needed using the numBatteryReplacements method.
        Question5B question1 = new Question5B();
        int finalAnswer = question1.numBatteryReplacements(serviceCenterList, targetMiles, startChargeCapacity);

        // Print out the result.
        System.out.println("The car's batteries need to be replaced: " + finalAnswer + " times.");

    }
}
