package com.loel.wordGenerator;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.loel.Passwords;

public class WordGenerator {
	FileReader fr;
	List<String> list;
	static Passwords pass;
	static String password = "";

	public WordGenerator() {
//		pass = new Passwords();
		// document why this constructor is empty
	}

	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		System.out.println("What is this password for?");
		String name = sc.nextLine();
		System.out.println("What is the username?");
		String username = sc.nextLine();
		WordGenerator wg = new WordGenerator();
		File file = new File("words.txt");
		try {
			wg.readFile(file);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		try {
			writeToFile(name, username, password);
		} catch (Exception e) {
			e.printStackTrace();
		}
		sc.close();
	}

	public void readFile(File file) throws IOException {
		list = new ArrayList<>();
		String line = "";
		BufferedReader bf = new BufferedReader(new FileReader(file));
		while ((line = bf.readLine()) != null) {
			list.add(line);
		}
		for (int i = 0; i < 20000; i++) {
			int rnd = getRandomNumber(1, list.size());
			int rnd2 = getRandomNumber(1, list.size());
			if (list.get(rnd).length() + list.get(rnd2).length() < 15) {
				System.out.println(1 + list.get(rnd).substring(0, 1).toUpperCase() + list.get(rnd).substring(1) + "@"
						+ list.get(rnd2).substring(0, 1).toUpperCase() + list.get(rnd2).substring(1));
				password = 1 + list.get(rnd).substring(0, 1).toUpperCase() + list.get(rnd).substring(1) + "@"
						+ list.get(rnd2).substring(0, 1).toUpperCase() + list.get(rnd2).substring(1);
				break;
			}
		}
		bf.close();
	}

	public static void writeToFile(String who, String username, String passGen) throws IOException {
		try (FileWriter fw = new FileWriter("D:\\Passwords\\pass.txt", true);
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

	public int getRandomNumber(int min, int max) {
		return (int) ((Math.random() * (max - min)) + min);
	}

}
