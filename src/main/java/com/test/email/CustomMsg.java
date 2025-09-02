package com.test.email;

public class CustomMsg {

    public static final String subject = "Verify Your Email Address";

    public static String emailBody(String token) {
        String message =
                "<html>" +
                "  <body>" +
                "    <p>" +
                "      Click the link below to verify your account and confirm your email, " +
                "      ensuring secure access and protection from unauthorized use." +
                "    </p>" +
                "    <p>" +
                "      <a href='http://localhost:8080/verify?token=" + token + "'>Verify Account</a>" +
                "    </p>" +
                "  </body>" +
                "</html>";
        return message;
    }

}
