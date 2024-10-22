package money;

class Sum implements Expression{
    Expression augend;
    Expression addend;

    public Sum(Expression augend, Expression addend) {
        this.augend = augend;
        this.addend = addend;
    }

    // interfaceのメソッドはpublicで定義する
    public Money reduce(Bank bank, String to) {
        // 足し算に使う値を2つとも変換する
        int amount = augend.reduce(bank, to).amount 
            + addend.reduce(bank, to).amount;
        return new Money(amount, to);
    }

    public Expression plus(Expression addend) {
        return null;
    }

}
