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
    public void alter(List<XmlSuite> suites) {
        suites.forEach(suite -> alterSuite(suite));
    }

    private void alterSuite(XmlSuite suite) {
        suite.getTests().forEach(test -> alterTest(test));

        suite.getPackages().forEach(package_ -> alterPackage(package_));
    }

    private void alterTest(XmlTest test) {
        test.getXmlPackages().forEach(package_ -> alterPackage(package_));

        test.getXmlClasses().forEach(xmlClass -> alterXmlClass(xmlClass));
    }

    private void alterPackage(XmlPackage package_) {
        package_.getXmlClasses().forEach(xmlClass -> alterXmlClass(xmlClass));
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
