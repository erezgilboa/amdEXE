package com.example.demo.books.services;

import com.example.demo.books.util.ObjectToFile;
import entities.Descriptor;
import entities.ResultWithSuccess;
import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
@PropertySource("classpath:application.properties")
public class DescriptorServiceImpl implements IDescriptorService {

    private static final Logger logger = LoggerFactory.getLogger(DescriptorServiceImpl.class);
    @Value("${descriptorFilePath}")
    private String descriptorFilePath;

    @Autowired
    ObjectToFile objectToFile;

    Map<String, Descriptor> descriptors = new HashMap<>();

    @Override
    public ResultWithSuccess getDescriptorByName(String name) {
        Descriptor descriptor = descriptors.get(name);
        if (descriptor == null)
            return new ResultWithSuccess(descriptor, false, "cant find descriptor");
        return new ResultWithSuccess(descriptor, true, "");
    }

    @Override
    public ResultWithSuccess saveOrUpdate(Descriptor descriptor) {
        if (descriptor == null) {
            logger.error("descriptor is null");
            return new ResultWithSuccess(null, false, "descriptor is null");
        }
        try {
            objectToFile.writeObjectToFileAsJson(descriptorFilePath+"/" + descriptor.getBookName() + ".json", descriptor);
            descriptors.put(descriptor.getBookName(), descriptor);
            return new ResultWithSuccess(descriptor, true, "");
        } catch (Exception e) {
            logger.error("error to save object to file:" + e.toString());
            return new ResultWithSuccess(descriptor, false, "error to save:" + e.toString());
        }

    }

    @PostConstruct
    public void init() {

        File dir = new File(descriptorFilePath);
        String[] extensions = new String[]{"json"};
        List<File> files = (List<File>) FileUtils.listFiles(dir, extensions, false);
        for (File file : files) {
            try {
                Descriptor descriptor = objectToFile.convertJsonFileToPOJOObject(file, Descriptor.class);
                if (descriptor != null)
                    descriptors.put(descriptor.getBookName(), descriptor);
            } catch (Exception e) {
                logger.error("error to load file to object:" + e.toString());
            }

        }
    }
}
