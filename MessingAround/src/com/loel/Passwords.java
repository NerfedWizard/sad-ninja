package com.loel;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;
import org.passay.*;

public class Passwords {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		System.out.println("What is this password for?");
		String name = sc.nextLine();
		try {
			writeToFile(name, generatePassayPassword());
		} catch (IOException e) {
			e.printStackTrace();
		}
		sc.close();
	}

	public static String generatePassayPassword() {
		PasswordGenerator gen = new PasswordGenerator();
		CharacterData lowerCaseChars = EnglishCharacterData.LowerCase;
		CharacterRule lowerCaseRule = new CharacterRule(lowerCaseChars);
		lowerCaseRule.setNumberOfCharacters(4);

		CharacterData upperCaseChars = EnglishCharacterData.UpperCase;
		CharacterRule upperCaseRule = new CharacterRule(upperCaseChars);
		upperCaseRule.setNumberOfCharacters(4);

		CharacterData digitChars = EnglishCharacterData.Digit;
		CharacterRule digitRule = new CharacterRule(digitChars);
		digitRule.setNumberOfCharacters(4);

		CharacterData specialChars = new CharacterData() {
			public String getErrorCode() {
				return AllowedCharacterRule.ERROR_CODE;
			}

			public String getCharacters() {
				return "!@#$%^&*()_+<>~`";
			}
		};
		CharacterRule splCharRule = new CharacterRule(specialChars);
		splCharRule.setNumberOfCharacters(3);

		String password = gen.generatePassword(15, splCharRule, lowerCaseRule, upperCaseRule, digitRule);
		return password;
	}

	public static void writeToFile(String who, String passGen) throws IOException {
		try (FileWriter fw = new FileWriter("E:\\Passwords\\password.txt", true);
				BufferedWriter bw = new BufferedWriter(fw);
				PrintWriter out = new PrintWriter(bw)) {
			out.println("\n---> " + who + " <---");
			out.println("--> " + passGen + " <--");
		} catch (IOException e) {
			System.err.println("file not written");
		}
		System.out.println("Password: --> " + passGen + " <-- has been saved!!");
	}
}
