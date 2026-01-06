package controller;

import model.Referral;
import util.CSVHandler;
import java.util.List;
import java.util.ArrayList;

public class ReferralManager {
    // 1. The static instance (The Singleton)
    private static ReferralManager instance;

    // The data it manages
    private List<Referral> referrals;
    private CSVHandler csvHandler;

    // 2. Private Constructor (Prevents direct instantiation)
    private ReferralManager() {
        this.csvHandler = new CSVHandler();
        this.referrals = new ArrayList<>();
    }

    // 3. Public Method to get the ONLY instance
    public static ReferralManager getInstance() {
        if (instance == null) {
            instance = new ReferralManager();
        }
        return instance;
    }

    // --- Business Logic Methods ---

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