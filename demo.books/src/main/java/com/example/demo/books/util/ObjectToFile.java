package com.example.demo.books.util;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;

@Service
public class ObjectToFile {

    private static final Logger logger = LoggerFactory.getLogger(ObjectToFile.class);

    public <T> void writeObjectToFileAsJson(String path, T ObjectToSave) throws IOException {

        ObjectMapper mapper = new ObjectMapper();
        try {
            mapper.writeValue(new File(path), ObjectToSave);
        } catch (Exception e) {
            logger.error("error to save object to file:" + e.toString());
            throw e;
        }
    }

    public <T> T convertJsonFileToPOJOObject(File file, Class<T> target) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        T value = null;
        try {
            value = mapper.readValue(file, target);
            return value;
        } catch (Exception e) {
            logger.error("error to read object from file:" + e.toString());
            throw e;
        }

    }


}
