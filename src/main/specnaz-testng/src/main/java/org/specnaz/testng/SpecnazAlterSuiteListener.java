package org.specnaz.testng;

import org.specnaz.Specnaz;
import org.specnaz.params.SpecnazParams;
import org.testng.IAlterSuiteListener;
import org.testng.xml.XmlClass;
import org.testng.xml.XmlPackage;
import org.testng.xml.XmlSuite;
import org.testng.xml.XmlTest;

import java.util.List;

public final class SpecnazAlterSuiteListener implements IAlterSuiteListener {
    @Override
    public void alter(List<XmlSuite> xmlSuites) {
        xmlSuites.forEach(xmlSuite -> alterXmlSuite(xmlSuite));
    }

    private void alterXmlSuite(XmlSuite xmlSuite) {
        xmlSuite.getTests().forEach(xmlTest -> alterXmlTest(xmlTest));

        xmlSuite.getPackages().forEach(xmlPackage -> alterXmlPackage(xmlPackage));
    }

    private void alterXmlTest(XmlTest xmlTest) {
        xmlTest.getPackages().forEach(xmlPackage -> alterXmlPackage(xmlPackage));

        xmlTest.getClasses().forEach(xmlClass -> alterXmlClass(xmlClass));
    }

    private void alterXmlPackage(XmlPackage xmlPackage) {
        xmlPackage.getXmlClasses().forEach(xmlClass -> alterXmlClass(xmlClass));
    }

    private void alterXmlClass(XmlClass xmlClass) {
        if (isSpecnazClass(xmlClass)) {
            xmlClass.getExcludedMethods().add(".*describes");
        }
    }

    private boolean isSpecnazClass(XmlClass xmlClass) {
        return Specnaz.class.isAssignableFrom(xmlClass.getSupportClass()) ||
                SpecnazParams.class.isAssignableFrom(xmlClass.getSupportClass());
    }
}
