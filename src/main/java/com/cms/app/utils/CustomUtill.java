package com.cms.app.utils;

import com.cms.app.model.Post;
import org.apache.commons.codec.binary.Base64;

import java.io.UnsupportedEncodingException;

/**
 * Created by ragu on 9/2/16.
 */
public class CustomUtill {

    public static Post converStringfromByte(Post post){
        byte[] encodeBase64 = Base64.encodeBase64(post.getImage());

        String base64Encoded = null;
        try {
            base64Encoded = new String(encodeBase64, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            System.err.println(" Error " + e.getMessage());
        }
        post.setImageSrc(base64Encoded);
        return post;
    }
}
