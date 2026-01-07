package controller;

import model.Referral;

import java.util.ArrayList;
import java.util.List;

public class ReferralManager {
    //  The static instance (The Singleton)
    private static ReferralManager instance;


    private List<Referral> referrals;
    private CSVHandler csvHandler;

    //  Private Constructor
    private ReferralManager() {
        this.csvHandler = new CSVHandler();
        this.referrals = new ArrayList<>();
    }

    // Public Method to ONLY instance
    public static ReferralManager getInstance() {
        if (instance == null) {
            instance = new ReferralManager();
        }
        return instance;
    }

    // ------------- Business Logic Methods -------------

    public void loadData(String filePath) {
        this.referrals = csvHandler.loadReferrals(filePath);
    }

    public void saveData(String filePath) {
        csvHandler.saveReferrals(filePath, this.referrals);
    }

    public List<Referral> getAllReferrals() {
        return this.referrals;
    }

    public void addReferral(Referral r) {
        referrals.add(r);
    }

    public void updateReferral(int index, Referral r) {
        referrals.set(index, r);
    }

    public void deleteReferral(int index) {
        referrals.remove(index);
    }
}