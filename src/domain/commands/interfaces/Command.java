package domain.commands.interfaces;

import domain.commands.EmptyInput;

public interface Command<T extends EmptyInput> {
    void execute(T input);
}
