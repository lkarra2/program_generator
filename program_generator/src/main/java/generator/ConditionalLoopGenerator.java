package generator;

import config.Configuration;

/**
 * Generate conditional loop statements: FOR loop, WHILE loop, and nested if
 */
public class ConditionalLoopGenerator {
    Configuration configuration;
    public ConditionalLoopGenerator(Configuration configuration) {
        this.configuration=configuration;
    }

     String generateForLoop(){
        int maxValueForLoop = configuration.getMaxValueForLoop();
        IdentifierGenerator identifierGenerator = new IdentifierGenerator();
        String identifier = "f"+identifierGenerator.getVariableName();
        String forLoop="\t\tfor(int "+identifier+"=0; "+identifier+"<"+maxValueForLoop+"; "+identifier+"++){\n\t\t}\n";
        return forLoop;
    }

     String generateWhileLoop(){
         IdentifierGenerator identifierGenerator = new IdentifierGenerator();
         String identifier = "w"+identifierGenerator.getVariableName();
         String whileLoop ="\t\tint "+identifier+"=0;\n"+"\t\twhile("+identifier+"<5){\n\t\t\t"+identifier+"++;\n\t\t}\n";
         return whileLoop;
    }

    String generateIfStatements(){
        int maxNestedIf = configuration.getMaxNestedIfs();
        int i = 0;
        String ifStmt = "";
        while (i < maxNestedIf) {
            IdentifierGenerator identifierGenerator = new IdentifierGenerator();
            String identifier = "c"+identifierGenerator.getVariableName();
            ifStmt += "\t\t\n\t\tint " + identifier + " = 0;\n" + "\t\tif (" + identifier + " < 5) {\n\t\t\t" + identifier + "++;";
            i++;
        }
        i = 0;
        while (i < maxNestedIf) {
            ifStmt += "\n\t\t}\n";
            i++;
        }
        return ifStmt;
    }

}
