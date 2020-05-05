package com.example;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;

public class RemoveCharactersInFirstString {
    public static void replaceInStrings(String[] args) {
        try {
            System.out.println("Enter the first string: ");
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            String str1 = br.readLine();

            System.out.println("Enter the second string: ");
            String str2 = br.readLine();

            Set<Character> charSet = new HashSet<>();
            for (Character c :
                    str2.toCharArray()) {
                charSet.add(c);
            }

            for(Character c: charSet) {
                str1 = str1.replace(c, ' ');
            }

            System.out.println("String: " + str1);

        } catch (IOException e) {
            System.out.println("Got an exception: " + e);
        }
    }

    public static void replaceInPlace(String[] args) {
        try {
            System.out.println("Enter the first string: ");
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            String str1 = br.readLine();

            System.out.println("Enter the second string: ");
            String str2 = br.readLine();

            int[] count = new int[255];
            for (char c :
                    str2.toCharArray()) {
                count[c] += 1;
            }

            char[] str1CharArray = str1.toCharArray();
            int jump = 0;
            for(int index = 0; index < str1CharArray.length && jump < str1CharArray.length; index++) {
                if(count[str1CharArray[index]] == 0) {
                    str1CharArray[jump] = str1CharArray[index];
                    jump++;
                }
            }

            System.out.println("String: " + new String(str1CharArray).substring(0, jump));

        } catch (IOException e) {
            System.out.println("Got an exception: " + e);
        }
    }

    public static void main(String[] args) {
        replaceInPlace(args);
    }
}
