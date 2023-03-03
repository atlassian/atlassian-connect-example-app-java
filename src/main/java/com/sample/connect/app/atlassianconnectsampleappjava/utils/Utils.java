package com.sample.connect.app.atlassianconnectsampleappjava.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.impl.crypto.DefaultJwtSignatureValidator;

import javax.crypto.spec.SecretKeySpec;

import java.util.Base64;

import static io.jsonwebtoken.SignatureAlgorithm.HS256;

public class Utils {
    public static boolean verifyTokenSignature(String token, String secretKey) {
        SignatureAlgorithm algorithm = HS256;
        SecretKeySpec secretKeySpec = new SecretKeySpec(secretKey.getBytes(), algorithm.getJcaName());

        DefaultJwtSignatureValidator validator = new DefaultJwtSignatureValidator(algorithm, secretKeySpec);

        String[] chunks = token.split("\\.");
        String tokenWithoutSignature = chunks[0] + "." + chunks[1];
        String signature = chunks[2];

        return validator.isValid(tokenWithoutSignature, signature);
    }

    public static boolean verifyTokenValidity(String token, String clientKeyFromDb) throws JsonProcessingException {
        Base64.Decoder decoder = Base64.getUrlDecoder();

        if (token == null || token.equals("")) {
            return false;
        }

        String[] chunks = token.split("\\.");
        String payload = new String(decoder.decode(chunks[1]));

        String clientKeyFromToken = new ObjectMapper().readValue(payload, JsonNode.class).get("iss").textValue();

        return clientKeyFromDb.equals(clientKeyFromToken);
    }
}
