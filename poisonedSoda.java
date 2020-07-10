import java.util.Random;

public class poisonedSoda {
    public static int totalDays = 0;

    public static int findPoisonedBottle(int[] sodaBottles) {
        int poisonBottleNum = 1001;
        int availableTestStrips = 10;
        int bottleNumMin = 0;
        int bottleNumMax = 999;
        while (availableTestStrips > 0)
        {
            int minimumElements = (bottleNumMax - bottleNumMin + 1) / availableTestStrips;
            int remainder = (bottleNumMax - bottleNumMin + 1) % availableTestStrips;
            Runtests:
            for (int i = 0; i < availableTestStrips; i++) {
                // this means that we have more test strips than range of possible poisoned bottles
                // thus we check each bottle one at a time.
                if (minimumElements == 0) {
                    for (int j = bottleNumMin; j < bottleNumMax + 1; j++) {
                        if (sodaBottles[j] == 1) {
                            totalDays += 7;
                            // Found which bottle is poisoned!
                            return j;
                        }
                    }
                }
                // this means we are still narrowing down the number of possible poisoned bottles.
                else {
                    int lowBound = bottleNumMin + (i * minimumElements);
                    int highBound = bottleNumMin + ((i + 1) * minimumElements - 1);
                    // if last for loop iteration then add remainder.
                    if (i == availableTestStrips - 1) {
                        highBound += remainder;
                    }
                    for (int j = lowBound; j < highBound + 1; j++) {
                        // Found poisoned bottle within range.
                        if (sodaBottles[j] == 1) {
                            bottleNumMin = lowBound;
                            bottleNumMax = highBound;
                            availableTestStrips -= 1;
                            totalDays += 7;
                            // this means that only one dropped was used on a test strip.
                            if (lowBound == highBound) {
                                return j;
                            }
                            break Runtests;
                        }
                    }
                }
            }
        }
        return poisonBottleNum;
    }

    public static int[] createSodaBottles() {
        int[] sodaBottles = new int[1000];
        int bottleToPoison = new Random().nextInt(1000);
        sodaBottles[bottleToPoison] = 1;
        return sodaBottles;
    }

    public static void main(String[] args) {
        // only one bottle will have value of 1, which means it is the poisoned bottle.
        int[] sodaBottles = createSodaBottles();
        int thePoisonedBottle = findPoisonedBottle(sodaBottles);
        System.out.println("Bottle number " + thePoisonedBottle + " is poisoned.");
        System.out.println("It took " + totalDays + " days to find.");
    }

}
