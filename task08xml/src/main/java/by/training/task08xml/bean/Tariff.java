package by.training.task08xml.bean;

public class Tariff {
    private String name;
    private String operatorName;
    private double payroll;
    private String date;

    private TariffParameters parameters;

    public Tariff() {
        parameters = new TariffParameters();
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setOperatorName(String operatorName) {
        this.operatorName = operatorName;
    }

    public void setPayroll(double payroll) {
        this.payroll = payroll;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getName() {
        return name;
    }

    public String getOperatorName() {
        return operatorName;
    }

    public double getPayroll() {
        return payroll;
    }

    public String getDate() {
        return date;
    }

    @Override
    public String toString() {
        return "Tariff{" +
                "name='" + name + '\'' +
                ", operatorName='" + operatorName + '\'' +
                ", payroll=" + payroll +
                ", date='" + date + '\'' +
                ", parameters=" + parameters +
                '}';
    }

    public TariffParameters getParameters() {
        return parameters;
    }

    public class TariffParameters {
        private String freeInternet;
        private String wifiHosting;
        private boolean favorite;
        private String subscription;
        private boolean accumulation;

        public String getFreeInternet() {
            return freeInternet;
        }

        public String getWifiHosting() {
            return wifiHosting;
        }

        public boolean isFavorite() {
            return favorite;
        }

        public String getSubscription() {
            return subscription;
        }

        public boolean isAccumulation() {
            return accumulation;
        }

        public void setFreeInternet(String freeInternet) {
            this.freeInternet = freeInternet;
        }

        public void setWifiHosting(String wifiHosting) {
            this.wifiHosting = wifiHosting;
        }

        public void setFavorite(boolean favorite) {
            this.favorite = favorite;
        }

        public void setSubscription(String subscription) {
            this.subscription = subscription;
        }

        public void setAccumulation(boolean accumulation) {
            this.accumulation = accumulation;
        }

        @Override
        public String toString() {
            return "TariffParameters{" +
                    "freeInternet='" + freeInternet + '\'' +
                    ", wifiHosting='" + wifiHosting + '\'' +
                    ", favorite=" + favorite +
                    ", subscription='" + subscription + '\'' +
                    ", accumulation=" + accumulation +
                    '}';
        }
    }
}
