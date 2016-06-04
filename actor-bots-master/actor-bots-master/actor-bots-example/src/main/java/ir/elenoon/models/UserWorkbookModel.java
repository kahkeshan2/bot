package ir.elenoon.models;

/**
 * Created by mohammad on 6/4/16.
 */
public class UserWorkbookModel {

    private String userSelectedOption;
    private String trueOption;

    public UserWorkbookModel() {
    }

    public UserWorkbookModel(String userSelectedOption, String trueOption) {
        this.userSelectedOption = userSelectedOption;
        this.trueOption = trueOption;
    }

    public String getUserSelectedOption() {
        return userSelectedOption;
    }

    public void setUserSelectedOption(String userSelectedOption) {
        this.userSelectedOption = userSelectedOption;
    }

    public String getTrueOption() {
        return trueOption;
    }

    public void setTrueOption(String trueOption) {
        this.trueOption = trueOption;
    }
}
