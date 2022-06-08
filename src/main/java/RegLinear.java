import java.awt.geom.Point2D;
import java.util.*;

@SuppressWarnings("SpellCheckingInspection")
public class RegLinear {
    private Double taxa;
    private Double coefA;
    private Double coefB;
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

    public Double getTaxa() {
        return taxa;
    }

    public void setTaxa(Double taxa) {
        this.taxa = taxa;
    }

    public Double getCoefA() {
        return coefA;
    }

    public void setCoefA(Double coefA) {
        this.coefA = coefA;
    }

    public Double getCoefB() {
        return coefB;
    }

    public void setCoefB(Double coefB) {
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

    public void Calculo() {
        double erroMedio;
        ArrayList<Double> erros = new ArrayList<>();
        ArrayList<Double> errosX = new ArrayList<>();
        ArrayList<Double> errosQuad = new ArrayList<>();
        double errosSum, errosXSum, errosQuadSum;

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

        coefA = coefA - taxa * (2 / 3) * errosSum;
        coefB = coefB - taxa * (2 / 3) * errosXSum;
        erroMedio = errosQuadSum / base.size();

        if (erroMedio > 0)
            Calculo();
    }
}