package lambda.exercise1;
class exercise1 {
    interface Counter {
        abstract Integer count(String str);
    }
    public static void main(String[]args){

        Counter lengthCounter = str -> {
            int length = str.length();
            return length;
        };
        Counter letterCounter = str -> {
            int letters = 0;
            char[] ch = str.toCharArray();
            for (int j = 0; j < str.length(); j++){
                if (Character.isLetter(ch[j])){
                    letters++;
                }
            }
            return letters;
        };
        System.out.println(lengthCounter.count("There are 8 planets in our solar system!"));
        System.out.println(letterCounter.count("There are 8 planets in our solar system!"));
    }
}

