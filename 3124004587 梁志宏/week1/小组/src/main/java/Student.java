public class Student extends User{
    private String num;
    private int xf;
    private String name;

    public Student(String name, String email) {
        super(name, email);
    }

    public Student(String name, String email, String num, int xf) {
        super(name, email);
        this.num = num;
        this.xf = xf;
        this.name = name;
    }

    public String getNum() {
        return num;
    }

    public void setNum(String num) {
        this.num = num;
    }

    public int getXf() {
        return xf;
    }

    public void setXf(int xf) {
        this.xf = xf;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}