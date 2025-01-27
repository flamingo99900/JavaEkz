import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.println("Введите значения формата rgb для конвертации в cmyk: ");
        int r = sc.nextInt();
        int g = sc.nextInt();
        int b = sc.nextInt();
        double[] cmyk = RGBconverter.RGBtoCMYK(r, g, b);
        System.out.printf("%.2f, %.2f, %.2f, %.2f\n", cmyk[0], cmyk[1], cmyk[2], cmyk[3]);

        System.out.println("Введите значения в формате cmyk для конвертации в rgb:");
        double c = sc.nextDouble();
        double m = sc.nextDouble();
        double y = sc.nextDouble();
        double k = sc.nextDouble();
        int[] rgb = CMYKconverter.CMYKtoRGB(c, m, y, k);
        System.out.println(rgb[0] + ", " + rgb[1] + ", " + rgb[2]);
    }
}
