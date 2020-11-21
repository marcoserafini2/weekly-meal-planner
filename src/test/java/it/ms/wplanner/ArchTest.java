package it.ms.wplanner;

import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.noClasses;

import com.tngtech.archunit.core.domain.JavaClasses;
import com.tngtech.archunit.core.importer.ClassFileImporter;
import com.tngtech.archunit.core.importer.ImportOption;
import org.junit.jupiter.api.Test;

class ArchTest {

    @Test
    void servicesAndRepositoriesShouldNotDependOnWebLayer() {
        JavaClasses importedClasses = new ClassFileImporter()
            .withImportOption(ImportOption.Predefined.DO_NOT_INCLUDE_TESTS)
            .importPackages("it.ms.wplanner");

        noClasses()
            .that()
            .resideInAnyPackage("it.ms.wplanner.service..")
            .or()
            .resideInAnyPackage("it.ms.wplanner.repository..")
            .should()
            .dependOnClassesThat()
            .resideInAnyPackage("..it.ms.wplanner.web..")
            .because("Services and repositories should not depend on web layer")
            .check(importedClasses);
    }
}
