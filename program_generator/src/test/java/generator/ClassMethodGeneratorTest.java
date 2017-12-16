package generator;

import config.Configuration;
import org.junit.Test;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static org.junit.Assert.assertTrue;

public class ClassMethodGeneratorTest {

    @Test
    public void testGenerateInterfaceMethodForClass(){
        ClassMethodGenerator classGenerator = new ClassMethodGenerator("method1", new Configuration(),true);
        Pattern p = Pattern.compile("\\tpublic\\s(int|byte|short|String|Object|long|float|double|char|void)\\smethod1[.\\s\\S]*");
        String output = classGenerator.generate(null);
        Matcher m = p.matcher(output);
        System.out.println(output);
        System.out.println(m.matches());
        assertTrue(m.matches());
    }

    @Test
    public void testGenerateClassMethod(){
        ClassMethodGenerator classGenerator = new ClassMethodGenerator("method1", new Configuration(),false);
        Pattern p = Pattern.compile("\\t(public|private|protected)\\s(int|byte|short|String|Object|long|float|double|char|void)\\smethod1[.\\S\\s]*");
        String output = classGenerator.generate(null);
        Matcher m = p.matcher(output);
        System.out.println(output);
        System.out.println(m.matches());
        assertTrue(m.matches());
    }
}
