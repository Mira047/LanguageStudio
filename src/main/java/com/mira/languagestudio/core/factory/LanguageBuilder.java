package com.mira.languagestudio.core.factory;

import com.mira.languagestudio.core.base.memory.HashMemory;
import com.mira.languagestudio.core.base.memory.LanguageMemory;
import com.mira.languagestudio.core.exception.BuildException;
import com.mira.languagestudio.core.factory.internal.Instruction;
import com.mira.languagestudio.core.factory.resourceloader.DefaultResourceLoader;
import com.mira.languagestudio.core.factory.settings.LanguageInfo;
import com.mira.languagestudio.core.util.SerializableConsumer;
import com.mira.languagestudio.core.util.SerializablePredicate;

import java.io.Serializable;
import java.net.URISyntaxException;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.List;

/**
 * The {@code LanguageBuilder} class is used to create and configure a new language.
 *
 * <p>A LanguageBuilder instance can be used to register new instructions, set the name, identifier, version, author and description of the language and build the {@code LanguageInfo} object that contains all the information and settings of the language.
 *
 * <p>The LanguageBuilder also has a {@code Registry} inner class which is used to register instructions for the language, it can be registered using a string identifier or using a predicate.
 *
 * <p>The Registry class also has a {@code loadDefaults} method that can be used to load default instructions from a path.
 *
 * @see LanguageInfo
 * @see Registry
 * @since 1.0
 */
public class LanguageBuilder {
    private String name = "Default Language Name";
    private String identifier = "default";
    private String version = "0.0.1";
    private String author = "";
    private String description = "";

    private final Registry registry;

    /**
     * Creates a new instance of {@code LanguageBuilder}.
     *
     * @return a new instance of {@code LanguageBuilder}.
     */
    public static LanguageBuilder create() {
        return new LanguageBuilder();
    }

    private LanguageBuilder() {
        registry = new Registry();

        memoryTypeOf(new HashMemory());
    }

    /**
     * Registers a new instruction for the language using a string identifier.
     *
     * @param identifier the string identifier of the instruction.
     * @param implementation the implementation of the instruction as a {@code Consumer} of {@code List<String>}.
     */
    public LanguageBuilder register(String identifier, SerializableConsumer<List<String>> implementation) {
        registry.register(identifier, new Instruction(implementation));
        return this;
    }

    /**
     * Registers a new instruction for the language using a predicate.
     *
     * @param predicate the predicate to match the instruction.
     * @param implementation the implementation of the instruction as a {@code Consumer} of {@code List<String>}.
     */
    public LanguageBuilder register(SerializablePredicate<List<String>> predicate, SerializableConsumer<List<String>> implementation) {
        registry.register(predicate, new Instruction(implementation));
        return this;
    }

    /**
     * Sets the name of the language.
     *
     * @param name the name of the language.
     */
    public LanguageBuilder withName(String name) {
        this.name = name;

        return this;
    }

    /**
     * Sets the identifier of the language.
     *
     * @param identifier the identifier of the language.
     */
    public LanguageBuilder withIdentifier(String identifier) {
        this.identifier = identifier;

        return this;
    }

    /**
     * Sets the version of the language.
     *
     * @param version the version of the language.
     */
    public LanguageBuilder withVersion(String version) {
        this.version = version;

        return this;
    }

    /**
     * Sets the author of the language.
     *
     * @param author the author of the language.
     */
    public LanguageBuilder withAuthor(String author) {
        this.author = author;

        return this;
    }

    /**
     * Sets the description of the language.
     *
     * @param description the description of the language.
     */
    public LanguageBuilder withDescription(String description) {
        this.description = description;

        return this;
    }

    public LanguageBuilder memoryTypeOf(LanguageMemory<?> memory) {
        registry.memoryTypeOf(memory);
        return this;
    }

    public static LanguageMemory<?> getMemoryReference(LanguageBuilder builder) {
        return builder.getMemoryReference();
    }

    public LanguageMemory<?> getMemoryReference() {
        return registry.getMemory();
    }

    /**
     * Loads the default instructions from a path.
     *
     * @param path the path where the default instructions are located.
     */
    public LanguageBuilder loadDefaults(Path path) {
        try {
            registry.loadDefaults(path);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return this;
    }

    /**
     * Loads the default instructions from the resources' folder.
     */
    public LanguageBuilder loadDefaults() {
        try {
            Path path = Path.of(getClass().getClassLoader().getResource("data/" + identifier + "/language-info.json").toURI());

            registry.loadDefaults(path);
        } catch (URISyntaxException | BuildException | NullPointerException e) {
            e.printStackTrace();
        }

        return this;
    }

    /**
     * Builds the {@code LanguageInfo} object that contains all the information and settings of the language.
     *
     * @return {@code LanguageInfo} object that contains all the information and settings of the language.
     */
    public LanguageInfo build() {
        return new LanguageInfo(name, identifier, version, author, description, registry);
    }

    /**
     * The {@code Registry} inner class is used to register instructions for the language,
     * it can be registered using a string identifier or using a predicate.
     *
     * <p>The Registry class also has a {@code loadDefaults} method that can be used to load default instructions from a path.
     *
     * @since 1.0
     */
    public static class Registry implements Serializable {
        private final HashMap<SerializablePredicate<List<String>>, Instruction> CUSTOM_IMPL = new HashMap<>();

        private final HashMap<SerializablePredicate<List<String>>, Instruction> DEFAULT_IMPL = new HashMap<>();

        private LanguageMemory<?> memory;

        protected Registry() {

        }

        protected void memoryTypeOf(LanguageMemory<?> memory) {
            this.memory = memory;
        }

        protected void register(String name, Instruction instruction) {
            CUSTOM_IMPL.put(args -> {
                if (args.size() == 0) {
                    return false;
                }

                return args.get(0).equals(name);
            }, instruction);

        }

        protected void register(SerializablePredicate<List<String>> predicate, Instruction instruction) {
            CUSTOM_IMPL.put(predicate, instruction);
        }

        protected void loadDefaults(Path path) throws BuildException {
            DefaultResourceLoader loader = new DefaultResourceLoader(this, path.toFile());

            loader.load();
        }

        public LanguageMemory<?> getMemory() {
            return memory;
        }

        public HashMap<SerializablePredicate<List<String>>, Instruction> getInstructions() {
            HashMap<SerializablePredicate<List<String>>, Instruction> instructions = new HashMap<>();

            instructions.putAll(DEFAULT_IMPL);
            instructions.putAll(CUSTOM_IMPL);

            return instructions;
        }
    }
}
