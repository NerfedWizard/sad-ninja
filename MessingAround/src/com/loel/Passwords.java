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
		System.out.println("What is the username?");
		String username = sc.nextLine();
		System.out.println("Do you want a new password? (Y)Create new password (N)Enter existing password to be saved");
		String response = sc.nextLine();
		if (response.toLowerCase().equals("y")) {
			try {
				writeToFile(name, username, generatePassayPassword());
			} catch (IOException e) {
				e.printStackTrace();
			}
		} else {
			System.out.println("What is the password you would like saved?");
			String password = sc.nextLine();
			try {
				writeToFile(name, username, password);
			} catch (IOException e) {
				e.printStackTrace();
			}
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
		return gen.generatePassword(15, splCharRule, lowerCaseRule, upperCaseRule, digitRule);
	}

	public static void writeToFile(String who, String username, String passGen) throws IOException {
		try (FileWriter fw = new FileWriter("E:\\Passwords\\password.txt", true);
				BufferedWriter bw = new BufferedWriter(fw);
				PrintWriter out = new PrintWriter(bw)) {
			out.println("\n---> " + who + " <---");
			out.println("---> " + username + " <---");
			out.println("--> " + passGen + " <--");
		} catch (IOException e) {
			System.err.println("file not written");
		}
		System.out.println("Password: --> " + passGen + " <-- has been saved!!");
	}
}
