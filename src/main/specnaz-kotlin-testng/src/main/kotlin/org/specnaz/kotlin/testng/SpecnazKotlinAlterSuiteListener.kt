package org.specnaz.kotlin.testng

import org.specnaz.kotlin.SpecnazKotlin
import org.specnaz.kotlin.params.SpecnazKotlinParams
import org.testng.IAlterSuiteListener
import org.testng.xml.XmlClass
import org.testng.xml.XmlSuite
import org.testng.xml.XmlTest

class SpecnazKotlinAlterSuiteListener : IAlterSuiteListener {
    override fun alter(xmlSuites: List<XmlSuite>) {
        for (xmlSuite in xmlSuites) {
            alterXmlSuite(xmlSuite)
        }
    }

    private fun alterXmlSuite(xmlSuite: XmlSuite) {
        xmlSuite.tests = xmlSuite.tests
                .map { xmlTest -> alterXmlTest(xmlTest) }
    }

    private fun alterXmlTest(xmlTest: XmlTest): XmlTest {
        xmlTest.classes = xmlTest.classes
                .map { xmlClass -> alterXmlClass(xmlClass) }
        return xmlTest
    }

    private fun alterXmlClass(xmlClass: XmlClass): XmlClass {
        if (isSpecnazKotlinClass(xmlClass))
            xmlClass.excludedMethods.add(".*describes")
        return xmlClass
    }

    private fun isSpecnazKotlinClass(xmlClass: XmlClass): Boolean {
        return SpecnazKotlin::class.java.isAssignableFrom(xmlClass.supportClass) ||
                SpecnazKotlinParams::class.java.isAssignableFrom(xmlClass.supportClass)
    }
}
