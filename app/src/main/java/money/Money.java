package money;

class Money implements Expression {
    protected int amount;
    protected String currency;

    Money(int amount, String currency) {
        this.amount = amount;
        this.currency = currency;
    }

    public boolean equals(Object object) {
        Money money = (Money) object;
        return amount == money.amount
            && currency().equals(money.currency());
    }

    static Money dollar(int amount) {
        return new Money(amount, "USD");
    }

    static Money franc(int amount) {
        return new Money(amount, "CHF");
    }

    String currency() {
        return currency;
    }

    public Money times(int multiplier) {
        return new Money(amount * multiplier, currency);
    };

    public String toString() {
        return amount + " " + currency;
    }

    // Expressionのメソッド
    public Expression plus(Expression addend) {
        return new Sum(this, addend);
    }

    // Expressionのメソッド
    public Money reduce (Bank bank, String to) {
        // Bankのrate関数で変換レートを計算
        int rate = bank.rate(currency, to);
        return new Money(amount / rate, to);
    }

}
