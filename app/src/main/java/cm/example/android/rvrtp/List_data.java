package cm.example.android.rvrtp;

public class List_data {
    String name, sal, date,notice;

    public List_data(String name, String sal, String date, String notice) {
        this.name = name;
        this.sal = sal;
        this.date = date;
        this.notice = notice;
    }

    public List_data() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSal() {
        return sal;
    }

    public void setSal(String sal) {
        this.sal = sal;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getNotice() {
        return notice;
    }

    public void setNotice(String notice) {
        this.notice = notice;
    }
}