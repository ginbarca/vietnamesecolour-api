package au.com.vietnamesecolour.utils;

import cn.apiclub.captcha.Captcha;
import cn.apiclub.captcha.backgrounds.FlatColorBackgroundProducer;
import cn.apiclub.captcha.gimpy.DropShadowGimpyRenderer;
import cn.apiclub.captcha.noise.CurvedLineNoiseProducer;
import cn.apiclub.captcha.text.producer.DefaultTextProducer;
import cn.apiclub.captcha.text.renderer.DefaultWordRenderer;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Arrays;
import java.util.Base64;
import java.util.Collections;
import java.util.List;

public class CaptchaUtils {

    private static final List<Color> textColors = Arrays.asList(
            Color.CYAN,
            Color.BLACK,
            Color.MAGENTA,
            Color.ORANGE,
            new Color(216, 237, 206),
            new Color(162, 124, 113),
            new Color(197, 149, 209),
            new Color(202, 159, 142),
            new Color(122, 226, 135),
            new Color(58, 142, 90),
            new Color(131, 131, 255),
            new Color(72, 8, 64),
            new Color(230, 129, 220),
            new Color(231, 252, 175)
    );
    private static final List<Color> backgroundColors = Arrays.asList(
            Color.GRAY,
            Color.BLACK,
            Color.WHITE,
            new Color(10, 43, 50),
            new Color(209, 255, 255),
            new Color(87, 9, 65),
            new Color(162, 202, 195),
            new Color(17, 8, 31),
            new Color(196, 187, 194),
            new Color(16, 0, 0),
            new Color(225, 197, 218),
            new Color(56, 47, 40),
            new Color(55, 98, 124)
    );
    private static final List<Font> textFonts = Arrays.asList(
            new Font("Nyala", Font.BOLD, 40),
            new Font("Bell MT", Font.BOLD, 40),
            new Font("Credit valley", Font.BOLD, 40),
            new Font("Courier", Font.BOLD, 40)
    );

    public static Captcha createCaptcha(int width, int height) {
        return new Captcha.Builder(width, height)
                .addBackground(new FlatColorBackgroundProducer(CommonUtils.randomObj(backgroundColors.toArray(new Color[0]))))
                .addText(
                        new DefaultTextProducer(6, "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789".toCharArray()),
                        new DefaultWordRenderer(
                                Collections.singletonList(CommonUtils.randomObj(textColors.toArray(new Color[0]))),
                                textFonts)
                )
                .gimp(new DropShadowGimpyRenderer())
                .addNoise(new CurvedLineNoiseProducer())
                .build();
    }

    public static String encodeBase64(Captcha captcha) {
        String image = null;
        try {
            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            ImageIO.write(captcha.getImage(), "png", outputStream);
            byte[] arr = Base64.getEncoder().encode(outputStream.toByteArray());
            image = new String(arr);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return image;
    }
}
