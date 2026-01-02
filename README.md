# Healthcare Management System

A Java-based application for managing patient records, clinician details, and appointments in a medical context. Built using **Java Swing** and following the **MVC (Model-View-Controller)** architectural pattern.

## 🚀 Features
* **MVC Architecture:** strict separation of data (Model), logic (Controller), and interface (View).
* **Singleton Pattern:** Implemented in `ReferralManager` to ensure centralized handling of referral requests.
* **Data Persistence:** Custom CSV handling (`CSVHandler`) to load and save patient/clinician data without external databases.
* **Dynamic GUI:** Interactive `JTable` views with sorting and scrolling capabilities.

## 🛠️ Tech Stack
* **Language:** Java (JDK 17+)
* **GUI Framework:** Swing
* **Data Storage:** CSV (Comma Separated Values)
* **IDE:** IntelliJ IDEA

## 📂 Project Structure
* `model/` - Data classes (Patient, Clinician, Appointment)
* `view/` - GUI components (MainFrame, TableModels)
* `controller/` - Logic handlers (ReferralManager)
* `util/` - Helper classes (CSVHandler)

## 👤 Author
[Visula Kaushalya / 24152437]