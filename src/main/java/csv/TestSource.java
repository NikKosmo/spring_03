package csv;

import tester.model.Test;

import java.io.IOException;

public interface TestSource {
    Test getTest() throws IOException;
}
