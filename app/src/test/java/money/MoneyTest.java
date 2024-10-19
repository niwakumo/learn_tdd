package money;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.DisplayName;

public class MoneyTest {
    @Test
    void testMultiplication() {
        Money five = Money.dollar(5);
        assertEquals(Money.dollar(10), five.times(2));

        assertEquals(Money.dollar(15), five.times(3));
    }

    @Test
    public void testEquality() {
        assertTrue(Money.dollar(5).equals(Money.dollar(5)));
        assertFalse(Money.dollar(5).equals(Money.dollar(6)));

        assertFalse(Money.franc(5).equals(Money.dollar(5)));
    }

    @Test
    public void testCurrency() {
        assertEquals("USD", Money.dollar(1).currency());
        assertEquals("CHF", Money.franc(1).currency());
    }

    @Test 
    public void testSimpleAddition() {
        Money five = Money.dollar(5);
        Expression sum = five.plus(five);
        Bank bank = new Bank();
        Money reduced = bank.reduce(sum, "USD");
        assertEquals(Money.dollar(10), reduced);
    }

    @Test
    public void testPlusReturnsSum() {
        Money five = Money.dollar(5);
        Expression result = five.plus(five);
        Sum sum = (Sum) result;
        assertEquals(five, sum.augend); // augend = 被加数
        assertEquals(five, sum.addend); 
    }

    @Test
    @DisplayName("合計した値をUSDに変換する")
    public void testReduceSum() {
        // 3ドルと4ドルを合計
        Expression sum = new Sum(Money.dollar(3), Money.dollar(4));
        Bank bank = new Bank();
        Money result = bank.reduce(sum, "USD"); // USDに変換
        assertEquals(Money.dollar(7), result); // sumの結果が7ドルになる
    }

    @Test
    @DisplayName("USDからUSDへの変換をテストする")
    public void testReduceMoney() {
        Bank bank = new Bank();
        Money result = bank.reduce(Money.dollar(1), "USD");
        assertEquals(Money.dollar(1), result);
    }

}

