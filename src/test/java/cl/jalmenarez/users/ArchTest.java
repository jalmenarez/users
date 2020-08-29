package cl.jalmenarez.users;

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
            .importPackages("cl.jalmenarez.users");

        noClasses()
            .that()
            .resideInAnyPackage("cl.jalmenarez.users.service..")
            .or()
            .resideInAnyPackage("cl.jalmenarez.users.repository..")
            .should()
            .dependOnClassesThat()
            .resideInAnyPackage("..cl.jalmenarez.users.web..")
            .because("Services and repositories should not depend on web layer")
            .check(importedClasses);
    }
}
