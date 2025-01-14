package util;

import javax.crypto.*;
import javax.crypto.spec.DESKeySpec;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.io.*;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Crypter {

    private Cipher deCipher;
    private Cipher enCipher;
    private SecretKeySpec key;
    private IvParameterSpec ivSpec;

    private MessageDigest digest;
    private static  final byte[] IV_BYTES = new byte[] { 0x07, 0x06, 0x05, 0x04, 0x03, 0x02, 0x01, 0x00};

    public Crypter(String key){
        this(key.getBytes(), IV_BYTES);
    }

    public Crypter(byte[] keyBytes, byte[] ivBytes) {
        ivSpec = new IvParameterSpec(ivBytes);

        try {
            DESKeySpec dkey = new DESKeySpec(keyBytes);
            key = new SecretKeySpec(dkey.getKey(), "DES");
            deCipher = Cipher.getInstance("DES/CBC/PKCS5Padding");
            enCipher = Cipher.getInstance("DES/CBC/PKCS5Padding");
        } catch (NoSuchAlgorithmException | InvalidKeyException | NoSuchPaddingException e){
            e.printStackTrace();
        }
    }

    public String md5(String original){
        if(digest == null){
            try {
                digest = MessageDigest.getInstance("MD5");
            } catch (NoSuchAlgorithmException e){
                e.printStackTrace();
            }
        }

        byte[] bytesOfMessage;
        try {
            bytesOfMessage = original.getBytes("UTF-8");
            byte[] data = digest.digest(bytesOfMessage);
            StringBuffer sb = new StringBuffer();
            for (int i = 0; i < data.length; i++) {
                sb.append(Integer.toString((data[i] & 0xff) + 0x100, 16).substring(1));
            }
            return sb.toString();
        } catch (UnsupportedEncodingException e){
            e.printStackTrace();
        }
    return original;
    }

    public byte[] encrypt(Object obj) throws InvalidKeyException,
            InvalidAlgorithmParameterException, IOException,
            IllegalBlockSizeException,
            BadPaddingException {
        byte[] input = convertToByteArray(obj);
        enCipher.init(Cipher.ENCRYPT_MODE, key, ivSpec);

        return enCipher.doFinal(input);

    }

    public Object decrypt(byte[] encrypted) throws InvalidKeyException,
    InvalidAlgorithmParameterException, IOException,
    IllegalBlockSizeException,
    BadPaddingException {
        deCipher.init(Cipher.DECRYPT_MODE, key, ivSpec);
        return convertToByteArray(deCipher.doFinal(encrypted));
    }

    private Object convertFromByteArray(byte[] byteObject) throws IOException, ClassNotFoundException {
        ByteArrayInputStream bais;
        ObjectInputStream in;
        bais = new ByteArrayInputStream(byteObject);
        in = new ObjectInputStream(bais);
        Object o = in.readObject();
        in.close();
        return o;
    }

    private byte[] convertToByteArray(Object complexObject) throws  IOException {
        ByteArrayOutputStream baos;
        ObjectOutputStream out;
        baos = new ByteArrayOutputStream();
        out = new ObjectOutputStream(baos);
        out.writeObject(complexObject);
        out.close();
        return baos.toByteArray();
    }
}
