package com.scccy.videoDownloader.pojo;

import java.util.Scanner;

public class ColorfulConsole {
    public static final String INFO = "INFO";
    public static final String ERROR = "ERROR";
    public static final String WARNING = "b bright_yellow";

    public void print(String message) {
        System.out.println(message);
    }

    public void print(String message, String style) {
        // 根据样式输出消息
        System.out.println("[" + style + "] " + message);
    }

    public String input(String prompt) {
        Scanner scanner = new Scanner(System.in);
        System.out.print(prompt);
        return scanner.nextLine();
    }
}
