package Grupo1.BackEndG1CP2.services;

import org.springframework.core.io.Resource;

public interface FileService {

    public Resource load(String name) throws Exception;
}
