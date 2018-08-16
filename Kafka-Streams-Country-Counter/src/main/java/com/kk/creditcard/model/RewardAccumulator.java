package com.kk.creditcard.model;
public class RewardAccumulator {

   // private String customerName;
    private String customerId;
    private double purchaseTotal;
    private int totalRewardPoints;
    private int currentRewardPoints;
    private int daysFromLastPurchase;
    
    // default 
    
    public RewardAccumulator() {

    }

    public RewardAccumulator(String customerName, double purchaseTotal) {
        this.customerId = customerName;
        this.purchaseTotal = purchaseTotal;
    }
    
    private RewardAccumulator(String customerId, double purchaseTotal, int rewardPoints) {
        this.customerId = customerId;
        this.purchaseTotal = purchaseTotal;
        this.currentRewardPoints = rewardPoints;
        this.totalRewardPoints = rewardPoints;
    }

    /*public String getCustomerName() {
        return customerName;
    }*/

    public String getCustomerId() {
        return customerId;
    }

    public double getPurchaseTotal() {
        return purchaseTotal;
    }

    public int getCurrentRewardPoints() {
        return currentRewardPoints;
    }

    public int getTotalRewardPoints() {
        return totalRewardPoints;
    }

    public void addRewardPoints(int previousTotalPoints) {
        this.totalRewardPoints += previousTotalPoints;
    }

    @Override
    public String toString() {
        return "RewardAccumulator{" +
                "customerId='" + customerId + '\'' +
                ", purchaseTotal=" + purchaseTotal +
                ", totalRewardPoints=" + totalRewardPoints +
                ", currentRewardPoints=" + currentRewardPoints +
                ", daysFromLastPurchase=" + daysFromLastPurchase +
                '}';
    }
    
    
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof RewardAccumulator)) return false;

        RewardAccumulator that = (RewardAccumulator) o;

        if (Double.compare(that.purchaseTotal, purchaseTotal) != 0) return false;
        if (totalRewardPoints != that.totalRewardPoints) return false;
        if (currentRewardPoints != that.currentRewardPoints) return false;
        if (daysFromLastPurchase != that.daysFromLastPurchase) return false;
        return customerId != null ? customerId.equals(that.customerId) : that.customerId == null;
    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = customerId != null ? customerId.hashCode() : 0;
        temp = Double.doubleToLongBits(purchaseTotal);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        result = 31 * result + totalRewardPoints;
        result = 31 * result + currentRewardPoints;
        result = 31 * result + daysFromLastPurchase;
        return result;
    }
    
    
    public static Builder builder(Purchase purchase){return new Builder(purchase);}

    public static final class Builder {
     //   private String customerName;
        private String customerId;
        private double purchaseTotal;
        private int daysSinceLastVisit;
        private int rewardPoints;

        private Builder(Purchase purchase){
          // this.customerName = purchase.getLastName()+","+purchase.getFirstName();
           this.customerId = purchase.getCustomerId();
           this.purchaseTotal = purchase.getPrice() * (double) purchase.getQuantity();
           this.rewardPoints = (int) purchaseTotal;
        }


        public RewardAccumulator build(){
        	 return new RewardAccumulator(customerId, purchaseTotal, rewardPoints);
        }

    }
}