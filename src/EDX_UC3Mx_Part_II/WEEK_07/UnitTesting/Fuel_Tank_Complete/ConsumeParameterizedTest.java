package EDX_UC3Mx_Part_II.WEEK_07.UnitTesting.Fuel_Tank_Complete;

import static org.junit.Assert.*;

import java.util.Arrays;
import java.util.Collection;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

@RunWith(Parameterized.class)
public class ConsumeParameterizedTest {

    FuelTank tank = new FuelTank(60,50);

    private int param;
    private int result;

    public ConsumeParameterizedTest(int param, int result) {
        this.param = param;
        this.result = result;
    }

    @Parameters
    public static Collection<Object[]> data() {
        return Arrays.asList(new Object[][]{
                {10,20},
                {15,35},
                {20,30},
                {35,45}
        });
    }

    @Test
    public void testConsume() {
        tank.consume(param);
        assertEquals(result, tank.getTankLevel(), 0);
    }

}