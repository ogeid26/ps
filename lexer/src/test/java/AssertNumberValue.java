import org.junit.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class AssertNumberValue {
    @Test
    public void testAdd() {
        assertEquals(42, Integer.sum(19, 23));
    }
}
