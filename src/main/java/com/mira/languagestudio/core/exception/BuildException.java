package com.mira.languagestudio.core.exception;


/**
 * {@code BuildException} is thrown when a build process fails.
 *
 * <p>This exception is typically thrown when configuration files fail to load or when there is an error in the configuration settings. This exception can also be thrown if there is an issue with the environment or dependencies required for the build process.
 *
 * <p> Note that this exception is only thrown when there is an error with the configuration files and not with any manually added code. Any issues with the manually added code should be handled separately.
 *
 * @since 1.0
 */
public class BuildException extends Exception {

    public BuildException() {
        super();
    }

    public BuildException(String message) {
        super(message);
    }
}
