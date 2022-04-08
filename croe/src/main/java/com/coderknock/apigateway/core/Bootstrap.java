package com.coderknock.apigateway.core;

import com.coderknock.apigateway.core.config.Version;
import picocli.CommandLine;
import picocli.CommandLine.Command;
import picocli.CommandLine.Parameters;

import java.io.File;
import java.math.BigInteger;
import java.nio.file.Files;
import java.security.MessageDigest;
import java.util.ResourceBundle;
import java.util.concurrent.Callable;

import static picocli.CommandLine.Option;

/**
 * 启动类
 *
 * @author sanchan
 * @微信 sanchan_coderknock
 * @B站 https://space.bilibili.com/62450448
 * @CSDN https://blog.csdn.net/sanchan
 * @WebSite https://coderknock.com
 * @date 2022-04-08
 */
@Command(name = "checksum", mixinStandardHelpOptions = true, version = "checksum " + Version.VERSION, resourceBundle = "com.coderknock.apigateway.core.bootstrap", sortOptions = false)
public class Bootstrap implements Callable<Integer> {
    ResourceBundle bundle = ResourceBundle.getBundle("com.coderknock.apigateway.core.bootstrap");

    @Option(names = {"-l", "--locale"}, descriptionKey = "Locale", paramLabel = "<locale>", order = 1)
    private String ignored;

    @Parameters(index = "0", descriptionKey = "File")
    private File file;

    @Option(names = {"-a", "--algorithm"}, descriptionKey = "Algorithms", order = 2)
    private String algorithm = "SHA-1";

    @Override
    public Integer call() throws Exception {
        byte[] fileContents = Files.readAllBytes(file.toPath());
        byte[] digest = MessageDigest.getInstance(algorithm).digest(fileContents);
        System.out.printf("%s: %s%n", bundle.getString("Label_File"), file);
        System.out.printf("%s: %s%n", bundle.getString("Label_Algorithm"), algorithm);
        System.out.printf("%s: %0" + (digest.length * 2) + "x", bundle.getString("Label_Checksum"), new BigInteger(1, digest));
        return 0;
    }

    public static void main(String... args) {
        // first phase: configure locale
        new CommandLine(new Bootstrap()).parseArgs(args);

        // second phase: parse all args (ignoring --locale) and run the app
        int exitCode = new CommandLine(new Bootstrap()).execute(args);
        System.exit(exitCode);
    }
}
