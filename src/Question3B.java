
//you are provided certain string and pattern, return true if pattern entirely matches the string otherwise return false.
//        Note: if pattern contains char @ it matches entire sequence of characters and # matches any single character within
//        string.
//        Input: String a=“tt”, pattern =”@”
//        Output: true
//        Input: String a=“ta”, pattern =”t”
//        Output: false
//        Input: String a=“ta”, pattern =”t#”
//        Output: true

public class Question3B {
    // method to check if the given string matches the given pattern
    public static boolean isMatch(String str, String pattern) {
        // initialize variables for string and pattern indexes, and string and pattern lengths
        int strIndex = 0, patternIndex = 0;
        int strLen = str.length(), patternLen = pattern.length();

        // loop through the string and pattern while the indexes are less than their respective lengths
        while (strIndex < strLen && patternIndex < patternLen) {
            // if pattern contains '@', match the entire string
            if (pattern.charAt(patternIndex) == '@') {
                return true;
            }
            // if pattern contains '#', match any single character
            else if (pattern.charAt(patternIndex) == '#') {
                patternIndex++;
                strIndex++;
            }
            // if characters match, move to next index
            else if (str.charAt(strIndex) == pattern.charAt(patternIndex)) {
                strIndex++;
                patternIndex++;
            }
            // if characters do not match, return false
            else {
                return false;
            }
        }

        // return true if both indexes reach the end
        return strIndex == strLen && patternIndex == patternLen;
    }

    public static void main(String[] args) {
        // example inputs and expected outputs
        String a1 = "tt", pattern1 = "@";
        String a2 = "ta", pattern2 = "t";
        String a3 = "ta", pattern3 = "t#";

        // test the isMatch method with the example inputs and print the results
        System.out.println(isMatch(a1, pattern1)); // expected output: true
        System.out.println(isMatch(a2, pattern2)); // expected output: false
        System.out.println(isMatch(a3, pattern3)); // expected output: true
    }
}
