package app.models;

public class ErrorModel {
    private String message;
    private boolean critical;

    public ErrorModel() {

    }

    public ErrorModel(String message, boolean critical) {
        this.message = message;
        this.critical = critical;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public boolean isCritical() {
        return critical;
    }

    public void setCritical(boolean critical) {
        this.critical = critical;
    }
}
