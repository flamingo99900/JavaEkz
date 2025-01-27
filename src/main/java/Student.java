class Student extends User {
    private double grant;
    private int course;

    public void setGrant(double grant) {
        this.grant = grant;
    }

    public double getGrant() {
        return grant;
    }

    public void setCourse(int course) {
        this.course = course;
    }

    public int getCourse() {
        return course;
    }
}
