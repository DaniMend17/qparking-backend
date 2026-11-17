package com.github.danimend17.qparking.architecture;

import com.github.danimend17.qparking.QparkingApplication;
import org.junit.jupiter.api.Test;
import org.springframework.modulith.core.ApplicationModules;

class ModularityTest {

    private final ApplicationModules modules = ApplicationModules.of(QparkingApplication.class);

    @Test
    void verifyModularArchitecture() {
        modules.verify();
    }

}
