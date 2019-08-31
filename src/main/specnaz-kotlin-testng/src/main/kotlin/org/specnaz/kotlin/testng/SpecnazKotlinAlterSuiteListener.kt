package org.specnaz.kotlin.testng

import org.specnaz.kotlin.SpecnazKotlin
import org.specnaz.kotlin.params.SpecnazKotlinParams
import org.testng.IAlterSuiteListener
import org.testng.xml.XmlClass
import org.testng.xml.XmlPackage
import org.testng.xml.XmlSuite
import org.testng.xml.XmlTest

class SpecnazKotlinAlterSuiteListener : IAlterSuiteListener {
    override fun alter(suites: List<XmlSuite>) {
        suites.forEach { suite -> alterSuite(suite) }
    }

    private fun alterSuite(suite: XmlSuite) {
        suite.tests.forEach { test -> alterTest(test) }

        suite.packages.forEach { package_ -> alterPackage(package_) }
    }

    private fun alterTest(test: XmlTest) {
        test.xmlPackages.forEach { package_ -> alterPackage(package_) }

        test.xmlClasses.forEach { xmlClass -> alterXmlClass(xmlClass) }
    }

    private fun alterPackage(package_: XmlPackage) {
        package_.xmlClasses.forEach { xmlClass -> alterXmlClass(xmlClass) }
    }

    private fun alterXmlClass(xmlClass: XmlClass) {
        if (isSpecnazKotlinClass(xmlClass)) {
            xmlClass.excludedMethods.add(".*describes")
        }
    }

    private fun isSpecnazKotlinClass(xmlClass: XmlClass): Boolean {
        return SpecnazKotlin::class.java.isAssignableFrom(xmlClass.supportClass) ||
                SpecnazKotlinParams::class.java.isAssignableFrom(xmlClass.supportClass)
    }
}
