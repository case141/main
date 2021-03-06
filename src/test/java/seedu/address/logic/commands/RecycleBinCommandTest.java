package seedu.address.logic.commands;

import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.logic.commands.CommandTestUtil.showSourceAtIndex;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_SOURCE;
import static seedu.address.testutil.TypicalSources.getTypicalDeletedSources;
import static seedu.address.testutil.TypicalSources.getTypicalSourceManager;

import org.junit.Before;
import org.junit.Test;

import seedu.address.logic.CommandHistory;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.ParserMode;
import seedu.address.model.UserPrefs;

/**
 * Contains integration tests (interaction with the Model) and unit tests for RecycleBinCommand.
 */
public class RecycleBinCommandTest {
    private Model model;
    private Model expectedModel;
    private CommandHistory commandHistory = new CommandHistory();

    @Before
    public void setUp() {
        model = new ModelManager(getTypicalSourceManager(), new UserPrefs(), getTypicalDeletedSources(), 0);
        expectedModel = new ModelManager(model.getSourceManager(), new UserPrefs(), model.getDeletedSources(), 0);
        expectedModel.setParserMode(ParserMode.SOURCE_MANAGER);
    }

    @Test
    public void execute_binNotFiltered_showsSameBin() {
        expectedModel.setParserMode(ParserMode.RECYCLE_BIN);
        assertCommandSuccess(new RecycleBinCommand(), model, commandHistory,
                RecycleBinCommand.MESSAGE_LIST_ALL_DELETED_SUCCESS, expectedModel);
    }

    @Test
    public void execute_binIsFiltered_showsEverything() {
        showSourceAtIndex(model, INDEX_FIRST_SOURCE);
        expectedModel.setParserMode(ParserMode.RECYCLE_BIN);
        assertCommandSuccess(new RecycleBinCommand(), model, commandHistory,
                RecycleBinCommand.MESSAGE_LIST_ALL_DELETED_SUCCESS, expectedModel);
    }
}

