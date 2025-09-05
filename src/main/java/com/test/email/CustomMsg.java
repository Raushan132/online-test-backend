package com.test.email;

import org.springframework.stereotype.Component;

@Component
public class CustomMsg {
    public static final String subject = "Verify Your Email Address";



    public static String emailBody(String token, Integer id,String baseUrl) {
        String message =
                "<html>" +
                "  <body>" +
                "    <p>" +
                "      Click the link below to verify your account and confirm your email, " +
                "      ensuring secure access and protection from unauthorized use." +
                "    </p>" +
                "    <p>" +
                "      <a href='"+baseUrl+"/auth/verify?token=" + token + "&id=" + id + "'>Verify Account</a>" +
                "    </p>" +
                "  </body>" +
                "</html>";
        return message;
    }
}
