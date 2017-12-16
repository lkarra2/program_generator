package generator;

import config.Configuration;
import org.junit.Test;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static org.junit.Assert.assertTrue;

public class InterfaceGeneratorTest {

    @Test
    public void testGenerate() throws Exception {
        InterfaceGenerator classGenerator = new InterfaceGenerator("interfaceSome", new Configuration(),0, null);
        Pattern p = Pattern.compile("public\\sinterface\\sinterfaceSome[.\\s\\S]*");//. represents single character
        String output = classGenerator.generate();
        Matcher m = p.matcher(output);
        System.out.println(output);
        System.out.println(m.matches());
        assertTrue(m.matches());
    }

}
