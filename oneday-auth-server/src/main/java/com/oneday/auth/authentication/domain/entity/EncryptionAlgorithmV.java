package com.oneday.auth.authentication.domain.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.util.DigestUtils;

/**
 * 加密算法值对象
 *
 * @author zhuangzf
 * @date 2019/4/2 13:57
 */
@AllArgsConstructor
@Getter
public enum  EncryptionAlgorithmV {
    /**
     *
     */
    MD5(0, "MD5",new MD5PasswordEncoder()),;

    private int code;
    private String value;
    private PasswordEncoder passwordEncoder;

    public interface PasswordEncoder {
        // TODO: 2019/4/2 改成配置属性
        String COMMON_SALT = "commonSalt";

        /**
         * 加密
         *
         * @param password
         * @param salt
         * @return
         */
        String encoder(String password, String salt);


    }

    public static class MD5PasswordEncoder implements PasswordEncoder{

        @Override
        public String encoder(String password, String salt) {
            salt = salt == null ? COMMON_SALT : COMMON_SALT.concat(salt);
            return String.valueOf(DigestUtils.md5DigestAsHex(COMMON_SALT.concat(password).concat(salt).getBytes()));
        }

    }

    public static void main(String[] args) {
        System.out.println(EncryptionAlgorithmV.MD5.ordinal());
    }

}
