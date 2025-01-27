class CMYKConverter extends ColorModel {
    private double c, m, y, k;

    public CMYKConverter(double c, double m, double y, double k) {
        this.c = c;
        this.m = m;
        this.y = y;
        this.k = k;
    }

    @Override
    public void convert() {
        int r = (int) (255 * (1 - c) * (1 - k));
        int g = (int) (255 * (1 - m) * (1 - k));
        int b = (int) (255 * (1 - y) * (1 - k));

        System.out.printf("RGB: %d, %d, %d\n", r, g, b);
    }
}