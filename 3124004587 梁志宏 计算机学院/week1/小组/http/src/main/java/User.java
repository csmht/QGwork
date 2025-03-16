public  class  User{
    private String mima;
    private String stuid;

    public User(String mima, String name) {
        this.mima = mima;
        this.stuid = name;
    }



    public String getMima() {
        return mima;
    }
    public void setMima(String mima) {
        this.mima = mima;
    }

    public String getStuid() {
        return stuid;
    }
    public void setStuid(String name) {
        this.stuid = name;
    }


}