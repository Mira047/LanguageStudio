package com.mira.languagestudio.core.factory.resourceloader;

import com.mira.languagestudio.core.factory.LanguageBuilder;

import java.io.File;

/**
 * DefaultResourceLoader is a class that is responsible for loading all data from JSON files
 * into the registry.
 *
 * <p>The class provides a way of loading language information, instructions, and other data
 * from JSON files and storing them in a registry. It also provides methods for adding new 
 * resources and updating existing ones.
 *
 * @since 1.0
 */
public class DefaultResourceLoader {

    private final LanguageBuilder.Registry registry;

    private final File languageInfoFile;

    /**
     * Creates a new DefaultResourceLoader instance with the given Registry
     *
     * @param registry the registry to load data into
     */
    public DefaultResourceLoader(LanguageBuilder.Registry registry, File languageInfoFile) {
        this.registry = registry;

        this.languageInfoFile = languageInfoFile;
    }

    /**
     * Loads all data from the JSON files into the registry.
     */
    public void load() {

    }

    /**
     * Loads data from the given JSON file and stores it in the registry
     *
     * @param jsonFile the JSON file to load data from
     */
    public void load(File jsonFile) {
        // code to load data from jsonFile and store it in the registry
    }

    /**
     * Adds a new resource to the registry
     *
     * @param key the key to identify the resource
     * @param value the value of the resource
     */
    public void addResource(String key, Object value) {
        // code to add the resource to the registry
    }

    /**
     * Updates an existing resource in the registry
     *
     * @param key the key to identify the resource
     * @param value the new value of the resource
     */
    public void updateResource(String key, Object value) {
        // code to update the resource in the registry
    }
}