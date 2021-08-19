package by.training.task08xml.bean;

public class TariffWithoutMinutes extends Tariff{
    private double withingNetwork;
    private double outOfNetwork;

    public TariffWithoutMinutes(Tariff tariff) {
        super.setName(tariff.getName());
        super.setDate(tariff.getDate());
        super.setOperatorName(tariff.getOperatorName());
        super.setPayroll(tariff.getPayroll());
        super.getParameters().setSubscription(tariff.getParameters().getSubscription());
        super.getParameters().setWifiHosting(tariff.getParameters().getWifiHosting());
        super.getParameters().setFreeInternet(tariff.getParameters().getFreeInternet());
        super.getParameters().setAccumulation(tariff.getParameters().isAccumulation());
        super.getParameters().setFavorite(tariff.getParameters().isFavorite());
    }

    public TariffWithoutMinutes(){}

    public double getWithinNetwork() {
        return withingNetwork;
    }

    public double getOutOfNetwork() {
        return outOfNetwork;
    }

    public void setWithingNetwork(double withingNetwork) {
        this.withingNetwork = withingNetwork;
    }

    public void setOutOfNetwork(double outOfNetwork) {
        this.outOfNetwork = outOfNetwork;
    }

    @Override
    public String toString() {
        return super.toString() +
                "withingNetwork=" + withingNetwork +
                ", outOfNetwork=" + outOfNetwork +
                '}';
    }
}
