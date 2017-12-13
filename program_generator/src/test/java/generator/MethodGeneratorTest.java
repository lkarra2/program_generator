package generator;

import config.Configuration;
import org.junit.Test;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import static org.junit.Assert.assertTrue;

public class MethodGeneratorTest {

    @Test
    public void TestGenerate(){
        MethodGenerator classGenerator = new MethodGenerator("method1", new Configuration());
        Pattern p = Pattern.compile("\\tpublic\\svoid\\smethod1[^\\w]*");
        String output = classGenerator.generate();
        Matcher m = p.matcher(output);
        System.out.println(output);
        System.out.println(m.matches());
        assertTrue(m.matches());
    }
}
