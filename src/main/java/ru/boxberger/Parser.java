package ru.boxberger;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static ru.boxberger.Constants.*;

public class Parser {

    public static Map<String, String> getPairsToday() {
        return parsePairsFromNames(getListPagesNames(TODAY_NAMES_PATH), TODAY);
    }

    public static Map<String, String> getPairsYesterday() {
        return parsePairsFromNames(getListPagesNames(YESTERDAY_NAMES_PATH), YESTERDAY);
    }

    private static Map<String, String> parsePairsFromNames(List<String> namesList, String day) {
        Map<String, String> pairs = new HashMap<>();

        for (String name : namesList) {
            pairs.put(getUrlByFileName(name, day), getPageByFileName(name, day));
        }

        return pairs;
    }

    private static String getPageByFileName(String name, String day) {
        return readFileContents(createHtmlFileName(name, day));
    }

    private static String createHtmlFileName(String name, String day) {
        return generateFilePath(name, day, HTML_POSTFIX);
    }

    private static String getUrlByFileName(String name, String day) {
        return readFileLine(createUrlFileName(name, day));
    }

    private static String createUrlFileName(String name, String day) {
        return generateFilePath(name, day, URL_POSTFIX);
    }

    private static String generateFilePath(String name, String day, String postfix) {
        String basePath = (day.equals(TODAY)) ? TODAY_URLS_PATH : YESTERDAY_URLS_PATH;
        return basePath + name + postfix;
    }

    private static String readFileContents(String fileName) {
        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            StringBuilder sb = new StringBuilder();
            String line;
            while ((line = br.readLine()) != null) {
                sb.append(line);
            }
            return sb.toString();
        } catch (IOException e) {
            System.out.println("Error reading file: " + fileName + "\n" + e.getMessage());
        }
        return null;
    }

    private static String readFileLine(String fileName) {
        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            return br.readLine();
        } catch (IOException e) {
            System.out.println("Error reading file: " + fileName + "\n" + e.getMessage());
        }
        return null;
    }

    public static List<String> getListPagesNames(String fileName) {
        List<String> namesList = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = br.readLine()) != null) {
                namesList.add(line);
            }
        } catch (IOException e) {
            System.out.println("Error reading file " + fileName + "\n" + e.getMessage());
        }
        return namesList;
    }
}