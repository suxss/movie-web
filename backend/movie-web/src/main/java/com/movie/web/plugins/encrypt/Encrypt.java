package com.movie.web.plugins.encrypt;

import org.apache.commons.codec.digest.DigestUtils;
import org.jetbrains.annotations.NotNull;

public class Encrypt {
    public static @NotNull String md5(String s){
        return DigestUtils.md5Hex(s);
    }

    public static @NotNull String sha256(String s){
        return DigestUtils.sha256Hex(s);
    }

    public static @NotNull String sha512(String s){
        return DigestUtils.sha512Hex(s);
    }
}
