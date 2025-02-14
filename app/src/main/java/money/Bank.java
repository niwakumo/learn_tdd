package money;

import java.util.Map;
import java.util.HashMap;

public class Bank {
    // 変換元と変換先のペア→レートのハッシュテーブル
    private Map<Pair, Integer> rates = new HashMap<> ();

    // reduce = 式を単純な形に変形する, 通貨を変換する
    Money reduce(Expression source, String to) {
        return source.reduce(this, to);
    }

    void addRate(String from, String to, int rate) {
        // ハッシュテーブルにレートを追加
        rates.put(new Pair(from, to), rate);
    }

    int rate(String from, String to) {
        // 変換元と変換先が同じ通貨の場合、レートは1を返す
        if (from.equals(to)) {
            return 1;
        }
        // ハッシュテーブルを参照してレートを返す
        return rates.get(new Pair(from, to));
    }
}
