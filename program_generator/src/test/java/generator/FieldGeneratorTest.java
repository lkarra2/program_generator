package generator;

import config.Configuration;
import org.junit.Test;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static org.junit.Assert.*;

public class FieldGeneratorTest {
    @Test
    public void testGenerateIfParameterType() throws Exception {
        FieldGenerator fieldGenerator = new FieldGenerator("newField", new Configuration());
        Pattern p = Pattern.compile("(int|byte|short|String|Object|long|float|double|char)\\snewField");
        String output = fieldGenerator.generate("parameter");
        Matcher m = p.matcher(output);
        System.out.println(output);
        assertTrue(m.matches());
    }

    @Test
    public void testGenerateIfFieldType() throws Exception {
        FieldGenerator fieldGenerator = new FieldGenerator("newField", new Configuration());
        Pattern p = Pattern.compile("(public|private|protected)\\s(static\\s)?(int|byte|short|String|Object|long|float|double|char)\\snewField");
        String output = fieldGenerator.generate("field");
        Matcher m = p.matcher(output);
        System.out.println(output);
        assertTrue(m.matches());
    }

    @Test
    public void testGenerateArray() throws Exception {
        FieldGenerator fieldGenerator = new FieldGenerator("newField", new Configuration());
        Pattern p = Pattern.compile("(int|byte|short|String|Object|long|float|double|char)\\W{2}\\snewField\\W\\snew\\s(int|byte|short|String|Object|long|float|double|char)\\W[0-9]*\\W");
        String output = fieldGenerator.generateArray();
        Matcher m = p.matcher(output);
        System.out.println(output);
        assertTrue(m.matches());
    }

}