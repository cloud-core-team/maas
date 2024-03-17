package org.cloud.core.maas;

import io.quarkus.runtime.Quarkus;
import io.quarkus.runtime.annotations.QuarkusMain;

@QuarkusMain
public class MaasMain {
    public static void main(String[] args) {
        Quarkus.run(args);
    }
}
