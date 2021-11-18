public class Patient {
    private String fullname, email, gender, date, password, placement;
    Patient(){}
    Patient(String fullname, String email, String gender, String date, String password, String placement){
        this.fullname = fullname;
        this.email=email; this.gender=gender;
        this.date = date;
        this.password = password;
        this.placement = placement;
    }
}
