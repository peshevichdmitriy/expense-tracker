package balance;

import transaction.Account;

public class Balance {

    private Account account;
    private Double amount;
    private Double exchange_rate;

    public Balance(Account account, Double amount, Double exchange_rate) {
        this.account = account;
        this.amount = amount;
        this.exchange_rate = exchange_rate;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    public Double getExchange_rate() {
        return exchange_rate;
    }

    public void setExchange_rate(Double exchange_rate) {
        this.exchange_rate = exchange_rate;
    }

    @Override
    public String toString() {
        return "Balance{" +
                "account='" + account + '\'' +
                ", amount=" + amount +
                '}';
    }
}
