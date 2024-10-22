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

    @Test
    @DisplayName("フラン→ドルの変換をテスト")
    public void testReduceMoneyDifferentCurrency() {
        Bank bank = new Bank();
        bank.addRate("CHF", "USD", 2);
        Money result = bank.reduce(Money.franc(2), "USD");
        assertEquals(Money.dollar(1), result);
    }

    @Test
    @DisplayName("学習用テスト：通貨の紐付けに使うハッシュテーブルを理解する")
    public void testArrayEquals() {
        // Javaの仕様上等しくならないので、assertFalseとしてテストを残す
        assertFalse((new Object[] {"abc"}).equals(new Object[] {"abc"}));
    }

    @Test
    @DisplayName("回帰テスト: 同じ通貨へのレート算出を確認")
    public void testIdentityRate() {
        Bank bank = new Bank();
        // bank.addRate("USD", "USD", 1); 同じ通貨はレート１で返すのが正しいのでこれは不要
        assertEquals(1, bank.rate("USD", "USD"));
    }

    @Test
    @DisplayName("CHFとUSDの加算結果をUSDに変換する")
    public void testMixedAdditon() {
        Expression fiveBucks = Money.dollar(5);
        Expression tenFrancs = Money.franc(10);
        Bank bank = new Bank();
        // "CHF" / 2 -> "USD" 
        bank.addRate("CHF", "USD", 2);
        // (5$ + 10F)の結果をUSDに変換する
        Money result = bank.reduce(fiveBucks.plus(tenFrancs), "USD");
        // 変換結果が10$になることを確認
        assertEquals(Money.dollar(10), result);
    }

}

