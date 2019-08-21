public class Line {
    String agent;
    double income;
    double expense;

    public Line(String agent, double income, double expense) {
        this.agent = agent;
        this.income = income;
        this.expense = expense;
    }

    public String getAgent() {
        return agent;
    }

    public double getIncome() {
        return income;
    }

    public double getExpense() {
        return expense;
    }
}
