package Income.model;

import java.time.LocalDate;

public class Income {

    private IncomeAccount account;
    private LocalDate date;
    private Double amount;
    private IncomeCategory category;
    private String description;

    /*public Income(String account, LocalDate date, Double amount, IncomeCategory category, String description) {
        this.account = account;
        this.date = date;
        this.amount = amount;
        this.category = category;
        this.description = description;
    }*/

    public IncomeAccount getAccount() { return account; }

    public void setAccount(IncomeAccount account) { this.account = account; }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public IncomeCategory getCategory() {
        return category;
    }

    public void setCategory(IncomeCategory category) {
        this.category = category;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "Income{" +
                "account='" + account + '\'' +
                ", date=" + date +
                ", amount=" + amount +
                ", category=" + category +
                ", description='" + description + '\'' +
                '}';
    }
}
