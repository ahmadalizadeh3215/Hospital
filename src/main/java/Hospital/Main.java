package Hospital;

import Hospital.DataBase.FakeDB;
import Hospital.Entity.*;
import Hospital.Exception.InvalidInput;
import Hospital.Exception.InvalidNationalCode;
import Hospital.Exception.NoDataFound;
import Hospital.Service.*;

import javax.xml.crypto.Data;
import java.text.SimpleDateFormat;
import java.util.*;

public class Main {
    private static final Scanner scanner = new Scanner(System.in);
    private static final AminService aminService = new AminService();
    private static final ClinicService clinicService = new ClinicService();
    private static final PatientService patientService = new PatientService();
    private static final PhysicianService physicianService = new PhysicianService();
    private static final PrescriptionService prescriptionService = new PrescriptionService();
    private static final TurnRatingsService turnRatingsService = new TurnRatingsService();
    private static final FakeDB fakeDB = new FakeDB();
    private static Integer selectOption;
    private static String password, nationalCode;
    private static Admin admin;
    private static Clinic clinic;
    private static Patient patient;
    private static Physician physician;
    private static Prescription prescription;
    private static TurnRatings turnRatings;



    public static void main(String[] args) {
        fakeDB.fillDB();
        mainMenu();
    }

    private static void mainMenu() {
        try {
            System.out.println("main menu");
            System.out.println("1.sign in");
            System.out.println("2.sign up");
            System.out.print("please select one option:");
            selectOption = getNumber();
            if (selectOption == 1) {
                menuLogin();

            } else if (selectOption == 2) {
                menuSignup();
            } else
                throw new InvalidInput();
        } catch (InvalidInput e) {
            System.out.println(e.getMessage());
            mainMenu();
        }
    }

    private static void menuSignup() {
        try {
            System.out.println("1.Admin ");
            System.out.println("2.patient ");
            System.out.println("3.physician ");
            System.out.print("please select one option:");
            selectOption = getNumber();
            if (selectOption <= 3) {
                registerMenu(selectOption);
            } else {
                throw new InvalidInput();
            }
        } catch (InvalidInput e) {
            System.out.println(e.getMessage());
            mainMenu();
        }
    }

    public static void registerMenu(int typeOfUser) {
        try {
            System.out.print("national code:");
            String nationalCode = scanner.next();
            checkNationalCode(nationalCode);
            scanner.nextLine();
            System.out.print("first name:");
            String firstName = scanner.next();
            scanner.nextLine();
            System.out.print("last name:");
            String lastName = scanner.next();
            scanner.nextLine();
            System.out.print("password:");
            String password = scanner.next();
            System.out.print("phone Number:");
            String phoneNumber = scanner.next();
            scanner.nextLine();
            if (typeOfUser == 1) {
                Admin admin = new Admin(Integer.valueOf(nationalCode), firstName, lastName
                        , password, phoneNumber);
                aminService.save(admin);
                System.out.println("register success!");
            } else if (typeOfUser == 2) {
                System.out.print("PatientDossier :");
                String patientDossier = scanner.next();
                Patient patient = new Patient(Integer.valueOf(nationalCode), firstName
                        , lastName, password, phoneNumber, patientDossier);
                patientService.save(patient);
                System.out.println("register success!");
            } else if (typeOfUser == 3) {
                System.out.print("Expertise :");
                String expertise = scanner.next();
                Physician physician = new Physician(Integer.valueOf(nationalCode), firstName
                        , lastName, password, phoneNumber, expertise);
                physicianService.save(physician);
                System.out.println("register success!");
            } else {
                throw new InvalidInput();
            }
        } catch (InvalidInput | InvalidNationalCode e) {
            System.out.println(e.getMessage());
            mainMenu();
        }
        mainMenu();
    }

    private static void menuLogin() {
        try {
            System.out.println("1.Admin ");
            System.out.println("2.patient ");
            System.out.println("3.physician ");
            System.out.print("please select one option:");
            selectOption = getNumber();
            if (selectOption <= 3) {
                scanner.nextLine();
                System.out.println("national code: ");
                nationalCode = scanner.nextLine();
                checkNationalCode(nationalCode);
                System.out.println("password : ");
                password = scanner.nextLine();
                if (selectOption == 1) {
                    admin = aminService.login(Integer.valueOf(nationalCode), password);
                    System.out.println("sign in success!");
                    adminMenu();
                } else if (selectOption == 2) {
                    patient = patientService.login(Integer.valueOf(nationalCode), password);
                    System.out.println("sign in success!");
                    patientMenu();
                } else if (selectOption == 3) {
                    physician = physicianService.login(Integer.valueOf(nationalCode), password);
                    System.out.println("sign in success!");

                }
            } else {
                throw new InvalidInput();
            }
        } catch (InvalidInput | NoDataFound e) {
            System.out.println(e.getMessage());
            mainMenu();
        } catch (InvalidNationalCode w) {
            System.out.println(w);
            mainMenu();
        }
    }

    private static void patientMenu() {
        try {
            System.out.println("1.show info: ");
            System.out.println("2.show info prescription: ");
            System.out.println("3.get turnRatings :");
            selectOption = getNumber();
            if (selectOption <= 3) {
                switch (selectOption) {
                    case 1:
                        System.out.println(patientService.findById(Integer.valueOf(nationalCode)));
                        patientMenu();
                        break;
                    case 2:
                        System.out.println(prescriptionService.PreviousVersions(Integer.valueOf(nationalCode)));
                        patientMenu();
                        break;
                    case 3:
                        MenuTurnRating();
                        break;

                }
            }

        } catch (InvalidInput e) {
            System.out.println(e.getMessage());
            mainMenu();
        }

    }

    private static void MenuTurnRating() {
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm");
        try {
            System.out.println(clinicService.findAll());
            System.out.print("please select one id:");
            selectOption = getNumber();
            scanner.nextLine();
            if (clinicService.findById(selectOption)!=null) {
                clinic = clinicService.findById(selectOption);
                if (clinicService.checkCapacity(selectOption)) {
                    TurnRatings turnRatings = new TurnRatings();
                   prescriptionService.save(new Prescription("eshal",patient,clinic.getPhysician()));
                    turnRatings.setVisitDate(formatter.format(new Date()));
                    turnRatings.setPatient(patient);
                    turnRatings.setClinic(clinic);
                    turnRatings.setPrescription(prescription);
                    turnRatingsService.save(turnRatings);
                    int cap = clinic.getCapacity() - 1;
                    clinic.setCapacity(cap);
                    clinicService.update(clinic);
                    System.out.println("turnRating is success!");
                    patientMenu();

                } else {
                    System.out.println("Capacity is full !!! ");
                    throw new NoDataFound();
                }
            }else {
                System.out.println("clinic id not found !!!");
                throw new NoDataFound();
            }

        } catch ( NoDataFound e) {
            scanner.nextLine();
            System.out.println(e.getMessage());

            patientMenu();
        }
    }

    private static void adminMenu() {
    }

    private static void checkNationalCode(String nationalCode) {
        char[] array = nationalCode.toCharArray();
        for (char a : array) {
            if (!Character.isDigit(a))
                throw new InvalidNationalCode();
        }
        if (nationalCode.length() != 10)
            throw new InvalidNationalCode();
    }

    public static int getNumber() {
        while (true) {
            try {
                return scanner.nextInt();

            } catch (InputMismatchException e) {
                System.out.println("Invalid value  ");
                System.out.println("Enter correct value : ");
                scanner.nextLine();
            }
        }

    }
}
