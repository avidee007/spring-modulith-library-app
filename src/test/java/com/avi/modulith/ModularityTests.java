package com.avi.modulith;

import org.junit.jupiter.api.Test;
import org.springframework.modulith.core.ApplicationModules;
import org.springframework.modulith.docs.Documenter;

class ModularityTests {

    ApplicationModules modules = ApplicationModules.of(ModulithLibraryApplication.class);

    @Test
    void verifiesModularStructure() {
        modules.forEach(System.out::println);
        modules.verify();
    }

    @Test
    void createModuleDocumentationInUML() {
        new Documenter(modules)
                .writeDocumentation()
                .writeModulesAsPlantUml(Documenter.DiagramOptions.defaults().withStyle(Documenter.DiagramOptions.DiagramStyle.UML));
    }

    @Test
    void createModuleDocumentationInC4() {
        new Documenter(modules)
                .writeDocumentation();
    }
}