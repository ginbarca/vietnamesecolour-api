package au.com.vietnamesecolour.controller;

import au.com.vietnamesecolour.config.data.ResponseData;
import au.com.vietnamesecolour.config.data.ResponseUtils;
import au.com.vietnamesecolour.config.exception.CommonErrorCode;
import au.com.vietnamesecolour.utils.CaptchaUtils;
import cn.apiclub.captcha.Captcha;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.OutputStream;

@RestController
@RequestMapping("/api/v1/public")
public class CaptchaController {

    @GetMapping("/captcha")
    public void getCaptcha(HttpServletResponse response, HttpServletRequest request) throws IOException {

        Captcha captcha = CaptchaUtils.createCaptcha(150, 40);

        String text = captcha.getAnswer();
        BufferedImage image = captcha.getImage();

        request.getSession().setAttribute("captcha" + request.getSession().getId(), text);
        response.setContentType("image/jpeg");
        OutputStream outputStream = response.getOutputStream();
        ImageIO.write(image, "jpg", outputStream);
        outputStream.close();
    }

    @PostMapping("/captcha/verify")
    public ResponseEntity<ResponseData<Void>> verifyCaptcha(HttpServletRequest request, @RequestParam("captcha") String captcha) {
        String captchaSession = (String) request.getSession().getAttribute("captcha" + request.getSession().getId());
        if (captcha.equals(captchaSession)) {
            return ResponseUtils.status(CommonErrorCode.CAPTCHA_VALID.getCode(), CommonErrorCode.CAPTCHA_VALID.getMessage(), CommonErrorCode.CAPTCHA_VALID.getHttpStatus());
        } else {
            return ResponseUtils.status(CommonErrorCode.CAPTCHA_INVALID.getCode(), CommonErrorCode.CAPTCHA_INVALID.getMessage(), CommonErrorCode.CAPTCHA_INVALID.getHttpStatus());
        }
    }

}
