package indi.wyx0k.story.core;

/**
 * story
 * --
 *
 * @author wyx
 * --
 * 2020/4/12
 */
public class CommandHandleException extends RuntimeException {
    public CommandHandleException() {
    }

    public CommandHandleException(String message) {
        super(message);
    }
}
