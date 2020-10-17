package edu.birzeit.monitoringsystem.uaa;

import com.tngtech.archunit.core.domain.JavaClasses;
import com.tngtech.archunit.core.importer.ClassFileImporter;
import com.tngtech.archunit.core.importer.ImportOption;
import org.junit.jupiter.api.Test;

import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.noClasses;

class ArchTest {

    @Test
    void servicesAndRepositoriesShouldNotDependOnWebLayer() {

        JavaClasses importedClasses = new ClassFileImporter()
            .withImportOption(ImportOption.Predefined.DO_NOT_INCLUDE_TESTS)
            .importPackages("edu.birzeit.monitoringsystem.uaa");

        noClasses()
            .that()
                .resideInAnyPackage("edu.birzeit.monitoringsystem.uaa.service..")
            .or()
                .resideInAnyPackage("edu.birzeit.monitoringsystem.uaa.repository..")
            .should().dependOnClassesThat()
                .resideInAnyPackage("..edu.birzeit.monitoringsystem.uaa.web..")
        .because("Services and repositories should not depend on web layer")
        .check(importedClasses);
    }
}
