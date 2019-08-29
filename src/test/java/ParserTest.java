import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class ParserTest {
    private TaskList tasks;
    private Ui ui;
    private Storage storage;

    @BeforeEach
    public void setUp() {
        this.tasks = new TaskListStub();
        this.ui = new UiStub();
        this.storage = new StorageStub();
    }

    @Test
    public void parse_bye_success() throws Exception {
        assertEquals(ByeCommand.class, Parser.parse("bye").getClass());
    }

    @Test
    public void parse_illegalCommand_success() throws Exception {
        assertEquals(IllegalCommand.class, Parser.parse("howerhiowe").getClass());
    }
}
