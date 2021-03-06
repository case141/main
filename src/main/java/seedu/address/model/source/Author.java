package seedu.address.model.source;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;
import static seedu.address.model.source.Source.validateSourceAttribute;

/**
 * Represents a Source's Author(s) in the infinity machine.
 * Guarantees: immutable; is valid as declared in {@link #isValidAuthor(String)}
 */
public class Author {

    public static final String MESSAGE_CONSTRAINTS = "Author names can take any value, and it should not be blank";

    /*
     * The first character of an Author's name must not be a whitespace,
     * otherwise " " (a blank string) becomes a valid input.
     */
    public static final String VALIDATION_REGEX = "[^\\s].*";

    public final String author;

    /**
     * Constructs a {@code Author}.
     *
     * @param author A valid author.
     */
    public Author(String author) {
        requireNonNull(author);
        checkArgument(validateSourceAttribute(author, VALIDATION_REGEX), MESSAGE_CONSTRAINTS);
        this.author = author;
    }

    public static boolean isValidAuthor(String test) {
        return validateSourceAttribute(test, VALIDATION_REGEX);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Author // instanceof handles nulls
                && author.equals(((Author) other).author)); // state check
    }

    @Override
    public int hashCode() {
        return author.hashCode();
    }

    /**
     * Format state as text for viewing.
     */
    @Override
    public String toString() {
        return author;
    }
}
