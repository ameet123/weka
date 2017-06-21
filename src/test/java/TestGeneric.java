import org.junit.BeforeClass;
import org.junit.Test;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStreamReader;
import java.util.Base64;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;

/**
 * Created by ameet.chaubal on 6/19/2017.
 */
public class TestGeneric {
    private static final String MSG = "hello world";
    private static Base64.Decoder decoder;
    private static Base64.Encoder encoder;

    @BeforeClass
    public static void init() {
        decoder = Base64.getDecoder();
        encoder = Base64.getEncoder();
    }

    private String decompress(byte[] bytes) {
        StringBuilder out = new StringBuilder();
        try {
            GZIPInputStream gis = new GZIPInputStream(new ByteArrayInputStream(bytes));
            BufferedReader bf = new BufferedReader(new InputStreamReader(gis, "UTF-8"));

            String line;
            while ((line = bf.readLine()) != null) {
                out.append(line);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return out.toString();
    }


    private byte[] compress(String str) throws Exception {
        if (str == null || str.length() == 0) {
            return null;
        }
        ByteArrayOutputStream obj = new ByteArrayOutputStream();
        GZIPOutputStream gzip = new GZIPOutputStream(obj);
        gzip.write(str.getBytes("UTF-8"));
        gzip.close();
        return obj.toByteArray();
    }

    @Test
    public void testBase64() throws Exception {
//        String msg = "eNpNkdGOgjAQRX+l6bvbqjEbzVCDtFEiikuL8dVI45IokGKM";
//        byte[] decoded = decoder.decode(msg);
//        System.out.println(decompress(msg.getBytes()));

        String zippedEncoded = encoder.encodeToString(compress(MSG));
        System.out.println(zippedEncoded);
        String decodedUnzipped = decompress(decoder.decode("eNpNkdGOgjAQRX+l6bvbqjEbzVCDtFEiikuL8dVI45IokGKM"));
        System.out.println("Deciphered==> "+decodedUnzipped);


//        String encoded = encoder.encodeToString(MSG.getBytes());
//        System.out.println("Encoded=> " + encoded);
//        System.out.println("Decoded=> " + new String(decoder.decode(encoded)));
    }
}
