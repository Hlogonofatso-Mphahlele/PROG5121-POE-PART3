
package chatapp;

public class Login {
    
    private String username;
    private String password;
    private String cellPhoneNumber;
    
    public Login(String username, String password, String cellPhoneNumber) {
        this.username = username;
        this.password = password;
        this.cellPhoneNumber = cellPhoneNumber;
    }
    
    //te username must have an umderscore and not have more than 5 charecters
    public boolean checkUserName() {
        return username.contains("_") && username.length() <= 5;
    }
    
    //the password must have 8+ charecters, a capital letter, a number, a special chareter
    public boolean checkPasswordComplexity() {
        boolean hasLength = password.length() >= 8;
        boolean hasCapitalLetter = password.matches(".*[A-Z].*");
        boolean hasANumber = password.matches(".*[0-9].*");
        boolean hasSpecialCharecter = password.matches(".*[~!@#$%^&*()_+{}|:<>?].*");
        
        return hasLength && hasCapitalLetter && hasANumber && hasSpecialCharecter;
    }
    
    //Cellphone number must have Mzanzi's international cell code
    public boolean checkCellPhoneNumber() {
        return cellPhoneNumber.matches("^\\+27\\d{9}$");
    }
    
    public String registerUser() {
        if(!checkUserName()) {
            return "Username not correctly formatted; please ensure that your username has an underscore and doesnt have more than five charecters.";
        }
        
        if(!checkPasswordComplexity()) {
            return "Password is not correctly formatted, ensure that it has at least eight charecters, a capital letter, a number and a speecial charecter.";
        }
        
        if(!checkCellPhoneNumber()) {
            return "Cell phone number incorrectly formatted or does not contain the international code.";
        }
        return "User registered successfully";
    }
    
    public boolean loginUser(String enteredUsername, String enteredPassword) {
        return username.equals(enteredUsername) && password.equals(enteredPassword);
    }
    
    public String returnLoginStatus(boolean loginSuccess, String firstName, String lastName) {
        if(loginSuccess) {
            return "Welcome  " + firstName + " " + lastName + " it is great to see you again";
        }
        return "username or password incorrect, please try again";
    }
    
}
