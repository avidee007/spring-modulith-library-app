package com.avi.modulith;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.modulith.core.ApplicationModules;
import org.springframework.modulith.docs.Documenter;

@SpringBootTest
class ModulithLibraryApplicationTests {

    /*@Test
    void contextLoads() {
    }*/


    @Test
    void verifiesModularStructure() {
        ApplicationModules modules = ApplicationModules.of(ModulithLibraryApplication.class);
        modules.forEach(System.out::println);
        modules.verify();
        new Documenter(modules)
                .writeDocumentation()
                .writeIndividualModulesAsPlantUml();
    }

}
