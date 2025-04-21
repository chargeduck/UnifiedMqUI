package net.lesscoding.unified.runner;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.io.IOException;

/**
 * @author eleven
 * @date 2025/4/21 9:59
 * @apiNote
 */
@Component
public class AutoOpenBrowserRunner implements ApplicationRunner {

    @Value("${server.port:54321}")
    private String port;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        String url = "http://localhost:" + port;
        String os = System.getProperty("os.name").toLowerCase();
        try {
            if (os.contains("win")) {
                // Windows 系统
                Runtime.getRuntime().exec("rundll32 url.dll,FileProtocolHandler " + url);
            } else if (os.contains("mac")) {
                // Mac 系统
                Runtime.getRuntime().exec("open " + url);
            } else if (os.contains("nix") || os.contains("nux")) {
                // Linux 系统，尝试多种浏览器
                String[] browsers = {"xdg-open", "google-chrome", "firefox", "mozilla", "opera", "epiphany", "konqueror", "netscape"};
                for (String browser : browsers) {
                    try {
                        Process process = Runtime.getRuntime().exec(new String[]{browser, url});
                        if (process != null) {
                            break;
                        }
                    } catch (IOException e) {
                        // 忽略异常，尝试下一个浏览器
                    }
                }
            }
        } catch (IOException e) {
            System.err.println("无法打开浏览器: " + e.getMessage());
        }
    }
}
