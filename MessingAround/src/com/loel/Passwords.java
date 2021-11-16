package com.loel;

import org.passay.*;

public class Passwords {

	public static void main(String[] args) {
		System.out.println(generatePassayPassword());

	}

	public static String generatePassayPassword() {
		PasswordGenerator gen = new PasswordGenerator();
		CharacterData lowerCaseChars = EnglishCharacterData.LowerCase;
		CharacterRule lowerCaseRule = new CharacterRule(lowerCaseChars);
		lowerCaseRule.setNumberOfCharacters(3);

		CharacterData upperCaseChars = EnglishCharacterData.UpperCase;
		CharacterRule upperCaseRule = new CharacterRule(upperCaseChars);
		upperCaseRule.setNumberOfCharacters(3);

		CharacterData digitChars = EnglishCharacterData.Digit;
		CharacterRule digitRule = new CharacterRule(digitChars);
		digitRule.setNumberOfCharacters(3);

		CharacterData specialChars = new CharacterData() {
			public String getErrorCode() {
				return AllowedCharacterRule.ERROR_CODE;
			}

			public String getCharacters() {
				return "!@#$%^&*()_+";
			}
		};
		CharacterRule splCharRule = new CharacterRule(specialChars);
		splCharRule.setNumberOfCharacters(3);

		String password = gen.generatePassword(15, splCharRule, lowerCaseRule, upperCaseRule, digitRule);
		return password;
	}

}
