package seedu.address.logic.commands;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.CommandHistory;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.source.Source;

import java.util.List;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.commands.AddCommand.MESSAGE_DUPLICATE_SOURCE;

/**
 * Restores a source identified using it's displayed index from the recently deleted list of sources.
 */
public class RestoreCommand extends Command{

    public static final String COMMAND_WORD = "restore";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Restores a source identified by the index number used in the recently deleted source list.\n"
            + "Parameters: INDEX (must be a positive integer)\n"
            + "Example: " + COMMAND_WORD + " 1";

    public static final String MESSAGE_DELETE_SOURCE_SUCCESS = "Restored Source: %1$s";

    private final Index targetIndex;

    public RestoreCommand(Index targetIndex) {
        this.targetIndex = targetIndex;
    }

    @Override
    public CommandResult execute(Model model, CommandHistory history) throws CommandException {
        requireNonNull(model);
        List<Source> recentlyDeletedList = model.getFilteredSourceList(); // need to change this to RD list
        Source toRestore = recentlyDeletedList.get(targetIndex.getZeroBased());

        if (targetIndex.getZeroBased() >= recentlyDeletedList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_SOURCE_DISPLAYED_INDEX);
        }

        if (model.hasSource(toRestore)) {
            throw new CommandException(MESSAGE_DUPLICATE_SOURCE);
        }

        model.addSource(toRestore);
        model.commitSourceManager();
        return new CommandResult(String.format(MESSAGE_DELETE_SOURCE_SUCCESS, toRestore));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof RestoreCommand // instanceof handles nulls
                && targetIndex.equals(((RestoreCommand) other).targetIndex)); // state check
    }
}