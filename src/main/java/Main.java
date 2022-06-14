import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        ArrayList<Point2D> base = new ArrayList<>();
        Scanner user = new Scanner(System.in);
        double taxaAprendizado = 0;
        double coefA = 0;
        double coefB = 0;
        Point2D pt1 = null;
        Point2D pt2 = null;
        double taxaErro = 0;

        try {
            System.out.print("Digite a taxa de aprendizado: ");
            taxaAprendizado = user.nextDouble();

            System.out.println("Digite os coeficientes A e B iniciais:");
            System.out.print("A: ");
            coefA = user.nextDouble();
            System.out.print("B: ");
            coefB = user.nextDouble();

            System.out.println("Digite o ponto inicial da primeira reta: ");
            System.out.print("X: ");
            double x1 = user.nextDouble();
            System.out.print("Y: ");
            double y1 = user.nextDouble();
            pt1 = new Point2D.Double(x1, y1);

            System.out.println("Digite o ponto final da primeira reta: ");
            System.out.print("X: ");
            double x2 = user.nextDouble();
            System.out.print("Y: ");
            double y2 = user.nextDouble();
            pt2 = new Point2D.Double(x2, y2);

            System.out.print("Digite a quantidade de pontos da base: ");
            int n = user.nextInt();

            System.out.println("Digite os valores X e Y de cada ponto: ");
            for (int i = 1; i <= n; i++) {
                System.out.print("X" + i + ": ");
                double x = user.nextDouble();
                System.out.print("Y" + i + ": ");
                double y = user.nextDouble();
                base.add(new Point2D.Double(x, y));
            }

            System.out.print("Digite a taxa de erro aceita: ");
            taxaErro = user.nextDouble();
        } catch (InputMismatchException e) {
            System.out.println("Digite um valor válido.");
            System.out.println("Encerrando...");
            System.exit(1);
        }

        RegLinear r1 = new RegLinear(taxaAprendizado, coefA, coefB, base, pt1, pt2);
        r1.calculo(taxaErro, 0);
    }
}
