package Arrays;

import java.util.*;



public class WordGuessingGame {
    private static Map<String, String> wordsWithClues = new HashMap<>();

    static {
        wordsWithClues.put("apple", "A sweet and crunchy fruit");
        wordsWithClues.put("mountain", "A large landform that rises prominently above its surroundings");
        wordsWithClues.put("ocean", "A vast and deep body of saltwater");
        wordsWithClues.put("guitar", "A musical instrument with six strings, played by strumming or plucking");
        wordsWithClues.put("sunflower", "A tall, yellow-flowered plant with a large central disk");
        wordsWithClues.put("bookshelf", "A piece of furniture for storing books");
        wordsWithClues.put("elephant", "A large mammal with tusks and a long trunk");
        wordsWithClues.put("rainbow", "A meteorological phenomenon that is caused by reflection, refraction, and dispersion of light");
        wordsWithClues.put("telescope", "An optical instrument that gathers and magnifies distant light");
        wordsWithClues.put("bicycle", "A two-wheeled vehicle powered by pedaling");
    }

    public static void main(String[] args) {
        playWordGuessGame();
    }

    public static void playWordGuessGame() {
        Random random = new Random();
        List<String> keysAsArray = new ArrayList<>(wordsWithClues.keySet());
        String wordToGuess = keysAsArray.get(random.nextInt(keysAsArray.size()));
        String clue = wordsWithClues.get(wordToGuess);

        Set<Character> guessedLetters = new HashSet<>();
        int score = 0;
        int attemptsLeft = 6;

        System.out.println("\nWelcome to the Word Guess Game :)");
        System.out.println("\nClue: " + clue);

        while (attemptsLeft > 0) {
            System.out.println(displayMaskedWord(wordToGuess, guessedLetters));
            Scanner scanner = new Scanner(System.in);
            System.out.print("Guess the letter: ");
            String guess = scanner.nextLine().toLowerCase();

            if (guess.length() == 1 && Character.isLetter(guess.charAt(0))) {
                char guessedLetter = guess.charAt(0);
                if (guessedLetters.contains(guessedLetter)) {
                    System.out.println("Hey! Already Guessed.");
                } else if (wordToGuess.toLowerCase().indexOf(guessedLetter) != -1) {
                    guessedLetters.add(guessedLetter);
                    int occurrence = 0;
                    for (char letter : wordToGuess.toLowerCase().toCharArray()) {
                        if (letter == guessedLetter) {
                            occurrence++;
                        }
                    }
                    score += 10 * occurrence;
                    String[] cheers = {"Good", "Great", "Awesome"};
                    System.out.println(cheers[random.nextInt(cheers.length)] + " guess!");
                } else {
                    attemptsLeft--;
                    System.out.println("Remaining attempts: " + attemptsLeft);
                }
            } else {
                System.out.println("Please enter a single letter.");
            }

            if (guessedLetters.containsAll(convertStringToSet(wordToGuess.toLowerCase()))) {
                System.out.println("Congratulations! You guessed the word '" + wordToGuess + "'");
                System.out.println("Your score is " + score);
                break;
            }
        }

        if (attemptsLeft == 0) {
            System.out.println("Attempts ran out");
            System.out.println("Your score is " + score + " and the word is " + wordToGuess);
        }
    }

    public static String displayMaskedWord(String word, Set<Character> guessedLetters) {
        StringBuilder maskedWord = new StringBuilder();
        for (char letter : word.toCharArray()) {
            if (guessedLetters.contains(Character.toLowerCase(letter))) {
                maskedWord.append(letter);
            } else {
                maskedWord.append("_");
            }
        }
        return maskedWord.toString();
    }

    public static Set<Character> convertStringToSet(String word) {
        Set<Character> charSet = new HashSet<>();
        for (char letter : word.toCharArray()) {
            charSet.add(letter);
        }
        return charSet;
    }
}
