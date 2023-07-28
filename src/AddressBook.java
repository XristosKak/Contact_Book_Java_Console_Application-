import java.util.ArrayList;
import java.util.Scanner;

public class AddressBook {
    private ArrayList<Contact> contacts;
    private Scanner scanner;

    public AddressBook() {
        contacts = new ArrayList<>();
        scanner = new Scanner(System.in);
    }

    public void start() {
        loadSampleContacts();  // Φορτώνει ενδεικτικές επαφές

        int choice;
        do {
            displayMenu();
            choice = getUserChoice();
            processUserChoice(choice);
        } while (choice != 7);
    }

    private void displayMenu() {
        System.out.println("Μενού επιλογών:");
        System.out.println("1. Προβολή όλων των επαφών");
        System.out.println("2. Προσθήκη νέας επαφής");
        System.out.println("3. Αναζήτηση επαφής βάσει ονόματος");
        System.out.println("4. Αναζήτηση επαφής βάσει τηλεφώνου");
        System.out.println("5. Επεξεργασία επαφής βάσει ονόματος");
        System.out.println("6. Διαγραφή επαφής βάσει ονόματος");
        System.out.println("7. Έξοδος από την εφαρμογή");
        System.out.print("Επιλέξτε μια επιλογή: ");
    }

    private int getUserChoice() {
        int choice;
        try {
            choice = scanner.nextInt();
        } catch (Exception e) {
            choice = -1;
        } finally {
            scanner.nextLine();
        }
        return choice;
    }

    private void processUserChoice(int choice) {
        System.out.println();
        switch (choice) {
            case 1:
                displayAllContacts();
                break;
            case 2:
                addNewContact();
                break;
            case 3:
                searchContactByName();
                break;
            case 4:
                searchContactByPhone();
                break;
            case 5:
                editContact();
                break;
            case 6:
                deleteContact();
                break;
            case 7:
                System.out.println("Αποχώρηση από την εφαρμογή. Αντίο!");
                break;
            default:
                System.out.println("Μη έγκυρη επιλογή. Παρακαλώ προσπαθήστε ξανά.");
                break;
        }
        System.out.println();
    }

    private void displayAllContacts() {
        if (contacts.isEmpty()) {
            System.out.println("Δεν υπάρχουν αποθηκευμένες επαφές.");
        } else {
            System.out.println("Αποθηκευμένες επαφές:");
            for (Contact contact : contacts) {
                System.out.println("Όνομα: " + contact.getName());
                System.out.println("Τηλέφωνο: " + contact.getPhone());
                System.out.println("Email: " + contact.getEmail());
                System.out.println("Διεύθυνση: " + contact.getAddress());
                System.out.println("-------------------------");
            }
        }
    }

    private void addNewContact() {
        System.out.print("Όνομα: ");
        String name = scanner.nextLine();
        System.out.print("Τηλέφωνο: ");
        String phone = scanner.nextLine();
        System.out.print("Email: ");
        String email = scanner.nextLine();
        System.out.print("Διεύθυνση: ");
        String address = scanner.nextLine();

        Contact newContact = new Contact(name, phone, email, address);
        contacts.add(newContact);
        System.out.println("Η επαφή προστέθηκε επιτυχώς.");
    }

    private void searchContactByName() {
        System.out.print("Εισαγάγετε το όνομα που αναζητάτε: ");
        String name = scanner.nextLine();

        boolean found = false;
        for (Contact contact : contacts) {
            if (contact.getName().equalsIgnoreCase(name)) {
                displayContactInfo(contact);
                found = true;
                break;
            }
        }

        if (!found) {
            System.out.println("Δεν βρέθηκε επαφή με το όνομα \"" + name + "\".");
        }
    }

    private void searchContactByPhone() {
        System.out.print("Εισαγάγετε το τηλέφωνο που αναζητάτε: ");
        String phone = scanner.nextLine();

        boolean found = false;
        for (Contact contact : contacts) {
            if (contact.getPhone().equals(phone)) {
                displayContactInfo(contact);
                found = true;
                break;
            }
        }

        if (!found) {
            System.out.println("Δεν βρέθηκε επαφή με το τηλέφωνο \"" + phone + "\".");
        }
    }

    private void editContact() {
        System.out.print("Εισαγάγετε το όνομα της επαφής προς επεξεργασία: ");
        String name = scanner.nextLine();

        boolean found = false;
        for (Contact contact : contacts) {
            if (contact.getName().equalsIgnoreCase(name)) {
                displayContactInfo(contact);

                System.out.println("Εισαγάγετε τα νέα στοιχεία:");

                System.out.print("Όνομα: ");
                String newName = scanner.nextLine();
                System.out.print("Τηλέφωνο: ");
                String newPhone = scanner.nextLine();
                System.out.print("Email: ");
                String newEmail = scanner.nextLine();
                System.out.print("Διεύθυνση: ");
                String newAddress = scanner.nextLine();

                contact.setName(newName);
                contact.setPhone(newPhone);
                contact.setEmail(newEmail);
                contact.setAddress(newAddress);

                System.out.println("Η επαφή ενημερώθηκε επιτυχώς.");
                found = true;
                break;
            }
        }

        if (!found) {
            System.out.println("Δεν βρέθηκε επαφή με το όνομα \"" + name + "\".");
        }
    }

    private void deleteContact() {
        System.out.print("Εισαγάγετε το όνομα της επαφής προς διαγραφή: ");
        String name = scanner.nextLine();

        boolean found = false;
        for (Contact contact : contacts) {
            if (contact.getName().equalsIgnoreCase(name)) {
                displayContactInfo(contact);
                System.out.print("Είστε βέβαιος ότι θέλετε να διαγράψετε αυτήν την επαφή; (Ν/Ο): ");
                String confirmation = scanner.nextLine();

                if (confirmation.equalsIgnoreCase("Ν")) {
                    contacts.remove(contact);
                    System.out.println("Η επαφή διαγράφηκε επιτυχώς.");
                } else {
                    System.out.println("Η διαγραφή ακυρώθηκε.");
                }

                found = true;
                break;
            }
        }

        if (!found) {
            System.out.println("Δεν βρέθηκε επαφή με το όνομα \"" + name + "\".");
        }
    }

    private void displayContactInfo(Contact contact) {
        System.out.println("Όνομα: " + contact.getName());
        System.out.println("Τηλέφωνο: " + contact.getPhone());
        System.out.println("Email: " + contact.getEmail());
        System.out.println("Διεύθυνση: " + contact.getAddress());
        System.out.println("-------------------------");
    }

    private void loadSampleContacts() {
        Contact contact1 = new Contact("Γιάννης", "1234567890", "giannis@example.com", "Αθήνα");
        Contact contact2 = new Contact("Μαρία", "9876543210", "maria@example.com", "Θεσσαλονίκη");
        Contact contact3 = new Contact("Πέτρος", "5555555555", "petros@example.com", "Πάτρα");

        contacts.add(contact1);
        contacts.add(contact2);
        contacts.add(contact3);
    }
}
