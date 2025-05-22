package net.lesscoding.unified.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;

/**
 * @author eleven
 * @date 2024/9/9 13:50
 * @apiNote
 */
public class IOStreamUtils {

    /**
     * 输入流转String
     *
     * @param is 输入流
     * @return 字符串
     * @throws IOException 异常
     */
    public static String inputStreamToString(InputStream is) throws IOException {
        return inputStreamToString(is, "");
    }

    /**
     * 输入流转String
     *
     * @param is        输入流
     * @param lineBreak 换行符
     * @return 字符串
     * @throws IOException 异常
     */
    public static String inputStreamToString(InputStream is, String lineBreak) throws IOException {
        StringBuilder sb = new StringBuilder();
        String line;
        BufferedReader br = new BufferedReader(new InputStreamReader(is, StandardCharsets.UTF_8));
        while ((line = br.readLine()) != null) {
            sb.append(line).append(lineBreak);
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        char[] charArray = System.lineSeparator().toCharArray();
        for (char c : charArray) {
            System.out.printf("字符: %c, 转义序列: \\u%04x, ASCII值: %d%n", c, (int)c, (int)c);
        }
    }
}
