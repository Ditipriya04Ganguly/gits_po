package com.CAR.ClinicalAlgorithmRules.config;

import org.kie.api.KieServices;
import org.kie.api.builder.*;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.kie.internal.io.ResourceFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.IOException;

@Configuration
public class Droolconfig {
    private KieServices kieservices= KieServices.Factory.get();
    private KieFileSystem getKieFileSystem() throws IOException{
        KieFileSystem kiefilesystem= kieservices.newKieFileSystem();
        kiefilesystem.write(ResourceFactory.newClassPathResource("rules.drl"));
        return kiefilesystem;
    }

    private void getKieRepository(){
        final KieRepository kierepository= kieservices.getRepository();
        kierepository.addKieModule(new KieModule() {
            @Override
            public ReleaseId getReleaseId() {
                return kierepository.getDefaultReleaseId();
            }
        });
    }

    @Bean
    public KieContainer getKieContainer() throws IOException {
        getKieRepository();
        KieBuilder kb= kieservices.newKieBuilder(getKieFileSystem());
        kb.buildAll();
        KieModule kiemodule= kb.getKieModule();
        KieContainer kcontainer= kieservices.newKieContainer(kiemodule.getReleaseId());
        return kcontainer;
    }

    @Bean
    public KieSession getKieSession() throws IOException{
        return getKieContainer().newKieSession();
    }
}
