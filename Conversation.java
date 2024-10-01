import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;


public class Conversation {

    // Main Method 
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        
        // Asking user to enter the number of conversation rounds
        System.out.print("How many rounds of conversation do you want? ");
        int rounds = scanner.nextInt();
        scanner.nextLine();  // Consume newline left-over

        // Initializing a list to store the conversation transcript
        List<String> transcript = new ArrayList<>();
        
        // Initial greeting
        System.out.println("Hello. What's on your mind?");
        transcript.add("Hello. What's on your mind?");

        // Loop for the specified number of rounds
        for (int i = 0; i < rounds; i++) {
            String userInput = scanner.nextLine();
            transcript.add("User: " + userInput);

            String response;
            // Checking if the input contains words that need to be mirrored
            if (containsMirrorableWords(userInput)) {
                response = mirrorWords(userInput);
            } else {
                response = getRandomCannedResponse();
            }

            // Print and add the response to the transcript
            System.out.println(response);
            transcript.add("Bot: " + response);
        }

        // Goodbye message and transcript printing
        System.out.println("It was a nice convo. But I have to go charge myself now :3. See yaaa!");
        transcript.add("It was a nice convo. But I have to go charge myself now :3. See yaaa!");

        System.out.println("\nTRANSCRIPT:");
        for (String full_transcript : transcript) {
            System.out.println(full_transcript);
        }

        // Close the scanner to prevent memory leaks
        scanner.close();
    }

    // Function to mirror specific words in the user's input
    private static String mirrorWords(String my_input) {
        // Map to hold word pairs for mirroring
        Map<String, String> mirrorWordsMap = new HashMap<>();
        mirrorWordsMap.put("I", "you");
        mirrorWordsMap.put("me", "you");
        mirrorWordsMap.put("am", "are");
        mirrorWordsMap.put("you", "I");
        mirrorWordsMap.put("my", "your");
        mirrorWordsMap.put("your", "my");
        

        // Splitting the input into words
        String[] words = my_input.split(" ");
        StringBuilder mirroredResponse = new StringBuilder();

        // Looping through each word to check for replacements
        for (String word : words) {
            // Replace the word if it's found in the map
            if (mirrorWordsMap.containsKey(word)) {
                mirroredResponse.append(mirrorWordsMap.get(word)).append(" ");
            } else {
                mirroredResponse.append(word).append(" ");
            }
        }

        // Return the final mirrored response as a trimmed string
        return mirroredResponse.toString().trim();
    }



    // Method to check if the input contains any words that should be mirrored
    private static boolean containsMirrorableWords(String input) {
        String[] mirrorableWords = {"I", "me", "am", "you", "my", "your"};
        for (String word : mirrorableWords) {
            if (input.contains(word)) {
                return true;
            }
        }
        return false;
    }



    // Method to return a random canned response
    private static String getRandomCannedResponse() {
        String[] responses = {
            "Interesting! Tell me more.",
            "Hmm, I see.",
            "I see, what else is on your mind?"
        };
        int index = (int) (Math.random() * responses.length);
        return responses[index];
    }
}
