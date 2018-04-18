package model.testdata;

import java.util.Random;
import model.registration.Registration;

/**
 *
 * @author John Ferdinand Antonio
 */
public class StudentTestDataModel {
    
    public Registration getRegistrationRecord(int level, int gradeLevelId, int schoolYearId, int paymentTermId) {
        Random rand = new Random();
        
        int lastNameIndex = rand.nextInt(lastNameArr.length);
        int firstNameIndex = rand.nextInt(firstNameArr.length);
        int middleNameIndex = rand.nextInt(middleNameArr.length);
        int studentTypeIndex = rand.nextInt(studentTypeArr.length);
        int placeOfBirthIndex = rand.nextInt(placeOfBirthArr.length);
        int nationalityIndex = rand.nextInt(nationalityArr.length);
        int religionIndex = rand.nextInt(religionArr.length);
        int genderIndex = rand.nextInt(genderArr.length);
        int fatherLastNameIndex = rand.nextInt(lastNameArr.length);
        int fatherFirstNameIndex = rand.nextInt(firstNameArr.length);
        int fatherMiddleNameIndex = rand.nextInt(middleNameArr.length);
        int fatherOccupationIndex = rand.nextInt(occupationArr.length);
        int fatherOfficePhoneNoIndex = rand.nextInt(contactNoArr.length);
        int fatherMobileNoIndex= rand.nextInt(contactNoArr.length);
        int isFatherContactInCaseEmergencyIndex = rand.nextInt(isContactIncaseOfEmergencyArr.length);
        
        int motherLastNameIndex = rand.nextInt(lastNameArr.length);
        int motherFirstNameIndex = rand.nextInt(firstNameArr.length);
        int motherMiddleNameIndex = rand.nextInt(middleNameArr.length);
        int motherOccupationIndex = rand.nextInt(occupationArr.length);
        int motherOfficeNoIndex = rand.nextInt(contactNoArr.length);
        int motherMobileNoIndex = rand.nextInt(contactNoArr.length);
        int isMotherContactInCaseEmergencyIndex = rand.nextInt(isContactIncaseOfEmergencyArr.length);
        
        String studentType = (studentTypeArr[studentTypeIndex]);
        String lastName = (lastNameArr[lastNameIndex]);
        String firstName = (firstNameArr[firstNameIndex]);
        String middleName = (middleNameArr[middleNameIndex]);
        String dateOfBirth = getDobOf(level);
        String placeOfBirth = (placeOfBirthArr[placeOfBirthIndex]);
        String nationality = (nationalityArr[nationalityIndex]);
        String religion = (religionArr[religionIndex]);
        String gender = (genderArr[genderIndex]);
        String fatherLastName = (lastNameArr[fatherLastNameIndex]);
        String fatherFirstName = (firstNameArr[fatherFirstNameIndex]);
        String fatherMiddleName = (middleNameArr[fatherMiddleNameIndex]);
        String fatherOccupation = (occupationArr[fatherOccupationIndex]);
        String fatherOfficePhoneNo = (contactNoArr[fatherOfficePhoneNoIndex]);
        String fatherMobileNo = (contactNoArr[fatherMobileNoIndex]);
        int isFatherContactIncaseOfEmergency = (isContactIncaseOfEmergencyArr[isFatherContactInCaseEmergencyIndex]);
        String motherLastName = (lastNameArr[motherLastNameIndex]);
        String motherFirstName = (firstNameArr[motherFirstNameIndex]);
        String motherMiddleName = (middleNameArr[motherMiddleNameIndex]);
        String motherOccupation = (occupationArr[motherOccupationIndex]);
        String motherOfficePhoneNo = (contactNoArr[motherOfficeNoIndex]);
        String motherMobileNo = (contactNoArr[motherMobileNoIndex]);
        int isMotherContactIncaseOfEmergency = (isContactIncaseOfEmergencyArr[isMotherContactInCaseEmergencyIndex]);
                
        Registration registration = new Registration();
//        registration.setBirthday(dateOfBirth);
        registration.setPlaceOfBirth(placeOfBirth);
        registration.setNationality(nationality);
        registration.setReligion(religion);
        registration.setFatherFirstName(fatherFirstName);
        registration.setFatherLastName(fatherLastName);
        registration.setFatherMiddleName(fatherMiddleName);
        registration.setFatherMobileNo(fatherMobileNo);
        registration.setFatherOccupation(fatherOccupation);
        registration.setFatherOfficePhoneNo(fatherOfficePhoneNo);
        registration.setIsFatherContactInCaseEmergency((isFatherContactIncaseOfEmergency == 1));
        registration.setMotherFirstName(motherFirstName);
        registration.setMotherLastName(motherLastName);
        registration.setMotherMiddleName(motherMiddleName);
        registration.setMotherMobileNo(motherMobileNo);
        registration.setMotherOccupation(motherOccupation);
        registration.setMotherOfficePhoneNo(motherOfficePhoneNo);
        registration.setIsMotherContactInCaseEmergency(isMotherContactIncaseOfEmergency==1);
        registration.setStudentType(studentType);
        registration.setFirstName(firstName);
        registration.setLastName(lastName);
        registration.setMiddleName(middleName);
        registration.setGender(gender);
        registration.setGradeLevelNo(gradeLevelId);
        registration.setSchoolYearYearFrom(schoolYearId);
        
        return registration;
    }
    
    private String getDobOf(int gradeLevel){
        String dateOfBirth = "";
        Random random = new Random();

        int[] dayArr = {10,11,12,13,14,15,16,17,18,19,20,21,22,23,24,25,26,27,28};
        int [] monthArr = {1,2,3,4,5,6,7,8,9,10,11};
        int dayIndex = random.nextInt(dayArr.length);
        int monthIndex = random.nextInt(monthArr.length);
        int month = monthArr[monthIndex];
        int day = dayArr[dayIndex];
        
        int[] kindergartenArr = {2012};
        int[] elementaryArr = {2006, 2007, 2008, 2009, 2010, 2011};
        int[] juniorHsArr = {2002, 2003, 2004, 2005, 2006};
        int kindergartenIndex = random.nextInt(kindergartenArr.length);
        int elementaryIndex = random.nextInt(elementaryArr.length);
        int juniorHsIndex = random.nextInt(juniorHsArr.length);
        
        int kindergartenYear = kindergartenArr[kindergartenIndex];
        int elementaryYear = elementaryArr[elementaryIndex];
        int juniorHsYear = juniorHsArr[juniorHsIndex];

        if (gradeLevel == 0) {
            dateOfBirth = (kindergartenYear + "") + "-" + (month + "") + "-" + (day + "");
        } else if (gradeLevel <= 6) {
            dateOfBirth = (elementaryYear + "") + "-" + (month + "") + "-" + (day + "");
        } else if (gradeLevel <= 10) {
            dateOfBirth = (juniorHsYear + "") + "-" + (month + "") + "-" + (day + "");
        }
        return dateOfBirth;
    }

    String[] firstNameArr = {
        "Rikki", "Ingrid", "Erwin", "Antionette", "Leonel", "Shavon", "Teddy", "Patricia",
        "Jesica", "Moses", "Dorothea", "Donte", "Maude", "Ed", "Jade", "Sean", "Ebonie", "Carlton",
        "Macy", "Stacee", "Marshall", "Hae", "Elsie", "Denna", "Tonja", "Sunday", "Alecia", "Michaela",
        "Elmo", "Leonore", "Lynnette", "Frederick", "Myles", "Rosa", "Merissa", "Joanie", "Pearle", "Margorie",
        "Maren", "Ludivina", "Lezlie", "Setsuko", "Winford", "Archie", "Marcellus", "Johnsie", "Arica", "Ezra",
        "Joana", "Brinda", "Stormy", "Zena", "Sibyl", "Janine", "Kaleigh", "Shauna", "Jeanice",
        "Lila", "Lourie", "Ruth", "Pattie", "Lilliana", "Elma", "Eve", "Apolonia", "Winter", "Elena", "Delcie",
        "Carisa", "Celestine", "Karrie", "Serita", "Ronda", "Idalia", "Bridgette", "Lillian", "Melia",
        "Mariel", "Mandi", "Jana", "Soon", "Carlota", "Meghan", "Venetta", "Yoshiko", "Janessa",
        "Loyce", "Vilma", "Ai", "Edith", "Tamekia", "Latashia", "Odette", "Marissa", "Kanisha",
        "Maudie", "Sudie", "Madeleine", "Rona", "Maragret"
    };

    String[] middleNameArr = {
        "Tedi", "Frans", "Cecile", "Lindy", "Crissie", "Ransell", "Indira", "Efrem", "Millie", "Morley", "Harriot",
        "Scott", "Latia", "Bernardo", "Mara", "Townie", "Adaline", "Marchall", "Sidonia", "Mano", "Atlanta", "Roderich",
        "Tootsie", "Conny", "Florette", "Ruddy", "Audi", "Tull", "Birgit", "Rogers", "Melantha", "Kendal", "Astra",
        "Riccardo", "Jocelyn", "Clim", "Corliss", "Orion", "Aurlie", "Loydie", "Aubrey", "Darrick", "Cathie", "Claire",
        "Clem", "Skippie", "Deborah", "Harvey",
        "Maegan", "Barny", "Casey", "Michele", "Asia", "Quinton", "Coralie", "Oran", "Charlotta", "Morse", "Mallorie",
        "Morey", "Jessie", "Griffin", "Phyllys", "Pen", "Cthrine", "Yuma", "Simonette", "Burk", "Merrielle", "Craggie",
        "Deirdre", "Thornie", "Katerina", "Alexio", "Brinn", "Derrik"};

    String[] lastNameArr = {
        "John Chiappini", "Bilodeau", "Mcnicol", "Shanley", "Stigler", "Badulescu", "Baskey", "Arreguin", "Yaqub",
        "Burke", "Giesbrecht", "Marston", "Markussen", "Fonseca", "Lackner", "Stilo", "Norato", "Cady", "Lueders-booth",
        "Udall", "Fuchs", "Brake", "Venne", "Tanenbaum", "Courval", "Compton", "Signon", "Lachenbruch", "Wallander",
        "Hulbert", "Lockhart", "Verdile", "Amadeo", "Polya", "Milioli", "Sedcole", "Ulam", "Klipp", "Deptula", "Sparda",
        "Valdez", "Kauppi", "Morin", "Bronicki", "Wilkin", "Lamarche", "Naus", "Korzennik", "Thibodeau", "Glider", "Ebeling",
        "Trautman", "Stewart", "Leis", "Dernburg", "Sassone", "Clear", "Rickles", "Benvenisty", "Russo", "Roussinos", "Lubit",
        "Hiles", "Cepko", "Prentice", "Flowers", "Still", "Upsdell", "Slosser", "Valelly", "Bertram", "Orfe", "Liss", "Scanlan",
        "Plummer", "Amsden", "Pesavento", "Alldredge", "Persky", "Gurish", "Metropolis", "Maddox", "Decierdo", "Teutsch",
        "Zarate", "Glider", "Funcke", "Lord", "Hamel", "Balshaw", "Mccarty", "Kasper", "Schluter", "Von mehren", "Baino",
        "El-shaarawi", "Brewer", "Carty", "Cocuzzo"};
    
    String[] occupationArr = {
        "Custom Wood Stair Builder", "Statistical Analyst", "Professor of Communication and Writing", "Cardroom Manager",
        "Social Psychologist", "Emcee", "Short Story Writer", "Lead Abatement Worker", "Multi-operation Forming Machine Setter", 
        "Piano Professor", "Nursing Service Director", "Gas Derrick Operator", "Molasses Preparer",
        "Drywall Applicator", "Chiropractor", "Voice-Over Artist", "Amusement Park Worker", "Tobacco Buyer", "Busser",
        "Line Haul Driver", "Freight Clerk", "Ambulance Driver-Paramedic", "AIDS Counselor", "Licensed Clinical Mental Health Counselor",
        "Strike Warfare/Missile Systems Officer", "Custom Wood Stair Builder", "Statistical Analyst",
        "Professor of Communication and Writing", "Cardroom Manager", "Social Psychologist", "Emcee",
        "Short Story Writer", "Lead Abatement Worker", "Multi-operation Forming Machine Setter", "Piano Professor",
        "Nursing Service Director", "Gas Derrick Operator", "Molasses Preparer", "Drywall Applicator", "Chiropractor",
        "Voice-Over Artist", "Amusement Park Worker", "Tobacco Buyer", "Busser", "Line Haul Driver", "Freight Clerk",
        "Ambulance Driver-Paramedic", "AIDS Counselor", "Licensed Clinical Mental Health Counselor",
        "Strike Warfare/Missile Systems Officer"
    };
    
    String[] contactNoArr = {
        "202-555-0167", "202-555-0114", "202-555-0183", "202-555-0189", "202-555-0163", "202-555-0100", "202-555-0102",
        "202-555-0197", "202-555-0112", "202-555-0173", "202-555-0161", "202-555-0116", "202-555-0173", "202-555-0189",
        "202-555-0137", "202-555-0146", "202-555-0174", "202-555-0186", "202-555-0145", "202-555-0195", "202-555-0104",
        "202-555-0139", "202-555-0195", "202-555-0185", "202-555-0169", "202-555-0179", "202-555-0175", "202-555-0198",
        "202-555-0100", "202-555-0138", "202-555-0170", "202-555-0131", "202-555-0110", "202-555-0199", "202-555-0184",
        "202-555-0183", "202-555-0123", "202-555-0148", "202-555-0132", "202-555-0162", "202-555-0153", "202-555-0190",
        "202-555-0179", "202-555-0105", "202-555-0135", "202-555-0145", "202-555-0125", "202-555-0165", "202-555-0123",
        "202-555-0102", "202-555-0131", "202-555-0101", "202-555-0125", "202-555-0114", "202-555-0181", "202-555-0187",
        "202-555-0195", "202-555-0145", "202-555-0152", "202-555-0179", "202-555-0177", "202-555-0109", "202-555-0175",
        "202-555-0150", "202 - 555 - 0150"
    };
    
    String[] placeOfBirthArr = {
        "Abra", "Capiz", "Lanao del Sur", "Rizal",
        "Agusan del Norte", "Catanduanes", "Leyte", "Romblon",
        "Agusan del Sur", "Cavite", "Maguindanao", "Samar",
        "Aklan", "Cebu", "Marinduque", "Sarangani",
        "Albay", "Compostela Valley", "Masbate", "Shariff Kabunsuan",
        "Antique", "Cotabato", "Metro Manila", "Siquijor",
        "Apayao", "Davao del Norte", "Misamis Occidental", "Sorsogon",
        "Aurora", "Davao del Sur", "Misamis Oriental", "South Cotabato",
        "Basilan", "Davao Oriental", "Mountain Province", "Southern Leyte",
        "Bataan", "Dinagat Islands", "Negros Occidental", "Sultan Kudarat",
        "Batanes", "Eastern Samar", "Negros Oriental", "Sulu",
        "Batangas", "Guimaras", "Northern Samar", "Surigao del Norte",
        "Benguet", "Ifugao", "Nueva Ecija", "Surigao del Sur",
        "Biliran", "Ilocos Norte", "Nueva Vizcaya", "Tarlac",
        "Bohol", "Ilocos Sur", "Occidental Mindoro", "Tawi-Tawi",
        "Bukidnon", "Iloilo", "Oriental Mindoro", "Zambales",
        "Bulacan", "Isabela", "Palawan", "Zamboanga del Norte",
        "Cagayan", "Kalinga", "Pampanga", "Zamboanga del Sur",
        "Camarines Norte", "La Union", "Pangasinan", "Zamboanga Sibugay",
        "Camarines Sur", "Laguna", "Quezon",
        "Camiguin", "Lanao del Norte", "Quirino"
    };
    
    String[] schoolLastAttendedArr = {
        "Beacon School, Manila", "Brent International School - Baguio", "Brent International School - Manila (Pasig Campus)", 
        "Brent International School - Manila (South Campus)", "Brent International School - Subic", "British School Manila", 
        "Cebu International School, Cebu", "German School Manila (Deutsche Schule Manila)", "Esteban International School, Manila", 
        "European International School, Manila", "Faith Academy, Manila", "French School Manila (L'Ecole Francaise de Manille)", 
        "Harvest Christian School International, Cebu", "Indang British School, Cavite", "International British Academy, Cavite", 
        "International School Manila", "Mahatma Gandhi International School, Manila", "Manila Japanese School", 
        "Reedley International School, Manila", "Singapore International School - Manila", "Southville International School Manila",
        "Thames International Business School - Manila", "Adamson University -.Manila", "Adventist University of the Philippines - Silang, Cavite", 
        "Alliance Biblical Seminary - Quezon City", "AMEC Bicol Christian College of Medicine - Legazpi, Albay", 
        "Asia Pacific Theological Seminary - Baguio City, Benguet", "Asia Pacific Nazarene Theological Seminary - Taytay, Rizal",
        "Asia Pacific Theological Seminary - Baguio City", "Asian Theological Seminary - Quezon City, Philippines", 
        "Baptist Theological College", "Bethel Bible College - Valenzuela, Metro Manila",
        "Bible Baptist Church Kindergarten - Quezon Ave, Iligan, Lanao Del Norte", 
        "Binalbagan Catholic College - Binalbagan, Negros Occidental", 
        "Brent International School - Subic", "Brent International School - Manila", 
        "Brent School International - Baguio", "Christ the King College - Gingoog City, Misamis Oriental", 
        "Colegio de San Pasual Baylon - Obando, Bulacan", "Colegio San Antonio - Binan, Laguna", 
        "College of the Holy Spirit - Tarlac, Tarlac", "Davao Bible College - Sasa, Davao City, Davao Del Sur", 
        "Davao Christian High School, Philippines", "Doane Baptist Seminary - Iloilo City, Iloilo", 
        "Faith Academy - Manila, Philippines", "Grace Christian High School, Philippines", 
        "Great Christian Academy - 747 Sun Valley Drive, Parañaque City, Philippines", 
        "Harvest Christian School International - Cebu City", "Holy Spirit School - Tagbilaran City, Bohol", 
        "Immanuel Mission International School - Dagong, Carmen, Cagayan de Oro City", 
        "International School of Theology-Asia, Quezon City", "Keith Williams Bible College - Ulas, Talomo District, Davao City", 
        "King's Kids Christian Academy - Sitio Looban, Cuta, Batangas City", "La Salle - Mandaluyong City, Boys, Catholic", 
        "Lourdes College of Cagayan de Oro - Cagayan de Oro City", "Mindanao International Christian Academy - Davao City", 
        "Mt. Moriah Christian Academy - Taguig City", "New Life Christian School of Cavite - 314 Maguyam, Silang Cavite 4118", 
        "Notre Dame of RVM College - Cotabato City", "Philippine Baptist Theological Seminary - Baguio City", 
        "Presbyterian Theological Seminary - Dasmarinas Cavite", "Sacred Heart College - Lucena City, Quezon", 
        "Sacred Heart School - Bauang, La Union", "Sacred Heart School of Mindanao", "Sacred Heart Seminary - Palo, Leyte", 
        "San Jacinto Seminary - Cagayan", "Silliman University - Dumaguete City", "St. Columban's College - Lingayen, Pangasinan", 
        "St. Dominic College - Basco, Batanes", "St. Dominic College - Bula, Camarines Sur", "St. Ezekiel Moreno Noviate Recoletos - Antipolo, Rizal", 
        "St. Ferdinand College - Ilagan, Isabela", "St. Francis College - Guihulngan, Negros Oriental", 
        "St. Francis de Sales Major Seminary - Lipa City, Batangas", "St. Francis of Assisi College System - Las Pinas, Metro Manila", 
        "St. Francis Xavier College Seminary - Davao City, Davao del Sur", "St. Gregory the Great Seminary – Barangay Panal, Tabaco City (Catholic)", 
        "St. Gabriel College School of Nursing - Kalibo, Aklan", "St. John and Paul Colleges - Makati City, Metro Manila", 
        "St. John the Evangelist-Theology - Palo, Leyte", "St. Joseph College - Cagayan", "St. Joseph College - Cavite City, Cavite", 
        "St. Joseph Formation House, Diocesan - Quezon City, Metro Manila", "St. Joseph Institute of Technology - Bislig, Surigao del Sur", 
        "St. Joseph Institute of Technology - Butuan City, Agusan del Norte", "St. Joseph’s College - Manila, Metro Manila", 
        "St. Joseph’s College - Quezon City, Metro Manila", "St. Jude College & General Hospital - Manila, Metro Manila", 
        "St. Jude Thaddeus Inst.of Technology - Surigao City, Surigao del Norte", "St. Jude’s College of Nursing - Manila, Metro Manila", 
        "St. Louis Anne Colleges - San Pedro, Laguna", "St. Louis College - San Fernando, La Union", "St. Louis University - Baguio City, Benguet", 
        "St. Louise de Marillac College - Sorsogon, Sorsogon", "St. Luke College Foundation - Tarlac, Tarlac", 
        "St. Luke’s College of Medicine - Manila, Metro Manila", 
        "St. Mary’s College - Meycauayan, Bulacan", "St. Mary's University - Bayombong, Nueva Vizcaya", "St. Michael College - Binan, Laguna", 
        "St. Michael’s College - Iligan City, Lanao del Norte", "St. Michael's College - Bulacan", "St. Paul College - Iloilo City, Iloilo", 
        "St. Paul College - Manila, Metro Manila", "St. Paul College - Quezon City, Metro Manila", "St. Paul's College - Dumaguete City, Negros Oriental", 
        "St. Paul University - Tuguegarao, Cagayan", "St. Paul's Business School of Tacloban - Tacloban City, Leyte", "St. Peter's College - Apalit, Pampanga", 
        "St. Peter's College of Balingasag", "St. Peter's College - Iligan", "St. Peter's College - Ormoc City, Leyte", "St. Peter Christian School - Quezon City", 
        "St. Rita College - Manila, Metro Manila", "St. Rita Hospital College of Nursing - Manila, Metro Manila", "St. Scholasticas College - Manila, Metro Manila", 
        "St. Theresa College - Tandag, Surigao del Sur", "St. Theresa’s College - Cebu City, Cebu", "St. Vincent College Seminary - Quezon City, Metro Manila", 
        "St. Vincent de Paul College - Bislig, Surigao del Sur", "St. Vincent de Paul College Seminary - Calbayog City, Western Samar", 
        "Tabernacle of Faith Christian Academy", "Tarlac Christian School - Tarlac City", "Trinity University of Asia - Quezon City)", 
        "University of the Immaculate Conception - Father Selga Street, Davao City", "Union Theological Seminary - Dasmarinas, Cavite", 
        "Wesleyan University - Cabanatuan City, Nueva Ecija", "Xavier School - Greenhills, San Juan, Metro Manila "
    };
    
    String[] nationalityArr
            = {
                "Afghan", "Albanian", "Algerian", "American", "Andorran", "Angolan", "Antiguans", "Argentinean", "Armenian",
                "Australian", "Austrian", "Azerbaijani", "Bahamian", "Bahraini", "Bangladeshi", "Barbadian", "Barbudans",
                "Batswana", "Belarusian", "Belgian", "Belizean", "Beninese", "Bhutanese", "Bolivian", "Bosnian", "Brazilian",
                "British", "Bruneian", "Bulgarian", "Burkinabe", "Burmese", "Burundian", "Cambodian", "Cameroonian", "Canadian",
                "CapeVerdean", "CentralAfrican", "Chadian", "Chilean", "Chinese", "Colombian", "Comoran", "Congolese", "Congolese",
                "CostaRican", "Croatian", "Cuban", "Cypriot", "Czech", "Danish", "Djibouti", "Dominican", "Dominican", "Dutch",
                "Dutchman", "Dutchwoman", "EastTimorese", "Ecuadorean", "Egyptian", "Emirian", "EquatorialGuinean", "Eritrean",
                "Estonian", "Ethiopian", "Fijian", "Filipino", "Finnish", "French", "Gabonese", "Gambian", "Georgian", "German",
                "Ghanaian", "Greek", "Grenadian", "Guatemalan", "Guinea-Bissauan", "Guinean", "Guyanese", "Haitian", "Herzegovinian",
                "Honduran", "Hungarian", "I-Kiribati", "Icelander", "Indian", "Indonesian", "Iranian", "Iraqi", "Irish", "Irish",
                "Israeli", "Italian", "Ivorian", "Jamaican", "Japanese", "Jordanian", "Kazakhstani", "Kenyan", "KittianandNevisian",
                "Kuwaiti", "Kyrgyz", "Laotian", "Latvian", "Lebanese", "Liberian", "Libyan", "Liechtensteiner", "Lithuanian",
                "Luxembourger", "Macedonian", "Malagasy", "Malawian", "Malaysian", "Maldivan", "Malian", "Maltese", "Marshallese",
                "Mauritanian", "Mauritian", "Mexican", "Micronesian", "Moldovan", "Monacan", "Mongolian", "Moroccan", "Mosotho",
                "Motswana", "Mozambican", "Namibian", "Nauruan", "Nepalese", "Netherlander", "NewZealander", "Ni-Vanuatu",
                "Nicaraguan", "Nigerian", "Nigerien", "NorthKorean", "NorthernIrish", "Norwegian", "Omani", "Pakistani", "Palauan",
                "Panamanian", "PapuaNewGuinean", "Paraguayan", "Peruvian", "Polish", "Portuguese", "Qatari", "Romanian", "Russian",
                "Rwandan", "SaintLucian", "Salvadoran", "Samoan", "SanMarinese", "SaoTomean", "Saudi", "Scottish", "Senegalese",
                "Serbian", "Seychellois", "SierraLeonean", "Singaporean", "Slovakian", "Slovenian", "SolomonIslander", "Somali",
                "SouthAfrican", "SouthKorean", "Spanish", "SriLankan", "Sudanese", "Surinamer", "Swazi", "Swedish", "Swiss", "Syrian",
                "Taiwanese", "Tajik", "Tanzanian", "Thai", "Togolese", "Tongan", "TrinidadianorTobagonian", "Tunisian", "Turkish",
                "Tuvaluan", "Ugandan", "Ukrainian", "Uruguayan", "Uzbekistani", "Venezuelan", "Vietnamese", "Welsh", "Welsh",
                "Yemenite", "Zambian", "Zimbabwean"

            };
    
    String [] religionArr = {
        "Catholic","Protestant","Taoism","Born again","Muslim"
    };
    
    String [] studentTypeArr = {
        "New","Transferee"
    };
    
    int[] isContactIncaseOfEmergencyArr = {1,0};
    String[] genderArr = {"Male","Female"};
}
