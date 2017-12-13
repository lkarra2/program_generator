package generator;

import org.junit.Test;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static org.junit.Assert.*;

public class IdentifierGeneratorTest {

    @Test
    public void testGenerate() throws Exception {
        IdentifierGenerator identifierGenerator = new IdentifierGenerator();
        Pattern p = Pattern.compile("[a-zA-Z]+");
        String output = identifierGenerator.getVariableName();
        Matcher m = p.matcher(output);
        System.out.println(output);
        assertTrue(m.matches());
    }

    @Test
    public void testGetSingleChar() throws Exception {
        IdentifierGenerator identifierGenerator = new IdentifierGenerator();
        Pattern p = Pattern.compile("[a-z]");
        String output = identifierGenerator.getSingleChar();
        Matcher m = p.matcher(output);
        System.out.println(output);
        assertTrue(m.matches());
    }

    @Test
    public void testGetInterfaceOrClassName() throws Exception {
        IdentifierGenerator identifierGenerator = new IdentifierGenerator();
        Pattern p = Pattern.compile("^[A-Z][a-zA-Z]+");
        String output = identifierGenerator.getClassOrInterfaceName();
        Matcher m = p.matcher(output);
        System.out.println(output);
        assertTrue(m.matches());
    }

}