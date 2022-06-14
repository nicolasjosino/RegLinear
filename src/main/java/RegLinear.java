import java.awt.geom.Point2D;
import java.util.*;

@SuppressWarnings("SpellCheckingInspection")
public class RegLinear {
    private final double taxa;
    private double coefA;
    private double coefB;
    private final ArrayList<Point2D> base;
    private final ArrayList<Point2D> points1;
    private final ArrayList<Point2D> points2;

    public RegLinear(Double taxa, Double coefA, Double coefB, ArrayList<Point2D> base, Point2D pt1, Point2D pt2) {
        this.taxa = taxa;
        this.coefA = coefA;
        this.coefB = coefB;
        this.base = base;
        this.points1 = new ArrayList<>();
        this.points2 = new ArrayList<>();
        this.points1.add(pt1);
        this.points2.add(pt2);
    }

    public double getTaxa() {
        return taxa;
    }

    public double getCoefA() {
        return coefA;
    }

    public void setCoefA(double coefA) {
        this.coefA = coefA;
    }

    public double getCoefB() {
        return coefB;
    }

    public void setCoefB(double coefB) {
        this.coefB = coefB;
    }

    public ArrayList<Point2D> getBase() {
        return base;
    }

    public ArrayList<Point2D> getPoints1() {
        return points1;
    }

    public ArrayList<Point2D> getPoints2() {
        return points2;
    }

    public String getPoint1(int i) {
        return "(" + getPoints1().get(i).getX() + ", " + getPoints1().get(i).getY() + ")";
    }

    public String getPoint2(int i) {
        return "(" + getPoints2().get(i).getX() + ", " + getPoints2().get(i).getY() + ")";
    }

    private void calculaNovaReta() {
        Point2D newPt1 = new Point2D.Double();
        Point2D newPt2 = new Point2D.Double();
        double x1 = points1.get(0).getX();
        double x2 = points2.get(0).getX();
        double y1, y2;
        int i = points1.size()-1;

        y1 = x1 * coefA + coefB;
        y2 = x2 * coefA + coefB;
        newPt1.setLocation(x1, y1);
        newPt2.setLocation(x2, y2);

        points1.add(newPt1);
        points2.add(newPt2);
        System.out.println("reta " + i + ": " +
                           getPoint1(i) +
                           " a " + getPoint2(i));
    }

    public void calculo(double taxaErro, double erroMedio) {
        ArrayList<Double> erros = new ArrayList<>();
        ArrayList<Double> errosX = new ArrayList<>();
        ArrayList<Double> errosQuad = new ArrayList<>();
        double errosSum, errosXSum, errosQuadSum, erroMedioAnterior;

        for (Point2D pt : base) {
            double predicao = pt.getX() * coefA + coefB;
            double erro = predicao - pt.getY();
            erros.add(erro);
            errosX.add(erro * pt.getX());
            errosQuad.add(Math.pow(erro, 2));
        }

        errosSum = erros.stream().mapToDouble(Double::doubleValue).sum();
        errosXSum = errosX.stream().mapToDouble(Double::doubleValue).sum();
        errosQuadSum = errosQuad.stream().mapToDouble(Double::doubleValue).sum();

        coefA = coefA - taxa * (2.0 / base.size()) * errosSum;
        coefB = coefB - taxa * (2.0 / base.size()) * errosXSum;
        calculaNovaReta();
        erroMedioAnterior = erroMedio;
        erroMedio = errosQuadSum / base.size();

        if ((erroMedio > erroMedioAnterior) && (erroMedioAnterior != 0)){
            System.out.println("Erro médio começou a aumentar, encerrando...");
            System.out.println("Erro médio anterior: " + erroMedioAnterior);
            System.out.println("Erro médio: " + erroMedio);
            System.exit(1);
        }
        else if (erroMedio > taxaErro)
            calculo(taxaErro, erroMedio);
    }
}