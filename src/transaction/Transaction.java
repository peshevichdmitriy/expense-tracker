package transaction;

import java.time.LocalDate;

public class Transaction {

    private Integer ID;
    private Account account;
    private LocalDate date;
    private Double amount;
    private Category category;
    private String description;

    /*public Transaction(ExpenseAccount account, LocalDate date, Double amount, ExpenseCategory category, String description) {
        this.account = account;
        this.date = date;
        this.amount = amount;
        this.category = category;
        this.description = description;
    }*/

    public Integer getID() { return ID; }

    public void setID(Integer ID) { this.ID = ID; }

    public Account getAccount() { return account; }

    public void setAccount(Account account) { this.account = account; }

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

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
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
        return "Transaction {" +
                "account='" + account + '\'' +
                ", date=" + date +
                ", amount=" + amount +
                ", category=" + category +
                ", description='" + description + '\'' +
                '}';
    }
}
