package main;

import generator.ProgramGenerator;

import java.io.File;
import java.io.FileNotFoundException;

/**
    Main Class to be run.
    Consists of generateClasses() and generateInterfaces() functions
 */

public class Main {

    //Controls flow of Program Generator
    public static void main(String[] args) throws FileNotFoundException {
        ProgramGenerator javaProgram = new ProgramGenerator();
        new File("./generatedFiles").mkdir();
        javaProgram.generateInterfaces();
        javaProgram.generateClasses();
    }


}
