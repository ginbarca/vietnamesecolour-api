package au.com.vietnamesecolour.utils;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.util.Calendar;
import java.util.List;
import java.util.Random;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;

public class CommonUtils {

    static Random rnd = new Random();

    public static String randomString(int len) {
        StringBuilder sb = new StringBuilder(len);

        for (int i = 0; i < len; ++i) {
            sb.append(
                    "0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ"
                            .charAt(
                                    rnd.nextInt(
                                            "0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ".length())));
        }

        return sb.toString();
    }

    public static <T> T randomObj(T[] objects) {
        Random r = new Random();
        return objects[r.nextInt(objects.length)];
    }

    public static <T> CompletableFuture<List<T>> allOf(List<CompletableFuture<T>> futuresList) {
        CompletableFuture<Void> allFuturesResult =
                CompletableFuture.allOf(futuresList.toArray(new CompletableFuture[0]));
        return allFuturesResult.thenApply(
                v -> futuresList.stream().map(CompletableFuture::join).collect(Collectors.<T>toList()));
    }

    public static long randomTokenLong(long heroId) {
        String rs = "" + heroId + Calendar.getInstance().getTimeInMillis();
        return Long.parseLong(rs);
    }

    public static int randomInt() {
        return rnd.nextInt(((99999 - 10000) + 1) + 10000);
    }

    public static String getApplicationUrl(HttpServletRequest request) {
        String baseUrl = ServletUriComponentsBuilder.fromRequestUri(request)
                .replacePath(null)
                .build()
                .toUriString();
        return baseUrl + request.getContextPath();
    }
}
