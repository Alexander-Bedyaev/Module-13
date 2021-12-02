import java.util.Random;

public class PhoneBook {
    private static final String[] nameArray = new String[]{"Петр", "Максим", "Станислав", "Клим", "Александр"};
    public String[] namePatArray;
    public String[] phoneNumberArray;

    PhoneBook() {
        namePatArray = new String[nameArray.length];
        namePatArray = getNamePatArray();
        phoneNumberArray = new String[nameArray.length];
        phoneNumberArray = getPhoneNumbers();
    }

    private String[] getNamePatArray() {
        String[] patronymicArray = new String[nameArray.length];
        Random random = new Random();
        String ovich = "ович";

        for (int i = 0; i < patronymicArray.length; i++) {
            patronymicArray[i] = nameArray[i] + ovich;
        }
        for (int i = 0; i < nameArray.length; i++) {
            int j = random.nextInt(nameArray.length - 1);
            namePatArray[i] = nameArray[i] + " " + patronymicArray[j];
        }
        return namePatArray;
    }

    private String[] getPhoneNumbers() {
        Random random = new Random();

        for (int i = 0; i < phoneNumberArray.length; i++) {
            phoneNumberArray[i] = "+7(" + (random.nextInt(99) + 900) + ")" +
                    String.format("%07d", random.nextInt(9999999));
        }
        return phoneNumberArray;
    }


}

