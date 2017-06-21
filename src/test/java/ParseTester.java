import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.base.Charsets;
import com.google.common.io.Resources;
import org.junit.Test;

import java.io.IOException;
import java.net.URL;

/**
 * Created by ameet.chaubal on 6/21/2017.
 */
public class ParseTester {
    private ObjectMapper objectMapper = new ObjectMapper();

    @Test
    public void testParse() throws IOException {
        URL url = Resources.getResource("lograw.json");
        String text = Resources.toString(url, Charsets.UTF_8);
        System.out.println(text);
        Outer rtsMessage = objectMapper.readValue(text, Outer.class);
        System.out.println(rtsMessage.getRtsMessage().getBody().getLogRaw().getType());
    }
}
