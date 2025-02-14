package money;

public class Pair {
    private String from;
    private String to;

    Pair (String from, String to) {
        this.from = from;
        this.to = to;
    }

    public boolean equals(Object object) {
        Pair pair = (Pair) object;
        // 引数のオブジェクトの変換元(from)と変換先(to)が等しいか判定
        return from.equals(pair.from) && to.equals(pair.to);
    }

    public int hashCode() {
        // HACK: 取り扱う通貨が増えたタイミングで再実装
        return 0;
    }
}
