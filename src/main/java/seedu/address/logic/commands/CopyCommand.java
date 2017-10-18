package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.List;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.person.ReadOnlyPerson;

public class CopyCommand extends Command {

    public static final String COMMAND_WORD = "c";
    public static final String CHOICE_NAME = "n";
    public static final String CHOICE_EMAIL = "e";
    public static final String CHOICE_PHONE = "p";
    public static final String CHOICE_ADDRESS = "a";

    public static final String MESSAGE_SUCCESS = "Data has been copied to the clipboard";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Copies the details of the person identified "
            + "by index number used in the last person listing. "
            + "Data would be copied to the system clipboard.\n"
            + "Parameters: INDEX (must be a positive integer) "
            + "PREFIX\n"
            + "Example: " + COMMAND_WORD + " 1 " + CHOICE_NAME;
    public static final String POSSIBLE_CHOICES = "Invalid choice input,the valid choices to be copied are:\n"
            + "1. " + CHOICE_NAME + " (name)\n"
            + "2. " + CHOICE_ADDRESS + " (address)\n"
            + "3. " + CHOICE_EMAIL + " (email)\n"
            + "4. " + CHOICE_PHONE + " (phone)\n";

    private final Index index;
    private final String choice;

    public CopyCommand(Index index, String choice) {
        this.index = index;
        this.choice = choice;
    }

    @Override
    public CommandResult execute() throws CommandException {
        requireNonNull(model);
        List<ReadOnlyPerson> lastShownList = model.getFilteredPersonList();

        if (index.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
        }

        ReadOnlyPerson targetPerson = lastShownList.get(index.getZeroBased());
        model.copy(targetPerson, choice);
        return new CommandResult(MESSAGE_SUCCESS);
    }
}
