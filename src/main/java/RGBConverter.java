class RGBConverter extends ColorModel {
    private int r, g, b;

    public RGBConverter(int r, int g, int b) {
        this.r = r;
        this.g = g;
        this.b = b;
    }

    @Override
    public void convert() {
        double c = 1 - (r / 255.0);
        double m = 1 - (g / 255.0);
        double y = 1 - (b / 255.0);
        double k = Math.min(c, Math.min(m, y));

        if (k < 1) {
            c = (c - k) / (1 - k);
            m = (m - k) / (1 - k);
            y = (y - k) / (1 - k);
        } else {
            c = 0;
            m = 0;
            y = 0;
        }

        System.out.printf("CMYK: %.2f, %.2f, %.2f, %.2f\n", c, m, y, k);
    }
}