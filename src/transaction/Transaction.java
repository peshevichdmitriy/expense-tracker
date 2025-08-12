package transaction;

import java.time.LocalDate;

public class Transaction {

    private Account account;
    private LocalDate date;
    private Double amount;
    private Category category;
    private String description;

    /*public Expense(ExpenseAccount account, LocalDate date, Double amount, ExpenseCategory category, String description) {
        this.account = account;
        this.date = date;
        this.amount = amount;
        this.category = category;
        this.description = description;
    }*/

    public ExpenseAccount getAccount() { return account; }

    public void setAccount(ExpenseAccount account) { this.account = account; }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public ExpenseCategory getCategory() {
        return category;
    }

    public void setCategory(ExpenseCategory category) {
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
        return "Expense{" +
                "account='" + account + '\'' +
                ", date=" + date +
                ", amount=" + amount +
                ", category=" + category +
                ", description='" + description + '\'' +
                '}';
    }
}
