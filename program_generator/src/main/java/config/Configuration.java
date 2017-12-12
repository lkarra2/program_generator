package config;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/*
    Holds all the configuration data and corresponding getters for each
 */

public class Configuration {
    public int getMaxNoOfArrays() {
        return maxNoOfArrays;
    }

    int maxNoOfArrays;

    public int getMinInterfacesToImplement() {
        return minInterfacesToImplement;
    }

    int minInterfacesToImplement;
    public int getNoOfInterfaces() {

        return noOfInterfaces;
    }

    int noOfInterfaces;

    public int getMaxNoOfMethodsPerInterface() {
        return maxNoOfMethodsPerInterface;
    }

    int maxNoOfMethodsPerInterface;

    public int getNoOfClasses() {
        return noOfClasses;
    }

    int noOfClasses;

    public int getMaxAllowedMethodCalls() {
        return maxAllowedMethodCalls;
    }

    int maxAllowedMethodCalls;

    public int getMaxValueForLoop() {
        return maxValueForLoop;
    }

    int maxValueForLoop;

    public String getAllowIndirectRecursion() {
        return allowIndirectRecursion;
    }

    String allowIndirectRecursion;

    public int getMaximumArraySize() {
        return maximumArraySize;
    }

    int maximumArraySize;

    public int getNoOfInheritanceChains() {
        return noOfInheritanceChains;
    }

    int noOfInheritanceChains;

    public int getMaxNestedIfs() {
        return maxNestedIfs;
    }

    int maxNestedIfs;

    public int getMinNoOfClassFields() {
        return minNoOfClassFields;
    }

    int minNoOfClassFields;

    public String getAllowArray() {
        return allowArray;
    }

    String allowArray;

    public int getMaxInheritanceDepth() {
        return maxInheritanceDepth;
    }

    int maxInheritanceDepth;

    public int getMinInheritanceDepth() {
        return minInheritanceDepth;
    }

    int minInheritanceDepth;

    public int getMaxNoOfParametersPerMethod() {
        return maxNoOfParametersPerMethod;
    }

    int maxNoOfParametersPerMethod;

    public int getMinNoOfParametersPerMethod() {
        return minNoOfParametersPerMethod;
    }

    int minNoOfParametersPerMethod;

    public int getTotalLOC() {
        return totalLOC;
    }

    int totalLOC;

    public int getMaxInterfacesToImplement() {
        return maxInterfacesToImplement;
    }

    int maxInterfacesToImplement;

    public int getMaxRecursionDepth() {
        return maxRecursionDepth;
    }

    int maxRecursionDepth;

    public String getClassNamePrefix() {
        return classNamePrefix;
    }

    String classNamePrefix;

    public int getMaxNoOfClassFields() {
        return maxNoOfClassFields;
    }

    int maxNoOfClassFields;

    public int getMaxNoOfMethodsPerClass() {
        return maxNoOfMethodsPerClass;
    }

    int maxNoOfMethodsPerClass;

    public float getRecursionProbability() {
        return recursionProbability;
    }

    float recursionProbability;

    public int getIntMaxValue() {
        return intMaxValue;
    }

    int intMaxValue;

    public List<String> getAllowedTypes() {
        return allowedTypes;
    }

    List<String> allowedTypes = new ArrayList<>();

    public Configuration() {
        File configFile = new File("./generator.xml");
        DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
        try {
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(configFile);
            doc.getDocumentElement().normalize();
            NodeList nList = doc.getElementsByTagName("ProgGen");
            for (int temp = 0; temp < nList.getLength(); temp++) {
                Node nNode = nList.item(temp);
                if (nNode.getNodeType() == Node.ELEMENT_NODE) {
                    Element eElement = (Element) nNode;
                    maxNoOfArrays = Integer.parseInt(eElement.getElementsByTagName("maxNoOfArrays").item(0).getTextContent());
                    minInterfacesToImplement = Integer.parseInt(eElement.getElementsByTagName("minInterfacesToImplement").item(0).getTextContent());
                    noOfInterfaces = Integer.parseInt(eElement.getElementsByTagName("noOfInterfaces").item(0).getTextContent());
                    maxNoOfMethodsPerInterface = Integer.parseInt(eElement.getElementsByTagName("maxNoOfMethodsPerInterface").item(0).getTextContent());
                    noOfClasses = Integer.parseInt(eElement.getElementsByTagName("noOfClasses").item(0).getTextContent());
                    maxAllowedMethodCalls = Integer.parseInt(eElement.getElementsByTagName("maxAllowedMethodCalls").item(0).getTextContent());
                    maxValueForLoop = Integer.parseInt(eElement.getElementsByTagName("maxValueForLoop").item(0).getTextContent());
                    allowIndirectRecursion = eElement.getElementsByTagName("allowIndirectRecursion").item(0).getTextContent();
                    maximumArraySize = Integer.parseInt(eElement.getElementsByTagName("maximumArraySize").item(0).getTextContent());
                    noOfInheritanceChains = Integer.parseInt(eElement.getElementsByTagName("noOfInheritanceChains").item(0).getTextContent());
                    maxNestedIfs = Integer.parseInt(eElement.getElementsByTagName("maxNestedIfs").item(0).getTextContent());
                    minNoOfClassFields = Integer.parseInt(eElement.getElementsByTagName("minNoOfClassFields").item(0).getTextContent());
                    allowArray = eElement.getElementsByTagName("allowArray").item(0).getTextContent();
                    maxInheritanceDepth = Integer.parseInt(eElement.getElementsByTagName("maxInheritanceDepth").item(0).getTextContent());
                    minInheritanceDepth = Integer.parseInt(eElement.getElementsByTagName("minInheritanceDepth").item(0).getTextContent());
                    maxNoOfParametersPerMethod = Integer.parseInt(eElement.getElementsByTagName("maxNoOfParametersPerMethod").item(0).getTextContent());
                    minNoOfParametersPerMethod = Integer.parseInt(eElement.getElementsByTagName("minNoOfParametersPerMethod").item(0).getTextContent());
                    totalLOC = Integer.parseInt(eElement.getElementsByTagName("totalLOC").item(0).getTextContent());
                    maxInterfacesToImplement = Integer.parseInt(eElement.getElementsByTagName("maxInterfacesToImplement").item(0).getTextContent());
                    maxRecursionDepth = Integer.parseInt(eElement.getElementsByTagName("maxRecursionDepth").item(0).getTextContent());
                    classNamePrefix = eElement.getElementsByTagName("classNamePrefix").item(0).getTextContent();
                    maxNoOfClassFields = Integer.parseInt(eElement.getElementsByTagName("maxNoOfClassFields").item(0).getTextContent());
                    maxNoOfMethodsPerClass = Integer.parseInt(eElement.getElementsByTagName("maxNoOfMethodsPerClass").item(0).getTextContent());
                    recursionProbability = Float.parseFloat(eElement.getElementsByTagName("recursionProbability").item(0).getTextContent());
                    intMaxValue = Integer.parseInt(eElement.getElementsByTagName("intMaxValue").item(0).getTextContent());
                }
            }
            nList = doc.getElementsByTagName("allowedTypes");
            for (int temp = 0; temp < nList.getLength(); temp++) {
                Node nNode = nList.item(temp);
                if (nNode.getNodeType() == Node.ELEMENT_NODE) {
                    Element eElement = (Element) nNode;
                    allowedTypes.add(eElement.getElementsByTagName("type1").item(0).getTextContent());
                    allowedTypes.add(eElement.getElementsByTagName("type2").item(0).getTextContent());
                    allowedTypes.add(eElement.getElementsByTagName("type3").item(0).getTextContent());
                    allowedTypes.add(eElement.getElementsByTagName("type4").item(0).getTextContent());
                    allowedTypes.add(eElement.getElementsByTagName("type5").item(0).getTextContent());
                    allowedTypes.add(eElement.getElementsByTagName("type6").item(0).getTextContent());
                    allowedTypes.add(eElement.getElementsByTagName("type7").item(0).getTextContent());
                    allowedTypes.add(eElement.getElementsByTagName("type8").item(0).getTextContent());
                    allowedTypes.add(eElement.getElementsByTagName("type9").item(0).getTextContent());
                }
            }

        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        }


    }

    public static void main(String[] args){
        Configuration conf = new Configuration();
    }
}
