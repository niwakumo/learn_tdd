package money;

public class Bank {
    // reduce = 式を単純な形に変形する, 通貨を変換する
    Money reduce(Expression source, String to) {
        return source.reduce(to);
    }
}
