import java.util.HashMap;
import java.util.Map;

class Question6B {
    public static boolean checkWords(String[] words, String result) {
        int num = 0;
        Map<Character, Integer> digitValues = new HashMap<>();
        String allWords = String.join("", words) + result;

        // assign unique digit values to each character in allWords
        for (int i = 0; i < allWords.length(); i++) {
            char c = allWords.charAt(i);
            if (!digitValues.containsKey(c)) {
                digitValues.put(c, digitValues.size());
            }
        }

        // calculate the sum of each word in words array
        for (String word : words) {
            int wordNum = 0;
            for (int i = 0; i < word.length(); i++) {
                int digitValue = digitValues.get(word.charAt(i));
                wordNum = wordNum * 10 + digitValue;
            }
            num += wordNum;
        }

        // calculate the value of result string
        int resultNum = 0;
        for (int i = 0; i < result.length(); i++) {
            int digitValue = digitValues.get(result.charAt(i));
            resultNum = resultNum * 10 + digitValue;
        }

        return num == resultNum;
    }


    public static void main(String[] args) {
       String[] words = {"SIX", "SEVEN", "SEVEN"};
       String result = "TWENTY";
       boolean isSumEqual = checkWords(words, result);
       System.out.println(isSumEqual); // Output: true
   }
}
