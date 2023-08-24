package exercise;

import static org.assertj.core.api.Assertions.assertThat;
import java.util.List;
import java.util.Arrays;
import java.util.ArrayList;
import org.junit.jupiter.api.Test;

class AppTest {

    @Test
    void testTake() {
        // BEGIN
        List<Integer> list1 = new ArrayList<>();
        List<Integer> expected1 = new ArrayList<>();
        List<Integer> result1 = App.take(list1, 3);
        assertThat(result1).isEqualTo(expected1);

        List<Integer> list2 = new ArrayList<>(Arrays.asList(1, 2, 3, 4));
        List<Integer> expected2 = new ArrayList<>(Arrays.asList(1, 2, 3, 4));
        List<Integer> result2 = App.take(list2, 10);
        assertThat(result2).isEqualTo(expected2);

        List<Integer> list3 = new ArrayList<>(Arrays.asList(1, 2, 3));
        List<Integer> expected3 = new ArrayList<>(Arrays.asList(1, 2));
        List<Integer> result3 = App.take(list3, 2);
        assertThat(result3).isEqualTo(expected3);
        // END
    }
}
