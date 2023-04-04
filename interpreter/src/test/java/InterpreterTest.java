import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class InterpreterTest {
    @Test
    public void testing(){
        Main m = new Main();
        assertEquals(8,m.func());
    }
}
