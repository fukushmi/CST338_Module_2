import java.util.Scanner;

public class Assig2
{
    // Ask for user input and validate if it's in range between 0-100
    // Continue asking until input is valid
    public static int getBet()
    {
        System.out.print("How much would you like to bet (1-100)"
           + " or 0 to quit?: ");
        // Get user input bet
        Scanner keyboard = new Scanner(System.in);
        int bet = keyboard.nextInt();
        // Continue looping unless entered value is in range 0-100
        while (true)
        {
            // If the bet is 0-100 then break out of loop
            if (bet == 0)
            {
                break;
            } else if (bet >= 1 && bet <= 100)
            {
                break;
            } else
            {
                System.out.print("Number must be in the range of 0-100, " +
                   "try again: ");
                bet = keyboard.nextInt();
            }
        }
        return bet;
    }
    
    // Reset the string values to "" and initialize with randomly generated
    // string from randString
    public static ThreeString pull()
    {
        ThreeString randomThreeString = new ThreeString();
        randomThreeString.getString1();
        randomThreeString.getString2();
        randomThreeString.getString3();
        // Initialize string
        randomThreeString.setString1(randString());
        randomThreeString.setString2(randString());
        randomThreeString.setString3(randString());
        return randomThreeString;
    }
    
    // Generate string randomly based on probability rules
    private static String randString()
    {
        // Generate random double value
        double randomDouble = Math.random() * 1000;
        // Convert double value to integer
        int randomInt = (int) randomDouble;
        // Initialize randomSelect to "" to start
        String randomSelect = "";
        
        // Probability rules
        // space = 50%
        // cherries = 25%
        // BAR = 12.5%
        // 7 = 12.5%
        if (randomInt <= 500)
        {
            randomSelect = "space";
        } else if (randomInt <= 750)
        {
            randomSelect = "cherries";
        } else if (randomInt <= 875)
        {
            randomSelect = "BAR";
        } else
        {
            randomSelect = "7";
        }
        return randomSelect;
    }
    
    public static int getPayMultiplier(ThreeString thePull)
    {
        int multiplierValue = 0;
        String string1 = thePull.getString1();
        String string2 = thePull.getString2();
        String string3 = thePull.getString3();
        
        if (string1 == "7" && string2 == "7" && string3 == "7")
        {
            multiplierValue = 100;
        } else if (string1 == "BAR" && string2 == "BAR" && string3 == "BAR")
        {
            multiplierValue = 50;
        } else if (string1 == "cherries" && string2 == "cherries" && string3 == "cherries")
        {
            multiplierValue = 30;
        } else if (string1 == "cherries" && string2 == "cherries" && string3 != "cherries")
        {
            multiplierValue = 15;
        } else if (string1 == "cherries" && string2 != "cherries")
        {
            multiplierValue = 5;
        } else
        {
            multiplierValue = 0;
        }
        return multiplierValue;
    }
    
    public static void display(ThreeString thePull, int winnings)
    {
        // Initialize multiplierChecker to result from getPayMultiplier method
        int multiplierChecker = getPayMultiplier(thePull);
        System.out.println("whirrrrrr .... and your pull is ... ");
        System.out.println(thePull.toString());
        // If the multiplierChecker above 0, that means users win some amount
        if (multiplierChecker > 0)
        {
            System.out.println("Congrats, you won " + winnings);
            System.out.println("                             ");
        } else
        {
            System.out.println("Sorry, you lost.");
            System.out.println("                ");
        }
    }
    
    public static void main(String[] args)
    {
        ThreeString pullRecord = new ThreeString();
        // Set quitGame to false
        boolean quitGame = false;
        // Continue to loop while quitGame is false
        while (!quitGame)
        {
            // Initialize validatedBet to result from getBet method
            int validatedBet = getBet();
            if (validatedBet == 0)
            {
                System.out.print(pullRecord.toStringWinnings());
                quitGame = true;
            } else
            {
                // Retrieve pulled strings
                pullRecord = pull();
                
                // Retrieve the multiplier based on the pulled strings
                int multiplier = getPayMultiplier(pullRecord);
                
                // Calculate the winning amount by multiplying bet
                int winnings = validatedBet * multiplier;
                
                // Display pulled strings and winning amount on console
                display(pullRecord, winnings);
              
                if (!pullRecord.saveWinnings(winnings))
                {
                    System.out.print(pullRecord.toStringWinnings());
                }
            }
        }
    }
}

class ThreeString
{
    // Defining and initializing variables
    private String string1, string2, string3;
    // Initialize to 0 as the starting position shall be 0
    private static int numPulls = 0;
    // Initialize to 20, representing the maximum length for each string
    public static final int MAX_LEN = 20;
    // Initialize to 40 as the maximum pull count is 40
    public static final int MAX_PULLS = 40;
    // Initialize integer array with size 40
    private static int[] pullWinnings = new int[MAX_PULLS];
    
    // Constructor to initialize string to ""
    ThreeString()
    {
        string1 = "";
        string2 = "";
        string3 = "";
    }
    
    // Function to validate string
    private boolean validString(String str)
    {
        // Validate if string is not null and its length is less than or equal
        // to MAX_LEN (20)
        if (str != null && str.length() <= MAX_LEN)
        {
            return true;
        } else
        {
            return false;
        }
    }
    
    // Accessor for string 1
    public String getString1()
    {
        return string1;
    }
    
    // Mutator for string 1, if conditions meet under validateString then
    // update the private member variable
    public boolean setString1(String str)
    {
        if (validString(str))
        {
            // Update string variable with incoming data
            string1 = str;
            return true;
        } else
        {
            return false;
        }
    }
    
    // Accessor for string 2
    public String getString2()
    {
        return string2;
    }
    
    // Mutator for string 2, if conditions meet under validateString then
    // update the private member variable
    public boolean setString2(String str)
    {
        if (validString(str))
        {
            string2 = str;
            return true;
        } else
        {
            return false;
        }
    }
    
    // Accessor for string 3
    public String getString3()
    {
        return string3;
    }
    
    // Mutator for string 3, if conditions meet under validateString then
    // update the private member variable
    public boolean setString3(String str)
    {
        if (validString(str))
        {
            string3 = str;
            return true;
        } else
        {
            return false;
        }
    }
    
    // Concatenate three strings
    public String toString()
    {
        return string1 + " " + string2 + " " + string3;
    }
    
    // Return true if new winning is added to the pullWinnings array
    public boolean saveWinnings(int winnings)
    {
        // Validate if current position is less than MAX_PULLS (40)
        if (numPulls < MAX_PULLS)
        {
            pullWinnings[numPulls] = winnings;
            numPulls++;
            return true;
        } else
        {
            return false;
        }
    }
    
    // Iterate through pullWinning array, print each and total win amount
    public String toStringWinnings()
    {
        System.out.println("Thanks for playing at the Casino!");
        System.out.println("You pulled the machine " + numPulls+ " times");
        // Initialize sum to 0 to start
        int sum = 0;
        System.out.println("Your individual winnings were: ");
        // Iterate over all pulls and print each winning amount
        for (int i = 0; i < numPulls; i++)
        {
            sum += pullWinnings[i];
            System.out.print(pullWinnings[i] + " ");
        }
        System.out.println("\n" + "******************************");
        // Return statement with sum winning
        return "Your total winnings were: $" + sum;
    }
}