package org.specnaz.testng;

import org.specnaz.Specnaz;
import org.specnaz.params.SpecnazParams;
import org.testng.IAlterSuiteListener;
import org.testng.xml.XmlClass;
import org.testng.xml.XmlSuite;
import org.testng.xml.XmlTest;

import java.util.List;
import java.util.stream.Collectors;

public final class SpecnazAlterSuiteListener implements IAlterSuiteListener {
    @Override
    public void alter(List<XmlSuite> xmlSuites) {
        for (XmlSuite xmlSuite : xmlSuites) {
            alterXmlSuite(xmlSuite);
        }
    }

    private void alterXmlSuite(XmlSuite xmlSuite) {
        xmlSuite.setTests(xmlSuite.getTests().stream()
                .map(xmlTest -> alterXmlTest(xmlTest))
                .collect(Collectors.toList()));
    }

    private XmlTest alterXmlTest(XmlTest xmlTest) {
        xmlTest.setClasses(xmlTest.getClasses().stream()
                .map(xmlClass -> alterXmlClass(xmlClass))
                .collect(Collectors.toList()));
        return xmlTest;
    }

    private XmlClass alterXmlClass(XmlClass xmlClass) {
        if (isSpecnazClass(xmlClass))
            xmlClass.getExcludedMethods().add(".*describes");
        return xmlClass;
    }

    private boolean isSpecnazClass(XmlClass xmlClass) {
        return Specnaz.class.isAssignableFrom(xmlClass.getSupportClass()) ||
                SpecnazParams.class.isAssignableFrom(xmlClass.getSupportClass());
    }
}
