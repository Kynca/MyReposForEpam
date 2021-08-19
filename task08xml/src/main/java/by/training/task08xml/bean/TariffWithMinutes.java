package by.training.task08xml.bean;

public class TariffWithMinutes extends Tariff {
    private String freeWithin;
    private String freeOut;

    public TariffWithMinutes() {
    }

    public TariffWithMinutes(Tariff tariff) {
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

    public String getFreeWithin() {
        return freeWithin;
    }

    public String getFreeOut() {
        return freeOut;
    }

    public void setFreeWithin(String freeWithin) {
        this.freeWithin = freeWithin;
    }

    public void setFreeOut(String freeOut) {
        this.freeOut = freeOut;
    }

    @Override
    public String toString() {
        return super.toString() +
                "freeWithin='" + freeWithin + '\'' +
                ", freeOut='" + freeOut + '\'' +
                '}';
    }
}
