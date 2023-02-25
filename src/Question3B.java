public class Question3B {
    public static boolean match(String a, String pattern) {
        int i = 0, j = 0;
        while (i < a.length() && j < pattern.length()) {
            if (pattern.charAt(j) == '@') {
                return i == a.length() - 1 && j == pattern.length() - 1;
            } else if (pattern.charAt(j) == '#') {
                i++;
                j++;
            } else if (a.charAt(i) == pattern.charAt(j)) {
                i++;
                j++;
            } else {
                return false;
            }
        }
        return i == a.length() && j == pattern.length();
    }

    public static void main(String[] args) {
        System.out.println(match("tt", "@")); // true
        System.out.println(match("ta", "t")); // false
        System.out.println(match("ta", "t#")); // true
    }
}
