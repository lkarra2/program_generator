package generator;

import config.Configuration;
import org.junit.Test;
import generator.ClassGenerator;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static org.junit.Assert.assertTrue;

public class ClassGeneratorTest {

    @Test
    public void TestGenerate() throws Exception {
        ClassGenerator classGenerator = new ClassGenerator("Class1", new Configuration());
        Pattern p = Pattern.compile("public\\sclass\\sTenKLOCClass1[.\\s\\S]*");
        String output = classGenerator.generate(1,null,null,0);
        Matcher m = p.matcher(output);
        System.out.println(output);
        System.out.println(m.matches());
        assertTrue(m.matches());
    }

}